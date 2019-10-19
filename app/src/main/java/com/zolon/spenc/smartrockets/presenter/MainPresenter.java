package com.zolon.spenc.smartrockets.presenter;

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
