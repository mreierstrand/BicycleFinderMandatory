package com.example.bicyclefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import render.animations.*;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LogMeIn";
    private FirebaseAuth mAuth;
    private Object AuthResult;
    private TextView messageView;
    Render render = new Render(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAuth = FirebaseAuth.getInstance();
        messageView = findViewById(R.id.mainMessageTextView);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        TextView messageView = findViewById(R.id.mainMessageTextView);
        if (currentUser == null) {
            messageView.setText("Ingen brugere er logget ind");
        } else {
            messageView.setText(currentUser.getEmail());
        }

        TextView loginHeader = findViewById(R.id.mainSubHeader);
        EditText emailView = findViewById(R.id.mainEmailEditText);
        EditText passwordView = findViewById(R.id.mainPasswordEditText);
        Button loginButton = findViewById(R.id.mainLoginButton);
        Button autoLoginButton = findViewById(R.id.mainLoginButtonAUTO);

        render.setAnimation(Bounce.InUp(loginHeader));
        render.setDuration(2000);
        render.start();

        render.setAnimation(Bounce.InUp(emailView));
        render.setDuration(2000);
        render.start();

        render.setAnimation(Bounce.InUp(passwordView));
        render.setDuration(2000);
        render.start();

        render.setAnimation(Bounce.InUp(loginButton));
        render.setDuration(2000);
        render.start();

        render.setAnimation(Bounce.InUp(autoLoginButton));
        render.setDuration(2000);
        render.start();
    }

    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        messageView.setText("");
        EditText emailView = findViewById(R.id.mainEmailEditText);
        EditText passwordView = findViewById(R.id.mainPasswordEditText);
        emailView.setText("");
        passwordView.setText("");
        passwordView.clearFocus();
    }

    public void mainRegisterButtonClick(View view) {
        startActivity(new Intent(getBaseContext(), RegisterActivity.class));
    }

    public void LoginUserClick(View view) {
        EditText emailView = findViewById(R.id.mainEmailEditText);
        EditText passwordView = findViewById(R.id.mainPasswordEditText);

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        if (email.isEmpty() && password.isEmpty()) {
            Error(view);
            Toast.makeText(getApplicationContext(), "Ingenting indtastet", Toast.LENGTH_LONG).show();
        } else if (email.isEmpty()) {
            Error(view);
            Toast.makeText(getApplicationContext(), "Ingen email indtastet", Toast.LENGTH_LONG).show();
        } else if (password.isEmpty()) {
            Error(view);
            Toast.makeText(getApplicationContext(), "Intet password indtastet", Toast.LENGTH_LONG).show();
        }

        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                messageView.setText("Login succes");
                                messageView.setTextColor(Color.WHITE);

                                Intent intent = new Intent(getBaseContext(), UserLoggedInActivity.class);
                                intent.putExtra("userLoggedIn", user.getEmail());
                                startActivity(intent);
                            } else {
                                Error(view);
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getBaseContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                messageView.setText("Du er ikke oprettet i systemet");
                                messageView.setTextColor(Color.RED);
                            }
                        }
                    });
        }
    }

    public void Error(View view) {
        EditText emailView = findViewById(R.id.mainEmailEditText);
        EditText passwordView = findViewById(R.id.mainPasswordEditText);
        Button loginButton = findViewById(R.id.mainLoginButton);
        Button autoLoginButton = findViewById(R.id.mainLoginButtonAUTO);

        render.setAnimation(Attention.Wobble(emailView));
        render.start();
        render.setAnimation(Attention.Wobble(passwordView));
        render.start();
        render.setAnimation(Attention.Wobble(loginButton));
        render.start();
        render.setAnimation(Attention.Wobble(autoLoginButton));
        render.start();
        render.setAnimation(Attention.Wobble(messageView));
        render.start();
        render.setDuration(2000);
    }

    public void LoginUserClickAUTO(View view) {
        EditText emailView = findViewById(R.id.mainEmailEditText);
        EditText passwordView = findViewById(R.id.mainPasswordEditText);

        emailView.setText("test@test.com");
        passwordView.setText("testtest");
        LoginUserClick(view);
    }
}
