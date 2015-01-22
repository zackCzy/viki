package cn.mini.service;

import java.util.List;

import cn.mini.domain.ReviewewComment;
import cn.mini.domain.UserBase;

public interface ReviewewCommentService {
	public void saveReviewewCom(ReviewewComment com) throws RuntimeException;
	public void removeReviewewCom(int id,UserBase user) throws RuntimeException;
	public List<ReviewewComment> getReviewewCom(int comId)throws RuntimeException;
}
