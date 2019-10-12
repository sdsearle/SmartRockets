package com.example.spenc.smartrockets.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.spenc.smartrockets.R;
import com.example.spenc.smartrockets.presenter.CustomizationPresenter;
import com.example.spenc.smartrockets.util.InputFilterMinMax;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import androidx.appcompat.app.AppCompatActivity;

public class CustomizationActivity extends AppCompatActivity implements CustomizationPresenter.View {

    CustomizationPresenter customizationPresenter;
    EditText popET;
    EditText mutationET;
    EditText lifeET;
    SeekBar mutationBar;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        customizationPresenter = new CustomizationPresenter(this);
        popET = findViewById(R.id.pop_size_input);
        lifeET = findViewById(R.id.life_input);
        mutationET = findViewById(R.id.mutation_input);
        mutationBar = findViewById(R.id.mutation_bar);
        customizationPresenter.load(this);
        mutationET.setFilters(new InputFilter[]{new InputFilterMinMax(0,1)});
        mutationET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty() && mutationET.hasFocus()) {
                    if(editable.toString().equals(".")){
                        mutationET.setText("0.");
                        mutationET.setSelection(2);
                        return;
                    }
                    float mutation = Float.parseFloat(editable.toString());
                    mutationBar.setProgress((int) (mutation * 1000));
                }
            }
        });

        mutationBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(!mutationET.hasFocus()) {
                    float mutation = seekBar.getProgress() / 1000.0f;
                    mutationET.setText(String.valueOf(mutation));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mutationET.clearFocus();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        customizationPresenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        customizationPresenter.attachView(this);
    }

    //Saves customization
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save){
            //Do save
            String pop = popET.getText().toString().isEmpty()?"25":popET.getText().toString();
            String life = lifeET.getText().toString().isEmpty()?"400":lifeET.getText().toString();
            String mutation = mutationET.getText().toString().isEmpty()?"0.01":mutationET.getText().toString();
            customizationPresenter.save(pop,life,mutation, this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onObserverClicked(String s) {

    }

    @Override
    public void handleBack() {
        onBackPressed();
    }

    //loads saved values
    @Override
    public void handleLoad(int pop, int life, float mutation) {
        popET.setText(String.valueOf(pop));
        lifeET.setText(String.valueOf(life));
        mutationET.setText(String.valueOf(mutation));
    }

    @Override
    public void showError(String s) {

    }
}
