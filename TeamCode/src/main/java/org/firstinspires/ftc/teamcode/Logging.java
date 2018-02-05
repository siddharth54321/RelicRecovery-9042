package org.firstinspires.ftc.teamcode;

import android.util.Log;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by anikaitsingh on 12/1/17.
 */

public class Logging {

    public static void log(String caption, Object value, Telemetry telemetry){
        Log.i(caption, value.toString());
        telemetry.addData(caption, value);
    }

}
