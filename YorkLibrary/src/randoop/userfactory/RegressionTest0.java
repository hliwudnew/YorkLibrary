package randoop.userfactory;

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
        foo.UserFactory userFactory0 = new foo.UserFactory();
        java.lang.Class<?> wildcardClass1 = userFactory0.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        foo.UserFactory userFactory0 = new foo.UserFactory();
        foo.User user2 = userFactory0.getUser("hi!");
        foo.User user4 = userFactory0.getUser("");
        foo.User user6 = userFactory0.getUser("hi!");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(user2);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(user4);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(user6);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        foo.UserFactory userFactory0 = new foo.UserFactory();
        foo.User user2 = userFactory0.getUser("hi!");
        foo.User user4 = userFactory0.getUser("");
        java.lang.Class<?> wildcardClass5 = userFactory0.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(user2);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(user4);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        foo.UserFactory userFactory0 = new foo.UserFactory();
        foo.User user2 = userFactory0.getUser("hi!");
        foo.User user4 = userFactory0.getUser("");
        foo.User user6 = userFactory0.getUser("");
        foo.User user8 = userFactory0.getUser("");
        java.lang.Class<?> wildcardClass9 = userFactory0.getClass();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(user2);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(user4);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(user6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(user8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(wildcardClass9);
    }
}

