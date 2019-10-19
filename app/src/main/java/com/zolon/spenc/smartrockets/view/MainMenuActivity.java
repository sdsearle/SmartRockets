package com.zolon.spenc.smartrockets.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zolon.spenc.smartrockets.R;
import com.zolon.spenc.smartrockets.presenter.MainPresenter;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity implements MainPresenter.View {

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        presenter = new MainPresenter(this);
        Button simButton = findViewById(R.id.simulation);
        Button customization = findViewById(R.id.customization);
        Button level_editor_button = findViewById(R.id.level_editor);
        View.OnClickListener simClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: ");
                switch (view.getId()) {
                    case R.id.simulation:
                        Intent intent = new Intent(MainMenuActivity.this, SimulationActivity.class);
                        MainMenuActivity.this.startActivity(intent);
                    break;
                    case R.id.customization:
                        Intent customizationIntent = new Intent(MainMenuActivity.this, CustomizationActivity.class);
                        MainMenuActivity.this.startActivity(customizationIntent);
                        break;
                    case R.id.level_editor:
                        Intent levelIntent = new Intent(MainMenuActivity.this, SimulationActivity.class);
                        MainMenuActivity.this.startActivity(levelIntent);
                        break;
                }
            }
        };
        simButton.setOnClickListener(simClick);
        customization.setOnClickListener(simClick);
        level_editor_button.setOnClickListener(simClick);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onObserverClicked(String s) {

    }

    @Override
    public void handleBack() {

    }

    @Override
    public void handleLoad(int pop, int life, float mutation) {

    }

    @Override
    public void showError(String s) {

    }

}
