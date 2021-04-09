package com.navyliu.widget.unit4Lsn2Fragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.navyliu.widget.R;

public class BActivity extends AppCompatActivity {

    private BFragment bFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        initFragment();
    }

    // 动态加载fragment
    private void initFragment() {
        /**
         * 初始化Fragment，可以直接new一个fragment对象.例如:bFragment = new BFragment();
         *
         * activity像fragment传值：
         * 1、初始化的时候传值：
         *      a、new一个bundle，将参数值put到bundle里面。例如：Bundle bundle = new Bundle(); bundle.putInt("age",2);
         *      b、将put了值的bundle，用setArguments方法设置给fragment；
         *      c、在fragment中使用getArguments获取从activity中传递过来的bundle对象；例如：Bundle bundle = this.getArguments();
         *      d、在fragment中通过bundle中的get方法获取对应的值：例如: int age = bundle.getInt("age");
         *      e、ab两步也可以使用接口方法实现，例如:bFragment = new BFragment().newInstance("这是来自Activity初始化Fragment的时候传的值");
         * 2、动态传值：
         *      a、首先在fragment中定义一个public的方法。例如：getParamsFromActivity(String string);
         *      b、然后在activity中需要传参的时候调用，fragment中的方法就行。例如本文件（BActivity）中的clickFragmentTagArgs方法；
         *      c、如果没有初始化fragment的时候，先对fragment对象获取过来
         */
        // bFragment = new BFragment(); //.newInstance("这是来自Activity初始化Fragment的时候传的值");
        // Bundle bundle = new Bundle();
        // bundle.putInt("age",2);
        // bFragment.setArguments(bundle);
        bFragment = new BFragment().newInstance("这是来自Activity初始化Fragment的时候传的值");


        /**
         * 动态加载fragment  开始
         * 步骤：
         */
        // 1、获取Fragment管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 2、调用fragment管理器中的  事务处理器
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 3、a)加载fragment，可以使用add加载，例如：transaction.add(R.id.layout_fragment, bFragment, "BFragment");
        //                  也可以使用replace加载，例如：transaction.replace(R.id.layout_fragment, bFragment, "BFragment");
        //   b)对于已经加载好的fragment，可以使用hide方法隐藏，例如：transaction.hide(bFragment);
        //                              也可以使用show方法显示，例如: transaction.show(bFragment);
        //                              或者使用remove方法将fragment移除，例如：transaction.remove(bFragment);
        FragmentTransaction fragmentTransaction = transaction.replace(R.id.layout_fragment, bFragment, "BFragment");

        // 4、提交，官网提供的方法是commit();
        // 建议使用commitAllowingStateLoss();
        // 当然，两种提交方法都能达到提交的效果
        // fragmentTransaction.commit();
        fragmentTransaction.commitAllowingStateLoss();
        /**
         * 动态加载fragment  结束
         */
    }


    /**
     * 获取fragment对象，可以直接new，然后赋值给一个变量保存起来；
     *                  也可以通过fragment的管理器，中通过findFragmentByTag来获取，这里需要注意的是在添加的时候必须设置了tag
     *                  所以，tag在设置的时候，要根据需求是否保证其唯一性。
     * @param view
     */
    public void clickFragmentTagArgs(View view) {
        // 获取Fragment
        BFragment fragment = (BFragment) getSupportFragmentManager().findFragmentByTag("BFragment");
        fragment.getParamsFromActivity("这是通过标签找到的Fragment传递过来的值");
    }


    public void clickFragmentArgs(View view) {
        if (bFragment != null) {
            bFragment.getParamsFromActivity("我是来自Activity的实时参数");
        }
    }

}
