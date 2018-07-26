package com.example.gagan.p2p.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gagan.p2p.R;
import com.example.gagan.p2p.helpers.CircleTransform;
import com.example.gagan.p2p.users.UserModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gagan on 6/25/2018.
 */

public class UsersAdapter extends BaseRecycleAdapter<UsersAdapter.ViewHolder> {
    private final List<UserModel> mData;
    public UsersAdapter(@NonNull List<UserModel> userModels) {
        mData = userModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onCustomBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_pic)
        ImageView imageView;
        @BindView(R.id.tv_name)
        TextView tv_name;
//        @BindView(R.id.tv_hisory)
//        TextView tv_hisory;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(UserModel userModel) {
            Picasso.get().load(userModel.getPicUri()).transform(new CircleTransform()).into(imageView);
            tv_name.setText(userModel.getName());
        }
    }
}
