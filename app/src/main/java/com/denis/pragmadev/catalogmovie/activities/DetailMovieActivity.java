package com.denis.pragmadev.catalogmovie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.denis.pragmadev.catalogmovie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rating)
    TextView tvRating;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.scrollViewDetails)
    ScrollView scrollViewDetails;
    @BindView(R.id.img_poster)
    ImageView imgPoster;
    @BindView(R.id.tv_release)
    TextView tvRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Catalog Movie");


        Intent detailMovie = getIntent();
        final String titleOri = detailMovie.getStringExtra("title");
        final Double vote = detailMovie.getDoubleExtra("vote", 0.0);
        final String overview = detailMovie.getStringExtra("overview");
        final String release = detailMovie.getStringExtra("release_date");
        final String poster_path = detailMovie.getStringExtra("poster");

        Glide.with(DetailMovieActivity.this)

                .load("http://image.tmdb.org/t/p/w185" + poster_path)
//                .override(500, 400)
                .into(imgPoster);

        tvRating.setText(String.valueOf("Ratings : " + vote));
        tvTitle.setText(String.valueOf(titleOri));
        tvRelease.setText(String.valueOf("Release: " + release));
        tvDescription.setText(String.valueOf(overview));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
