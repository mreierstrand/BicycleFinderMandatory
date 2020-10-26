package com.example.bicyclefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class MyPageDetailedBikeActivity extends AppCompatActivity {

    public static final String BIKE = "BIKE";

    private Bike wantedBike;

    EditText name;
    EditText phoneNo;
    EditText stelNummer;
    EditText type;
    EditText maerke;
    EditText farve;
    EditText sted;
    EditText dato;
    EditText missingFound;

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
    }


    public void GoBackToMyPageActivityClick(View view) {
        finish();

    }

    public void DeleteBikeClickButton(View view) {
        //Delete bike code here
    }
}