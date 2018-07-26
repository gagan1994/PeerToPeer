package com.example.gagan.p2p.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gagan.p2p.helpers.RecyclerItemClick;

import java.util.List;

/**
 * Created by Gagan on 6/26/2018.
 */

public abstract class BaseRecycleAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter {
    private RecyclerItemClick _listner;

    public void setItemClickListner(RecyclerItemClick listner) {
        this._listner = listner;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        onCustomBindViewHolder((VH) holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_listner != null)
                    _listner.onItemClickListner(holder.getAdapterPosition());
            }
        });
    }

    public abstract void onCustomBindViewHolder(@NonNull VH holder, int position);
}
