package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Driving Distance test", group = "Test")
public class DriveDistanceAuton extends LinearOpMode{

    public void runOpMode(){
        Robot robot = new Robot(this.hardwareMap);
        robot.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.stop();

        waitForStart();

        int dist = (int)ConstantsAndCalculations.distanceToTicks(5);
        float power = 1;
        robot.driveDistance(dist, dist, power);
    }
}
