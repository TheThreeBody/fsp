package com.npsex.fsp.manager.service.impl;

import java.util.List;
import java.util.Map;

import com.npsex.fsp.commons.core.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npsex.fsp.commons.core.exception.ServiceException;
import com.npsex.fsp.manager.mapper.vocs.ResourceMapper;
import com.npsex.fsp.commons.core.pojo.tree.ResourceEntity;
import com.npsex.fsp.manager.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<ResourceEntity, Long>
		implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;

	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为resourceMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(resourceMapper);
	}

	@Override
	public List<ResourceEntity> findResourcesByUserId(int userId) {
		return resourceMapper.findResourcesByUserId(userId);
	}

	@Override
	public List<ResourceEntity> queryResourceList(Map<String, Object> parameter) {
		return resourceMapper.queryResourceList(parameter);
	}

	@Override
	public List<ResourceEntity> findResourcesMenuByUserId(int userId) {
		return resourceMapper.findResourcesMenuByUserId(userId);
	}

	@Override
	public boolean deleteRoleAndResource(List<Long> resourceIds) {
		try
		{
			for (Long resourceId : resourceIds) {
				resourceMapper.deleteRolePerm(resourceId);
			}

			resourceMapper.deleteBatchById(resourceIds);
			return true;
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)

 * @see com.webside.resource.service.ResourceService#queryTreeGridListByPage(java.util.Map)

 */
	@Override
	public List<ResourceEntity> queryTreeGridListByPage(Map<String, Object> parameter) {
		return resourceMapper.queryTreeGridListByPage(parameter);
	}

}
