package com.navyliu.widget.unit3Lsn4RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.navyliu.widget.R;
import com.navyliu.widget.unit3Lsn4RecyclerView.swipeToLoadLayout.OnLoadMoreListener;
import com.navyliu.widget.unit3Lsn4RecyclerView.swipeToLoadLayout.OnRefreshListener;
import com.navyliu.widget.unit3Lsn4RecyclerView.swipeToLoadLayout.SwipeToLoadLayout;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity
        implements OnRefreshListener, OnLoadMoreListener {

    private static final int adsas = 1;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;
    private View mHeaderView;
    private View mFooterView;
    ArrayList<RecyclerBean> starList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        swipeToLoadLayout = (SwipeToLoadLayout) this.findViewById(R.id.swipeToLoadLayout);
        recyclerView = (RecyclerView) this.findViewById(R.id.swipe_target);
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.view_header, null);
        mFooterView = LayoutInflater.from(this).inflate(R.layout.view_footer_loading, null);

        /**
         * 初始化数据源
         */
        Integer[] strSrcArr = {R.mipmap.angelababy, R.mipmap.huangxiaoming, R.mipmap.nini};
        String[] strTitleArr = {"Angelababy", "黄晓明", "倪妮"};
        String[] strRemarkArr = {"Angelababy（杨颖），1989年2月28日出生于上海市，华语影视女演员、时尚模特。2003年，Angelababy以模特身份出道，此后，她因担任时尚模特而在香港崭露头角。2007年，开始将工作重心转向大银幕。"
                , "黄晓明，1977年11月13日生于山东省青岛市市南区，中国内地男演员、歌手，毕业于北京电影学院表演系。1998年主演个人首部都市剧《爱情不是游戏》，从而进入演艺圈。2001年凭借主演古装剧《大汉天子》获得关注。2005年起连续10年入选“福布斯中国名人榜”。2006年主演武侠剧《神雕侠侣》。"
                , "倪妮，1988年8月8日出生于江苏省南京市，中国内地女演员，毕业于南京传媒学院语言传播系。2011年，因出演战争史诗电影《金陵十三钗》女主角玉墨一角而进入演艺圈，并凭借此片获得第六届亚洲电影大奖最佳新演员等多个奖项。2013年，主演爱情悬疑电影《杀戒》、爱情电影《我想和你好好的》及治愈系电影《等风来》。"
        };
        starList = new ArrayList<RecyclerBean>();
        RecyclerBean starBean;
        for (int i = 0; i < 15; i++) {
            starBean = new RecyclerBean();
            Integer t = i % 3;
            starBean.setSrc(strSrcArr[t])
                    .setRemark(strRemarkArr[t])
                    .setUsername(strTitleArr[t] + "_" + i);
            starList.add(starBean);
        }


        // 初始化布局管理类（LinearLayoutManager：线性布局；GridLayoutManager：网格布局；StaggeredGridLayoutManager：流布局）
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        // 将布局设置到recyclerview控件上
        recyclerView.setLayoutManager(linearLayoutManager);
        // 初始化adpater适配器，同时传入上下文和数据源
        RecyclerAdapter adapter = new RecyclerAdapter(this, starList);
        // 将适配器设置到recyclerview上
        recyclerView.setAdapter(adapter);
        // 添加头部view和底部view
        adapter.setHeader(mHeaderView);
        adapter.setFooter(mFooterView);
        // 设置监听（上拉加载更多，下拉刷新）
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        // 设置item所占用的列数，常用于GridLayoutManager网格布局
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

        // 设置点击事件
        adapter.setOnRecyclerItemListener(new RecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemListener(View view, int position) {
                switch (view.getId()) {
                    case R.id.image:
                        RecyclerBean recyclerBean = starList.get(position);
                        recyclerBean.setUsername("asdfasdfasdf");
                        starList.set(position,recyclerBean);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.textview:
                        Toast.makeText(RecyclerViewActivity.this, starList.get(position).getUsername() + " 的名字", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

    }

    @Override
    public void onLoadMore() {
        /**
         * 上拉加载更多，触发以后，发送网络请求，解析返回数据，
         * 将返回的数据加到数据源里面，然后刷新adapter
         */
        swipeToLoadLayout.setLoadingMore(false);
//        starList =
    }

    @Override
    public void onRefresh() {
        /**
         * 下拉刷新，触发以后，发送网络请求，解析返回数据，
         * 将数据源里面的数据清空，然后将服务器返回的数据赋值给数据源，刷新adapter
         */
        swipeToLoadLayout.setRefreshing(false);
    }
}