package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by anikaitsingh on 12/11/17.
 */

public class Vision {

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    public Vision(HardwareMap hardwareMap) {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AQ/l4q3/////AAAAGWX0r7rdzUsuj6QCa8YP5D5rYoWgT9lkaNDBWNoJ37Vndims7VbiMjkoHPruZLW8gJFb1CZ/CLxh1pmzXR3FeegDAHHi5+xEa3FREjvP4lQXlXMH0/1A5qrY0q0lfueGcAu2RJRiLKSrWJySeglWiNz3pIRg+LtUYoXv9kON+OO0pNdix3SZyLiFkyQ3fEEZKRVPlr27Uy5gLNCwVHPqhcmtDiVOVMRHTj9WX+Mp+w5S0DDiPqecb8LlQbGRhmbD7em8VtS8OapE7i+OzSTeYJnFjuek5hYXJzCOu9eWHCNTLIlsAPYXajH6VHybDJdpGy9RNh3oBce47ewsz5voSdhTfr8nGbkYpmkhOi7LLtjG";


        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);


        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();
    }

    public RelicRecoveryVuMark getVuMark(Telemetry telemetry) {

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
            telemetry.addData("VuMark", "%s visible", vuMark);
        } else {
            telemetry.addData("VuMark", "not visible");
        }

        telemetry.update();
        return vuMark;
    }


}
