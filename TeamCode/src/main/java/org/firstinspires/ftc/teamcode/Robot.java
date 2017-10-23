package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.robotcore.external.Const;

public class Robot {
    //drive motors
    public DcMotor leftFront, leftBack, rightFront, rightBack;
    public boolean activeOpmode;

    //jewel mechanism
    private Servo jewel;
    private ColorSensor color;
    private boolean jewelup = true;


    public Robot(HardwareMap map){
        init(map);
    }

    public void init(HardwareMap map){
        //drivetrain
        leftFront = map.dcMotor.get("2");
        rightFront = map.dcMotor.get("1");
        leftBack = map.dcMotor.get("4"); // changed originally rightFront
        rightBack = map.dcMotor.get("3");

        //jewelMechanism
        //jewel = map.servo.get("jewel");//TODO // FIXME: 10/21/17

        resetMotorDirection();
        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //Driving Methods
    public void setDriveMode(DcMotor.RunMode mode){
        leftFront.setMode(mode);
        leftBack.setMode(mode);
        rightFront.setMode(mode);
        rightBack.setMode(mode);
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behav){
        leftFront.setZeroPowerBehavior(behav);
        leftBack.setZeroPowerBehavior(behav);
        rightFront.setZeroPowerBehavior(behav);
        rightBack.setZeroPowerBehavior(behav);
    }

    public void resetMotorDirection(){
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        //set right motors in reverse (configuration of motors on robot)
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
    }

    public void setDrivePower(double left, double right) {
        left = rangeKeep(left, -1, 1);
        right = rangeKeep(right, -1, 1);

        leftFront.setPower(left);
        leftBack.setPower(left);

        rightFront.setPower(right);
        rightBack.setPower(right);

    }

    public void setDrivePower(float val){
        setDrivePower(val,val);
    }

    public void stop(){
        setDrivePower(0);
    }

//    public void driveDistance(int left, int right, float power){
//        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);
//        leftFront.setTargetPosition(left);
//        leftBack.setTargetPosition(left);
//        rightFront.setTargetPosition(right);
//        rightBack.setTargetPosition(right);
//        setDrivePower(power);
//    }

    /**
     * @param left right target in ticks
     * @param right right target in ticks
     * @param power initial power
     */
    public void driveDistance(int left, int right, float power){
        double leftError;
        double rightError;
        do {
            leftError = left - (leftFront.getCurrentPosition() + leftBack.getCurrentPosition()) / 2.0;
            rightError = left - (rightFront.getCurrentPosition() + rightBack.getCurrentPosition()) / 2.0;
            setDrivePower(ConstantsAndCalculations.P_CONSTANT_DRIVING*leftError, ConstantsAndCalculations.P_CONSTANT_DRIVING*rightError);
        }while(Math.abs(leftError) > ConstantsAndCalculations.DRIVE_TOLERANCE || this.activeOpmode);

        this.stop();
    }

    private double rangeKeep(double x, double min, double max){
        if (x < min) return min;
        else if(x > max) return max;
        return x;
    }


    //Jewel Mech
    public void toggleJewel(){
        if(jewelup){
            jewel.setPosition(1);
        }else{
            jewel.setPosition(0);
        }

        jewelup = !jewelup;
    }


}
