package com.massage.infosys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.massage.infosys.po.AuthUser;

@Repository
public interface AuthUserMapper {

	/**
	 * 根据id查找用户
	 * 
	 * @param id
	 *            参数id
	 * @return
	 */
	@Select("select * from authuser where id=#{id}")
	AuthUser findById(Long id);

	/**
	 * @return
	 */
	@Select("select * from authuser")
	List<AuthUser> findAll();
	
	/**
	 * 根据用户名查找对象
	 * 
	 * @param username
	 * @return
	 */
	@Select("select * from authuser where username=#{username}")
	AuthUser findByUserName(@Param("username") String username);

	/**
	 * 插入一条记录
	 * @return 影响的行数
	 */
	@Insert("INSERT INTO authuser(username,password,nickname,role,create_time,rec_time)"
			+ " values(#{username},#{password},#{nickname},#{role},now(),now())")
	int save(@Param("username") String username,
			@Param("password") String password,
			@Param("nickname") String nickname,
			@Param("role") int role);
}
