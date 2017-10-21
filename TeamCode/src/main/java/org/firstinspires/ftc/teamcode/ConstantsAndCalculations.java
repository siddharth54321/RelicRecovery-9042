package org.firstinspires.ftc.teamcode;

public class ConstantsAndCalculations {
    public static final float GEAR_RATIO = 50;
    public static final float TICKS_PER_ROTATION = 1120;
    public static final float WHEEL_DIAMETER = 4; //inch

    public static final float DRIVE_TOLERANCE = 100;
    public static final float P_CONSTANT_DRIVING = 1;

    //value in inches
    public static float distanceToTicks(float dist){
        float circumference = (float)Math.PI* WHEEL_DIAMETER;
        float wheelRevolutions = dist/circumference;
        float shaftRevolutions = wheelRevolutions * GEAR_RATIO;
        return shaftRevolutions * TICKS_PER_ROTATION;
    }
}
