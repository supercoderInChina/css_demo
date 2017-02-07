package com.demo.demo_swiperefreshlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YangJW on 2016/7/1 09:37.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    // 设置底部布局
    private static final int TYPE_FOOTER = 0;
    // 设置默认布局
    private static final int TYPE_DEFAULT = 1;

    // 数据源
    private List<String> mData;
    // 上下文
    private Context mContext;
    // 判断是不是最后一个item，默认是true
    private boolean mShowFooter = true;

    // 构造函数
    public MyAdapter(Context mContext)
    {
        this.mContext = mContext;
    }

    // 设置数据并刷新
    public void setData(List<String> Data)
    {
        this.mData = Data;
        this.notifyDataSetChanged();
    }

    // 设置不同的item
    @Override
    public int getItemViewType(int position)
    {
        // 判断当前位置+1是不是等于数据总数（因为数组从0开始计数），是的就加载底部布局刷新，不是就加载默认布局
        if (position + 1 == getItemCount())
        {
            return TYPE_FOOTER;
        } else
        {
            return TYPE_DEFAULT;
        }
    }

    // 设置布局（相当于设置convertView）
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        if (viewType == TYPE_DEFAULT)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);

            DefaultViewHolder vh = new DefaultViewHolder(v);

            return vh;
        } else
        {
            // 实例化布局
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, null);
            // 代码实现加载布局
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            return new FooterViewHolder(view);
        }
    }



    // 在布局中加载数据（绑定数据）
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        // 用来在运行时指出对象是某一个对象
        if (holder instanceof DefaultViewHolder)
        {
            String str = mData.get(position);

            if (str == null)
            {
                return;
            }

            ((DefaultViewHolder) holder).tv.setText(str);
        }
    }

    // 获取数据数目
    @Override
    public int getItemCount()
    {
        // 判断是不是显示底部，是就返回1，不是返回0
        int begin = mShowFooter? 1 : 0;
        // 没有数据的时候，直接返回begin
        if (mData == null)
        {
            return begin;
        }
        // 因为底部布局要占一个位置，所以总数目要+1
        return mData.size() + begin;
    }


    // 设置是否显示底部加载提示（将值传递给全局变量）
    public void isShowFooter(boolean showFooter)
    {
        this.mShowFooter = showFooter;
    }

    // 判断是否显示底部，数据来自全局变量
    public boolean isShowFooter()
    {
        return this.mShowFooter;
    }



    // 底部布局的ViewHolder
    public class FooterViewHolder extends RecyclerView.ViewHolder
    {
        public FooterViewHolder(View view)
        {
            super(view);
        }
    }

    // 默认布局的ViewHolder
    public class DefaultViewHolder extends RecyclerView.ViewHolder
    {

        public TextView tv;

        public DefaultViewHolder(View itemView)
        {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.tv);

        }
    }


}
