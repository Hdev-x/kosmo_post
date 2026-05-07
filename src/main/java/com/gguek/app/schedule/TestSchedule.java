package com.gguek.app.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestSchedule {

    // @Scheduled(fixedRateString = "2000", initialDelay = 1000 )
    // public void useFixRate() throws Exception{
    //     System.out.println("고정 간격으로 반복 할 코드");
    // }

    // @Scheduled(fixedDelay = 2000, initialDelayString = "2000")
    // public void useFixDelay() throws Exception{
    //     System.out.println("고정 간격으로 반복 Delay");
    // }

    @Scheduled(cron = "0 * * * * *")
    public void useCron()throws Exception{
        System.out.println("은지쿵냐 사룽해");
    }
}
