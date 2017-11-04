package com.mohammadsayed.celebrities.persondetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseGridAdapter;
import com.mohammadsayed.celebrities.data.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class PersonPhotosAdapter extends BaseGridAdapter<PersonPhotosAdapter.PersonPhotoViewHolder> {

    private List<Image> mImages;

    public PersonPhotosAdapter(Context context, int displayWidth, int columnSpan) {
        super(context, displayWidth, columnSpan);
        mImages = new ArrayList<>();
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
        Image image = mImages.get(position);

        /*if (!StringUtil.isEmpty(image.getProfilePicture(), true)) {
            String fullUrl = AppUtility.getFullUrl(Constants.Image.PROFILE_SIZE, image.getProfilePicture());
            Picasso.with(mContext).load(fullUrl).error(R.drawable.img_not_found).into(holder.mIvPhoto);
        } else {
            Picasso.with(mContext).load(R.drawable.img_not_found).into(holder.mIvPhoto);
        }*/
    }

    @Override
    public int getItemCount() {
        if (mImages != null) {
            return mImages.size();
        }
        return 0;
    }

    public void setImages(List<Image> images) {
        if (images == null) {
            return;
        }
        this.mImages = images;
        notifyDataSetChanged();
    }

    public void addPersonsList(List<Image> images) {
        if (images == null) {
            return;
        }
        mImages.addAll(images);
        notifyDataSetChanged();
    }

    public void clear() {
        mImages.clear();
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
