package ca.bcit.comp3717final;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DBRetriever {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbRef;
    private List<Movie> movies = new ArrayList<>();

    public interface DataStatus {
        void DataLoaded(List<Movie> movies, List<String> keys);
    }

    public DBRetriever() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = firebaseDatabase.getReference("movie");
    }

    public void readMovieData(final DataStatus dataStatus) {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                movies.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Movie movie = keyNode.getValue(Movie.class);
                    movies.add(movie);
                }
                dataStatus.DataLoaded(movies, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
