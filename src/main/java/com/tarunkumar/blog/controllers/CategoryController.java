package com.tarunkumar.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarunkumar.blog.payloads.ApiResponse;
import com.tarunkumar.blog.payloads.CategoryDto;
import com.tarunkumar.blog.services.CategoryService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return  new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//Update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer catId ){
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, catId);
		return  new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	//Delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId ){
		this.categoryService.deleteCategory(catId);
		return  new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully !!", true),HttpStatus.OK);
	}
	//get 
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId ){
		CategoryDto categoryDto=this.categoryService.getCategory(catId);
		return  new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
	
	//get all  
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
			List<CategoryDto> categories=this.categoryService.getCategories();
			return ResponseEntity.ok(categories);
		}

}
