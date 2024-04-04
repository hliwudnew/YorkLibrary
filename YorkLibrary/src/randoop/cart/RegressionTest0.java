package randoop.cart;

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
        foo.Item[] itemArray0 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList1 = new java.util.ArrayList<foo.Item>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList1, itemArray0);
        foo.User user3 = null;
        foo.Cart cart4 = new foo.Cart(itemList1, user3);
        java.lang.Class<?> wildcardClass5 = itemList1.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.Item[] itemArray0 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList1 = new java.util.ArrayList<foo.Item>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList1, itemArray0);
        foo.User user3 = null;
        foo.Cart cart4 = new foo.Cart(itemList1, user3);
        int int5 = cart4.canCheckout();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + (-2) + "'", int5 == (-2));
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.Item[] itemArray0 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList1 = new java.util.ArrayList<foo.Item>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList1, itemArray0);
        foo.User user3 = null;
        foo.Cart cart4 = new foo.Cart(itemList1, user3);
        double double5 = cart4.getInitialPrice();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 0.0d + "'", double5 == 0.0d);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.Item[] itemArray0 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList1 = new java.util.ArrayList<foo.Item>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList1, itemArray0);
        foo.User user3 = null;
        foo.Cart cart4 = new foo.Cart(itemList1, user3);
        cart4.setCurrency("");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        foo.Item[] itemArray0 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList1 = new java.util.ArrayList<foo.Item>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList1, itemArray0);
        foo.User user3 = null;
        foo.Cart cart4 = new foo.Cart(itemList1, user3);
        double double5 = cart4.getConvertedPrice();
        cart4.setCurrency("");
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean8 = cart4.checkout();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.lang.Double.doubleValue()\" because \"rate\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 0.0d + "'", double5 == 0.0d);
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        foo.Item[] itemArray0 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList1 = new java.util.ArrayList<foo.Item>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList1, itemArray0);
        foo.User user3 = null;
        foo.Cart cart4 = new foo.Cart(itemList1, user3);
        double double5 = cart4.getConvertedPrice();
        int int6 = cart4.canCheckout();
        foo.Item item7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cart4.add(item7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.Item.getPrice()\" because \"item\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 0.0d + "'", double5 == 0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + (-2) + "'", int6 == (-2));
    }
}

