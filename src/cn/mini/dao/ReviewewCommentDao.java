package cn.mini.dao;

import java.util.List;

import cn.mini.domain.ReviewewComment;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;

public interface ReviewewCommentDao {
	public void saveReviewewCom(ReviewewComment com) throws DaoException;
	public void removeReviewewCom(int id,UserBase user) throws DaoException;
	public List<ReviewewComment> getReviewewCom(int comId)throws DaoException;
}
