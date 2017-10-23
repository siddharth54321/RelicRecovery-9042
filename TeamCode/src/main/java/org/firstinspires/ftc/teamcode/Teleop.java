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

        robot.leftBack.setPower(gamepad1.left_trigger);
        robot.leftFront.setPower(.55*gamepad1.left_trigger); // fixed
        robot.rightBack.setPower(.55*-gamepad1.right_trigger);
        robot.rightFront.setPower(.55*-gamepad1.right_trigger);

        if(gamepad1.a){
            robot.toggleJewel();

        }

        telemetry.addData("Left Stick", gamepad1.left_stick_y);
        telemetry.addData("Right Stick", gamepad1.right_stick_y);
    }
    public void stop(){
        robot.setDrivePower(0,0);
    }

}

