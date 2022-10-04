package com.navyliu.widget.homeword;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.navyliu.widget.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DFragment extends Fragment implements View.OnClickListener {

    private AppCompatButton choose1Btn, choose2Btn, choose3Btn;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DFragment newInstance(Bundle bundle) {
        DFragment fragment = new DFragment();
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
        View view = inflater.inflate(R.layout.fragment_d, container, false);

        choose1Btn = (AppCompatButton) view.findViewById(R.id.btn_choose1);
        choose2Btn = (AppCompatButton) view.findViewById(R.id.btn_choose2);
        choose3Btn = (AppCompatButton) view.findViewById(R.id.btn_choose3);

        choose1Btn.setOnClickListener(this);
        choose2Btn.setOnClickListener(this);
        choose3Btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int tab_index = 0;
        switch (v.getId()) {
            case R.id.btn_choose1:
                tab_index = 0;
                break;
            case R.id.btn_choose2:
                tab_index = 1;
                break;
            case R.id.btn_choose3:
                tab_index = 2;
                break;
        }
        HomewordActivity homewordActivity = (HomewordActivity) getActivity();
        homewordActivity.chooseFragment(tab_index);
    }
}