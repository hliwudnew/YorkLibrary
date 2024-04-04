package randoop.paymentcontext;

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
        foo.PaymentStrategy paymentStrategy0 = null;
        foo.PaymentContext paymentContext1 = new foo.PaymentContext(paymentStrategy0);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean3 = paymentContext1.pay((double) (byte) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.PaymentStrategy.pay(double)\" because \"this.payStrat\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.PaymentStrategy paymentStrategy0 = null;
        foo.PaymentContext paymentContext1 = new foo.PaymentContext(paymentStrategy0);
        java.lang.Class<?> wildcardClass2 = paymentContext1.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.PaymentStrategy paymentStrategy0 = null;
        foo.PaymentContext paymentContext1 = new foo.PaymentContext(paymentStrategy0);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean3 = paymentContext1.pay((-1.0d));
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.PaymentStrategy.pay(double)\" because \"this.payStrat\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        java.lang.String[] strArray0 = foo.PaymentContext.getPaymentMethods();
        java.lang.Class<?> wildcardClass1 = strArray0.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        java.lang.Object obj0 = new java.lang.Object();
        java.lang.Class<?> wildcardClass1 = obj0.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        foo.PaymentStrategy paymentStrategy0 = null;
        foo.PaymentContext paymentContext1 = new foo.PaymentContext(paymentStrategy0);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean3 = paymentContext1.pay((double) 100.0f);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.PaymentStrategy.pay(double)\" because \"this.payStrat\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test7() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test7");
        foo.PaymentStrategy paymentStrategy0 = null;
        foo.PaymentContext paymentContext1 = new foo.PaymentContext(paymentStrategy0);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean3 = paymentContext1.pay((double) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.PaymentStrategy.pay(double)\" because \"this.payStrat\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test8() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test8");
        foo.PaymentStrategy paymentStrategy0 = null;
        foo.PaymentContext paymentContext1 = new foo.PaymentContext(paymentStrategy0);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean3 = paymentContext1.pay(0.0d);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.PaymentStrategy.pay(double)\" because \"this.payStrat\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }
}

