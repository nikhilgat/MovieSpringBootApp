package com.app.movie.services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.movie.exceptions.PostNotFoundException;
import com.app.movie.exceptions.ResourceNotFoundException;
import com.app.movie.model.posts;
import com.app.movie.repositories.postsDao;


@SuppressWarnings("unused")
@Service
public class postsService {

	@Autowired
	private postsDao Dao;
	
	public List<posts> loadposts(){
		List<posts> load = new ArrayList<posts>();
		load.add (new posts());
		return load;
		}
  

		private List<posts> listofposts=loadposts();
		public List<posts> getallposts(){
			return listofposts;
		}
		
		
		public String addposts(posts post){
			listofposts.add(post);
			return "post added";
		}
		
	
		public void deleteposts(Integer Id) {
			posts post =Dao.findById(Id).orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ Id));
			Dao.delete(post);
			
			
		}

		public posts readSinglePost(Integer id) {
			posts post = Dao.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
	        return post;
		}

		
}
