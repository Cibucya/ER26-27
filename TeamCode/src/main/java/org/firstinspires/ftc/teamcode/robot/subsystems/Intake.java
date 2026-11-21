package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.List;

public class Intake implements Subsystem {

    public enum State {
        IDLE,
        SPINNING
    }

    private ArrayList<DcMotorEx> motors = new ArrayList<>();

    private DcMotorEx liftMotor = null;
    private DcMotorEx frontMotor = null;
    private Intake.State state = Intake.State.IDLE;
    private double targetPower = 0;

    public Intake() {}

    @Override
    public void init(HardwareMap ahwMap) {
        liftMotor = ahwMap.get(DcMotorEx.class, "servo_intake_lift");
        frontMotor = ahwMap.get(DcMotorEx.class, "servo_intake_front");
        
        motors.clear();
        motors.addAll(List.of(liftMotor, frontMotor));

        liftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        for (DcMotorEx motor : motors) motor.setPower(0);
    }

    public void spinUp(double power) {
        this.targetPower = Math.max(-1.0, Math.min(1.0, power));
        this.state = State.SPINNING;
    }

    @Override
    public void periodic() {
        if (state == State.SPINNING){
            for (DcMotorEx motor : motors) motor.setPower(targetPower);
        }
    }

    @Override
    public void stop() {
        state = State.IDLE;
        for (DcMotorEx motor : motors) motor.setPower(0);
    }
}
