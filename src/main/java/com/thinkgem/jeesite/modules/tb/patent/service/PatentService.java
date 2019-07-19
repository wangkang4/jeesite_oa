package com.thinkgem.jeesite.modules.tb.patent.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.patent.dao.PatentDao;
import com.thinkgem.jeesite.modules.tb.patent.entity.Patent;
import com.thinkgem.jeesite.modules.tb.patent.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
@Service
public class PatentService  extends CrudService<PatentDao,Patent>{

	@Autowired
	private PatentDao patentDao;
	
	/**
	 * 依赖注入actTaskServic属性
	 */
	@Autowired
	private ActTaskService actTaskService;
	
	/**
	 * 表单提交/修改/销毁
	 */
	@Transactional(readOnly=false)
	public void save(Patent patent){
		if(StringUtils.isBlank(patent.getId())){//提交申请
			patent.preInsert();
			patent.setStatu("审核中");
			dao.insert(patent);
			startGet(patent);			
		}else{
			patent.preUpdate();
			dao.update(patent);
			if ("yes".equals(patent.getAct().getFlag())) {
				patent.getAct().setComment("[重申]" + patent.getUser().getLoginName()+"-专利申请");
				patent.setStatu("审核中");
				dao.updateStatu(patent);
			} else {
				patent.getAct().setComment("[销毁]" + patent.getAct().getComment());
				patent.setStatu("销毁");
				dao.updateStatu(patent);
				dao.delete(patent);
			}
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("rebut", "yes".equals(patent.getAct().getFlag()) ? "1" : "0");
			String taskId = dao.selectTaskIdByProcinsId(patent.getAct().getProcInsId());
			
			// 启动部署成功的工作流
		
			actTaskService.complete(taskId, patent.getAct().getProcInsId(),
					patent.getAct().getComment(), patent.getUser().getLoginName()+"-专利申请", vars);
		}
	}
	
	/**
	 * 插入负表数据
	 * @param person
	 */
	@Transactional(readOnly=false)
	public void insertPerson(Person person) {
		patentDao.insertPerson(person);
	}
	
	/**
	 * 删除附表数据
	 * @param person
	 */
	@Transactional(readOnly=false)
	public void deletePerson(String id) {
		patentDao.deletePerson(id);
	}

	/**
	 * 根据p_id查询负表信息
	 * @param id
	 * @return 
	 */
	@Transactional(readOnly=false)
	public List<Person> findPerson(String id) {
		return patentDao.findPerson(id);
	}

	/**
	 * 流程审核
	 * @param patent
	 */
	@Transactional(readOnly = false)
	public void auditSave(Patent patent){
			// 设置审核意见
			patent.getAct().setComment(
					("yes".equals(patent.getAct().getFlag()) ? "[同意]" : "[驳回]") + patent.getAct().getComment());
			patent.preUpdate();
			
			/**获取当前审核人的角色*/
			String roleStr = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
			String[] roles = roleStr.split(",");
			/**判断是否为这一角色的人*/
			Boolean thd_a = Arrays.asList(roles).contains("桃花岛总裁");
			Boolean thd_b = Arrays.asList(roles).contains("桃花岛执行董事");
			Boolean thd_c = Arrays.asList(roles).contains("桃花岛财务总监");
			Boolean thd_d = Arrays.asList(roles).contains("桃花岛行政级别");
			/**研发总监*/
			if (thd_b) {
				patent.setProneText(patent.getAct().getComment());
				dao.updateProneText(patent);
			}
			/**财务总监*/
			if (thd_c) {
				patent.setPrtwoText(patent.getAct().getComment());
				dao.updatePrtwoText(patent);
			}
			/**总裁*/
			if (thd_a) {
				patent.setPrthreeText(patent.getAct().getComment());
				dao.updatePrthreeText(patent);
			}
			/**各地行政*/
			if (thd_d) {
				patent.setPrfourText(patent.getAct().getComment());
				dao.updatePrfourText(patent);
			}
			if(!"yes".equals(patent.getAct().getFlag())){
				patent.setStatu("驳回");
				dao.updateStatu(patent);
			}
			Map<String, Object> vars = Maps.newHashMap();
			/**流程正常走向*/
			vars.put("agree", "yes".equals(patent.getAct().getFlag()) ? "1" : "0");
			if("yes".equals(patent.getAct().getFlag())&&thd_d){
					patent.setStatu("审核通过");
					dao.updateStatu(patent);
					/**流程结束走向*/
					vars.put("agree", "yes".equals(patent.getAct().getFlag()) ? "3" : "0");
				}
			actTaskService.complete(patent.getAct().getTaskId(), patent.getAct().getProcInsId(),
					patent.getAct().getComment(), vars);
		
	}
	
	/**
	 * 通过流程实例id获取信息
	 * @param procInsId
	 * @return
	 */
	public Patent getByProcInsId(String procInsId) {
		
		return dao.getByProcInsId(procInsId);
	}
	
	private void startGet(Patent patent) {
		
		/**
		 * 流程：
		 * 提交人——研发总监——财务总监——总裁——各地行政
		 */
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("userTask1", UserUtils.getByRoleEnname("thd_executive_director").get(0));
		vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
		vars.put("userTask3", UserUtils.getByRoleEnname("CEO").get(0));
		//获取区域名
		String areaName = UserUtils.getUser().getOffice().getArea().getName();
		if("北京".equals(areaName)){
			vars.put("userTask4", UserUtils.getByRoleEnname("thd_beijing_xingzheng").get(0));
		}else if("合肥市".equals(areaName)){
			vars.put("userTask4", UserUtils.getByRoleEnname("thd_hefei_xingzheng").get(0));
		}else if("济南市".equals(areaName)){
			vars.put("userTask4", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
		}else if("上海市".equals(areaName)){
			vars.put("userTask4", UserUtils.getByRoleEnname("thd_shanghai_xingzheng").get(0));
		}else if("杭州市".equals(areaName)){
			vars.put("userTask4", UserUtils.getByRoleEnname("thd_hangzhou_xingzheng").get(0));
		}else if("福州市".equals(areaName)){
			vars.put("userTask4", UserUtils.getByRoleEnname("thd_fuzhou_xingzheng").get(0));
		}else{
			return;
		}
		actTaskService.startProcess(ActUtils.THD_PATENT[0],ActUtils.THD_PATENT[1], patent.getId(),
				patent.getUser().getLoginName()+"-专利申请", vars);
	}

	
	/**
	 * 撤销流程
	 * @param procInsId
	 */
	@Transactional(readOnly=false)
	public void deletePatent(String procInsId) {
		patentDao.deletePatent(procInsId);
		
	}
	/**
	 * 撤销流程
	 * @param procInsId
	 */
	@Transactional(readOnly=false)
	public void deleteTask(String procInsId) {
		patentDao.deleteTask(procInsId);
		
	}
	
	/**
	 * 行政人员查看所属区域申请列表
	 * @param page
	 * @param patent
	 * @return
	 */
	@Transactional(readOnly=false)
	public Page<Patent> findPage3(Page<Patent> page, Patent patent) {
		patent.setPage(page);
		System.out.println(UserUtils.getUser().getOffice().getArea().getName());
		page.setList(dao.findList2(UserUtils.getUser().getOffice().getArea().getName()));
		return page;
	}

	/**
	 * 财务人员查看员工申请列表
	 * @param page
	 * @param patent
	 * @return
	 */
	public Page<Patent> findPage4(Page<Patent> page, Patent patent) {
		patent.setPage(page);
		page.setList(dao.findList3(patent));
		return page;
	}
}
