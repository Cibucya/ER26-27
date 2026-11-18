package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.subsystems.MechanumDrive;

public class Robot {
    private HardwareMap hwMap = null;
    public MechanumDrive drive = null;

    private DcMotorEx frontShooter;
    private CRServo turretAxis, intakeLeft, intakeRight;


    public Robot() {}

    /**
     * Initialize the robot hardware.
     * @param ahwMap The HardwareMap from the OpMode.
     */
    public void init(HardwareMap ahwMap) {
        if (ahwMap == null) {
            throw new IllegalArgumentException("Robot: Hardware map not initialized.");
        }

        // TODO: change when motors are installed and driver station is set up
        frontShooter = ahwMap.get(DcMotorEx.class, "front_shooter_drive");
        turretAxis = ahwMap.get(CRServo.class, "servo_turret_axis");
        intakeLeft = ahwMap.get(CRServo.class, "servo_intake_left");
        intakeRight = ahwMap.get(CRServo.class, "servo_intake_right");


        // TODO: change when motors are installed
        frontShooter.setDirection(DcMotor.Direction.FORWARD);
        turretAxis.setDirection(CRServo.Direction.FORWARD);
        intakeLeft.setDirection(DcMotor.Direction.FORWARD);
        intakeRight.setDirection(DcMotor.Direction.FORWARD);

        this.hwMap = ahwMap;

        this.drive = new MechanumDrive();

        this.drive.init(hwMap);
    }

    public void periodic() {
        this.drive.periodic();
    }

    public void setPushPower(double turretPwr, double intakePwr, double shooterPwr){
        turretAxis.setPower(turretPwr);
        intakeLeft.setPower(intakePwr);
        intakeRight.setPower(intakePwr);
        frontShooter.setPower(shooterPwr);
    }

}
