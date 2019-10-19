package com.zolon.spenc.smartrockets.model;

public class ZVector {

    private double x;
    private double y;
    private int maxForce = 4;

    //Creates random Zvecotor
    ZVector(){
        x = Math.random() * maxForce -maxForce/2;
        y = Math.random() * maxForce -maxForce/2;
    }

    public ZVector(int x, int y){
        this.x = x;
        this.y = y;
    }

    static ZVector randVector(){
        return new ZVector();
    }

    public void add(ZVector force) {
        x += force.getX();
        y += force.getY();
    }

    double getY() {
        return y;
    }

    double getX() {
        return x;
    }

    void copy(ZVector target) {
        x=target.getX();
        y=target.getY();
    }

    void set(int i) {
        x = i;
        y = i;
    }

    void limit(int i) {
        if (x > maxForce){
            x = maxForce;
        }
        if (y > maxForce){
            y =maxForce;
        }
    }

    void setZero() {
        x = 0;
        y = 0;
    }
}
