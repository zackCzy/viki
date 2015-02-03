package cn.mini.service;

import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.exception.ServiceException;

public interface SportNewService {
	public List getSpacsSportNewDao(List <UserBase> parm,int page,int size)throws ServiceException;
}
