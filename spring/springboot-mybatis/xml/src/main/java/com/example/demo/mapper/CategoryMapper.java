package com.example.demo.mapper;

import com.example.demo.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper {
	List<Category> findAll();
	int create(Category category);
	int remove(Category category);
	Category findById(int id);
	int update(Category category);
	List<Category> findByName(String name);
	List<Category> findByIdAndName(Map<String,Object> map);
	List<Category> findJoinProduct();
	List<Category> findByPage(@Param("start") int start, @Param("count")int count);
}