package cn.mini.dao;

import java.io.InputStream;

import cn.mini.domain.UserBase;
import cn.mini.domain.UserPhoto;
import cn.mini.exception.DaoException;

public interface UserPhotoDao {
	 public int saveTempUserPhoto(UserBase user,InputStream photo)throws DaoException;
	 public UserPhoto getUserPhoto(int id)throws DaoException;
	 public UserPhoto getUserPhoto(UserBase user)throws DaoException;
	 public void removeUserPhoto(UserPhoto up)throws DaoException;
	 public int updateTempUserPhoto(UserPhoto up,InputStream photo)throws DaoException;
	 public void saveUserPhoto(UserPhoto up,InputStream bigphoto,InputStream smallphoto)throws DaoException;
}
