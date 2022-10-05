package com.navyliu.widget.unit5Lsn1SharePreferences;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;

class SDFileHelper {
    final String TAG = this.getClass().getName();

    private Context mContent;

    SDFileHelper() {
    }

    public SDFileHelper(Context context) {
        super();
        this.mContent = context;
    }

    /**
     * @param filename
     * @param content
     * @throws IOException
     */
    public void saveFileToSD(String filename, String content) throws IOException {
        // 如果手机已经插入了sd卡，并且具有读写sd卡的权限
        String externalStorageState = Environment.getExternalStorageState();

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath()
                    + "/" + filename;
            File file = new File(filename);

//            String[] command = {"chmod", "777", file.toString()};
//            ProcessBuilder builder = new ProcessBuilder(command);
//            try {
//                builder.start();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (!file.exists()){
//                File dir = new File(file.getParent());
//                dir.mkdirs();
//                file.createNewFile();
//            }
            // 这里不要用openFileOutput，那个是王手机内存中写数据
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(content.getBytes());
            // 将String字符串以字节流的形式写入到输入流中
            stream.close();
        } else {
            Log.d(TAG, "saveFileToSD: SD卡不存在或者不可读写");
        }
    }


    public String readFromSD(String filename) throws IOException {
        StringBuffer sb = new StringBuffer("");
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath()
                    + "/" + filename;
            // 打开文件输入流
            File file = new File(filename);
            FileInputStream stream = new FileInputStream(file);
            byte[] temp = new byte[1024];

            int len = 0;
            // 读取文件内容
            while ((len = stream.read(temp)) > 0) {
                sb.append(new String(temp, 0, len));
            }
            // 关闭输入流
            stream.close();
        }
        return sb.toString();
    }

}
