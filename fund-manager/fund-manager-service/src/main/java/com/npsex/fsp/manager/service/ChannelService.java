package com.npsex.fsp.manager.service;

import com.npsex.fsp.manager.pojo.ChannelEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by James Wen on 2017/2/10.
 */
public interface ChannelService {

    public List<ChannelEntity> queryListAll(Map<String, Object> parameter);

    public List<ChannelEntity> queryListByPage(Map<String, Object> parameter);
}
