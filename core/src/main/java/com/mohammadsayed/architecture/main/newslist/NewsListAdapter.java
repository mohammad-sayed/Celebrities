package com.mohammadsayed.architecture.main.newslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mohammadsayed.architecture.R;
import com.mohammadsayed.architecture.data.News;

import java.util.ArrayList;

/**
 * Created by mohammad on 1/23/17.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {

    private Context mContext;
    private ArrayList<News> mNewsList;
    private OnNewsListAdapterListener mOnNewsListAdapterListener;

    public NewsListAdapter(Context context, ArrayList<News> newsList, OnNewsListAdapterListener onNewsListAdapterListener) {
        this.mContext = context;
        this.mNewsList = newsList;
        this.mOnNewsListAdapterListener = onNewsListAdapterListener;
    }

    @Override
    public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_of_news_list, parent,false);
        return new NewsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {
        News news = mNewsList.get(position);
        holder.mTvTitle.setText(news.getTitle());
        holder.mTvDescription.setText(news.getDescription());
    }

    @Override
    public int getItemCount() {
        if (mNewsList == null)
            return 0;
        return mNewsList.size();
    }

    public void updateNews(ArrayList<News> newsList) {
        this.mNewsList = newsList;
        notifyDataSetChanged();
    }

    public class NewsListViewHolder extends RecyclerView.ViewHolder {

        public TextView mTvTitle;
        public TextView mTvDescription;

        public NewsListViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvDescription = (TextView) itemView.findViewById(R.id.tv_description);
        }
    }

    public interface OnNewsListAdapterListener {
        void onNewsClickListener(News news);
    }
}
