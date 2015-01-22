package cn.mini.service;

import java.util.List;

import cn.mini.domain.Comment;
import cn.mini.domain.UserBase;


public interface LogCommentService {
	public void saveLogCom(int id,Comment c) throws RuntimeException;
	public void removeLogCom(int id,UserBase user) throws RuntimeException;
	public List<Comment> getLogCom(int id)throws RuntimeException;
	public Comment getComment(int id)throws RuntimeException;
}
