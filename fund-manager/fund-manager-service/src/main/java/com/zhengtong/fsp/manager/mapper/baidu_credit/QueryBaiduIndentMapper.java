package com.zhengtong.fsp.manager.mapper.baidu_credit;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by dongwen on 2017/7/24.
 */
@Repository
public interface QueryBaiduIndentMapper {

    List<Map<String, Object>> queryBaiduIndent(Map<String, Object> param);

    List<Map<String,Object>> queryBaiduIndentDetail(Map<String, Object> param);

    List<Map<String,Object>> queryBaiduFinancialFile(Map<String, Object> param);
}
