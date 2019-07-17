package com.zhengtong.fsp.manager.mapper.job.dict;

import com.zhengtong.fsp.commons.core.base.BaseMapper;
import com.zhengtong.fsp.manager.pojo.job.dict.DictItemEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DictItemMapper extends BaseMapper<DictItemEntity, Long> {

    DictItemEntity selectDict(Map<String, String> map);
}