package randoop.nonfaculty;

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
        foo.Nonfaculty nonfaculty0 = new foo.Nonfaculty();
        foo.OnlineItem onlineItem1 = null;
        nonfaculty0.subscribe(onlineItem1);
        foo.OnlineItem[] onlineItemArray3 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList4 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList4, onlineItemArray3);
        nonfaculty0.setSubscriptions(onlineItemList4);
        java.lang.Class<?> wildcardClass7 = nonfaculty0.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray3);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass7);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.Nonfaculty nonfaculty0 = new foo.Nonfaculty();
        foo.OnlineItem onlineItem1 = null;
        nonfaculty0.subscribe(onlineItem1);
        foo.OnlineItem[] onlineItemArray3 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList4 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList4, onlineItemArray3);
        nonfaculty0.setSubscriptions(onlineItemList4);
        foo.PhysicalItem physicalItem7 = null;
        nonfaculty0.returnPhysicalItem(physicalItem7);
        foo.Menu menu9 = null;
        nonfaculty0.setMenu(menu9);
        java.lang.String str11 = nonfaculty0.getPassword();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray3);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(str11);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.Nonfaculty nonfaculty0 = new foo.Nonfaculty();
        foo.OnlineItem onlineItem1 = null;
        nonfaculty0.subscribe(onlineItem1);
        foo.OnlineItem[] onlineItemArray3 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList4 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList4, onlineItemArray3);
        nonfaculty0.setSubscriptions(onlineItemList4);
        foo.Menu menu7 = null;
        nonfaculty0.setMenu(menu7);
        nonfaculty0.setPassword("");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray3);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.Nonfaculty nonfaculty0 = new foo.Nonfaculty();
        foo.OnlineItem onlineItem1 = null;
        nonfaculty0.subscribe(onlineItem1);
        foo.OnlineItem[] onlineItemArray3 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList4 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList4, onlineItemArray3);
        nonfaculty0.setSubscriptions(onlineItemList4);
        foo.OnlineItem onlineItem7 = null;
        nonfaculty0.unSubscribe(onlineItem7);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray3);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }
}

