package randoop.add;

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
        foo.Cart cart0 = null;
        foo.Add add1 = new foo.Add(cart0);
        java.lang.Class<?> wildcardClass2 = add1.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.Cart cart0 = null;
        foo.Add add1 = new foo.Add(cart0);
        foo.Item item2 = null;
        // The following exception was thrown during execution in test generation
        try {
            add1.execute(item2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.Cart.add(foo.Item)\" because \"this.cart\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }
}

