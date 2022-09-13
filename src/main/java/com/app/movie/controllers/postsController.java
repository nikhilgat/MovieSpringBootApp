package com.app.movie.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.movie.model.posts;
import com.app.movie.repositories.postsDao;
import com.app.movie.services.postsService;

@RequestMapping
@RestController

public class postsController {

	@Autowired
	private postsService service;
	
	@Autowired
	private postsDao Dao;
	
	@GetMapping("/")
	public String hello() {
		return "Hello";
	}
	

	@GetMapping("/getposts")
	public List<posts> getallposts() {
		List<posts> reverse = Dao.findAll();
        Collections.reverse(reverse);
		return reverse;
	}
	
	
	@GetMapping("/getposts/{id}")
    public posts getSinglePost(@PathVariable @RequestBody Integer id) {
        return service.readSinglePost(id);
    }
	
	
	@PostMapping("/postposts")
	public posts addposts(@RequestBody posts post) {
		return Dao.save(post);
			
	}
	
	@DeleteMapping("/deleteposts/{id}")
    public String deleteUser(@PathVariable(value = "id") Integer Id) throws Exception {
        service.deleteposts(Id);
        return "Deleted Post";
    }
	
	
	

}
