package com.kankanla.e560.m20190829_morse;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Game extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private final String T = "### Game";
    private Thread wth;
    private Boolean isWork;
    private Context context;
    private int sleepint = 50;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;

    public Game(Context context) {
        super(context);
        this.context = context;
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i(T, "surfaceCreated");
        isWork = true;
        wth = new Thread(this);
        wth.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//        isWork = false;
    }

    private void myDraw() {
        try {
            canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.BLUE);
            }
        } catch (Exception e) {
            Log.i(T, "myDraw Exception");
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void run() {
        while (isWork) {
            Long s = System.currentTimeMillis();
            myDraw();
            Long e = System.currentTimeMillis();
            if (sleepint - (e - s) > 0) {
                try {
                    Thread.sleep(sleepint - (e - s));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
