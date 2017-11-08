package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Robot {
    //drive motors
    public DcMotor leftFront, leftBack, rightFront, rightBack, linearSlide;
    public boolean activeOpmode;

    //jewel mechanism
    public Servo jewel;
    public ColorSensor color;
    public boolean jewelup = true;

    public Servo glyphLeft;
    private boolean glyphOpen = true;

    public double oldLeftSpeed = 0, oldRightSpeed = 0;

    public Robot(HardwareMap map) {
        init(map);
    }

    public void init(HardwareMap map) {
        //drivetrain
        leftFront = map.dcMotor.get("2");
        rightFront = map.dcMotor.get("1");
        leftBack = map.dcMotor.get("4"); // changed originally rightFront
        rightBack = map.dcMotor.get("3");


        //operator
        linearSlide = map.dcMotor.get("LinearSlide");
        linearSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //jewelMechanism
        jewel = map.servo.get("Jewel");//TODO // FIXME: 10/21/17
//        glyphLeft = map.servo.get("glyphLeft");

//        resetMotorDirection();
        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ElapsedTime time = new ElapsedTime();
        while (time.seconds() < 2) {

        }
        setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
        leftFront.setPower(left); // fixed to be 55 percent power
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


    /**
     * @param left  right target in ticks
     * @param right right target in ticks
     * @param power initial power
     */
    public void driveDistance(int left, int right, float power) {
        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setTargetPosition(left);
        leftBack.setTargetPosition(left);
        rightFront.setTargetPosition(right);
        rightBack.setTargetPosition(right);

        leftFront.setPower(.55 * power);
        leftBack.setPower(.55 * power);
        rightFront.setPower(.55 * power);
        rightBack.setPower(power);
    }


//    public void driveDistance(int left, int right, float power){
//        double leftError;
//        double rightError;
//        do {
//            if (!this.activeOpmode) {
//                break;
//            }
//            leftError = left - (leftFront.getCurrentPosition() + leftBack.getCurrentPosition()) / 2.0;
//            rightError = right - (rightFront.getCurrentPosition() + rightBack.getCurrentPosition()) / 2.0;
//            double leftP = RobotMap.P_CONSTANT_DRIVING*leftError;
//            double rightP = RobotMap.P_CONSTANT_DRIVING*rightError;
//
//            leftBack.setPower(leftP);
//            leftFront.setPower(.55*leftP); // fixed to be 55 percent power
//            rightBack.setPower(.55*-rightP);
//            rightFront.setPower(.55*-rightP);
//        }while(Math.abs(leftError) > RobotMap.DRIVE_TOLERANCE || Math.abs(rightError) > RobotMap.DRIVE_TOLERANCE);
//
//        this.stop();
//    }

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
            glyphLeft.setPosition(1);
        } else {
            glyphLeft.setPosition(0);
        }

        glyphOpen = !glyphOpen;
    }

}
