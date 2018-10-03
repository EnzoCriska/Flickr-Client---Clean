package com.example.admin.flickrclient_clean.presentation.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.admin.flickrclient_clean.R;
import com.squareup.picasso.Picasso;

public class FullActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_image);
        ImageView image = findViewById(R.id.fullImage);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        Picasso.get()
                .load(bundle.getString("URL"))
                .into(image);
    }
}
