package com.zhanghuagui.uninstallapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingActivity extends Activity {

    private ImageView mCloseView;
    private TextView mAllView;
    private RecyclerView mRecyclerView;
    private WhiteAdapter mAdapter;

    private boolean mIsAll = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mCloseView = findViewById(R.id.iv_close);
        mAllView = findViewById(R.id.tv_all);
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new WhiteAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(Helper.loadItems());

        mCloseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        refreshAllView();
        mAllView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.handleAll(mIsAll);
                mIsAll = !mIsAll;
                refreshAllView();
            }
        });
    }

    private void refreshAllView() {
        if (mIsAll) {
            mAllView.setText(R.string.choose_all);
        } else {
            mAllView.setText(R.string.choose_all_not);
        }
    }
}
