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
        waitForStart();

        robot.jewel.toggleJewel();
        boolean red;

        while (opModeIsActive()) {
            red = robot.jewel.isRed(telemetry);
        }
    }
}

