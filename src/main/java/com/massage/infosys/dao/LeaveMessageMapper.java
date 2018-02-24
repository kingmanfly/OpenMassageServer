package com.massage.infosys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveMessageMapper {

	@Insert("INSERT INTO leavemessage(comefrom,name,contact,title,content,create_time)"
			+ " values(#{comefrom},#{name},#{contact},#{title},#{content},now())")
	int save(@Param("comefrom") String comefrom,@Param("name") String name, @Param("contact") String contact,@Param("title") String title, @Param("content") String content);
}
