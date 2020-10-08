package com.example.bicyclefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MyPageActivity extends AppCompatActivity {

    FirebaseAuth mAuth;


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
    }

    public void LogoutButtonClick(View view) {
        mAuth.signOut();
        finish();
//        startActivity(new Intent(this,LoginActivity.class));
    }

}