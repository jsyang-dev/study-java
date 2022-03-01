package me.study.java.reflection;

@MyAnnotation
public class Book {

    public static String a = "A";

    private String b = "B";

    public Book() {
    }

    public Book(String b) {
        this.b = b;
    }

    private void c() {
        System.out.println("c");
    }

    public int d(int left, int right) {
        return left + right;
    }
}
