package cn.mini.dao;

import java.util.List;

import cn.mini.domain.SearchMusic;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserSpaceMusic;
import cn.mini.exception.DaoException;

public interface UserMusicDao {
	public List<Object> Search(String musicName,int page,int pageSize) throws DaoException;
	public List<SearchMusic> getUserMusic(UserBase User) throws DaoException;
	public Long selectCount(String musicName) throws DaoException;
	public SearchMusic selectMusic(int id) throws DaoException;
	public UserSpaceMusic selectSpaceMusic(int id) throws DaoException;
	public void createMusic(UserSpaceMusic usm) throws DaoException;
	public void removeMusic(UserSpaceMusic usm) throws DaoException;
}
