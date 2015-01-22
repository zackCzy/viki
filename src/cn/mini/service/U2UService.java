package cn.mini.service;

import java.util.Set;

import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;

public interface U2UService {
	public Set<UserBase> findFriends (int id)throws RuntimeException;
	public Set<UserBase> findFriends (String name)throws RuntimeException;
	public void addFriends(int id, int addid)throws RuntimeException;
	public void removeFollow(UserBase user,UserBase removeUser)throws DaoException;
	public void removeFriend(UserBase user,UserBase removeUser)throws DaoException;
}
