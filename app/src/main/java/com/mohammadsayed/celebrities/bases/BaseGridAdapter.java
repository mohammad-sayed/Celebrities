package com.mohammadsayed.celebrities.bases;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mohammad Sayed on 11/4/2017.
 */

public abstract class BaseGridAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context mContext;
    protected int mDisplayWidth;
    protected int mColumnSpan;

    public BaseGridAdapter(Context context, int displayWidth, int columnSpan) {
        this.mContext = context;
        this.mDisplayWidth = displayWidth;
        this.mColumnSpan = columnSpan;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(getItemLayout(), parent, false);
        view.getLayoutParams().width = mDisplayWidth / mColumnSpan;
        return createViewHolder(view);
    }

    abstract protected int getItemLayout();

    abstract protected VH createViewHolder(View view);
}
