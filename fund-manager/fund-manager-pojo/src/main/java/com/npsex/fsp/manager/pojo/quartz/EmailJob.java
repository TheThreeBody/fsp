package com.npsex.fsp.manager.pojo.quartz;

import com.npsex.fsp.commons.utils.EmailUtil;
import com.npsex.fsp.commons.utils.HttpClientUtil;
import jodd.mail.MailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @ClassName: QuartzJobFactory
 * @Description: 任务具体实现类:无状态、可在正在运行时停止 ;这里实现的是无状态job,即job可以并发
 *               如果要实现有状态的job，只需给job类加上注解 @DisallowConcurrentExecution @DisallowConcurrentExecution
 * @author DongWen
 * @date 2016年7月15日 上午11:04:50
 *
 */
public class EmailJob implements InterruptableJob {

	private static final Logger logger = LogManager.getLogger(EmailJob.class);

	@Autowired
	EmailUtil emailUtil;

	private boolean interrupted = false;

	private JobKey jobKey = null;

	public EmailJob() {
		logger.info("new class---------------------");
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("开始准备执行Emailjob");
		jobKey = context.getJobDetail().getKey();
		if (context.isRecovering()) {
			// job is recovering
			logger.info("job 已恢复");
		} else {
			// job is start
			logger.info("job 开始执行");
			try {
				if (!interrupted) {
					String s = HttpClientUtil.doPostJson("http://mysql.npsex.com:8083/syqrs/generateGrxxsqs.html","{}");
					logger.info("s>>>>>>>>>>>>>>>>>>>>>>>>>:" + s);
//					emailUtil.send126Mail("wjggwm@126.com", "job test", "job test");
					//todo
					logger.info("job 执行...");
				} else {
					logger.info("用户停止了这个任务job: " + jobKey);
					return;
				}
			} catch (MailException e) {
				logger.error("发送邮件异常", e);
			}
		}

		System.out.println("任务成功运行");

	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		interrupted = true;
		logger.info("用户停止了这个任务job: " + jobKey);
	}

}
