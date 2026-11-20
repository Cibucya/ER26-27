package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Turret implements Subsystem {
    public enum State {
        IDLE,
        SPINNING
    }

    private CRServo turret = null;
    private State state = State.IDLE;
    private double targetPower = 0;

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
        if (state == State.SPINNING) turret.setPower(targetPower);
        else turret.setPower(0);
    }

    @Override
    public void stop() {
        state = State.IDLE;
        turret.setPower(0);
    }
}