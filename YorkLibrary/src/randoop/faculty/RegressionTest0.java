package randoop.faculty;

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
        foo.Faculty faculty0 = new foo.Faculty();
        faculty0.setPassword("");
        java.util.ArrayList<foo.Item> itemList3 = faculty0.getTextBooks();
        foo.Course course4 = null;
        faculty0.addCourse(course4);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemList3);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.Faculty faculty0 = new foo.Faculty();
        faculty0.setEmail("");
        foo.Item item3 = null;
        faculty0.addTextBook(item3);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.Faculty faculty0 = new foo.Faculty();
        faculty0.setPassword("");
        java.util.ArrayList<foo.Item> itemList3 = faculty0.getTextBooks();
        foo.Course[] courseArray4 = new foo.Course[] {};
        java.util.ArrayList<foo.Course> courseList5 = new java.util.ArrayList<foo.Course>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<foo.Course>) courseList5, courseArray4);
        foo.Faculty faculty7 = new foo.Faculty(itemList3, courseList5);
        foo.PhysicalItem physicalItem8 = null;
        faculty7.rentPhysicalItem(physicalItem8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemList3);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(courseArray4);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.Faculty faculty0 = new foo.Faculty();
        faculty0.setPassword("");
        java.util.ArrayList<foo.OnlineItem> onlineItemList3 = faculty0.getSubscriptions();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemList3);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        foo.Faculty faculty0 = new foo.Faculty();
        faculty0.setPassword("");
        foo.OnlineItem onlineItem3 = null;
        faculty0.unSubscribe(onlineItem3);
    }
}

