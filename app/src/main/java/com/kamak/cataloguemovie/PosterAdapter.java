package com.kamak.cataloguemovie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.Holder> {
     List<DetailMovieModel> detailMovieModelList;

    public PosterAdapter(List<DetailMovieModel> detailMovieModelList) {
        this.detailMovieModelList=new ArrayList<>(detailMovieModelList);

    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {//method bertugas untuk mengatur tampilan layout
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.poster_grid,viewGroup,false);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {//menangani logic
//        Picasso.with(holder.itemView.getContext())
//                .load("http://image.tmdb.org/t/p/w185/"+detailMovieModelList.get(i).getPosterPath)
//                .placeholder(R.mipmap.ic_launcher)
//                .into(holder.imageView);
        DetailMovieModel movieModel=detailMovieModelList.get(i);
        Picasso.get()
                .load("http://image.tmdb.org/t/p/w185/"+movieModel.getPosterPath())
                .placeholder(R.drawable.thenun)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(),DetailActivity.class);
                intent.putExtra(DetailMovieModel.KEY_ID,detailMovieModelList.get(i).getID());
                intent.putExtra(DetailMovieModel.KEY_VOTE,detailMovieModelList.get(i).getVote());
                intent.putExtra(DetailMovieModel.KEY_POSTER_PATH,detailMovieModelList.get(i).getPosterPath());
                intent.putExtra(DetailMovieModel.KEY_RELEASE_DATE,detailMovieModelList.get(i).getReleaseDate());
                intent.putExtra(DetailMovieModel.KEY_TITLE,detailMovieModelList.get(i).getTitle());
                holder.itemView.getContext().startActivity(intent);


            }
        });


    }


    @Override
    public int getItemCount() {
        return (detailMovieModelList!=null?detailMovieModelList.size():0);
    }

    public void setPosterAdapter(List<DetailMovieModel> posterPathList) {
        this.detailMovieModelList = new ArrayList<>(posterPathList);
        notifyDataSetChanged();//antisipasi ketika ada data yang dimodif ulang
    }


    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgposter)ImageView imageView;





        public Holder(View view) {
            super(view);
            imageView =(ImageView)itemView.findViewById(R.id.imgposter);
            ButterKnife.bind(this,itemView);


        }
    }
}
