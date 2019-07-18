package com.npsex.fsp.manager.service.esurfing;

import com.npsex.fsp.manager.pojo.FrozenEntity;
import com.npsex.fsp.manager.pojo.IndentEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by dongwen on 2017/7/14.
 */
public interface TestEsurfingService {
    List<IndentEntity> queryEsurfingOrderInfo(Map<String, Object> parameter);

    List<FrozenEntity> queryEsurfingFrozenAmount(Map<String, Object> parameter);

    List<Map<String,Object>> queryEsurfingVBSID(Map<String, Object> parameter);

    int updateFrozenOrder(Map<String, Object> parameter);

    int updateFrozenCustCredit(Map<String, Object> parameter);
}
