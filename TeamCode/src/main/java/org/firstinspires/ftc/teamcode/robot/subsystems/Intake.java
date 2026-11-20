package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake implements Subsystem {

    public enum State {
        IDLE,
        SPINNING
    }
    private CRServo intakeLift = null;
    private CRServo intakeFront = null;
    private Intake.State state = Intake.State.IDLE;
    private double targetPower = 0;

    public Intake() {}

    @Override
    public void init(HardwareMap ahwMap) {
        intakeLift = ahwMap.get(CRServo.class, "servo_intake_lift");
        intakeFront = ahwMap.get(CRServo.class, "servo_intake_front");

        intakeLift.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeFront.setDirection(DcMotorSimple.Direction.FORWARD);

        intakeLift.setPower(0);
        intakeFront.setPower(0);
    }

    public void spinUp(double power) {
        this.targetPower = Math.max(-1.0, Math.min(1.0, power));
        this.state = State.SPINNING;
    }

    @Override
    public void periodic() {
        if (state == State.SPINNING){
            intakeLift.setPower(targetPower);
            intakeFront.setPower(targetPower);
        }
    }

    @Override
    public void stop() {
        state = State.IDLE;
        intakeLift.setPower(0);
        intakeFront.setPower(0);
    }

}
