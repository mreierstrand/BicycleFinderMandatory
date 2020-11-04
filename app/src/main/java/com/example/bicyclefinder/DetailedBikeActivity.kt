package com.example.bicyclefinder

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class DetailedBikeActivity : AppCompatActivity() {

    companion object {
        const val BIKE = "BIKE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_bike)
        val tabString = "\t \t \t"
        val intent = intent
        val wantedBike = intent.getSerializableExtra(BIKE) as Bike

        val frameNumber = findViewById<EditText>(R.id.detailedBikeFrameNoEditText)
        val type = findViewById<EditText>(R.id.detailedBikeKindOfBikeEditText)
        val brand = findViewById<EditText>(R.id.detailedBikeBrandEditText)
        val color = findViewById<EditText>(R.id.detailedBikeColorEditText)
        val placeFound = findViewById<EditText>(R.id.detailedBikePlaceEditText)
        val date = findViewById<EditText>(R.id.detailedBikeDateEditText)
        val missingFound = findViewById<EditText>(R.id.detailedBikeMissingFoundEditText)
        val name = findViewById<EditText>(R.id.detailedBikeNameEditText)
        val phoneNo = findViewById<EditText>(R.id.detailedBikePhoneNoEditText)

        frameNumber?.isEnabled = false
        frameNumber?.setText("Stelnummer: " + tabString + wantedBike!!.frameNumber)

        type?.isEnabled = false
        type?.setText("Type: " + tabString + wantedBike!!.kindOfBicycle)

        brand?.isEnabled = false
        brand?.setText("Mærke: " + tabString + wantedBike!!.brand)

        color?.isEnabled = false
        color?.setText("Farve: " + tabString + wantedBike!!.colors)

        placeFound?.isEnabled = false
        placeFound?.setText("Sted: " + tabString + wantedBike!!.place)

        date?.isEnabled = false
        date?.setText("Dato: " + tabString + wantedBike!!.date)

        missingFound?.isEnabled = false
        missingFound?.setText("Fremlyst / Efterlyst: " + tabString + wantedBike!!.missingFound)

        name?.isEnabled = false
        name?.setText("Fundet af: " + tabString + wantedBike!!.name)

        phoneNo?.isEnabled = false
        phoneNo?.setText("Telefonnummer: " + tabString + wantedBike!!.phoneNo)
    }

    fun GoBackToListActivityClick(view: View?) {
        finish()
    }
}


