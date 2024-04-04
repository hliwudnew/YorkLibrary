package randoop.managementteam;

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
        foo.LibrarySystem librarySystem0 = null;
        foo.ManagementTeam managementTeam1 = new foo.ManagementTeam(librarySystem0);
        managementTeam1.removeCourse("");
        // The following exception was thrown during execution in test generation
        try {
            managementTeam1.addStudentToCourse("", "");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.LibrarySystem.getCourseByCode(String)\" because \"this.system\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.LibrarySystem librarySystem0 = null;
        foo.ManagementTeam managementTeam1 = new foo.ManagementTeam(librarySystem0);
        managementTeam1.verifyAccount("", "");
        foo.Item item5 = null;
        // The following exception was thrown during execution in test generation
        try {
            managementTeam1.enableItem(item5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.Item.setStatus(foo.ItemStateContext)\" because \"item\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.LibrarySystem librarySystem0 = null;
        foo.ManagementTeam managementTeam1 = new foo.ManagementTeam(librarySystem0);
        managementTeam1.verifyAccount("", "");
        managementTeam1.verifyAccount("hi!", "hi!");
        foo.PhysicalItem physicalItem8 = null;
        managementTeam1.addPhysicalItem(physicalItem8);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        foo.LibrarySystem librarySystem0 = null;
        foo.ManagementTeam managementTeam1 = new foo.ManagementTeam(librarySystem0);
        managementTeam1.verifyAccount("", "");
        // The following exception was thrown during execution in test generation
        try {
            managementTeam1.addTextBookToCourse("hi!", "hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"foo.LibrarySystem.getCourseByCode(String)\" because \"this.system\" is null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        foo.ManagementTeam managementTeam0 = new foo.ManagementTeam();
    }
}

