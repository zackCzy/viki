package cn.mini.service.impl;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.SpaceSearchDao;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.service.SpaceSearchService;

@Service("spaceSearchServiceImpl")
public class SpaceSearchServiceImpl implements SpaceSearchService {

	@Resource(name="spaceSearchDaoImpl")
	SpaceSearchDao ssd=null;
	@Override
	public List<UserBase> searchUser(String name, int page, int pageSize)
			throws RuntimeException {
		return ssd.searchUser(name, page, pageSize);
	}

	@Override
	public List<UserLog> searchLog(String logName, int page, int pageSize)
			throws RuntimeException {
		return ssd.searchLog(logName, page, pageSize);
	}

	@Override
	public List<UserLog> searchSmallSpeak(String logName, int page, int pageSize)
			throws RuntimeException {
		return ssd.searchSmallSpeak(logName, page, pageSize);
	}

	@Override
	public BigInteger searchUserCount(String name) throws RuntimeException {
		return ssd.searchUserCount(name);
	}

	@Override
	public Long searchLogCount(String logName) throws RuntimeException {
		return ssd.searchLogCount(logName);
	}

	@Override
	public Long searchSmallSpeakCount(String logName)
			throws RuntimeException {
		return ssd.searchSmallSpeakCount(logName);
	}

}
