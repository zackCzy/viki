package cn.mini.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.SportNewDao;
import cn.mini.domain.UserBase;
import cn.mini.service.SportNewService;
@Service("sportNewServiceImpl")
public class SportNewServiceImpl implements SportNewService {

	@Resource(name="sportNewDaoImpl")
	private SportNewDao snd=null;
	@Override
	public List getSpacsSportNewDao(List<UserBase> parm, int page, int size)
			throws RuntimeException {
		return snd.getSpacsSportNewDao(parm, page, size);
	}
}
