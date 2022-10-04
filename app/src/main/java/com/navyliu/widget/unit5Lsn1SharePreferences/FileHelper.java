package com.navyliu.widget.unit5Lsn1SharePreferences;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class FileHelper {
    private Context mContext;

    public FileHelper() {

    }

    public FileHelper(Context context) {
        super();
        this.mContext = context;
    }

    /**
     * 保存文件的方法，将内容写到文件中，使用输出流
     *
     * @param filename
     * @param content
     * @throws IOException
     */
    public void save(String filename, String content) throws IOException {
        // 设置为私有模式，创建出来的文件只能被本应用访问，同时会覆盖源文件
        FileOutputStream fileOutputStream = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
        // 将String字符串以字节流的形式写入到输出流中
        fileOutputStream.write(content.getBytes());
        // 关闭输出流
        fileOutputStream.close();
    }

    public void append(String filename, String content) throws IOException {
        FileOutputStream fileOutputStream = mContext.openFileOutput(filename, Context.MODE_APPEND);
        fileOutputStream.write(content.getBytes());
        fileOutputStream.close();
    }

    /**
     * 读取文件内容
     * @param filename
     * @return
     * @throws IOException
     */
    public String read(String filename) throws IOException {
        // 打开文件输出流
        FileInputStream fileInputStream = mContext.openFileInput(filename);
        byte[] bytes = new byte[1024]; //
        StringBuffer buffer = new StringBuffer("");
        int len = 0;
        // 读取文件内容：
        while ((len = fileInputStream.read(bytes)) > 0) {
            buffer.append(new String(bytes, 0, len));
        }
        // 关闭输入流
        fileInputStream.close();
        return buffer.toString();
    }


}
