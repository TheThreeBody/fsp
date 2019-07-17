package com.zhengtong.fsp.manager.quartzJob;

import com.zhengtong.fsp.manager.service.job.GenerateConRecodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by songhuiping on 2017/7/28.
 */

public class GrxxsqsJob implements InterruptableJob {
    private static final Logger logger = LogManager.getLogger(SyqrsJob.class);

    @Autowired
    private GenerateConRecodeService generateConRecodeService;

    private JobKey jobKey = null;

    public GrxxsqsJob(){

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        try {
            logger.info("----------grxxsqsJob开始执行----");
            long start = System.currentTimeMillis();
            generateConRecodeService.insertContract();
            long end = System.currentTimeMillis();
            logger.info("----------grxxsqsJob执行时间----"+(end-start)+"ms");
            logger.info("grxxsqsJob执行结束");
        } catch (Exception e) {
            logger.error("生成使用确认书任务异常", e);
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {

        logger.info("停止任务job: " + jobKey);
    }
}
