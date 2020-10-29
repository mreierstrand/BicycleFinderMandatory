package com.example.bicyclefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);


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

    private void getAndShowMyBikes() {
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

    public void LogoutButtonClick(View view) {
        mAuth.signOut();
        finish();
//        startActivity(new Intent(this,LoginActivity.class));
    }

    public void GoBackToBikesButtonClick(View view) {

        FirebaseUser user = mAuth.getCurrentUser();
        Intent intent = new Intent(MyPageActivity.this, UserLoggedInActivity.class);
        intent.putExtra("userLoggedInMail", user.getEmail());
        startActivity(intent);


        //finish();
    }
}