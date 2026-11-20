package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

public interface Subsystem {
    void init(HardwareMap hwMap);
    void periodic();
    void stop();
}
