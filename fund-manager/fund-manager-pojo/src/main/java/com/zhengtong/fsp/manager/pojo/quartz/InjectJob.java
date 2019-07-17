package com.zhengtong.fsp.manager.pojo.quartz;

import com.zhengtong.fsp.manager.pojo.ScheduleJob;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 
 * @ClassName: InjectJob
 * @Description: QuartzJobBean:This allows to implement dependency-injected Quartz Jobs without a dependency on Spring base classes
 * @author Dongwen
 * @date 2016年7月18日 下午5:54:08
 *
 */
public class InjectJob extends QuartzJobBean {

	private static final Logger logger = LogManager.getLogger(InjectJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("任务执行了...");
		/*
		 * JobExecutionContext 将会合并JobDetail与Trigger的JobDataMap，如果其中属性名相同，后者将覆盖前者。
		 * 可以使用JobExecutionContext.getMergedJobDataMap()方法来获取合并后的JobDataMap
		 */
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		
		System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]");
		
	}

}
