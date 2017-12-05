package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name = "Driving Distance test", group = "Test")
public class DriveDistanceAuton extends LinearOpMode{

    public void runOpMode(){
        Robot robot = new Robot(this.hardwareMap);
        robot.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.stop();

        waitForStart();
        robot.setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);
        float dist = RobotMap.distanceToTicks(5);

        float power = 1;

        PID pidLeft = new PID(RobotMap.P_CONSTANT_DRIVING);
        PID pidRight = new PID(RobotMap.P_CONSTANT_DRIVING);
        pidLeft.setTarget(dist);
        pidRight.setTarget(dist);


        while(opModeIsActive() || pidLeft.err < RobotMap.DRIVE_TOLERANCE){
            double[] positionCurr = robot.getPosition();
            double avgLeft = (positionCurr[0]+ positionCurr[1])/2.0;
            double avgRight = (positionCurr[2]+ positionCurr[3])/2.0;
            robot.setDrivePower(pidLeft.getValue(avgLeft, 1), pidRight.getValue(avgRight, 1));
        }

            robot.stop();
        robot.stop();
    }
}
