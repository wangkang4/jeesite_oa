package com.thinkgem.jeesite.modules.expenseStatistical.service;

import com.thinkgem.jeesite.modules.expenseStatistical.dao.ExpenseStatisticalDao;
import com.thinkgem.jeesite.modules.expenseStatistical.entity.ExpenseStatistical;
import com.thinkgem.jeesite.modules.expenseStatistical.entity.TypeAndDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseStatisticalService {
    @Autowired
    private ExpenseStatisticalDao expenseStatisticalDao;

    public List<ExpenseStatistical> selectExpenseStatistical(ExpenseStatistical expenseStatistical) {
        return expenseStatisticalDao.selectExpenseStatistical ( expenseStatistical );
    }

    public List<String> selectAllCostType() {
        return expenseStatisticalDao.selectAllCostType ();
    }

    public List<ExpenseStatistical> selectExpenseDescriptionStatistical(ExpenseStatistical expenseStatistical) {
        return expenseStatisticalDao.selectExpenseDescriptionStatistical ( expenseStatistical );
    }

    public List<TypeAndDescription> selectAllCostDescription() {
        return expenseStatisticalDao.selectAllCostDescription ();
    }
}
