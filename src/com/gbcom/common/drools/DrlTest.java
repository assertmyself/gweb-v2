package com.gbcom.common.drools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TEST - MIAN
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-7-1,下午05:15:02
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.drools.demo.DrlTest
 */
public class DrlTest {





	/**
	 * test         this is nothin ,
	 * @param args String[]
	 * @throws IOException  IOException
	 */
	public static void main(String[] args) throws IOException {
		
		new DrlTest().excute();
		
		/*
		PointRuleEngine<ApDevice> pointRuleEngine = new PointRuleEngineImpl<ApDevice>();
//		PointRuleEngine<ApDevice> pointRuleEngine = new PointRuleEngineDBImpl<ApDevice>();
		while(true){
			InputStream is = System.in;
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String input = br.readLine();
			
			if(null != input && "s".equals(input)){
				System.out.println("初始化规则引擎...");
				pointRuleEngine.initEngine();
				System.out.println("初始化规则引擎结束.");
			}else if("e".equals(input)){
				List<ApDevice> list = new ArrayList<ApDevice>();
				for(int i=0;i<10;i++){
					final ApDevice pointDomain = new ApDevice();
					pointDomain.setApName("hello"+i);
					pointDomain.setAssocState(i%3);
					pointDomain.setDevForm(i%3);
					list.add(pointDomain);
				}
				pointRuleEngine.executeRuleEngine(list);
			} else if("r".equals(input)){
				System.out.println("刷新规则文件...");
				pointRuleEngine.refreshEnginRule();
				System.out.println("刷新规则文件结束.");
			}
		}
	*/}
	
	/**
	 * excute
	 */
	public void excute(){
//		DroolsRuleEngine<ApDevice> pointRuleEngine = ApDeviceDroolsEngineFactory.newInstance(ApDeviceDroolsEngineFactory.ENGINE_TYPE_FILE);
		
		DroolsRuleEngine pointRuleEngine = DroolsEngineFactory.fileEngine();
			System.out.println("初始化规则引擎...");
			//pointRuleEngine.initEngine();
			System.out.println("初始化规则引擎结束.");
			List<Object> list = new ArrayList<Object>();
			for(int i=0;i<10;i++){
				final Device pointDomain = new Device();
				pointDomain.setName("hello"+i);
				pointDomain.setState(i%3);
				pointDomain.setForm(i%3);
				list.add(pointDomain);
			}
			
			pointRuleEngine.executeRuleEngine(list);
			
			System.out.println("the endsl");

	}

	private class Device{
		private String name;
		private int state;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		public int getForm() {
			return form;
		}
		public void setForm(int form) {
			this.form = form;
		}
		private int form;
	}
}
