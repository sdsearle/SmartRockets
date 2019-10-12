package com.example.spenc.smartrockets.presenter;

import android.content.Intent;
import android.view.View;

import com.example.spenc.smartrockets.view.MainMenuActivity;

public class MainPresenter implements BasePresenter {

    private View view;

    public MainPresenter(View view){
        this.view = view;
    }

    public void run(){
        update();
        show();
    }

    private void update(){

    }

    private void show(){

    }

    @Override
    public void attachView(View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
