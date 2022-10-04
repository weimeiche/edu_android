package com.navyliu.widget.homeword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.navyliu.widget.R;

import java.util.ArrayList;

public class BAdapter extends RecyclerView.Adapter<BAdapter.ViewHolder> {

    private ArrayList<NewsBean> mList;
    private Context mContext;

    public BAdapter(Context context, ArrayList<NewsBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public BAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BAdapter.ViewHolder holder, int position) {
        NewsBean newsBean = mList.get(position);
        holder.titleTxt.setText(newsBean.getTitle());
        holder.authorTxt.setText(newsBean.getNewspaper());
        holder.statusTxt.setText(newsBean.getStatus());



    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView titleTxt, authorTxt, statusTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_title);
            authorTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_author);
            statusTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_status);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
