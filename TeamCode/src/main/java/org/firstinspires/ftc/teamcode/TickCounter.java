package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.ConstantsAndCalculations;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name = "Tick Counter test", group = "Test")

public class TickCounter extends LinearOpMode {

    public void runOpMode(){
        Robot robot = new Robot(this.hardwareMap);
        robot.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.stop();

        waitForStart();
        robot.setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while(opModeIsActive()){
            robot.activeOpmode = true;
            telemetry.addData("left Position", (robot.leftFront.getCurrentPosition()+robot.leftBack.getCurrentPosition())/2.0);
            telemetry.addData("right Position", (robot.rightFront.getCurrentPosition()+robot.rightBack.getCurrentPosition())/2.0);;
            telemetry.update();
        }
        robot.activeOpmode = false;
        robot.stop();
    }
}

