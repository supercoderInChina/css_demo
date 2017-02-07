package com.demo.demo_swiperefreshlayout;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener
{

    // SwipeRefreshLayout
    private SwipeRefreshLayout mSwipeRefreshLayout;
    // RecyclerView
    private RecyclerView mRecyclerView;
    // RecyclerView的ListView模式
    private LinearLayoutManager mLayoutManager;

    // Adapter
    private MyAdapter mAdapter;
    int countA = 0;
    int countB = 0;

    // 数据源
    private List<String> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        // 刷新监听事件，必须有
        mSwipeRefreshLayout.setOnRefreshListener(this);

//        mSwipeRefreshLayout.setScrollBarStyle();

        // 设置进度圈的背景色
//        mSwipeRefreshLayout.setProgressBackgroundColor(R.color.colorAccent);
        // 设置进度动画的颜色,可以使用多种颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(this);

        mData = new ArrayList<String>();
        // 初始化Adapter
        mAdapter = new MyAdapter(getApplicationContext());
        // 设置RecyclerView属性为ListView
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 加载适配器
        mRecyclerView.setAdapter(mAdapter);
        // 添加滑动监听
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        initADatas();
    }


    // RecyclerView的滑动监听事件
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener()
    {
        private int lastVisibleItem;

        // 滑动状态改变
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState)
        {
            super.onScrollStateChanged(recyclerView, newState);
            /**
             * scrollState有三种状态，分别是SCROLL_STATE_IDLE、SCROLL_STATE_TOUCH_SCROLL、SCROLL_STATE_FLING
             * SCROLL_STATE_IDLE是当屏幕停止滚动时
             * SCROLL_STATE_TOUCH_SCROLL是当用户在以触屏方式滚动屏幕并且手指仍然还在屏幕上时
             * SCROLL_STATE_FLING是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
             */
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter())
            {

                // 加载更多
                initADatas();
//                LogUtils.d(TAG, "loading more data");
//                mNewsPresenter.loadNews(mType, pageIndex + Urls.PAZE_SIZE);
            }
        }

        // 滑动位置
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy)
        {
            super.onScrolled(recyclerView, dx, dy);
            // 给lastVisibleItem赋值
            // findLastVisibleItemPosition()是返回最后一个item的位置
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }
    };

    // 设置下拉刷新数据
    private void initBDatas()
    {
        for (int i = 0; i < 20; i++)
        {
            mData.add(0, "第B" + String.valueOf(countB) + "个");
            countB++;
        }
        mAdapter.setData(mData);
    }

    // 设置上拉加载数据
    private void initADatas()
    {
        for (int i = 0; i < 20; i++)
        {
            mData.add("第A" + String.valueOf(countA) + "个");
            countA++;
        }
        mAdapter.setData(mData);
    }


    // 设置下拉刷新
    @Override
    public void onRefresh()
    {
        initBDatas();
        // 关闭加载进度条
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
