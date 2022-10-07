package com.spring.app.service;

import java.util.List;

import com.spring.app.dao.UserDAO;
import com.spring.app.model.UserInfoModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService{
	@Autowired
private UserDAO userDao;

public UserInfoModel isValidAdminUserService(String username, String password) {

	UserInfoModel userInfoModel = userDao.isValidAdminUser(username, password);

	return userInfoModel;

}

@SuppressWarnings({ "unchecked", "rawtypes" })
public List<UserInfoModel> getUserDataService() {
	
	 List<UserInfoModel> usersList= (List)userDao.getUserDatabyRollid(2);
        
       return usersList;
    }



public  UserInfoModel  isCorrectPassword_or_NotService(String password) {
	
	  UserInfoModel  usersList=  userDao.isCorrectPassword_or_Not(password);
        
       return usersList;
    }

	
public  boolean  updateAdminPasswordService(UserInfoModel userInfoModel, String currentpassword,
		String newpassword, String confirmpassword) {
	int count=0;
	boolean isUpdated=false;
	    count=  userDao.updateAdminPassword(confirmpassword,userInfoModel.getFirstname());
	     
        if(count>0)
        {
        	isUpdated=true;
        }
       return isUpdated;
    }

public void insertUserDataService(String firstname, String lastname, String password, String confirmpassword, int rollid, Long mobileno, String email)
{
	UserInfoModel   u =new UserInfoModel(firstname, lastname, confirmpassword, rollid, mobileno, email);
	userDao.save(u);
}
}
