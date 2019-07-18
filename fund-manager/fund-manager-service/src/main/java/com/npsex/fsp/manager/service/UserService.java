package com.npsex.fsp.manager.service;

import java.util.List;
import java.util.Map;

import com.npsex.fsp.commons.core.exception.ServiceException;
import com.npsex.fsp.manager.pojo.UserEntity;

public interface UserService {

	public List<UserEntity> queryListByPage(Map<String, Object> parameter);

	public UserEntity findByName(String accountName);
	
	public int insert(UserEntity userEntity, String password);
	
	public UserEntity findById(Long id);

	public int update(UserEntity userEntity);
	
	public int updateOnly(UserEntity userEntity) throws ServiceException;
	
	public int updatePassword(UserEntity userEntity, String password) throws ServiceException;
    
    public int deleteBatchById(List<Long> userIds);
    
}