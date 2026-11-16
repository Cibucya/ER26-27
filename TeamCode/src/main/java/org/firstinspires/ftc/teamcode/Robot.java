package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    // Drive motors
    private DcMotorEx frontLeft, frontRight, backLeft, backRight;
    private CRServo turretAxis, intakeLeft, intakeRight;

    public Robot() { }

    /**
     * Initialize the robot hardware.
     * @param ahwMap The HardwareMap from the OpMode.
     */
    public void init(HardwareMap ahwMap) {
        // TODO: change when motors are installed and driver station is set up
        frontLeft  = ahwMap.get(DcMotorEx.class, "front_left_drive");
        frontRight = ahwMap.get(DcMotorEx.class, "front_right_drive");
        backLeft   = ahwMap.get(DcMotorEx.class, "back_left_drive");
        backRight  = ahwMap.get(DcMotorEx.class, "back_right_drive");
        turretAxis = ahwMap.get(CRServo.class, "servo_turret_axis");
        intakeLeft = ahwMap.get(CRServo.class, "servo_intake_left");
        intakeRight = ahwMap.get(CRServo.class, "servo_intake_right");


        // TODO: change when motors are installed
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        turretAxis.setDirection(CRServo.Direction.FORWARD);
        intakeLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeRight.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        reset();
    }

    /**
     * Resets encoders
     */
    public void reset() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void setPushPower(double turretPwr, double intakePwr){
        turretAxis.setPower(turretPwr);
        intakeLeft.setPower(intakePwr);
        intakeRight.setPower(intakePwr);
    }
}
