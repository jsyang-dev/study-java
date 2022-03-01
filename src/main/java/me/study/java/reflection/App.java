package me.study.java.reflection;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        Class<Book> bookClass = Book.class;
        Book book = new Book();

        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);
                System.out.printf("%s %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            int modifiers = f.getModifiers();
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));

            Arrays.stream(f.getAnnotations()).forEach(a -> {
                MyAnnotation myAnnotation = (MyAnnotation) a;
                System.out.printf("%s %s %d\n", a, myAnnotation.value(), myAnnotation.number());
            });
        });

        Arrays.stream(bookClass.getMethods()).forEach(m -> {
            int modifiers = m.getModifiers();
            System.out.println(m);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(m.getReturnType());
        });

        Arrays.stream(bookClass.getDeclaredConstructors()).forEach(System.out::println);
        System.out.println(MyBook.class.getSuperclass());
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);

        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);
    }
}
