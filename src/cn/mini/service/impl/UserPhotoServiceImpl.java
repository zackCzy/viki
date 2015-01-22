package cn.mini.service.impl;

import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.UserPhotoDao;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserPhoto;
import cn.mini.exception.DaoException;
import cn.mini.service.UserPhotoService;
import cn.mini.service.UserService;

@Service("userPhotoServiceImpl")
public class UserPhotoServiceImpl implements UserPhotoService {

	@Resource(name = "userPhotoDaoImpl")
	private UserPhotoDao upd = null;
	@Resource(name = "userServiceImpl")
	private UserService us = null;

	@Override
	public UserPhoto getUserPhoto(int id) throws DaoException {
		return upd.getUserPhoto(id);
	}

	@Override
	public void removeUserPhoto(UserPhoto up) throws DaoException {
		upd.removeUserPhoto(up);

	}

	@Override
	public int updateTempUserPhoto(int id, InputStream photo) throws DaoException {
		UserBase user=new UserBase();
		user.setId(id);
		UserPhoto up=getUserPhoto(user);
		return upd.updateTempUserPhoto(up, photo);
	}

	@Override
	public int saveTempUserPhoto(int id, InputStream photo) throws DaoException {
		UserBase user=new UserBase();
		user.setId(id);
		return upd.saveTempUserPhoto(user, photo);
	}

	@Override
	public void saveUserPhoto(UserPhoto up, InputStream bigphoto,
			InputStream smallphoto) throws DaoException {
		upd.saveUserPhoto(up, bigphoto, smallphoto);
	}

	@Override
	public UserPhoto getUserPhoto(UserBase user) throws RuntimeException {
		return upd.getUserPhoto(user);
	}

}
