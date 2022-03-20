package frc.robot.subsystems.drivetrain.commands.auto_routines;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.commands.PathResetOdometry;
import frc.robot.subsystems.drivetrain.commands.TrajectoryFollow;
import frc.robot.subsystems.intake.IntakeFeeder;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.LimelightShoot;
import frc.robot.subsystems.vision.VisionSubsystem;

public class TwoBallBottomTarmac extends SequentialCommandGroup {

	IntakeFeeder intake_ = IntakeFeeder.getInstance();
	Shooter shooter_ = Shooter.getInstance();
	VisionSubsystem vision_ = VisionSubsystem.getInstance();

	public TwoBallBottomTarmac() {
		addCommands(

				new PathResetOdometry("Tarmac-Almost-A"), (
						new TrajectoryFollow("Tarmac-Almost-A").withTimeout(2)
								.raceWith(new RunCommand(intake_::intake, intake_))
				)
						.andThen(new InstantCommand(intake_::stopIntake)),

				new LimelightShoot(Constants.kWallShotVelocity, true, false)
		);

	}

}