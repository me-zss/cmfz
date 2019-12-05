package com.shun;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduleExecutorServer {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("haha:"+new Date());
            }
        };
        scheduledExecutorService.scheduleAtFixedRate(runnable,1,3, TimeUnit.SECONDS);
    }
}
