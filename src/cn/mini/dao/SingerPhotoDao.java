package cn.mini.dao;

import cn.mini.domain.SingerPhoto;
import cn.mini.exception.DaoException;

public interface SingerPhotoDao {
	public SingerPhoto getSingerPhoto(int id)throws DaoException;
	public SingerPhoto getSingerPhoto(String name)throws DaoException;
}
