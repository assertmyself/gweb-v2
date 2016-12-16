package com.gbcom.common.drools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.drools.RuleBase;
import org.drools.StatefulSession;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;

/**
 *  * 全局对象，DroolsRuleEngineDBImpl  会加载 当前 drools的规则 （从配置文件）
 * 
 * 规则是固定的，单例对象加载就初始化完成， 支持后续规则的刷新操作。
 * 
 * 调用 excute方法，，对list fact 进行匹配。可以对任意对象进行  match
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-7-6,下午02:33:07
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.common.drools.DroolsRuleEngineImpl
 */
public class DroolsRuleEngineImpl implements DroolsRuleEngine {
	private static final Logger logger = Logger.getLogger(DroolsRuleEngineImpl.class);
	private static class DroolsRuleEngineHolder{
		private static final DroolsRuleEngine INSTANCE  = new  DroolsRuleEngineImpl();
	}
	/**
	 * 获取单例
	 * @return DroolsRuleEngine
	 */
	public static DroolsRuleEngine getInstance(){
		return DroolsRuleEngineHolder.INSTANCE;
	}
	private DroolsRuleEngineImpl(){
		initEngine();
	}
	
	//private field
	private RuleBase ruleBase;

	/* (non-Javadoc)
	 * @see com.drools.demo.point.PointRuleEngine#initEngine()
	 */
	private void initEngine() {
		// 设置时间格式
		System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
		ruleBase = RuleBaseFacatory.getRuleBase();
		try {
			PackageBuilder backageBuilder = getFilePackageBuilder();
			ruleBase.addPackages(backageBuilder.getPackages());
		} catch (DroolsParserException e) {
			e.printStackTrace();
			logger.error("drools parse failed!!!", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("drools other err failed!!!", e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.drools.demo.point.PointRuleEngine#refreshEnginRule()
	 */
	public void refreshEnginRule() {
		ruleBase = RuleBaseFacatory.getRuleBase();
		org.drools.rule.Package[] packages = ruleBase.getPackages();
		for(org.drools.rule.Package pg : packages) {
			ruleBase.removePackage(pg.getName());
		}
		
		initEngine();
	}

	/** (non-Javadoc)
	 * @param domain  List<Object> 
	 * @see com.drools.demo.point.PointRuleEngine#executeRuleEngine(com.drools.demo.point.PointDomain)
	 */
	public void executeRuleEngine(final List<Object> domain) {
		if(null == ruleBase.getPackages() || 0 == ruleBase.getPackages().length) {
			return;
		}
		
		StatefulSession statefulSession = ruleBase.newStatefulSession();
		for(Object each : domain){
			statefulSession.insert(each);
		}
		
		// fire
		statefulSession.fireAllRules();
		
		statefulSession.dispose();
	}

	/**
	 * 从Drl规则文件中读取规则
	 * @return
	 * @throws Exception
	 */
	private PackageBuilder getFilePackageBuilder() throws Exception {
		// 装载测试脚本文件
		List<Reader> readers = readRuleFile();

		PackageBuilder backageBuilder = new PackageBuilder();
		for (Reader r : readers) {
			backageBuilder.addPackageFromDrl(r);
		}
		
		// 检查脚本是否有问题
		if(backageBuilder.hasErrors()) {
			throw new Exception(backageBuilder.getErrors().toString());
		}
		
		return backageBuilder;
	}

	/**
	 * @param drlFilePath 脚本文件路径
	 * @return
	 * @throws FileNotFoundException
	 */
	private List<Reader> readRuleFile() throws FileNotFoundException {

		List<Reader> readers = new ArrayList<Reader>();
		
        String filePath = this.getClass().getClassLoader().getResource("drl/").getFile();

        File file = new File(filePath);
        File[] files = file.listFiles();
        if (files == null) {
        	throw new FileNotFoundException("drl ="+filePath);
        }

        for (File f : files) {
        	 if (f.isDirectory()){}else{
        		 readers.add(new FileReader(f));
        	 }
        }


		return readers;
	}

}