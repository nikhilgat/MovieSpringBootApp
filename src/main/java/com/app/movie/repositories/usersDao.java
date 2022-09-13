package com.app.movie.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.movie.model.User;
@EnableJpaRepositories
public interface usersDao extends JpaRepository <User, Integer> {
	
	Optional<User> findByName(String name);

}
