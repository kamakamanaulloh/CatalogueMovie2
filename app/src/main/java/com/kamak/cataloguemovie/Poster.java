package com.kamak.cataloguemovie;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Poster extends AppCompatActivity {
    @BindView(R.id.rcposter)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<DetailMovieModel>detailMovieModelList;
    PosterAdapter posterAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        detailMovieModelList=new ArrayList<>();
        posterAdapter=new PosterAdapter(detailMovieModelList);

        recyclerView.setAdapter(posterAdapter);
        new GetMovieData().execute();


    }

    private class GetMovieData extends AsyncTask<Void,String,List<DetailMovieModel>>{

        @Override
        protected List<DetailMovieModel> doInBackground(Void... voids) {
            String url="https://api.themoviedb.org/3/movie/popular?api_key=5afa93632eb4b47ccefcc296ff3f202c";
            DetailMovieModel movieModel=null;
            String result=HTTPrequest.AksesInternet(url);
            try {
                if (result!=null){
                    JSONObject objectmovie=new JSONObject(result);
                    JSONArray arrayResult=objectmovie.getJSONArray("results");
                    for(int x=0;x<arrayResult.length();x++){
                        JSONObject m=arrayResult.getJSONObject(x);
                        movieModel =new
                                DetailMovieModel(m.getInt(DetailMovieModel.KEY_ID),
                                m.getDouble(DetailMovieModel.KEY_VOTE),
                                m.getString(DetailMovieModel.KEY_POSTER_PATH),
                                m.getString(DetailMovieModel.KEY_TITLE),
                                m.getString(DetailMovieModel.KEY_RELEASE_DATE));
                        detailMovieModelList.add(movieModel);
                    }

                }
                else {
                    Toast.makeText(Poster.this,"Server Bermasalah",Toast.LENGTH_SHORT).show();
                }

            }catch (JSONException e){
                Log.e("Poster",e.getMessage());
            }


            return detailMovieModelList;

        }

        @Override
        protected void onPostExecute(List<DetailMovieModel> detailMovieModels) {
            super.onPostExecute(detailMovieModels);
            posterAdapter.setPosterAdapter(detailMovieModels);
        }
    }
}