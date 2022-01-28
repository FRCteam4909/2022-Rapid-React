// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.swervedrivespecialties.swervelib.ModuleConfiguration;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper.GearRatio;

import frc.robot.utils.Conversion;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = Conversion.inchesToMeters(22.181999);

    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = Conversion.inchesToMeters(22.181999);

    /**
     * The free speed of a Falcon 500 Motor
     * 
     * In actual RPM, not ticks
     */
    public static final double FALCON_500_FREE_SPEED = 6380;
    /**
     * The gear ratio of the Swerve Module
     * 
     * From L1-L4
     */
    public static final GearRatio GEAR_RATIO = GearRatio.L2;
    /**
     * The type of Swerve Module used
     * 
     * From L1-L4
     */
    public static final ModuleConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4_L2; 

    /**
     * CAN ID of the PigeonIMU
     */
    public static final int DRIVETRAIN_PIGEON_ID = 13;
    
    // FRONT LEFT : Florida
    public static final int FRONT_LEFT_DRIVE_MOTOR = 1;
    public static final int FRONT_LEFT_STEER_MOTOR = 2;
    public static final int FRONT_LEFT_STEER_ENCODER = 9;
    public static final double FRONT_LEFT_STEER_OFFSET = -Math.toRadians(194.238); // FIXME Measure and set front left steer offset //192.205810546875

    // FRONT RIGHT : France
    public static final int FRONT_RIGHT_DRIVE_MOTOR = 3;
    public static final int FRONT_RIGHT_STEER_MOTOR = 4;
    public static final int FRONT_RIGHT_STEER_ENCODER = 10;
    public static final double FRONT_RIGHT_STEER_OFFSET = -Math.toRadians(10.272); // FIXME Measure and set back left steer offset //9.3109130859375

    // BACK RIGHT : Railroad
    public static final int BACK_RIGHT_DRIVE_MOTOR = 5;
    public static final int BACK_RIGHT_STEER_MOTOR = 6;
    public static final int BACK_RIGHT_STEER_ENCODER = 11;
    public static final double BACK_RIGHT_STEER_OFFSET = -Math.toRadians(302.783); // FIXME Measure and set back right steer offset //305.419921875

    // BACK LEFT : Real Life
    public static final int BACK_LEFT_DRIVE_MOTOR = 7;
    public static final int BACK_LEFT_STEER_MOTOR = 8;
    public static final int BACK_LEFT_STEER_ENCODER = 12;
    public static final double BACK_LEFT_STEER_OFFSET = -Math.toRadians(124.708); // FIXME Measure and set back left steer offset //126.28784179687499
    
    // The Max Acceleration Value for the robot [only in AUTO]
    public static final double MAX_ACCEL = 4;

    // The Max Velocity for the robot [only in AUTO]
    public static final double MAX_VEL = 6.5;

    // Superstructure Constants
    public static final double kIntakeForwardVoltage = 6.0;
    public static final double kIntakeReverseVotlage = -6.0;
    public static final double kFeederFeedingVoltage = 1.0;
    public static final double kFeederShootingVoltage = 12.0;
    public static final double kCenteringWheelForwardVoltage = 4.0;
    public static final double kCenteringWheelReverseVotlage = -4.0;

}
