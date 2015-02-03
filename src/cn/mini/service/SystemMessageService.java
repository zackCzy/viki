package cn.mini.service;

import java.util.List;

import cn.mini.domain.SystemMessage;
import cn.mini.domain.UserBase;
import cn.mini.exception.ServiceException;

public interface SystemMessageService {
	public void addSystemMessage(String messageTitle,String messageContent,java.sql.Timestamp date,UserBase user)throws ServiceException;
	public List<SystemMessage> findSystemMessage(UserBase user)throws ServiceException;
	public SystemMessage findSystemMessage(int id)throws ServiceException;
}
