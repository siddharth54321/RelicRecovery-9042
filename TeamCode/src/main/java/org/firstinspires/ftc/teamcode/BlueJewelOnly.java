package org.firstinspires.ftc.teamcode;

/**
 * Created by anikaitsingh on 12/9/17.
 */

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Blue: Jewel Only", group = "Sensor")
public class BlueJewelOnly extends LinearOpMode {

    ColorSensor sensorColor;
    Robot robot;

    @Override
    public void runOpMode() {
        robot = new Robot(this.hardwareMap);

        sensorColor = hardwareMap.get(ColorSensor.class, "color");
        sensorColor.enableLed(true);
        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        final double SCALE_FACTOR = 255;
        waitForStart();

        boolean red = false;

        ElapsedTime time = new ElapsedTime();
        time.startTime();
        String str = "init";

        while (opModeIsActive()) {
            robot.jewel.setPosition(0.3);

            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR), (int) (sensorColor.green() * SCALE_FACTOR), (int) (sensorColor.blue() * SCALE_FACTOR), hsvValues);

            red = hsvValues[0] < 25 || hsvValues[0] > 330;
            if (red) str = "red";
            else str = "not red";


            // send the info back to driver station using telemetry function.
            telemetry.addData("Alpha", sensorColor.alpha());
            telemetry.addData("Red  ", sensorColor.red());
            telemetry.addData("Green", sensorColor.green());
            telemetry.addData("Blue ", sensorColor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.addData("Is Red", str);
            telemetry.addData("Servo Position", robot.jewel.getPosition());
            telemetry.update();

            if (time.seconds() > 3) break;
        }


        telemetry.addData("Detected", str);
        telemetry.update();

        double power;
        if (red) {
            power = -.2;
        } else {
            power = .2;
        }

        //for testing purposes
        time.reset();
        robot.setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (opModeIsActive()) {
            robot.setDrivePower(power);
            if (time.time() > 0.5) {
                break;
            }
            telemetry.addData("Detected", str);
            telemetry.update();
        }
        robot.jewel.setPosition(1);
    }
}