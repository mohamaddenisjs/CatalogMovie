package com.denis.pragmadev.catalogmovie;

import android.content.Context;

import com.denis.pragmadev.catalogmovie.adapters.MovieAdapter;

public interface MainView {

    interface View{
        void showMovie(MovieAdapter adapter);
        void showError(String message);
        void setrrorFieldSearch(String message);
    }

    interface UserListener{
        void searchMovie(Context context, String search);
    }
}
