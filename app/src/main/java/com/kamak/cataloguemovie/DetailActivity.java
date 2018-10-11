package com.kamak.cataloguemovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.img)ImageView imgtitle;
    @BindView(R.id.txttitle)TextView txtjudul;
    @BindView(R.id.txtyear)TextView txtyear;
    @BindView(R.id.txtrate)TextView txtrating;

    @BindView(R.id.btnfav)Button btnfav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent mintent=getIntent();
        getIntent().getIntExtra(DetailMovieModel.KEY_ID,0);
        txtyear.setText(getIntent().getStringExtra(DetailMovieModel.KEY_TITLE));
        txtjudul.setText(getIntent().getStringExtra(DetailMovieModel.KEY_RELEASE_DATE));
        txtrating.setText(getIntent().getDoubleExtra(String.valueOf(DetailMovieModel.KEY_VOTE),0)+"/10");
        Picasso.get()
                .load("http://image.tmdb.org/t/p/w185/"+getIntent().getStringExtra(DetailMovieModel.KEY_POSTER_PATH))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imgtitle);
    }
}
