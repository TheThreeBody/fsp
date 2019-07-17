/**
 * 
 */
package com.zhengtong.fsp.manager.service;

import com.zhengtong.fsp.manager.pojo.UserSessionEntity;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.List;

/**
 * @ClassName UserSessionService
 * @Description TODO
 *
 * @author Dongwen
 * @data 2016年12月14日 下午6:10:19
 */
public interface UserSessionService {

	/**
	 * 
	 * @Description 获取所有session用户
	 * @return
	 *
	 * @author Dongwen
	 * @data 2016年12月30日 下午5:22:24
	 */
	public  List<UserSessionEntity> getAllUser();
	
	/**
	 * 
	 * @Description TODO
	 * @param userIds
	 * @return
	 *
	 * @author Dongwen
	 * @data 2016年12月30日 下午5:22:21
	 */
	public List<SimplePrincipalCollection> getSimplePrincipalCollectionByUserId(Long...userIds);
	
	/**
	 * 
	 * @Description 获取单个session用户
	 * @param sessionId
	 * @return
	 *
	 * @author Dongwen
	 * @data 2016年12月30日 下午5:22:18
	 */
	public UserSessionEntity getSession(String sessionId);
	
	/**
	 * 
	 * @Description 获取单个session用户
	 * @param session
	 * @return
	 *
	 * @author Dongwen
	 * @data 2016年12月30日 下午5:22:14
	 */
	public UserSessionEntity getSessionUser(Session session);
	
	/**
	 * 
	 * @Description 踢出用户
	 * @param sessionId
	 *
	 * @author Dongwen
	 * @data 2016年12月30日 下午5:05:25
	 */
	public boolean kickoutUser(String sessionId);
	
	
}
