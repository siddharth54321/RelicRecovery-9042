/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.Gyro;
import org.firstinspires.ftc.teamcode.Logging;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.RobotMap;

@Disabled
@Autonomous(name = "Turn 90 test", group = "Sensor")
public class IMUTurning_Test extends LinearOpMode
{

    // The IMU sensor object
    BNO055IMU imu;

    Robot robot;

    Orientation angles;
    Acceleration gravity;


    @Override public void runOpMode() {

        Gyro gyro = new Gyro(hardwareMap);
        imu = gyro.imu;
        robot = new Robot(hardwareMap);
        waitForStart();

        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);

        PID pid = new PID(RobotMap.P_TURN);
        double target = gyro.getYaw() - 90;//heading

        pid.setTarget(target);

        pid.getValue(gyro.getYaw());

        // Loop and update the dashboard
        while (opModeIsActive() && Math.abs(pid.err) >= RobotMap.TURN_TOLERANCE) {
            Logging.log("roll: ", gyro.getRoll(), telemetry);
            Logging.log("pitch: ", gyro.getPitch(), telemetry);
            Logging.log("yaw: ", gyro.getYaw(), telemetry);
            Logging.log("error", pid.err,telemetry);
            Logging.log("target", target,telemetry);
            Logging.log("Turn Condition", Math.abs(pid.err) >= RobotMap.TURN_TOLERANCE, telemetry);
            telemetry.update();

            robot.setDrivePower(pid.getValue(gyro.getYaw()), -pid.getValue(gyro.getYaw()));
        }

        //TODO figure out which orientation
        while(opModeIsActive()){
            Logging.log("roll: ", gyro.getRoll(), telemetry);
            Logging.log("pitch: ", gyro.getPitch(), telemetry);
            Logging.log("yaw: ", gyro.getYaw(), telemetry);
            Logging.log("error", pid.err,telemetry);
            Logging.log("target", target,telemetry);
            Logging.log("Turn Condition", Math.abs(pid.err) >= RobotMap.TURN_TOLERANCE, telemetry);
            telemetry.update();
        }
    }

}