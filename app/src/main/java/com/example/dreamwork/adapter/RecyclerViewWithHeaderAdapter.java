package com.example.dreamwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dreamwork.R;
import com.example.dreamwork.util.V;

import java.util.List;

/**
 * Created by co-mall on 2016/5/20.
 */
public class RecyclerViewWithHeaderAdapter extends RecyclerView.Adapter<RecyclerViewWithHeaderAdapter.MyViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;


    private View mHeaderView;

    private Context mContext;
    private List<String> datas;

    public RecyclerViewWithHeaderAdapter(List<String> datas) {
        this.datas = datas;
    }


    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        notifyItemInserted(0);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(mHeaderView);
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        }

        int realposition = getRealPosition(holder);

        holder.tv.setText(datas.get(realposition));
    }

    private int getRealPosition(RecyclerViewWithHeaderAdapter.MyViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {

        return mHeaderView == null ? datas.size() : datas.size() - 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }

        if (mHeaderView == null) {
            return TYPE_NORMAL;
        }

        return TYPE_NORMAL;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = V.f(itemView, R.id.item);
        }
    }
}
