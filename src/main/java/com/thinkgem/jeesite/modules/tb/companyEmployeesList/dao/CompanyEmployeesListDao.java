package com.thinkgem.jeesite.modules.tb.companyEmployeesList.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.companyEmployeesList.entity.CompanyEmployeesList;

import java.util.List;

@MyBatisDao
public interface CompanyEmployeesListDao {
    List<CompanyEmployeesList> getCompanyEmployeesList();
}
