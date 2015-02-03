package cn.mini.service;

import cn.mini.domain.SpaceDatum;
import cn.mini.domain.UnitDatum;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserBaseDails;
import cn.mini.domain.UserBaseDatum;
import cn.mini.exception.ServiceException;

public interface UserDatumService {
	public SpaceDatum getSpaceDatum(UserBase user)throws ServiceException;
	public UserBaseDails getUserBaseDails(UserBase user)throws ServiceException;
	public UserBaseDatum getUserBaseDatum(UserBase user)throws ServiceException;
	public UnitDatum getUnitDatum(UserBase user)throws ServiceException;
	public void saveSpaceDatum(SpaceDatum sd)throws ServiceException;
	public void saveUserBaseDails(UserBaseDails ubd)throws ServiceException;
	public void saveUserBaseDatum(UserBaseDatum ubd)throws ServiceException;
	public void saveUnitDatum(UnitDatum ud)throws ServiceException;
	public void updateSpaceDatum(SpaceDatum sd)throws ServiceException;
	public void updateUserBaseDails(UserBaseDails ubd)throws ServiceException;
	public void updateUserBaseDatum(UserBaseDatum ubd)throws ServiceException;
	public void updateUnitDatum(UnitDatum ud)throws ServiceException;
	public UserBase exitsEmail(String email)throws ServiceException;
}
