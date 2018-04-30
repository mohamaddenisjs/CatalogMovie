package com.denis.pragmadev.catalogmovie;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.denis.pragmadev.catalogmovie.adapters.MovieAdapter;
import com.denis.pragmadev.catalogmovie.model.ResultsItem;
import com.denis.pragmadev.catalogmovie.model.TmdbResponse;
import com.denis.pragmadev.catalogmovie.services.ApiClient;
import com.denis.pragmadev.catalogmovie.services.TmdbApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainView.UserListener {

    private MainView.View view;

    public MainPresenter(MainView.View view) {
        this.view = view;
    }

    @Override
    public void searchMovie(final Context context, String search) {
        if (isConnecting(context)){

            if (search.equals("")){
                view.setrrorFieldSearch("Silahkan isi pencarian");
            } else {
                TmdbApi api = ApiClient.getService();
                Call<TmdbResponse> call = api.searchMovie(search);
                call.enqueue(new Callback<TmdbResponse>() {
                    @Override
                    public void onResponse(Call<TmdbResponse> call, Response<TmdbResponse> response) {
                        List<ResultsItem> resultsList = response.body().getResults();
                        if (resultsList.size() > 0 ){
                            MovieAdapter adapter = new MovieAdapter(context, resultsList);
                            view.showMovie(adapter);
                        } else {
                            view.showError("Film tidak ada");
                        }
                    }

                    @Override
                    public void onFailure(Call<TmdbResponse> call, Throwable t) {
                        view.showError("Pencarian gagal");

                    }
                });
            }
        } else {
            view.showError("Please check your connection internet");
        }
    }

    private boolean isConnecting(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null){
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
