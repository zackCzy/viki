package cn.mini.service;

import cn.mini.domain.SpaceDatum;
import cn.mini.domain.UnitDatum;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserBaseDails;
import cn.mini.domain.UserBaseDatum;

public interface UserDatumService {
	public SpaceDatum getSpaceDatum(UserBase user)throws RuntimeException;
	public UserBaseDails getUserBaseDails(UserBase user)throws RuntimeException;
	public UserBaseDatum getUserBaseDatum(UserBase user)throws RuntimeException;
	public UnitDatum getUnitDatum(UserBase user)throws RuntimeException;
	public void saveSpaceDatum(SpaceDatum sd)throws RuntimeException;
	public void saveUserBaseDails(UserBaseDails ubd)throws RuntimeException;
	public void saveUserBaseDatum(UserBaseDatum ubd)throws RuntimeException;
	public void saveUnitDatum(UnitDatum ud)throws RuntimeException;
	public void updateSpaceDatum(SpaceDatum sd)throws RuntimeException;
	public void updateUserBaseDails(UserBaseDails ubd)throws RuntimeException;
	public void updateUserBaseDatum(UserBaseDatum ubd)throws RuntimeException;
	public void updateUnitDatum(UnitDatum ud)throws RuntimeException;
	public UserBase exitsEmail(String email)throws RuntimeException;
}
