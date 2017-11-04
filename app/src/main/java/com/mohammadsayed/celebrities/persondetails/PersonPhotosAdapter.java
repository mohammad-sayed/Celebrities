package com.mohammadsayed.celebrities.persondetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.mohammadsayed.architecture.utils.StringUtil;
import com.mohammadsayed.celebrities.AppUtility;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseGridAdapter;
import com.mohammadsayed.celebrities.data.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class PersonPhotosAdapter extends BaseGridAdapter<PersonPhotosAdapter.PersonPhotoViewHolder> {

    private List<Photo> mPhotos;

    public PersonPhotosAdapter(Context context, int displayWidth, int columnSpan) {
        super(context, displayWidth, columnSpan);
        mPhotos = new ArrayList<>();
    }


    @Override
    protected int getItemLayout() {
        return R.layout.list_item_photo;
    }

    @Override
    protected PersonPhotoViewHolder createViewHolder(View view) {
        return new PersonPhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonPhotoViewHolder holder, int position) {
        Photo photo = mPhotos.get(position);

        if (!StringUtil.isEmpty(photo.getPhotoPath(), true)) {
            String fullUrl = AppUtility.getFullUrl(Constants.Photo.PROFILE_SIZE, photo.getPhotoPath());
            Picasso.with(mContext).load(fullUrl).error(R.drawable.img_not_found).into(holder.mIvPhoto);
        } else {
            Picasso.with(mContext).load(R.drawable.img_not_found).into(holder.mIvPhoto);
        }
    }

    @Override
    public int getItemCount() {
        if (mPhotos != null) {
            return mPhotos.size();
        }
        return 0;
    }

    public void setPhotos(List<Photo> photos) {
        if (photos == null) {
            return;
        }
        this.mPhotos = photos;
        notifyDataSetChanged();
    }

    public void addPersonsList(List<Photo> photos) {
        if (photos == null) {
            return;
        }
        mPhotos.addAll(photos);
        notifyDataSetChanged();
    }

    public void clear() {
        mPhotos.clear();
        notifyDataSetChanged();
    }

    public class PersonPhotoViewHolder extends RecyclerView.ViewHolder {

        public ImageView mIvPhoto;

        public PersonPhotoViewHolder(View itemView) {
            super(itemView);
            mIvPhoto = itemView.findViewById(R.id.iv_item_photo);
        }
    }
}
