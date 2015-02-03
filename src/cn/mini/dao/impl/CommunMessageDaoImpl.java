package cn.mini.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.mini.dao.BaseDao;
import cn.mini.dao.CommunMessageDao;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;
@Repository("communMessageDaoImpl")
public class CommunMessageDaoImpl extends BaseDao implements CommunMessageDao  {

	@Override
	public List<?> getPageCommunMessage(UserBase user, int page, int pageSize)
			throws DaoException {
		try {
			String hql="from ReviewewComment where user=?";
			Query  q=getSession().createQuery(hql);
			q.setParameter(0,user);
			q.setFirstResult((page-1)*pageSize);		
			q.setMaxResults(pageSize);
			return q.list();
		} catch (Exception e) {
			throw new DaoException("CommunMessageDao-getPageCommunMessage:"+e.getMessage(),e);
		}
	}

}
