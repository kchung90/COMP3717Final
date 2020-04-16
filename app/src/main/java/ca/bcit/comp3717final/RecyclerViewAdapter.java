package ca.bcit.comp3717final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecyclerViewAdapter {
    private Context context;
    private MovieAdapter movieAdapter;

    public void setRecyclerView(RecyclerView recyclerView, Context context, List<Movie> movies, List<String> keys) {
        this.context = context;
        this.movieAdapter = new MovieAdapter(movies, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(movieAdapter);
    }

    class MovieItemView extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDesc;
        private TextView textViewUrl;

        private String key;

        public MovieItemView(ViewGroup parent) {
            super(LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false));

            textViewTitle = (TextView) itemView.findViewById(R.id.title);
            textViewDesc = (TextView) itemView.findViewById(R.id.description);
            textViewUrl = (TextView) itemView.findViewById(R.id.movie_url);
        }

        public void bind(Movie movie, String key) {
            textViewTitle.setText(movie.getTitle());
            textViewDesc.setText(movie.getDescription());
            textViewUrl.setText(movie.getUrl());
            this.key = key;
        }
    }

    class MovieAdapter extends RecyclerView.Adapter<MovieItemView> {
        private List<Movie> movies;
        private List<String> keys;

        public MovieAdapter(List<Movie> movies, List<String> keys) {
            this.movies = movies;
            this.keys = keys;
        }

        @NonNull
        @Override
        public MovieItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MovieItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MovieItemView holder, int position) {
            holder.bind(movies.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }
    }
}
