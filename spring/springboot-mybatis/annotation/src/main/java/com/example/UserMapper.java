package com.example;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
	@Insert("INSERT INTO users(id, user_name, user_sex, nick_name) VALUES(#{id}, #{userName}, #{userSex}, #{nickName})")
	void insert(User user);
	
	@Select("SELECT * FROM users")
	@Results({
		@Result(property = "userSex",  column = "user_sex", javaType = UserSex.class),
		@Result(property = "nickName", column = "nick_name"),
		@Result(property = "userName", column = "user_name")
	})
	List<User> getAll();
	
	@Select("SELECT * FROM users WHERE id = #{id}")
	@Results({
		@Result(property = "userSex",  column = "user_sex", javaType = UserSex.class),
		@Result(property = "nickName", column = "nick_name"),
		@Result(property = "userName", column = "user_name")
	})
	User getOne(Long id);

	@Update("UPDATE users SET user_name=#{userName}, nick_name=#{nickName}, user_sex=#{userSex} WHERE id =#{id}")
	void update(User user);

	@Delete("DELETE FROM users WHERE id =#{id}")
	void delete(Long id);

	@Delete("DELETE FROM users")
	void deleteAll();

}