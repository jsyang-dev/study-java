package me.study.java.bytecode;

public class Magician {

    // VM options: -javaagent:magician-agent-1.0-SNAPSHOT.jar
    public static void main(String[] args) {
        System.out.println(new Hat().pullOut());
    }
}
