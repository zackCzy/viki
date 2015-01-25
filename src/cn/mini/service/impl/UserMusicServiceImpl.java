package cn.mini.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.UserDao;
import cn.mini.dao.UserMusicDao;
import cn.mini.domain.PageMusic;
import cn.mini.domain.SearchMusic;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserSpaceMusic;
import cn.mini.domain.VikiMusic;
import cn.mini.exception.DaoException;
import cn.mini.exception.ServiceException;
import cn.mini.service.UserMusicService;

@Service("userMusicServiceImpl")
public class UserMusicServiceImpl implements UserMusicService {
	@Resource(name = "userMusicDaoImpl")
	private UserMusicDao umd = null;
	@Resource(name = "userDaoImpl")
	private UserDao ud = null;

	@Override
	public List<Object> Search(String musicName, int page, int pageSize)
			throws ServiceException {
		try {
			List<Object> list = umd.Search(musicName, page, pageSize);
			Long pageCount = umd.selectCount(musicName);
			PageMusic p = new PageMusic();
			p.setPagecount(pageCount);
			p.setCurrentpage(page);
			list.add(p);
			return list;
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-Search:", e);
		}
	}

	@Override
	public List<SearchMusic> getUserMusic(UserBase User)
			throws RuntimeException {
		try {
			return umd.getUserMusic(User);
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-getUserMusic:", e);
		}
	}

	@Override
	public Long selectCount(String musicName) throws ServiceException {
		try {
			return umd.selectCount(musicName);
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-selectCount:", e);
		}
	}

	@Override
	public SearchMusic selectMusic(int id) throws ServiceException {
		try {
			return umd.selectMusic(id);
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-selectMusic:", e);
		}
	}
	@Override
	public SearchMusic selectMusic(String musicId) throws ServiceException {
		try {
			return umd.selectMusic(musicId);
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-selectMusic:", e);
		}
	}
	@Override
	public void createMusic(int id, int userId) throws ServiceException {
		try {
			SearchMusic sm = umd.selectMusic(id);
			UserBase user = ud.findUser(userId);
			UserSpaceMusic usm = new UserSpaceMusic();
			org.apache.commons.beanutils.BeanUtils.copyProperties(usm, sm);
			usm.setUser(user);
			umd.createMusic(usm);
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-createMusic:", e);
		} catch (IllegalAccessException e) {
			throw new ServiceException(
					"UserMuseicService-createMusic-IllegalAccessException:", e);
		} catch (InvocationTargetException e) {
			throw new ServiceException(
					"UserMuseicService-createMusic-InvocationTargetException:",
					e);
		}
	}

	@Override
	public void removeMusic(int id) throws ServiceException {
		try {
			UserSpaceMusic usm = umd.selectSpaceMusic(id);
			umd.removeMusic(usm);
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-removeMusic:", e);
		}
	}

	@Override
	public void createInternationMusic(UserSpaceMusic usm, int userid)
			throws RuntimeException {
		try {
			UserBase user = ud.findUser(userid);
			usm.setUser(user);
			umd.createMusic(usm);
		} catch (DaoException e) {
			throw new ServiceException(
					"UserMuseicService-createInternationMusic:", e);
		}
	}

	@Override
	public VikiMusic addVikiMusic(String musicId, int userid,String typeName) throws ServiceException {
		try {
			UserBase user = ud.findUser(userid);
			SearchMusic smusic=selectMusic(musicId);
			VikiMusic vm=new VikiMusic();		
			org.apache.commons.beanutils.BeanUtils.copyProperties(vm, smusic);
			vm.setUser(user);
			vm.setTypeName(typeName);
			umd.addVikiMusic(vm);
			return vm;
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-addVikiMusic:", e);
		} catch (IllegalAccessException e) {
			throw new ServiceException("UserMuseicService-addVikiMusic-IllegalAccessException:", e);
		} catch (InvocationTargetException e) {
			throw new ServiceException("UserMuseicService-addVikiMusic-InvocationTargetException:", e);
		}
	}

	@Override
	public List<VikiMusic> findVikiMusics(UserBase user)
			throws ServiceException {
		try {
			return umd.findVikiMusics(user);
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-findVikiMusics:", e);
		}

	}

	@Override
	public VikiMusic findVikiMusic(int vikiId) throws ServiceException {
		try {
			return umd.findVikiMusic(vikiId);
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-findVikiMusic:", e);
		}

	}

	@Override
	public void removeVikiMusic(int vikiMusicId) throws ServiceException {
		try {
			VikiMusic vm=umd.findVikiMusic(vikiMusicId);
			umd.removeVikiMusic(vm);
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-removeVikiMusic:", e);
		}
		
	}
	@Override
	public void removeVikiMusic(String vikiMusicId,int userid)
			throws ServiceException {
		try {
			UserBase user=ud.findUser(userid);
			VikiMusic vm=umd.findVikiMusic(vikiMusicId, user);
			umd.removeVikiMusic(vm);
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-removeVikiMusic:", e);
		}
	}
	@Override
	public void removeVikiMusic(int vikiId,int userid)
			throws ServiceException {
		try {
			UserBase user=ud.findUser(userid);
			VikiMusic vm=umd.findVikiMusic(vikiId);
			if(user.getId()==vm.getUser().getId()){
				umd.removeVikiMusic(vm);
			}
		} catch (DaoException e) {
			throw new ServiceException("UserMuseicService-removeVikiMusic:", e);
		}
	}

	@Override
	public VikiMusic findVikiMusic(String vikiMusicId, int userid)
			throws ServiceException {
		UserBase user = ud.findUser(userid);
		return umd.findVikiMusic(vikiMusicId, user);
	}



}
