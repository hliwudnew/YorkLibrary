package randoop.currencyexchange;

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
        foo.Payment payment0 = null;
        foo.CurrencyExchange currencyExchange1 = new foo.CurrencyExchange(payment0);
        // The following exception was thrown during execution in test generation
        try {
            double double3 = currencyExchange1.getPrice("");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.Payment.getConvertedPrice(java.lang.Double)\" because \"this.payment\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.Payment payment0 = null;
        foo.CurrencyExchange currencyExchange1 = new foo.CurrencyExchange(payment0);
        java.lang.Class<?> wildcardClass2 = currencyExchange1.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.Payment payment0 = null;
        foo.CurrencyExchange currencyExchange1 = new foo.CurrencyExchange(payment0);
        // The following exception was thrown during execution in test generation
        try {
            double double3 = currencyExchange1.getPrice("hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.Payment.getConvertedPrice(java.lang.Double)\" because \"this.payment\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        java.util.ArrayList<java.lang.String> strList0 = foo.CurrencyExchange.getCurrencyList();
        java.lang.Class<?> wildcardClass1 = strList0.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList0);
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
}

