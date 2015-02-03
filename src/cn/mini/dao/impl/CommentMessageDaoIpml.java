package cn.mini.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.mini.dao.BaseDao;
import cn.mini.dao.CommentMessageDao;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;
@Repository("commentMessageDaoIpml")
public class CommentMessageDaoIpml extends BaseDao implements CommentMessageDao {

	@Override
	public List<?> getPageCommentMessage(UserBase user,int page, int pageSize)
			throws DaoException {
		try {
			String hql="from Comment where user=? or comUser=?";
			Query  q=getSession().createQuery(hql);
			q.setParameter(0,user);
			q.setParameter(1,user);
			q.setFirstResult((page-1)*pageSize);		
			q.setMaxResults(pageSize);
			return q.list();
		} catch (Exception e) {
			throw new DaoException("CommentMessageDao-getPageCommentMessage:"+e.getMessage(),e);
		}
	}
}
