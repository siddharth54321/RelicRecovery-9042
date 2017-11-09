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
        left    right                   x       b
            down                            a
     */

    public void loop(){

        if(gamepad2.dpad_right){
            robot.toggleJewel();
        }

        if(gamepad2.y){
            robot.linearSlide.setPower(0.5); //coarse adjustment
        }else if(gamepad2.a){
            robot.linearSlide.setPower(-0.5); //coarse adjustment
        }else if(gamepad2.x){
            robot.linearSlide.setPower(0.2); //fine adjustment
        }else if(gamepad2.b){
            robot.linearSlide.setPower(-0.2); //fine adjustment
        }else{
            robot.linearSlide.setPower(0);
        }

        //driver
        if(gamepad1.a){
            robot.setDrivePower(-.3f);
        }
        else if(gamepad1.y){
            robot.setDrivePower(.3f);
        }
        else if(gamepad1.b){
            robot.setDrivePower(-.3f, .3f);
        }
        else if(gamepad1.x){
            robot.setDrivePower(.3f, -.3f);
        }else{
            robot.setDrivePower(gamepad1.left_stick_y, gamepad1.right_stick_y);
            robot.smoothDrive(gamepad1.left_stick_y, gamepad1.right_stick_y);
        }

        if(gamepad1.dpad_up){
            RobotMap.INCREMENT += .1;
        }else if(gamepad1.dpad_down){
            RobotMap.INCREMENT -= .1;
        }

        telemetry.addData("Left Stick", gamepad1.left_stick_y);
        telemetry.addData("Right Stick", gamepad1.right_stick_y);

        telemetry.addData("Left Power Front", robot.leftFront.getPower());
        telemetry.addData("Left Power Back", robot.leftBack.getPower());
        telemetry.addData("Right Power Front", robot.rightFront.getPower());
        telemetry.addData("Right Power Back", robot.rightBack.getPower());

        telemetry.addData("Jewel Position", robot.jewel.getPosition());
        telemetry.addData("Smooth Increment:", RobotMap.INCREMENT);
        telemetry.update();
    }
    public void stop(){
        robot.setDrivePower(0,0);
    }

}