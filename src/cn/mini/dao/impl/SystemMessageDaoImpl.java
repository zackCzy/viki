package cn.mini.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.mini.dao.BaseDao;
import cn.mini.dao.SystemMessageDao;
import cn.mini.domain.SystemMessage;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;

@Repository("systemMessageDaoImpl")
public class SystemMessageDaoImpl extends BaseDao implements SystemMessageDao {

	@Override
	public void addSystemMessage(SystemMessage sm) throws DaoException {
		try {
			getSession().save(sm);
		} catch (Exception e) {
			throw new DaoException("SystemMessageDao-addSystemMessage:"+e.getMessage(),e);
		}
	}

	@Override
	public List<SystemMessage> findSystemMessage(UserBase user)
			throws DaoException {
		try {
			Criteria c=getSession().createCriteria(SystemMessage.class);
			c.add(Restrictions.eq("user", user));
			return c.list();
		} catch (Exception e) {
			throw new DaoException("SystemMessageDao-findSystemMessage:"+e.getMessage(),e);
		}
	}

	@Override
	public SystemMessage findSystemMessage(int id) throws DaoException {
		try {
			return (SystemMessage) getSession().get(SystemMessage.class, id);
		} catch (Exception e) {
			throw new DaoException("SystemMessageDao-findSystemMessage:"+e.getMessage(),e);
		}
	}

}
