package com.zhengtong.fsp.manager.mapper.esurfing;

import com.zhengtong.fsp.commons.core.base.BaseMapper;
import com.zhengtong.fsp.manager.pojo.FrozenEntity;
import com.zhengtong.fsp.manager.pojo.IndentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by dongwen on 2017/7/5.
 */
@Repository
public interface IndentMapper extends BaseMapper<IndentEntity, Long> {

    List<IndentEntity> queryGobooIndentListByPage(Map<String, Object> parameter);

    List<IndentEntity> queryEsurfingOrderInfo(Map<String, Object> parameter);

    List<FrozenEntity> queryEsurfingFrozenAmount(Map<String, Object> parameter);

    List<Map<String,Object>> queryEsurfingVBSID(Map<String, Object> parameter);

    int modifyFrozenOrder(Map<String, Object> parameter);

    int modifyFrozenCustCredit(Map<String, Object> parameter);
}
