package com.example.bicyclefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    }

    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        messageView.setText("");
        EditText emailView = findViewById(R.id.mainEmailEditText);
        EditText passwordView = findViewById(R.id.mainPasswordEditText);
        passwordView.setText("");
        passwordView.clearFocus();
    }


    public void mainRegisterButtonClick(View view) {
        startActivity(new Intent(getBaseContext(),RegisterActivity.class));
    }

    public void LoginUserClick(View view) {
        EditText emailView = findViewById(R.id.mainEmailEditText);
        EditText passwordView = findViewById(R.id.mainPasswordEditText);
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            messageView.setText("Login succes");

                            Intent intent = new Intent(getBaseContext(), UserLoggedInActivity.class);
                            intent.putExtra("userLoggedIn", user.getEmail());
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getBaseContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            messageView.setText("Du er ikke oprettet i systemet");
                            messageView.setTextColor(Color.RED);
                        }
                    }
                });

    }

    public void LoginUserClickAUTO(View view) {
        EditText emailView = findViewById(R.id.mainEmailEditText);
        EditText passwordView = findViewById(R.id.mainPasswordEditText);

        emailView.setText("test@test.com");
        passwordView.setText("testtest");
        LoginUserClick(view);
    }
}
