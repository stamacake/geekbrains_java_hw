package Hw10.TestClass;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class Tests {
    private static Object tmp;

    private static boolean BeforeAfter(Class tmpClass) {
        int beforeCount = 0, afterCount = 0;
        for (Method method : tmpClass.getDeclaredMethods()) {
            if (method.getAnnotation(After.class) != null) afterCount++;
            if (method.getAnnotation(Before.class) != null) beforeCount++;
        }

        return (beforeCount < 2) && (afterCount < 2);
    }

    public static void start(Class tmpClass) {

        // Before/After annotations = 1/0
        if (!BeforeAfter(tmpClass)) throw new RuntimeException();

        try {
            tmp = tmpClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println(e);
        }

        Method[] methods = tmpClass.getDeclaredMethods();

        //find before,after, others
        Method after = null;
        Method before = null;
        List<Method> allMethods = new ArrayList<>();
        for (Method mtd : methods) {
            if (mtd.getAnnotation(After.class) != null) {
                after = mtd;
            } else if (mtd.getAnnotation(Before.class) != null) {
                before = mtd;
            } else if (mtd.getAnnotation(Test.class) != null) {
                allMethods.add(mtd);
            }
        }

        //check priorities
        for (Method mtd : allMethods) {
            if (mtd.getAnnotation(Test.class).priority() > 10 || mtd.getAnnotation(Test.class).priority() < 0)
                throw new RuntimeException();
        }

        // sort by priority
        allMethods.sort(Comparator.comparingInt(i -> i.getAnnotation(Test.class).priority()));
        Collections.reverse(allMethods);

        // push before after
        if (before != null) allMethods.add(0, before);
        if (after != null) allMethods.add(after);

        // invoke methods

        try {
            for (Method mtd : allMethods) {
                //check access
                if (Modifier.isPrivate(mtd.getModifiers()))
                    mtd.setAccessible(true);
                mtd.invoke(tmp);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
