package com.npsex.fsp.manager.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.npsex.fsp.commons.core.base.BaseServiceImpl;
import com.npsex.fsp.commons.core.exception.ServiceException;
import com.npsex.fsp.manager.mapper.vocs.ScheduleJobMapper;
import com.npsex.fsp.manager.pojo.ScheduleJob;
import com.npsex.fsp.manager.service.ScheduleJobService;
import jxl.common.Logger;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends
		BaseServiceImpl<ScheduleJob, Long> implements ScheduleJobService {

	private static Logger logger = Logger.getLogger(ScheduleJobServiceImpl.class);

	@Autowired
	private ScheduleJobMapper scheduleJobMapper;

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper

	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(scheduleJobMapper);
	}

	@Override
	public List<ScheduleJob> getPlanJobList() {
		List<ScheduleJob> jobList = new ArrayList<>();
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		try {
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeySet = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeySet) {

				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);

				for (Trigger trigger : triggers) {

					ScheduleJob scheduleJob = new ScheduleJob();

					this.wrapScheduleJob(scheduleJob, scheduler, jobKey, trigger);

					jobList.add(scheduleJob);

				}

				JobDetail jobDetail = scheduler.getJobDetail(jobKey);
				ScheduleJob scheduleJob = new ScheduleJob();
				scheduleJob.setJobName(jobKey.getName());
				scheduleJob.setJobGroup(jobKey.getGroup());
				scheduleJob.setJobClassName(jobDetail.getJobClass().getName());
				scheduleJob.setJobDesc(jobDetail.getDescription());
				jobList.add(scheduleJob);
			}
		} catch (SchedulerException e) {
			logger.error("获取计划任务列表失败", e);
			throw new ServiceException("获取计划任务列表失败", e);
		}
		return jobList;
	}

	@Override
	public List<ScheduleJob> getRunningJobList() {
		List<ScheduleJob> jobList = null;
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		try {
			List<JobExecutionContext> executingJobList = scheduler.getCurrentlyExecutingJobs();
			jobList = new ArrayList<>(executingJobList.size());
			for (JobExecutionContext executingJob : executingJobList) {
				ScheduleJob scheduleJob = new ScheduleJob();
				JobDetail jobDetail = executingJob.getJobDetail();
				JobKey jobKey = jobDetail.getKey();
				Trigger trigger = executingJob.getTrigger();
				this.wrapScheduleJob(scheduleJob, scheduler, jobKey, trigger);
				jobList.add(scheduleJob);
			}
		} catch (Exception e) {
			logger.error("获取计划任务列表失败", e);
			throw new ServiceException("获取计划任务列表失败", e);
		}
		return jobList;
	}

	@Override
	public List<ScheduleJob> getTriggers(ScheduleJob job){
		List<ScheduleJob> scheduleJobList = new ArrayList<ScheduleJob>();
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger jobTrigger : triggers) {
				ScheduleJob scheduleJob = new ScheduleJob();
				this.wrapScheduleJob(scheduleJob, scheduler, jobKey, jobTrigger);
				scheduleJobList.add(scheduleJob);
			}

		} catch (SchedulerException e) {
			logger.error("jobName: "+job.getJobName()+" jobGroup: "+job.getJobGroup()+" 获取trigger异常：",e);
			throw new ServiceException("获取job失败", e);
		}
		return scheduleJobList;
	}



	@Override
	public ScheduleJob getScheduleJobEntity(ScheduleJob job){
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
			TriggerKey triggerKey = job.getTriggerKey();
			Trigger trigger = scheduler.getTrigger(triggerKey);
			this.wrapScheduleJob(job, scheduler, jobKey, trigger);
		} catch (SchedulerException e) {
			logger.error("获取job失败", e);
			throw new ServiceException("获取job失败", e);
		}
		return job;
	}

	@Transactional
	@Override
	public boolean addJob(ScheduleJob job) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobDetail jobDetail = job.getJobDetail();
			if(StringUtils.isEmpty(job.getTriggerGroup()))
			{
				//使用默认组名称:DEFAULT

				job.setTriggerGroup(Scheduler.DEFAULT_GROUP);
			}
			// 存储job

			jobDetail.getJobDataMap().put("scheduleJob", job);
			if(!StringUtils.isEmpty(job.getTriggerName())){
				// 表达式调度构建器

				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(job.getCronExpression());
				// 按新的cronExpression表达式构建一个新的trigger

				CronTrigger trigger = TriggerBuilder.newTrigger()
						.withIdentity(job.getTriggerName(),job.getTriggerGroup())
						.startAt(job.getStartDate()) // job开始日期

						.endAt(job.getEndDate())// job结束日期

						.withSchedule(scheduleBuilder).build();
				// 将job添加到quartz的scheduler容器

				scheduler.scheduleJob(jobDetail, trigger);
			}else
			{
				scheduler.addJob(jobDetail, true);
			}
			return Boolean.TRUE;
		} catch (SchedulerException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public boolean updateJobTrigger(ScheduleJob job) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			// 获取触发器标识

			TriggerKey triggerKey = job.getTriggerKey();
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			// Trigger已存在，更新相应的定时设置

			// 表达式调度构建器

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(job.getCronExpression());

			// 按新的cronExpression表达式重新构建trigger

			trigger = trigger.getTriggerBuilder()
					.forJob(job.getJobKey())
					.withIdentity(triggerKey)
					.startAt(job.getStartDate()) // job开始日期

					.endAt(job.getEndDate())// job结束日期

					.withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行

			scheduler.rescheduleJob(triggerKey, trigger);
			return Boolean.TRUE;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public boolean pauseJob(JobKey jobKey) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.pauseJob(jobKey);
			return Boolean.TRUE;
		} catch (SchedulerException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public boolean resumeJob(JobKey jobKey) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.resumeJob(jobKey);
			return Boolean.TRUE;
		} catch (SchedulerException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public boolean deleteJob(JobKey jobKey) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			return scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			throw new ServiceException(e);
		}
	}



	@Transactional
	@Override
	public boolean executeJob(JobKey jobKey) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			if(scheduler.checkExists(jobKey))
			{
				scheduler.triggerJob(jobKey);
			}
		} catch (SchedulerException e) {
			throw new ServiceException(e);
		}
		return Boolean.TRUE;
	}

	@Transactional
	@Override
	public boolean addJobTrigger(ScheduleJob job) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			// 表达式调度构建器

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(job.getCronExpression());
			// 按新的cronExpression表达式构建一个新的trigger

			CronTrigger trigger = TriggerBuilder.newTrigger()
					.forJob(job.getJobKey()) //绑定job

					.withIdentity(job.getTriggerKey())
					.startAt(job.getStartDate()) // job开始日期

					.endAt(job.getEndDate())// job结束日期

					.withSchedule(scheduleBuilder).build();
			// 将trigger添加到quartz的scheduler容器

			scheduler.scheduleJob(trigger);
			return Boolean.TRUE;
		} catch (SchedulerException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public boolean deleteJobTrigger(TriggerKey triggerKey) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			return scheduler.unscheduleJob(triggerKey);
		} catch (SchedulerException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public boolean pauseJobTrigger(TriggerKey triggerKey) {
		try
		{
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.pauseTrigger(triggerKey);
			return Boolean.TRUE;
		} catch (SchedulerException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public boolean resumeJobTrigger(TriggerKey triggerKey) {
		try
		{
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.resumeTrigger(triggerKey);
			return Boolean.TRUE;
		} catch (SchedulerException e) {
			throw new ServiceException(e);
		}
	}

	/**

	 * 封装ScheduleJob对象

	 *

	 * @param scheduleJob

	 * @param scheduler

	 * @param jobKey

	 * @param trigger

	 */
	private void wrapScheduleJob(ScheduleJob scheduleJob,
								 Scheduler scheduler, JobKey jobKey, Trigger trigger) {
		try {
			scheduleJob.setJobName(jobKey.getName());
			scheduleJob.setJobGroup(jobKey.getGroup());

			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			scheduleJob.setJobClass(jobDetail.getJobClass());
			scheduleJob.setJobDesc(jobDetail.getDescription());

			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			scheduleJob.setTriggerStatus(triggerState.name());

			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				TriggerKey triggerKey = cronTrigger.getKey();
				scheduleJob.setTriggerName(triggerKey.getName());
				scheduleJob.setTriggerGroup(triggerKey.getGroup());
				scheduleJob.setNextFireTime(cronTrigger.getNextFireTime());
				scheduleJob.setCronExpression(cronTrigger.getCronExpression());
				scheduleJob.setStartDate(cronTrigger.getStartTime());
				scheduleJob.setEndDate(cronTrigger.getEndTime());
			}
		} catch (SchedulerException e) {
			logger.error("获取触发器状态失败", e);
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public boolean startAllJob() {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			throw new ServiceException(e);
		}
		return Boolean.TRUE;
	}

	@Transactional
	@Override
	public boolean shutdownAllJob() {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.shutdown(false);
		} catch (SchedulerException e) {
			throw new ServiceException(e);
		}
		return Boolean.TRUE;
	}

	@Transactional
	@Override
	public boolean interruptJob(JobKey jobKey) {
		try
		{
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.getSchedulerInstanceId();
			return scheduler.interrupt(jobKey);
		} catch (SchedulerException e) {
			throw new ServiceException(e);
		}
	}


	
}
