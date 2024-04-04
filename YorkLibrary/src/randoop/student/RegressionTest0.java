package randoop.student;

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
        foo.Student student0 = new foo.Student();
        student0.setPassword("");
        student0.unSubscribeById(1);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.Student student0 = new foo.Student();
        java.util.ArrayList<foo.PhysicalItem> physicalItemList1 = null;
        student0.setRented(physicalItemList1);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.Student student0 = new foo.Student();
        student0.setPassword("");
        foo.PhysicalItem physicalItem3 = null;
        student0.returnPhysicalItem(physicalItem3);
        int int5 = student0.numberOverdue();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.Course[] courseArray0 = new foo.Course[] {};
        java.util.ArrayList<foo.Course> courseList1 = new java.util.ArrayList<foo.Course>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.Course>) courseList1, courseArray0);
        foo.Student student3 = new foo.Student(courseList1);
        java.lang.String str4 = student3.getEmail();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(courseArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(str4);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        foo.Student student0 = new foo.Student();
        java.util.ArrayList<foo.OnlineItem> onlineItemList1 = student0.getSubscriptions();
        java.util.ArrayList<foo.Course> courseList2 = student0.getCourses();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemList1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(courseList2);
    }
}

