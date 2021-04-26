package com.navyliu.widget.unit7Lsn1BradcaseReceiver.test;

import android.content.Intent;
import android.os.Build;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class BroadcastPassedby {
    public void enableImlicit() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }
//        ActivityManager am = (ActivityManager)
    }

    public static class ActivityManagerHandler implements InvocationHandler {

        private Object mActivityManager;

        public ActivityManagerHandler(Object activityManager) {
            mActivityManager = activityManager;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("broadcastIntent")) {
                for (Object o : args) {
                    if (null == o) {
                        continue;
                    }
                    if (o instanceof Intent) {
                        Intent intent = (Intent) o;
                        int includeBackground = Reflect.on(Intent.class).field("FLAG_RECEIVER_INCLUDE_BACKGROUND").get();
                        intent.setFlags(intent.getFlags() | includeBackground);
                    }
                }
            }
            return method.invoke(mActivityManager, args);
        }
    }
}
