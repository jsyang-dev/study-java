package me.study.java.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogThread extends Thread {

    private final int seq;

    public LogThread(int seq) {
        this.seq = seq;
    }

    @Override
    public void run() {
        Logger logger = new Logger();
        logger.write(seq + " : " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
