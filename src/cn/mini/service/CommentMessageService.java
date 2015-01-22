package cn.mini.service;

import java.util.List;

import cn.mini.domain.UserBase;

public interface CommentMessageService {
	public List<?> getPageCommentMessage(UserBase user,int page,int pageSize)throws RuntimeException;
}
