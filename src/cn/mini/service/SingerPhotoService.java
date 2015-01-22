package cn.mini.service;

import cn.mini.domain.SingerPhoto;

public interface SingerPhotoService {
	public SingerPhoto getSingerPhoto(int id)throws RuntimeException;
	public SingerPhoto getSingerPhoto(String name)throws RuntimeException;
}
