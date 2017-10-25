package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by anikaitsingh on 10/24/17.
 */

public class DriveSubsystem {

    public DcMotor leftFront, leftBack, rightFront, rightBack;


    public DriveSubsystem(HardwareMap map){
        init(map);
    }

    public void init(HardwareMap map){
        //drivetrain
        leftFront = map.dcMotor.get("2");
        rightFront = map.dcMotor.get("1");
        leftBack = map.dcMotor.get("4"); // changed originally rightFront
        rightBack = map.dcMotor.get("3");

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


    public void setDrivePower(double left, double right) {
        left = rangeKeep(left, -1, 1);
        right = rangeKeep(right, -1, 1);

        leftBack.setPower(left);
        leftFront.setPower(.55*left); // fixed to be 55 percent power
        rightBack.setPower(.55*-right);
        rightFront.setPower(.55*-right);

    }

    public void setDrivePower(float val){
        setDrivePower(val,val);
    }

    public void stop(){
        setDrivePower(0);
    }


    /**
     * @param left right target in ticks
     * @param right right target in ticks
     * @param power initial power
     */
    public void driveDistance(int left, int right, float power){
        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setTargetPosition(left);
        leftBack.setTargetPosition(left);
        rightFront.setTargetPosition(right);
        rightBack.setTargetPosition(right);

        leftBack.setPower(power);
        leftFront.setPower(power);
        rightBack.setPower(power);
        rightFront.setPower(power);
    }



    private double rangeKeep(double x, double min, double max){
        if (x < min) return min;
        else if(x > max) return max;
        return x;
    }
}
