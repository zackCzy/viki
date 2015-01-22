package cn.mini.service;

import java.util.List;

import cn.mini.domain.SearchMusic;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserSpaceMusic;
import cn.mini.exception.DaoException;

public interface UserMusicService {
	public List<Object> Search(String musicName,int page,int pageSize) throws RuntimeException;
	public List<SearchMusic> getUserMusic(UserBase User) throws RuntimeException;
	public Long selectCount(String musicName) throws RuntimeException;
	public SearchMusic selectMusic(int id) throws RuntimeException;
	public void createMusic(int id,int userId) throws RuntimeException;
	public void createInternationMusic(UserSpaceMusic usm,int userid) throws RuntimeException;
	public void removeMusic(int id) throws DaoException;
}
