package com.debora.logintry;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnCompass, btnCamera, btnCurrencyConverter, btnLocationSearch, btnToDoList;
    private TaskRepository taskRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Inisialisasi Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Tampilkan tombol back
        getSupportActionBar().setTitle(""); // Clear default title

        // Inisialisasi button dari layout
        btnCompass = findViewById(R.id.btnCompass);
        btnCamera = findViewById(R.id.btnCamera);
        btnCurrencyConverter = findViewById(R.id.btnCurrencyConverter);
        btnLocationSearch = findViewById(R.id.btnLocationSearch);
        btnToDoList = findViewById(R.id.btnToDoList);

        // Inisialisasi TaskRepository
        taskRepository = new TaskRepository();

        // Mengatur listener untuk setiap button
        btnCompass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToKompas();
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCamera();
            }
        });

        btnCurrencyConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCurrencyConverter();
            }
        });

        btnLocationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLocationSearch();
            }
        });

        btnToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToToDoList();
            }
        });

        // Panggil method untuk mengambil data tasks dari Firebase
        taskRepository.getTasks(new TaskRepository.TaskDataListener() {
            @Override
            public void onTaskDataLoaded(List<Task> tasks) {
                // Proses data tugas yang diperoleh dari Firebase
                // Contoh sederhana: tampilkan jumlah tugas
                int taskCount = tasks.size();
                // Misalnya, tampilkan toast
                // Toast.makeText(DashboardActivity.this, "Jumlah tugas: " + taskCount, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(DatabaseError databaseError) {
                // Handle error jika terjadi masalah saat mengambil data
            }
        });
    }

    // Method untuk navigasi ke halaman Kompas
    private void navigateToKompas() {
        Intent intent = new Intent(DashboardActivity.this, KompasActivity.class);
        startActivity(intent);
    }

    // Method untuk navigasi ke halaman Camera (sesuaikan dengan activity yang sesuai)
    private void navigateToCamera() {
        Intent intent = new Intent(DashboardActivity.this, CameraActivity.class);
        startActivity(intent);
    }

    // Method untuk navigasi ke halaman Currency Converter (sesuaikan dengan activity yang sesuai)
    private void navigateToCurrencyConverter() {
        Intent intent = new Intent(DashboardActivity.this, KonverensiActivity.class);
        startActivity(intent);
    }

    // Method untuk navigasi ke halaman Location Search (sesuaikan dengan activity yang sesuai)
    private void navigateToLocationSearch() {
        Intent intent = new Intent(DashboardActivity.this, SearchLocationActivity.class);
        startActivity(intent);
    }

    // Method untuk navigasi ke halaman To Do List (sesuaikan dengan activity yang sesuai)
    private void navigateToToDoList() {
        Intent intent = new Intent(DashboardActivity.this, CrudActivity.class);
        startActivity(intent);
    }


    // Override method onOptionsItemSelected untuk menangani aksi saat item menu (termasuk tombol back) ditekan
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Override method onBackPressed untuk menangani aksi saat tombol back perangkat ditekan
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Pastikan tidak ada kode tambahan yang mencegah navigasi ke LoginActivity
    }
}