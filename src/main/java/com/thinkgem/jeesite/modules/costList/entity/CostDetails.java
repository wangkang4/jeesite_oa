package com.thinkgem.jeesite.modules.costList.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 费用统计详情表
 *
 * @author shengchanghao
 */
public class CostDetails extends DataEntity<CostDetails> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 费用详情表id
     */
    private Integer detailsId;
    /**
     * 费用详情表内容
     */
    private String detailsName;
    /**
     * 关联统计总表id
     */
    private Integer costId;

    public Integer getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
    }

    public String getDetailsName() {
        return detailsName;
    }

    public void setDetailsName(String detailsName) {
        this.detailsName = detailsName;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
