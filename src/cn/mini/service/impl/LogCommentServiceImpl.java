package cn.mini.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.LogCommentDao;
import cn.mini.dao.UserLogDao;
import cn.mini.domain.Comment;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.service.LogCommentService;

@Service("logCommentServiceImpl")
public class LogCommentServiceImpl implements LogCommentService {
	@Resource(name = "userLogDaoImpl")
	private UserLogDao uld = null;
	@Resource(name="logCommentDaoImpl")
	private LogCommentDao lcd=null;
	@Override
	public void saveLogCom(int id,Comment c) throws RuntimeException {
		UserLog ul=uld.getUserLog(id);
		ul.setCommentNum(ul.getCommentNum()+1);
		uld.updateLog(ul);
		c.setUserlog(ul);
		c.setUser(c.getUserlog().getUser());
		lcd.saveLogCom(c);
	}

	@Override
	public void removeLogCom(int id,UserBase user) throws RuntimeException {
		lcd.removeLogCom(id,user);
	}

	@Override
	public List<Comment> getLogCom(int id) throws RuntimeException {
		return lcd.getLogCom(id);
	}

	@Override
	public Comment getComment(int id) throws RuntimeException {
		return lcd.getComment(id);
	}

}
