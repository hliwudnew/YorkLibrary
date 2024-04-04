package randoop.onlineitem;

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
        foo.User[] userArray0 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList1 = new java.util.ArrayList<foo.User>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList1, userArray0);
        foo.OnlineItem onlineItem4 = new foo.OnlineItem(userList1, "");
        java.lang.String str5 = onlineItem4.getName();
        foo.ItemStateContext itemStateContext6 = onlineItem4.getStatus();
        foo.User user7 = null;
        onlineItem4.removeSubscriber(user7);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(str5);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext6);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.User[] userArray0 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList1 = new java.util.ArrayList<foo.User>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList1, userArray0);
        foo.OnlineItem onlineItem4 = new foo.OnlineItem(userList1, "");
        onlineItem4.setPrice((double) '4');
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.User[] userArray0 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList1 = new java.util.ArrayList<foo.User>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList1, userArray0);
        foo.OnlineItem onlineItem4 = new foo.OnlineItem(userList1, "");
        foo.User[] userArray5 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList6 = new java.util.ArrayList<foo.User>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList6, userArray5);
        foo.OnlineItem onlineItem9 = new foo.OnlineItem(userList6, "");
        onlineItem4.setSubscribers(userList6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray5);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.User[] userArray0 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList1 = new java.util.ArrayList<foo.User>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList1, userArray0);
        foo.OnlineItem onlineItem4 = new foo.OnlineItem(userList1, "");
        java.lang.String str5 = onlineItem4.getName();
        foo.ItemStateContext itemStateContext6 = onlineItem4.getStatus();
        double double7 = onlineItem4.getPrice();
        foo.User user8 = null;
        onlineItem4.addSubscriber(user8);
        java.lang.String str10 = onlineItem4.getLink();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(str5);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemStateContext6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double7 + "' != '" + 0.0d + "'", double7 == 0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str10 + "' != '" + "" + "'", str10.equals(""));
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        foo.OnlineItem onlineItem0 = new foo.OnlineItem();
    }
}

