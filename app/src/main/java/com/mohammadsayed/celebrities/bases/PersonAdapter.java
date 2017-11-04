package com.mohammadsayed.celebrities.bases;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private Context mContext;
    private List<Person> mPersonDetails;
    private int mDisplayWidth;
    private int mColumnSpan;

    public PersonAdapter(Context context, int displayWidth, int columnSpan) {
        this.mContext = context;
        mPersonDetails = new ArrayList<>();
        this.mDisplayWidth = displayWidth;
        this.mColumnSpan = columnSpan;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_person, parent, false);
        view.getLayoutParams().width = mDisplayWidth / mColumnSpan;
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Person person = mPersonDetails.get(position);

        if (!StringUtil.isEmpty(person.getProfilePicture(), true)) {
            String fullUrl = AppUtility.getFullUrl(Constants.Image.PROFILE_SIZE, person.getProfilePicture());
            Picasso.with(mContext).load(fullUrl).error(R.drawable.img_not_found).into(holder.mIvProfilePicture);
        } else {
            Picasso.with(mContext).load(R.drawable.img_not_found).into(holder.mIvProfilePicture);
        }

        holder.mTvName.setText(person.getName());
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
