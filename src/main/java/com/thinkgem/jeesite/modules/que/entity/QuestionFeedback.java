package com.thinkgem.jeesite.modules.que.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 问题反馈对象实体类
 *
 * @author chengxin
 */
public class QuestionFeedback extends DataEntity<QuestionFeedback> {

    private static final long serialVersionUID = 1L;

    /**
     * 问题记录对象
     */
    private QuestionRecord questionRecord;
    /**
     * 记录id
     */
    private String id;
    /**
     * 问题id
     */
    private String ProblemId;
    /**
     * 问题解决时间
     */
    private Date solveTime;    //
    /**
     * 问题解决方案
     */
    private String solveProgramme;  //
    /**
     * 问题其他说明
     */
    private String remarks;
    /**
     * 创建者
     */
    private String createdName;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 更新者
     */
    private String updateName;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 删除标识
     */
    private char deleteMark;

    public QuestionFeedback() {
        super ();
    }

    public QuestionFeedback(QuestionRecord questionRecord, String id, String problemId, Date solveTime,
                            String solveProgramme, String remarks, String createdName, String createdTime, String updateName,
                            String updateTime, char deleteMark) {
        super ();
        this.questionRecord = questionRecord;
        this.id = id;
        ProblemId = problemId;
        this.solveTime = solveTime;
        this.solveProgramme = solveProgramme;
        this.remarks = remarks;
        this.createdName = createdName;
        this.createdTime = createdTime;
        this.updateName = updateName;
        this.updateTime = updateTime;
        this.deleteMark = deleteMark;
    }


    public QuestionRecord getQuestionRecord() {
        return questionRecord;
    }

    public void setQuestionRecord(QuestionRecord questionRecord) {
        this.questionRecord = questionRecord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProblemId() {
        return ProblemId;
    }

    public void setProblemId(String problemId) {
        ProblemId = problemId;
    }

    public Date getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
    }

    public String getSolveProgramme() {
        return solveProgramme;
    }

    public void setSolveProgramme(String solveProgramme) {
        this.solveProgramme = solveProgramme;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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
        result = prime * result + ((ProblemId == null) ? 0 : ProblemId.hashCode ());
        result = prime * result + ((createdName == null) ? 0 : createdName.hashCode ());
        result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode ());
        result = prime * result + deleteMark;
        result = prime * result + ((id == null) ? 0 : id.hashCode ());
        result = prime * result + ((questionRecord == null) ? 0 : questionRecord.hashCode ());
        result = prime * result + ((remarks == null) ? 0 : remarks.hashCode ());
        result = prime * result + ((solveProgramme == null) ? 0 : solveProgramme.hashCode ());
        result = prime * result + ((solveTime == null) ? 0 : solveTime.hashCode ());
        result = prime * result + ((updateName == null) ? 0 : updateName.hashCode ());
        result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode ());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals ( obj ))
            return false;
        if (getClass () != obj.getClass ())
            return false;
        QuestionFeedback other = (QuestionFeedback) obj;
        if (ProblemId == null) {
            if (other.ProblemId != null)
                return false;
        } else if (!ProblemId.equals ( other.ProblemId ))
            return false;
        if (createdName == null) {
            if (other.createdName != null)
                return false;
        } else if (!createdName.equals ( other.createdName ))
            return false;
        if (createdTime == null) {
            if (other.createdTime != null)
                return false;
        } else if (!createdTime.equals ( other.createdTime ))
            return false;
        if (deleteMark != other.deleteMark)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals ( other.id ))
            return false;
        if (questionRecord == null) {
            if (other.questionRecord != null)
                return false;
        } else if (!questionRecord.equals ( other.questionRecord ))
            return false;
        if (remarks == null) {
            if (other.remarks != null)
                return false;
        } else if (!remarks.equals ( other.remarks ))
            return false;
        if (solveProgramme == null) {
            if (other.solveProgramme != null)
                return false;
        } else if (!solveProgramme.equals ( other.solveProgramme ))
            return false;
        if (solveTime == null) {
            if (other.solveTime != null)
                return false;
        } else if (!solveTime.equals ( other.solveTime ))
            return false;
        if (updateName == null) {
            if (other.updateName != null)
                return false;
        } else if (!updateName.equals ( other.updateName ))
            return false;
        if (updateTime == null) {
            if (other.updateTime != null)
                return false;
        } else if (!updateTime.equals ( other.updateTime ))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "QuestionFeedback [questionRecord=" + questionRecord + ", id=" + id + ", ProblemId=" + ProblemId
                + ", solveTime=" + solveTime + ", solveProgramme=" + solveProgramme + ", remarks=" + remarks
                + ", createdName=" + createdName + ", createdTime=" + createdTime + ", updateName=" + updateName
                + ", updateTime=" + updateTime + ", deleteMark=" + deleteMark + "]";
    }

}
