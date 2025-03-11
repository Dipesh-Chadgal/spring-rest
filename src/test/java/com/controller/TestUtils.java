package com.controller;

public class TestUtils {
    public static String businessTestFile = "business_tests.txt";

    public static String currentTest() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public static void yakshaAssert(String testName, boolean result, String testFile) {
        System.out.println("Test Name: " + testName + " | Result: " + (result ? "PASS" : "FAIL"));
    }

    public static void testReport() {
        System.out.println("All test cases executed.");
    }
}
