package com.compas.flanir.compas;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Asus on 14.11.2015.
 */
public class MainActivity extends AppCompatActivity {
    private Sensor sensor;
    private SensorManager manager;
    private  CompassView compass;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        compass = (CompassView) findViewById(R.id.compass);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(compass);
    }

    @Override
    protected  void onResume() {
        super.onResume();
        manager.registerListener(compass, sensor, SensorManager.SENSOR_DELAY_UI);
    }


}
