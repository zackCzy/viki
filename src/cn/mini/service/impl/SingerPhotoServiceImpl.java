package cn.mini.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.SingerPhotoDao;
import cn.mini.domain.SingerPhoto;
import cn.mini.service.SingerPhotoService;

@Service("singerPhotoServiceImpl")
public class SingerPhotoServiceImpl implements SingerPhotoService {
	@Resource(name="singerPhotoDaoImpl")
	SingerPhotoDao spd=null;
	@Override
	public SingerPhoto getSingerPhoto(int id) throws RuntimeException {
		return spd.getSingerPhoto(id);
	}

	@Override
	public SingerPhoto getSingerPhoto(String name) throws RuntimeException {
		return spd.getSingerPhoto(name);
	}

}
