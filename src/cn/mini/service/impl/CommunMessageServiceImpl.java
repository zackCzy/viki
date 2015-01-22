package cn.mini.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.CommunMessageDao;
import cn.mini.domain.UserBase;
import cn.mini.service.CommunMessageService;
@Service("communMessageServiceImpl")
public class CommunMessageServiceImpl implements CommunMessageService {

	@Resource(name="communMessageDaoImpl")
	private CommunMessageDao cmd=null;
	@Override
	public List<?> getPageCommunMessage(UserBase user, int page, int pageSize) throws RuntimeException {
		return cmd.getPageCommunMessage(user, page, pageSize);
	}

}
