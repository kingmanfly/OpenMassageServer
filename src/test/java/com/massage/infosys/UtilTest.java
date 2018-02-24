package com.massage.infosys;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.massage.infosys.util.Util;


@RunWith(SpringRunner.class)  
@SpringBootTest(classes = Util.class)  
public class UtilTest extends TestCase{
	
	@Test  
    public void testCreateRandomVcode() {  
        System.out.println(Util.createRandomVcode(4));
        assertEquals(true, Util.createRandomVcode(4).length() == 4);
    }  
	
	@Test  
    public void testIsPhonePattern() {  
        System.out.println(Util.isCellphonePattern("17321292727"));
        assertEquals(true, Util.isCellphonePattern("17321292727"));
    }  
}
