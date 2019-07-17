package com.zhengtong.fsp.manager.service.job;

import java.util.Date;

/**
 * Created by xiepeng on 2017/7/25.
 */
public interface EsurfingDataSync {

    /**
     * 借据合同（使用确认书）
     * @param reportTime 使用确认书param
     * */
    public boolean addSyqrsData(Date reportTime) throws Exception;
}
