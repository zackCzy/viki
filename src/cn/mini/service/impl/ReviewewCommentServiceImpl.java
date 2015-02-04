package cn.mini.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.ReviewewCommentDao;
import cn.mini.dao.UserLogDao;
import cn.mini.domain.ReviewewComment;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.service.ReviewewCommentService;
@Service("reviewewCommentServiceImpl")
public class ReviewewCommentServiceImpl implements ReviewewCommentService {
	@Resource(name = "userLogDaoImpl")
	private UserLogDao uld = null;
	@Resource(name="reviewewCommentDaoImpl")
	private ReviewewCommentDao pcd=null;
	@Override
	public void saveReviewewCom(ReviewewComment com) throws RuntimeException {
		UserLog userLog=com.getComment().getUserlog();
		userLog.setCommentNum(userLog.getCommentNum()+1);
		uld.updateLog(userLog);
		pcd.saveReviewewCom(com);	
	}

	@Override
	public void removeReviewewCom(int id, UserBase user)
			throws RuntimeException {
		pcd.removeReviewewCom(id, user);
		
	}

	@Override
	public List<ReviewewComment> getReviewewCom(int comId) throws RuntimeException {
		return pcd.getReviewewCom(comId);
	}
}
