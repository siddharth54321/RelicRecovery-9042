package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Logging;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Created by anikaitsingh on 11/26/17.
 */

@Autonomous(name = "MoveOneRotation")
public class MoveOneRotation extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(hardwareMap);
        robot.setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.leftBack.setTargetPosition(-460);
        robot.leftFront.setTargetPosition(-575);
        robot.rightBack.setTargetPosition(-575);
        robot.rightFront.setTargetPosition(-575);

        robot.leftBack.setPower(-1);
        robot.leftFront.setPower(-1);
        robot.rightBack.setPower(-1);
        robot.rightFront.setPower(-1);

        waitForStart();

        while (opModeIsActive()){
            double[] positionCurr = robot.getPosition();
            Logging.log("Left Back Motor Position", positionCurr[0], telemetry);
            Logging.log("Left Front Motor Position", positionCurr[1], telemetry);
            Logging.log("Right Back Motor Position", positionCurr[2], telemetry);
            Logging.log("Right Front Motor Position", positionCurr[3], telemetry);
            telemetry.update();
        }
    }
}
