package com.example.bicyclefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private static final String LOG_TAG = "FoundCycles";
    private FirebaseAuth mAuth;
    private Bike currentBike;
    private TextView messageView;
    private MaterialToolbar topAppBar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);


        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), UserLoggedInActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        intent.getStringExtra("userLoggedInMail");

        EditText editTextMail = findViewById(R.id.myPageEditTextEmail);
        editTextMail.setText("Email: " + intent.getStringExtra("userLoggedInMail"));
        editTextMail.setEnabled(false);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.myPageProgressBar);
        messageView = findViewById(R.id.myPageMessageTextView);

        getAndShowMyBikes();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void getAndShowMyBikes() {
        String firebaseUserId = mAuth.getCurrentUser().getUid();
        BikeService bikeFinderService = ApiUtils.getBikeService();
        Call<List<Bike>> getMyBikesCall = bikeFinderService.getBikesbyFirebaseId(firebaseUserId);
        progressBar.setVisibility(View.VISIBLE);

        getMyBikesCall.enqueue(new Callback<List<Bike>>() {
            @Override
            public void onResponse(Call<List<Bike>> call, Response<List<Bike>> response) {
                Log.d(LOG_TAG, response.raw().toString());
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    List<Bike> allBikes = response.body();
                    Log.d(LOG_TAG, allBikes.toString());
                    populateRecyclerView(allBikes);
                } else {
                    String message = "Problem " + response.code() + " " + response.message();
                    Log.d(LOG_TAG, message);
                }
            }

            @Override
            public void onFailure(Call<List<Bike>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    private void populateRecyclerView(List<Bike> allBikes) {
        RecyclerView recyclerView = findViewById(R.id.loggedInRecyclerView);
        Log.d(LOG_TAG, "FindBikes" + allBikes.toString());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter<Bike> adapter = new RecyclerViewAdapter<>(allBikes);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((view, position, item) -> {
            Bike bike = (Bike) item;
            Log.d(LOG_TAG, item.toString());

            Intent intent = new Intent(this, MyPageDetailedBikeActivity.class);
            intent.putExtra(DetailedBikeActivity.BIKE, bike);
            Log.d(LOG_TAG, "putExtra " + bike.toString());
            startActivity(intent);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void MenulogoutClicked(MenuItem item) {
        mAuth.signOut();
        Intent intent = new Intent(MyPageActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}