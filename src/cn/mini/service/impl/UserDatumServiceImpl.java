package cn.mini.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.UserDatumDao;
import cn.mini.domain.SpaceDatum;
import cn.mini.domain.UnitDatum;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserBaseDails;
import cn.mini.domain.UserBaseDatum;
import cn.mini.exception.DaoException;
import cn.mini.service.UserDatumService;

@Service("userDatumServiceImpl")
public class UserDatumServiceImpl implements UserDatumService {

	@Resource(name="userDatumDaoImpl")
	UserDatumDao udd=null;
	@Override
	public SpaceDatum getSpaceDatum(UserBase user) throws RuntimeException {		
		return udd.getSpaceDatum(user);
	}

	@Override
	public UserBaseDails getUserBaseDails(UserBase user) throws RuntimeException {
		return udd.getUserBaseDails(user);
	}

	@Override
	public UserBaseDatum getUserBaseDatum(UserBase user) throws RuntimeException {
		return udd.getUserBaseDatum(user);
	}

	@Override
	public UnitDatum getUnitDatum(UserBase user) throws DaoException {
		return udd.getUnitDatum(user);
	}

	@Override
	public void saveSpaceDatum(SpaceDatum sd) throws RuntimeException {
		udd.saveSpaceDatum(sd);
	}

	@Override
	public void saveUserBaseDails(UserBaseDails ubd) throws RuntimeException {
		udd.saveUserBaseDails(ubd);

	}

	@Override
	public void saveUserBaseDatum(UserBaseDatum ubd) throws RuntimeException {
		udd.saveUserBaseDatum(ubd);
	}

	@Override
	public void saveUnitDatum(UnitDatum ud) throws RuntimeException {
		udd.saveUnitDatum(ud);
	}

	@Override
	public void updateSpaceDatum(SpaceDatum sd) throws RuntimeException {
		udd.updateSpaceDatum(sd);
	}
	@Override
	public void updateUserBaseDails(UserBaseDails ubd) throws RuntimeException {
		udd.updateUserBaseDails(ubd);
	}

	@Override
	public void updateUserBaseDatum(UserBaseDatum ubd) throws RuntimeException {
		udd.updateUserBaseDatum(ubd);
	}

	@Override
	public void updateUnitDatum(UnitDatum ud) throws RuntimeException {
		udd.updateUnitDatum(ud);
	}

	@Override
	public UserBase exitsEmail(String email) throws RuntimeException {	
		return udd.exitsEmail(email);
	}

}
