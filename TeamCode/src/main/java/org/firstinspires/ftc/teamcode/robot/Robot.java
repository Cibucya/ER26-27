package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.subsystems.MechanumDrive;

public class Robot {
    private HardwareMap hwMap = null;
    public MechanumDrive drive = null;

    public Robot() {}

    /**
     * Initialize the robot hardware.
     * @param ahwMap The HardwareMap from the OpMode.
     */
    public void init(HardwareMap ahwMap) {
        if (ahwMap == null) {
            throw new IllegalArgumentException("Robot: Hardware map not initialized.");
        }

        this.hwMap = ahwMap;
        this.drive = new MechanumDrive();

        this.drive.init(hwMap);
    }

    public void periodic() {
        this.drive.periodic();
    }
}
