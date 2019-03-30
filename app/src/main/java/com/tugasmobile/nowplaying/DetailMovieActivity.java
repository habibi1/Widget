package com.tugasmobile.nowplaying;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailMovieActivity extends AppCompatActivity {
    TextView judul, deskripsi, popularitas, rating, tanggalRilis;
    ImageView imageMovie;

    public static String EXTRA_JUDUL = "extra_judul";
    public static String EXTRA_RILIS = "extra_rilis";
    public static String EXTRA_DESKRIPSI = "extra_deskripsi";
    public static String EXTRA_POSTER = "extra_poster";
    public static String EXTRA_RATING = "extra_rating";
    public static String EXTRA_POPULARITY = "extra_popularity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getIntent().getStringExtra(EXTRA_JUDUL));

        judul = findViewById(R.id.tvTitle);
        deskripsi = findViewById(R.id.tvDescription);
        popularitas = findViewById(R.id.tvPopularity);
        rating = findViewById(R.id.tvRating);
        tanggalRilis = findViewById(R.id.tvDateRelease);
        imageMovie = findViewById(R.id.ivImageMovie);

        judul.setText(getIntent().getStringExtra(EXTRA_JUDUL));
        deskripsi.setText(getIntent().getStringExtra(EXTRA_DESKRIPSI));
        tanggalRilis.setText(getIntent().getStringExtra(EXTRA_RILIS));
        rating.setText(getIntent().getStringExtra(EXTRA_RATING));
        popularitas.setText(getIntent().getStringExtra(EXTRA_POPULARITY));
        Picasso.get().load("http://image.tmdb.org/t/p/w500/"+getIntent().getStringExtra(EXTRA_POSTER)).placeholder(this.getResources().
                getDrawable(R.drawable.ic_movie_black_24dp)).error(this.getResources().
                getDrawable(R.drawable.ic_movie_black_24dp)).into(imageMovie);
    }
}
