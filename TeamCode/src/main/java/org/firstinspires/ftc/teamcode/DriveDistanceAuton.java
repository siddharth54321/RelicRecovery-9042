package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Driving Distance test", group = "Test")
public class DriveDistanceAuton extends LinearOpMode{

    public void runOpMode(){
        Robot robot = new Robot(this.hardwareMap);
        robot.drive.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.drive.stop();

        waitForStart();
        robot.drive.setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        int dist = (int)ConstantsAndCalculations.distanceToTicks(5);
        float power = 1;
        robot.drive.driveDistance(-dist, dist, power);
        while(opModeIsActive()){
            robot.activeOpmode = true;
            telemetry.addData("left Position", (robot.drive.leftFront.getCurrentPosition()+robot.drive.leftBack.getCurrentPosition())/2.0);
            telemetry.addData("right Position", (robot.drive.rightFront.getCurrentPosition()+robot.drive.rightBack.getCurrentPosition())/2.0);
            telemetry.addData("target Position", dist);
            telemetry.update();
        }
        robot.activeOpmode = false;
        robot.drive.stop();
    }
}
