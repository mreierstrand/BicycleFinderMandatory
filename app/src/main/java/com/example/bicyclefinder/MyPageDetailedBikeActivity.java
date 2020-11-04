package com.example.bicyclefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageDetailedBikeActivity extends AppCompatActivity {

    public static final String BIKE = "BIKE";
    private static final String LOG_TAG = "myPage";
    MyPageActivity myPageActivity = new MyPageActivity();
    private Bike wantedBike;
    ProgressBar progressBar;

    EditText name;
    EditText phoneNo;
    EditText stelNummer;
    EditText type;
    EditText maerke;
    EditText farve;
    EditText sted;
    EditText dato;
    EditText missingFound;

    TextView messageView;

    private Bike currentBike;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_detailed_bike);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        String tabString = "\t \t \t";

        Intent intent = getIntent();
        wantedBike = (Bike) intent.getSerializableExtra(BIKE);

        stelNummer = findViewById(R.id.myPageDetailedBikeFrameNoEditText);
        stelNummer.setEnabled(false);
        stelNummer.setText("Stelnummer: " + tabString + wantedBike.getFrameNumber());

        type = findViewById(R.id.myPageDetailedBikeBikeKindOfBikeEditText);
        type.setEnabled(false);
        type.setText("Type: " + tabString + wantedBike.getKindOfBicycle());

        maerke = findViewById(R.id.myPageDetailedBikeBikeBrandEditText);
        maerke.setEnabled(false);
        maerke.setText("Mærke: " + tabString + wantedBike.getBrand());

        farve = findViewById(R.id.myPageDetailedBikeBikeColorEditText);
        farve.setEnabled(false);
        farve.setText("Farve: " + tabString + wantedBike.getColors());

        sted = findViewById(R.id.myPageDetailedBikeBikePlaceEditText);
        sted.setEnabled(false);
        sted.setText("Sted: " + tabString + wantedBike.getPlace());

        dato = findViewById(R.id.myPageDetailedBikeBikeDateEditText);
        dato.setEnabled(false);
        dato.setText("Dato: " + tabString + wantedBike.getDate());

        missingFound = findViewById(R.id.myPageDetailedBikeBikeMissingFoundEditText);
        missingFound.setEnabled(false);
        missingFound.setText("Fremlyst / Efterlyst: " + tabString + wantedBike.getMissingFound());

        name = findViewById(R.id.myPageDetailedBikeBikeNameEditText);
        name.setEnabled(false);
        name.setText("Fundet af: " + tabString + wantedBike.getName());

        phoneNo = findViewById(R.id.myPageDetailedBikeBikePhoneNoEditText);
        phoneNo.setEnabled(false);
        phoneNo.setText("Telefonnummer: " + tabString + wantedBike.getPhoneNo());

        messageView = findViewById(R.id.myDetailedPageMessageTextView);
        progressBar = findViewById(R.id.myPageDetailedProgressBar);
        mAuth = FirebaseAuth.getInstance();
    }


    public void GoBackToMyPageActivityClick(View view) {
        finish();
    }

    public void DeleteBikeClickButton(View view) {
        BikeService bikeService = ApiUtils.getBikeService();
        int bikeId = wantedBike.getId();
        Call<Integer> callDeleteBike = bikeService.deleteBike(bikeId);
        progressBar.setVisibility(View.VISIBLE);
        callDeleteBike.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    messageView.setText("Cykel er slettet");
                    Toast.makeText(getBaseContext(), "Cykel slettet!", Toast.LENGTH_SHORT).show();

                    //Email bliver null
                    //Intent intent = new Intent(getBaseContext(), MyPageActivity.class);
                    //FirebaseUser user = mAuth.getCurrentUser();
                    //intent.putExtra("userLoggedIn", user.getEmail());
                    //startActivity(intent);

                    //Listen over cykler bliver ikke opdateret når finish() bliver kaldt
                    finish();

                    Log.d(LOG_TAG, "Cykel med stelnummer: " + wantedBike.getFrameNumber() + " er slettet");
                } else {
                    Log.d(LOG_TAG, "Der er sket en fejl :-(");
                    messageView.setText("Cyklen er ikke blevet slettet.");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                messageView.setText(t.getMessage());
            }
        });
    }
}