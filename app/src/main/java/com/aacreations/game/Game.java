package com.aacreations.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * Game maneges all objects in the game and is responsible for updating all states and render all
 * objects to the screen
 */

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    Context context;

    Paint paint;

    public Game(Context context, AttributeSet attrs) {
        super(context);

        paint = new Paint();
        int colour = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(colour);
        paint.setTextSize(50);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.context = context;
        gameLoop = new GameLoop(this, surfaceHolder);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }

    public void update() {
    }
}
