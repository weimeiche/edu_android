package com.navyliu.widget.unit8Lsn3Http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class PostUtils {

    public static String RequestCityByPost(String path, String mode, String pid, String lvl) {
        String msg = "";
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
            // 设置请求方式，请求超时信息
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5 * 1000);
            connection.setConnectTimeout(5 * 1000);
            // 设置运行输入、输出
            connection.setDoInput(true);
            connection.setDoInput(true);
            // Post方式不能缓存，需要手动设置为false
            connection.setUseCaches(false);
            // 拼接要请求的数据
            String data = "mode=" + URLEncoder.encode(mode, "UTF-8") +
                    "&pid=" + URLEncoder.encode(pid, "UTF-8") +
                    "&lvl=" + URLEncoder.encode(lvl, "UTF-8");
            // 还可以设置一些请求头信息
            // 获取输出流
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data.getBytes());
            outputStream.flush();
            if (connection.getResponseCode() == 200) {
                // 获取相应的输入流对象
                InputStream inputStream = connection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte[] buffer = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = inputStream.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    message.write(buffer, 0, len);
                }
                // 释放资源
                inputStream.close();
                message.close();
                // 返回字符串
                msg = new String(message.toByteArray());
                return msg;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
