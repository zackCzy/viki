package cn.mini.service;

import java.util.List;
import java.util.Set;

import cn.mini.domain.UserBase;

public interface SportNewService {
	public List getSpacsSportNewDao(List <UserBase> parm,int page,int size)throws RuntimeException;
}
