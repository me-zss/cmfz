package com.shun;

import java.util.Timer;
import java.util.TimerTask;

public class TestTask {
    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("haha");
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,2000,3000);
    }
}
