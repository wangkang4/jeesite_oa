package com.thinkgem.jeesite.modules.CostExcel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.CostExcel.entity.CostRecondExcelFile;

@MyBatisDao
public interface CostRecondExcelFileDao extends CrudDao<CostRecondExcelFile> {

    /**
     * 插入一个Excel文件信息
     */
    public int insertone(CostRecondExcelFile entity);

}
