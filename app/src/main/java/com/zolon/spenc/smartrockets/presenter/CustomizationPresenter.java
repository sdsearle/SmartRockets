package com.zolon.spenc.smartrockets.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.zolon.spenc.smartrockets.R;

public class CustomizationPresenter implements BasePresenter {

    private View view;

    public CustomizationPresenter(View view){
        this.view  = view;
    }

    @Override
    public void attachView(View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public void save(String popString, String lifeString, String mutationString, Context context) {
        int pop = Integer.parseInt(popString);
        int life = Integer.parseInt(lifeString);
        float mutation = Float.parseFloat(mutationString);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt(context.getResources().getString(R.string.population), pop);
        editor.putInt(context.getResources().getString(R.string.lifespan), life);
        editor.putFloat(context.getResources().getString(R.string.mutation), mutation);
        editor.apply();
        view.handleBack();
    }

    public void load(Context context) {
        int pop = PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getResources().getString(R.string.population),25);
        int life = PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getResources().getString(R.string.lifespan),400);
        float mutation = PreferenceManager.getDefaultSharedPreferences(context).getFloat(context.getResources().getString(R.string.mutation),0.01f);
        view.handleLoad(pop, life, mutation);
    }
}
