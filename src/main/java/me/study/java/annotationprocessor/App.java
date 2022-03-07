package me.study.java.annotationprocessor;

public class App {

    public static void main(String[] args) {
        Hat hat = new MagicHat();
        System.out.println(hat.pullOut());
    }
}
