package com.thinkgem.jeesite.common.mapper.insertRollBack;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * @ClassName: GetSaleSummary
 * @Description: TODO(用于审批环节打印)
 * @author: WangFucheng
 * @date 2018年4月11日 下午2:17:18
 */
public class HistoryPROC extends DataEntity<HistoryPROC> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String assignee;
    private String endTime;
    private String message;

    public String getName() {
        return name;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}