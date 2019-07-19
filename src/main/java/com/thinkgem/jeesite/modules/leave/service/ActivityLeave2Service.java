package com.thinkgem.jeesite.modules.leave.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.leave.dao.ActivityLeave2Dao;
import com.thinkgem.jeesite.modules.leave.entity.ActivityLeave2;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class ActivityLeave2Service extends CrudService<ActivityLeave2Dao, ActivityLeave2> {

    @Autowired
    private ActTaskService actTaskService;

    @Autowired
    private ActivityLeave2Dao activityLeave2Dao;

    public ActivityLeave2 getByProcInsId(String procInsId) {
        return dao.getByProcInsId ( procInsId );
    }

    public Page<ActivityLeave2> findPage(Page<ActivityLeave2> page, ActivityLeave2 activityLeave2) {
        activityLeave2.setPage ( page );
        page.setList ( dao.findList ( activityLeave2 ) );
        return page;
    }

    public String getOfficeNameByUserId(String id) {
        return dao.getOfficeNameByUserId ( id );
    }

    public Office getOfficeByUserId(String id) {
        return dao.getOfficeByUserId ( id );
    }


    //更新消除的请假天数
    @Transactional(readOnly = false)
    public void updateRemoveDays(ActivityLeave2 activityLeave2) {
        dao.updateRemoveDays ( activityLeave2 );
    }


    /**
     * 保存或编辑申请信息
     */
    @Transactional(readOnly = false)
    public void save(ActivityLeave2 activityLeave2) {
        if (StringUtils.isBlank ( activityLeave2.getId () )) {

            activityLeave2.preInsert ();
            //把请假天数改为小时数
            Double days = activityLeave2.getDays ();
            Double hours = days * 8;
            activityLeave2.setDays ( hours );
            activityLeave2.setStatus ( "审核中" );
            dao.insert ( activityLeave2 );
            Map<String, Object> vars = Maps.newHashMap ();
            vars.put ( "day", activityLeave2.getDays () );
            /*获取当前登录人的上级*/
            String leaderId = UserUtils.getUser ().getLeader ();//上级id
            String leaderName = UserUtils.get ( leaderId ).getName ();//姓名

            /*通过id获取指定人的角色分配
             *如有多个角色，会获取到以逗号区分的字符串。如“经理，总监，部长”
             * */
            String roleStr = UserUtils.get ( leaderId ).getRoleNames ();
            String[] roles = roleStr.split ( "," );//
            /*判断是否为这一角色的人*/
            Boolean b1 = Arrays.asList ( roles ).contains ( "桃花岛办事处级别负责人" );
            /**
             * 如果上级领导为办事处级别
             */
            if (b1) {
                vars.put ( "userTask1", leaderName );
            }
            /**
             * 如果请假时间大于一天 直接由办事处级别领导审批
             */
            else if (hours > 8) {
                /*获取上级的上级领导姓名*/
                String leader_leader = UserUtils.get ( UserUtils.get ( leaderId ).getLeader () ).getName ();
                vars.put ( "userTask1", leader_leader );
            }
            /**如果请假时间小于等于一天 直接由部门级别审核*/
            else if (hours <= 8) {
                vars.put ( "userTask1", leaderName );
            }
			/*else{
				vars.put("userTask1", leaderName);
				vars.put("userTask2", leader_leader);
			}*/
            else {
                return;
            }
            actTaskService.startProcess ( ActUtils.THD_LEAVE[0], ActUtils.THD_LEAVE[1], activityLeave2.getId (), activityLeave2.getReason (), vars );
        } else {
            activityLeave2.preUpdate ();
            dao.update ( activityLeave2 );
            if ("yes".equals ( activityLeave2.getAct ().getFlag () )) {
                activityLeave2.getAct ().setComment ( "[重申]" + activityLeave2.getReason () );
                activityLeave2.setStatus ( "审核中" );
                //设置申请状态码
                dao.update ( activityLeave2 );
            } else {
                activityLeave2.getAct ().setComment ( "[销毁]" + activityLeave2.getAct ().getComment () );
                String id = activityLeave2.getId ();
                activityLeave2.setStatus ( "销毁" );
                dao.update ( activityLeave2 );
                activityLeave2Dao.delete ( id );
            }
            Map<String, Object> vars = Maps.newHashMap ();
            vars.put ( "rebut", "yes".equals ( activityLeave2.getAct ().getFlag () ) ? "1" : "0" );
            String pid = activityLeave2.getProcInsId ();
            String taskId = activityLeave2Dao.getTaskIdByPId ( pid );
            actTaskService.complete ( taskId, activityLeave2.getAct ().getProcInsId (), activityLeave2.getAct ().getComment (), activityLeave2.getReason (), vars );
        }
    }

    /**
     * 不同环节的审核内容保存
     */
    @Transactional(readOnly = false)
    public void auditSave(ActivityLeave2 activityLeave2) {
        //设置审核意见
        activityLeave2.getAct ().setComment ( ("yes".equals ( activityLeave2.getAct ().getFlag () ) ? "[同意]" : "[驳回]") + activityLeave2.getAct ().getComment () );
        activityLeave2.preUpdate ();
        //对不同环节的业务逻辑进行操作
        activityLeave2.setStatus ( "审核中" );
        //各个审核环节,项目经理审核意见
        Map<String, Object> vars = Maps.newHashMap ();

        activityLeave2.setLeaderText ( activityLeave2.getAct ().getComment () );
        if (checkApproveNo ( activityLeave2.getLeaderText () )) {
            activityLeave2.setStatus ( "驳回" );
        }
        if (checkApproveYes ( activityLeave2.getLeaderText () )) {
            activityLeave2.setStatus ( "审核通过" );
        }
        dao.updateLeaderText ( activityLeave2 );
        vars.put ( "agree", "yes".equals ( activityLeave2.getAct ().getFlag () ) ? "3" : "0" );

        //提交流程任务
        actTaskService.complete ( activityLeave2.getAct ().getTaskId (), activityLeave2.getAct ().getProcInsId (), activityLeave2.getAct ().getComment (), vars );
    }

    //查询所有的请假列表
    public Page<ActivityLeave2> findAllPage(Page<ActivityLeave2> page, ActivityLeave2 activityLeave2) {
        activityLeave2.setPage ( page );
        page.setList ( dao.findAllList ( activityLeave2 ) );
        return page;
    }

    private boolean checkApproveNo(String result) {
        if (result != null && result != "" && result.contains ( "驳回" )) {
            return true;
        }
        return false;
    }

    private boolean checkApproveYes(String result) {
        if (result != null && result != "" && result.contains ( "同意" )) {
            return true;
        }
        return false;
    }

    public String findAttachNameByAttachAddress(String attachAddress) {
        String message = activityLeave2Dao.findAttachNameByAttachAddress ( attachAddress );
        return message;
    }

    ;
}
