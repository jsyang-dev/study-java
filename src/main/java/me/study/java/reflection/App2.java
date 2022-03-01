package me.study.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App2 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> bookClass = Class.forName("me.study.java.reflection.Book");
        Constructor<?> constructor = bookClass.getConstructor();
        Book book = (Book) constructor.newInstance();
        System.out.println(book);

        Constructor<?> constructor2 = bookClass.getConstructor(String.class);
        Book myBook = (Book) constructor2.newInstance("myBook");
        System.out.println(myBook);

        Field a = Book.class.getDeclaredField("a");
        System.out.println(a.get(null));
        a.set(null, "AAA");
        System.out.println(a.get(null));

        Field b = Book.class.getDeclaredField("b");
        b.setAccessible(true);
        System.out.println(b.get(book));
        b.set(book, "BBB");
        System.out.println(b.get(book));

        Method c = Book.class.getDeclaredMethod("c");
        c.setAccessible(true);
        c.invoke(book);

        Method d = Book.class.getDeclaredMethod("d", int.class, int.class);
        int sum = (int) d.invoke(book, 1, 2);
        System.out.println(sum);
    }
}
