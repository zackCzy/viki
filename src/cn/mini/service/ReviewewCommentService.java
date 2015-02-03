package cn.mini.service;

import java.util.List;

import cn.mini.domain.ReviewewComment;
import cn.mini.domain.UserBase;
import cn.mini.exception.ServiceException;

public interface ReviewewCommentService {
	public void saveReviewewCom(ReviewewComment com) throws ServiceException;
	public void removeReviewewCom(int id,UserBase user) throws ServiceException;
	public List<ReviewewComment> getReviewewCom(int comId)throws ServiceException;
}
