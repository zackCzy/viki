package cn.mini.dao;

import java.util.List;

import cn.mini.domain.SystemMessage;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;

public interface SystemMessageDao {
	public void addSystemMessage(SystemMessage sm)throws DaoException;
	public List<SystemMessage> findSystemMessage(UserBase user)throws DaoException;
	public SystemMessage findSystemMessage(int id)throws DaoException;
}
