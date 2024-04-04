package randoop.visitor;

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
        foo.Visitor visitor0 = new foo.Visitor();
        foo.Menu menu1 = visitor0.getMenu();
        foo.OnlineItem[] onlineItemArray2 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList3 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList3, onlineItemArray2);
        visitor0.setSubscriptions(onlineItemList3);
        foo.PhysicalItem physicalItem6 = null;
        visitor0.returnPhysicalItem(physicalItem6);
        foo.Visitor visitor8 = new foo.Visitor();
        foo.Menu menu9 = visitor8.getMenu();
        visitor0.setMenu(menu9);
        foo.Menu menu11 = visitor0.getMenu();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(menu1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray2);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(menu9);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(menu11);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.Visitor visitor0 = new foo.Visitor();
        foo.Menu menu1 = visitor0.getMenu();
        foo.OnlineItem[] onlineItemArray2 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList3 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList3, onlineItemArray2);
        visitor0.setSubscriptions(onlineItemList3);
        foo.PhysicalItem physicalItem6 = null;
        visitor0.returnPhysicalItem(physicalItem6);
        foo.Visitor visitor8 = new foo.Visitor();
        foo.Menu menu9 = visitor8.getMenu();
        foo.OnlineItem[] onlineItemArray10 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList11 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList11, onlineItemArray10);
        visitor8.setSubscriptions(onlineItemList11);
        visitor0.setSubscriptions(onlineItemList11);
        int int15 = visitor0.numberOverdue();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(menu1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray2);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(menu9);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray10);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.Visitor visitor0 = new foo.Visitor();
        foo.Cart cart1 = null;
        visitor0.setCart(cart1);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.Visitor visitor0 = new foo.Visitor();
        foo.Menu menu1 = visitor0.getMenu();
        foo.OnlineItem[] onlineItemArray2 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList3 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList3, onlineItemArray2);
        visitor0.setSubscriptions(onlineItemList3);
        foo.Menu menu6 = visitor0.getMenu();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(menu1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray2);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(menu6);
    }
}

