package com.valkyrierobotics.frc2019.subsystems;

import com.valkyrierobotics.frc2019.Constants;

import edu.wpi.first.wpilibj.Solenoid;

public class BallIntake extends Subsystem {
    // The boolean value indicating that the solenoid is open
    // Avoid magic numbers in the code
    private static final boolean kSolenoidOpen = true;

    private Solenoid intakeSolenoid;
    private boolean is_open;

    private static BallIntake instance = null;

    private BallIntake() {
        intakeSolenoid = new Solenoid(Constants.kBallIntakeSolenoid);

        // this is a single solenoid, let's default it's initial state to closed
        is_open = true;
        close();
    }

    public synchronized static BallIntake getInstance() {
        if (instance == null) {
            instance = new BallIntake();
        }
        return instance;
    }

    @Override
    public void stop() {
    }

    // Since we know our state, we can avoid double-closes and thus not pollute
    // the CAN bus so often (we hope).
    public synchronized void close() {
        if (is_open) {
            intakeSolenoid.set(!kSolenoidOpen);
            is_open = false;
        }
    }

    public synchronized void open() {
        if (!is_open) {
            intakeSolenoid.set(kSolenoidOpen);
            is_open = true;
        }
    }
}