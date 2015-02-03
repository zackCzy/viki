package cn.mini.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.mini.dao.BaseDao;
import cn.mini.dao.SingerPhotoDao;
import cn.mini.domain.SingerPhoto;
import cn.mini.exception.DaoException;


@Repository("singerPhotoDaoImpl")
public class SingerPhotoDaoImpl extends BaseDao implements SingerPhotoDao {

	@Override
	public SingerPhoto getSingerPhoto(int id) throws DaoException {
		try {
			return (SingerPhoto) getSession().get(SingerPhoto.class, id);
		} catch (Exception e) {
			throw new DaoException("SingerPhotoDao-getSingerPhoto"+e.getMessage(),e);
		}
	}

	@Override
	public SingerPhoto getSingerPhoto(String name) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(SingerPhoto.class);
			c.add(Restrictions.eq("singerName", name));		
			return (SingerPhoto) c.list().get(0);
		} catch (Exception e) {
			throw new DaoException("SingerPhotoDao-getSingerPhoto"+e.getMessage(),e);
		}
	}

}
