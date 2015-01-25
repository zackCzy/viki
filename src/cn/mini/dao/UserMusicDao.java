package cn.mini.dao;

import java.util.List;

import cn.mini.domain.SearchMusic;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserSpaceMusic;
import cn.mini.domain.VikiMusic;
import cn.mini.exception.DaoException;

public interface UserMusicDao {
	public List<Object> Search(String musicName,int page,int pageSize) throws DaoException;
	public List<SearchMusic> getUserMusic(UserBase User) throws DaoException;
	public Long selectCount(String musicName) throws DaoException;
	public SearchMusic selectMusic(int id) throws DaoException;
	public SearchMusic selectMusic(String musicId) throws DaoException;
	public UserSpaceMusic selectSpaceMusic(int id) throws DaoException;
	public void createMusic(UserSpaceMusic usm) throws DaoException;
	public void removeMusic(UserSpaceMusic usm) throws DaoException;
	public VikiMusic addVikiMusic(VikiMusic vm) throws DaoException;
	public List<VikiMusic> findVikiMusics(UserBase user) throws DaoException;
	public VikiMusic findVikiMusic(String musicId,UserBase user) throws DaoException;
	public VikiMusic findVikiMusic(int id) throws DaoException;
	public void removeVikiMusic(VikiMusic vm) throws DaoException;
}
