package cn.mini.dao;

import java.util.Set;

import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;

public interface U2UDao {
	public Set<UserBase> findFriends (int id)throws DaoException;
	public Set<UserBase> findFriends (String name)throws DaoException;
	public void addFriends(UserBase user)throws DaoException;
	public void removeFollow(UserBase user)throws DaoException;
	public void removeFriend(UserBase user)throws DaoException;
}
