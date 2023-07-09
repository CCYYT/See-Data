package protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.ArrayUtils;

public class FrameHeaderJointBytesCodec extends ChannelInboundHandlerAdapter {
    private byte[] bytes;
    public FrameHeaderJointBytesCodec(byte[] bytes){
        this.bytes = bytes;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        buf.release();
        if(req.length>=16) super.channelRead(ctx,ArrayUtils.addAll(bytes,req));
    }
}
