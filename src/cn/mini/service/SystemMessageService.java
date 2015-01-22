package cn.mini.service;

import java.util.List;

import cn.mini.domain.SystemMessage;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;

public interface SystemMessageService {
	public void addSystemMessage(String messageTitle,String messageContent,java.sql.Timestamp date,UserBase user)throws DaoException;
	public List<SystemMessage> findSystemMessage(UserBase user)throws DaoException;
	public SystemMessage findSystemMessage(int id)throws DaoException;
}
