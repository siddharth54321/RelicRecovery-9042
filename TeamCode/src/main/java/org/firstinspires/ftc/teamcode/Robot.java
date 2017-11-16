package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    //drive motors
    public DcMotor leftFront, leftBack, rightFront, rightBack;
    public DcMotor linearSlideLeft, linearSlideRight;
    public boolean activeOpmode;

    //jewel mechanism
    public Servo jewel;
    public ColorSensor color;
    public boolean jewelup = true;

    public Servo glyphLeft;
    public Servo glyphRight;
    private boolean glyphOpen = true;

    public double oldLeftSpeed = 0, oldRightSpeed = 0;

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


        //operator
        linearSlideLeft = map.dcMotor.get("slideLeft");
        linearSlideLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linearSlideRight = map.dcMotor.get("slideRight");
        linearSlideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //jewelMechanism
        jewel = map.servo.get("Jewel");//TODO // FIXME: 10/21/17
        glyphLeft = map.servo.get("glyphLeft");
        glyphRight = map.servo.get("glyphRight");

        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linearSlideLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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

    public void setDrivePower(float val) {
        setDrivePower(-val, -val);
    }

    public void setDrivePower(double left, double right) {
        left = rangeKeep(left, -1, 1);
        right = rangeKeep(right, -1, 1);

        leftBack.setPower(left);
        leftFront.setPower(left);
        rightBack.setPower(-right);
        rightFront.setPower(-right);

    }

    public double[] getPower() {
        return new double[]{leftBack.getPower(), leftFront.getPower(), rightBack.getPower(), rightFront.getPower()};
    }

    public void smoothDrive(double left, double right) {
        oldLeftSpeed = RobotMap.smoothSpeed(left, oldLeftSpeed, RobotMap.INCREMENT);
        oldRightSpeed = RobotMap.smoothSpeed(right, oldRightSpeed, RobotMap.INCREMENT);

        left = oldLeftSpeed;
        right = oldRightSpeed;

        setDrivePower(left, right);
    }

    public void stop() {
        setDrivePower(0);
    }

//    public void driveDistance(int dist, float power) {
//        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);
//        leftFront.setTargetPosition(dist);
//        leftBack.setTargetPosition(dist);
//        rightFront.setTargetPosition(dist);
//        rightBack.setTargetPosition(dist);
//
//        leftFront.setPower(.55 * power);
//        leftBack.setPower(.55 * power);
//        rightFront.setPower(.55 * power);
//        rightBack.setPower(power);
//    }

    double leftError;
    double rightError;
    double leftTarget;
    double rightTarget;
    double powerD;

    public void driveDistance(int dist, float power){

        leftTarget = leftFront.getCurrentPosition()+ dist;
        rightTarget = rightFront.getCurrentPosition()+ dist;

        leftError = dist - (leftFront.getCurrentPosition());
        rightError = dist - (rightFront.getCurrentPosition());
        while(activeOpmode) {
            if(Math.abs(leftError) < RobotMap.DRIVE_TOLERANCE) break;
            else if(Math.abs(rightError) < RobotMap.DRIVE_TOLERANCE) break;
            else{
                powerD = RobotMap.P_CONSTANT_DRIVING*leftError;
                leftError = dist - (leftFront.getCurrentPosition());
                rightError = dist - (rightFront.getCurrentPosition());

                leftBack.setPower(RobotMap.P_CONSTANT_DRIVING*leftError);
                leftFront.setPower(RobotMap.P_CONSTANT_DRIVING*leftError);
                rightBack.setPower(-RobotMap.P_CONSTANT_DRIVING*rightError);
                rightFront.setPower(-RobotMap.P_CONSTANT_DRIVING*rightError);
            }
        }

        this.stop();
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

    //Glyph Servo
    public void toggleGlyph() {
        if (glyphOpen) {
            glyphLeft.setPosition(0.5);
            glyphRight.setPosition(0.5);
        } else {
            glyphLeft.setPosition(0);
            glyphRight.setPosition(1);
        }

        glyphOpen = !glyphOpen;
    }

}
