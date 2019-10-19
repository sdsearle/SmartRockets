package com.zolon.spenc.smartrockets.presenter;

import com.zolon.spenc.smartrockets.view.BaseView;

public interface BasePresenter<V extends BaseView> {

    void attachView(View view);
    void detachView();

    interface View extends BaseView{
        void onObserverClicked(String s);

        void handleBack();

        void handleLoad(int pop, int life, float mutation);
    }
}
