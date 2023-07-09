package util;

import queue.MediumProducer;

import java.io.IOException;
import java.util.Arrays;

public class byteUtil {
    private static int BYTES_CAPACITY = 32768;
    private static byte[] bytes = new byte[BYTES_CAPACITY];
    private static int size = 0;
    private static byte[] checkByte = {-1,-40,-1,-32,0,16,74};
    private static int checkIndex =0;

    public static void sendBytes(byte[] req) throws IOException {
//        System.out.println("数据: "+Arrays.toString(req));
//        System.out.println("长度: "+req.length);
        for (int i = 0; i < req.length; i++) {
            bytes[size++] = req[i];
            if(req[i] == checkByte[checkIndex]){
                if(checkIndex==checkByte.length-1){
                    if(size == checkByte.length){
                        checkIndex = 0;
                    }else{
                        for (int j = 0; j < checkByte.length; j++) {
                            bytes[size] = 0;
                            size--;
                        }
                        MediumProducer.publishMsgByBytes(bytes);
                        bytes = new byte[BYTES_CAPACITY];
                        for (int j = 0; j < checkByte.length; j++) {
                            bytes[j] = checkByte[j];
                        }
                        size = checkByte.length;
                    }
                }else {
                    if(checkIndex==0 || i == 0){
                        checkIndex++;
                    }else if(req[i-1] == checkByte[checkIndex-1]){
                        checkIndex++;
                    }else {
                        checkIndex = 0;
                    }
                }
                continue;
            }
            checkIndex = 0;
        }
    }

}
