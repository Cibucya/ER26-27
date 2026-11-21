package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Turret implements Subsystem {
    public enum State {
        IDLE,
        SPINNING,
        TRACKING
    }

    private CRServo turret = null;
    private State state = State.IDLE;
    private double targetPower = 0;
    // TODO: instead of this, create a camera instance in this class. make this variable availabre in camera class.
    private Webcam webcam = null;

    public void setWebcam(Webcam webcam){
        this.webcam = webcam;
    }

    public void startTracking() {
        this.state = State.TRACKING;
    }

    public void stopTracking(){
        this.state = State.IDLE;
        turret .setPower(0);
    }

    // TODO: implement startTracking() and stopTracking()
    // I also thing that this thing might not work since we are not counting for the servo gear ratio, speed, etc.
    // Thus, we will have a giant offset. We will see when the turret will be ready tomorrow and test it.
    // Maybe we won't use auto decetion. Just fix all the todo's which I've mentioned.

    /**
     * @param ahwMap The Hardware Map from the OpMode
     */
    @Override
    public void init(HardwareMap ahwMap) {
        turret = ahwMap.get(CRServo.class, "turret");
        turret.setDirection(CRServo.Direction.FORWARD);
        turret.setPower(0);
    }

    public void spinUp(double power) {
        this.targetPower = Math.max(-1.0, Math.min(1.0, power));
        this.state = State.SPINNING;
    }

    /**
     * Called once per control loop iteration
     * Handles rotation and updates
     */
    @Override
    public void periodic() {
        if (state == State.SPINNING) {
            turret.setPower(targetPower);
        }

        else if (state == State.TRACKING) {
            if (webcam != null && webcam.isTagVisible()){
                double currentTagAngle = webcam.getTagAngle();

                // Kp Scales the motor power based on the angular error distance to the target
                // A higher Kp makes the turret snap faster, while too high causes oscillation
                double Kp = 0.02;
                double autoPower = Kp * currentTagAngle;
                autoPower = Math.max(-1.0, (Math.min(1.0, autoPower)));

                if (Math.abs(currentTagAngle) < 2.0){
                    autoPower = 0;
                } else {
                    turret.setPower(0);
                }
                turret.setPower(autoPower);
            }
        } else turret.setPower(0);
    }

    @Override
    public void stop() {
        state = State.IDLE;
        turret.setPower(0);
    }
}