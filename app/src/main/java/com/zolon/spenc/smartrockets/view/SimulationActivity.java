package com.zolon.spenc.smartrockets.view;

import android.os.Bundle;

import com.zolon.spenc.smartrockets.R;

import androidx.appcompat.app.AppCompatActivity;

public class SimulationActivity extends AppCompatActivity {

    SimulationView simulationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);
        simulationView = findViewById(R.id.simulation_view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(simulationView != null) {
            simulationView.onActivityPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(simulationView != null) {
            simulationView.onActivityResume();
        }
    }
}
