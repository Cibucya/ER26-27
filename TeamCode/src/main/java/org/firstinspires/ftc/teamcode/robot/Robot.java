package org.firstinspires.ftc.teamcode.robot;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.robot.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.robot.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
import org.firstinspires.ftc.teamcode.robot.subsystems.Webcam;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private final List<Subsystem> subsystems = new ArrayList<>();

    private HardwareMap hwMap = null;
    public MecanumDrive drive = new MecanumDrive();
    public Shooter shooter = new Shooter();
    public Turret turret = new Turret();
    public Intake intake = new Intake();
    public Webcam webcam = new Webcam();

    public Robot() {
        subsystems.add(drive);
        subsystems.add(shooter);
        subsystems.add(turret);
        subsystems.add(intake);
        subsystems.add(webcam);
    }

    /**
     * Initialize the robot hardware.
     * @param ahwMap The HardwareMap from the OpMode.
     */
    public void init(HardwareMap ahwMap) {
        if (ahwMap == null) {
            throw new IllegalArgumentException("Robot: Hardware map not initialized.");
        }

        this.hwMap = ahwMap;

        for (Subsystem subsystem : subsystems) {
            subsystem.init(hwMap);
        }

        turret.setWebcam(this.webcam);
    }

    /**
     * Called once per control loop iteration
     * Handles robot behaviour and updates
     */
    public void periodic() {
        for (Subsystem subsystem : subsystems) {
            subsystem.periodic();
        }
    }

    public void stop() {
        for (Subsystem subsystem : subsystems) {
            subsystem.stop();
        }
    }
}
