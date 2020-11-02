package com.example.bicyclefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBikeActivity extends AppCompatActivity {

    public static final String CURRENTUSER = "CURRENTUSER";

    ProgressBar progressBar;

    RadioGroup radioGroup;
    RadioButton radioButtonEfterlyst;
    RadioButton radioButtonFremlyst;

    EditText addNameOfPerson;
    EditText addPhoneNo;
    EditText addStelnummer;
    EditText addType;
    EditText addMaerke;
    EditText addFarve;
    EditText addSted;
    EditText addDato;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bike);

        radioGroup = findViewById(R.id.addBikeRadioGroup);
        radioButtonEfterlyst = findViewById(R.id.efterlystChosen);
        radioButtonFremlyst = findViewById(R.id.fremlystChosen);

        addNameOfPerson = findViewById(R.id.addBikeNameOfPersonEditText);
        addPhoneNo = findViewById(R.id.addBikePhoneNoOfPersonEditText);
        addStelnummer = findViewById(R.id.addBikeFrameNoEditText);
        addType = findViewById(R.id.addBikeKindOfBikeEditText);
        addMaerke = findViewById(R.id.addBikeBrandEditText);
        addFarve = findViewById(R.id.addBikeColorEditText);
        addSted = findViewById(R.id.addBikePlaceEditText);
        addDato = findViewById(R.id.addBikeDateEditText);

        progressBar = findViewById(R.id.addBikeProgressBar);
        mAuth = FirebaseAuth.getInstance();
    }

    public void AddBikeClick(View view) {
        String name = addNameOfPerson.getText().toString().trim();
        String phone = addPhoneNo.getText().toString().trim();
        String stelnummer = addStelnummer.getText().toString().trim();
        String type = addType.getText().toString().trim();
        String maerke = addMaerke.getText().toString().trim();
        String farve = addFarve.getText().toString().trim();
        String sted = addSted.getText().toString().trim();
        String firebaseUserId = mAuth.getCurrentUser().getUid();

        int errorColor = ContextCompat.getColor(getApplicationContext(),R.color.errorColor);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(errorColor);

        if  (name.isEmpty()) {
            String errorString = "Navn kan ikke være tom. Skriv noget!";
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
            addNameOfPerson.setError(spannableStringBuilder);
        }
        else if (phone.isEmpty()) {
            String errorString = "Stelnummer kan ikke være tom. Skriv noget!";
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
            addPhoneNo.setError(spannableStringBuilder);
        }
        else if (stelnummer.isEmpty()) {
            String errorString = "Stelnummer kan ikke være tom. Skriv noget!";
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
            addStelnummer.setError(spannableStringBuilder);
        } else if (type.isEmpty()) {
            String errorString = "Cykeltype kan ikke være tom. Skriv noget!";
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
            addType.setError(spannableStringBuilder);
        } else if (maerke.isEmpty()) {
            String errorString = "Cykelmærke kan ikke være tom. Skriv noget!";
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
            addMaerke.setError(spannableStringBuilder);
        } else if (farve.isEmpty()) {
            String errorString = "Cykelfarve kan ikke være tom. Skriv noget!";
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
            addFarve.setError(spannableStringBuilder);
        } else if (sted.isEmpty()) {
            String errorString = "Sted kan ikke være tom. Skriv noget!";
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
            addSted.setError(spannableStringBuilder);
        } else {
            Bike bikeToAdd = new Bike(stelnummer, type, maerke, farve, sted, "",1, getMissingFound(), firebaseUserId, name, phone);


        BikeService bikeService = ApiUtils.getBikeService();
        Call<Bike> AddBike = bikeService.postBike(bikeToAdd);
        progressBar.setVisibility(View.VISIBLE);

        AddBike.enqueue(new Callback<Bike>() {
            @Override
            public void onResponse(Call<Bike> call, Response<Bike> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getBaseContext(), "Cykel oprettet!",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), UserLoggedInActivity.class);
                    startActivity(intent);
                } else Toast.makeText(getBaseContext(), "Der er sket en fejl, prøv igen",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Bike> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Der er sket en fejl, prøv igen",
                        Toast.LENGTH_SHORT).show();
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
        return "Det kan ikke ske";
    }
}