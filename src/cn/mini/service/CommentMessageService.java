package cn.mini.service;

import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.exception.ServiceException;

public interface CommentMessageService {
	public List<?> getPageCommentMessage(UserBase user,int page,int pageSize)throws ServiceException;
}
