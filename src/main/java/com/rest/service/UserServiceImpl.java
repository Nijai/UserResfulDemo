package com.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.model.User;
import com.rest.repository.IUserRepository;


@Service
public class UserServiceImpl implements IUserService {	
	@Autowired
	private IUserRepository userrepository;
	
	
	@Override
	public User saveUser(User uObj) {
		Optional<User> optional = this.userrepository.findById(uObj.getUserId());
		if(optional.isPresent()) {
			System.out.println("User Already Exists!");
			return null;
		}
		else {
			User adduObj = this.userrepository.save(uObj);
			return adduObj;
		}
		
	}

	@Override
	public User updateUser(User uObj, int uid) {
	    Optional<User> optional = this.userrepository.findById(uid);
	    
	    if (optional.isPresent()) {
	        User existingUser = optional.get();
	        
	        // Update the existing user object with the new values
	        if(uObj.getUserName() != null) {
	        	existingUser.setUserName(uObj.getUserName());
	        }
			if(uObj.getUserEmail() != null) {
				existingUser.setUserEmail(uObj.getUserEmail());
			}
			if(uObj.getPassword() != null) {
				existingUser.setPassword(uObj.getPassword());
			}
	        
	        // ... update other properties as needed
	        
	        User updatedUser = this.userrepository.save(existingUser);
	        return updatedUser;
	    } else {
	        System.out.println("User Doest Not Exists for Update.");
	    }
	    return null;
	}


	@Override
	public User getUserById(int uid) {
	    Optional<User> optional = this.userrepository.findById(uid);
	    
	    if (optional.isPresent()) {
	        return optional.get();
	    } else {
	        System.out.println("User Does not exists!");
	    }
		return null;
	}

	@Override
	public List<User> getAllUser() {
		return this.userrepository.findAll();
	}

	@Override
	public boolean delUser(int uid) {
		Optional<User> userOptional = this.userrepository.findById(uid);
		boolean status = false;
		if(userOptional.isPresent()) {
			this.userrepository.delete(userOptional.get());
			status = true;
		}else {
			System.out.println("User data does not exists to delete!");
		}
		return status;
	}

}
