package cn.mini.dao;

import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;

public interface UserDao{
	public void registerUser(UserBase um) throws DaoException;

	public void updateUser(Object bean) throws DaoException;

	public void removeUser(UserBase user) throws DaoException;

	public int sginUser(String name, String password) throws DaoException;

	public int selectUser(String name) throws DaoException;

	public  UserBase findUser(int id) throws DaoException;
	
	public  UserBase findUser(String name) throws DaoException;

	public  List<UserBase> findUserList(String name) throws DaoException;

	public  List<UserBase> findUserAll(int page,int pageSize) throws DaoException;


}
