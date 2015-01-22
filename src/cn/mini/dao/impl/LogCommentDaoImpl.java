package cn.mini.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.mini.dao.BaseDao;
import cn.mini.dao.LogCommentDao;
import cn.mini.domain.Comment;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.exception.DaoException;


@Repository("logCommentDaoImpl")
public class LogCommentDaoImpl extends BaseDao implements LogCommentDao {	
	@Override
	public void saveLogCom(Comment com) throws DaoException {
		try {
			getSession().save(com);
		} catch (Exception e) {
			throw new DaoException("LogCommentDao:"+e.getMessage(), e);
		}
	}

	@Override
	public void removeLogCom(int id,UserBase user) throws DaoException {
		try {		
			Comment c=(Comment) getSession().get(Comment.class,id);
			UserLog ul=c.getUserlog();
			if(c.getUser().getId()!=user.getId()){
				throw new DaoException("用户权限不够");
			}
			ul.setCommentNum(ul.getCommentNum()-1);
			getSession().update(ul);
			getSession().delete(c);
		} catch (Exception e) {
			throw new DaoException("LogCommentDao:"+e.getMessage(), e);
		}
	}

	@Override
	public List<Comment> getLogCom(int id) throws DaoException {
		try {
			Query q = getSession()
			.createQuery("from  Comment as comment  where userlog=?  order by date desc");
			UserLog ul=new UserLog();
			ul.setId(id);
			q.setParameter(0, ul);
			return q.list();
		} catch (Exception e) {
			throw new DaoException("LogCommentDao:"+e.getMessage(), e);
		}
	}

	@Override
	public Comment getComment(int id) throws DaoException {
		try {
			return (Comment) getSession().get(Comment.class, id);
		} catch (Exception e) {
			throw new DaoException("LogCommentDao:"+e.getMessage(), e);
		}
	}

}
