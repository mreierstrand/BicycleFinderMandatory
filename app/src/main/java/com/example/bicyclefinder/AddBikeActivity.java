package com.example.bicyclefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.Api;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBikeActivity extends AppCompatActivity {

    public static final String CURRENTUSER = "CURRENTUSER";
    private User currentUser;


    ProgressBar progressBar;

    RadioGroup radioGroup;
    RadioButton radioButtonEfterlyst;
    RadioButton radioButtonFremlyst;

    EditText addStelnummer;
    TextView stelnummerError;

    EditText addType;
    TextView typeError;

    EditText addMaerke;
    TextView maerkeError;

    EditText addFarve;
    TextView farveError;

    EditText addSted;
    TextView stedError;

    TextView message;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bike);

        radioGroup = findViewById(R.id.addBikeRadioGroup);
        radioButtonEfterlyst = findViewById(R.id.efterlystChosen);
        radioButtonFremlyst = findViewById(R.id.fremlystChosen);

        addStelnummer = findViewById(R.id.addBikeFrameNoEditText);
        stelnummerError = findViewById(R.id.addBikeStelNummerError);

        addType = findViewById(R.id.addBikeKindOfBikeEditText);
        typeError = findViewById(R.id.addBikeTypeError);

        addMaerke = findViewById(R.id.addBikeBrandEditText);
        maerkeError = findViewById(R.id.addBikeMaerkeError);

        addFarve = findViewById(R.id.addBikeColorEditText);
        farveError = findViewById(R.id.addBikeFarveError);

        addSted = findViewById(R.id.addBikePlaceEditText);
        stedError = findViewById(R.id.addBikeStedError);

        message = findViewById(R.id.addBikeMessageTextView);
        progressBar = findViewById(R.id.addBikeProgressBar);
    }

    /*public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.efterlystChosen:
                if (checked)
                    return;
                break;
            case R.id.fremlystChosen:
                if (checked)
                    //Do stuff
                break;
        }
    }*/

    public void AddBikeClick(View view) {
        String stelnummer = addStelnummer.getText().toString().trim();
        String type = addType.getText().toString().trim();
        String maerke = addMaerke.getText().toString().trim();
        String farve = addFarve.getText().toString().trim();
        String sted = addSted.getText().toString().trim();

        if (stelnummer.isEmpty()) {
            stedError.setVisibility(View.VISIBLE);
            stedError.setText("Stelnummer kan ikke være tom. Skriv noget!");
        } else if (type.isEmpty()) {
            typeError.setVisibility((View.VISIBLE));
            typeError.setText("Cykeltype kan ikke være tom. Skriv noget!");
        } else if (maerke.isEmpty()) {
            maerkeError.setVisibility(View.VISIBLE);
            maerkeError.setText("Cykelmærke kan ikke være tom. Skriv noget!");
        } else if (farve.isEmpty()) {
            farveError.setVisibility(View.VISIBLE);
            farveError.setText("Cykelfarve kan ikke være tom. Skriv noget!");
        } else if (sted.isEmpty()) {
            stedError.setVisibility(View.VISIBLE);
            stedError.setText("Sted kan ikke være tom. Skriv noget!");
        } else {
            Bike bikeToAdd = new Bike(100, stelnummer, type, maerke, farve, sted, currentUser.getId(), getMissingFound());

        BikeService bikeService = ApiUtils.getBikeService();
        Call<Bike> AddBike = bikeService.postBike(bikeToAdd);

//        Call<Bike> AddBike = ApiUtils.GetInstance().getBikeService().postBike(bikeToAdd);

        progressBar.setVisibility(View.VISIBLE);
        AddBike.enqueue(new Callback<Bike>() {
            @Override
            public void onResponse(Call<Bike> call, Response<Bike> response) {
                if (response.isSuccessful()){
                    message.setTextColor(Color.GREEN);
                    message.setText("Cykel oprettet");
                } else message.setText("Der er sket en fejl, prøv igen." + response.message());
                message.setTextColor(Color.RED);
            }

            @Override
            public void onFailure(Call<Bike> call, Throwable t) {
                message.setText("Der er sket en fejl, prøv igen. " + t.getMessage());
            }
        });
    }
}

    private String getMissingFound() {
        if (radioGroup.getCheckedRadioButtonId() == radioButtonEfterlyst.getId()){
            return "Found";
        } else if(radioGroup.getCheckedRadioButtonId() == radioButtonFremlyst.getId()){
            return "Missing";
        }
        return "Der er sket en fejl, prøv igen.";
    }
}