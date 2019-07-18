package com.npsex.fsp.manager.pojo.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;


public class WorkerItem implements Runnable, Serializable {

    private static final Logger logger = LogManager.getLogger(WorkerItem.class);
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 执行任务
     */
    @Override
    public void run() {
        try {
            Thread.sleep(ThreadPool.SLEEP_TIME);
            System.out.print("任务执行了");
        } catch (InterruptedException e) {
            logger.error("执行任务异常:{}", e);
        }

    }

}
