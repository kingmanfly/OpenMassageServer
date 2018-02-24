package com.massage.infosys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.massage.infosys.dto.SkillerRegisterPojo;
import com.massage.infosys.po.Skiller;

@Repository
public interface SkillerMapper {

	/**
	 * 获取技师个人信息
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT u.*,s.* from user as u inner join skiller as s "
			+ "on u.sharp_id = s.sharp_id " + "where s.sharp_id = #{id}")
	@ResultMap("com.massage.infosys.dao.SkillerMapper.skillerMap")
	Skiller findById(Long id);

	@Select("SELECT u.*,s.* from user as u inner join skiller as s "
			+ "on u.sharp_id = s.sharp_id " +
			"LIMIT #{startNo},#{pageSize};")
	@ResultMap("com.massage.infosys.dao.SkillerMapper.skillerMap")
	List<Skiller> findAll(@Param("startNo") int startNo, @Param("pageSize") int pageSize);
	
	@Select("SELECT COUNT(*) FROM skiller")
	int findAllCounts();
	/**
	 * 获取热门技师,默认获取2条
	 * 
	 * @return
	 */
	@Select("select s.*, u.*, d.counting from skiller as s  "
			+ "INNER JOIN data_statistics as d on s.sharp_id = d.content "
			+ "inner join user u on s.sharp_id = u.sharp_id "
			+ "where d.category = 1 ORDER by d.counting desc LIMIT 0,2;")
	@ResultMap("com.massage.infosys.dao.SkillerMapper.skillerMap")
	List<Skiller> findhotSkillers();

	/**
	 * 根据用户经纬度获取周边的技师信息
	 * 
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            维度
	 * @param pageNo
	 *            偏移量
	 * @param pageSize
	 *            每页最大获取数量
	 * @return
	 */
	@Select("SELECT s.*,u.*," + 
	"(st_distance(point(u.longitude, u.latitude),point(#{longitude},#{latitude}))*111195) AS distance " +
	"FROM user AS u INNER JOIN skiller AS s " +
	"ON u.sharp_id = s.sharp_id " +
	"where status=1 " +
	"ORDER BY distance " +
	"LIMIT #{pageNo},#{pageSize};")
	@ResultMap("com.massage.infosys.dao.SkillerMapper.skillerMap")
	List<Skiller> findNearlySkillers(@Param("longitude") Double longitude,
			@Param("latitude") Double latitude, @Param("pageNo") int pageNo,
			@Param("pageSize") int pageSize);

	// 技师升级 根据sharp_id查询技师表中是否存在记录
	@Select("select * from skiller where sharp_id=#{sharpId}")
	Skiller selectSkillerPhoneBySharpId(Long sharpId);

	// 技师升级更新技师表
	@Update("update skiller set pic_head_path=#{pic_head_path},description=#{description},"
			+ "age=#{age},height=#{height},level=#{level},location=#{location},rec_time=Now(),sex=#{sex} where sharp_id=#{sharp_id}")
	int updateSkillerBySpojo(SkillerRegisterPojo sPojo);

	// 技师升级插入技师表
	@Insert("insert into skiller values(NULL,#{phone},#{pic_head_path},#{pic_show_path},#{description},#{age},#{height},#{level},#{location},"
			+ "NOW(),NOW(),#{sharp_id},#{sex})")
	int insertSkillBySpojo(SkillerRegisterPojo sPojo);

	/**
	 * 测试
	 * 
	 * @param p1
	 */
	@Update("update skiller set phone=#{p1} where skiller_id='1000'")
	int update1(String p1);

	@Update("update skiller set phone=#{p2} where skiller_id='1001'")
	int update2(String p2);

	/**
	 * 根据token查询技师生活照路径
	 * 
	 * @param token
	 * @return
	 */
	@Select("select t1.pic_show_path from skiller t1 left outer join user t2 on t1.sharp_id=t2.sharp_id where t2.token=#{token} ")
	String selectPicShowPathByToken(String token);

	@Update("update skiller t1 inner join user t2 on t1.sharp_id=t2.sharp_id set t1.pic_show_path=#{pic_showPath} "
			+ "where t2.token=#{token}")
	void updateskillerBypicShowPath(@Param("pic_showPath") String pic_showPath,
			@Param("token") String token);

	/**
	 * 根据token查询个人电话
	 * 
	 * @param token
	 * @return
	 */
	@Select("select phone from user where token=#{token}")
	String selectPhoneByToken(String token);

	@Select("select t1.* from skiller t1 left outer join user t2 on t1.sharp_id=t2.sharp_id where t2.token=#{token}")
	Skiller selectSkillerByToken(String token);

	/**
	 * 用户上传生活照，更新数据库
	 * 
	 * @param pic_showPath
	 * @param sharp_id
	 */
	@Update("update skiller set pic_show_path=#{pic_showPath} where sharp_id=#{sharp_id}")
	void updatePicShowPath(@Param("pic_showPath") String pic_showPath,
			@Param("sharp_id") Long sharp_id);

	@Update("update skiller t1 inner join user t2 on t1.sharp_id=t2.sharp_id set t1.pic_head_path=#{headUrl}"
			+ " where t2.token=#{token}")
	void updatePicHeadPath(@Param("headUrl") String headUrl,
			@Param("token") String token);

}
