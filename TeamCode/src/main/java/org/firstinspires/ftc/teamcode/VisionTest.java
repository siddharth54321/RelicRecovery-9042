package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by anikaitsingh on 12/13/17.
 */

@Autonomous(name = "Vision Test")
public class VisionTest extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
        Vision vision = new Vision(this.hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            vision.getVuMark(telemetry);
        }
    }
}
