package com.pino.rabbitmq_demo.Util;

public final class ThreadUtils {

    private ThreadUtils() {
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
