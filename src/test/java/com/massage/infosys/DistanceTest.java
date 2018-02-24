package com.massage.infosys;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.massage.infosys.util.Distance;
import com.massage.infosys.util.Util;


@RunWith(SpringRunner.class)  
@SpringBootTest(classes = Distance.class)  
public class DistanceTest extends TestCase{
	
	@Test  
    public void testCreateRandomVcode() {  
        System.out.println(Distance.getDistance(121.3538, 31.173544, 121.374569, 31.196179));
        assertEquals(true, Util.createRandomVcode(4).length() == 4);
    }  
}
