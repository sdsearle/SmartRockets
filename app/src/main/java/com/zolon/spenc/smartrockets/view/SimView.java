package com.zolon.spenc.smartrockets.view;

import com.zolon.spenc.smartrockets.model.Rocket;

public interface SimView extends BaseView {
    void loadLevel();

    void updateSim(Rocket[] rockets);
}
