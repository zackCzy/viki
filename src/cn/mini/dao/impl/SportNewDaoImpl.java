package cn.mini.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.mini.dao.BaseDao;
import cn.mini.dao.SportNewDao;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.exception.DaoException;
@Repository("sportNewDaoImpl")
public class SportNewDaoImpl extends BaseDao implements SportNewDao {

	@Override
	public List<UserLog> getSpacsSportNewDao(List<UserBase> parm,int page,int pageSize) throws DaoException {
		try {
			String hql="from  UserLog as user_log  where draft=? and rubbish=? and ";
			String temp="";
			for (UserBase u:parm) {
				temp+="user=? or ";
			}
			hql+=temp.substring(0, temp.length()-3);
			hql+="order by modifyDate desc";
			Query hqlSetem=getSession().createQuery(hql);
			hqlSetem.setBoolean(0, false);
			hqlSetem.setBoolean(1, false);
			for (int i=0;i<parm.size();i++) {
				hqlSetem.setParameter(i+2, parm.get(i))	;
			}
			hqlSetem.setFirstResult((page - 1) * pageSize);
			hqlSetem.setMaxResults(pageSize);	
			return hqlSetem.list();
		} catch (Exception e) {
			throw new DaoException("SportNewDao-getSpacsSportNewDao:"+e.getMessage(),e);
		}
	}

}
