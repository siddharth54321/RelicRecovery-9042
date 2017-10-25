package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by anikaitsingh on 10/24/17.
 */

public class JewelSubsystem {
    //jewel mechanism
    private Servo jewel;
    private ColorSensor sensorColor;
    private boolean jewelup = true;

    float hsvValues[] = {0F, 0F, 0F};
    final float values[] = hsvValues;
    final double SCALE_FACTOR = 255;

    public JewelSubsystem(HardwareMap map){
        init(map);

    }

    public void init(HardwareMap map){
        jewel = map.servo.get("jewel");//TODO // FIXME: 10/21/17

        sensorColor = map.get(ColorSensor.class, "color");
        sensorColor.enableLed(true);
    }

    public boolean isRed(Telemetry telemetry){
        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR), (int) (sensorColor.green() * SCALE_FACTOR), (int) (sensorColor.blue() * SCALE_FACTOR), hsvValues);

        boolean red = hsvValues[0] < 25 || hsvValues[0] > 330;
        String str;

        if (red) str = "red";
        else str = "not red";

        // send the info back to driver station using telemetry function.
        telemetry.addData("Alpha", sensorColor.alpha());
        telemetry.addData("Red  ", sensorColor.red());
        telemetry.addData("Green", sensorColor.green());
        telemetry.addData("Blue ", sensorColor.blue());
        telemetry.addData("Hue", hsvValues[0]);
        telemetry.addData("Is Red", str);
        telemetry.update();

        return red;
    }

    //Jewel Mech
    public void toggleJewel(){
        if(jewelup){
            jewel.setPosition(1);
        }else{
            jewel.setPosition(0);
        }

        jewelup = !jewelup;
    }

}
