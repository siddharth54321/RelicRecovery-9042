package org.firstinspires.ftc.teamcode;


import android.graphics.Color;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "Sensor: Jewel Auton", group = "Sensor")
public class JewelAuton extends LinearOpMode {

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

        robot.toggleJewel();

        boolean red;

        ElapsedTime time = new ElapsedTime();
        time.startTime();
        String str = "init";

        while (opModeIsActive()) {
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
            telemetry.update();

            // if (time.time() > 5) break;
        }

        /*
        telemetry.addData("Detected", str);
        telemetry.update();

        //for testing purposes
        time.reset();
        robot.setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (opModeIsActive()) {
            robot.setDrivePower(.20f);
            if(time.time()>2) break;
        }
        */
    }
}

