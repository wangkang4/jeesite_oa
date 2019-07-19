package com.thinkgem.jeesite.common.mapper.insertRollBack;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.sale.entity.JumpCmd;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${adminPath}/tb/roll")
public class RollBackController extends BaseController {
    @Autowired
    private RollBackService rollBackService;

    @RequestMapping("back")
    public String rollback(String taskId) {
        //根据要跳转的任务ID获取其任务
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();
        HistoricTaskInstance hisTask = processEngine.getHistoryService ()
                .createHistoricTaskInstanceQuery ().taskId ( taskId )
                .singleResult ();
        //记录进入数据库
        rollBackService.insertRollBack ( taskId, hisTask.getProcessInstanceId () );
        //进而获取流程实例
        ProcessInstance instance = processEngine.getRuntimeService ()
                .createProcessInstanceQuery ()
                .processInstanceId ( hisTask.getProcessInstanceId () )
                .singleResult ();
        //取得流程定义
        ProcessDefinitionEntity definition = (ProcessDefinitionEntity) processEngine.getRepositoryService ().getProcessDefinition ( hisTask.getProcessDefinitionId () );
        //获取历史任务的Activity
        ActivityImpl hisActivity = definition.findActivity ( hisTask.getTaskDefinitionKey () );
        processEngine.getManagementService ().executeCommand ( new JumpCmd ( instance.getId (), hisActivity.getId () ) );
        return "redirect:" + adminPath + "/act/task/newHistoric";
    }

    /**
     * @param tableName 业务表名
     * @param id        主键id
     * @param view      重定向位置
     * @return String   返回类型
     * @throws
     * @Title: withdraw
     * @Description: TODO(撤回任务 ， 需要判断有无审批过 ， 否则已办任务中会展示空数据)
     * @author: WangFucheng
     */
    @RequestMapping("withdraw")
    public String withdraw(String tableName, String id, String view) {
        rollBackService.withdraw ( tableName, id );
        if ("special".equals ( view )) {
            return "redirect:" + adminPath + "/tb/money/" + view + "List";
        }
        if ("business".equals ( view )) {
            return "redirect:" + adminPath + "/tb/money/" + view + "List";
        }
        return "redirect:" + adminPath + "/tb/" + view + "/list";
    }
}
