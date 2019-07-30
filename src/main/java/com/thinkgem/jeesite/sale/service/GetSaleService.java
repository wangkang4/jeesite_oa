package com.thinkgem.jeesite.sale.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Business;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Special;
import com.thinkgem.jeesite.sale.dao.GetSaleDao;
import com.thinkgem.jeesite.sale.entity.DownloadGetSale;
import com.thinkgem.jeesite.sale.entity.GetSale;
import com.thinkgem.jeesite.sale.entity.GetSaleCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class GetSaleService extends CrudService<GetSaleDao, GetSale> {

    @Autowired
    private GetSaleDao getSaleDao;
    @Autowired
    private ActTaskService actTaskService;

//	public int updateNotAgree(GetSale getSale){
//		return getSaleDao.updateNotAgree(getSale);
//	}
//	
//	public int updateSuccess(GetSale getSale){
//		return getSaleDao.updateSuccess(getSale);
//	}

    public String getSaleDetailIdByProcInsId(String procInsId) {
        return dao.getSaleDetailIdByProcInsId(procInsId);
    }

    public GetSale getByProcInsId(String procInsId) {
        return dao.getByProcInsId(procInsId);
    }

//	public Page<GetSale> findPage(Page<GetSale> page, GetSale getSale) {
//		getSale.setPage(page);
//		page.setList(dao.findList(getSale));
//		return page;
//	}

    public Page<GetSale> findCWPage(Page<GetSale> page, GetSale getSale) {
        getSale.setPage(page);
        page.setList(getSaleDao.findCWPage(getSale));
        return page;
    }

    // 草稿列表；
    public Page<GetSale> findDraft(Page<GetSale> page, GetSale getSale) {
        getSale.setPage(page);
        page.setList(getSaleDao.findDraft(getSale));
        return page;
    }

    // 报销列表
    public Page<GetSale> findPages(Page<GetSale> page, GetSale getSale) {
        getSale.setPage(page);
        page.setList(getSaleDao.findList(getSale));
        return page;
    }

    public String getOfficeNameByUserId(String id) {
        return dao.getOfficeNameByUserId(id);
    }

    public Office getOfficeByUserId(String id) {
        return dao.getOfficeByUserId(id);
    }

    /**
     * 保存或编辑申请信息
     */
    @Override
    @Transactional(readOnly = false)
    public void save(GetSale getSale) {
        GetSaleCount count = new GetSaleCount();
        //首次提交申请
        if (StringUtils.isBlank(getSale.getId())) {
            getSale.setStatus(1);
            updateGetSale(getSale, count);
            dao.insert(getSale);
            startGet(getSale);
        } else {//有id 草稿，或者驳回
            if (getSale.getStatus() == 0) {//草稿
                if ("yes".equals(getSale.getAct().getFlag())) {//提交申请
                    getSale.setStatus(1);
                    getSale.preInsert();
                    updateGetSale(getSale, count);
                    dao.insert(getSale);
                    startGet(getSale);
                }
                if ("no".equals(getSale.getAct().getFlag())) {//销毁草稿
                    getSale.setStatu("销毁");
                    getSaleDao.updateStatu(getSale);
                    getSaleDao.updateNum(getSale.getSaleDetailId());//修改详情表中的出差编号为null
                    dao.delete(getSale);

                }
            } else {//驳回列表
                getSale.preUpdate();
                updateGetSale(getSale, count);
                dao.update(getSale);
                if ("yes".equals(getSale.getAct().getFlag())) {
                    getSale.getAct().setComment("[重申]" + getSale.getReason());
                    getSale.setStatu("审核中");
                    getSaleDao.updateStatu(getSale);
                } else {
                    getSale.getAct().setComment("[销毁]" + getSale.getAct().getComment());
                    getSale.setStatu("销毁");
                    getSaleDao.updateStatu(getSale);
                    getSaleDao.updateNum(getSale.getSaleDetailId());//修改详情表中的出差编号为null
                    dao.delete(getSale);
                }

                Map<String, Object> vars = Maps.newHashMap();
                vars.put("rebut", "yes".equals(getSale.getAct().getFlag()) ? "1" : "0");
                vars.put("forMoney", getSale.getForMoney());
                String taskId = getSaleDao.selectTaskIdByProcinsId(getSale.getAct().getProcInsId());
                actTaskService.complete(taskId, getSale.getAct().getProcInsId(),
                        getSale.getAct().getComment(), getSale.getReason(), vars);
            }
        }
    }

    public void updateGetSale(GetSale getSale, GetSaleCount count) {
        String userName = UserUtils.get(getSale.getUser().getId()).getName();
        String officeName = UserUtils.get(getSale.getUser().getId()).getOffice().getName();
        getSale.setReason(getSale.getCostType() + "-" + officeName + "-" + userName);
        String reason = getSale.getReason();
        Date day = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String time = sdf.format(day);
        if (StringUtils.isBlank(getSale.getsNum())) {//无编号，第一次提交
            getSale.preInsert();
            synchronized (count) {
                count = getSaleDao.selectCount();
                if (time.equals(count.getTime())) {
                    count.setCount(count.getCount() + 1);
                    getSaleDao.updateCount(count);
                    String num = String.format("%04d", count.getCount());
                    getSale.setReason(time + num + "-" + reason);
                } else {
                    count.setTime(time);
                    count.setCount(1);
                    getSaleDao.updateCount(count);
                    String num = String.format("%04d", count.getCount());
                    getSale.setReason(time + num + "-" + reason);
                }
            }
        } else {//驳回后修改标题
            getSale.setReason(getSale.getsNum() + "-" + reason);
        }
    }

    public void startGet(GetSale getSale) {
        Map<String, Object> vars = Maps.newHashMap();
        vars.put("forMoney", getSale.getForMoney());

        /**
         * @update Mr.dong
         * 先判断提交人的直属上级角色
         * 1.如果直属上级是总裁
         * 	则：提交人--到各地行政助理--到出纳--财务主管|总监--总经理
         * 2.如果直属上级是办事处负责人级别
         * 	则：提交人--各地行政助理--上级领导--到出纳--财务主管|总监--总经理
         * 3.如过直属上级是部门级别
         * 	则提交人--各地行政助理--上级领导的上级--到出纳--财务主管|总监--总经理
         *  **注意：在到达财务主管节点时，财务主管可将流程转至财务总监审核（userTask6）
         */

        /**
         * 获取当前人的直属上级
         */
        String leaderId = UserUtils.getUser().getLeader();//直属上级id
        String roleStr = UserUtils.get(leaderId).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
        String[] roles = roleStr.split(",");//

        /**判断是否为这一角色的人*/
        Boolean a_thd = Arrays.asList(roles).contains("桃花岛总裁");
        Boolean b_thd = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
        Boolean c_thd = Arrays.asList(roles).contains("桃花岛部门级别负责人");
        if (a_thd) {
            /**获取当前人的角色*/
            String roleStr_now = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();
            String[] roles_now = roleStr_now.split(",");//
            Boolean b1 = Arrays.asList(roles_now).contains("桃花岛上海区负责人");
            Boolean b2 = Arrays.asList(roles_now).contains("桃花岛北京区负责人");
            Boolean b3 = Arrays.asList(roles_now).contains("桃花岛合肥区负责人");
            Boolean b4 = Arrays.asList(roles_now).contains("桃花岛杭州区负责人");
            Boolean b5 = Arrays.asList(roles_now).contains("桃花岛济南区负责人");
            Boolean b6 = Arrays.asList(roles_now).contains("桃花岛福州区负责人");
            Boolean b7 = Arrays.asList(roles_now).contains("桃花岛执行董事");
            /**如果是北京区负责人*/
            if (b1) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_shanghai_xingzheng").get(0));
            }/**如果是上海区负责人*/
            else if (b2) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_beijing_xingzheng").get(0));
            }/**如果是合肥区负责人/执行董事*/
            else if (b3 || b7) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_hefei_xingzheng").get(0));
            }/**如果是杭州区负责人*/
            else if (b4) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_hangzhou_xingzheng").get(0));
            }/**如果是济南区负责人*/
            else if (b5) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
            }/**如果是福州区负责人*/
            else if (b6) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_fuzhou_xingzheng").get(0));
            } else {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
            }
            vars.put("userTask2", UserUtils.getByRoleEnname("thd_chuna").get(0));
            if (getSale.getForMoney() > 3000) {
                vars.put("userTask3", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
            } else {
                vars.put("userTask3", UserUtils.getByRoleEnname("thd_caiwuzhuguan").get(0));
            }
            vars.put("userTask4", UserUtils.getByRoleEnname("thd_general_manager").get(0));

        } else if (b_thd) {
            /**获取上级领导名*/
            String leaderName = UserUtils.get(leaderId).getName();//直属上级姓名
            if (Arrays.asList(roles).contains("桃花岛上海区负责人")) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_shanghai_xingzheng").get(0));
            } else if (Arrays.asList(roles).contains("桃花岛北京区负责人")) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_beijing_xingzheng").get(0));
            } else if (Arrays.asList(roles).contains("桃花岛合肥区负责人") || Arrays.asList(roles).contains("桃花岛执行董事")) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_hefei_xingzheng").get(0));
            } else if (Arrays.asList(roles).contains("桃花岛杭州区负责人")) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_hangzhou_xingzheng").get(0));
            } else if (Arrays.asList(roles).contains("桃花岛济南区负责人")) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
            } else if (Arrays.asList(roles).contains("桃花岛福州区负责人")) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_fuzhou_xingzheng").get(0));
            } else {
                return;
            }
            vars.put("userTask2", leaderName);
            vars.put("userTask3", UserUtils.getByRoleEnname("thd_chuna").get(0));
            if (getSale.getForMoney() > 3000) {
                vars.put("userTask4", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
            } else {
                vars.put("userTask4", UserUtils.getByRoleEnname("thd_caiwuzhuguan").get(0));
            }
            vars.put("userTask5", UserUtils.getByRoleEnname("thd_general_manager").get(0));

        } else if (c_thd) {

            /**获取上级的上级领导姓名*/
            String leader_leader = UserUtils.get(UserUtils.get(leaderId).getLeader()).getName();//
            /**获取上级的上级领导角色*/
            String roleStr_leader = UserUtils.get(UserUtils.get(UserUtils.get(leaderId).getLeader()).getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
            String[] roles_leader = roleStr_leader.split(",");//
            if (Arrays.asList(roles_leader).contains("桃花岛上海区负责人")) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_shanghai_xingzheng").get(0));
            } else if (Arrays.asList(roles_leader).contains("桃花岛北京区负责人")) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_beijing_xingzheng").get(0));
            } else if (Arrays.asList(roles_leader).contains("桃花岛合肥区负责人") || Arrays.asList(roles_leader).contains("桃花岛执行董事")) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_hefei_xingzheng").get(0));
            } else if (Arrays.asList(roles_leader).contains("桃花岛杭州区负责人")) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_hangzhou_xingzheng").get(0));
            } else if (Arrays.asList(roles_leader).contains("桃花岛济南区负责人")) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_jinan_xingzheng").get(0));
            } else if (Arrays.asList(roles_leader).contains("桃花岛福州区负责人")) {
                vars.put("userTask1", UserUtils.getByRoleEnname("thd_fuzhou_xingzheng").get(0));
            } else {
                return;
            }
            vars.put("userTask2", leader_leader);
            vars.put("userTask3", UserUtils.getByRoleEnname("thd_chuna").get(0));
            if (getSale.getForMoney() > 3000) {
                vars.put("userTask4", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
            } else {
                vars.put("userTask4", UserUtils.getByRoleEnname("thd_caiwuzhuguan").get(0));
            }
            vars.put("userTask5", UserUtils.getByRoleEnname("thd_general_manager").get(0));
        }
        vars.put("userTask6", UserUtils.getByRoleEnname("thd_caiwuzongjian").get(0));
        actTaskService.startProcess(ActUtils.THD_GETSALE[0], ActUtils.THD_GETSALE[1], getSale.getId(),
                getSale.getReason(), vars);

    }

    @Transactional(readOnly = false)
    public void saveDraft(GetSale getSale) {
        if (StringUtils.isBlank(getSale.getId())) {
            getSale.preInsert();
            dao.insert(getSale);
        } else {
            getSale.preUpdate();
            dao.update(getSale);
        }
    }

    /**
     * 不同环节的审核内容保存
     */
    @Transactional(readOnly = false)
    public void auditSave(GetSale getSale) {
        Map<String, Object> vars = Maps.newHashMap();
        // 设置审核意见
        if ("yes".equals(getSale.getAct().getFlag())) {
            getSale.getAct().setComment("[同意]" + getSale.getAct().getComment());
        } else if ("no".equals(getSale.getAct().getFlag())) {
            getSale.getAct().setComment("[驳回]" + getSale.getAct().getComment());
        } else {
            getSale.getAct().setComment("[有疑问]" + getSale.getAct().getComment());
        }
        getSale.preUpdate();

        vars.put("agree", !"no".equals(getSale.getAct().getFlag()) ? "1" : "0");

        /**获取当前审核人的角色*/
        String roleStr = UserUtils.get(UserUtils.getUser().getId()).getRoleNames();//如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
        String[] roles = roleStr.split(",");
        /**判断是否为这一角色的人*/
        Boolean thd_a = Arrays.asList(roles).contains("桃花岛行政级别");
        Boolean thd_b = Arrays.asList(roles).contains("桃花岛办事处级别负责人");
        Boolean thd_c = Arrays.asList(roles).contains("桃花岛出纳");
        Boolean thd_d = Arrays.asList(roles).contains("桃花岛财务主管");
        Boolean thd_e = Arrays.asList(roles).contains("桃花岛财务总监");
        Boolean thd_f = Arrays.asList(roles).contains("桃花岛总经理");
        // 对不同环节的业务逻辑进行操作
        if (!"yes".equals(getSale.getAct().getFlag())) {
            getSale.setStatu("驳回");
            getSaleDao.updateStatu(getSale);
        }
        if ("yes".equals(getSale.getAct().getFlag()) && (thd_a || thd_b)) {
            getSale.setStatu("审核中");
            getSaleDao.updateStatu(getSale);
        }
        if ("yes".equals(getSale.getAct().getFlag()) && (thd_c || thd_d || thd_e)) {
            getSale.setStatu("审核通过");
            getSaleDao.updateStatu(getSale);
        }
        if ("yes".equals(getSale.getAct().getFlag()) && thd_f) {
            getSale.setStatu("同意");
            getSaleDao.updateStatu(getSale);
            vars.put("agree", "yes".equals(getSale.getAct().getFlag()) ? "3" : "0");
        }
        /**各地行政*/
        if (thd_a) {
            getSale.setPrText(getSale.getAct().getComment());
            dao.updatePrText(getSale);
        }
        /**办事处级别*/
        if (thd_b) {
            getSale.setLeadertwoText(getSale.getAct().getComment());
            dao.updateLeadertwoText(getSale);
        }
        /**出纳*/
        if (thd_c) {
            getSale.setPrthreeText(getSale.getAct().getComment());
            dao.updateprthreeText(getSale);
        }
        /**财务主管*/
        if (thd_d) {
            getSale.setPrfourText(getSale.getAct().getComment());
            dao.updateprfourText(getSale);
            if ("doubt".equals(getSale.getAct().getFlag())) {
                vars.put("agree", "doubt".equals(getSale.getAct().getFlag()) ? "2" : "0");
            }
        }
        /**财务总监*/
        if (thd_e) {
            getSale.setPrtwoText(getSale.getAct().getComment());
            dao.updateprtwoText(getSale);
        }
        if (thd_f) {
            getSale.setPrfiveText(getSale.getAct().getComment());
            dao.updateprfiveText(getSale);
        }
        // 提交流程任务
        actTaskService.complete(getSale.getAct().getTaskId(), getSale.getAct().getProcInsId(),
                getSale.getAct().getComment(), vars);
    }

    public void updateForm(GetSale getSale) {
        getSaleDao.updateForm(getSale);
    }


    //实现报销撤回功能
    @Transactional(readOnly = false)
    public void withdraw(GetSale getSale) {
        getSale.setStatu("撤回");
        getSaleDao.updateStatu(getSale);
        dao.withdraw(getSale.getId());
    }

    //增加上传文件的地址
    @Transactional(readOnly = false)
    public void upload(GetSale getSale) {
        getSaleDao.upload(getSale);
    }

    //会退的数据存入一个记录的表中
    @Transactional(readOnly = false)
    public void insertRollBack(String taskId, String process) {
        getSaleDao.insertRollBack(taskId, process);
    }

    //查询某人的专项费用预审批
    public List<Special> selectSpecial(String id) {
        return getSaleDao.selectSpecial(id);
    }

    //查询某人的业务招待预审批
    public List<Business> selectBusiness(String id) {
        return getSaleDao.selectBusiness(id);
    }

    public String selectTaskKey(String procInstId) {
        return getSaleDao.selectTaskKey(procInstId);
    }

    /**
     * 行政人员查看所属区域申请列表
     *
     * @param page
     * @param getSale
     * @return
     */
    @Transactional(readOnly = false)
    public Page<GetSale> findPage2(Page<GetSale> page, GetSale getSale) {
        getSale.setPage(page);
        System.out.println(UserUtils.getUser().getOffice().getArea().getName());
        page.setList(dao.findList2(UserUtils.getUser().getOffice().getArea().getName()));
        return page;
    }

    /**
     * @return java.util.List<com.thinkgem.jeesite.sale.entity.DownloadGetSale>
     * @Author gangzi
     * @Description 报销申请中员工报销列表导出表格
     * @Date 19:45 2019/6/5
     * @Param [getSale]
     **/
    public List<DownloadGetSale> downList(GetSale getSale) {
        return getSaleDao.downList(getSale);
    }
}
