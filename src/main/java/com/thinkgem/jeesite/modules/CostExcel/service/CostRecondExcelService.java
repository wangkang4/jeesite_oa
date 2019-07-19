package com.thinkgem.jeesite.modules.CostExcel.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.CostExcel.dao.CostRecondExcelDao;
import com.thinkgem.jeesite.modules.CostExcel.entity.CostRecondExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 费用Service
 *
 * @author tanchaoyang
 */
@Service
@Transactional(readOnly = false)
public class CostRecondExcelService extends CrudService<CostRecondExcelDao, CostRecondExcel> {

    @Autowired
    private CostRecondExcelDao costRecondExcelDao;

    public Page<CostRecondExcel> findPage(Page<CostRecondExcel> page, CostRecondExcel entity) {
        return super.findPage ( page, entity );
    }

    /**
     * 插入集合数据
     */
    public int insertList(List<CostRecondExcel> costRecondExcelList) {
        return costRecondExcelDao.insertList ( costRecondExcelList );
    }


    /**
     * 获取某条数据
     */
    public CostRecondExcel getone(String id) {
        return costRecondExcelDao.getone ( id );
    }


    /**
     * 更新某条数据
     */
    public void updateone(CostRecondExcel costRecondExcel) {
        costRecondExcelDao.updateone ( costRecondExcel );
    }


    /**
     * 插入某条数据
     */
    public void insertone(CostRecondExcel costRecondExcel) {
        costRecondExcelDao.insertone ( costRecondExcel );
    }


    /**
     * 删除某个
     */
    public void deleteone(String id) {
        costRecondExcelDao.deleteone ( id );
    }

    public String getUserNameByUserId(String userId) {
        return costRecondExcelDao.getUserName ( userId );
    }

    public String getOffieNameByOffie(String offieId) {
        return costRecondExcelDao.getOffieName ( offieId );
    }

    public void updateHaveSee(CostRecondExcel costRecondExcel) {
        costRecondExcelDao.uodateHaveSee ( costRecondExcel );
    }
}
