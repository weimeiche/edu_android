package com.navyliu.widget.unit4Lsn2Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.navyliu.widget.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AFragment extends Fragment implements View.OnClickListener {

    private final String TAG = this.getClass().getName();

    private AppCompatButton aBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 通过inflater.inflate来引入布局文件
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        Log.d(TAG, "onCreateView:================"); // 生命周期测试
        /**
         * 新建一个方法来初始化控件
         * 初始化控件也是findViewById
         * 但是因为这里都是在上面通过inflater.inflate引入的view里面，所以要在view里面找
         */
        initView(view);

        return view;
    }

    private void initView(View view) {
        aBtn = (AppCompatButton) view.findViewById(R.id.btn_fragment_a);
        aBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fragment_a:
                clickFragmentArgsToActivity();
                break;
        }
    }


    /**
     * fragment 向 activity传值  开始
     * 步骤：
     * 1、定义个接口 FragmentListener，并在接口中实现一个方法ActivityGetResultFromFragment；
     * 2、定义一个接口变量fragmentListener；
     * 3、为接口设置一个set方法，setFragmentListener(FragmentListener listener),
     *      在set方法中将传入的接口变量赋值给定义的接口变量fragmentListener;
     * 4、在需要传值的地方调用接口函数中的方法ActivityGetResultFromFragment。
     *      例如：点击某个按钮以后触发事件，然后调用clickFragmentArgsToActivity函数
     *
     */

    private FragmentListener fragmentListener;

    public static interface FragmentListener {
        public void ActivityGetResultFromFragment(String string);
    }

    public void setFragmentListener(FragmentListener listener) {

        this.fragmentListener = listener;
    }

    public void clickFragmentArgsToActivity() {
        if (fragmentListener != null) {
            fragmentListener.ActivityGetResultFromFragment("这是来自fragment传递到Activity的值");
        }
    }
    /**
     * fragment 向 activity传值  结束
     */



    /**
     * 生命周期测试  开始
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ==============="); // 生命周期测试
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ==================="); // 生命周期测试
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ================"); // 生命周期测试
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ==============="); // 生命周期测试
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ========="); // 生命周期测试
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ==========="); // 生命周期测试
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: =========="); // 生命周期测试
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ================"); // 生命周期测试
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: =========="); // 生命周期测试
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ======================"); // 生命周期测试
    }
    /**
     * 生命周期测试  结束
     */
}