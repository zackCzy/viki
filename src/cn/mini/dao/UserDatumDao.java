package cn.mini.dao;

import cn.mini.domain.SpaceDatum;
import cn.mini.domain.UnitDatum;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserBaseDails;
import cn.mini.domain.UserBaseDatum;
import cn.mini.exception.DaoException;

public interface UserDatumDao {
	public SpaceDatum getSpaceDatum(UserBase user)throws DaoException;
	public UserBase exitsEmail(String email)throws DaoException;
	public UserBaseDails getUserBaseDails(UserBase user)throws DaoException;
	public UserBaseDatum getUserBaseDatum(UserBase user)throws DaoException;
	public UnitDatum getUnitDatum(UserBase user)throws DaoException;
	public void saveSpaceDatum(SpaceDatum sd)throws DaoException;
	public void saveUserBaseDails(UserBaseDails ubd)throws DaoException;
	public void saveUserBaseDatum(UserBaseDatum ubd)throws DaoException;
	public void saveUnitDatum(UnitDatum ud)throws DaoException;
	public void updateSpaceDatum(SpaceDatum sd)throws DaoException;
	public void updateUserBaseDails(UserBaseDails ubd)throws DaoException;
	public void updateUserBaseDatum(UserBaseDatum ubd)throws DaoException;
	public void updateUnitDatum(UnitDatum ud)throws DaoException;
}
