package com.navyliu.widget.homeword;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.navyliu.widget.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<NewsBean> newsList;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BFragment newInstance(Bundle bundle) {
        BFragment fragment = new BFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化数据
        newsList = new ArrayList<>();
        NewsBean newsBean;
        for (int i = 0; i < 100; i++) {
            newsBean = new NewsBean()
                    .setTitle("新闻标题" + i)
                    .setNewspaper("报社名称" + i)
                    .setStatus("热");
            newsList.add(newsBean);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b2, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        // 初始化recyclerview
        initRecyclerView();
        return view;
    }

    /**
     * recyclerview的加载，参考 unit3Lsn4RecyclerView
     */
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        BAdapter bAdapter = new BAdapter(getActivity(), newsList);
        recyclerView.setAdapter(bAdapter);
    }
}