package com.debora.logintry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class KonverensiActivity extends AppCompatActivity {
    TextView t, textViewResult;
    EditText e;
    Button b;
    RadioButton rb1, rb2, rb3;
    RadioGroup radioGroup;
    TextView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konverensi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> onBackPressed()); // Set onClick listener for back button

        t = findViewById(R.id.textView);
        textViewResult = findViewById(R.id.textViewResult);
        e = findViewById(R.id.editTextNumber);
        b = findViewById(R.id.button);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        radioGroup = findViewById(R.id.radioGroup);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double k = Double.parseDouble(e.getText().toString());
                double k1 = 0;
                String currency = "";

                if (rb1.isChecked()) {
                    k1 = k * 15227;
                    currency = "Dollar";
                } else if (rb2.isChecked()) {
                    k1 = k * 16264;
                    currency = "Euro";
                } else if (rb3.isChecked()) {
                    k1 = k * 18314.48;
                    currency = "Pounds";
                }

                e.setText(String.format("%.2f", k1));
                String result = String.format("%.2f", k1) + " Rupiah";
                textViewResult.setText(result);
                Toast.makeText(KonverensiActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Pengaturan");
        menu.add("Nilai Kami");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Tangani tombol back di toolbar
            return true;
        } else if (item.getTitle().equals("Pengaturan")) {
            Toast.makeText(this, "Saya Butuh Bantuan", Toast.LENGTH_SHORT).show();
        } else if (item.getTitle().equals("Nilai Kami")) {
            Toast.makeText(this, "Saya bingung", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
