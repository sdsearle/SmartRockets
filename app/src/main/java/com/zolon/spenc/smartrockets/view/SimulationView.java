package com.zolon.spenc.smartrockets.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import com.zolon.spenc.smartrockets.model.Rocket;
import com.zolon.spenc.smartrockets.presenter.SimulationPresenter;

public class SimulationView extends View implements SimulationPresenter.View {
    private  Paint blue;
    private PointF screenSize;
    private Paint textView;
    private Paint red;
    private Rocket[] rockets;
    private SimulationPresenter simulationPresenter;
    private boolean isLoaded;
    private Paint black;

    public SimulationView(Context context) {
        super(context);
        screenSize = new PointF(getWidth(),getHeight());
        textView = new Paint();
        textView.setColor(Color.BLACK);
        textView.setTextSize(32);
        textView.setStrokeWidth(5);
        loadColors();
    }

    public SimulationView(Context context, AttributeSet attributeSet) {
        super(context);
        screenSize = new PointF(getWidth(),getHeight());
        textView = new Paint();
        textView.setColor(Color.BLACK);
        textView.setTextSize(32);
        textView.setStrokeWidth(2);
        textView.setStyle(Paint.Style.FILL_AND_STROKE);
        loadColors();
        setup();
    }

    private void setup() {
        simulationPresenter = new SimulationPresenter( this);
    }

    private void loadColors() {
        red = new Paint();
        red.setColor(Color.RED);
        blue = new Paint();
        blue.setColor(Color.BLUE);
        black = new Paint();
        black.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw target
        canvas.drawCircle(getWidth()/2,10,50,red);
        //draw spawn
        canvas.drawCircle(getWidth()/2,getHeight()-10,50,blue);
        //draw Barrier
        canvas.drawRect(getWidth()/4, getHeight()*2/3,getWidth()-getWidth()/4,getHeight()*2/3+100,black);

        //draw rockets
        if(isLoaded) {
            simulationPresenter.drawRockets(canvas);
            simulationPresenter.update();
        }

        //Start Redraw
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public void loadLevel() {
        simulationPresenter.setTarget(getWidth()/2,+10);
        simulationPresenter.setSpawn(getWidth()/2,getHeight()-10);
        simulationPresenter.setWindow(getWidth(),getHeight());
        simulationPresenter.startSim(getContext());
        simulationPresenter.setBarrier(getWidth()/4, getHeight()*2/3,getWidth()/2,100);
        isLoaded = true;
    }

    @Override
    public void updateSim(Rocket[] rockets) {

    }

    @Override
    public void showError(String s) {

    }

    public void onActivityPause() {
        simulationPresenter.detachView();
    }

    public void onActivityResume() {
        simulationPresenter.attachView(this);
        //loadLevel();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        loadLevel();
    }
}
