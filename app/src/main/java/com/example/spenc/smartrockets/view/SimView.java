package com.example.spenc.smartrockets.view;

import com.example.spenc.smartrockets.model.Rocket;

public interface SimView extends BaseView {
    void loadLevel();

    void updateSim(Rocket[] rockets);
}
