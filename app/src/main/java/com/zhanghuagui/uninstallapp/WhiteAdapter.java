package com.zhanghuagui.uninstallapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WhiteAdapter extends RecyclerView.Adapter<WhiteAdapter.WhiteHolder> {

    private List<Item> mItems = new ArrayList<>();

    public void setData(List<Item> datas) {
        mItems.clear();
        if (datas != null) {
            mItems.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void handleAll(boolean isAll) {
        Helper.deleteAllDB();
        if (isAll) {
            Helper.insertAllDB(mItems);
        }
        for (Item item : mItems) {
            item.setWhite(isAll);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WhiteAdapter.WhiteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.view_white, viewGroup, false);
        return new WhiteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WhiteHolder holder, int position) {
        final Item item = mItems.get(position);
        holder.mNameView.setText(item.getAppName());
        holder.mPackageView.setText(item.getPackageName());
        holder.mIconView.setImageDrawable(item.getIcon());
        holder.mFlagView.setImageResource(item.isWhite() ? R.mipmap.ic_selected : R.mipmap.ic_unselected);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.isWhite()) {
                    Helper.deleteDB(item);
                } else {
                    Helper.insertDB(item);
                }
                item.setWhite(!item.isWhite());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class WhiteHolder extends RecyclerView.ViewHolder {

        ImageView mIconView;
        TextView mNameView;
        TextView mPackageView;
        ImageView mFlagView;

        WhiteHolder(@NonNull View itemView) {
            super(itemView);
            mIconView = itemView.findViewById(R.id.iv_icon);
            mNameView = itemView.findViewById(R.id.tv_name);
            mPackageView = itemView.findViewById(R.id.tv_package);
            mFlagView = itemView.findViewById(R.id.iv_flag);
        }
    }
}
