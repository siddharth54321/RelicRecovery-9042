package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.robotcore.external.Const;

public class Robot {
    //Subsystem
    public DriveSubsystem drive;
    public JewelSubsystem jewel;

    //instance
    public boolean activeOpmode;


    public Robot(HardwareMap map){
        init(map);
    }

    public void init(HardwareMap map){
        drive = new DriveSubsystem(map);
        jewel = new JewelSubsystem(map);
    }


}
