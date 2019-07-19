package com.thinkgem.jeesite.sale.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * @Author gangzi
 * @Description 报销申请中员工报销列表导出表格格式
 * @Date 19:44 2019/6/5
 * @Param
 * @return
 **/
public class DownloadGetSale extends DataEntity<DownloadGetSale> {


    private static final long serialVersionUID = 1L;
    private String userName;
    private String areaName;
    private String officeName;
    private String reason;
    private String forMoney;
    private String createTime;

    @ExcelField(title = "姓名", sort = 10)
    public String getUserName() {
        return userName;
    }

    @ExcelField(title = "公司", sort = 20)
    public String getAreaName() {
        return areaName;
    }

    @ExcelField(title = "部门", sort = 25)
    public String getOfficeName() {
        return officeName;
    }

    @ExcelField(title = "标题", sort = 30)
    public String getReason() {
        return reason;
    }

    @ExcelField(title = "报销总金额", sort = 40)
    public String getForMoney() {
        return forMoney;
    }

    @ExcelField(title = "申请时间", sort = 50)
    public String getCreateTime() {
        return createTime;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setForMoney(String forMoney) {
        this.forMoney = forMoney;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


}
