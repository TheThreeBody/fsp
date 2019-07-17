package com.zhengtong.fsp.manager.service.baidu_credit.impl;

import com.zhengtong.fsp.manager.mapper.baidu_credit.QueryBaiduIndentMapper;
import com.zhengtong.fsp.manager.service.baidu_credit.QueryBaiduIndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dongwen on 2017/7/24.
 */
@Service
public class QueryBaiduIndentServiceImpl implements QueryBaiduIndentService {

    @Autowired
    private QueryBaiduIndentMapper queryBaiduIndentMapper;

    @Override
    public List<Map<String, Object>> queryBaiduIndent(Map<String, Object> param){
        return queryBaiduIndentMapper.queryBaiduIndent(param);
    }

    @Override
    public List<Map<String, Object>> queryBaiduIndentDetail(Map<String, Object> param) {
        return queryBaiduIndentMapper.queryBaiduIndentDetail(param);
    }

    @Override
    public List<Map<String, Object>> queryBaiduFinancialFile(Map<String, Object> param) {
        return queryBaiduIndentMapper.queryBaiduFinancialFile(param);
    }


}
