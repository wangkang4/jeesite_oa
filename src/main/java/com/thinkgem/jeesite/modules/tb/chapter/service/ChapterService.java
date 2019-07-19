package com.thinkgem.jeesite.modules.tb.chapter.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.chapter.dao.ChapterDao;
import com.thinkgem.jeesite.modules.tb.chapter.entity.Chapter;
import com.thinkgem.jeesite.modules.tb.chapter.entity.ChapterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 用印申请
 * @update Mr.dong
 *
 */
@Service
public class ChapterService extends CrudService<ChapterDao, Chapter> {

	@Autowired
	private ChapterDao chapterDao;
	@Autowired
	private ActTaskService actTaskService;
	
	public List<ChapterType> selectChapterType(){
		return chapterDao.selectChapterType();
	}
	public List<ChapterType> selectFileType(){
		return chapterDao.selectFileType();
	}
	
	@Transactional(readOnly = false)
	public void save(Chapter chapter){
		if(StringUtils.isBlank(chapter.getId())){
			chapter.preInsert();
			dao.insert(chapter);
			//启动流程
			startGet(chapter);
			
		}else{//驳回列表
			chapter.preUpdate();
			dao.update(chapter);
			if ("yes".equals(chapter.getAct().getFlag())) {
				chapter.getAct().setComment("[重申]" + chapter.getReason());
				chapter.setStatu("审核中");
				chapterDao.updateStatu(chapter);
			} else {
				chapter.getAct().setComment("[销毁]");
				chapter.setStatu("销毁");
				chapterDao.updateStatu(chapter);
				dao.delete(chapter);
			}
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("rebut", "yes".equals(chapter.getAct().getFlag()) ? "1" : "0");
			String taskId = chapterDao.selectTaskIdByProcinsId(chapter.getAct().getProcInsId());
			actTaskService.complete(taskId, chapter.getAct().getProcInsId(),
					chapter.getAct().getComment(), chapter.getReason(), vars);
		}
		
	}
	public void startGet(Chapter chapter){
		Map<String, Object> vars = new HashMap<String,Object>();
		vars.put("chapterTime", chapter.getChapterTime());
		vars.put("assgine", chapter.getAct().getAssignee());
		
		/**
		 * @update Mr.dong
		 * 用印申请流程思路：
		 * 首先获取当前人的上级领导
		 * 	1.如果直属上级是总裁
		 * 	则：提交人--财务总监--各地行政
		 * 	2.如果直属上级是办事处级别负责人
		 *  则：提交人--直属上级--财务总监--各地行政
		 *  3.如果直属上级是部门级别负责人
		 *  则：提交人--直属上级的上级--财务总监--各地行政
		 */
		
		/**获取当前人的直属上级*/
		String leaderId = UserUtils.getUser().getLeader();//直属上级id
		String leaderName = UserUtils.get(leaderId).getName();//直属上级姓名
		/**获取直属上级的角色*/
		String roleStr = UserUtils.get(leaderId).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");//
		/**判断是否为这一角色的人*/
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛总裁");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean thd_c = Arrays.asList(roles).contains("桃花岛部门级别负责人");
		
		if(thd_a){
			vars.put("userTask1", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			/**获取当前人的角色*/
			String roleStr_now = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();
			String[] roles_now = roleStr_now.split(",");
			if(Arrays.asList(roles_now).contains("桃花岛上海区负责人")){
				vars.put("userTask2", UserUtils.getByRoleEnname("thd_shanghai_xingzheng").get(0));
			}else if(Arrays.asList(roles_now).contains("桃花岛北京区负责人")){
				vars.put("userTask2", UserUtils.getByRoleEnname("thd_beijing_xingzheng").get(0));
			}else if(Arrays.asList(roles_now).contains("桃花岛合肥区负责人")||Arrays.asList(roles_now).contains("桃花岛执行董事")){
				vars.put("userTask2", UserUtils.getByRoleEnname("thd_hefei_xingzheng").get(0));
			}else if(Arrays.asList(roles_now).contains("桃花岛杭州区负责人")){
				vars.put("userTask2", UserUtils.getByRoleEnname("thd_hangzhou_xingzheng").get(0));
			}else if(Arrays.asList(roles_now).contains("桃花岛济南区负责人")){
				vars.put("userTask2", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
			}else if(Arrays.asList(roles_now).contains("桃花岛福州区负责人")){
				vars.put("userTask2", UserUtils.getByRoleEnname("thd_fuzhou_xingzheng").get(0));
			}else{
				return;
			}
		}else if(thd_b){
			vars.put("userTask1", leaderName);
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			if(Arrays.asList(roles).contains("桃花岛上海区负责人")){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_shanghai_xingzheng").get(0));
			}else if(Arrays.asList(roles).contains("桃花岛北京区负责人")){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_beijing_xingzheng").get(0));
			}else if(Arrays.asList(roles).contains("桃花岛合肥区负责人")||Arrays.asList(roles).contains("桃花岛执行董事")){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_hefei_xingzheng").get(0));
			}else if(Arrays.asList(roles).contains("桃花岛杭州区负责人")){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_hangzhou_xingzheng").get(0));
			}else if(Arrays.asList(roles).contains("桃花岛济南区负责人")){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
			}else if(Arrays.asList(roles).contains("桃花岛福州区负责人")){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_fuzhou_xingzheng").get(0));
			}else{
				return;
			}
		}else if(thd_c){
			/**获取上级的上级领导姓名*/
			String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();
			/**获取上级的上级领导角色*/
			String roleStr_leader = UserUtils.get(UserUtils.get(UserUtils.get(leaderId).getLeader()).getId()).getRoleNames();
			String[] roles_leader = roleStr_leader.split(",");
			vars.put("userTask1", leader_leader);
			vars.put("userTask2", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
			if(Arrays.asList(roles_leader).contains("桃花岛上海区负责人")){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_shanghai_xingzheng").get(0));
			}else if(Arrays.asList(roles_leader).contains("桃花岛北京区负责人")){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_beijing_xingzheng").get(0));
			}else if(Arrays.asList(roles_leader).contains("桃花岛合肥区负责人")||Arrays.asList(roles_leader).contains("桃花岛执行董事")){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_hefei_xingzheng").get(0));
			}else if(Arrays.asList(roles_leader).contains("桃花岛杭州区负责人")){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_hangzhou_xingzheng").get(0));
			}else if(Arrays.asList(roles_leader).contains("桃花岛济南区负责人")){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
			}else if(Arrays.asList(roles_leader).contains("桃花岛福州区负责人")){
				vars.put("userTask3", UserUtils.getByRoleEnname("thd_fuzhou_xingzheng").get(0));
			}else{
				return;
			}
		}
		actTaskService.startProcess(ActUtils.THD_SEAL[0], ActUtils.THD_SEAL[1], chapter.getId(), chapter.getReason(), vars);
		
	}
	@Transactional(readOnly = false)
	public void auditSave(Chapter chapter){
		// 设置审核意见
		chapter.getAct().setComment(
				("yes".equals(chapter.getAct().getFlag()) ? "[同意]" : "[驳回]") + chapter.getAct().getComment());
		chapter.preUpdate();
		
		/**获取当前审核人的角色*/
		String roleStr = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
		String[] roles = roleStr.split(",");
		/**判断是否为这一角色的人*/
		Boolean thd_a = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
		Boolean thd_b = Arrays.asList(roles).contains("桃花岛财务总监");
		Boolean thd_c = Arrays.asList(roles).contains("桃花岛行政级别");
		Map<String, Object> vars = Maps.newHashMap();
		if (thd_a&&!thd_b) {
			chapter.setPrthreeText(chapter.getAct().getComment());
			dao.updatePrthreeText(chapter);
		}
		if (thd_b) {
			chapter.setPrfourText(chapter.getAct().getComment());
			dao.updatePrfourText(chapter);
		}
		if (thd_c) {
			chapter.setPrfiveText(chapter.getAct().getComment());
			dao.updatePrfiveText(chapter);
		}
		/**流程正常走向 同意1*/
		vars.put("agree", "yes".equals(chapter.getAct().getFlag()) ? "1" : "0");
		if(!"yes".equals(chapter.getAct().getFlag())){
			chapter.setStatu("驳回");
			chapterDao.updateStatu(chapter);
		}
		/**如果为行政级别人员审核则改变流程走向 同意则agree==3结束*/
		if("yes".equals(chapter.getAct().getFlag())&&thd_c){
			chapter.setStatu("审核通过");
			chapterDao.updateStatu(chapter);
			vars.put("agree", "yes".equals(chapter.getAct().getFlag()) ? "3" : "0");
		}
		
		actTaskService.complete(chapter.getAct().getTaskId(), chapter.getAct().getProcInsId(),
				chapter.getAct().getComment(), vars);
		
			
	}
	public Chapter getByProcInsId(String procInsId){
		return dao.getByProcInsId(procInsId);
	}
	public Page<Chapter> findEmployeesPage(Page<Chapter> page, Chapter chapter) {
		chapter.setPage(page);
		page.setList(dao.findEmployeesList(chapter));
		return page;
	}
	/**
	 * 行政人员查看所属区域申请列表
	 * @param page
	 * @param invoiceApply
	 * @return
	 */
	@Transactional(readOnly=false)
	public Page<Chapter> findPage3(Page<Chapter> page, Chapter chapter) {
		chapter.setPage(page);
		System.out.println(UserUtils.getUser().getOffice().getArea().getName());
		page.setList(dao.findList2(UserUtils.getUser().getOffice().getArea().getName()));
		return page;
	}
	
	/**
	 * 验证印章是否被外借使用
	 * @param ct
	 */
	public int findChapterUse(String ct) {
		return chapterDao.findChapterUse(ct);
	}
	/**
	 * 查询正在被使用的外借章
	 * @param ct
	 * @return
	 */
	public String findChapterUseName(String ct) {
		return chapterDao.findChapterUseName(ct);
	}
}
