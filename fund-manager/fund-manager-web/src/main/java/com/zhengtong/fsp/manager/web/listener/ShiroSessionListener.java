package com.zhengtong.fsp.manager.web.listener;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.stereotype.Component;

import static jxl.biff.FormatRecord.logger;

/**
 * 
 * @ClassName ShiroSessionListener
 * @Description shiro session 会话监听器
 *
 * @author Dongwen
 * @data 2016年12月13日 下午2:49:29
 */
@Component
public class ShiroSessionListener implements SessionListener {

//	@Autowired
//    private ShiroSessionRepository shiroSessionRepository;

    private static Logger logggr = LogManager.getLogger(ShiroSessionListener.class);
    /**
     * session会话开始
     */
    @Override
    public void onStart(Session session) {
        logger.debug("会话创建：" + session.getId());
    }
    /**
     * session会话结束
     */
    @Override
    public void onStop(Session session) { //退出时触发
        logger.info("会话停止：" + session.getId());
    }

    /**
     * session会话到期
     */
    @Override
    public void onExpiration(Session session) {
//    	//session过期进行清理
//        shiroSessionRepository.deleteSession(session.getId());
        logger.debug("会话过期：" + session.getId());
    }

}

