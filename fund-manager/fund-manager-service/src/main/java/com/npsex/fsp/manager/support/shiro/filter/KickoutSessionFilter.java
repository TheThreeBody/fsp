package com.npsex.fsp.manager.support.shiro.filter;

import com.npsex.fsp.manager.support.shiro.ShiroAuthenticationManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 同一用户后登陆踢出前面的用户
 *
 * @author Dongwen
 */
public class KickoutSessionFilter extends AccessControlFilter {

    private String kickoutUrl; //踢出后到的地址
    private boolean kickoutAfter = false; //踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
    private int maxSession = 1; //同一个帐号最大会话数 默认1

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-kickout-session");
    }

    /**
     * 表示是否允许访问
     * 如果允许访问 返回true 否则返回false
     *
     * @param request
     * @param response
     * @param mappedValue [urls]配置中拦截器参数部分
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        Subject subject = getSubject(request, response);
        //取到请求的uri ，进行权限判断
        String url = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        if (url != null && url.startsWith(contextPath)) {
            url = url.replace(contextPath, "");
        }
        //如果是相关目录 or 没有登录 就直接return true
        if (url.startsWith("/openapi/") || (!subject.isAuthenticated() && !subject.isRemembered())) {
            return Boolean.TRUE;
        }
        Session session = subject.getSession();
        Serializable sessionId = session.getId();
        //获取tokenId
        Long userId = ShiroAuthenticationManager.getUserId();
        // 同步控制
        Deque<Serializable> deque = cache.get("" + userId);
        if (deque == null) {
            deque = new LinkedList<Serializable>();
            cache.put("" + userId, deque);
        }

        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if (!deque.contains(sessionId) && session.getAttribute(ShiroUtils.KICKOUT_STATUS) == null) {
            deque.push(sessionId);
        }

        //如果队列里的sessionId数超出最大会话数，开始踢人
        while (deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            if (kickoutAfter) { //如果踢出后者
                kickoutSessionId = deque.removeFirst();
            } else { //否则踢出前者
                kickoutSessionId = deque.removeLast();
            }

            try {
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if (kickoutSession != null) {
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute(ShiroUtils.KICKOUT_STATUS, true);
                }
            } catch (Exception e) {//ignore exception
                e.printStackTrace();
            }
        }

		/* 如果被剔出了   1.如果是ajax请求,则返回json提示信息
		                  2.如果是普通请求，直接跳转到登录页      */
        Boolean marker = (Boolean) session.getAttribute(ShiroUtils.KICKOUT_STATUS);
        if (null != marker && marker) {
            Map<String, Object> result = new HashMap<String, Object>();
            //判断是不是Ajax请求
            if (ShiroUtils.isAjax(request)) {
                result.put("status", "403");
                result.put("message", "您已经被踢出，请重新登录！");
                result.put("url", ShiroUtils.LOGIN_URL);
                ShiroUtils.writeJson(response, result);
            } else {
                WebUtils.getSavedRequest(request);
                //再重定向到登录页面
                WebUtils.issueRedirect(request, response, ShiroUtils.LOGIN_URL);
            }
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * 表示当访问拒绝时是否已经处理了
     * 如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //退出
        Subject subject = getSubject(request, response);
        subject.logout();
        return Boolean.FALSE;
    }
}
