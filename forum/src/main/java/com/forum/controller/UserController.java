package com.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forum.model.UserDetailsModel;
import com.forum.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/post-userdetails")
	public ResponseEntity<String> postuserDetails(@RequestBody UserDetailsModel userModel) {
		return userService.postUser(userModel);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-userdetails")
	public ResponseEntity<UserDetailsModel> getuserDetails(@RequestParam(required = false) int userId) {
		return userService.getUser(userId);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/edit-userdetails")
	public ResponseEntity<String> edituserDetails(@RequestBody UserDetailsModel userModel) {
		return userService.editUser(userModel);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteuserDetails(@RequestParam(required = false) int userId) {
		return userService.deleteUser(userId);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public ResponseEntity<UserDetailsModel> validateLogin(@RequestBody UserDetailsModel userModel) {
		return userService.validateUser(userModel);
	}
}
