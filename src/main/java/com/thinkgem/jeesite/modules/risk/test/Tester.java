package com.thinkgem.jeesite.modules.risk.test;

import com.thinkgem.jeesite.modules.risk.dao.PageDao;
import com.thinkgem.jeesite.modules.risk.dao.RiskInfoBackDao;
import com.thinkgem.jeesite.modules.risk.dao.RiskInfoDao;
import com.thinkgem.jeesite.modules.risk.entity.RiskBack;
import com.thinkgem.jeesite.modules.risk.entity.RiskInfo;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Tester {
	
	@Test
	public void test(){
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-context.xml");
		RiskInfoDao rif=ac.getBean("riskInfoDao", RiskInfoDao.class);
		String id=rif.selectIdByResponseName("李国强");
		System.out.println("id:"+id);
	}
	@Test
	public void testDao(){
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-context.xml");
		RiskInfoDao rif=ac.getBean("riskInfoDao", RiskInfoDao.class);
		RiskInfo r=new RiskInfo();
		r.setId("8");
		r.setProId("97018a0a3ac947f1b717d3e289baa0fe");
		r.setRiskDescrible("极低");
		r.setRiskAnswer("来啦");
		r.setRiskName("压力");
		int i=rif.insertNewInfo(r);
		System.out.println("i:"+i);
		
	}
	@Test
	public void test2(){
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-context.xml");
		RiskInfoDao rif=ac.getBean("riskInfoDao", RiskInfoDao.class);
		String id=rif.selectIdByProjectName("海慧寺");
		System.out.println("id:"+id);
	}
	@Test
	public void testDelete(){
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-context.xml");
		RiskInfoDao rif=ac.getBean("riskInfoDao", RiskInfoDao.class);
		int  id=rif.deleteRiskInfoByRiskId("8");
		System.out.println("id:"+id);
	}
	@Test
	public void testSelect(){
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-context.xml");
		RiskInfoDao rif=ac.getBean("riskInfoDao", RiskInfoDao.class);
		RiskInfo risk  =rif.findRiskInfoById("2");
		System.out.println("risk:"+risk);
	}
	@Test
	public void testUpdate(){
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-context.xml");
		RiskInfoDao rif=ac.getBean("riskInfoDao", RiskInfoDao.class);
		RiskInfo riskInfo=new RiskInfo( );
		riskInfo.setId("215b998f-cc44-4f7e-a11f-f32452159017");
		riskInfo.setRiskName("测试");
		riskInfo.setRiskInfu("轻");
		riskInfo.setResponseId("2c95932adaa6493db4bf84076f71242b");
		int risk  =rif.updateRiskInfoById(riskInfo);
		System.out.println("risk:"+risk);
	}
	@Test
	public void testUpdateBack(){
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-context.xml");
		RiskInfoBackDao ribd=ac.getBean("riskInfoBackDao", RiskInfoBackDao.class);
		RiskBack rb=new RiskBack();
		rb.setRiskContent("一个人太孤独");
		rb.setRiskId("215b998f-cc44-4f7e-a11f-f32452159017");
		int risk  =ribd.updateRiskBack(rb);
		System.out.println("risk:"+risk);
	}
	@Test
	public void testProjectName(){
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-context.xml");
		RiskInfoDao rif=ac.getBean("riskInfoDao", RiskInfoDao.class);
		List<String> list=rif.getProjectName();
		System.out.println("list:"+list);
	}
	@Test
	public void testPage(){
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-context.xml");
		PageDao pd=ac.getBean("pageDao", PageDao.class);
//		Integer list=pd.findAllRiskInfo();
//		System.out.println("list:"+list);
	}
}
