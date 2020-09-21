package com.example.bicyclefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.logging.Handler;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "RegisterMe";
    private FirebaseAuth mAuth;
    private Object AuthResult;
    private TextView messageView;
    private Button registerButton;
    private ProgressBar progressBar;
    private EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        messageView = findViewById(R.id.mainMessageTextView);
        mAuth = FirebaseAuth.getInstance();
        initializeUI();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUserClick();
            }
        });
    }

    private void RegisterUserClick() {
        progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = emailText.getText().toString().trim();
        password = passwordText.getText().toString().trim();


//        if ("".equals(email)) {
//            messageView.setText("Ingen email indtastet");
//            return;
//        }
//        if ("".equals(password)) {
//            messageView.setText("Intet password indtastet");
//            return;
//        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Ingen email indtastet", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
            return;

        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Intet password indtastet", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //messageView.setText("Bruger oprettet, gå til login");
                            Toast.makeText(getApplicationContext(),"Registrering færdiggjort! Vent venligst", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                        startActivity(intent);

                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }).start();

                        } else {
                            try {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                //messageView.setText("Der er opstået et problem, prøv igen");
                                Toast.makeText(getApplicationContext(),"Registrering mislykkedes. Prøv igen", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                AuthResult result = task.getResult();
                            } catch (RuntimeException ex) {
                                messageView.setText(ex.getCause().getMessage());
                            }
                        }
                    }

                    // ...
                });
    };

    private void initializeUI(){
    emailText = findViewById(R.id.registerEmailEditText);
    passwordText = findViewById(R.id.registerPasswordEditText);
    registerButton = findViewById(R.id.registerCreateUserButton);
    progressBar = findViewById(R.id.registerProgressBar);
    }


}
