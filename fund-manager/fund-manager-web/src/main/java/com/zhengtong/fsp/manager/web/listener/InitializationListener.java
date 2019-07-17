/**
 *
 */
package com.zhengtong.fsp.manager.web.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * @author Dongwen
 * @ClassName InitializationListener
 * @Description 系统启动时执行初始化任务
 * @data 2016年12月6日 下午4:21:41
 */
@Component
public class InitializationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LogManager.getLogger(InitializationListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (null == event.getApplicationContext().getParent()) {

            /**
             * 这里可以添加容器启动后需要执行的代码
             */

            logger.info("系统初始化完成");
        }
    }

}
