package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by anikaitsingh on 11/1/17.
 */

@Autonomous(name = "Park Auton")
public class ParkAuton extends LinearOpMode{

    public void runOpMode(){
        Robot robot = new Robot(this.hardwareMap);

        ElapsedTime time = new ElapsedTime();
        time.reset();
        while(time.seconds() < 3){
            robot.setDrivePower(1);
        }

        robot.setDrivePower(0);


        while(time.seconds() < 0.3){
            robot.setDrivePower(-1);
        }
    }

}
