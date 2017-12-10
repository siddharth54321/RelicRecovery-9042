package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.util.Log;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by anikaitsingh on 12/9/17.
 */

public class SensorColor {
    ColorSensor sensorColor;
    float hsvValues[] = {0F, 0F, 0F};
    final double SCALE_FACTOR = 255;

    public SensorColor(String name, HardwareMap map){
        sensorColor = map.get(ColorSensor.class, name);
        sensorColor.enableLed(true);
    }

    public float[] getReading(Telemetry telemetry){
        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR), (int) (sensorColor.green() * SCALE_FACTOR), (int) (sensorColor.blue() * SCALE_FACTOR), hsvValues);
        log(telemetry);
        return getReading();
    }

    public float[] getReading(){
        return new float[]{sensorColor.red(), sensorColor.green() ,sensorColor.blue(), hsvValues[0]};
    }



    public void log(Telemetry telemetry){
        // send the info back to driver station using telemetry function.
        telemetry.addData("Alpha", sensorColor.alpha());
        telemetry.addData("Red  ", sensorColor.red());
        telemetry.addData("Green", sensorColor.green());
        telemetry.addData("Blue ", sensorColor.blue());
        telemetry.addData("Hue", hsvValues[0]);

        Log.i("Alpha", String.valueOf(sensorColor.alpha()));
        Log.i("Red  ", String.valueOf(sensorColor.red()));
        Log.i("Green", String.valueOf(sensorColor.green()));
        Log.i("Blue ", String.valueOf(sensorColor.blue()));
        Log.i("Hue", String.valueOf(hsvValues[0]));
    }
}
