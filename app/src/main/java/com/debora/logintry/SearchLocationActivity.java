package com.debora.logintry;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class SearchLocationActivity extends AppCompatActivity {

    private SearchView searchView;
    private ListView listView;
    private List<String> locationList;
    private LocationAdapterActivity locationAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Initialize views
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);

        // Sample data for location list
        locationList = new ArrayList<>();
        locationList.add("New York");
        locationList.add("Los Angeles");
        locationList.add("Chicago");
        locationList.add("Houston");
        locationList.add("Phoenix");

        // Set up adapter
        locationAdapter = new LocationAdapterActivity(this, locationList);
        listView.setAdapter(locationAdapter);

        // Set up search view listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                locationAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}