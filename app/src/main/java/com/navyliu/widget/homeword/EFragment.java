package com.navyliu.widget.homeword;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.navyliu.widget.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EFragment extends Fragment {

    private AppCompatTextView textView;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EFragment newInstance(Bundle bundle) {
        EFragment fragment = new EFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
//        bundle.getString("test")
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_e, container, false);
        textView = (AppCompatTextView) view.findViewById(R.id.textview);

        return view;
    }
}