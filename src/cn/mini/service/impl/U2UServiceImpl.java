package cn.mini.service.impl;

import java.sql.Timestamp;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.U2UDao;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;
import cn.mini.service.SystemMessageService;
import cn.mini.service.U2UService;
import cn.mini.service.UserService;
@Service("u2UServiceImpl")
public class U2UServiceImpl implements U2UService {

	@Resource(name="u2UDaoImpl")
	U2UDao u2d=null;
	@Resource(name="userServiceImpl")
	UserService us=null;
	@Resource(name="systemMessageServiceImpl")
	SystemMessageService sms=null;
	@Override
	public Set<UserBase> findFriends(int id) throws RuntimeException {	
		return u2d.findFriends(id);
	}

	@Override
	public Set<UserBase> findFriends(String name) throws RuntimeException {
		return u2d.findFriends(name);
	}

	@Override
	public void addFriends(int id, int addid) throws RuntimeException {
			UserBase user=us.findUserService(id);
			UserBase adduser=us.findUserService(addid);
			Set<UserBase> followUsers=user.getFollowUsers();
			followUsers.add(adduser);
			user.setFollowUsers(followUsers);
			u2d.addFriends(user);
			sms.addSystemMessage(user.getName(), "已经成为您的粉丝了", new Timestamp(new java.util.Date().getTime()), adduser);		
			sms.addSystemMessage(adduser.getName(), "您关注了"+adduser.getName(), new Timestamp(new java.util.Date().getTime()), user);		
	}

	@Override
	public void removeFriend(UserBase user, UserBase removeUser)
			throws DaoException {
		Set<UserBase> users=user.getFansUsers();	
		users.remove(removeUser);
		u2d.removeFriend(user);	
	}
	@Override
	public void removeFollow(UserBase user, UserBase removeUser)
			throws DaoException {
		Set<UserBase> users=user.getFollowUsers();	
		users.remove(removeUser);
		u2d.removeFollow(user);	
	}
}
