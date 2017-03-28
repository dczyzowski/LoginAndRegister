package com.example.damian.loginandregister;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    ImageSwitcher imageSwitcher;
    GetGalleryList getGalleryList;
    ProgressBar progressBar;
    ArrayList<Drawable> drawables;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = (ImageView) findViewById(R.id.test);

        drawables = new ArrayList<>();

        progressBar = new ProgressBar(getApplicationContext());
        getGalleryList = new GetGalleryList(getApplicationContext(), progressBar);
        getGalleryList.execute();



        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Upload some photo!", Snackbar.LENGTH_LONG)
                        .setAction("Upload", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void getList(){
        drawables = getGalleryList.drawableList();
        imageView.setImageDrawable(drawables.get(1));
    }
}
