package com.hoperun.oracle.demo.component;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hoperun.oracle.demo.config.Constants;


@Component
public class ScheduledTasks
{
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private Constants constants;
    
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
	LOGGER.info("The time is now {}", dateFormat.format(new Date()));
	System.out.println(constants.name);
    }
    
    @Scheduled(cron = "0/5 * * * * *")
    public void reportCurrentTime1() {
	LOGGER.info("The time is now {}", dateFormat.format(new Date()));
	System.out.println("BBBBBBBBBB");
    }
    
    @Scheduled(cron = "0/8 * * * * *")
    public void reportCurrentTime2() {
	LOGGER.info("The time is now {}", dateFormat.format(new Date()));
	System.out.println("CCCCCCCCCC");
    }
}
