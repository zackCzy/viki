package cn.mini.service;

import java.util.List;

import cn.mini.domain.SearchMusic;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserSpaceMusic;
import cn.mini.domain.VikiMusic;
import cn.mini.exception.ServiceException;

public interface UserMusicService {
	public List<Object> Search(String musicName,int page,int pageSize) throws ServiceException;
	public List<SearchMusic> getUserMusic(UserBase User) throws ServiceException;
	public Long selectCount(String musicName) throws ServiceException;
	public SearchMusic selectMusic(int id) throws ServiceException;
	public SearchMusic selectMusic(String musicId) throws ServiceException;
	public void createMusic(int id,int userId) throws ServiceException;
	public void createInternationMusic(UserSpaceMusic usm,int userid) throws ServiceException;
	public void removeMusic(int id) throws ServiceException;
	public VikiMusic addVikiMusic(String musicId,int userid,String typeName) throws ServiceException;
	public List<VikiMusic> findVikiMusics(UserBase user) throws ServiceException;
	public VikiMusic findVikiMusic(int vikiId) throws ServiceException;
	public VikiMusic findVikiMusic(String vikiMusicId,int userid) throws ServiceException;
	public void removeVikiMusic(int vikiMusicId) throws ServiceException;
	public void removeVikiMusic(String vikiMusicId,int userid) throws ServiceException;
	public void removeVikiMusic(int vikiId,int userid) throws ServiceException;
}
