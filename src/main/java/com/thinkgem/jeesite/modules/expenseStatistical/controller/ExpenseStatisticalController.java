package com.thinkgem.jeesite.modules.expenseStatistical.controller;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.expenseStatistical.entity.ExpenseStatistical;
import com.thinkgem.jeesite.modules.expenseStatistical.entity.TypeAndDescription;
import com.thinkgem.jeesite.modules.expenseStatistical.service.ExpenseStatisticalService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("${adminPath}/get/expenseStatistical")
public class ExpenseStatisticalController extends BaseController {
    @Autowired
    private ExpenseStatisticalService expenseStatisticalService;

    @RequestMapping("list")
    public String list() {
        return "modules/expenseStatistical/expenseStatistical";
    }

    @ResponseBody
    @RequestMapping("expenseStatistical")
    public Map<String, Object> selectExpenseStatistical2(String userId, String officeId, String startTime1, String endTime1) {
        ExpenseStatistical expenseStatistical = new ExpenseStatistical ();
        User user = new User ();
        user.setId ( userId );
        Office office = new Office ();
        office.setId ( officeId );
        expenseStatistical.setUser ( user );
        expenseStatistical.setOffice ( office );
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
        try {
            if (StringUtils.isNotBlank ( startTime1 )) {
                startTime1 = startTime1 + " 00:00:00";
                expenseStatistical.setStartTime ( sdf.parse ( startTime1 ) );
            }
            if (StringUtils.isNotBlank ( endTime1 )) {
                endTime1 = endTime1 + " 23:59:59";
                expenseStatistical.setEndTime ( sdf.parse ( endTime1 ) );
            }
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        Map<String, Double> data = new HashMap<String, Double> ();
        List<String> mapKey = expenseStatisticalService.selectAllCostType ();
        List<ExpenseStatistical> mapValue = expenseStatisticalService.selectExpenseStatistical ( expenseStatistical );
        for (String costType : mapKey) {
            data.put ( costType, 0.0 );
            for (ExpenseStatistical expense : mapValue) {
                if (expense.getCostType ().equals ( costType )) {
                    data.put ( costType, expense.getMoney () );
                }
            }
        }
        Map<String, Map<String, Double>> serise = new HashMap<String, Map<String, Double>> ();
        List<TypeAndDescription> mapKey1 = expenseStatisticalService.selectAllCostDescription ();
        mapValue = expenseStatisticalService.selectExpenseDescriptionStatistical ( expenseStatistical );
        for (TypeAndDescription td : mapKey1) {
            String key = td.getCostType ();
            serise.put ( key, new HashMap<String, Double> () );
        }
        for (TypeAndDescription td : mapKey1) {
            serise.get ( td.getCostType () ).put ( td.getCostDescription (), 0.0 );
            for (ExpenseStatistical expense : mapValue) {
                if (expense.getCostType ().equals ( td.getCostDescription () )) {
                    serise.get ( td.getCostType () ).put ( td.getCostDescription (), expense.getMoney () );
                }
            }
        }
        Map<String, Object> map = new HashMap<String, Object> ();
        map.put ( "data", data );
        map.put ( "serise", serise );
        return map;
    }

}
