package com.massage.infosys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.massage.infosys.dto.SkillerRegisterPojo;
import com.massage.infosys.po.User;

@Repository
public interface UserMapper {

	/**
	 * 根据id查找用户
	 * @param id
	 * @return
	 */
	@Select("select * from user where sharp_id=#{id}")
	User findById(Long id);
	
	@Select("select * from user LIMIT #{startNo},#{pageSize};")
	List<User> findAll(@Param("startNo")Integer startNo,
			@Param("pageSize")Integer pageSize);
	
	@Select("SELECT COUNT(*) FROM user")
	int findAllCounts();
	
	
	/**
	 * 根据手机号查找用户
	 * @param phone
	 * @return
	 */
	@Select("select phone from user where phone=#{phone}")
	User findByPhone(String phone);
	
	/**
	 * 根据手机号码和密码查找用户
	 * @param phone
	 * @param passwrod
	 * @return
	 */
	@Select("select * from user where phone = #{phone} and password=#{password}")
	User findByPhoneAndpassWord(@Param("phone") String nickname,@Param("password") String passwrod);
	
	/**
	 * 添加用户
	 * @param phone
	 * @param nickname
	 * @param password
	 * @param token
	 * @param latitude TODO
	 * @param longitude TODO
	 * @return
	 */
	@Insert("INSERT INTO user(phone,nickname,password,latitude,longitude,create_time,rec_time,category,token)"
			+ " values(#{phone},#{nickname},#{password},#{latitude},#{longitude},now(),now(),1,#{token})")
	int save(@Param("phone")String phone,
			@Param("nickname")String nickname,
			@Param("password")String password,
			@Param("token")String token,
			@Param("latitude")Double latitude, 
			@Param("longitude")Double longitude);
	
	//修改密码
	@Update("update user set password=#{newpassword} where phone=#{phone}")
	int updatePassword(@Param("newpassword") String newpassword, @Param("phone") String phone);

	@Update("update user set password=#{newpassword},nickname=#{nickname},rec_time=NOW() where phone=#{phone}")
	int updateUser(@Param("nickname") String nickname,@Param("newpassword") String newpassword, @Param("phone") String phone);
	
	//技师升级更新user表
	@Update("update user set latitude=#{latitude},longitude=#{longitude},rec_time=NOW(),category='2' where token=#{token}")
	int updateUserBySpojo(SkillerRegisterPojo sPojo);

	//技师升级根据token查询sharp_id
	@Select("select sharp_id from user where token=#{token}")
	Long selectUserSharpIdByToken(String token);

	//获取个人信息
	@Select("select t1.nickname,t1.category,t2.* from user t1 left outer join skiller t2 on t1.sharp_id=t2.sharp_id where t1.token=#{token}")
	List<SkillerRegisterPojo> selectPeopleDeilByToken(String token);

	//刷新位置
	@Update("update user set latitude=#{latitude},longitude=#{longitude},rec_time=NOW() where token=#{token}")
	int updateUserLocation(@Param("token")String token, @Param("latitude")Double latitude,@Param("longitude") Double longitude);

	//普通用户编辑
	@Update("update user set nickname=#{nickName},rec_time=NOW() where token=#{token}")
	int updateUserInfoByToken(@Param("token")String token, @Param("nickName")String nickName);

	//技师编辑个人信息
	@Update("update user t1 inner join skiller t2 on t1.sharp_id=t2.sharp_id set t1.nickname=#{nickname},"
			+ "t1.rec_time=NOW(),t1.phone=#{phone},t2.phone=#{phone},t2.age=#{age},t2.height=#{height},"
			+ "t2.level=#{level},t2.sex=#{sex},t2.location=#{location},t2.description=#{description},t2.rec_time=NOW()"
			+ " where t1.token=#{token}")
	int updateSkillerInfoByToken(SkillerRegisterPojo skillerRegisterPojo);

	@Select("select t1.pic_head_path from skiller t1 left outer join user t2 on t1.sharp_id=t2.sharp_id where t2.token=#{token}")
	String selectPicHeadPathByToken(String token);
}
