package com.zhengtong.fsp.manager.quartzJob;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhengtong.fsp.commons.core.base.BaseController;
import com.zhengtong.fsp.commons.core.datasource.DatabaseContextHolder;
import com.zhengtong.fsp.manager.pojo.quartz.CommonPersonalInfo;
import com.zhengtong.fsp.manager.service.PersonalInformationJobService;

public class PersonalInformationJob extends BaseController implements InterruptableJob{
	
	public static List<String> partitionDbKeys = new ArrayList<String>();

	private static final Integer MAX_QUERY_THREAD_NUM = 10;//建议最好是cpu核数*2

	private static ExecutorService threadPool = Executors.newFixedThreadPool(MAX_QUERY_THREAD_NUM);

	private CompletionService<List<CommonPersonalInfo>> completionService = new ExecutorCompletionService<List<CommonPersonalInfo>>(
			threadPool);
	
	static{
//		partitionDbKeys.add("dataSourceGoboo");
//		partitionDbKeys.add("dataSourceHebao");
//		partitionDbKeys.add("dataSourceEsurfingCredit");
//		partitionDbKeys.add("dataSourceEsurfing");
	}
	
	@Autowired
	private PersonalInformationJobService piJobService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("开始转移各个分库，个人信息授信信息");
		List<CommonPersonalInfo> result = new ArrayList<CommonPersonalInfo>();
		
		for (String dbkey : partitionDbKeys) {
			completionService.submit(new PersonalInfoCallable<List<CommonPersonalInfo>>(dbkey) {
				@Override
				public List<CommonPersonalInfo> call() throws Exception {
					DatabaseContextHolder.setCustomerType(getPartitionDbKeys());
					return piJobService.queryPersonalInfo(getPartitionDbKeys());
				}
			});
		}
		
		for (String dbkey : partitionDbKeys) {
			try {
				Future<List<CommonPersonalInfo>> futrue = completionService.take();
				result.addAll(futrue.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		logger.info("分库中个人信息授权书共：{}",result.size());
		piJobService.batchSavePersonInfo(result);
		
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		
	}

}

abstract class PersonalInfoCallable<T> implements Callable<T>{
	
	private String partitionDbKeys;
	
	public String getPartitionDbKeys(){
		return partitionDbKeys;
	}
	
	public PersonalInfoCallable(String partitionDbKeys){
		this.partitionDbKeys =partitionDbKeys;
	}
	
	
}

