package com.npsex.fsp.manager.mapper.baidu_credit;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 *异常查询和放款查询
 * @author dell
 *
 */
@Repository
public interface IssueDataMapper {
	List<Map<String, Object>> queryIssue(@Param("beginDate") String beginDate, @Param("endDate")String endDate);
	List<Map<String, Object>> queryIssueDetail(@Param("id") Long id,@Param("type") String type);
	int updateIssueData(Map<String, Object> param);
	List<Map<String, Object>> queryloanData(Map<String, Object> param);
	int updateLoanData(Map<String,Object> param);
	int insertLoanOperationRecord(Map<String,Object> param);
	List<Map<String, Object>> selectLoanOperationRecord(@Param("vbsBid") Integer vbsBid);
	int updateLoanOperationRecord(Map<String,Object> param);
	int updateAccountStatus(Map<String, Object> param);
	int releaseFrozenAccount(@Param("vbsBid") Integer vbsBid,@Param("updateTime") Date updateTime);
	int updateOrderStatusVbsNotifyNumber(@Param("vbsBid")Integer vbsBid,@Param("noifyNumber") Integer noifyNumber);
}
