package com.compas.flanir.compas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Asus on 14.11.2015.
 */
public class CompassView extends View implements SensorEventListener {

    float angleX = 0, angleY = 1;
    private final Paint border = new Paint();
    private final Paint northArrow = new Paint();
    public CompassView(Context context, AttributeSet attrributeSet) {
        super(context, attrributeSet);

        border.setColor(Color.GREEN);
        northArrow.setColor(Color.BLUE);
        border.setStrokeWidth(25);
        northArrow.setStrokeWidth(border.getStrokeWidth() / 3f * 2f);
        border.setStyle(Paint.Style.STROKE);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // nope
    }

    @Override
    public  void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        angleX = (float)(x / Math.sqrt(x * x + y * y));
        angleY = (float)(y / Math.sqrt(x * x + y * y));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        float radius = Math.min(width, height) * 0.45f;
        float len = radius * 0.9f;
        canvas.drawCircle(width / 2, height / 2, radius, border);
        int ePosX = (int) (width / 2 + len * angleX);
        int ePosY = (int) (height / 2 + len * angleY);


        canvas.drawLine(width / 2, height / 2, ePosX, ePosY, northArrow);
    }
}
