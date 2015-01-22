package cn.mini.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.UserDao;
import cn.mini.dao.UserMusicDao;
import cn.mini.domain.PageMusic;
import cn.mini.domain.SearchMusic;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserSpaceMusic;
import cn.mini.exception.DaoException;
import cn.mini.service.UserMusicService;

@Service("userMusicServiceImpl")
public class UserMusicServiceImpl implements UserMusicService {
	@Resource(name="userMusicDaoImpl")
	private UserMusicDao umd=null;
	@Resource(name="userDaoImpl")
	private UserDao ud=null;
	@Override
	public List<Object> Search(String musicName, int page, int pageSize)
			throws RuntimeException {
		List<Object> list=umd.Search(musicName, page, pageSize);
		Long pageCount=umd.selectCount(musicName);
		PageMusic p=new PageMusic();
		p.setPagecount(pageCount);
		p.setCurrentpage(page);
		list.add(p);
		return list;
	}

	@Override
	public List<SearchMusic> getUserMusic(UserBase User)
			throws RuntimeException {	
		return umd.getUserMusic(User);
	}

	@Override
	public Long selectCount(String musicName) throws RuntimeException {
		return umd.selectCount(musicName);
	}

	@Override
	public SearchMusic selectMusic(int id) throws RuntimeException {
		return umd.selectMusic(id);
	}

	@Override
	public void createMusic(int id, int userId) throws RuntimeException {
		try {
			SearchMusic sm=umd.selectMusic(id);
			UserBase user=ud.findUser(userId);
			UserSpaceMusic usm=new UserSpaceMusic();
			org.apache.commons.beanutils.BeanUtils.copyProperties(usm, sm);
			usm.setUser(user);
			umd.createMusic(usm);
		} catch (Exception e) {
		  throw new RuntimeException();
		}
	}

	@Override
	public void removeMusic(int id) throws DaoException {
		UserSpaceMusic usm=umd.selectSpaceMusic(id);
		umd.removeMusic(usm);
	}

	@Override
	public void createInternationMusic(UserSpaceMusic usm, int userid)
			throws RuntimeException {
		UserBase user=ud.findUser(userid);
		usm.setUser(user);
		umd.createMusic(usm);
	}
}
