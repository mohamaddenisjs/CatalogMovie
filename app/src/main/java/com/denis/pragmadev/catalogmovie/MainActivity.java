package com.denis.pragmadev.catalogmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.denis.pragmadev.catalogmovie.adapters.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView.View {

    @BindView(R.id.progressBarMain)
    ProgressBar progressBarMain;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    @BindView(R.id.linearLayoutSearch)
    LinearLayout linearLayoutSearch;
    @BindView(R.id.recyclerMovie)
    RecyclerView recyclerMovie;

    private MainView.UserListener listener;

    MovieAdapter adapter;
    List<String> dataItem = new ArrayList<>();
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        listener = new MainPresenter(this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerMovie.setLayoutManager(linearLayoutManager);
        recyclerMovie.setHasFixedSize(true);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = edtSearch.getText().toString();
                progressBarMain.setVisibility(View.VISIBLE);
                recyclerMovie.setVisibility(View.GONE);
                listener.searchMovie(getBaseContext(), search);
            }
        });


    }

    @Override
    public void showMovie(MovieAdapter adapter) {
        progressBarMain.setVisibility(View.GONE);
        recyclerMovie.setVisibility(View.VISIBLE);
        recyclerMovie.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        progressBarMain.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setrrorFieldSearch(String message) {

        edtSearch.setError(message);
        progressBarMain.setVisibility(View.GONE);
    }
}
