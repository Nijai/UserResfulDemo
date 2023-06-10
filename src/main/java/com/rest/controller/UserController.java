package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.model.User;
import com.rest.service.IUserService;

@RestController
@RequestMapping("api/v1")
public class UserController {
	@Autowired
	private IUserService userservice;
	
	// http://localhost:8080/api/v1/adduser
	// http://localhost:8080/api/v1/getAllUser
	// http://localhost:8080/api/v1/delUser
	// http://localhost:8080/api/v1/updateUser
	// http://localhost:8080/api/v1/getUserById
	
	
	
	private ResponseEntity<?> responseEntity;
	
	@PostMapping("/adduser")
	public ResponseEntity<?> saveUserHandler(@RequestBody User uObj){
		User newUser = this.userservice.saveUser(uObj);
		responseEntity = new ResponseEntity<>(newUser, HttpStatus.CREATED);
		return responseEntity;

	}
	@GetMapping("/getAllUser")
	public ResponseEntity<?> getAllUserHandler(){
		List<User> allUsers = this.userservice.getAllUser();
		responseEntity = new ResponseEntity<>(allUsers,HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping("/getUserById/{uid}")
	public ResponseEntity<?> getUserById(@PathVariable int uid){
		User user = this.userservice.getUserById(uid);
	    
	    if (user != null) {
	        return new ResponseEntity<>(user, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	    }
		
	}
	
	@PutMapping("/updateUser/{uid}")
	public ResponseEntity<?> updateUserHandler(@RequestBody User uObj, @PathVariable int uid) {
	    User updatedUser = this.userservice.updateUser(uObj, uid);
	    
	    if (updatedUser != null) {
	        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	    }
	  
	}
	
	@DeleteMapping("/delUser/{uid}")
	public ResponseEntity<?> deleteUserHandler(@PathVariable int uid) {
	    boolean deleted = this.userservice.delUser(uid);
	    
	    if (deleted) {
	        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	    }
	}


}
