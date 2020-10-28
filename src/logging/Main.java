package logging;

public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            Thread thread = new LogThread(i);
            thread.start();
        }
    }
}
