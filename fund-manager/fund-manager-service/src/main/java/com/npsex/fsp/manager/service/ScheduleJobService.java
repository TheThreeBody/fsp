package com.npsex.fsp.manager.service;

import java.util.List;
import java.util.Map;

import com.npsex.fsp.manager.pojo.ScheduleJob;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

public interface ScheduleJobService {

	public List<ScheduleJob> queryListByPage(Map<String, Object> parameter);

	public int insert(ScheduleJob job);
	
	public ScheduleJob findById(Long id);

	public int update(ScheduleJob job);
    
    public int deleteById(Long id);
    
    /**
     * 
     * @Title: getPlanJobList
     * @Description: 获取计划中的任务列表
     * @return	List<ScheduleJob>
     * @throws
     */
    public List<ScheduleJob> getPlanJobList();
    
    /**
     * 
     * @Title: getRunningJobList
     * @Description: 获取正在运行的任务列表
     * @return	List<ScheduleJob>
     * @throws
     */
    public List<ScheduleJob> getRunningJobList();
    /**
     * 
     * @Title: getTriggers
     * @Description: 根据job获取和该job绑定的trigger列表信息
     * @param job
     * @return	List<Trigger>
     * @throws
     */
    public List<ScheduleJob> getTriggers(ScheduleJob job);
    /**
     * 
     * @Title: getScheduleJobEntity
     * @Description: 根据job从scheduler中获取ScheduleJobEntity信息
     * @param job
     * @return	ScheduleJob
     * @throws
     */
    public ScheduleJob getScheduleJobEntity(ScheduleJob job);
    /**
     * 
     * @Title: addJob
     * @Description: 动态添加job
     * @param job
     * @return	boolean
     * @throws
     */
    public boolean addJob(ScheduleJob job);
    /**
     * 
     * @Title: addJobTrigger
     * @Description: 动态添加trigger
     * @param job
     * @return	boolean
     * @throws
     */
    public boolean addJobTrigger(ScheduleJob job);
    /**
     * 
     * @Title: updateJobTrigger
     * @Description: 更新triggerr
     * @param job
     * @return	boolean
     * @throws
     */
    public boolean updateJobTrigger(ScheduleJob job);
    /**
     * 
     * @Title: pauseJob
     * @Description: 暂停job
     * @param job
     * @return	boolean
     * @throws
     */
    public boolean pauseJob(JobKey jobKey);
    /**
     * 
     * @Title: pauseJobTrigger
     * @Description: 暂停trigger
     * @param job
     * @return	boolean
     * @throws
     */
    public boolean pauseJobTrigger(TriggerKey triggerKey);
    /**
     * 
     * @Title: resumeJob
     * @Description: 恢复job
     * @param job
     * @return	boolean
     * @throws
     */
    public boolean resumeJob(JobKey jobKey);
    /**
     * 
     * @Title: resumeJobTrigger
     * @Description: 恢复trigger
     * @param job
     * @return	boolean
     * @throws
     */
    public boolean resumeJobTrigger(TriggerKey triggerKey);
    /**
     * 
     * @Title: deleteJob
     * @Description: 删除job,同时会删除和job相关的trigger
     * @param job
     * @return	boolean
     * @throws
     */
    public boolean deleteJob(JobKey jobKey);
    /**
     * 
     * @Title: deleteJobTrigger
     * @Description: 删除job触发器
     * @param job
     * @return	boolean
     * @throws
     */
    public boolean deleteJobTrigger(TriggerKey triggerKey);
    /**
     * 
     * @Title: executeJob
     * @Description: 执行job
     * @param job
     * @return	boolean
     * @throws
     */
    public boolean executeJob(JobKey jobKey);
    
    public boolean interruptJob(JobKey jobKey);
    /**
     * 
     * @Title: startAllJob
     * @Description: 开始所有job
     * @return	boolean
     * @throws
     */
    public boolean startAllJob();
    /**
     * 
     * @Title: shutdownAllJob
     * @Description: 暂停所有job
     * @return	boolean
     * @throws
     */
    public boolean shutdownAllJob();
}
