package com.navyliu.widget.unit6Lsn1ContentProvider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.navyliu.widget.R;

import java.security.Permission;
import java.util.ArrayList;

import pub.devrel.easypermissions.EasyPermissions;

public class ContentProviderActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private AppCompatEditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        findViewId();
    }

    private void findViewId() {
        edit = (AppCompatEditText) this.findViewById(R.id.edit);
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_add: // 向ContentProvider中插入数据
                insertUser();
                break;
            case R.id.btn_query: // 查询ContentProvider中插入数据
                queryUser();
                break;
            case R.id.btn_sms: // 获取短信
                getMsgs();
                break;
            case R.id.btn_contants: // 获取联系人
                getContacts();
                break;
            case R.id.btn_query_contacts: // 查询联系人
                queryContact();
                break;
        }
    }

    private void queryUser() {
        Uri uri = Uri.parse("content://com.navyliu.widgit.userProvider/user");
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri
                , new String[]{"name", "password", "id"}
                , null, null, null, null);
        if (cursor != null) {
            Log.d(TAG, "queryUser: d======curr is not null");

            ArrayList<UserBean> userList = new ArrayList<UserBean>();
            UserBean userBean;
            while (cursor.moveToNext()) {
                userBean = new UserBean();
                userBean.setId(cursor.getString(cursor.getColumnIndex("id")));
                userBean.setName(cursor.getString(cursor.getColumnIndex("name")));
                userBean.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                userList.add(userBean);
                Log.d(TAG, "queryUser: ===" + userBean.getUser());
            }
        } else {
            Log.d(TAG, "queryUser: d======curr is null");
        }
    }

    private void insertUser() {
        ContentValues values = new ContentValues();
        values.put("name", "唐三");
        values.put("password", "123456");

        Uri uri = Uri.parse("content://com.navyliu.widgit.userProvider/user");
        ContentResolver resolver = getContentResolver();
        resolver.insert(uri, values);
        Toast.makeText(this, "用户数据插入成功", Toast.LENGTH_LONG).show();
    }


    private void getMsgs() {
        Uri uri = Uri.parse("content://sms/");
        Log.d(TAG, "getMsgs: ====uri:" + uri);
        ContentResolver resolver = getContentResolver();
        // 获取哪些列的信息
        Cursor cursor = resolver.query(uri
                , new String[]{"address", "date", "type", "body"}
                , null, null, null);
        Log.d(TAG, "getMsgs: ===cursor.getPosition()=" + cursor.getPosition());

        while (cursor.moveToNext()) {
            /**
             * type: 1:接收；
             *      2:发送
             *      3：草稿
             *      4：发件箱
             *      5：发送失败
             *      6：待发送列表
             *      0：所有短信
             */
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            Log.d(TAG, "getMsgs: =====address:" + address);
            Log.d(TAG, "getMsgs: =====date:" + date);
            Log.d(TAG, "getMsgs: =====type:" + type);
            Log.d(TAG, "getMsgs: =====body:" + body);
            Log.d(TAG, "getMsgs: ====================================:");
        }
        cursor.close();
    }

    private void getContacts() {
        String[] permissions = {Manifest.permission.READ_CONTACTS
                , Manifest.permission.WRITE_CONTACTS};
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //①查询raw_contacts表获得联系人的id
            ContentResolver resolver = getContentResolver();
//            ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

            Uri uri =  ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            //查询联系人数据
            Cursor cursor = resolver.query(uri, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                //获取联系人姓名,手机号码
                String cName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String cNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                System.out.println("姓名:" + cName);
                System.out.println("号码:" + cNum);
                System.out.println("======================");
            }
            cursor.close();
        } else {
            EasyPermissions.requestPermissions(this
                    , "必须要的权限，否则就别想用这个功能。", 111, permissions);
        }
    }


    private void queryContact() {
        String num = edit.getText().toString();
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + num);
        Log.d(TAG, "queryContact: =====" + ContactsContract.CommonDataKinds.Phone.NUMBER);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"display_name", "data1"}, null, null, null);
        while (cursor.moveToNext()) {
            String display_name = cursor.getString(cursor.getColumnIndex("display_name"));
            String number = cursor.getString(cursor.getColumnIndex("data1"));
            Log.d(TAG, "queryContact: ====display_name:" + display_name + ",phone:" + number);
        }
        cursor.close();
    }
}