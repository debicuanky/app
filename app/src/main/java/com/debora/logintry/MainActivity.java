package com.debora.logintry;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if user is logged in
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            // User is logged in, navigate to main app screen
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        } else {
            // User is not logged in, navigate to login screen
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }
}
