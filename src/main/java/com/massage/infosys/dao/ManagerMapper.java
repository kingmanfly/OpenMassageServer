package com.massage.infosys.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerMapper {
	@Update("update skiller set status=#{status} where sharp_id=#{id}")
	int updateStatus(@Param("id") String id, @Param("status") int status);
}
