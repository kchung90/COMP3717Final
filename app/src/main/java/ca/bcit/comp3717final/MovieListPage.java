package ca.bcit.comp3717final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.List;

public class MovieListPage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button addToListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_page);

        addToListButton = (Button) findViewById(R.id.add_to_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        new DBRetriever().readMovieData(new DBRetriever.DataStatus() {
            @Override
            public void DataLoaded(List<Movie> movies, List<String> keys) {
                new RecyclerViewAdapter().setRecyclerView(recyclerView, MovieListPage.this, movies, keys);
            }
        });

        addToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MovieListPage.this, AddMovieItemPage.class));
            }
        });
    }
}
