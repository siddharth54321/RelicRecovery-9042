package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Teleop extends OpMode {
    //gamepad 1 is driver
    Robot robot;

    public void init(){
        robot = new Robot(this.hardwareMap);
    }

    /*
        Gamepad Layout (https://images-na.ssl-images-amazon.com/images/I/91RsGVBf1IL._SL1500_.jpg)
             up                             y
        left    right                   x       a
            down                            b
     */

    public void loop(){

       robot.setDrivePower(gamepad1.left_stick_y, gamepad1.right_stick_y);

        if(gamepad2.a){
            robot.toggleJewel();
        }

        robot.linearSlide.setPower(gamepad2.left_stick_y * .1);

        telemetry.addData("Left Stick", gamepad1.left_stick_y);
        telemetry.addData("Right Stick", gamepad1.right_stick_y);
    }
    public void stop(){
        robot.setDrivePower(0,0);
    }

}

