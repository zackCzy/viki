package cn.mini.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.SystemMessageDao;
import cn.mini.domain.SystemMessage;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;
import cn.mini.service.SystemMessageService;
@Service("systemMessageServiceImpl")
public class SystemMessageServiceImpl implements SystemMessageService {

	@Resource(name="systemMessageDaoImpl")
	SystemMessageDao smd=null;
	@Override
	public void addSystemMessage(String messageTitle, String messageContent,
			Timestamp date, UserBase user) throws DaoException {
		SystemMessage sm=new SystemMessage();
		sm.setDate(date);
		sm.setMessageContent(messageContent);
		sm.setMessageTitle(messageTitle);
		sm.setUser(user);
		smd.addSystemMessage(sm);
	}

	@Override
	public List<SystemMessage> findSystemMessage(UserBase user)
			throws DaoException {
		
		return null;
	}

	@Override
	public SystemMessage findSystemMessage(int id) throws DaoException {
		
		return null;
	}

}
