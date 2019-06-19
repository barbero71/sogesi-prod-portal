package it.sogesispa.prod.web.services;

import it.sogesispa.prod.web.dao.UserDAO;
import it.sogesispa.prod.web.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService
{
	private UserDAO userDao;

	@Autowired
	public void setUserDao(UserDAO userDao)
	{
		this.userDao = userDao;
	}


	public User setUser(String userName)
	{
		return userDao.setSession(userName);
	}
	
	public User setUser(String userId, String password)
	{
		return userDao.setSession(userId, password);
	}

}
