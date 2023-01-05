// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;



import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  private CANSparkMax  leftMotor1 = new CANSparkMax(12, MotorType.kBrushless);
  private CANSparkMax  leftMotor2 = new CANSparkMax(13, MotorType.kBrushless);
  private CANSparkMax  rightMotor1 = new CANSparkMax(15, MotorType.kBrushless);
  private CANSparkMax  rightMotor2 = new CANSparkMax(14, MotorType.kBrushless);
  WPI_TalonFX colecter= new  WPI_TalonFX(1);
  WPI_TalonFX shooter1= new  WPI_TalonFX(2);
  WPI_TalonFX shooter2= new  WPI_TalonFX(3);
  WPI_TalonSRX system = new  WPI_TalonSRX(4);


  
  MotorControllerGroup group1 = new MotorControllerGroup(rightMotor1, rightMotor2);
  MotorControllerGroup group2 = new MotorControllerGroup(leftMotor1, leftMotor2);
  DifferentialDrive myDrive = new DifferentialDrive(group2, group1);
  XboxController mycontroller = new XboxController(0);

  private double startTime;

  @Override
  public void robotInit() {
    rightMotor1.setIdleMode(IdleMode.kBrake);
    rightMotor2.setIdleMode(IdleMode.kBrake);
    leftMotor1.setIdleMode(IdleMode.kBrake);
    leftMotor2.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
    rightMotor1.setIdleMode(IdleMode.kCoast);
    rightMotor2.setIdleMode(IdleMode.kCoast);
    leftMotor1.setIdleMode(IdleMode.kCoast);
    leftMotor2.setIdleMode(IdleMode.kCoast);
  }

  @Override
  public void autonomousPeriodic() {
    double time =Timer.getFPGATimestamp();
    if (time<=3){ 
      myDrive.arcadeDrive(0.2,0);
    }
     else {
      myDrive.arcadeDrive(0,0);
     } 

  }

  @Override
  public void teleopInit() {
    rightMotor1.setIdleMode(IdleMode.kCoast);
    rightMotor2.setIdleMode(IdleMode.kCoast);
    leftMotor1.setIdleMode(IdleMode.kCoast);
    leftMotor2.setIdleMode(IdleMode.kCoast);
  }

  @Override
  public void teleopPeriodic() {
    double rotate = mycontroller.getLeftY()*0.8;
    double speed = mycontroller.getRightX()*0.8;

    myDrive.arcadeDrive(speed, rotate);

    rightMotor1.setIdleMode(IdleMode.kCoast);
    rightMotor2.setIdleMode(IdleMode.kCoast);
    leftMotor1.setIdleMode(IdleMode.kCoast);
    leftMotor2.setIdleMode(IdleMode.kCoast);

    
    //double shoot =  mycontroller.getLeftTriggerAxis()*0.4;
    //double collect = mycontroller.getRightTriggerAxis()*0.4;

  if (mycontroller.getRawButton(1)) {
     colecter.set(-0.5);
   } else {
    colecter.set(0);
   }
   if (mycontroller.getRawButton(2)) {
    shooter1.set(0.5);
    shooter2.set(0.5);
   } else {
    shooter1.set(0);
    shooter2.set(0);
    }   
    //colecter.set(collect);
    //shooter1.set(shoot);
    //shooter2.set(shoot);

  }

  

  @Override
  public void disabledInit() {
    rightMotor1.setIdleMode(IdleMode.kBrake);
    rightMotor2.setIdleMode(IdleMode.kBrake);
    leftMotor1.setIdleMode(IdleMode.kBrake);
    leftMotor2.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
