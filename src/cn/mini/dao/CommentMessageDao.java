package cn.mini.dao;

import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;

public interface CommentMessageDao {
	public List<?> getPageCommentMessage(UserBase user,int page,int pageSize)throws DaoException;
}
