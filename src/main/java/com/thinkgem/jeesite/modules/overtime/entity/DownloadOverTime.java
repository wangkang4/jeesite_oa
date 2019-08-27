package com.thinkgem.jeesite.modules.overtime.entity;
/**
 * @Copyright: Copyright (c) 2019-2020 Digitalchina CO.,LTD. All rights reserved.
 * @Company: 北京桃花岛信息技术有限公司
 * @version: V1.0
 * @修改历史:
 */

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * @Title: jeesite_oa
 * @Description: 导出上个月加班情况列表表格格式
 * @author: gangzi
 */
public class DownloadOverTime extends DataEntity<DownloadOverTime> {

    private static final long serialVersionUID = 1L;
    /**姓名*/
    private String userName;
    /**部门*/
    private String officeName;
    /**上个月总加班时间*/
    private String days;

    @ExcelField(title = "姓名", sort = 10)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ExcelField(title = "部门", sort = 20)
    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }


    @ExcelField(title = "加班总时间", sort = 30)
    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
