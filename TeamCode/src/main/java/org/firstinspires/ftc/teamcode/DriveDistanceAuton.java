package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Arrays;

@Autonomous(name = "Driving Distance test", group = "Test")
public class DriveDistanceAuton extends LinearOpMode{

    public void runOpMode(){
        Robot robot = new Robot(this.hardwareMap);
        robot.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.stop();

        waitForStart();
        robot.setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        float dist = RobotMap.distanceToTicks(5);

        float power = 1;

        robot.driveDistance(dist);


        while(opModeIsActive()  ){
            robot.activeOpmode = true;
            // 0
            telemetry.addData("left Position", robot.leftFront.getCurrentPosition());
            // 0
            telemetry.addData("right Position", robot.rightFront.getCurrentPosition());
            // 0.0,0.0,0.0,0.0
            telemetry.addData("Motor Power", Arrays.toString(robot.getPower()));
            // 0.0, 0.0
            telemetry.addData("Motor Power", robot.powerD_L +" " + robot.powerD_R);
            // 8256
            telemetry.addData("target Position", dist);
            // true
            telemetry.addData("Active opmode", robot.activeOpmode);
            // 8256
            telemetry.addData("left Error", robot.leftError);
            // 8256
            telemetry.addData("right Error", robot.rightError);
            // false
            telemetry.addData("Boolean Case", Math.abs(robot.leftError) < RobotMap.DRIVE_TOLERANCE && Math.abs(robot.rightError) < RobotMap.DRIVE_TOLERANCE);
            telemetry.update();

        }
        robot.activeOpmode = false;
        robot.stop();
    }
}
