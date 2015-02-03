package cn.mini.service;

import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.exception.ServiceException;

public interface UserService {
	public void saveUser(UserBase um) throws ServiceException;

	public void updateUser(UserBase bean) throws ServiceException;

	public int sginUserService(String name, String password)
			throws ServiceException;

	public int selectUserService(String name) throws ServiceException;

	public UserBase findUserService(int id) throws ServiceException;

	public UserBase findUser(String name) throws ServiceException;

	public void removeUser(UserBase user) throws ServiceException;
	
	public void removeNoCheckUser(String name) throws ServiceException;

	public List<UserBase> findUserAll(int page, int pageSize)
			throws ServiceException;
}
