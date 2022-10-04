package com.navyliu.widget.unit8Lsn3Http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class GetData {
    // 定义一个获取网络图片数据的方法
    public static byte[] getImage(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        // 设置连接超时未5s
        conn.setConnectTimeout(50*1000);
        // 设置请求类型为get类型
        conn.setRequestMethod("GET");
        // 判断请求URL是否成功
        if (conn.getResponseCode() != 200){
            throw  new RuntimeException("请求url失败");
        }
        InputStream inputStream = conn.getInputStream();
        byte[] bytes = StreamTool.read(inputStream);
        inputStream.close();
        return bytes;
    }

    // 获取网页的HTML源代码
    public static String getHtml(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(50*1000);
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() == 200){
            InputStream inputStream = connection.getInputStream();
            byte[] bytes = StreamTool.read(inputStream);
            String html = new String(bytes, "UTF-8");
            return html;
        }
        return null;
    }
}
