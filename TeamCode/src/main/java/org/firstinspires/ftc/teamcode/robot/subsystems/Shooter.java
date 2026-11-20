package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter implements Subsystem {
    public enum State {
        IDLE,
        SPINNING
    }

    private DcMotorEx shooter = null;
    private State state = State.IDLE;
    private double targetPower = 0;

    public Shooter() {}

    /**
     * @param ahwMap The HardwareMap of the OpMode.
     */
    @Override
    public void init(HardwareMap ahwMap) {
        shooter = ahwMap.get(DcMotorEx.class, "shooter");
        shooter.setDirection(DcMotor.Direction.FORWARD);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooter.setPower(0);
    }

    public void spinUp(double power) {
        this.targetPower = Math.max(-1.0, Math.min(1.0, power));
        this.state = State.SPINNING;
    }

    /**
     * Called once per control loop iteration
     * Handles shooting condition and updates
     */
    @Override
    public void periodic() {
        if (state == State.SPINNING) {
            shooter.setPower(targetPower);
        }
        else {
            shooter.setPower(0);
        }
    }

    @Override
    public void stop() {
        state = State.IDLE;
        shooter.setPower(0);
    }
}
