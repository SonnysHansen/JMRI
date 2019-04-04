package jmri.jmrit.beantable;

import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import jmri.util.JUnitUtil;
import org.junit.*;
import org.netbeans.jemmy.operators.JFrameOperator;

/**
 *
 * @author Paul Bender Copyright (C) 2017	
 */
public class ReporterTableActionTest extends AbstractTableActionBase {

    @Test
    public void testCTor() {
        Assert.assertNotNull("exists",a);
    }

    @Override
    public String getTableFrameName(){
       return Bundle.getMessage("TitleReporterTable");
    }

    @Override
    @Test
    public void testGetClassDescription(){
         Assert.assertEquals("Reporter Table Action class description","Reporter Table",a.getClassDescription());
    }

    /**
     * Check the return value of includeAddButton.  The table generated by 
     * this action includes an Add Button.
     */
    @Override
    @Test
    public void testIncludeAddButton(){
         Assert.assertTrue("Default include add button",a.includeAddButton());
    }

    @Test
    @Override
    public void testAddButton() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Assume.assumeTrue(a.includeAddButton());
        a.actionPerformed(null);
        JFrame f = JFrameOperator.waitJFrame(getTableFrameName(), true, true);

        // find the "Add... " button and press it.
	jmri.util.swing.JemmyUtil.pressButton(new JFrameOperator(f),Bundle.getMessage("ButtonAdd"));
        JFrame f1 = JFrameOperator.waitJFrame(getAddFrameName(), true, true);
	jmri.util.swing.JemmyUtil.pressButton(new JFrameOperator(f1),Bundle.getMessage("ButtonClose")); // not sure why this is close in this frame.
        JUnitUtil.dispose(f1);
        JUnitUtil.dispose(f);
    }

    @Override
    public String getAddFrameName(){
        return Bundle.getMessage("TitleAddReporter");
    }

    @Test
    @Override
    @Ignore("No Edit button on Reporter table")
    public void testEditButton() {
    }

    // The minimal setup for log4J
    @Override
    @Before
    public void setUp() {
        JUnitUtil.setUp();
        jmri.util.JUnitUtil.resetProfileManager();
        helpTarget = "package.jmri.jmrit.beantable.ReporterTable"; 
        a = new ReporterTableAction();
    }

    @Override
    @After
    public void tearDown() {
        JUnitUtil.tearDown();
    }

    // private final static Logger log = LoggerFactory.getLogger(ReporterTableActionTest.class);

}
