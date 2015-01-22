package cn.mini.dao;

import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.exception.DaoException;

public interface SportNewDao {
 public List<UserLog> getSpacsSportNewDao(List<UserBase> parm,int page,int size)throws DaoException;
 
}
