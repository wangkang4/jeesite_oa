package com.thinkgem.jeesite.modules.expense.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.expense.dao.ExpenseDetailDao;
import com.thinkgem.jeesite.modules.expense.entity.ExpenseDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 报销详情表全列插入Service层
 *
 * @author vat
 */
@Service
@Transactional(readOnly = false)
public class ExpenseDetailService extends CrudService<ExpenseDetailDao, ExpenseDetail> {
    @Autowired
    private ExpenseDetailDao expenseDetailDao;

    public void insertAll(List<ExpenseDetail> list) {
        expenseDetailDao.insertAll ( list );
    }

    public List<ExpenseDetail> findById(String saleDetailId) {
        List<ExpenseDetail> list = expenseDetailDao.findById ( saleDetailId );
        return list;
    }

    public void updateById(ExpenseDetail expenseDetail) {
        expenseDetailDao.updateById ( expenseDetail );
    }

    public void deleteAll(String saleDetailIds) {
        expenseDetailDao.deleteAll ( saleDetailIds );
    }

    /**
     * @param expenseDetail void   返回类型
     * @throws
     * @Title: updateAllowMoney
     * @Description: 修改实际报销金额
     * @author: WangFucheng
     */
    public void updateAllowMoney(ExpenseDetail expenseDetail) {
        dao.updateAllowMoney ( expenseDetail );
    }

    public int selectNum(String num) {

        return expenseDetailDao.selectNum ( num );
    }
}
