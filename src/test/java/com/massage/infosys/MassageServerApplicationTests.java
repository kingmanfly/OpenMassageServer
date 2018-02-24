package com.massage.infosys;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.massage.infosys.dao.DatatStatisticsMapper;
import com.massage.infosys.dao.SkillerMapper;
import com.massage.infosys.dao.UserMapper;
import com.massage.infosys.dao.cache.RedisDao;
import com.massage.infosys.po.AuthUser;
import com.massage.infosys.po.Skiller;
import com.massage.infosys.service.AuthUserService;

import junit.framework.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MassageServerApplication.class)
public class MassageServerApplicationTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SkillerMapper skillerMapper;
	
	@Autowired
	private DatatStatisticsMapper data_statisticsMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AuthUserService authUserService;
	
	@Autowired
	private RedisDao redisDao;
	
	/**
	 * 
	 */
	@Test
	public void testAuthUserService() {
		AuthUser authUser = new AuthUser();
		authUser.setUsername("admin");
		authUser.setNickname("Admin");
		authUser.setPassword("admin");
		authUser.setRole(1);
		Assert.assertEquals(1, authUserService.insertAuthUser(authUser));
		
	}
	
	@Test
	public void contextLoads() {
		Skiller skiller  =skillerMapper.findById(1000l);
		logger.info(skiller.getUser().getNickname());
	}
	
	@Test
	public void updatedatastatices() throws Exception{
		int result = data_statisticsMapper.update("1000");
		logger.info("淇敼缁撴灉锛�"+result+"");
	}
	
	@Test
	public void findhotSkillers() throws Exception{
		List<Skiller> skillers = skillerMapper.findhotSkillers();
		logger.info(skillers.get(0).getDataStatistics().getCounting()+"");
	}
	
	@Test
	public void findNearlySkillers() throws Exception{
		List<Skiller> skillers = skillerMapper.findNearlySkillers(121.538591, 31.219622,0,2);
		logger.info(skillers.size()+"");
		logger.info(skillers.get(0).getUser().toString());
	}
	
	@Test
	public void findByNicknameAndpassWord() throws Exception{
//		logger.info(this.redisDao.set("age", "16"));
		logger.info(this.redisDao.get("age"));
		
	}
	
	@Test
	public void saveuser() throws Exception{
		int result  = this.userMapper.save("15801916377", "灏忔潕", "1234", "sdfvsf123", -1.0, -1.0);
		logger.info(result+"");
	}

}
