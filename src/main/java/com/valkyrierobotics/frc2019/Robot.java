package com.valkyrierobotics.frc2019;

import java.util.Arrays;
import java.util.List;

import com.valkyrierobotics.frc2019.subsystems.Subsystem;
import com.valkyrierobotics.frc2019.subsystems.BallIntake;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
    private final Subsystems subsystems = new Subsystems(
        Arrays.asList(
            BallIntake.getInstance()
        )
    );

    @Override
    public void robotInit() {
        // Set up the robot here!
    }

    @Override
    public void disabledInit() {
        // Runs when the robot enters disabled mode
    }

    @Override
    public void autonomousInit() {
        // Runs when the robot enters autonomous mode
    }

    @Override
    public void teleopInit() {
        // Runs when the robot enters teleop mode

        BallIntake.getInstance().close();
    }

    @Override
    public void disabledPeriodic() {
        // Runs when the robot is in disabled mode

        subsystems.executeLoop();
    }

    @Override
    public void autonomousPeriodic() {
        // Runs when the robot is in autonomous mode

        subsystems.executeLoop();
    }

    @Override
    public void teleopPeriodic() {
        // Runs when the robot is in teleop mode

        subsystems.executeLoop();
    }

    // This class wraps a bunch of features of subsystems so that
    // we don't have to call a bunch of subsystems :^)
    class Subsystems {
        private final List<Subsystem> subsystems;

        public Subsystems(List<Subsystem> subsystems) {
            this.subsystems = subsystems;
        }

        public void stop() {
            subsystems.forEach((subsystem) -> subsystem.stop());
        }

        public void executeLoop() {
            for (Subsystem s : subsystems) {
                subsystems.forEach((subsystem) -> subsystem.readInputs());
            }
            for (Subsystem s : subsystems) {
                subsystems.forEach((subsystem) -> subsystem.writeOutputs());
            }
        }
    }
}
