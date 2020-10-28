package logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    public synchronized void write(String str) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test.log", true))){
            writer.append(str).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
