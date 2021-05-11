package com.navyliu.widget.unit8Lsn3Http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {
    // 从流中读取数据
    public static byte[] read(InputStream inputStream) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer,0,len);
        }
        inputStream.close();
        return outputStream.toByteArray();
    }
}
