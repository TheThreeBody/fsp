package com.npsex.fsp.manager.service;

import com.npsex.fsp.commons.core.pojo.tree.ResourceEntity;

public interface RoleResourceService {

	/**
	 * 
	 * @Title: insertRoleAndResource
	 * @Description: 添加资源(权限)时同步给超级管理员赋予该权限
	 * @param resourceEntity	
	 * @return	boolean
	 * @throws
	 */
	public boolean insertRoleAndResource(ResourceEntity resourceEntity);
	
}
