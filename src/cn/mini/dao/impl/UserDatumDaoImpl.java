package cn.mini.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.mini.dao.BaseDao;
import cn.mini.dao.UserDatumDao;
import cn.mini.domain.SpaceDatum;
import cn.mini.domain.UnitDatum;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserBaseDails;
import cn.mini.domain.UserBaseDatum;
import cn.mini.exception.DaoException;

@Repository("userDatumDaoImpl")
public class UserDatumDaoImpl extends BaseDao implements UserDatumDao {

	@Override
	public SpaceDatum getSpaceDatum(UserBase user) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(SpaceDatum.class);
			c.add(Restrictions.eq("user",user));		
			return (SpaceDatum) c.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-getSpaceDatum:"+e.getMessage(),e);
		}
	}

	@Override
	public UserBaseDails getUserBaseDails(UserBase user) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(UserBaseDails.class);
			c.add(Restrictions.eq("user",user));		
			return (UserBaseDails) c.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-getSpaceDatum:"+e.getMessage(),e);
		}
	}

	@Override
	public UserBaseDatum getUserBaseDatum(UserBase user) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(UserBaseDatum.class);
			c.add(Restrictions.eq("user",user));		
			return (UserBaseDatum) c.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-getSpaceDatum:"+e.getMessage(),e);
		}
	}

	@Override
	public UnitDatum getUnitDatum(UserBase user) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(UnitDatum.class);
			c.add(Restrictions.eq("user",user));		
			return (UnitDatum) c.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-getSpaceDatum:"+e.getMessage(),e);
		}
	}

	@Override
	public void saveSpaceDatum(SpaceDatum sd) throws DaoException {
		try {
			getSession().save(sd);
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-getSpaceDatum:"+e.getMessage(),e);
		}	
	}

	@Override
	public void saveUserBaseDails(UserBaseDails ubd) throws DaoException {
		try {
			getSession().save(ubd);
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-getSpaceDatum:"+e.getMessage(),e);
		}	
	}

	@Override
	public void saveUserBaseDatum(UserBaseDatum ubd) throws DaoException {
		try {
			getSession().save(ubd);
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-saveUserBaseDatum:"+e.getMessage(),e);
		}	
		
	}

	@Override
	public void saveUnitDatum(UnitDatum ud) throws DaoException {
		try {
			getSession().save(ud);
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-getSpaceDatum:"+e.getMessage(),e);
		}	
		
	}

	@Override
	public void updateSpaceDatum(SpaceDatum sd) throws DaoException {
		try {
			getSession().update(sd);
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-updateSpaceDatum:"+e.getMessage(),e);
		}			
	}

	@Override
	public void updateUserBaseDails(UserBaseDails ubd) throws DaoException {
		try {
			getSession().update(ubd);
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-updateUserBaseDails:"+e.getMessage(),e);
		}		
	}

	@Override
	public void updateUserBaseDatum(UserBaseDatum ubd) throws DaoException {
		try {
			getSession().update(ubd);
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-updateUserBaseDatum:"+e.getMessage(),e);
		}			
	}

	@Override
	public void updateUnitDatum(UnitDatum ud) throws DaoException {
		try {
			getSession().update(ud);
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-updateUnitDatum:"+e.getMessage(),e);
		}				
	}

	@Override
	public UserBase exitsEmail(String email) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(UserBase.class);
			c.add(Restrictions.eq("email", email));
			return ((UserBase)c.uniqueResult());
		} catch (Exception e) {
			throw new DaoException("UserDatumDao-exitsEmail:"+e.getMessage(),e);
		}	
	}

}
