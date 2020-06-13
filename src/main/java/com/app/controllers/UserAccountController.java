
package com.app.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.services.interfaces.IUserService;
import com.app.user.pojos.User;
import com.app.utils.Token;
import com.app.utils.UserUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserAccountController {

	@Autowired
	private IUserService userService;

	@GetMapping("/searches/get")
	public ResponseEntity<?> getSearchHistory(@RequestHeader String Authorization) {
		try {
			Token token = UserUtils.parseToken(Authorization);
			if (UserUtils.validateToken(token)) {
				return new ResponseEntity<Set<String>>(userService.getSearchHistory(token.getId()), HttpStatus.OK);
			}
			return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logOutUser(@RequestHeader String Authorization) {
		try {
			Token token = UserUtils.parseToken(Authorization);
			if (UserUtils.validateToken(token)) {
				UserUtils.removeToken(token);
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			}
			return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/profile")
	public ResponseEntity<?> getUserProfile(@RequestHeader String Authorization) {
		try {
			Token token = UserUtils.parseToken(Authorization);
			if (UserUtils.validateToken(token)) {
				User user = userService.getUser(token.getId());
				user.setPassword("");
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
			return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateUser(@RequestHeader String Authorization, @RequestBody String userJson) {
		try {
			Token token = UserUtils.parseToken(Authorization);
			if (UserUtils.validateToken(token)) {
				System.out.println("string received " + userJson);
				User user = UserUtils.parseUserJson(userJson);
				System.out.println("user " + user);
				user.setId(token.getId());
				userService.updateUser(user);
				user.setPassword("");
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
			return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody String userJson) {
		try {
			System.out.println("string received " + userJson);
			User user = UserUtils.parseUserJson(userJson);
			System.out.println("user " + user);
			userService.addUser(user);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestBody String userJson) {
		try {
			System.out.println("String received " + userJson);
			User userToRegister = UserUtils.parseUserJson(userJson);
			System.out.println("Object received " + userToRegister);
			User userFromDB = userService.validateUser(userToRegister);
			System.out.println("user validated " + userFromDB);
			if (userFromDB != null) {
				System.out.println("valid user");
				Token token = UserUtils.getToken(userFromDB);
				return new ResponseEntity<Token>(token, HttpStatus.OK);
			}
			return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
