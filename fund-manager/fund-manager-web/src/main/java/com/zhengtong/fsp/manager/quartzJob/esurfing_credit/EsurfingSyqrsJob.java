package com.zhengtong.fsp.manager.quartzJob.esurfing_credit;

import com.zhengtong.fsp.commons.utils.DateTimeUtils;
import com.zhengtong.fsp.manager.service.job.EsurfingDataSync;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by xiepeng on 2017/7/31.
 */
public class EsurfingSyqrsJob implements InterruptableJob {
    private static final Logger logger = LogManager.getLogger(EsurfingSyqrsJob.class);

    @Autowired
    private EsurfingDataSync esurfingDataSync;

    private JobKey jobKey = null;

    public EsurfingSyqrsJob(){

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            logger.info("EsurfingSyqrsJob开始执行");
            esurfingDataSync.addSyqrsData(DateTimeUtils.addDayTime(new Date(),-1));
            logger.info("EsurfingSyqrsJob执行结束");
        } catch (Exception e) {
            logger.error("获取使用授权书数据异常", e);
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {

        logger.info("停止任务job: " + jobKey);
    }
}
