package cn.mini.service;

import java.io.InputStream;

import cn.mini.domain.UserBase;
import cn.mini.domain.UserPhoto;
import cn.mini.exception.ServiceException;

public interface UserPhotoService {
	 public int saveTempUserPhoto(int id,InputStream photo)throws ServiceException;
	 public UserPhoto getUserPhoto(int id)throws ServiceException;
	 public UserPhoto getUserPhoto(UserBase user)throws ServiceException;
	 public void removeUserPhoto(UserPhoto up)throws ServiceException;
	 public int updateTempUserPhoto(int id,InputStream photo)throws ServiceException;
	 public void saveUserPhoto(UserPhoto up,InputStream bigphoto,InputStream smallphoto)throws ServiceException;
}
