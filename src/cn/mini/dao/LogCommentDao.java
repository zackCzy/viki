package cn.mini.dao;

import java.util.List;

import cn.mini.domain.Comment;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;

public interface LogCommentDao {
	public void saveLogCom(Comment com) throws DaoException;
	public void removeLogCom(int id,UserBase user) throws DaoException;
	public List<Comment> getLogCom(int id)throws DaoException;
	public Comment getComment(int id)throws DaoException;
}
