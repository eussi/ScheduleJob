package com.dcits.schedulejob.task;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;

@Component
public class TaskTest {
	public final Logger log = Logger.getLogger(this.getClass());
 
	public void run() {
		for (int i = 0; i < 1; i++) {
            System.out.println("任务运行中：" + " run......................................" + (new Date()));
		}

	}

	public void run1() {
        System.out.println("任务运行中：" + " run1......................................" + (new Date()));
	}
}
