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
        robot.drive.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.drive.stop();

        waitForStart();
        robot.drive.setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while(opModeIsActive()){
            robot.activeOpmode = true;
            telemetry.addData("left Position", (robot.drive.leftFront.getCurrentPosition()+robot.drive.leftBack.getCurrentPosition())/2.0);
            telemetry.addData("right Position", (robot.drive.rightFront.getCurrentPosition()+robot.drive.rightBack.getCurrentPosition())/2.0);;
            telemetry.update();
        }
        robot.activeOpmode = false;
        robot.drive.stop();
    }
}

