package com.ftable21.android.weather.basic;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

public abstract  class BaseRecyclerViewAdapter <T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    protected AdapterView.OnItemClickListener mOnItemClickListener;

    public void setOnClickListener(AdapterView.OnItemClickListener onClickListener) { mOnItemClickListener = onClickListener; }

    protected void onItemHolderClick(RecyclerView.ViewHolder itemHolder) {
        if (mOnItemClickListener != null) { mOnItemClickListener.onItemClick(null, itemHolder.itemView, itemHolder.getAdapterPosition(), itemHolder.getItemId()); }
        else { throw new IllegalStateException("Fuck you! Call setOnItemClickListener method set the click event listeners!"); }
    }
}
