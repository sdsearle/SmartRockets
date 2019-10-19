package com.zolon.spenc.smartrockets.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Rocket {

    public static ZVector spawn;
    public static ZVector target;
    DNA dna;
    private ZVector position;
    private ZVector velocity;
    private ZVector acceleration;
    private boolean isFinished;
    private boolean isCrashed;
    private double fitness;
    Paint rocketColor;

    Rocket(){
        setup();
        dna = new DNA();
    }

    private void setup() {
        position = new ZVector((int) spawn.getX(), (int)spawn.getY());
        velocity = new ZVector(0,0);
        acceleration = new ZVector(0,0);
        isCrashed = false;
        isFinished = false;
        fitness = 0;
        rocketColor = new Paint();
        rocketColor.setColor(Color.rgb((int) (Math.random()*255),(int) (Math.random()*255),(int) (Math.random()*255)));
        rocketColor.setStyle(Paint.Style.FILL_AND_STROKE);
        rocketColor.setStrokeWidth(2);
    }

    Rocket(DNA dna){
        setup();
        this.dna = dna;
    }

    private void applyForce(ZVector force){
        acceleration.add(force);
    }

    void calcFitness(Barrier barrier){
        // Takes distance to target
        double dt = Math.hypot(position.getX()-target.getX(), position.getY()-target.getY());
        if (dt == 0){
            dt = 0.01;
        }
        dt = Math.hypot(spawn.getX()-target.getX(), spawn.getY()-target.getY())/dt;
        // Takes distance to spawn
        double ds = Math.hypot(position.getX()-spawn.getX(), position.getY()-spawn.getY());
        //calculated fitness
        //TODO:calculate fitness bases on additional flags that determine what gets calculated in fitness
        fitness = dt;// + ds *2;
        // If rocket gets to target increase fitness of rocket
        if (isFinished) {
            this.fitness *= 10;
        }
        // If rocket does not get to target decrease fitness
        if (isCrashed) {
            if(position.getY() < barrier.getPos().getY()){
                this.fitness /= 10;
            }else {
                this.fitness /= 100;
            }
        }
    }


    double getFitness() {
        return fitness;
    }

    //normalises the data
    void normaliseFitness(double maxfit) {
        fitness /= maxfit;
    }

    void update(ZVector window, int count, Barrier barrier) {
        // Checks distance from rocket to target
        int d = (int) Math.hypot(position.getX() - target.getX(), position.getY() - target.getY());
        // If distance less than 10 pixels, then it has reached target
        if (d < 25) {
            isFinished = true;
            this.position.copy(target);
        }
        //Rocket hit the barrier
        if (position.getX() > barrier.getPos().getX() && position.getX() < barrier.getPos().getX() + barrier.getWidth() && position.getY() > barrier.getPos().getY() && position.getY() < barrier.getPos().getY() + barrier.getHeight()) {
            isCrashed = true;
        }
        // Rocket has hit left or right of window
        if (position.getX() > window.getX() || position.getX() < 0) {
            isCrashed = true;
        }
        // Rocket has hit top or bottom of window
        if (position.getY() > window.getY() || position.getY() < 0) {
            isCrashed = true;
        }


        //applies the random vectors defined in dna to consecutive frames of rocket
        applyForce(this.dna.genes[count]);
        // if rocket has not got to goal and not crashed then update physics engine
        if (!isFinished && !isCrashed) {
            velocity.add(acceleration);
            position.add(velocity);
            acceleration.setZero();
            velocity.limit(4);
        }
    }

    void draw(Canvas canvas) {
        //float[] rocketPos = new float[]{(float) (position.getX() + 5), (float) (position.getY() + 3), (float) position.getX(), (float) (position.getY() - 9), (float) (position.getX() - 5), (float) (position.getY() + 3)};
        //canvas.drawLines(rocketPos,rocketColor);
        canvas.drawCircle((float) position.getX(), (float) position.getY(),10,rocketColor);
        //canvas.drawCircle(50, 100,5,rocketColor);
    }
}
