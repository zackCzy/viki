package cn.mini.service;

import cn.mini.domain.SingerPhoto;
import cn.mini.exception.ServiceException;

public interface SingerPhotoService {
	public SingerPhoto getSingerPhoto(int id)throws ServiceException;
	public SingerPhoto getSingerPhoto(String name)throws ServiceException;
}
