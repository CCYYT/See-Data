import handler.ServerHandler;
import handler.ValidateConnectionHandler;
import handler.brk.ServerHandler_brk;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import protocol.MessageCodec;
import queue.DeviceCommandConsumer;
import util.RabbitUtil;

public class TcpServer {
    private int port;
    private ServerSocketChannel serverSocketChannel;
    public TcpServer(int port){
        this.port = port;
        bind();
    }
    public void bind(){
        //服务端要建立两个group，一个负责接收客户端的连接，一个负责处理数据传输
        //连接处理group
        EventLoopGroup boss = new NioEventLoopGroup();
        //事件处理group
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 绑定处理group
        bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                //保持连接数
                .option(ChannelOption.SO_BACKLOG, 300)
                //有数据立即发送
                .option(ChannelOption.TCP_NODELAY, true)
                //保持连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        ChannelPipeline p = sc.pipeline();
                        //粘包、半包处理器
                        p.addLast(new DelimiterBasedFrameDecoder(65535,true,false,Unpooled.copiedBuffer(new byte[]{-1,0x32})));
//                        p.addLast(new LengthFieldBasedFrameDecoder(65535,6,2,8,2));
                        //协议解码
                        p.addLast(new MessageCodec());
                        //验证连接
                        p.addLast(new ValidateConnectionHandler());
                        //消息处理器
                        p.addLast(new ServerHandler());
//                        p.addLast(new ServerHandler_brk());

                    }
                });
//                //处理新连接
//                .childHandler(new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    protected void initChannel(SocketChannel sc) throws Exception {
//                        // 增加任务处理
//                        ChannelPipeline p = sc.pipeline();
//                        byte[] frameHeader = new byte[]{-1,0x32};
////                        p.addLast(new VideoHandler(32768,new byte[]{-1,-40,-1,-32,0,16,74}));
////                        p.addLast(new VideoHandler());
////                        p.addLast(new JpegHandler());
//                        p.addLast(new DelimiterBasedFrameDecoder(65535,true,false,Unpooled.copiedBuffer(frameHeader)));
//                        p.addLast(new FrameToDataPacketCodec());
////                        p.addLast(new FrameHeaderJointBytesHandler(frameHeader));
////                        p.addLast()
//                        p.addLast(
//                                //使用了netty自带的编码器和解码器
////                                    new StringDecoder(),
////                                    new StringEncoder(),
//                                //心跳检测，读超时，写超时，读写超时
//                                //new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS),
//
//                                //自定义的处理器
//                                new ServerHandler()
//                        );
//                    }
//                });
        //绑定端口，同步等待成功
        ChannelFuture future;
        try {
            future = bootstrap.bind(port).sync();
            if (future.isSuccess()) {
                serverSocketChannel = (ServerSocketChannel) future.channel();
                System.out.println("服务端启动成功，端口："+port);
            } else {
                System.out.println("服务端启动失败！");
            }
            //等待服务监听端口关闭,就是由于这里会将线程阻塞，导致无法发送信息，所以我这里开了线程
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //优雅地退出，释放线程池资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //加载消费者
        Class.forName("queue.DeviceCommandConsumer");
        TcpServer tcpServer = new TcpServer(3322);
    }

}
