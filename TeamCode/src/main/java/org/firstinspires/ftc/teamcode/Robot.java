package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    // Drive motors
    private DcMotorEx frontShooter;
    private CRServo turretAxis, intakeLeft, intakeRight;

    public Robot() { }

    /**
     * Initialize the robot hardware.
     * @param ahwMap The HardwareMap from the OpMode.
     */
    public void init(HardwareMap ahwMap) {
        // TODO: change when motors are installed and driver station is set up
        frontShooter = ahwMap.get(DcMotorEx.class, "front_shooter_drive");
        turretAxis = ahwMap.get(CRServo.class, "servo_turret_axis");
        intakeLeft = ahwMap.get(CRServo.class, "servo_intake_left");
        intakeRight = ahwMap.get(CRServo.class, "servo_intake_right");


        // TODO: change when motors are installed
        frontShooter.setDirection(DcMotor.Direction.FORWARD);
        turretAxis.setDirection(CRServo.Direction.FORWARD);
        intakeLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    /**
     * Resets encoders
     */

    public void setPushPower(double turretPwr, double intakePwr, double shooterPwr){
        turretAxis.setPower(turretPwr);
        intakeLeft.setPower(intakePwr);
        intakeRight.setPower(intakePwr);
        frontShooter.setPower(shooterPwr);
    }
}
