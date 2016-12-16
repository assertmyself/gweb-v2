package com.gbcom.system.manager;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gbcom.system.daoservice.SysAreaService;
import com.gbcom.system.domain.SysArea;

@RunWith(SpringJUnit4ClassRunner.class) 
//@ContextConfiguration("classpath:/context/testApplicationContext.xml") 
@ContextConfiguration("classpath:context/applicationContext.xml") 
//@Transactional
public class SysAreaManagerTest {
	@Autowired
	SysAreaService sysAreaService;
	@Autowired
	SysAreaManager sysAreaManager;

	@Before
	public void setUp() throws Exception {
		assertNotNull("",sysAreaService);
		assertNotNull("",sysAreaManager);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave1() {
		for(int i=0 ;i<10;i++){
			SysArea area = new SysArea();
			area.setParent(null);
			area.setAreaCode("A-"+i);
			area.setAreaName("Aa-"+i);
			area.setIsLeaf(false);
			area.setDescription("aaaaaa");
			area.setDisplayName("xxA-"+i);
			area.setLayer(0L);
			sysAreaService.save(area);
		}
	}
	@Test
	public void testSave2() {
		List<SysArea> list = sysAreaManager.findByLayer(0L);
		for(SysArea each : list){
			
			for(int i=0 ;i<5;i++){
				SysArea area = new SysArea();
				area.setParent(each);
				area.setAreaCode(each.getAreaCode() +"--"+"B-"+i);
				area.setAreaName(each.getAreaCode() +"--"+"Bb-"+i);
				area.setIsLeaf(false);
				area.setDescription(each.getAreaCode() +"-"+"BBB");
				area.setDisplayName(each.getAreaCode() +"-"+"xxB-"+i);
				area.setLayer(1L);
				sysAreaService.save(area);
			}
		}
		 list = sysAreaManager.findByLayer(1L);
		for(SysArea each : list){
			
			for(int i=0 ;i<5;i++){
				SysArea area = new SysArea();
				area.setParent(each);
				area.setAreaCode(each.getAreaCode() +"--"+"C-"+i);
				area.setAreaName(each.getAreaCode() +"--"+"Cc-"+i);
				area.setIsLeaf(true);
				area.setDescription(each.getAreaCode() +"-"+"CCC");
				area.setDisplayName(each.getAreaCode() +"--"+"xxC-"+i);
				area.setLayer(2L);
				sysAreaService.save(area);
			}
		}
	}
	
	
	@Test
	public void testFindByLayer() {
		List<SysArea> list = sysAreaManager.findByLayer(0l);
		assertNotNull("",list);
	}

	@Test
	public void testGetSysAreaLong() {
		SysArea area = sysAreaManager.getSysArea(1l);
		assertNotNull("",area);
	}

	@Test
	public void testGetChild() {
		SysArea area = sysAreaManager.getSysArea(1l);
		List<SysArea> list = sysAreaManager.getChild(area);
		assertNotNull("",list);
	}

}
