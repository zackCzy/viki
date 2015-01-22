package cn.mini.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.mini.dao.BaseDao;
import cn.mini.dao.ReviewewCommentDao;
import cn.mini.domain.Comment;
import cn.mini.domain.ReviewewComment;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;
@Repository("reviewewCommentDaoImpl")
public class ReviewewCommentDaoImpl extends BaseDao implements ReviewewCommentDao {

	@Override
	public void saveReviewewCom(ReviewewComment com) throws DaoException {
		try {
			getSession().save(com);
		} catch (Exception e) {
			throw new DaoException("ReviewewCommentDao:"+e.getMessage(), e);
		}
	}

	@Override
	public void removeReviewewCom(int rcomId, UserBase user) throws DaoException {
		try {		
			ReviewewComment c=(ReviewewComment) getSession().get(ReviewewComment.class,rcomId);
			if(c.getUser().getId()!=user.getId()){
				throw new DaoException("用户权限不够");
			}else{
				getSession().delete(c);
			}	
		} catch (Exception e) {
			throw new DaoException("ReviewewCommentDao:"+e.getMessage(), e);
		}
	}

	@Override
	public List<ReviewewComment> getReviewewCom(int comId) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(ReviewewComment.class);
			Query q = getSession()
			.createQuery("from  ReviewewComment as reviewew_com  where Comment=?  order by date desc");
			Comment com=new Comment();
			com.setId(comId);
			q.setParameter(0, com);
			return q.list();
		} catch (Exception e) {
			throw new DaoException("ReviewewCommentDao:"+e.getMessage(), e);
		}
	}

}
