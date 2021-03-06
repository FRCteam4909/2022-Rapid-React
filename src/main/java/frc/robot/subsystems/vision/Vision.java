package frc.robot.subsystems.vision;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.SimVisionSystem;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoMode.PixelFormat;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;

public class Vision extends SubsystemBase {

    private final double LIMELIGHT_HEIGHT_METERS = Units.inchesToMeters(42.5);
    private final double GOAL_HEIGHT_METERS = Units.inchesToMeters(103);
    private final double LIMELIGHT_PITCH_RADIANS = Units.degreesToRadians(15);

    private static Vision m_instance = null; 
    private PhotonPipelineResult m_pipelineResult;

    private boolean m_isAligned;
    private double m_xOffset;
    private double m_distance;
    private double m_avgDistance;

    private double limelightOffset;
    private PIDController m_turnPID;

    private PhotonCamera limelight;

    private LinearFilter m_distanceFilter;
    private LinearFilter m_offsetFilter;

    // private SimVisionSystem simVision;

    private Vision() {
        limelight = new PhotonCamera(NetworkTableInstance.getDefault(), "Limelight");
        m_turnPID = 
            new PIDController(VisionConstants.kVisionPIDGains.kP, VisionConstants.kVisionPIDGains.kI, VisionConstants.kVisionPIDGains.kD);

        m_distanceFilter = LinearFilter.movingAverage(10);
        m_offsetFilter = LinearFilter.movingAverage(10);
        SmartDashboard.putData(m_turnPID);

        // simVision = new SimVisionSystem(
        //     "Limelight",
        //     0,
        //     0, 
        //     new Transform2d(), 
        //     0, 
        //     0, 
        //     0, 
        //     0, 
        //     0);
    }


    @Override
    public void periodic() {
        m_pipelineResult = limelight.getLatestResult();

        if (m_pipelineResult.hasTargets()) {
            double targetPitch = Units.degreesToRadians(m_pipelineResult.getBestTarget().getPitch());

            m_distance = PhotonUtils.calculateDistanceToTargetMeters(
                LIMELIGHT_HEIGHT_METERS,
                GOAL_HEIGHT_METERS,
                LIMELIGHT_PITCH_RADIANS,
                targetPitch);

            m_xOffset = m_offsetFilter.calculate(m_pipelineResult.getBestTarget().getYaw());
            m_avgDistance = m_distanceFilter.calculate(m_distance);

            SmartDashboard.putBoolean("Is Aligned", m_isAligned);
            SmartDashboard.putNumber("Photon Distance", m_distance);

        } else {
            m_xOffset = 0;
            m_distance = 0;
        }

        if (Math.abs(this.m_xOffset) <= 1) m_isAligned = true;
        else m_isAligned = false;
    }

    public void setLimelightOffset() {

        double offset = -this.m_xOffset;
        double angularSpeed = -m_turnPID.calculate(offset, 0);
        SmartDashboard.putNumber("Angular Speed", angularSpeed);
        SmartDashboard.putNumber("Offset", offset);
        if (this.m_isAligned == true) {
            limelightOffset = 0;
        } else {
            if (this.m_xOffset <= 0)
                limelightOffset = angularSpeed + VisionConstants.kVisionPIDGains.kF;

            if (this.m_xOffset >= 0) 
                limelightOffset = angularSpeed - VisionConstants.kVisionPIDGains.kF;
        }

    }

    public void setLimelightOffset(double value) {
        limelightOffset = value;
    }

    public void takeSnapshot() {
        limelight.takeInputSnapshot();
        limelight.takeOutputSnapshot();
    }

    public double getLatencySeconds() {
        return m_pipelineResult.getLatencyMillis() / 1000d;
    }

    public double getDistance() {
        return m_distance;
    }

    public double getAverageDistance(){
        return m_avgDistance;
    }

    public double getLimelightOffset() {
        return this.limelightOffset;
    }

    public static Vision getInstance() {
        if (m_instance == null) {
            m_instance = new Vision();
        }

        return m_instance;
    }
    
}
