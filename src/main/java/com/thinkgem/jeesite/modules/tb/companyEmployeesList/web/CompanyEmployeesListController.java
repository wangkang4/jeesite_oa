package com.thinkgem.jeesite.modules.tb.companyEmployeesList.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.tb.companyEmployeesList.entity.CompanyEmployeesList;
import com.thinkgem.jeesite.modules.tb.companyEmployeesList.service.CompanyEmployeesListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
    @RequestMapping(value="${adminPath}/tb/companyEmployeesList")
    public class CompanyEmployeesListController extends BaseController {

        @Autowired(required=false)
        private CompanyEmployeesListService companyEmployeesListService;

        @RequestMapping("list")
        public String list(){
            return "modules/tb/companyEmployeesList/findCompanyEmployeesList";
        }

        @RequestMapping("/ddddd")
        @ResponseBody
        public List<CompanyEmployeesList> ddddd(){
            List<CompanyEmployeesList> list  = new ArrayList<>();
            list = companyEmployeesListService.getUserCompanyEmployeesListList();
            return list;
        }
    }

