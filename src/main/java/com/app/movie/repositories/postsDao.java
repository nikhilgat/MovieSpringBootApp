package com.app.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.movie.model.posts;

@EnableJpaRepositories
public interface postsDao extends JpaRepository <posts,Integer> {

}
