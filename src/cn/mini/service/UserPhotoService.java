package cn.mini.service;

import java.io.InputStream;

import cn.mini.domain.UserBase;
import cn.mini.domain.UserPhoto;

public interface UserPhotoService {
	 public int saveTempUserPhoto(int id,InputStream photo)throws RuntimeException;
	 public UserPhoto getUserPhoto(int id)throws RuntimeException;
	 public UserPhoto getUserPhoto(UserBase user)throws RuntimeException;
	 public void removeUserPhoto(UserPhoto up)throws RuntimeException;
	 public int updateTempUserPhoto(int id,InputStream photo)throws RuntimeException;
	 public void saveUserPhoto(UserPhoto up,InputStream bigphoto,InputStream smallphoto)throws RuntimeException;
}
