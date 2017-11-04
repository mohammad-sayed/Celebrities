package com.mohammadsayed.celebrities.bases;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohammadsayed.architecture.utils.StringUtil;
import com.mohammadsayed.celebrities.AppUtility;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.data.Person;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class PersonAdapter extends BaseGridAdapter<PersonAdapter.PersonViewHolder> {

    private PersonAdapter.OnPersonSelectedListener mOnPersonSelectedListener;
    private List<Person> mPersonDetails;

    public PersonAdapter(Context context, int displayWidth, int columnSpan, OnPersonSelectedListener onPersonSelectedListener) {
        super(context, displayWidth, columnSpan);
        this.mOnPersonSelectedListener = onPersonSelectedListener;
        mPersonDetails = new ArrayList<>();
    }


    @Override
    protected int getItemLayout() {
        return R.layout.list_item_person;
    }

    @Override
    protected PersonViewHolder createViewHolder(View view) {
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        final Person person = mPersonDetails.get(position);

        if (!StringUtil.isEmpty(person.getProfilePicture(), true)) {
            String fullUrl = AppUtility.getFullUrl(Constants.Photo.PROFILE_SIZE, person.getProfilePicture());
            Picasso.with(mContext).load(fullUrl).error(R.drawable.img_not_found).into(holder.mIvProfilePicture);
        } else {
            Picasso.with(mContext).load(R.drawable.img_not_found).into(holder.mIvProfilePicture);
        }

        holder.mTvName.setText(person.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnPersonSelectedListener != null) {
                    mOnPersonSelectedListener.onPersonSelected(person);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mPersonDetails != null) {
            return mPersonDetails.size();
        }
        return 0;
    }

    public void setPersonsList(List<Person> personDetails) {
        if (personDetails == null) {
            return;
        }
        this.mPersonDetails = personDetails;
        notifyDataSetChanged();
    }

    public void addPersonsList(List<Person> personDetails) {
        if (personDetails == null) {
            return;
        }
        mPersonDetails.addAll(personDetails);
        notifyDataSetChanged();
    }

    public void clear() {
        mPersonDetails.clear();
        notifyDataSetChanged();
    }

    public interface OnPersonSelectedListener {
        void onPersonSelected(Person person);
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        public ImageView mIvProfilePicture;
        public TextView mTvName;

        public PersonViewHolder(View itemView) {
            super(itemView);
            mIvProfilePicture = itemView.findViewById(R.id.person_iv_profile_picture);
            mTvName = itemView.findViewById(R.id.person_name);
        }
    }
}
