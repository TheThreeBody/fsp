package com.zhengtong.fsp.manager.service.esurfing.impl;

import com.zhengtong.fsp.commons.core.base.BaseServiceImpl;
import com.zhengtong.fsp.manager.mapper.esurfing.IndentMapper;
import com.zhengtong.fsp.manager.pojo.FrozenEntity;
import com.zhengtong.fsp.manager.pojo.IndentEntity;
import com.zhengtong.fsp.manager.service.esurfing.TestEsurfingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dongwen on 2017/7/14.
 */
@Service("testEsurfingService")
public class TestEsurfingServiceImpl extends BaseServiceImpl<IndentEntity, Long> implements TestEsurfingService {

    @Autowired
    private IndentMapper indentMapper;

    @Override
    public List<IndentEntity> queryEsurfingOrderInfo(Map<String, Object> parameter) {
        return indentMapper.queryEsurfingOrderInfo(parameter);
    }

    public List<FrozenEntity> queryEsurfingFrozenAmount(Map<String, Object> parameter) {
        return indentMapper.queryEsurfingFrozenAmount(parameter);
    }

    @Override
    public int updateFrozenOrder(Map<String, Object> parameter) {
        return indentMapper.modifyFrozenOrder(parameter);
    }

    @Override
    public int updateFrozenCustCredit(Map<String, Object> parameter) {
        return indentMapper.modifyFrozenCustCredit(parameter);
    }
    @Override
    public List<Map<String,Object>> queryEsurfingVBSID(Map<String, Object> parameter) {
        return indentMapper.queryEsurfingVBSID(parameter);
    }

}
