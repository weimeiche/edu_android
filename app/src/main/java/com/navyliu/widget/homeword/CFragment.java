package com.navyliu.widget.homeword;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.navyliu.widget.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CFragment extends Fragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CFragment newInstance(Bundle bundle) {
        CFragment fragment = new CFragment();
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
        View view = inflater.inflate(R.layout.fragment_c, container, false);
        AppCompatImageView imageView = (AppCompatImageView) view.findViewById(R.id.img);
        imageView.setImageResource(R.drawable.btn_home);

        return view;
    }
}