package Hw10;

import Hw10.TestClass.After;
import Hw10.TestClass.Before;
import Hw10.TestClass.Test;

public class TestTest {
    @After
    public void afterTest() {
        System.out.println("After");
    }

    @Test
    public void test1() {
        System.out.println("Test 1 default priority");
    }

    @Test(priority = 7)
    private void test2() {
        System.out.println("Test 2 private pr=7");
    }

    @Test(priority = 3)
    public void test3() {
        System.out.println("Test 3 pr=3");
    }

    @Test(priority = 2)
    public void test4() {
        System.out.println("Test 4 pr=2");
    }

    @Before
    public void beforeTest() {
        System.out.println("Before");
    }
}
