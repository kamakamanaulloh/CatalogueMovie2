package com.kamak.cataloguemovie;

public class DetailMovieModel {
    public static final String KEY_ID="id";
    public static final String KEY_VOTE="vote_average";
    public static final String KEY_POSTER_PATH="poster_path";
    public static final String KEY_RELEASE_DATE="release_date";
    public static final String KEY_TITLE="title";


    private final int id;
    public String getPosterPath;
    double vote;
    String poster_path,release_date,title;

    public DetailMovieModel(int id, double vote, String poster_path, String release_date, String title) {
        this.id=id;
        this.vote=vote;
        this.poster_path=poster_path;
        this.release_date=release_date;
        this.title=title;

    }

//    public DetailMovieModel(int id, double vote_average, String poster_path, String release_date, String title) {
//    }

    public int getID() {
        return id;
    }

    public double getVote() {
        return vote;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }


}


