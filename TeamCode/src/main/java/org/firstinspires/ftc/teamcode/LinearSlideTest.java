package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by anikaitsingh on 11/10/17.
 */

@Disabled
@Autonomous(name = "Linear Slide Test")
public class LinearSlideTest extends OpMode{
    DcMotor left, right;


    public void init(){
        left = this.hardwareMap.dcMotor.get("2");
        right = this.hardwareMap.dcMotor.get("1");
    }

    public void loop(){
        left.setPower(.30*gamepad1.left_stick_y);
        right.setPower(.30*gamepad1.left_stick_y);
    }
}
