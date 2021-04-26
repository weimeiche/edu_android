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
 * Use the {@link AFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AFragment extends Fragment implements View.OnClickListener {

    private AppCompatButton btn;

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
        btn = (AppCompatButton) view.findViewById(R.id.btn1);

        setLisener();
        return view;
    }

    private void setLisener() {
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                clickFragmentArgsToActivity(0, 999);
                break;
        }
    }


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