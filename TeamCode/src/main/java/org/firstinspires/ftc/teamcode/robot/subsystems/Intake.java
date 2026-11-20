package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake implements Subsystem{

    public enum State {
        IDLE,
        SPINNING
    }
    private CRServo intakeLeft, intakeRight;
    private Intake.State state = Intake.State.IDLE;
    private double power = 0;

    public Intake(){}

    @Override
    public void init(HardwareMap ahwMap){
        intakeLeft = ahwMap.get(CRServo.class, "servo_intake_left");
        intakeRight = ahwMap.get(CRServo.class, "servo_intake_right");

        intakeLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeRight.setDirection(DcMotorSimple.Direction.FORWARD);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);
    }

    public void spin (double power){
        this.power = Math.max(-1.0, Math.min(1.0, power));
        this.state = State.SPINNING;
    }

    @Override
    public void periodic(){
        if (state == State.SPINNING){
            intakeLeft.setPower(power);
            intakeRight.setPower(power);
        }
    }

    @Override
    public void stop(){
        state = State.IDLE;
        power = 0;
        intakeLeft.setPower(0);
        intakeRight.setPower(0);
    }

}
