package com.navyliu.widget.unit3Lsn4RecyclerView;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.navyliu.widget.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0; // 说明是带有HEADER的
    private static final int TYPE_FOOTER = 1; // 说明是带有FOOTER的
    private static final int TYPE_NORMAL = 2; // 说明是带有header和footer的
    // 获取从activity中传递过来的每个item的数据集合
    private View mHeaderView;
    private View mFooterView;

    private ArrayList<RecyclerBean> mList = new ArrayList<RecyclerBean>();
    private Context mContext;
    private int column = 1;

    public void setHeader(View headerView) {
        this.mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void setFooter(View footerView) {
        this.mFooterView = footerView;
        notifyItemInserted(0);
    }

    RecyclerAdapter(Context context, ArrayList<RecyclerBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    // 添加一个元素到指定位置
    public void addItem(RecyclerBean content, int position) {
        mList.add(position, content);
        notifyItemInserted(position); // attention
    }

    // 移除其中一个元素
    public void removeItem(RecyclerBean model) {
        int position = mList.indexOf(model);
        mList.remove(position);
        notifyItemRemoved(position);
    }

    // 刷新适配器
    public void refesh() {
        notifyDataSetChanged();
    }

    // 创建新的view， 被LayoutManager所调用
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new ViewHolder(mHeaderView);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new ViewHolder(mFooterView);
        }
        View inflateView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, null);
        ViewHolder viewHolder = new ViewHolder(inflateView);
        return viewHolder;
    }

    /// 重新itemViewType,加入Header和footerview的关键，通过判断item的类型，从而绑定不同的view
    public int getItemViewType(int position) {
        if (mFooterView == null && mHeaderView == null) {
            return TYPE_NORMAL;
        }
        if (mHeaderView != null) {
            if (position == 0) {
                return TYPE_HEADER;
            }
        }
        if (mFooterView != null) {
            if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            }
        }
        return TYPE_NORMAL;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    // 将数据与界面进行绑定操作
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            // 这里加载数据的时候注意，是从position-1开始，因为position==0已经被header占用了
            int index = mHeaderView == null ? position : position - 1;
            holder.imageView.setImageDrawable(mContext.getResources().getDrawable(mList.get(index).getSrc(), null));
            holder.textview.setText(mList.get(index).getUsername());
            holder.remarkTxt.setText(mList.get(index).getRemark());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnRecyclerItemClickListener.onItemListener(holder.imageView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {
            return mList.size();
        }
        if (mHeaderView != null && mFooterView != null) {
            return mList.size() + 2;
        }
        return mList.size() + 1;
    }

    // 自定义的viewholder，持有每个item的所有界面元素
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private AppCompatTextView textview;
        private AppCompatTextView remarkTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 如果是headerView或者是footerview，直接返回
            if (itemView == mHeaderView || itemView == mFooterView) {
                return;
            }
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textview = (AppCompatTextView) itemView.findViewById(R.id.textview);
            remarkTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_remark);
        }
    }

    private OnRecyclerItemClickListener mOnRecyclerItemClickListener = null;

    public void setOnRecyclerItemListener(OnRecyclerItemClickListener listener) {
        this.mOnRecyclerItemClickListener = listener;
    }

    public interface OnRecyclerItemClickListener {
        void onItemListener(View view, int position);
    }
}
