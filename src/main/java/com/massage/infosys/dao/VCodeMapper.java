package com.massage.infosys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.massage.infosys.po.VCode;

@Repository
public interface VCodeMapper {

	/**
	 * 根据id查找用户
	 * 
	 * @param id
	 *            参数id
	 * @return
	 */
	@Select("select * from vcode where id=#{id}")
	VCode findById(Long id);

	/**
	 * 根据手机号查找对象
	 * 
	 * @param phone
	 * @return
	 */
	@Select("select * from vcode where phone=#{phone}")
	VCode findByPhone(@Param("phone") String phone);

	/**
	 * 插入一条记录
	 * 
	 * @param phone
	 * @param vcode
	 * @return
	 */
	@Insert("INSERT INTO vcode(phone,vcode,expired,create_time,rec_time)"
			+ " values(#{phone},#{vcode},1000 * 60,now(),now())")
	int save(@Param("phone") String phone, @Param("vcode") String vcode);

	/**
	 * 根据手机号更新验证码
	 * 
	 * @param phone
	 * @param vcode
	 * @return
	 */
	@Update("update vcode set vcode=#{vcode},rec_time=NOW() where phone=#{phone}")
	int updateByPhone(@Param("phone") String phone, @Param("vcode") String vcode);
}
