package cn.mini.dao.impl;

import java.io.InputStream;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.mini.dao.BaseDao;
import cn.mini.dao.UserPhotoDao;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserPhoto;
import cn.mini.exception.DaoException;

@Repository("userPhotoDaoImpl")
public class UserPhotoDaoImpl extends BaseDao implements UserPhotoDao {

	@Override
	public int saveTempUserPhoto(UserBase user,InputStream photo) throws DaoException {
		try {
			UserPhoto up=new UserPhoto();
			up.setUser(user);
			up.setTempPhoto(getSession().getLobHelper().createBlob(photo,photo.available()));
			getSession().save(up);	
			return up.getId();
		} catch (Exception e) {		
			throw new RuntimeException("UserPhotoDao:"+e.getMessage(),e);
		}
	}

	@Override
	public UserPhoto getUserPhoto(int id) throws DaoException {
		try {		
			return  (UserPhoto) getSession().get(UserPhoto.class, id);
		} catch (Exception e) {
			throw new RuntimeException("UserPhotoDao:"+e.getMessage(),e);
		}
	}
	@Override
	public void removeUserPhoto(UserPhoto up) throws DaoException {
		try {
			getSession().delete(up);
		} catch (Exception e) {
			throw new RuntimeException("UserPhotoDao:"+e.getMessage(),e);
		}

	}

	@Override
	public int updateTempUserPhoto(UserPhoto up,InputStream bigphoto) throws DaoException {
		try {			
			up.setTempPhoto(getSession().getLobHelper().createBlob(bigphoto,bigphoto.available()));		
			getSession().update(up);
			return up.getId();
		} catch (Exception e) {
			throw new RuntimeException("UserPhotoDao:"+e.getMessage(),e);
		}
	}

	@Override
	public void saveUserPhoto(UserPhoto up, InputStream bigphoto,
			InputStream smallphoto) throws DaoException {
		try {
			up.setBigPhoto(getSession().getLobHelper().createBlob(bigphoto,bigphoto.available()));		
			up.setSmallPhoto(getSession().getLobHelper().createBlob(smallphoto,smallphoto.available()));		
			up.setSourcePhoto(up.getTempPhoto());
			getSession().update(up);
		} catch (Exception e) {
			throw new RuntimeException("UserPhotoDao:"+e.getMessage(),e);
		}
	}

	@Override
	public UserPhoto getUserPhoto(UserBase user) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(UserPhoto.class);
			c.add(Restrictions.eq("user",user));		
			return (UserPhoto) c.uniqueResult();
		} catch (Exception e) {
			throw new RuntimeException("UserPhotoDao:"+e.getMessage(),e);
		}
	}
}
