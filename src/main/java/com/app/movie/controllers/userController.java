package com.app.movie.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.movie.model.User;
import com.app.movie.model.jwtRequest;
import com.app.movie.model.jwtResponse;
import com.app.movie.repositories.customUserDetailsService;
import com.app.movie.repositories.usersDao;
import com.app.movie.util.jwtUtil;

@RestController
@CrossOrigin
public class userController {
	
	@Autowired
	private usersDao Dao;
	
	//to get all the user data
	@GetMapping("/getusers")
	public ResponseEntity<List<User>> getdata() {
		return ResponseEntity.ok(Dao.findAll());
	}
	
	//to save the incoming user data
	@PostMapping("/saveusers")
	public User postuserdata(@RequestBody User user ) {
		user.setActive(true);
		String userName = user.getName();
		if(String.valueOf(userName).contains("admin")) {
			user.setRoles("ROLE_ADMIN,ROLE_USER");
		} 
		else {
			user.setRoles("ROLE_USER");
		}
		return Dao.save(user);
	}
	
	@Autowired
	private customUserDetailsService custdetails;
	
	@Autowired
	private jwtUtil jwtutil;
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody jwtRequest jwtreq) throws Exception
	{
		 System.out.println(jwtreq);
		 
		 try
		 {
			 this.authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(jwtreq.getUsername(), jwtreq.getPassword()));
		 }
		 catch (UsernameNotFoundException e)
		 {
			 e.printStackTrace();
			 throw new Exception("bad credentials");
		 }
		 catch(BadCredentialsException b)
		 {
			 b.printStackTrace();
			 throw new Exception("bad credentials");
		 }
		 UserDetails userDetails = this.custdetails.loadUserByUsername(jwtreq.getUsername());
		 
		 
		 String token = this.jwtutil.generateToken(userDetails);
		 
		 System.out.println("JWT" + token);
		 
		 return ResponseEntity.ok(new jwtResponse(token));
		 
		 
	}
	
}


