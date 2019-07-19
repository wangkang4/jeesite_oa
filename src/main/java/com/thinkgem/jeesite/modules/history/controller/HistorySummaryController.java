package com.thinkgem.jeesite.modules.history.controller;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.insertRollBack.HistoryPROC;
import com.thinkgem.jeesite.common.mapper.insertRollBack.RollBackService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.expense.entity.CostType;
import com.thinkgem.jeesite.modules.expense.entity.ExpenseDetail;
import com.thinkgem.jeesite.modules.expense.service.CostTypeService;
import com.thinkgem.jeesite.modules.expense.service.ExpenseDetailService;
import com.thinkgem.jeesite.modules.history.entity.DownGetSale;
import com.thinkgem.jeesite.modules.history.entity.GetSaleSummary;
import com.thinkgem.jeesite.modules.history.service.HistorySummaryService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.sale.entity.GetSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "${adminPath}/get/history")
public class HistorySummaryController {
    @Autowired
    private HistorySummaryService historySummaryService;

    @Autowired
    private CostTypeService costTypeService;

    @Autowired
    private ExpenseDetailService expenseDetailService;
    @Autowired
    private RollBackService rollBackService;


    /**
     * @param getSale
     * @param request
     * @param response
     * @param model
     * @return String   返回类型
     * @throws
     * @Title: historyExpense
     * @Description: TODO(返回历史信息汇总页面)
     * @author: WangFucheng
     */
    @RequestMapping(value = "historyExpense")
    public String historyExpense(GetSale getSale, HttpServletRequest request, HttpServletResponse response, Model model, String startTime1, String endTime1) {
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
        SimpleDateFormat sdf1 = new SimpleDateFormat ( "yyyy-MM-dd" );
        try {
            if (StringUtils.isNotBlank ( startTime1 )) {
                model.addAttribute ( "startTime", sdf1.parse ( startTime1 ) );
                getSale.setSt ( sdf.parse ( startTime1 + " 00:00:00" ) );
            }
            if (StringUtils.isNoneBlank ( endTime1 )) {
                model.addAttribute ( "endTime", sdf1.parse ( endTime1 ) );
                getSale.setEt ( sdf.parse ( endTime1 + " 23:59:59" ) );
            }
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        if (getSale.getUser () != null && getSale.getUser ().getId () != null && getSale.getUser ().getId () != "") {
            model.addAttribute ( "userId", getSale.getUser ().getId () );
            model.addAttribute ( "userName", getSale.getUser ().getName () );
        }
        Page<GetSale> page = historySummaryService.findPage ( new Page<GetSale> ( request, response ), getSale );
        model.addAttribute ( "page", page );
        return "modules/getSalefolder/historySummary";
    }

    @RequestMapping(value = "historyExpenseDetail")
    public String historyExpenseDetai(String id, Model model) {

        GetSale getSale = historySummaryService.getSaleById ( id );
        model.addAttribute ( "getSale", getSale );

        List<ExpenseDetail> listOther = expenseDetailService.findById ( getSale.getSaleDetailId () );
        model.addAttribute ( "listOther", listOther );

        /*
         *修改审批汇总详情页面
         *将添加页面出差总天数栏，添加页面实际报销总金额栏和可报销总金额，
         *修改原来的总金额字段（取得值均为expense_detail表中数据）
         *
         */
        double allowMoney = 0;
        double days = 0;
        double amountMoney = 0;
        for (ExpenseDetail ex : listOther) {

            allowMoney += ex.getAllowMoney ().doubleValue ();
            amountMoney += ex.getAmountMoney ().doubleValue ();
            if (ex.getDay () != null) {
                days = +ex.getDay ();
            } else {
                days = 0;
            }
        }
        model.addAttribute ( "allowMoney", allowMoney );//实际报销总金额
        model.addAttribute ( "amountMoney", amountMoney );//可报销总金额
        model.addAttribute ( "days", days );

        List<CostType> list = costTypeService.findCostType ();
        model.addAttribute ( "list", list );

        List<HistoryPROC> historyList = rollBackService.selectHistoryPROC ( getSale.getAct ().getProcInsId () );
        model.addAttribute ( "historyList", historyList );
        return "modules/getSalefolder/historySummaryDetail";
    }

    @RequestMapping(value = "print")
    public String print(String id, Model model) {

        GetSale getSale = historySummaryService.getSaleById ( id );
        model.addAttribute ( "getSale", getSale );
        List<ExpenseDetail> listOther = expenseDetailService.findById ( getSale.getSaleDetailId () );
        model.addAttribute ( "listOther", listOther );
        List<CostType> list = costTypeService.findCostType ();
        model.addAttribute ( "list", list );
        List<HistoryPROC> historyList = rollBackService.selectHistoryPROC ( getSale.getAct ().getProcInsId () );
        model.addAttribute ( "historyList", historyList );
        return "modules/getSalefolder/printHistorySummary";
    }

    @ResponseBody
    @RequestMapping(value = "ExExcel")
    public String ExportCost(String startTime1, String endTime1, String userId1, HttpServletRequest request, HttpServletResponse response,
                             RedirectAttributes redirectAttributes) {
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
        try {
            String fileName = DateUtils.getDate ( "yyyy_MM_dd_" ) + "报销记录" + ".xlsx";
            GetSale getSale = new GetSale ();
            if (StringUtils.isNotBlank ( startTime1 )) {
                getSale.setSt ( sdf.parse ( startTime1 + " 00:00:00" ) );
            }
            if (StringUtils.isNotBlank ( endTime1 )) {
                getSale.setEt ( sdf.parse ( endTime1 + " 23:59:59" ) );
            }
            if (StringUtils.isNotBlank ( userId1 )) {
                User user = new User ();
                user.setId ( userId1 );
                getSale.setUser ( user );
            }

//			Page<Checkin> page = checkinService.findPage(new Page<Checkin>(request, response), checkin);
            List<DownGetSale> list = historySummaryService.downList ( getSale );
            new ExportExcel ( "报销信息", DownGetSale.class ).setDataList ( list ).write ( response, fileName )
                    .dispose ();
            return null;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return "redirect:" + Global.getAdminPath () + "/get/history/historyExpense?repage";
    }

    @RequestMapping(value = "list")
    public String list(GetSaleSummary getSaleSummary, HttpServletRequest request, HttpServletResponse response, Model model, String startTime1, String endTime1) {

        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
        try {
            Date st = null;
            Date et = null;
            if (StringUtils.isNotBlank ( startTime1 )) {
                st = sdf.parse ( startTime1 + " 00:00:00" );
            }
            if (StringUtils.isNotBlank ( endTime1 )) {
                et = sdf.parse ( endTime1 + " 23:59:59" );
            }
            model.addAttribute ( "startTime", st );
            model.addAttribute ( "endTime", et );
            List<GetSaleSummary> list = historySummaryService.selectAmount ( st, et );
            model.addAttribute ( "list", list );
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return "modules/getSalefolder/historySummaryList";
    }

    @RequestMapping(value = "dayList")
    public String dayList() {
        return "modules/getSalefolder/historyDayList";
    }

    @ResponseBody
    @RequestMapping(value = "SummaryExExcel")
    public String SummaryExportCost(String startTime1, String endTime1, HttpServletRequest request, HttpServletResponse response,
                                    RedirectAttributes redirectAttributes) {
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
        try {
            String fileName = DateUtils.getDate ( "yyyy_MM_dd_" ) + "报销金额汇总" + ".xlsx";
            Date st = null;
            Date et = null;
            if (StringUtils.isNotBlank ( startTime1 )) {
                st = sdf.parse ( startTime1 + " 00:00:00" );
            }
            if (StringUtils.isNotBlank ( endTime1 )) {
                et = sdf.parse ( endTime1 + " 23:59:59" );
            }

//			Page<Checkin> page = checkinService.findPage(new Page<Checkin>(request, response), checkin);
            List<GetSaleSummary> list = historySummaryService.selectAmount ( st, et );
            new ExportExcel ( "报销金额汇总信息", GetSaleSummary.class ).setDataList ( list ).write ( response, fileName )
                    .dispose ();
            return null;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return "redirect:" + Global.getAdminPath () + "/get/history/list?repage";
    }

    /**
     * 批量打印 判断选中条目
     *
     * @param tasks
     * @return 返回map
     */
    @ResponseBody
    @RequestMapping(value = "printAdd")
    public Map<String, String> print(String tasks) {
        String[] task = tasks.replace ( " ", "" ).split ( "\\?" );
        Map<String, String> map = new HashMap<String, String> ();
        if (!task[0].equals ( "" )) {
            map.put ( "result", "ok" );
        } else {
            map.put ( "result", "error" );
        }
        return map;
    }

    /**
     * 批量打印页面
     *
     * @param task
     * @param model
     * @return
     */
    @RequestMapping(value = "printList")
    public String print2(String task, Model model) {
        String ids[] = task.split ( "\\?" );
        List<Object> listAll = new ArrayList<Object> ();
        for (String id : ids) {
            List<Object> list = new ArrayList<Object> ();
            GetSale getSale = historySummaryService.getSaleById ( id );
            list.add ( getSale );
            List<ExpenseDetail> listOther = expenseDetailService.findById ( getSale.getSaleDetailId () );
            list.add ( listOther );
            List<CostType> list2 = costTypeService.findCostType ();
            list.add ( list2 );
            List<HistoryPROC> historyList = rollBackService.selectHistoryPROC ( getSale.getAct ().getProcInsId () );
            list.add ( historyList );
            listAll.add ( list );
        }
        model.addAttribute ( "listAll", listAll );
        return "modules/getSalefolder/printList";
    }


}


