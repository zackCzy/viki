package cn.mini.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.CommentMessageDao;
import cn.mini.domain.UserBase;
import cn.mini.exception.ServiceException;
import cn.mini.service.CommentMessageService;
@Service("commentMessageServiceImpl")
public class CommentMessageServiceImpl implements CommentMessageService {

	@Resource(name="commentMessageDaoIpml")
	private CommentMessageDao cmd=null;
	
	@Override
	public List<?> getPageCommentMessage(UserBase user, int page, int pageSize)
			throws ServiceException {
		try {
			return cmd.getPageCommentMessage(user, page, pageSize);
		} catch (Exception e) {
			throw new ServiceException("CommentMessageService-getPageCommentMessage:"+e.getMessage(),e);
		}
		
	}

}
