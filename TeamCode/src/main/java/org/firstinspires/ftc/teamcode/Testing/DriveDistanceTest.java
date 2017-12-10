package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Utilities.Logging;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Utilities.Robot;
import org.firstinspires.ftc.teamcode.Utilities.RobotMap;

@Autonomous(name = "Driving Distance test", group = "Test")
public class DriveDistanceTest extends LinearOpMode{

    public void runOpMode(){
        Robot robot = new Robot(this.hardwareMap);
        robot.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.stop();

        waitForStart();
        robot.setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        float dist = RobotMap.distanceToTicks(5);

        float power = 1;

        PID pidLeft = new PID(RobotMap.P_CONSTANT_DRIVING);
        PID pidRight = new PID(RobotMap.P_CONSTANT_DRIVING);
        pidLeft.setTarget(dist);
        pidRight.setTarget(dist);


        while(opModeIsActive() || (pidLeft.err < RobotMap.DRIVE_TOLERANCE && pidRight.err < RobotMap.DRIVE_TOLERANCE )){
            double[] positionCurr = robot.getPosition();
            double avgLeft = (positionCurr[0]+ positionCurr[1])/2.0;
            double avgRight = (positionCurr[2]+ positionCurr[3])/2.0;
            robot.setDrivePower(pidLeft.getValueP(avgLeft), pidRight.getValueP(avgRight));

            Logging.log("Left Back Motor Position", positionCurr[0], telemetry);
            Logging.log("Left Front Motor Position", positionCurr[1], telemetry);
            Logging.log("Right Back Motor Position", positionCurr[2], telemetry);
            Logging.log("Right Front Motor Position", positionCurr[3], telemetry);
            telemetry.update();
        }

        robot.stop();
    }
}
