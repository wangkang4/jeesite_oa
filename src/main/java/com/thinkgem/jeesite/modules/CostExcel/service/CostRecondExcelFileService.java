package com.thinkgem.jeesite.modules.CostExcel.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.CostExcel.dao.CostRecondExcelFileDao;
import com.thinkgem.jeesite.modules.CostExcel.entity.CostRecondExcelFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class CostRecondExcelFileService extends CrudService<CostRecondExcelFileDao, CostRecondExcelFile> {

    @Autowired
    private CostRecondExcelFileDao costRecondExcelFileDao;

    /**
     * 插入一条Excel文件信息
     */
    public int insertone(CostRecondExcelFile entity) {
        return costRecondExcelFileDao.insertone ( entity );
    }

}
