package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "tele")
public class Teleop extends OpMode {
    //gamepad 1 is driver
    Robot robot;
    boolean smooth = false;

    public void init() {
        robot = new Robot(this.hardwareMap);
        robot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    public void loop() {

        if (gamepad1.a) {
            robot.setDrivePower(-.3f);
        } else if (gamepad1.y) {
            robot.setDrivePower(.3f);
        } else if (gamepad1.b) {
            robot.setDrivePower(-.3f, .3f);
        } else if (gamepad1.x) {
            robot.setDrivePower(.3f, -.3f);
        } else {
            if (gamepad1.right_trigger > 0.5) {
                robot.setDrivePower(0.3 * gamepad1.left_stick_y, 0.3 * gamepad1.right_stick_y);
            } else if (gamepad1.right_trigger < 0.5) {
                robot.smoothDrive(gamepad1.left_stick_y, gamepad1.right_stick_y);
            } else robot.setDrivePower(gamepad1.left_stick_y, gamepad1.right_stick_y);
        }


        robot.smoothIntake(gamepad2.left_stick_y);

        if (gamepad2.dpad_right) {
            robot.toggleJewel();
        }

        if (gamepad2.dpad_left) {
            robot.toggleFlipper();
        }

        telemetry.addData("Left Stick", gamepad1.left_stick_y);
        telemetry.addData("Right Stick", gamepad1.right_stick_y);

        telemetry.addData("Left Power Front", robot.leftFront.getPower());
        telemetry.addData("Left Power Back", robot.leftBack.getPower());
        telemetry.addData("Right Power Front", robot.rightFront.getPower());
        telemetry.addData("Right Power Back", robot.rightBack.getPower());

        telemetry.addData("Left Power Front Position", robot.leftFront.getCurrentPosition());
        telemetry.addData("Left Power Back Position", robot.leftBack.getCurrentPosition());
        telemetry.addData("Right Power Front Position", robot.rightFront.getCurrentPosition());
        telemetry.addData("Right Power Back Position", robot.rightBack.getCurrentPosition());

        telemetry.addData("Jewel Position", robot.jewel.getPosition());
        telemetry.addData("Flipper Position", robot.flipper.getPosition());
        telemetry.addData("Smooth: ", smooth);
        telemetry.update();
    }

    public void stop() {
        robot.setDrivePower(0, 0);
    }

}