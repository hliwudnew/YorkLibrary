package randoop.menu;

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
        java.lang.Object obj0 = new java.lang.Object();
        java.lang.Class<?> wildcardClass1 = obj0.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.ICartCommand1 iCartCommand1_0 = null;
        foo.ICartCommand1 iCartCommand1_1 = null;
        foo.ICartCommand2 iCartCommand2_2 = null;
        foo.ICartCommand2 iCartCommand2_3 = null;
        foo.Menu menu4 = new foo.Menu(iCartCommand1_0, iCartCommand1_1, iCartCommand2_2, iCartCommand2_3);
        java.lang.Class<?> wildcardClass5 = menu4.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.ICartCommand1 iCartCommand1_0 = null;
        foo.ICartCommand1 iCartCommand1_1 = null;
        foo.ICartCommand2 iCartCommand2_2 = null;
        foo.ICartCommand2 iCartCommand2_3 = null;
        foo.Menu menu4 = new foo.Menu(iCartCommand1_0, iCartCommand1_1, iCartCommand2_2, iCartCommand2_3);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean5 = menu4.clickCheckout();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.ICartCommand2.execute()\" because \"this.checkoutCommand\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.ICartCommand1 iCartCommand1_0 = null;
        foo.ICartCommand1 iCartCommand1_1 = null;
        foo.ICartCommand2 iCartCommand2_2 = null;
        foo.ICartCommand2 iCartCommand2_3 = null;
        foo.Menu menu4 = new foo.Menu(iCartCommand1_0, iCartCommand1_1, iCartCommand2_2, iCartCommand2_3);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean5 = menu4.clickClear();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.ICartCommand2.execute()\" because \"this.clearCommand\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        foo.ICartCommand1 iCartCommand1_0 = null;
        foo.ICartCommand1 iCartCommand1_1 = null;
        foo.ICartCommand2 iCartCommand2_2 = null;
        foo.ICartCommand2 iCartCommand2_3 = null;
        foo.Menu menu4 = new foo.Menu(iCartCommand1_0, iCartCommand1_1, iCartCommand2_2, iCartCommand2_3);
        foo.Item item5 = null;
        // The following exception was thrown during execution in test generation
        try {
            menu4.clickRemove(item5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.ICartCommand1.execute(foo.Item)\" because \"this.removeCommand\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        foo.ICartCommand1 iCartCommand1_0 = null;
        foo.ICartCommand1 iCartCommand1_1 = null;
        foo.ICartCommand2 iCartCommand2_2 = null;
        foo.ICartCommand2 iCartCommand2_3 = null;
        foo.Menu menu4 = new foo.Menu(iCartCommand1_0, iCartCommand1_1, iCartCommand2_2, iCartCommand2_3);
        foo.Item item5 = null;
        // The following exception was thrown during execution in test generation
        try {
            menu4.clickAdd(item5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.ICartCommand1.execute(foo.Item)\" because \"this.addCommand\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }
}

