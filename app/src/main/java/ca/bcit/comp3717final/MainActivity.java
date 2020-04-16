package ca.bcit.comp3717final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText titleEditText;
    EditText descriptionEditText;
    EditText urlEditText;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEditText = (EditText) findViewById(R.id.title);
        descriptionEditText = (EditText) findViewById(R.id.description);
        urlEditText = (EditText) findViewById(R.id.movie_url);
        addButton = (Button) findViewById(R.id.add_movie);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();

                String titleString = titleEditText.getText().toString();
                String descString = descriptionEditText.getText().toString();
                String urlString = urlEditText.getText().toString();

                if (!titleString.isEmpty() && !descString.isEmpty() && !urlString.isEmpty()) {
                    Movie movie = new Movie(titleString, descString, urlString);
                    myRef.child("movie").push().setValue(movie);

                    Toast.makeText(v.getContext(), "You have added " + movie.getTitle() + " to the database.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Please make sure all fields are filled.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
