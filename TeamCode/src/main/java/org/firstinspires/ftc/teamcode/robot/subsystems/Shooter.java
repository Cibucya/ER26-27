package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter implements Subsystem {
    public enum State {
        IDLE,
        SPINNING
    }

    private DcMotorEx shooterPollen = null;
    private DcMotorEx shooterNectar = null;
    private State statePollen = State.IDLE;
    private State stateNectar = State.IDLE;
    private double targetPowerPollen = 0;
    private double targetPowerNectar = 0;

    public Shooter() {}

    /**
     * @param ahwMap The HardwareMap of the OpMode.
     */
    @Override
    public void init(HardwareMap ahwMap) {
        shooterPollen = ahwMap.get(DcMotorEx.class, "shooter_pollen");
        shooterNectar = ahwMap.get(DcMotorEx.class, "shooter_nectar");

        shooterPollen.setDirection(DcMotor.Direction.FORWARD);
        shooterNectar.setDirection(DcMotor.Direction.FORWARD);

        shooterPollen.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterNectar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        shooterPollen.setPower(0);
        shooterNectar.setPower(0);
    }

    public void spinUpPollen(double power) {
        this.targetPowerPollen = Math.max(-1.0, Math.min(1.0, power));
        this.statePollen = State.SPINNING;
    }

    public void spinUpNectar(double power) {
        this.targetPowerNectar = Math.max(-1.0, Math.min(1.0, power));
        this.stateNectar = State.SPINNING;
    }

    public void spinUp(double power) {
        spinUpPollen(power);
        spinUpNectar(power);
    }

    /**
     * Called once per control loop iteration
     * Handles shooting condition and updates
     */
    @Override
    public void periodic() {
        if (statePollen == State.SPINNING) shooterPollen.setPower(targetPowerPollen);
        else shooterPollen.setPower(0);

        if (stateNectar == State.SPINNING) shooterNectar.setPower(targetPowerNectar);
        else shooterNectar.setPower(0);
    }

    public void stopPollen() {
        statePollen = State.IDLE;
        shooterPollen.setPower(0);
    }

    public void stopNectar() {
        stateNectar = State.IDLE;
        shooterNectar.setPower(0);
    }

    @Override
    public void stop() {
        stopPollen();
        stopNectar();
    }
}
