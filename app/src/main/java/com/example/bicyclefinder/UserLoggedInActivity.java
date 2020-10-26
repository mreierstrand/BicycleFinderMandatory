package com.example.bicyclefinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bicyclefinder.databinding.ActivityUserLoggedInBinding;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoggedInActivity extends AppCompatActivity {
    private static final String LOG_TAG = "FoundCycles";
    private TextView messageView;
    private ProgressBar progressBar;
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton floatingActionButtonMyPage;
    private FirebaseAuth mAuth;
    Toast t;

    //Opretter en binding
    ActivityUserLoggedInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserLoggedInBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_user_logged_in);
        //Bruger denne i stedet for den over
        setContentView(binding.getRoot());
        messageView = findViewById(R.id.loggedInMessageTextView);
        progressBar = findViewById(R.id.loggedInProgressBar);
        floatingActionButton = findViewById(R.id.loggedinFloatingActionButton);
        floatingActionButtonMyPage = findViewById(R.id.loggedinFloatingActionButtonMyPage);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();

        t = Toast.makeText(this,"Velkommen " + intent.getStringExtra("userLoggedIn"), Toast.LENGTH_SHORT);
        t.show();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLoggedInActivity.this, AddBikeActivity.class);
                startActivity(intent);
            }
        });

        floatingActionButtonMyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = mAuth.getCurrentUser();
                Intent intent = new Intent(UserLoggedInActivity.this, MyPageActivity.class);
                intent.putExtra("userLoggedInMail", user.getEmail());
                startActivity(intent);
                finish();
            }
        });

        MaterialButtonToggleGroup materialButtonToggleGroup = findViewById(R.id.toggleGroup);
        materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {

                if(group.getCheckedButtonId()==R.id.loggedInMissingButton)
                {
                    getAndShowAllMissingBikes();
                }
                else if(group.getCheckedButtonId()==R.id.loggedInFoundButton)
                {
                    getAndShowAllFoundBikes();
                }
                else if(group.getCheckedButtonId()==R.id.loggedInAllButton)
                {
                    getAndShowAllBikes();
                }
                else {
                        //Impossible
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getAndShowAllBikes();
        floatingActionButton.show();
        //getAndShowAllMissingBikes();
        //getAndShowAllFoundBikes();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        floatingActionButton.hide();
    }


    private void getAndShowAllBikes() {
        BikeService bikeFinderService = ApiUtils.getBikeService();
            Call<List<Bike>> getAllBikesCall = bikeFinderService.getAllBikes();
//        messageView.setText("");
        progressBar.setVisibility(View.VISIBLE);

        getAllBikesCall.enqueue(new Callback<List<Bike>>() {
            @Override
            public void onResponse(Call<List<Bike>> call, Response<List<Bike>> response) {
                //testResponseBody body = response.raw();
                Log.d(LOG_TAG, response.raw().toString());
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    List<Bike> allBikes = response.body();
                    Log.d(LOG_TAG, allBikes.toString());
                    populateRecyclerView(allBikes);
                } else {
                    String message = "Problem " + response.code() + " " + response.message();
                    Log.d(LOG_TAG, message);
                    messageView.setText(message);
                }
            }

            @Override
            public void onFailure(Call<List<Bike>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e(LOG_TAG, t.getMessage());
                messageView.setText(t.getMessage());
            }
        });
    }

    private void getAndShowAllMissingBikes() {
        BikeService bikeFinderService = ApiUtils.getBikeService();
        Call<List<Bike>> getMissingBikesCall = bikeFinderService.getBikebyMissingFound("missing");

        //messageView.setText("");
        progressBar.setVisibility(View.VISIBLE);

        getMissingBikesCall.enqueue(new Callback<List<Bike>>() {
            @Override
            public void onResponse(Call<List<Bike>> call, Response<List<Bike>> response) {
                //testResponseBody body = response.raw();
                Log.d(LOG_TAG, response.raw().toString());
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    List<Bike> allBikes = response.body();
                    Log.d(LOG_TAG, allBikes.toString());
                    populateRecyclerView(allBikes);
                } else {
                    String message = "Problem " + response.code() + " " + response.message();
                    Log.d(LOG_TAG, message);
                    messageView.setText(message);
                }
            }

            @Override
            public void onFailure(Call<List<Bike>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e(LOG_TAG, t.getMessage());
                messageView.setText(t.getMessage());
            }
        });
    }

    private void getAndShowAllFoundBikes() {
        BikeService bikeFinderService = ApiUtils.getBikeService();
        Call<List<Bike>> getMissingBikesCall = bikeFinderService.getBikebyMissingFound("found");
        progressBar.setVisibility(View.VISIBLE);

        getMissingBikesCall.enqueue(new Callback<List<Bike>>() {
            @Override
            public void onResponse(Call<List<Bike>> call, Response<List<Bike>> response) {
                //testResponseBody body = response.raw();
                Log.d(LOG_TAG, response.raw().toString());
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    List<Bike> allBikes = response.body();
                    Log.d(LOG_TAG, allBikes.toString());
                    populateRecyclerView(allBikes);
                } else {
                    String message = "Problem " + response.code() + " " + response.message();
                    Log.d(LOG_TAG, message);
                    messageView.setText(message);
                }
            }

            @Override
            public void onFailure(Call<List<Bike>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e(LOG_TAG, t.getMessage());
                messageView.setText(t.getMessage());
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
            Intent intent = new Intent(UserLoggedInActivity.this, DetailedBikeActivity.class);
            intent.putExtra(DetailedBikeActivity.BIKE, bike);
            Log.d(LOG_TAG, "putExtra " + bike.toString());
            startActivity(intent);
        });
    }



}