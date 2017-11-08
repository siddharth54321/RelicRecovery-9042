package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.util.ArrayList;

/**
 * Created by anikaitsingh on 11/7/17.
 */

public class CheckLevelLinearSlide extends OpMode{
    Robot robot;
    ArrayList<Integer> list;

    public void init(){
        robot = new Robot(this.hardwareMap);
        list = new ArrayList<Integer>();
    }

    public void loop(){
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

        if(gamepad2.dpad_right){
            list.add(robot.linearSlide.getCurrentPosition());
        }

        telemetry.addData("Linear Slide Position", this.robot.linearSlide.getCurrentPosition());
        telemetry.addData("Important Positions", list.toArray());
        telemetry.update();
    }
}
