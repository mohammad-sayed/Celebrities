package com.mohammadsayed.celebrities.bases;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.data.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private Context mContext;
    private List<Person> mPersonDetails;

    public PersonAdapter(Context context) {
        this.mContext = context;
        mPersonDetails = new ArrayList<>();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Person person = mPersonDetails.get(position);
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

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        public PersonViewHolder(View itemView) {
            super(itemView);
        }
    }
}
