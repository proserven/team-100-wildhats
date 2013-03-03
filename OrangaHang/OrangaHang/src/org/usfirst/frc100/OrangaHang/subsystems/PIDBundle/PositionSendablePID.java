/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc100.OrangaHang.subsystems.PIDBundle;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 *
 * @author Isis
 */
public class PositionSendablePID implements Sendable{
    
    private Preferences prefs = Preferences.getInstance();
    
    private final PIDSource m_source;
    private final PIDOutput m_output;
    private final TimedThread m_thread;
    private final PositionPIDBase m_base;
    private final String m_name;
    private static NetworkTable myTable;

    private String dashboardName(String key) {
        return m_name + key;
    }//end dashboardName

    public String getName() {
        return m_name;
    }
    
    public double getP() {
        return m_base.getP();
    }
    
    public PositionSendablePID(String name, PIDSource source, PIDOutput output, double distRatio) {
        m_base = new PositionPIDBase(distRatio, name);
        m_name = name;
        myTable = NetworkTable.getTable("SmartDashboard/" + m_name);
        PIDInit();
        m_source = source;
        m_output = output;
        
        
        Callable callable = new Callable() {
            Timer timer = new Timer();
            public void call() {
                if (timer.get() == 0.0){
                    timer.start();
                    return;
                }
                double input = m_source.pidGet();
                m_base.setInput(input);
                getValues();
                double result = m_base.calculate(timer.get());
                //The following do not go in the widget table b/c not displayed by widget
                SmartDashboard.putNumber(dashboardName("Input"), input);
                SmartDashboard.putNumber(dashboardName("Output"), result);
                SmartDashboard.putBoolean(dashboardName("Enabled"), m_base.isEnabled());
                timer.reset();
                if (m_base.isEnabled()) {
                    m_output.pidWrite(result);
                }
            }
        };
        m_thread = new TimedThread(callable);
        m_thread.setPeriod(50);//TODO: add to preferences and widget
        m_thread.start();
        
        SmartDashboard.putData(m_name, this);
    }//end SendablePID

    private void PIDInit() {
        

    }//end PIDInit

    public void writePreferences() {
        prefs.putString(m_name + "P", "" + m_base.getP());
        prefs.putString(m_name + "I", "" + m_base.getI());
        prefs.putString(m_name + "D", "" + m_base.getD());
        prefs.putString(m_name + "MaxOutput", "" + m_base.getMaxOutput());
        prefs.putString(m_name + "MinOutput", "" + m_base.getMinOutput());
        prefs.putString(m_name + "MaxVelocity", "" + m_base.getMaxVelocity());
        prefs.save();
    }
    
    public void getValues() {
        try {
            m_base.setKP(Double.parseDouble(myTable.getString("P")));
        } catch (java.lang.ClassCastException ex) {
            m_base.setKP(myTable.getNumber("P"));
        } catch (edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException ex) { //Catches if the table key isnt defined yet
            myTable.putString("P", prefs.getString(m_name + "P", "0.0")); //TODO: Load from Preferences instead of static variable
        }
        try {
            m_base.setKI(Double.parseDouble(myTable.getString("I")));
        } catch (java.lang.ClassCastException ex) {
            m_base.setKI(myTable.getNumber("I"));
        }  catch (edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException ex) {
            myTable.putString("I", prefs.getString(m_name + "I", "0.0"));
        }
        try {
            m_base.setKD(Double.parseDouble(myTable.getString("D")));
        } catch (java.lang.ClassCastException ex) {
            m_base.setKD(myTable.getNumber("D"));
        }  catch (edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException ex) {
            myTable.putString("D", prefs.getString(m_name + "D", "0.0"));
        }
        try {
            m_base.setMaxOutput(Double.parseDouble(myTable.getString("MinOutput")));
        } catch (java.lang.ClassCastException ex) {
            m_base.setMaxOutput(myTable.getNumber("MinOutput"));
        }  catch (edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException ex) {
            myTable.putString("MinOutput", prefs.getString(m_name + "MinOutput", "0.0"));
        }
        try {
            m_base.setMinOutput(Double.parseDouble(myTable.getString("MinOutput")));
        } catch (java.lang.ClassCastException ex) {
            m_base.setMinOutput(myTable.getNumber("MinOutput"));
        }  catch (edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException ex) {
            myTable.putString("MinOutput", prefs.getString(m_name + "MinOutput", "0.0"));
        }
        try { //Extra read for Max Velocity specific to position PID
            m_base.setMaxVeloc(Double.parseDouble(myTable.getString("MaxVelocity")));
        } catch (java.lang.ClassCastException ex) {
            m_base.setMaxVeloc(myTable.getNumber("MaxVelocity"));
        } catch (edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException ex) {
            myTable.putString("MaxVelocity", prefs.getString(m_name + "MaxVelocity", "0.0"));
        }
    }//end getValues
    
    public void setSetpoint(double setpoint) {
        m_base.setSetpoint(setpoint);
    }//end setSetpoint
    
    public void enable(){
        m_base.enable();
    }//end enable
    
    public void disable(){
        m_base.disable();
        m_output.pidWrite(0.0);
    }//end disable

    public void initTable(ITable itable) {
        //Nothing
    }

    public ITable getTable() {
        return myTable;
    }

    public String getSmartDashboardType() {
        return "Pidget";
    }
      
}//end SendablePID

