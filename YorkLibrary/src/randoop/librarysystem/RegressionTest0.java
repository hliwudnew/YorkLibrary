package randoop.librarysystem;

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
        foo.Item[] itemArray3 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList4 = new java.util.ArrayList<foo.Item>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList4, itemArray3);
        foo.User[] userArray6 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList7 = new java.util.ArrayList<foo.User>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList7, userArray6);
        foo.OnlineItem[] onlineItemArray9 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList10 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList10, onlineItemArray9);
        foo.Course[] courseArray12 = new foo.Course[] {};
        java.util.ArrayList<foo.Course> courseList13 = new java.util.ArrayList<foo.Course>();
        boolean boolean14 = java.util.Collections.addAll((java.util.Collection<foo.Course>) courseList13, courseArray12);
        foo.LibrarySystem librarySystem15 = new foo.LibrarySystem(itemList1, itemList4, userList7, onlineItemList10, courseList13);
        foo.User user16 = null;
        // The following exception was thrown during execution in test generation
        try {
            librarySystem15.addUser(user16);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray3);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray9);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(courseArray12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        foo.Item[] itemArray0 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList1 = new java.util.ArrayList<foo.Item>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList1, itemArray0);
        foo.Item[] itemArray3 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList4 = new java.util.ArrayList<foo.Item>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList4, itemArray3);
        foo.User[] userArray6 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList7 = new java.util.ArrayList<foo.User>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList7, userArray6);
        foo.OnlineItem[] onlineItemArray9 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList10 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList10, onlineItemArray9);
        foo.Course[] courseArray12 = new foo.Course[] {};
        java.util.ArrayList<foo.Course> courseList13 = new java.util.ArrayList<foo.Course>();
        boolean boolean14 = java.util.Collections.addAll((java.util.Collection<foo.Course>) courseList13, courseArray12);
        foo.LibrarySystem librarySystem15 = new foo.LibrarySystem(itemList1, itemList4, userList7, onlineItemList10, courseList13);
        int int16 = librarySystem15.getNUM_COPIES_PER_ITEM();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray3);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray9);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(courseArray12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 20 + "'", int16 == 20);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.Item[] itemArray0 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList1 = new java.util.ArrayList<foo.Item>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList1, itemArray0);
        foo.Item[] itemArray3 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList4 = new java.util.ArrayList<foo.Item>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList4, itemArray3);
        foo.User[] userArray6 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList7 = new java.util.ArrayList<foo.User>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList7, userArray6);
        foo.OnlineItem[] onlineItemArray9 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList10 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList10, onlineItemArray9);
        foo.Course[] courseArray12 = new foo.Course[] {};
        java.util.ArrayList<foo.Course> courseList13 = new java.util.ArrayList<foo.Course>();
        boolean boolean14 = java.util.Collections.addAll((java.util.Collection<foo.Course>) courseList13, courseArray12);
        foo.LibrarySystem librarySystem15 = new foo.LibrarySystem(itemList1, itemList4, userList7, onlineItemList10, courseList13);
        foo.Item item17 = librarySystem15.getPhysicalItem(0);
        foo.Item[] itemArray18 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList19 = new java.util.ArrayList<foo.Item>();
        boolean boolean20 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList19, itemArray18);
        foo.Item[] itemArray21 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList22 = new java.util.ArrayList<foo.Item>();
        boolean boolean23 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList22, itemArray21);
        foo.User[] userArray24 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList25 = new java.util.ArrayList<foo.User>();
        boolean boolean26 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList25, userArray24);
        foo.OnlineItem[] onlineItemArray27 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList28 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean29 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList28, onlineItemArray27);
        foo.Course[] courseArray30 = new foo.Course[] {};
        java.util.ArrayList<foo.Course> courseList31 = new java.util.ArrayList<foo.Course>();
        boolean boolean32 = java.util.Collections.addAll((java.util.Collection<foo.Course>) courseList31, courseArray30);
        foo.LibrarySystem librarySystem33 = new foo.LibrarySystem(itemList19, itemList22, userList25, onlineItemList28, courseList31);
        librarySystem15.setCourses(courseList31);
        java.util.ArrayList<foo.Course> courseList35 = librarySystem15.getCourses();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray3);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray9);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(courseArray12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(item17);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray18);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray21);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray24);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray27);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(courseArray30);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(courseList35);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.Item[] itemArray0 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList1 = new java.util.ArrayList<foo.Item>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList1, itemArray0);
        foo.Item[] itemArray3 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList4 = new java.util.ArrayList<foo.Item>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList4, itemArray3);
        foo.User[] userArray6 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList7 = new java.util.ArrayList<foo.User>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList7, userArray6);
        foo.OnlineItem[] onlineItemArray9 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList10 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList10, onlineItemArray9);
        foo.Course[] courseArray12 = new foo.Course[] {};
        java.util.ArrayList<foo.Course> courseList13 = new java.util.ArrayList<foo.Course>();
        boolean boolean14 = java.util.Collections.addAll((java.util.Collection<foo.Course>) courseList13, courseArray12);
        foo.LibrarySystem librarySystem15 = new foo.LibrarySystem(itemList1, itemList4, userList7, onlineItemList10, courseList13);
        foo.PhysicalItem[] physicalItemArray16 = new foo.PhysicalItem[] {};
        java.util.ArrayList<foo.PhysicalItem> physicalItemList17 = new java.util.ArrayList<foo.PhysicalItem>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<foo.PhysicalItem>) physicalItemList17, physicalItemArray16);
        librarySystem15.setItems(physicalItemList17);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray3);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray9);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(courseArray12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(physicalItemArray16);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        foo.Item[] itemArray0 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList1 = new java.util.ArrayList<foo.Item>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList1, itemArray0);
        foo.Item[] itemArray3 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList4 = new java.util.ArrayList<foo.Item>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList4, itemArray3);
        foo.User[] userArray6 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList7 = new java.util.ArrayList<foo.User>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList7, userArray6);
        foo.OnlineItem[] onlineItemArray9 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList10 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList10, onlineItemArray9);
        foo.Course[] courseArray12 = new foo.Course[] {};
        java.util.ArrayList<foo.Course> courseList13 = new java.util.ArrayList<foo.Course>();
        boolean boolean14 = java.util.Collections.addAll((java.util.Collection<foo.Course>) courseList13, courseArray12);
        foo.LibrarySystem librarySystem15 = new foo.LibrarySystem(itemList1, itemList4, userList7, onlineItemList10, courseList13);
        foo.Item item17 = librarySystem15.getPhysicalItem(0);
        foo.Item[] itemArray18 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList19 = new java.util.ArrayList<foo.Item>();
        boolean boolean20 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList19, itemArray18);
        foo.Item[] itemArray21 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList22 = new java.util.ArrayList<foo.Item>();
        boolean boolean23 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList22, itemArray21);
        foo.User[] userArray24 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList25 = new java.util.ArrayList<foo.User>();
        boolean boolean26 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList25, userArray24);
        foo.OnlineItem[] onlineItemArray27 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList28 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean29 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList28, onlineItemArray27);
        foo.Course[] courseArray30 = new foo.Course[] {};
        java.util.ArrayList<foo.Course> courseList31 = new java.util.ArrayList<foo.Course>();
        boolean boolean32 = java.util.Collections.addAll((java.util.Collection<foo.Course>) courseList31, courseArray30);
        foo.LibrarySystem librarySystem33 = new foo.LibrarySystem(itemList19, itemList22, userList25, onlineItemList28, courseList31);
        librarySystem15.setCourses(courseList31);
        java.util.ArrayList<foo.Faculty> facultyList35 = librarySystem15.getFaculty();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray3);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray9);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(courseArray12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(item17);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray18);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray21);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray24);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray27);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(courseArray30);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(facultyList35);
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        foo.Item[] itemArray0 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList1 = new java.util.ArrayList<foo.Item>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList1, itemArray0);
        foo.Item[] itemArray3 = new foo.Item[] {};
        java.util.ArrayList<foo.Item> itemList4 = new java.util.ArrayList<foo.Item>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<foo.Item>) itemList4, itemArray3);
        foo.User[] userArray6 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList7 = new java.util.ArrayList<foo.User>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList7, userArray6);
        foo.OnlineItem[] onlineItemArray9 = new foo.OnlineItem[] {};
        java.util.ArrayList<foo.OnlineItem> onlineItemList10 = new java.util.ArrayList<foo.OnlineItem>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<foo.OnlineItem>) onlineItemList10, onlineItemArray9);
        foo.Course[] courseArray12 = new foo.Course[] {};
        java.util.ArrayList<foo.Course> courseList13 = new java.util.ArrayList<foo.Course>();
        boolean boolean14 = java.util.Collections.addAll((java.util.Collection<foo.Course>) courseList13, courseArray12);
        foo.LibrarySystem librarySystem15 = new foo.LibrarySystem(itemList1, itemList4, userList7, onlineItemList10, courseList13);
        foo.User[] userArray16 = new foo.User[] {};
        java.util.ArrayList<foo.User> userList17 = new java.util.ArrayList<foo.User>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<foo.User>) userList17, userArray16);
        librarySystem15.setUsers(userList17);
        java.util.ArrayList<foo.Faculty> facultyList20 = librarySystem15.getFaculty();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(itemArray3);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(onlineItemArray9);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(courseArray12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(userArray16);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(facultyList20);
    }
}

