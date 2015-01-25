package cn.mini.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.mini.dao.BaseDao;
import cn.mini.dao.UserMusicDao;
import cn.mini.domain.SearchMusic;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserSpaceMusic;
import cn.mini.domain.VikiMusic;
import cn.mini.exception.DaoException;

@Component("userMusicDaoImpl")
public class UserMusicDaoImpl extends BaseDao implements UserMusicDao {

	@Override
	public List<Object> Search(String musicName, int page, int pageSize)
			throws DaoException {
		try {
			String hql = "from SearchMusic where song like ? or singer=?";
			Query q = getSession().createQuery(hql);
			q.setString(0, "%" + musicName + "%");
			q.setString(1, musicName);
			q.setFirstResult((page - 1) * pageSize);
			q.setMaxResults(pageSize);	
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserMusicDao-Search:"+e.getMessage(),e);
		}
	}

	@Override
	public List<SearchMusic> getUserMusic(UserBase User) throws DaoException {
		
		return null;
	}

	@Override
	public Long selectCount(String musicName) throws DaoException {
		try {
			String hql = "SELECT COUNT(*) from SearchMusic where song like ? or singer=?";
			Query q = getSession().createQuery(hql);
			q.setString(0, "%" + musicName + "%");
			q.setString(1, musicName);	
			return (Long) q.list().get(0);
		} catch (Exception e) {
			throw new DaoException("UserMusicDao-selectCount:"+e.getMessage(),e);
		}
	}

	@Override
	public SearchMusic selectMusic(int id) throws DaoException {
		try {
			return (SearchMusic) getSession().get(SearchMusic.class, id);
		} catch (Exception e) {
			throw new DaoException("UserMusicDao-selectMusic:"+e.getMessage(),e);
		}
	}

	@Override
	public void createMusic(UserSpaceMusic usm) throws DaoException {
		try {
			getSession().save(usm);
		} catch (Exception e) {
			throw new DaoException("UserMusicDao-createMusic:"+e.getMessage(),e);
		}	
	}

	@Override
	public void removeMusic(UserSpaceMusic usm) throws DaoException {
		try {
			getSession().delete(usm);
		} catch (Exception e) {
			throw new DaoException("UserMusicDao-removeMusic:"+e.getMessage(),e);
		}	
	}

	@Override
	public UserSpaceMusic selectSpaceMusic(int id) throws DaoException {
		try {
			return (UserSpaceMusic) getSession().get(UserSpaceMusic.class, id);
		} catch (Exception e) {
			throw new DaoException("UserMusicDao-selectSpaceMusic:"+e.getMessage(),e);
		}
	}

	@Override
	public VikiMusic addVikiMusic(VikiMusic vm) throws DaoException {
		try {	
			getSession().save(vm);
			return vm;
		} catch (Exception e) {
			throw new DaoException("UserMusicDao-addVikiMusic:"+e.getMessage(),e);
		}
	}

	@Override
	public List<VikiMusic> findVikiMusics(UserBase user) throws DaoException {
		try {	
			String hql="from VikiMusic where user =?";
			Query q = getSession().createQuery(hql);
			q.setParameter(0, user);
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserMusicDao-findVikiMusics:"+e.getMessage(),e);
		}
		
	}

	@Override
	public void removeVikiMusic(VikiMusic vm) throws DaoException {
		try {	
			getSession().delete(vm);
		} catch (Exception e) {
			throw new DaoException("UserMusicDao-removeVikiMusic:"+e.getMessage(),e);
		}
	}

	@Override
	public VikiMusic findVikiMusic(int id) throws DaoException {
		try {
			return (VikiMusic) getSession().get(VikiMusic.class, id);
		} catch (Exception e) {
			throw new DaoException("UserMusicDao-findVikiMusic:"+e.getMessage(),e);
		}
	}

	@Override
	public SearchMusic selectMusic(String musicId) throws DaoException {
		try {
			String hql="from SearchMusic where musicId = ?";
			Query q=getSession().createQuery(hql);
			q.setString(0, musicId);
			return (SearchMusic) q.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("UserMusicDao-findVikiMusic:"+e.getMessage(),e);
		}
	}

	@Override
	public VikiMusic findVikiMusic(String musicId, UserBase user)
			throws DaoException {
		try {
			String hql="from VikiMusic where musicId = ? and  user=?";
			Query q=getSession().createQuery(hql);
			q.setString(0, musicId);
			q.setParameter(1, user);
			return (VikiMusic) q.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("UserMusicDao-findVikiMusic:"+e.getMessage(),e);
		}
	}
}
