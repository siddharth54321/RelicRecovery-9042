package org.firstinspires.ftc.teamcode.Utilities;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {
    //drive motors
    public DcMotor leftFront, leftBack, rightFront, rightBack;
    public DcMotor intake;
    public boolean activeOpmode;

    //jewel mechanism
    public Servo jewel;
    public ColorSensor color;
    public boolean jewelup = true;

    public double oldLeftSpeed = 0, oldRightSpeed = 0, oldIntakeSpeed = 0;

    public Robot(HardwareMap map) {
        init(map);
    }

    public void init(HardwareMap map) {
        RobotMap.INCREMENT = 0.1;
        //drivetrain
        leftFront = map.dcMotor.get("2");
        rightFront = map.dcMotor.get("1");
        leftBack = map.dcMotor.get("4"); // changed originally rightFront
        rightBack = map.dcMotor.get("3");

        jewel = map.servo.get("jewelS");
        color = map.colorSensor.get("color");
        intake = map.dcMotor.get("intake");
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    //Driving Methods
    public void setDriveMode(DcMotor.RunMode mode) {
        leftFront.setMode(mode);
        leftBack.setMode(mode);
        rightFront.setMode(mode);
        rightBack.setMode(mode);
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behav) {
        leftFront.setZeroPowerBehavior(behav);
        leftBack.setZeroPowerBehavior(behav);
        rightFront.setZeroPowerBehavior(behav);
        rightBack.setZeroPowerBehavior(behav);
    }

    public void setDrivePower(double val) {
        setDrivePower(-val, -val);
    }

    public void setDrivePower(double left, double right) {
        left = rangeKeep(left, -1, 1);
        right = rangeKeep(right, -1, 1);

        leftBack.setPower(-left);
        leftFront.setPower(left);
        rightBack.setPower(right);
        rightFront.setPower(right);
    }

    public double[] getPower() {
        return new double[]{leftBack.getPower(), leftFront.getPower(), rightBack.getPower(), rightFront.getPower()};
    }

    public double[] getPosition() {
        return new double[]{leftBack.getCurrentPosition(), leftFront.getCurrentPosition(), rightBack.getCurrentPosition(), rightFront.getCurrentPosition()};
    }

    public void smoothDrive(double left, double right) {
        oldLeftSpeed = RobotMap.smoothSpeed(left, oldLeftSpeed, RobotMap.INCREMENT);
        oldRightSpeed = RobotMap.smoothSpeed(right, oldRightSpeed, RobotMap.INCREMENT);

        left = oldLeftSpeed;
        right = oldRightSpeed;

        setDrivePower(left, right);
    }

    public void smoothIntake(double speed) {
        oldIntakeSpeed = RobotMap.smoothSpeed(speed, oldIntakeSpeed, RobotMap.INCREMENT);

        speed = oldIntakeSpeed;

        this.intake.setPower(speed);
    }

    public void stop() {
        setDrivePower(0);
    }

    private double rangeKeep(double x, double min, double max) {
        if (x < min) return min;
        else if (x > max) return max;
        return x;
    }

    //Jewel Mech
    public void toggleJewel() {
        if (jewel.getPosition() == 1) {
            jewel.setPosition(0);
        } else {
            jewel.setPosition(1);
        }

        jewelup = !jewelup;
    }



}