package com.zhengtong.fsp.manager.service.job;

/**
 * Created by songhuiping on 2017/7/17.
 */

public interface GenerateSyqrsService {

    /**
     * 生成合同
     * @param consume_time 消费时间
     */
    public void insertSyqrs(String consume_time);
}
