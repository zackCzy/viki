package cn.mini.service;

import java.util.Set;

import cn.mini.domain.UserBase;
import cn.mini.exception.ServiceException;

public interface U2UService {
	public Set<UserBase> findFriends (int id)throws ServiceException;
	public Set<UserBase> findFriends (String name)throws ServiceException;
	public void addFriends(int id, int addid)throws ServiceException;
	public void removeFollow(UserBase user,UserBase removeUser)throws ServiceException;
	public void removeFriend(UserBase user,UserBase removeUser)throws ServiceException;
}
