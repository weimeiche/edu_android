package com.navyliu.widget.unit5Lsn1SharePreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannedString;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.navyliu.widget.R;

import java.io.IOException;

import pub.devrel.easypermissions.EasyPermissions;

public class SharePreferencesActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    private AppCompatEditText usernameEdit, passwordEdit;
    private AppCompatTextView loginStatusTxt;
    private int READ = 0;
    private int WRITE = 1;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);

        findView();
        initLogin();
    }

    /**
     * 自动填充用户名和密码
     */
    private void initLogin() {
        SharedPreferences user_login = this.getSharedPreferences("user_login", MODE_PRIVATE);
        String username = user_login.getString("username", "");
        String password = user_login.getString("password", "");
        usernameEdit.setText(username);
        passwordEdit.setText(password);
    }

    private void findView() {
        usernameEdit = (AppCompatEditText) this.findViewById(R.id.edit_username);
        passwordEdit = (AppCompatEditText) this.findViewById(R.id.edit_password);
        loginStatusTxt = (AppCompatTextView) this.findViewById(R.id.txt_login_status);
    }


    private void readSharePerferences() {
        /**
         * 读取SharePreferences中保存数据
         *  1、通过getSharedPreferences获取SharePreference；
         *  2、通过get方法获取SharePregerences中保存的值
         */
        SharedPreferences user = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        String username = user.getString("username", "123");
        String password = user.getString("password", "123");
        String stu_no = user.getString("stu_no", "666");
        Log.d(TAG, "onCreate: ====username:" + username
                + ", password:" + password + ", stu_no:" + stu_no);
    }


    private void saveSharePerference() {
        /**
         * 向SharePreferences中保存数据
         *  1、通过getSharedPreferences获取SharePreference；
         *  2、通过.edit()获取编辑器；
         *  3、用put方法想编辑器中写入数据；
         *  4、用commit()提交
         */
        String usernameStr = usernameEdit.getText().toString();
        String passwordStr = passwordEdit.getText().toString();
        if (usernameStr.isEmpty()) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_LONG).show();
            return;
        }
        if (passwordStr.isEmpty()) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show();
            return;
        }

        SharedPreferences user = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = user.edit();
        editor.putString("username", usernameStr);
        editor.putString("password", passwordStr);

        editor.commit();
    }


    private void editFileSD(int writeOrRead) {

        SDFileHelper helper = new SDFileHelper();
        if (writeOrRead == WRITE) {
            String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (EasyPermissions.hasPermissions(this, perms)) {
                // Already have permission, do the thing
                // ...
                Log.d(TAG, "editFileSD: ======================");
                if (writeOrRead == WRITE) {
                    try {
                        helper.saveFileToSD("sd_test.txt", "测试SD卡存储数据121212121");
                        Log.d(TAG, "editFileSD: =======================试SD卡存储数据==");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // Do not have permissions, request them now
                //第二个参数是被拒绝后再次申请该权限的解释
                //第三个参数是请求码
                //第四个参数是要申请的权限
                EasyPermissions.requestPermissions(this, "asdfasd",
                        0, perms);
            }
        } else {
            String str = "";
            try {
                str = helper.readFromSD("sd_test.txt");
                Log.d(TAG, "editFileSD: ======sd_test=================" + str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void editFile(int readOrWrite) {
        try {
            FileHelper fileHelper = new FileHelper(this);
            if (readOrWrite == WRITE) {
                String str = usernameEdit.getText().toString();
                // 向APP中写入数据
                fileHelper.append("test.txt", str);
            }
            if (readOrWrite == READ) {
                // 读取APP中文件里面的数据
                String str = fileHelper.read("test.txt");
                Log.d(TAG, "onCreate: ======" + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_read_file:
                editFile(READ);
                break;
            case R.id.btn_save:
                editFile(WRITE);
                break;
            case R.id.btn_sd:
                editFileSD(WRITE);
                break;
            case R.id.btn_sdread:
                editFileSD(READ);
                break;
            case R.id.btn_write_sp: // 向shareperferences中写入内容
                saveSharePerference();
                break;
            case R.id.btn_readsp: // 读sharepreference的内容
                readSharePerferences();
                break;
            case R.id.btn_login: // 模拟登录
                login();
                break;
            case R.id.btn_edit_password: // 修改密码
                editPassword();
                break;
        }
    }

    /**
     * 修改密码
     */
    private void editPassword() {
        // 输入新旧密码
        String old_password = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        if (old_password.isEmpty()) {
            Toast.makeText(this, "请输入旧密码。", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "请输入新密码。", Toast.LENGTH_LONG).show();
            return;
        }
        // 获取正确的密码
        SharedPreferences user = this.getSharedPreferences("user", MODE_PRIVATE);
        String right_password = user.getString("password", "");

        // 旧密码验证
        if (!old_password.equals(right_password)) {
            Toast.makeText(this, "修改失败，旧密码错误。", Toast.LENGTH_LONG).show();
            return;
        }
        if (!old_password.equals(password)) {
            Toast.makeText(this, "修改失败，新密码不能和旧密码相同。", Toast.LENGTH_LONG).show();
            return;
        }
        SharedPreferences.Editor editor = user.edit();
        editor.putString("password", password);
        editor.commit();
        Toast.makeText(this, "密码修改成功。", Toast.LENGTH_LONG).show();
    }

    /**
     * 模拟用户登录
     */
    private void login() {
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        if (username.isEmpty()) { // 用户名不允许为空
            Toast.makeText(this, "请输入用户名。", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "请输入密码。", Toast.LENGTH_LONG).show();
            return;
        }
        SharedPreferences user = this.getSharedPreferences("user", MODE_PRIVATE);
        String right_username = user.getString("username", "");
        String right_password = user.getString("password", "");

        if (!username.equals(right_username)) {
            Toast.makeText(this, "用户名错误，请输入正确的用户名。", Toast.LENGTH_LONG).show();
            return;
        }
        if (!password.equals(right_password)) {
            Toast.makeText(this, "密码错误，请输入正确的密码。", Toast.LENGTH_LONG).show();
            return;
        }

        SharedPreferences user_login = this.getSharedPreferences("user_login", MODE_PRIVATE);
        SharedPreferences.Editor editor = user_login.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();

        Toast.makeText(this, "登录成功。", Toast.LENGTH_LONG).show();
        usernameEdit.setVisibility(View.GONE);
        passwordEdit.setVisibility(View.GONE);

        SpannedString spannedString = new SpannedString(username + ",欢迎您。");
        loginStatusTxt.setText(spannedString);

    }
}