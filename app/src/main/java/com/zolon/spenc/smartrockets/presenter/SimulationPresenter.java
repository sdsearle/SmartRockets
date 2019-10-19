package com.zolon.spenc.smartrockets.presenter;

import android.content.Context;
import android.graphics.Canvas;
import android.preference.PreferenceManager;
import android.util.Log;

import com.zolon.spenc.smartrockets.R;
import com.zolon.spenc.smartrockets.model.Barrier;
import com.zolon.spenc.smartrockets.model.DNA;
import com.zolon.spenc.smartrockets.model.Population;
import com.zolon.spenc.smartrockets.model.Rocket;
import com.zolon.spenc.smartrockets.view.SimView;
import com.zolon.spenc.smartrockets.model.ZVector;

public class SimulationPresenter<V extends SimView>  {

    private View view;
    Population population;
    int count = 0;
    private ZVector target;
    private ZVector window;
    private Barrier barrier;

    public SimulationPresenter(View view){
        this.view = view;
    }

    public void attachView(View view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

    public void update(){
        Log.d("UPDATE", "update: " + count);
        population.update(window,count, barrier);
        count ++;
        if(count >= DNA.getLifespan()){
            population.evaluate(barrier);
            population.selection();
            count = 0;
        }
    }

    public void show(){

    }

    //Creates Target
    public void setTarget(int width, int height) {
        //move to model
        target = new ZVector(width,height);
        Rocket.target = this.target;
    }

    //Creates Spawn
    public void setSpawn(int width, int height) {
        //move to model
        Rocket.spawn = new ZVector(width,height);
    }

    //Creates Window Barrier
    public void setWindow(int width, int height) {
        //move to model
        window = new ZVector(width, height);
    }

    //Sets up Simulation
    public void startSim(Context context) {
        Population.setPopSize(PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getResources().getString(R.string.population),25));
        DNA.setLifespan(PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getResources().getString(R.string.lifespan),400));
        DNA.setMutation(PreferenceManager.getDefaultSharedPreferences(context).getFloat(context.getResources().getString(R.string.mutation),0.01f));
        population = new Population();
        view.updateSim(population.getRockets());
    }

    //Draws the rocket from model info
    public void drawRockets(Canvas canvas) {
        population.drawRockets(canvas);
    }

    //Creates Barrier
    public void setBarrier(int x, int y, int width, int height) {
        barrier = new Barrier(x,y,width,height);
    }

    public interface View extends SimView{

    }

}
