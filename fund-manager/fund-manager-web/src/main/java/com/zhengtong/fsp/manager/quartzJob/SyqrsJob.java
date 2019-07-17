package com.zhengtong.fsp.manager.quartzJob;

import com.zhengtong.fsp.manager.service.job.GenerateSyqrsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by songhuiping on 2017/7/28.
 */
public class SyqrsJob implements InterruptableJob {
    private static final Logger logger = LogManager.getLogger(SyqrsJob.class);

    @Autowired
    private GenerateSyqrsService generateSyqrsService;

    private JobKey jobKey = null;

    public SyqrsJob(){

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        java.util.Calendar calendar = java.util.Calendar.getInstance();
//        calendar.add(java.util.Calendar.DAY_OF_MONTH, -1);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String consume_time = formatter.format(date);
        try {
            logger.info("syqrsJob开始执行----消费时间是："+consume_time);
            generateSyqrsService.insertSyqrs(consume_time);
            logger.info("syqrsJob执行结束");
        } catch (Exception e) {
            logger.error("生成使用确认书任务异常", e);
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {

        logger.info("停止任务job: " + jobKey);
    }
}
