package randoop.physicalitem;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setFee((double) (byte) 10);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        foo.ItemStateContext itemStateContext9 = null;
        // The following exception was thrown during execution in test generation
        try {
            physicalItem8.setStatus(itemStateContext9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.ItemStateContext.getState()\" because \"status\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setDiscount((double) (-1L));
        java.lang.String str11 = physicalItem8.getName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "hi!" + "'", str11.equals("hi!"));
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem((int) (short) 0, "", (double) (short) -1, itemStateContext3, date4, "", (double) 100.0f, (double) 1.0f);
        physicalItem8.setId((int) (short) 1);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setName("");
        physicalItem3.setName("hi!");
    }
}

