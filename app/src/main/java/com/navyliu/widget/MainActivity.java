package com.navyliu.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.navyliu.widget.homeword.HomewordActivity;
import com.navyliu.widget.pageview.ViewpagerActivity;
import com.navyliu.widget.unit4Lsn1Activity.activity;
import com.navyliu.widget.unit3Lsn4RecyclerView.RecyclerViewActivity;
import com.navyliu.widget.test.TestActivity;
import com.navyliu.widget.unit2Lsn1Layout.LayoutActivity;
import com.navyliu.widget.unit2Lsn3Resource.ResourceActivity;
import com.navyliu.widget.unit3Lsn1Textview.TextviewActivity;
import com.navyliu.widget.unit3Lsn2EditViewButton.EditViewActivity;
import com.navyliu.widget.unit3Lsn3ImageView.ImageViewWidgetActivity;
import com.navyliu.widget.unit4Lsn2Fragment.FragmentActivity;
import com.navyliu.widget.unit5Lsn1SharePreferences.SharePreferencesActivity;
import com.navyliu.widget.unit5Lsn2Sqlite.SqliteActivity;
import com.navyliu.widget.unit6Lsn1ContentProvider.ContentProviderActivity;
import com.navyliu.widget.unit7Lsn1BradcaseReceiver.BroadcastReceiverActivity;
import com.navyliu.widget.unit7Lsn2Service.ServiceActivity;
import com.navyliu.widget.unit8Lsn1Handler.HandlerActivity;
import com.navyliu.widget.unit8Lsn2Listener.ListenerActivity;
import com.navyliu.widget.unit8Lsn3Http.HttpActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AsyncLayoutInflater(this)
                .inflate(R.layout.activity_main, null
                        , new AsyncLayoutInflater.OnInflateFinishedListener() {
                            @Override
                            public void onInflateFinished(@NonNull View view, int resid, @Nullable ViewGroup parent) {
                                setContentView(view);
                            }
                        });
//        setContentView(R.layout.activity_main);
    }


    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_test: // 测试练习
                Intent testIntent = new Intent(this, TestActivity.class);
                startActivity(testIntent);
                break;
            case R.id.btn_layout: // 第2章 第一节课布局layout
                Intent layoutIntent = new Intent(this, LayoutActivity.class);
                startActivity(layoutIntent);
                break;
            case R.id.btn_view: // 第2章 第二节课布局layout
                Intent viewIntent = new Intent(this, com.navyliu.widget.unit2Lsn2Layout.LayoutActivity.class);
                startActivity(viewIntent);
                break;
            case R.id.btn_resource: // 第2章 第三节课布局资源文件
                Intent resourceIntent = new Intent(this, ResourceActivity.class);
                startActivity(resourceIntent);
                break;
            case R.id.btn_textview: // 第3章 第一节课常见控件TextView
                Intent textviewIntent = new Intent(this, TextviewActivity.class);
                startActivity(textviewIntent);
                break;
            case R.id.btn_editview: // 第3章 第二节课常见控件Editview/Button
                Intent editIntent = new Intent(this, EditViewActivity.class);
                startActivity(editIntent);
                break;
            case R.id.btn_widget: // 第3章 第三节课其他常见控件
                Intent widgetIntent = new Intent(this, ImageViewWidgetActivity.class);
                startActivity(widgetIntent);
                break;
            case R.id.btn_recyclerview: // 第3章 第四节课RecyclerView
//                startActivity(new Intent(this, GridActivity.class));  // 跳转到网格布局
                Intent recyclerIntent = new Intent(this, RecyclerViewActivity.class);
                startActivity(recyclerIntent);
                break;

            case R.id.btn_activity: // 第4章 第一节课Activity
                Intent activityIntent = new Intent(this, activity.class);
                startActivity(activityIntent);
                break;
            case R.id.btn_fragment: // 第4章 第二节课Fragment
                Intent fragmentIntent = new Intent(this, FragmentActivity.class);
                startActivity(fragmentIntent);
                break;
            case R.id.btn_sharepreferences: // 第5章 第一节课数据存储SharePreferences
                Intent sharePreferencesIntent = new Intent(this, SharePreferencesActivity.class);
                startActivity(sharePreferencesIntent);
                Log.d(TAG, "onclick: ===========sharePreferencesIntent===============");
                break;
            case R.id.btn_sqlite: // 第5章 第二节课数据存储SQLite
                Intent sqliteIntent = new Intent(this, SqliteActivity.class);
                startActivity(sqliteIntent);
                break;
            case R.id.btn_content_provider: // 第6章 内容提供者ContentProvider
                Intent providerIntent = new Intent(this, ContentProviderActivity.class);
                startActivity(providerIntent);
                break;
            case R.id.btn_broadcast_receiver: // 第7章 广播接收者BroadcastReceiver
                Intent broadcastIntent = new Intent(this, BroadcastReceiverActivity.class);
                startActivity(broadcastIntent);
                break;
            case R.id.btn_homework:
                Intent homeIntent = new Intent(this, HomewordActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.btn_service: // 第7章 第二节课 服务Service
                Intent serviceIntent = new Intent(this, ServiceActivity.class);
                startActivity(serviceIntent);
                break;
            case R.id.btn_handler: // 第8章 第一节课 Handler机制
                Intent handerIntent = new Intent(this, HandlerActivity.class);
                startActivity(handerIntent);
                break;
            case R.id.btn_listener: // 第8章 第二节课 监听事件
                Intent listenerIntent = new Intent(this, ListenerActivity.class);
                startActivity(listenerIntent);
                break;
            case R.id.btn_http: // 第8章 第三节课 网络请求
                Intent httpIntent = new Intent(this, HttpActivity.class);
                startActivity(httpIntent);
                break;
            case R.id.btn_viewpager2: // viewpager2+fragment
                Intent viewpagerIntent = new Intent(this, ViewpagerActivity.class);
                startActivity(viewpagerIntent);
                break;
        }
    }

}
