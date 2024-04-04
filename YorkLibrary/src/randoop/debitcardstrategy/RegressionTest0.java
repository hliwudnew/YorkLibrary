package randoop.debitcardstrategy;

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
        foo.DebitCardStrategy debitCardStrategy4 = new foo.DebitCardStrategy("", "", "hi!", "");
        java.lang.Class<?> wildcardClass5 = debitCardStrategy4.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.DebitCardStrategy debitCardStrategy4 = new foo.DebitCardStrategy("hi!", "", "hi!", "");
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.DebitCardStrategy debitCardStrategy4 = new foo.DebitCardStrategy("", "hi!", "", "hi!");
        java.lang.Class<?> wildcardClass5 = debitCardStrategy4.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        foo.DebitCardStrategy debitCardStrategy4 = new foo.DebitCardStrategy("", "", "", "hi!");
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        foo.DebitCardStrategy debitCardStrategy4 = new foo.DebitCardStrategy("", "hi!", "", "hi!");
        boolean boolean6 = debitCardStrategy4.pay(0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test7() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test7");
        foo.DebitCardStrategy debitCardStrategy4 = new foo.DebitCardStrategy("", "", "hi!", "");
        boolean boolean6 = debitCardStrategy4.pay((double) (short) 100);
        boolean boolean8 = debitCardStrategy4.pay((double) 10.0f);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }
}

