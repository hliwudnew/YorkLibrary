package randoop.course;

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
        foo.PhysicalItem[] physicalItemArray2 = new foo.PhysicalItem[] {};
        java.util.ArrayList<foo.PhysicalItem> physicalItemList3 = new java.util.ArrayList<foo.PhysicalItem>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<foo.PhysicalItem>) physicalItemList3, physicalItemArray2);
        foo.Student[] studentArray5 = new foo.Student[] {};
        java.util.ArrayList<foo.Student> studentList6 = new java.util.ArrayList<foo.Student>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<foo.Student>) studentList6, studentArray5);
        foo.Faculty[] facultyArray8 = new foo.Faculty[] {};
        java.util.ArrayList<foo.Faculty> facultyList9 = new java.util.ArrayList<foo.Faculty>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<foo.Faculty>) facultyList9, facultyArray8);
        foo.Course course11 = new foo.Course("hi!", "hi!", physicalItemList3, studentList6, facultyList9);
        foo.Student student12 = null;
        course11.addStudent(student12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(physicalItemArray2);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(studentArray5);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(facultyArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.Course course0 = new foo.Course();
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.PhysicalItem[] physicalItemArray2 = new foo.PhysicalItem[] {};
        java.util.ArrayList<foo.PhysicalItem> physicalItemList3 = new java.util.ArrayList<foo.PhysicalItem>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<foo.PhysicalItem>) physicalItemList3, physicalItemArray2);
        foo.Student[] studentArray5 = new foo.Student[] {};
        java.util.ArrayList<foo.Student> studentList6 = new java.util.ArrayList<foo.Student>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<foo.Student>) studentList6, studentArray5);
        foo.Faculty[] facultyArray8 = new foo.Faculty[] {};
        java.util.ArrayList<foo.Faculty> facultyList9 = new java.util.ArrayList<foo.Faculty>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<foo.Faculty>) facultyList9, facultyArray8);
        foo.Course course11 = new foo.Course("hi!", "hi!", physicalItemList3, studentList6, facultyList9);
        course11.setCode("");
        java.util.ArrayList<foo.Student> studentList14 = course11.getStudents();
        foo.Student student15 = null;
        course11.addStudent(student15);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(physicalItemArray2);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(studentArray5);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(facultyArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(studentList14);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.PhysicalItem[] physicalItemArray2 = new foo.PhysicalItem[] {};
        java.util.ArrayList<foo.PhysicalItem> physicalItemList3 = new java.util.ArrayList<foo.PhysicalItem>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<foo.PhysicalItem>) physicalItemList3, physicalItemArray2);
        foo.Student[] studentArray5 = new foo.Student[] {};
        java.util.ArrayList<foo.Student> studentList6 = new java.util.ArrayList<foo.Student>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<foo.Student>) studentList6, studentArray5);
        foo.Faculty[] facultyArray8 = new foo.Faculty[] {};
        java.util.ArrayList<foo.Faculty> facultyList9 = new java.util.ArrayList<foo.Faculty>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<foo.Faculty>) facultyList9, facultyArray8);
        foo.Course course11 = new foo.Course("hi!", "hi!", physicalItemList3, studentList6, facultyList9);
        java.util.ArrayList<foo.PhysicalItem> physicalItemList12 = course11.getTextBooks();
        java.util.ArrayList<foo.Faculty> facultyList13 = course11.getFaculty();
        java.util.ArrayList<foo.PhysicalItem> physicalItemList14 = course11.getTextBooks();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(physicalItemArray2);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(studentArray5);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(facultyArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(physicalItemList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(facultyList13);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(physicalItemList14);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        java.util.ArrayList<foo.PhysicalItem> physicalItemList2 = null;
        foo.PhysicalItem[] physicalItemArray5 = new foo.PhysicalItem[] {};
        java.util.ArrayList<foo.PhysicalItem> physicalItemList6 = new java.util.ArrayList<foo.PhysicalItem>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<foo.PhysicalItem>) physicalItemList6, physicalItemArray5);
        foo.Student[] studentArray8 = new foo.Student[] {};
        java.util.ArrayList<foo.Student> studentList9 = new java.util.ArrayList<foo.Student>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<foo.Student>) studentList9, studentArray8);
        foo.Faculty[] facultyArray11 = new foo.Faculty[] {};
        java.util.ArrayList<foo.Faculty> facultyList12 = new java.util.ArrayList<foo.Faculty>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<foo.Faculty>) facultyList12, facultyArray11);
        foo.Course course14 = new foo.Course("hi!", "hi!", physicalItemList6, studentList9, facultyList12);
        foo.Faculty[] facultyArray15 = new foo.Faculty[] {};
        java.util.ArrayList<foo.Faculty> facultyList16 = new java.util.ArrayList<foo.Faculty>();
        boolean boolean17 = java.util.Collections.addAll((java.util.Collection<foo.Faculty>) facultyList16, facultyArray15);
        foo.Course course18 = new foo.Course("", "hi!", physicalItemList2, studentList9, facultyList16);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(physicalItemArray5);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(studentArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(facultyArray11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(facultyArray15);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
    }
}

