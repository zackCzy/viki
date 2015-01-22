package cn.mini.service;

import java.util.List;

import cn.mini.domain.UserBase;

public interface CommunMessageService {
	public List<?> getPageCommunMessage(UserBase user,int page,int pageSize)throws RuntimeException;
}
