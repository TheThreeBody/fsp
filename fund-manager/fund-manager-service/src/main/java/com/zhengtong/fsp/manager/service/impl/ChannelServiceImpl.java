package com.zhengtong.fsp.manager.service.impl;

import com.zhengtong.fsp.commons.core.base.BaseServiceImpl;
import com.zhengtong.fsp.manager.mapper.vocs.ChannelMapper;
import com.zhengtong.fsp.manager.pojo.ChannelEntity;
import com.zhengtong.fsp.manager.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by James Wen on 2017/2/10.
 */
@Service("ChannelService")
public class ChannelServiceImpl extends BaseServiceImpl<ChannelEntity, Long>
implements ChannelService {
    @Autowired
    private ChannelMapper channelMapper;

    // 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(channelMapper);
    }


}
