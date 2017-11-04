package com.mohammadsayed.celebrities.photo;

import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.mohammadsayed.architecture.core.idle.BaseIdleActivity;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.data.Photo;

public class PhotoActivity extends BaseIdleActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void initializeViewsAndData() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Photo photo = intent.getParcelableExtra(Constants.ExtrasKeys.PHOTO);
        PhotoFragment photoFragment = (PhotoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_photo);
        photoFragment.setPhoto(photo);
    }
}
