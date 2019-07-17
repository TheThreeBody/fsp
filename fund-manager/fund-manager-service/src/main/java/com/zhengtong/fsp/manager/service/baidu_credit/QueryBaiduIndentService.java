package com.zhengtong.fsp.manager.service.baidu_credit;

import com.zhengtong.fsp.commons.core.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by dongwen on 2017/7/24.
 */
public interface QueryBaiduIndentService {

    List<Map<String, Object>> queryBaiduIndent(Map<String, Object> param);

    List<Map<String, Object>> queryBaiduIndentDetail(Map<String, Object> param);

    List<Map<String, Object>> queryBaiduFinancialFile(Map<String, Object> param);


}
