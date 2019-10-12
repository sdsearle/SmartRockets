package com.example.spenc.smartrockets.model;

public class Barrier {
    private ZVector pos;
    private int width;
    private int height;

    public Barrier(int x, int y, int width, int height){
        pos = new ZVector(x,y);
        this.width = width;
        this.height = height;
    }


    public ZVector getPos() {
        return pos;
    }

    public void setPos(ZVector pos) {
        this.pos = pos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
