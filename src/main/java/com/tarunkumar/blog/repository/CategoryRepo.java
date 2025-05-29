package com.tarunkumar.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarunkumar.blog.entities.Category;

public interface  CategoryRepo extends JpaRepository<Category,Integer>{

	Category save(Category cat);

}
