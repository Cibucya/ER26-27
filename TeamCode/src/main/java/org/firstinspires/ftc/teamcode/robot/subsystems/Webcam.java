package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.List;

public class Webcam implements Subsystem{

    private AprilTagProcessor aprilTag = null;
    private VisionPortal visionPortal = null;

    private double tagAngle = 0.0;
    private double tagDistance = 0.0;
    private boolean tagVisible = false;

    private int minTagId = 0;
    private int maxTagId = 50;

    public Webcam(){}

    @Override
    public void init(HardwareMap ahwMap){
        aprilTag = new AprilTagProcessor.Builder().build();

        visionPortal = new VisionPortal.Builder().setCamera(ahwMap.get(WebcamName.class, "Webcam"))
                .addProcessor(aprilTag).build();
    }

    public void setTagIdRange(int min, int max){
        this.minTagId = min;
        this.maxTagId = max;
    }

    public void updateDetecting(){
        if ( aprilTag != null) {
            tagVisible = false;
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();

            for (AprilTagDetection detection : currentDetections) {
                if (detection instanceof AprilTagSingleDetection) {
                    AprilTagSingleDetection singleDet = (AprilTagSingleDetection) detection;

                    if (singleDet.ftcPose != null && singleDet.id >= minTagId && singleDet.id <= maxTagId) {
                        tagAngle = singleDet.ftcPose.bearing;
                        tagDistance = singleDet.ftcPose.y;
                        tagVisible = true;
                        break;
                    }
                }
            }
        } else {
            tagVisible = false;
        }
    }

    @Override
    public void periodic(){}

    @Override
    public void stop(){
        if (visionPortal != null){
            visionPortal.close();
        }
    }

    public boolean isTagVisible(){
        return tagVisible;
    }

    public double getTagAngle(){
        return tagAngle;
    }

    public double getTagDistance(){
        return tagDistance;
    }


}
