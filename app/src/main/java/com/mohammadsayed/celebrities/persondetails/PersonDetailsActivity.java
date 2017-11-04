package com.mohammadsayed.celebrities.persondetails;

import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.mohammadsayed.architecture.core.idle.BaseIdleActivity;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.data.Person;

public class PersonDetailsActivity extends BaseIdleActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_person_details;
    }

    @Override
    protected void initializeViewsAndData() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Person person = intent.getParcelableExtra(Constants.ExtrasKeys.PERSON);
        PersonDetailsFragment personDetailsFragment = (PersonDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_person_details);
        personDetailsFragment.setPerson(person);
    }
}
