package cn.mini.service;

import java.util.List;

import cn.mini.domain.Comment;
import cn.mini.domain.UserBase;
import cn.mini.exception.ServiceException;


public interface LogCommentService {
	public void saveLogCom(int id,Comment c) throws ServiceException;
	public void removeLogCom(int id,UserBase user) throws ServiceException;
	public List<Comment> getLogCom(int id)throws ServiceException;
	public Comment getComment(int id)throws ServiceException;
}
