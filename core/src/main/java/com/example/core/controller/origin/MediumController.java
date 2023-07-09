package com.example.core.controller.origin;

import com.example.common.result.Code;
import com.example.common.result.Result;
import com.example.core.websocket.MediaWebSocket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
* 通过request轮询推流
* */
@RestController
@RequestMapping("/medium")
public class MediumController {
    @Value("${document.uploadPath}")
    private String path;

    private static ObjectMapper objectMapper = new ObjectMapper();

    String classpath = ResourceUtils.getURL("classpath:static").getPath();

    public MediumController() throws FileNotFoundException {
    }

    @PostMapping("imageUpload")
//    @PostMapping("video")
    public void imageUpload(HttpServletRequest request) throws IOException {
//        BufferedReader br = request.getReader();
//        String str, wholeStr = "";
//        while((str = br.readLine()) != null){
//            wholeStr += str;
//        }
//        System.out.println(wholeStr);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        try (ServletInputStream sis = request.getInputStream()) {

            OutputStream os = new FileOutputStream(classpath + path + dateFormat.format(date)+".jpg");
            BufferedOutputStream bos = new BufferedOutputStream(os);

            byte[] buf = new byte[1024];
            int length = 0;
            length = sis.readLine(buf, 0, buf.length);//使用sis的读取数据的方法
            while (length != -1) {
                bos.write(buf, 0, length);
                length = sis.read(buf);
            }
            bos.close();
            os.close();
        }

    }

    @PostMapping("video")
    public void videoUpload(HttpServletRequest request) throws IOException {
        ServletInputStream sis = request.getInputStream();
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len= sis.readLine(buff,0,buff.length);
        while (len != -1){
            swapStream.write(buff,0,len);
            len = sis.read(buff);
        }
        byte[] bf = swapStream.toByteArray();
        sis.close();
        swapStream.close();
        MediaWebSocket.broadcast(bf);


//        byte[] buf = new byte[1024];
//        int length = 0;
//        length = sis.readLine(buf, 0, buf.length);//使用sis的读取数据的方法
//        while (length != -1) {
//            bos.write(buf, 0, length);
//            length = sis.read(buf);
//        }



    }

    @GetMapping("image")
    public Result getImage() throws IOException {
        File f = new File(classpath + path+"1.jpeg");
        byte[] buffer = new byte[(int) f.length()];
        InputStream is = new FileInputStream(classpath + path+"1.jpeg");
        is.read(buffer);
        is.close();
//        String s = objectMapper.writeValueAsString(buffer);
        String s0 = Arrays.toString(buffer);
//        byte[] b = new byte[]{65};
//        String s1 = Arrays.toString(b);
//        String s2 = objectMapper.writeValueAsString(b);
        return new Result(buffer.length>0? Code.GET_OK:Code.GET_ERR,s0);
    }

    @GetMapping("images")
    public String[] getImages(){
        File file = new File(classpath + path);
        if(!file.exists()){
            file.mkdirs();
        }
        return Arrays.stream(file.list())
                .filter(s -> s.split("\\.").length>1)
                .map(s -> path+s)
                .toArray(String[]::new);

    }

    @DeleteMapping("images")
    public Result delImage(@RequestParam String imageUrl){
        File file = new File(classpath+imageUrl);
        return new Result(file.delete()?Code.DELETE_OK:Code.DELETE_ERR,"");
    }
}
