package com.example.bicyclefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailedBikeActivity extends AppCompatActivity {
    public static final String BIKE = "BIKE";

    private Bike wantedBike;

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
        setContentView(R.layout.activity_detailed_bike);

        String tabString = "\t \t \t";

        Intent intent = getIntent();
        wantedBike = (Bike) intent.getSerializableExtra(BIKE);

        stelNummer = findViewById(R.id.detailedBikeFrameNoEditText);
        stelNummer.setEnabled(false);
        stelNummer.setText("Stelnummer: " + tabString + wantedBike.getFrameNumber());

        type = findViewById(R.id.detailedBikeKindOfBikeEditText);
        type.setEnabled(false);
        type.setText("Type: " + tabString + wantedBike.getKindOfBicycle());

        maerke = findViewById(R.id.detailedBikeBrandEditText);
        maerke.setEnabled(false);
        maerke.setText("Mærke: " + tabString + wantedBike.getBrand());

        farve = findViewById(R.id.detailedBikeColorEditText);
        farve.setEnabled(false);
        farve.setText("Farve: " + tabString + wantedBike.getColors());

        sted = findViewById(R.id.detailedBikePlaceEditText);
        sted.setEnabled(false);
        sted.setText("Sted: " + tabString + wantedBike.getPlace());

        dato = findViewById(R.id.detailedBikeDateEditText);
        dato.setEnabled(false);
        dato.setText("Dato: " + tabString + wantedBike.getDate());

        missingFound = findViewById(R.id.detailedBikeMissingFoundEditText);
        missingFound.setEnabled(false);
        missingFound.setText("Fremlyst / Efterlyst: " + tabString + wantedBike.getMissingFound());
    }

    public void GoBackToListActivityClick(View view) {
        finish();
    }
}