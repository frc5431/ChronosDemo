/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5431.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import org.usfirst.frc.team5431.robot.Titan.Command;
import org.usfirst.frc.team5431.robot.subsystems.ExampleSubsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private Titan.LogitechExtreme3D Logitech; 
	private Titan.Xbox driverXbox;
	private WPI_TalonSRX frontLeft, frontRight, rearLeft, rearRight,shootLeft,shootRight,intake;
	private DifferentialDrive driveBase;
	private SpeedControllerGroup left, right,shooter; 
	private Titan.Toggle isIntaking = new Titan.Toggle();

	

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void robotInit() {
	/**
	 * initializing all the variables in the project
	 */
	driverXbox = new Titan.Xbox(0);
	Logitech = new Titan.LogitechExtreme3D(1); 
	
	driverXbox.setDeadzone(0.15);
	
	frontLeft = new WPI_TalonSRX(2);	frontLeft.setInverted(true);
	frontRight = new WPI_TalonSRX(8);	frontRight.setInverted(true);
	rearLeft = new WPI_TalonSRX(3);		rearLeft.setInverted(true);
	rearRight = new WPI_TalonSRX(7);	rearRight.setInverted(true);
	shootLeft = new WPI_TalonSRX(4);	rearRight.setInverted(true);
	shootRight = new WPI_TalonSRX(6); 
	frontLeft.setInverted(true);
	frontRight.setInverted(true);
	rearLeft.setInverted(true);
	rearRight.setInverted(true);
	intake = new WPI_TalonSRX(5); 
	left = new SpeedControllerGroup(frontLeft,rearLeft);
	right = new SpeedControllerGroup(frontRight,rearRight);
	shooter = new SpeedControllerGroup(shootLeft,shootRight);
	driveBase = new DifferentialDrive(left,right); 	
}

	/**
	 * This function is called periodically during operator control.
	 */ 
	@Override
	public void teleopPeriodic() {
		double leftY = driverXbox.getRawAxis(Titan.Xbox.Axis.LEFT_Y); 
		double rightY = driverXbox.getRawAxis(Titan.Xbox.Axis.RIGHT_Y); 
		double shooterY = Logitech.getRawAxis(Titan.LogitechExtreme3D.Axis.Y); 
		boolean boolIntake = Logitech.getRawButton(1); 
		boolean boolOutake = Logitech.getRawButton(2); 
		driveBase.tankDrive(leftY, rightY);
		shooter.set(shooterY);
		if(isIntaking.isToggled(boolIntake)) {
			intake.set(0.6);
		}
		else if(boolOutake) {
			intake.set(-0.6);
		}
		else {
			intake.set(0);
		}
		
		
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
