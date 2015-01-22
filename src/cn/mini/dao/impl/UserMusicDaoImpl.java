package cn.mini.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.mini.dao.BaseDao;
import cn.mini.dao.UserMusicDao;
import cn.mini.domain.SearchMusic;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserSpaceMusic;
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
			throw new DaoException("UserMusicDao:"+e.getMessage(),e);
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
			throw new DaoException("UserMusicDao:"+e.getMessage(),e);
		}
	}

	@Override
	public SearchMusic selectMusic(int id) throws DaoException {
		try {
			return (SearchMusic) getSession().get(SearchMusic.class, id);
		} catch (Exception e) {
			throw new DaoException("UserMusicDao:"+e.getMessage(),e);
		}
	}

	@Override
	public void createMusic(UserSpaceMusic usm) throws DaoException {
		try {
			getSession().save(usm);
		} catch (Exception e) {
			throw new DaoException("UserMusicDao:"+e.getMessage(),e);
		}	
	}

	@Override
	public void removeMusic(UserSpaceMusic usm) throws DaoException {
		try {
			getSession().delete(usm);
		} catch (Exception e) {
			throw new DaoException("UserMusicDao:"+e.getMessage(),e);
		}	
	}

	@Override
	public UserSpaceMusic selectSpaceMusic(int id) throws DaoException {
		try {
			return (UserSpaceMusic) getSession().get(UserSpaceMusic.class, id);
		} catch (Exception e) {
			throw new DaoException("UserMusicDao:"+e.getMessage(),e);
		}
	}

}
