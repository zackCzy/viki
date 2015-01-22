package cn.mini.service;

import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;

public interface UserService {
	public void saveUser(UserBase um) throws RuntimeException;

	public void updateUser(UserBase bean) throws RuntimeException;

	public int sginUserService(String name, String password)
			throws RuntimeException;

	public int selectUserService(String name) throws RuntimeException;

	public UserBase findUserService(int id) throws RuntimeException;

	public UserBase findUser(String name) throws RuntimeException;

	public void removeUser(UserBase user) throws RuntimeException;
	
	public void removeNoCheckUser(String name) throws RuntimeException;

	public List<UserBase> findUserAll(int page, int pageSize)
			throws DaoException;
}
