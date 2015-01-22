package cn.mini.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.UserDao;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;
import cn.mini.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Resource(name="userDaoImpl")
	private  UserDao ud=null;
	@Override
	public void saveUser(UserBase user) throws RuntimeException {	
		try {	
			ud.registerUser(user);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}	
	}


	@Override
	public int sginUserService(String name, String password) throws RuntimeException {
		int id=ud.sginUser(name, password);
		return id;
	}

	@Override
	public int selectUserService(String name) throws RuntimeException {
		return ud.selectUser(name);
	}

	@Override
	public UserBase findUserService(int id) throws RuntimeException {
		return ud.findUser(id);
	}

	@Override
	public UserBase findUser(String name) throws RuntimeException {
		return ud.findUser(name);
	}

	@Override
	public void updateUser(UserBase bean) throws RuntimeException {
		ud.updateUser(bean);	
	}

	@Override
	public void removeNoCheckUser(String email) throws RuntimeException {
		List<UserBase> users=ud.findUserList(email);
		for (UserBase u:users) {
			String emailToken=u.getActive();
			if(emailToken.length()>0){
				System.out.println(u.getName());
				ud.removeUser(u);
			}
		}
	}


	@Override
	public List<UserBase> findUserAll(int page, int pageSize)
			throws DaoException {
		return ud.findUserAll(page, pageSize);
	}


	@Override
	public void removeUser(UserBase user) throws RuntimeException {
		ud.removeUser(user);
	}

}
