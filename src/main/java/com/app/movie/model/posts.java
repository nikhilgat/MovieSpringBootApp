package com.app.movie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class posts {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int Id;
	@Column(columnDefinition="TEXT")
	String title;
	@Column(columnDefinition="TEXT")
	String description;
	@Column(columnDefinition="LONGTEXT")
	String review;
	@Column(columnDefinition="TEXT")
	String imageUrl;
	
	
	public posts() {
		super();
	}


	public posts(int id, String title, String description, String review, String imageUrl) {
		super();
		Id = id;
		this.title = title;
		this.description = description;
		this.review = review;
		this.imageUrl = imageUrl;
	}


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	@Override
	public String toString() {
		return "posts [Id=" + Id + ", title=" + title + ", description=" + description + ", review=" + review
				+ ", imageUrl=" + imageUrl + "]";
	}
	
}
