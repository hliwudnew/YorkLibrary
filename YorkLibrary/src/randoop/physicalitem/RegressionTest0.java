package randoop.physicalitem;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test01");
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
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test02");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem((int) (short) 0, "", (double) (short) -1, itemStateContext3, date4, "", (double) 100.0f, (double) 1.0f);
        physicalItem8.setId((int) (short) 1);
        foo.ItemStateContext itemStateContext11 = null;
        // The following exception was thrown during execution in test generation
        try {
            physicalItem8.setStatus(itemStateContext11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.ItemStateContext.getState()\" because \"status\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test03");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        // The following exception was thrown during execution in test generation
        try {
            foo.PhysicalItem physicalItem4 = physicalItem3.cloneItem();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read field \"value\" because \"original\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test04");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setFee((double) (byte) 10);
        // The following exception was thrown during execution in test generation
        try {
            int int11 = physicalItem8.daysOverdue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.Date.getTime()\" because the return value of \"foo.PhysicalItem.getDueDate()\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test05");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setDiscount((double) (-1L));
        java.lang.String str11 = physicalItem8.getName();
        double double12 = physicalItem8.getPrice();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "hi!" + "'", str11.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double12 + "' != '" + (-1.0d) + "'", double12 == (-1.0d));
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test06");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        // The following exception was thrown during execution in test generation
        try {
            int int4 = physicalItem3.daysOverdue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.Date.getTime()\" because the return value of \"foo.PhysicalItem.getDueDate()\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test07");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem((int) (short) 0, "", (double) (short) -1, itemStateContext3, date4, "", (double) 100.0f, (double) 1.0f);
        physicalItem8.setId((int) (short) 1);
        foo.ItemStateContext itemStateContext11 = physicalItem8.getStatus();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(itemStateContext11);
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test08");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "hi!", (double) (byte) 0);
        int int4 = physicalItem3.getId();
        // The following exception was thrown during execution in test generation
        try {
            foo.PhysicalItem physicalItem5 = physicalItem3.cloneItem();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read field \"value\" because \"original\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test09");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem((int) (short) 0, "", (double) (short) -1, itemStateContext3, date4, "", (double) 100.0f, (double) 1.0f);
        java.lang.String str9 = physicalItem8.getName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "" + "'", str9.equals(""));
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test10");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setLost(true);
        foo.ItemStateContext itemStateContext6 = physicalItem3.getStatus();
        java.lang.String str7 = physicalItem3.getName();
        physicalItem3.setLost(true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test11");
        int int0 = foo.PhysicalItem.getNextValidId();
        // Regression assertion (captures the current behavior of the code)
// flaky:         org.junit.Assert.assertTrue("'" + int0 + "' != '" + 34 + "'", int0 == 34);
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test12");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "hi!", (double) (byte) 0);
        int int4 = physicalItem3.getId();
        double double5 = physicalItem3.getDiscount();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 0.0d + "'", double5 == 0.0d);
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test13");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        java.util.Date date9 = null;
        physicalItem8.setDueDate(date9);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = physicalItem8.getDueStatus();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.Date.getTime()\" because \"this.dueDate\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test14");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setFee((double) 0);
        java.lang.String str11 = physicalItem8.getName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "hi!" + "'", str11.equals("hi!"));
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test15");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        java.util.Date date9 = null;
        physicalItem8.setDueDate(date9);
        physicalItem8.setId(1);
    }

    @Test
    public void test16() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test16");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setLost(true);
        double double6 = physicalItem3.getFee();
        java.util.Date date7 = null;
        physicalItem3.setDueDate(date7);
        double double9 = physicalItem3.getFee();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 1.0d + "'", double6 == 1.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 1.0d + "'", double9 == 1.0d);
    }

    @Test
    public void test17() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test17");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setName("");
        java.lang.String str6 = physicalItem3.getName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str6 + "' != '" + "" + "'", str6.equals(""));
    }

    @Test
    public void test18() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test18");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setDiscount((double) (-1L));
        java.lang.String str11 = physicalItem8.getName();
        physicalItem8.setBorrower("hi!");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "hi!" + "'", str11.equals("hi!"));
    }

    @Test
    public void test19() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test19");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setName("");
        physicalItem3.setName("hi!");
        java.lang.String str8 = physicalItem3.getName();
        boolean boolean9 = physicalItem3.isLost();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "hi!" + "'", str8.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test20() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test20");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "hi!", (double) 100.0f);
        int int4 = physicalItem3.getId();
        java.util.Date date5 = physicalItem3.getDueDate();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(date5);
    }

    @Test
    public void test21() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test21");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setLost(true);
        double double6 = physicalItem3.getFee();
        java.util.Date date7 = null;
        physicalItem3.setDueDate(date7);
        physicalItem3.setLost(true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 1.0d + "'", double6 == 1.0d);
    }

    @Test
    public void test22() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test22");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        java.util.Date date9 = null;
        physicalItem8.setDueDate(date9);
        java.lang.Class<?> wildcardClass11 = physicalItem8.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass11);
    }

    @Test
    public void test23() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test23");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setDiscount((double) (-1L));
        physicalItem8.setFee((double) 10L);
        boolean boolean13 = physicalItem8.isLost();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test24() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test24");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "hi!", (double) 100.0f);
        int int4 = physicalItem3.getId();
        java.util.Date date5 = null;
        physicalItem3.setDueDate(date5);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test25() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test25");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "hi!", (double) 100.0f);
        physicalItem3.setLost(true);
        // The following exception was thrown during execution in test generation
        try {
            double double6 = physicalItem3.calculateFee();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.Date.getTime()\" because the return value of \"foo.PhysicalItem.getDueDate()\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test26() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test26");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setDiscount((double) (-1L));
        java.lang.String str11 = physicalItem8.getName();
        java.util.Date date12 = null;
        physicalItem8.setDueDate(date12);
        java.util.Date date14 = null;
        physicalItem8.setDueDate(date14);
        java.util.Date date16 = null;
        foo.PhysicalItem physicalItem19 = new foo.PhysicalItem(date16, "", (double) 1.0f);
        physicalItem19.setLost(true);
        foo.ItemStateContext itemStateContext22 = physicalItem19.getStatus();
        foo.ItemStateContext itemStateContext23 = physicalItem19.getStatus();
        // The following exception was thrown during execution in test generation
        try {
            physicalItem8.setStatus(itemStateContext23);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.ItemStateContext.setState(foo.ItemState)\" because \"this.status\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "hi!" + "'", str11.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext22);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext23);
    }

    @Test
    public void test27() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test27");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setName("");
        physicalItem3.setName("hi!");
        physicalItem3.setDiscount((double) (-1L));
    }

    @Test
    public void test28() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test28");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setFee((double) 0);
        physicalItem8.setBorrower("hi!");
        // The following exception was thrown during execution in test generation
        try {
            foo.PhysicalItem physicalItem13 = physicalItem8.cloneItem();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.ItemStateContext.getState()\" because the return value of \"foo.PhysicalItem.getStatus()\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test29() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test29");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setLost(true);
        foo.ItemStateContext itemStateContext6 = physicalItem3.getStatus();
        double double7 = physicalItem3.getPrice();
        int int8 = physicalItem3.getId();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double7 + "' != '" + 0.0d + "'", double7 == 0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test30() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test30");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setFee((double) (byte) 10);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = physicalItem8.getDueStatus();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.Date.getTime()\" because \"this.dueDate\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test31() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test31");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setLost(true);
        // The following exception was thrown during execution in test generation
        try {
            int int6 = physicalItem3.daysOverdue();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.Date.getTime()\" because the return value of \"foo.PhysicalItem.getDueDate()\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test32() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test32");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "hi!", (double) (byte) 0);
        int int4 = physicalItem3.getId();
        java.util.Date date5 = physicalItem3.getDueDate();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(date5);
    }

    @Test
    public void test33() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test33");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "hi!", (double) 100.0f);
        int int4 = physicalItem3.getId();
        // The following exception was thrown during execution in test generation
        try {
            double double5 = physicalItem3.calculateFee();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.Date.getTime()\" because the return value of \"foo.PhysicalItem.getDueDate()\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test34() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test34");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setFee((double) (byte) 10);
        physicalItem8.setLost(false);
        // The following exception was thrown during execution in test generation
        try {
            double double13 = physicalItem8.calculateFee();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.Date.getTime()\" because the return value of \"foo.PhysicalItem.getDueDate()\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test35() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test35");
        foo.PhysicalItem physicalItem0 = new foo.PhysicalItem();
        double double1 = physicalItem0.getFee();
        java.lang.Class<?> wildcardClass2 = physicalItem0.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double1 + "' != '" + 0.0d + "'", double1 == 0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test36() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test36");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setLost(true);
        double double6 = physicalItem3.getDiscount();
        java.lang.String str7 = physicalItem3.getName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 0.0d + "'", double6 == 0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test37() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test37");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setLost(true);
        foo.ItemStateContext itemStateContext6 = physicalItem3.getStatus();
        foo.ItemStateContext itemStateContext7 = physicalItem3.getStatus();
        double double8 = physicalItem3.getFee();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext7);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double8 + "' != '" + 1.0d + "'", double8 == 1.0d);
    }

    @Test
    public void test38() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test38");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setFee((double) 0);
        physicalItem8.setBorrower("hi!");
        physicalItem8.setDiscount(0.0d);
        physicalItem8.setLost(false);
    }

    @Test
    public void test39() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test39");
        java.util.Date date6 = null;
        foo.PhysicalItem physicalItem9 = new foo.PhysicalItem(date6, "", (double) 1.0f);
        physicalItem9.setLost(true);
        foo.ItemStateContext itemStateContext12 = physicalItem9.getStatus();
        java.util.Date date13 = null;
        foo.PhysicalItem physicalItem17 = new foo.PhysicalItem(10, "", (double) '#', itemStateContext12, date13, "hi!", (double) (byte) 100, (double) (byte) -1);
        java.util.Date date18 = null;
        foo.PhysicalItem physicalItem22 = new foo.PhysicalItem((int) (byte) 1, "", (double) 10.0f, itemStateContext12, date18, "", (double) 1.0f, (double) 1.0f);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext12);
    }

    @Test
    public void test40() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test40");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setLost(true);
        double double6 = physicalItem3.getFee();
        physicalItem3.setId((int) (byte) -1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 1.0d + "'", double6 == 1.0d);
    }

    @Test
    public void test41() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test41");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setDiscount((double) (-1L));
        java.lang.String str11 = physicalItem8.getName();
        physicalItem8.setLost(true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "hi!" + "'", str11.equals("hi!"));
    }

    @Test
    public void test42() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test42");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setLost(true);
        foo.ItemStateContext itemStateContext6 = physicalItem3.getStatus();
        physicalItem3.setDiscount((double) (-1L));
        java.util.Date date9 = physicalItem3.getDueDate();
        physicalItem3.setBorrower("hi!");
        double double12 = physicalItem3.getFee();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(date9);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double12 + "' != '" + 1.0d + "'", double12 == 1.0d);
    }

    @Test
    public void test43() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test43");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "hi!", (double) 100.0f);
        int int4 = physicalItem3.getId();
        java.lang.Class<?> wildcardClass5 = physicalItem3.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test44() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test44");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setFee((double) (byte) 10);
        physicalItem8.setLost(false);
        physicalItem8.setFee(0.0d);
    }

    @Test
    public void test45() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test45");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setName("");
        int int6 = physicalItem3.getId();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
    }

    @Test
    public void test46() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test46");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setFee((double) (byte) 10);
        physicalItem8.setLost(false);
        int int13 = physicalItem8.getId();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
    }

    @Test
    public void test47() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test47");
        foo.PhysicalItem physicalItem0 = new foo.PhysicalItem();
        foo.ItemStateContext itemStateContext1 = physicalItem0.getStatus();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext1);
    }

    @Test
    public void test48() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test48");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setLost(true);
        double double6 = physicalItem3.getFee();
        java.util.Date date7 = null;
        physicalItem3.setDueDate(date7);
        java.lang.Class<?> wildcardClass9 = physicalItem3.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 1.0d + "'", double6 == 1.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass9);
    }

    @Test
    public void test49() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test49");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setDiscount((double) (-1L));
        java.lang.String str11 = physicalItem8.getName();
        java.util.Date date12 = null;
        physicalItem8.setDueDate(date12);
        physicalItem8.setDiscount((double) (byte) 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "hi!" + "'", str11.equals("hi!"));
    }

    @Test
    public void test50() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test50");
        java.util.Date date3 = null;
        foo.PhysicalItem physicalItem6 = new foo.PhysicalItem(date3, "", (double) 1.0f);
        physicalItem6.setLost(true);
        foo.ItemStateContext itemStateContext9 = physicalItem6.getStatus();
        java.util.Date date10 = null;
        foo.PhysicalItem physicalItem14 = new foo.PhysicalItem(100, "", (double) (byte) 10, itemStateContext9, date10, "hi!", (double) (byte) 1, (double) 34);
        physicalItem14.setName("hi!");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext9);
    }

    @Test
    public void test51() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test51");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem((int) (short) 0, "", (double) (short) -1, itemStateContext3, date4, "", (double) 100.0f, (double) 1.0f);
        physicalItem8.setId((int) (short) 1);
        java.util.Date date11 = physicalItem8.getDueDate();
        java.lang.String str12 = physicalItem8.getName();
        foo.ItemStateContext itemStateContext13 = physicalItem8.getStatus();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(date11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str12 + "' != '" + "" + "'", str12.equals(""));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(itemStateContext13);
    }

    @Test
    public void test52() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test52");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "", (double) 1.0f);
        physicalItem3.setLost(true);
        foo.ItemStateContext itemStateContext6 = physicalItem3.getStatus();
        double double7 = physicalItem3.getPrice();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = physicalItem3.getDueStatus();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.Date.getTime()\" because \"this.dueDate\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double7 + "' != '" + 0.0d + "'", double7 == 0.0d);
    }

    @Test
    public void test53() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test53");
        java.util.Date date6 = null;
        foo.PhysicalItem physicalItem9 = new foo.PhysicalItem(date6, "", (double) 1.0f);
        physicalItem9.setLost(true);
        foo.ItemStateContext itemStateContext12 = physicalItem9.getStatus();
        java.util.Date date13 = null;
        foo.PhysicalItem physicalItem17 = new foo.PhysicalItem((int) '4', "", 0.0d, itemStateContext12, date13, "hi!", (double) 0.0f, 1.0d);
        java.util.Date date18 = null;
        foo.PhysicalItem physicalItem22 = new foo.PhysicalItem((int) (byte) 0, "", (double) '#', itemStateContext12, date18, "", (double) 10.0f, (-1.0d));
        physicalItem22.setId(0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext12);
    }

    @Test
    public void test54() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test54");
        java.util.Date date0 = null;
        foo.PhysicalItem physicalItem3 = new foo.PhysicalItem(date0, "hi!", (double) 100.0f);
        int int4 = physicalItem3.getId();
        int int5 = physicalItem3.getId();
        physicalItem3.setFee((double) (short) 100);
        physicalItem3.setFee((double) (-1L));
        physicalItem3.setName("");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
    }

    @Test
    public void test55() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test55");
        foo.ItemStateContext itemStateContext3 = null;
        java.util.Date date4 = null;
        foo.PhysicalItem physicalItem8 = new foo.PhysicalItem(0, "hi!", (double) (byte) -1, itemStateContext3, date4, "", (double) (byte) 10, (double) '#');
        physicalItem8.setFee((double) 0);
        physicalItem8.setName("");
    }
}
