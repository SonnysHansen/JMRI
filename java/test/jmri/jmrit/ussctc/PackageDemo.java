package jmri.jmrit.ussctc;

import jmri.*;
import jmri.util.*;
import org.junit.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Demo of classes in jmri.jmrit.ussctc
 *
 * @author	Bob Jacobsen Copyright 2003, 2007, 2015, 2017
  */
public class PackageDemo {

    public PackageDemo(String s) {
    }

    // Main entry point
    static public void main(String[] args) {
        apps.tests.Log4JFixture.setUp();
        JUnitUtil.resetInstanceManager();
        JUnitUtil.initConfigureManager();
        JUnitUtil.initInternalTurnoutManager();
        JUnitUtil.initInternalLightManager();
        JUnitUtil.initInternalSensorManager();
        JUnitUtil.initMemoryManager();
        JUnitUtil.initShutDownManager();
        JUnitUtil.resetProfileManager();

        // load file that defines various NamedBeans and pops a demo panel
        try {
            InstanceManager.getDefault(ConfigureManager.class)
                    .load(new java.io.File("java/test/jmri/jmrit/ussctc/PackageDemo.xml"));
            InstanceManager.getDefault(LogixManager.class).activateAllLogixs();
        } catch (Exception e) { System.err.println(e); }
        
        // create and wire USS CTC objects
        CodeLine line = new CodeLine("Code Sequencer Start", "IT101", "IT102", "IT103", "IT104");
        CodeButton button = new CodeButton("Sec1 Code", "Sec1 Code");
        TurnoutSection turnout = new TurnoutSection("Sec 1 Layout TO", "Sec1 TO 1 N", "Sec1 TO 1 R", "Sec1 TO 1 N", "Sec1 TO 1 R");
        Column col = new Column("1", line, button, turnout);
        button.setColumn(col);
        
        // user interacts here
        
        // wait for Swing to end
        Thread.getAllStackTraces().keySet().forEach((t) -> 
            { 
                if (t.getName().startsWith("AWT-EventQueue")) {  
                    try {
                        t.join();  // Wait for AWT to end on last window deleted
                    } catch (Exception e) {}
                }
            });
    }

}
