package com.zhengtong.fsp.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengtong.fsp.commons.core.exception.ServiceException;
import com.zhengtong.fsp.commons.core.pojo.tree.ResourceEntity;
import com.zhengtong.fsp.manager.service.ResourceService;
import com.zhengtong.fsp.manager.pojo.RoleEntity;
import com.zhengtong.fsp.manager.service.RoleService;
import com.zhengtong.fsp.manager.service.RoleResourceService;

@Service("roleResourceService")
public class RoleResourceServiceImpl implements RoleResourceService{

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Override
	public boolean insertRoleAndResource(ResourceEntity resourceEntity) {
		try
		{
			//1、添加资源
			resourceService.insert(resourceEntity);
			//2、超级管理员直接赋予该权限
			RoleEntity role = roleService.findByName("超级管理员");
			roleService.addRolePerm(role.getId(), resourceEntity.getId());
			return true;
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}

}
