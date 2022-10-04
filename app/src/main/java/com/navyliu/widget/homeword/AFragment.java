package com.navyliu.widget.homeword;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.navyliu.widget.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AFragment extends Fragment implements View.OnClickListener {

    private AppCompatButton btn1, btn2, btn3, btn4;
    private AppCompatEditText editText;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AFragment newInstance(Bundle bundle) {
        AFragment fragment = new AFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a2, container, false);


        findView(view);
        setLisener();
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void findView(View view) {
        editText = (AppCompatEditText) view.findViewById(R.id.edit_num);
        btn1 = (AppCompatButton) view.findViewById(R.id.btn1);
        btn2 = (AppCompatButton) view.findViewById(R.id.btn2);
        btn3 = (AppCompatButton) view.findViewById(R.id.btn3);
        btn4 = (AppCompatButton) view.findViewById(R.id.btn4);
    }

    /**
     * 绑定监听事件
     */
    private void setLisener() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    /**
     * 实现按钮的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        // 获取输入框中的值，并转换为正数
        String str = editText.getText().toString();
        int num = str.isEmpty() ? 0 : Integer.parseInt(str);

        // 判断当前点击的是第几个按钮，并将其赋值给index记录
        int index = 0;
        switch (v.getId()) {
            case R.id.btn1:
                index = 0;
                break;
            case R.id.btn2:
                index = 1;
                break;
            case R.id.btn3:
                index = 2;
                break;
            case R.id.btn4:
                index = 3;
                break;
        }
        clickFragmentArgsToActivity(index, num);
    }


    /**
     * 创建接口，实现fragment向activity传递参数
     */
    private FragmentListener fragmentListener;

    public static interface FragmentListener {
        public void ActivityGetResultFromFragment(Bundle bundle);
    }

    public void setFragmentListener(FragmentListener listener) {

        this.fragmentListener = listener;
    }

    public void clickFragmentArgsToActivity(int tab_index, int new_num) {
        if (fragmentListener != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("tab_index", tab_index);
            bundle.putInt("new_num", new_num);
            fragmentListener.ActivityGetResultFromFragment(bundle);
        }
    }
}