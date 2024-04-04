package randoop.itemstatecontext;

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
        foo.ItemState itemState0 = null;
        foo.ItemStateContext itemStateContext1 = new foo.ItemStateContext(itemState0);
        java.lang.Class<?> wildcardClass2 = itemStateContext1.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.ItemState itemState0 = null;
        foo.ItemStateContext itemStateContext1 = new foo.ItemStateContext(itemState0);
        foo.ItemState itemState2 = null;
        itemStateContext1.setState(itemState2);
        java.lang.Class<?> wildcardClass4 = itemStateContext1.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass4);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.ItemState itemState0 = null;
        foo.ItemStateContext itemStateContext1 = new foo.ItemStateContext(itemState0);
        foo.ItemState itemState2 = null;
        itemStateContext1.setState(itemState2);
        // The following exception was thrown during execution in test generation
        try {
            itemStateContext1.status();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.ItemState.status(foo.ItemStateContext)\" because \"this.curState\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.ItemStateContext itemStateContext0 = new foo.ItemStateContext();
        java.lang.Class<?> wildcardClass1 = itemStateContext0.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        foo.ItemState itemState0 = null;
        foo.ItemStateContext itemStateContext1 = new foo.ItemStateContext(itemState0);
        foo.ItemState itemState2 = null;
        itemStateContext1.setState(itemState2);
        foo.ItemState itemState4 = itemStateContext1.getState();
        foo.ItemState itemState5 = null;
        itemStateContext1.setState(itemState5);
        foo.ItemState itemState7 = null;
        itemStateContext1.setState(itemState7);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(itemState4);
    }
}

