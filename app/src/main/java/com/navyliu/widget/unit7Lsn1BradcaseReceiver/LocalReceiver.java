package com.navyliu.widget.unit7Lsn1BradcaseReceiver;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;

import com.navyliu.widget.unit4Lsn1Activity.AppManager;

public class LocalReceiver extends BroadcastReceiver {

    private final String TAG = this.getClass().getName();

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d(TAG, "onClick: ======退出应用，跳到登录页面=============");
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("警告：")
//                .setMessage("您的账号在别处登录，请重新登录")
//                .setCancelable(false)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        AppManager.getAppManager().finishAllActivity();
////                        new Intent(context)
//                        Log.d(TAG, "onClick: ======退出应用，跳到登录页面=============");
//                    }
//                });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        alertDialog.show();
    }
}
