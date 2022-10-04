package com.navyliu.widget.unit4Lsn2Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.navyliu.widget.R;

public class AActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    private AFragment aFragment;
    private AppCompatTextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        /**
         * 静态引入fragment
         * 直接在布局文件中引入，使用fragment控件，name属性引入。
         * 例如：
         <fragment
         android:id="@+id/fragment"
         android:name="com.navyliu.widget.unit4_lsn2_fragment.AFragment"
         android:layout_width="match_parent"
         android:layout_height="match_parent" />
         */
        textView = (AppCompatTextView) this.findViewById(R.id.txt_activity);
        /**
         * 获取fragment对象，首先用getSupportFragmentManager()获取fragment管理器
         * 然后从管理器中通过findFragmentById去找到对应的fragment
         */
        aFragment = (AFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);


        /**
         * 接收fragment中传过来的参数  开始
         * 步骤：
         * 1、调用fragment中设置的接口对应的set方法；
         * 2、在set方法中传入接口，并回调接口中实现的方法ActivityGetResultFromFragment。
         * 回调返回的值，就是fragment中传回的值
         */
        aFragment.setFragmentListener(new AFragment.FragmentListener() {
            @Override
            public void ActivityGetResultFromFragment(String string) {
                textView.setText(string);
                Log.d(TAG, "ActivityGetResultFromFragment: =======" + string);
            }
        });
        /**
         * 接收fragment中传过来的参数  结束
         */
    }

    public void naviBActivity(View view) {
        Intent intent = new Intent(this, BActivity.class);
        startActivity(intent);
    }

}
