package com.denis.pragmadev.catalogmovie.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.denis.pragmadev.catalogmovie.R;
import com.denis.pragmadev.catalogmovie.activities.DetailMovieActivity;
import com.denis.pragmadev.catalogmovie.model.ResultsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.SearchHolder> {


    private Context mContext;
    private List<ResultsItem> resultList;

    public MovieAdapter(Context mContext, List<ResultsItem> resultList) {
        this.mContext = mContext;
        this.resultList = resultList;
    }


    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {

        holder.bindData(resultList.get(position));


    }

    public class SearchHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.img_poster_item)
        ImageView imgPosterItem;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.linearlayout)
        LinearLayout linearlayout;


        public SearchHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bindData(final ResultsItem resultsItem) {

            Glide.with(mContext)
                    .load("http://image.tmdb.org/t/p/w185" + resultsItem.getPosterPath())
//                    .override(100, 100)
                    .into(imgPosterItem);
            tvTitle.setText(resultsItem.getTitle());
            tvDesc.setText(resultsItem.getOverview());
            tvDate.setText(resultsItem.getReleaseDate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailMovieActivity.class);
                    intent.putExtra("vote", resultsItem.getVoteAverage());
                    intent.putExtra("title", resultsItem.getOriginalTitle());
                    intent.putExtra("overview", resultsItem.getOverview());
                    intent.putExtra("release_date", resultsItem.getReleaseDate());
                    intent.putExtra("poster", resultsItem.getPosterPath());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
