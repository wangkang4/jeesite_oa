package com.thinkgem.jeesite.modules.que.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProject;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

/**
 * 项目问题记录实体类
 *
 * @param <QuestionRecord>
 * @param <T>
 * @author chengxin
 */
public class QuestionRecord extends DataEntity<QuestionRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 项目对象
     */
    private PmsProject pms;
    /**
     * 负责人对象
     */
    private User user;
    /**
     * 问题id
     */
    private String id;
    /**
     * 项目id
     */
    private String projectId;
    /**
     * 问题标题
     */
    private String problemName;    //
    /**
     * 问题类型
     */
    private String problemType;  //
    /**
     * 问题来源
     */
    private String problemSource;
    /**
     * 问题状态
     */
    private String problemState;
    /**
     * 负责人id
     */
    private String leaderId;
    /**
     * 提交时间
     */
    private Date submitTime;
    /**
     * 到期时间
     */
    private Date dueTime;
    /**
     * 严重程度
     */
    private String problemUrgent;
    /**
     * 详细描述
     */
    private String problemDescribe;
    /**
     * 附件id
     */
    private String attachId;
    /**
     * 附件地址
     */
    private String attachAddress;
    /**
     * 删除标识
     */
    private char deleteMark;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 更新时间
     */
    private String updateTime;

    public QuestionRecord() {
        super ();
    }

    public QuestionRecord(PmsProject pms, User user, String id, String projectId, String problemName,
                          String problemType, String problemSource, String problemState, String leaderId, Date submitTime,
                          Date dueTime, String problemUrgent, String problemDescribe, String attachId, String createdName,
                          String createdTime, String updateName, String updateTime, char deleteMark) {
        super ();
        this.pms = pms;
        this.user = user;
        this.id = id;
        this.projectId = projectId;
        this.problemName = problemName;
        this.problemType = problemType;
        this.problemSource = problemSource;
        this.problemState = problemState;
        this.leaderId = leaderId;
        this.submitTime = submitTime;
        this.dueTime = dueTime;
        this.problemUrgent = problemUrgent;
        this.problemDescribe = problemDescribe;
        this.attachId = attachId;
        this.deleteMark = deleteMark;
    }


    public PmsProject getPms() {
        return pms;
    }

    public void setPms(PmsProject pms) {
        this.pms = pms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemSource() {
        return problemSource;
    }

    public void setProblemSource(String problemSource) {
        this.problemSource = problemSource;
    }

    public String getProblemState() {
        return problemState;
    }

    public void setProblemState(String problemState) {
        this.problemState = problemState;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public String getProblemUrgent() {
        return problemUrgent;
    }

    public void setProblemUrgent(String problemUrgent) {
        this.problemUrgent = problemUrgent;
    }

    public String getProblemDescribe() {
        return problemDescribe;
    }

    public void setProblemDescribe(String problemDescribe) {
        this.problemDescribe = problemDescribe;
    }

    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    public char getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(char deleteMark) {
        this.deleteMark = deleteMark;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((attachId == null) ? 0 : attachId.hashCode ());
        result = prime * result + deleteMark;
        result = prime * result + ((dueTime == null) ? 0 : dueTime.hashCode ());
        result = prime * result + ((id == null) ? 0 : id.hashCode ());
        result = prime * result + ((leaderId == null) ? 0 : leaderId.hashCode ());
        result = prime * result + ((pms == null) ? 0 : pms.hashCode ());
        result = prime * result + ((problemDescribe == null) ? 0 : problemDescribe.hashCode ());
        result = prime * result + ((problemName == null) ? 0 : problemName.hashCode ());
        result = prime * result + ((problemSource == null) ? 0 : problemSource.hashCode ());
        result = prime * result + ((problemState == null) ? 0 : problemState.hashCode ());
        result = prime * result + ((problemType == null) ? 0 : problemType.hashCode ());
        result = prime * result + ((problemUrgent == null) ? 0 : problemUrgent.hashCode ());
        result = prime * result + ((projectId == null) ? 0 : projectId.hashCode ());
        result = prime * result + ((submitTime == null) ? 0 : submitTime.hashCode ());
        result = prime * result + ((user == null) ? 0 : user.hashCode ());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass () != obj.getClass ())
            return false;
        QuestionRecord other = (QuestionRecord) obj;
        if (attachId == null) {
            if (other.attachId != null)
                return false;
        } else if (!attachId.equals ( other.attachId ))
            return false;
        if (deleteMark != other.deleteMark)
            return false;
        if (dueTime == null) {
            if (other.dueTime != null)
                return false;
        } else if (!dueTime.equals ( other.dueTime ))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals ( other.id ))
            return false;
        if (leaderId == null) {
            if (other.leaderId != null)
                return false;
        } else if (!leaderId.equals ( other.leaderId ))
            return false;
        if (pms == null) {
            if (other.pms != null)
                return false;
        } else if (!pms.equals ( other.pms ))
            return false;
        if (problemDescribe == null) {
            if (other.problemDescribe != null)
                return false;
        } else if (!problemDescribe.equals ( other.problemDescribe ))
            return false;
        if (problemName == null) {
            if (other.problemName != null)
                return false;
        } else if (!problemName.equals ( other.problemName ))
            return false;
        if (problemSource == null) {
            if (other.problemSource != null)
                return false;
        } else if (!problemSource.equals ( other.problemSource ))
            return false;
        if (problemState == null) {
            if (other.problemState != null)
                return false;
        } else if (!problemState.equals ( other.problemState ))
            return false;
        if (problemType == null) {
            if (other.problemType != null)
                return false;
        } else if (!problemType.equals ( other.problemType ))
            return false;
        if (problemUrgent == null) {
            if (other.problemUrgent != null)
                return false;
        } else if (!problemUrgent.equals ( other.problemUrgent ))
            return false;
        if (projectId == null) {
            if (other.projectId != null)
                return false;
        } else if (!projectId.equals ( other.projectId ))
            return false;
        if (submitTime == null) {
            if (other.submitTime != null)
                return false;
        } else if (!submitTime.equals ( other.submitTime ))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals ( other.user ))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "QuestionRecord [pms=" + pms + ", user=" + user + ", id=" + id + ", projectId=" + projectId
                + ", problemName=" + problemName + ", problemType=" + problemType + ", problemSource=" + problemSource
                + ", problemState=" + problemState + ", leaderId=" + leaderId + ", submitTime=" + submitTime
                + ", dueTime=" + dueTime + ", problemUrgent=" + problemUrgent + ", problemDescribe=" + problemDescribe
                + ", attachId=" + attachId + ", deleteMark=" + deleteMark + "]";
    }

    @Override
    public void preInsert() {
        // TODO Auto-generated method stub

    }

    @Override
    public void preUpdate() {
        // TODO Auto-generated method stub

    }

    public String getAttachAddress() {
        return attachAddress;
    }

    public void setAttachAddress(String attachAddress) {
        this.attachAddress = attachAddress;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


}
