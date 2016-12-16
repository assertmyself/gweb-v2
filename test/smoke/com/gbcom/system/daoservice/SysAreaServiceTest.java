package com.gbcom.system.daoservice;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
//@ContextConfiguration("classpath:/context/testApplicationContext.xml") 
@ContextConfiguration("classpath:context/applicationContext.xml") 
//@Transactional
public class SysAreaServiceTest {
	@Autowired
	SysAreaService service;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave1() {
	}
	@Test
	public void testSave2() {
		fail("Not yet implemented");
	}
	@Test
	public void testSave3() {
		fail("Not yet implemented");
	}

}
