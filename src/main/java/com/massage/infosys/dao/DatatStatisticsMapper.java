package com.massage.infosys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;



@Repository
public interface DatatStatisticsMapper {

	/**
	 * 保存数据11
	 * @param content 
	 * @param counting 计数
	 * @param category 类型
	 * @return
	 */
	@Insert("INSERT IGNORE INTO data_statistics(content,counting,category) " +
            "VALUES (#{content},#{counting},#{category})")
	int save(@Param("content")String content,@Param("counting")int counting,@Param("category")int category);
	
	/**
	 * 自动加一计数
	 * @param content 类型
	 * @return
	 */
	@Update("update data_statistics set counting = counting+1 ,rec_time =now() where content = #{content}")
	int update(String content);
}
