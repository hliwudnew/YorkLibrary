package randoop.giftcardstrategy;

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
        foo.GiftCardStrategy giftCardStrategy1 = new foo.GiftCardStrategy("");
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean3 = giftCardStrategy1.pay(0.0d);
            org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: begin 0, end 3, length 0");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
        // Expected exception.
        }
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.GiftCardStrategy giftCardStrategy1 = new foo.GiftCardStrategy("");
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean3 = giftCardStrategy1.pay((double) 0.0f);
            org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: begin 0, end 3, length 0");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
        // Expected exception.
        }
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.GiftCardStrategy giftCardStrategy1 = new foo.GiftCardStrategy("");
        java.lang.Class<?> wildcardClass2 = giftCardStrategy1.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        foo.GiftCardStrategy giftCardStrategy1 = new foo.GiftCardStrategy("hi!");
        java.lang.Class<?> wildcardClass2 = giftCardStrategy1.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        foo.GiftCardStrategy giftCardStrategy1 = new foo.GiftCardStrategy("hi!");
        boolean boolean3 = giftCardStrategy1.pay((double) 100.0f);
        boolean boolean5 = giftCardStrategy1.pay((double) (byte) -1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test7() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test7");
        foo.GiftCardStrategy giftCardStrategy1 = new foo.GiftCardStrategy("hi!");
        boolean boolean3 = giftCardStrategy1.pay((double) (short) 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
    }
}

