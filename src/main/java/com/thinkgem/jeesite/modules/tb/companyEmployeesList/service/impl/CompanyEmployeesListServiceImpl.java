package com.thinkgem.jeesite.modules.tb.companyEmployeesList.service.impl;

import com.thinkgem.jeesite.modules.tb.companyEmployeesList.dao.CompanyEmployeesListDao;
import com.thinkgem.jeesite.modules.tb.companyEmployeesList.entity.CompanyEmployeesList;
import com.thinkgem.jeesite.modules.tb.companyEmployeesList.service.CompanyEmployeesListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyEmployeesListServiceImpl implements CompanyEmployeesListService {

    @Autowired
    private CompanyEmployeesListDao companyEmployeesListDao;

    @Override
    public List<CompanyEmployeesList> getUserCompanyEmployeesListList(){
        return companyEmployeesListDao.getCompanyEmployeesList();
    }
}
