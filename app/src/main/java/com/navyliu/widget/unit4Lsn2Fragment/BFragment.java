package com.navyliu.widget.unit4Lsn2Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.navyliu.widget.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BFragment extends Fragment {

    private final String TAG = this.getClass().getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        // 接收来自初始化时的参数
//        Bundle bundle = this.getArguments();
//        int age = bundle.getInt("age");


        String arg = this.getArguments().getString("arg");
        Log.d(TAG, "onCreateView: ===" + arg);
        return view;
    }



    public void getParamsFromActivity(String string) {
        Log.d(TAG, "getParamsFromActivity: =======" + string);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: =============");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ===================");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ================");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ===============");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: =========");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ===========");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ==========");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ================");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ==========");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ======================");
    }

    public BFragment newInstance(String string) {
        Bundle bundle = new Bundle();
        bundle.putString("arg", string);
        BFragment bFragment = new BFragment();
        bFragment.setArguments(bundle);
        return bFragment;
    }
}