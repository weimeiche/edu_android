package com.navyliu.widget.unit5Lsn1SharePreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.navyliu.widget.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import pub.devrel.easypermissions.EasyPermissions;

public class SharePreferencesActivity extends Activity {

    private final String TAG = this.getClass().getName();

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);

        /**
         * 向SharePreferences中保存数据
         *  1、通过getSharedPreferences获取SharePreference；
         *  2、通过.edit()获取编辑器；
         *  3、用put方法想编辑器中写入数据；
         *  4、用commit()提交
         */
        SharedPreferences user = this.getSharedPreferences("user1"
                , Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        edit.putString("username", "用户名");
        edit.commit();

        /**
         * 读取SharePreferences中保存数据
         *  1、通过getSharedPreferences获取SharePreference；
         *  2、通过get方法获取SharePregerences中保存的值
         */
        SharedPreferences user1 = this.getSharedPreferences("user1"
                , Context.MODE_PRIVATE);
        String username = user1.getString("username", "123");
        String password = user1.getString("password", "123");
        Log.d(TAG, "onCreate: ====username:" + username
                + ", password:" + password);


        try {
            // 向APP中写入数据
            FileHelper fileHelper = new FileHelper(this);
            fileHelper.save("test.txt", "测试数据");

            // 读取APP中文件里面的数据
            String str = fileHelper.read("test.txt");
            Log.d(TAG, "onCreate: ======" + str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 需要申请的权限
         * 1、在app目录下引入第三方库EasyPermissions；
         * 2、在AndroidManifest中的application中写入android:requestLegacyExternalStorage="true"
         */
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, permissions)) {
            try {
                // 向SD卡中写入数据
                SDFileHelper sdFileHelper = new SDFileHelper(this);
                sdFileHelper.saveFileToSD("sd_test.txt", "测试数据sD");

                // 读取SD卡中的数据
                String sd = sdFileHelper.readFromSD("sd_test.txt");
                Log.d(TAG, "onCreate: =========sd_test==" + sd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //第二个参数是被拒绝后再次申请该权限的解释
            //第三个参数是请求码
            //第四个参数是要申请的权限
            EasyPermissions.requestPermissions(this, "必要的权限", 0, permissions);
        }

    }
}