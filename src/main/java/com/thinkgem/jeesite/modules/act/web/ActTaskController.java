/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.invoice.entity.InvoiceApply;
import com.thinkgem.jeesite.modules.invoice.service.InvoiceApplyService;
import com.thinkgem.jeesite.modules.leave.entity.ActivityLeave2;
import com.thinkgem.jeesite.modules.leave.service.ActivityLeave2Service;
import com.thinkgem.jeesite.modules.overtime.entity.OverTime;
import com.thinkgem.jeesite.modules.overtime.service.OverTimeService;
import com.thinkgem.jeesite.modules.purchase.entity.Purchase;
import com.thinkgem.jeesite.modules.purchase.service.PurchaseService;
import com.thinkgem.jeesite.modules.quit.entity.Quit;
import com.thinkgem.jeesite.modules.quit.service.QuitService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.borrowing.entity.Borrowing;
import com.thinkgem.jeesite.modules.tb.borrowing.service.BorrowingService;
import com.thinkgem.jeesite.modules.tb.chapter.entity.Chapter;
import com.thinkgem.jeesite.modules.tb.chapter.service.ChapterService;
import com.thinkgem.jeesite.modules.tb.contract.entity.Contract;
import com.thinkgem.jeesite.modules.tb.contract.service.ContractService;
import com.thinkgem.jeesite.modules.tb.driving.entity.Driving;
import com.thinkgem.jeesite.modules.tb.driving.service.DrivingService;
import com.thinkgem.jeesite.modules.tb.induction.entity.Induction;
import com.thinkgem.jeesite.modules.tb.induction.service.InductionService;
import com.thinkgem.jeesite.modules.tb.licenses.entity.Licenses;
import com.thinkgem.jeesite.modules.tb.licenses.service.LicensesService;
import com.thinkgem.jeesite.modules.tb.party.entity.Party;
import com.thinkgem.jeesite.modules.tb.party.service.PartyService;
import com.thinkgem.jeesite.modules.tb.patent.entity.Patent;
import com.thinkgem.jeesite.modules.tb.patent.service.PatentService;
import com.thinkgem.jeesite.modules.tb.pay.entity.Pay;
import com.thinkgem.jeesite.modules.tb.pay.service.PayService;
import com.thinkgem.jeesite.modules.tb.signet.entity.Signet;
import com.thinkgem.jeesite.modules.tb.signet.service.SignetService;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Business;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Special;
import com.thinkgem.jeesite.modules.tb.tbMoney.service.BusinessService;
import com.thinkgem.jeesite.modules.tb.tbMoney.service.SpecialService;
import com.thinkgem.jeesite.modules.tb.travelApply.entity.TravelApply;
import com.thinkgem.jeesite.modules.tb.travelApply.service.TravelService;
import com.thinkgem.jeesite.sale.entity.GetSale;
import com.thinkgem.jeesite.sale.service.GetSaleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程个人任务相关Controller
 *
 * @author ThinkGem
 * @version 2013-11-03
 */
@Controller
@RequestMapping(value = "${adminPath}/act/task")
public class ActTaskController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger ( getClass () );

    @Autowired
    private ActTaskService actTaskService;
    @Autowired
    private GetSaleService getSaleService;
    @Autowired
    private OverTimeService overTimeService;
    @Autowired
    private ActivityLeave2Service activityLeave2Service;
    @Autowired
    private PayService payService;
    @Autowired
    private SpecialService specialService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private LicensesService licensesService;
    @Autowired
    private SignetService signetService;
    @Autowired
    private BorrowingService borrowingService;
    @Autowired
    private InvoiceApplyService invoiceApplyService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private QuitService quitService;
    @Autowired
    private InductionService inductionService;
    @Autowired
    private DrivingService drivingService;
    @Autowired
    private PartyService partyService;
    @Autowired
    private PatentService patentService;
    @Autowired
    private TravelService travelService;

    /**
     * 获取待办列表
     *
     * @param procDefKey 流程定义标识
     * @return
     */
    @RequestMapping(value = {"todo", ""})
    public String todoList(Act act, String userId, String userName, String title, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        HttpSession session = request.getSession ();
        if (StringUtils.isNotBlank ( act.getProcDefKey () )) {
            session.setAttribute ( "procDefKey", act.getProcDefKey () );
        } else {
            act.setProcDefKey ( (String) session.getAttribute ( "procDefKey" ) );
        }
        String loginName = null;
        if (StringUtils.isNotBlank ( userId )) {
            if (userId.equals ( "clear" )) {
                session.removeAttribute ( "uId" );
                session.removeAttribute ( "uName" );
                session.removeAttribute ( "procDefKey" );
            } else {
                loginName = UserUtils.get ( userId ).getLoginName ();
                session.setAttribute ( "uId", userId );
                session.setAttribute ( "uName", userName );
            }
        } else {
            userId = (String) session.getAttribute ( "uId" );
            if (UserUtils.get ( userId ) != null) {
                loginName = UserUtils.get ( userId ).getLoginName ();
            }
        }
        if ("all".equals ( act.getProcDefKey () )) {//点击全部流程调用这个
            act.setProcDefKey ( "" );
        }
        List<Act> list = actTaskService.todoList ( act, loginName, title, null );
        if (StringUtils.isNotBlank ( title )) {
            model.addAttribute ( "title", title );
        }
        model.addAttribute ( "list", list );
        if (UserUtils.getPrincipal ().isMobileLogin ()) {
            return renderString ( response, list );
        }
        return "modules/act/actTaskTodoList";
    }

    /**
     * @param act
     * @param userId
     * @param userName
     * @param title
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception String   返回类型
     * @throws
     * @Title: getSale
     * @Description: 获取待办列表，仅获取报销信息
     * @author: WangFucheng
     */
    @RequestMapping(value = "getSale")
    public String getSale(Act act, String userId, String userName, String title, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        HttpSession session = request.getSession ();
        String loginName = null;
        if (StringUtils.isNotBlank ( userId )) {
            if (userId.equals ( "clear" )) {
                session.removeAttribute ( "guId" );
                session.removeAttribute ( "guName" );
            } else {
                loginName = UserUtils.get ( userId ).getLoginName ();
                session.setAttribute ( "guId", userId );
                session.setAttribute ( "guName", userName );
            }
        } else {
            userId = (String) session.getAttribute ( "guId" );
            if (UserUtils.get ( userId ) != null) {
                loginName = UserUtils.get ( userId ).getLoginName ();
            }
        }
        if ("all".equals ( act.getProcDefKey () )) {//点击全部流程调用这个
            act.setProcDefKey ( "" );
        }
        List<Act> list = actTaskService.todoList ( act, loginName, title, "getSale" );
        if (StringUtils.isNotBlank ( title )) {
            model.addAttribute ( "title", title );
        }
        model.addAttribute ( "list", list );
        if (UserUtils.getPrincipal ().isMobileLogin ()) {
            return renderString ( response, list );
        }
        return "modules/act/actTaskGetSale";
    }

    /**
     * @param tasks
     * @return Map<String, String>   返回类型
     * @throws
     * @Title: agree
     * @Description: (实现批量同意功能)
     * @author: WangFucheng
     */
    @ResponseBody
    @RequestMapping("agree")
    public Map<String, String> agree(String tasks, String flag) {
        String task[] = tasks.split ( "\\?" );
        Map<String, String> map = new HashMap<String, String> ();
        if (task.length > 0) {
            map.put ( "result", "ok" );
        } else {
            map.put ( "result", "error" );
        }
        for (int i = 0; i < task.length; i++) {//task 任务ID，任务键，流程实例ID，流程类型ID
            String tas[] = task[i].split ( "," );
            Act a = new Act ();
            a.setTaskId ( tas[0] );
            a.setFlag ( flag );
            a.setComment ( "" );
            a.setTaskDefKey ( tas[1] );
            a.setProcInsId ( tas[2] );
            logger.info ( "======tas[3]======::::" + tas[3] );
            if (tas[3].contains ( "getSale" )) {//报销
                GetSale gs = getSaleService.getByProcInsId ( tas[2] );
                gs.setAct ( a );
                getSaleService.auditSave ( gs );
                continue;
            }
            /**报销新流程*/
            if (tas[3].contains ( "thd_getsale" )) {//报销
                GetSale gs = getSaleService.getByProcInsId ( tas[2] );
                gs.setAct ( a );
                getSaleService.auditSave ( gs );
                continue;
            }

            if (tas[3].contains ( "eave" )) {//请假
                ActivityLeave2 al = activityLeave2Service.getByProcInsId ( tas[2] );
                al.setAct ( a );
                activityLeave2Service.auditSave ( al );
                continue;
            }
            /**请假新流程*/
            if (tas[3].contains ( "thd_leave" )) {//请假
                ActivityLeave2 al = activityLeave2Service.getByProcInsId ( tas[2] );
                al.setAct ( a );
                activityLeave2Service.auditSave ( al );
                continue;
            }
            if (tas[3].contains ( "OverTime" ) || tas[3].contains ( "overtime" )) {//加班
                OverTime ot = overTimeService.getByProcInsId ( tas[2] );
                ot.setAct ( a );
                overTimeService.auditSave ( ot );
                continue;
            }
            /**加班新流程*/
            if (tas[3].contains ( "thd_overTime" )) {//加班
                OverTime ot = overTimeService.getByProcInsId ( tas[2] );
                ot.setAct ( a );
                overTimeService.auditSave ( ot );
                continue;
            }
            if (tas[3].contains ( "Pay" )) {//付款
                Pay pay = payService.getByProcInsId ( tas[2] );
                pay.setAct ( a );
                payService.auditSave ( pay );
                continue;
            }
            /**新对外付款*/
            if (tas[3].contains ( "thd_pay" )) {//付款
                Pay pay = payService.getByProcInsId ( tas[2] );
                pay.setAct ( a );
                payService.auditSave ( pay );
                continue;
            }
            if (tas[3].contains ( "Contract" )) {//合同
                Contract contract = contractService.getByProcInsId ( tas[2] );
                contract.setAct ( a );
                contractService.auditSave ( contract );
                continue;
            }
            if (tas[3].contains ( "thd_contract" )) {//新合同
                Contract contract = contractService.getByProcInsId ( tas[2] );
                contract.setAct ( a );
                contractService.auditSave ( contract );
                continue;
            }
            if (tas[3].contains ( "tbMoney" )) {//预审批
                Special s = specialService.getByProcInsId ( tas[2] );
                Business b = businessService.getByProcInsId ( tas[2] );
                if (s != null) {
                    s.setAct ( a );
                    specialService.auditSave ( s );
                }
                if (b != null) {
                    b.setAct ( a );
                    businessService.auditSave ( b );
                }
                continue;
            }
            /**预审批新流程*/
            if (tas[3].contains ( "thd_special" )) {//专项费用申请
                Special special = specialService.getByProcInsId ( tas[2] );
                special.setAct ( a );
                specialService.auditSave ( special );
                continue;
            }
            if (tas[3].contains ( "thd_business" )) {//业务招待申请
                Business business = businessService.getByProcInsId ( tas[2] );
                business.setAct ( a );
                businessService.auditSave ( business );
                continue;
            }

            if (tas[3].contains ( "Borrowing" )) {//借款
                Borrowing borrowing = borrowingService.getByProcInsId ( tas[2] );
                borrowing.setAct ( a );
                borrowingService.auditSave ( borrowing );
                continue;
            }
            /**新借款申请流程*/
            if (tas[3].contains ( "thd_borrowing" )) {//借款
                Borrowing borrowing = borrowingService.getByProcInsId ( tas[2] );
                borrowing.setAct ( a );
                borrowingService.auditSave ( borrowing );
                continue;
            }

            if (tas[3].contains ( "chapter" )) {//用印
                Chapter chapter = chapterService.getByProcInsId ( tas[2] );
                chapter.setAct ( a );
                chapterService.auditSave ( chapter );
                continue;
            }
            /**新流程*/
            if (tas[3].contains ( "thd_seal" )) {//用印
                Chapter chapter = chapterService.getByProcInsId ( tas[2] );
                chapter.setAct ( a );
                chapterService.auditSave ( chapter );
                continue;
            }

            if (tas[3].contains ( "Licenses" )) {//证照
                Licenses licenses = licensesService.getByProcInsId ( tas[2] );
                licenses.setAct ( a );
                licensesService.auditSave ( licenses );
                continue;
            }
            /**新证照申请流程*/
            if (tas[3].contains ( "thd_licenses" )) {
                Licenses licenses = licensesService.getByProcInsId ( tas[2] );
                licenses.setAct ( a );
                licensesService.auditSave ( licenses );
                continue;
            }
            if (tas[3].contains ( "Signet" )) {//刻章
                Signet signet = signetService.getByProcInsId ( tas[2] );
                signet.setAct ( a );
                signetService.auditSave ( signet );
                continue;
            }
            if (tas[3].contains ( "ticket" )) { //开票申请
                InvoiceApply invoiceApply = invoiceApplyService.getByProcInsId ( tas[2] );
                invoiceApply.setAct ( a );
                invoiceApplyService.auditSave ( invoiceApply );
                continue;
            }
            /**新开票申请流程*/
            if (tas[3].contains ( "thd_ticket" )) { //开票申请
                InvoiceApply invoiceApply = invoiceApplyService.getByProcInsId ( tas[2] );
                invoiceApply.setAct ( a );
                invoiceApplyService.auditSave ( invoiceApply );
                continue;
            }
            if (tas[3].contains ( "purchase" )) { //采购申请
                Purchase purchase = purchaseService.getByProcInsId ( tas[2] );
                purchase.setAct ( a );
                purchaseService.auditSave ( purchase );
                continue;
            }
            /**新采购申请流程*/
            if (tas[3].contains ( "thd_purchase" )) { //采购申请
                Purchase purchase = purchaseService.getByProcInsId ( tas[2] );
                purchase.setAct ( a );
                purchaseService.auditSave ( purchase );
                continue;
            }

            if (tas[3].contains ( "Quit" ) || tas[3].contains ( "quit" )) { //离职申请
                Quit quit = quitService.getByProcInsId ( tas[2] );
                quit.setAct ( a );
                quitService.auditSave ( quit );
                continue;
            }
            if (tas[3].contains ( "induction" )) {//刻章
                Induction induction = inductionService.getByProcInsId ( tas[2] );
                induction.setAct ( a );
                inductionService.auditSave ( induction );
                continue;
            }
            if (tas[3].contains ( "driving" )) {//自驾车出差
                Driving driving = drivingService.getByProcInsId ( tas[2] );
                driving.setAct ( a );
                drivingService.auditSave ( driving );
                continue;
            }
            /**新自驾车申请流程*/
            if (tas[3].contains ( "thd_driving" )) {//自驾车出差
                Driving driving = drivingService.getByProcInsId ( tas[2] );
                driving.setAct ( a );
                drivingService.auditSave ( driving );
                continue;
            }
            if (tas[3].contains ( "Party" )) {//团建
                Party party = partyService.getByProcInsId ( tas[2] );
                party.setAct ( a );
                partyService.auditSave ( party );
                continue;
            }
            if (tas[3].contains ( "thd_party" )) {//团建
                Party party = partyService.getByProcInsId ( tas[2] );
                party.setAct ( a );
                partyService.auditSave ( party );
                continue;
            }
            if (tas[3].contains ( "patent" )) {//专利
                Patent patent = patentService.getByProcInsId ( tas[2] );
                patent.setAct ( a );
                patentService.auditSave ( patent );
                continue;
            }
            if (tas[3].contains ( "travelApply" )) {//出差
                TravelApply travelApply = travelService.getByProcInsId ( tas[2] );
                travelApply.setAct ( a );
                travelService.auditSave ( travelApply );
                continue;
            }
            /**新出差申请*/
            if (tas[3].contains ( "thd_travel" )) {//出差
                TravelApply travelApply = travelService.getByProcInsId ( tas[2] );
                travelApply.setAct ( a );
                travelService.auditSave ( travelApply );
                continue;
            }

            if (tas[3].contains ( "induction" )) {//入职
                TravelApply travelApply = travelService.getByProcInsId ( tas[2] );
                travelApply.setAct ( a );
                travelService.auditSave ( travelApply );
                continue;
            }
            /**
             * 新入职
             */
            if (tas[3].contains ( "tb_induction" )) {//入职
                TravelApply travelApply = travelService.getByProcInsId ( tas[2] );
                travelApply.setAct ( a );
                travelService.auditSave ( travelApply );
                continue;
            }
        }
        return map;
    }

    /**
     * 获取已办任务
     *
     * @param page
     * @param procDefKey 流程定义标识
     * @return
     */
    @RequestMapping(value = "historic")
    public String historicList(Act act, String userId, String userName, String title, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        Page<Act> page = new Page<Act> ( request, response );
        String loginName = null;
        if (StringUtils.isNotBlank ( userId )) {
            loginName = UserUtils.get ( userId ).getLoginName ();
            model.addAttribute ( "userId", userId );
            model.addAttribute ( "userName", userName );
        }
        page = actTaskService.historicList ( page, act, loginName, title );
        if (StringUtils.isNotBlank ( title )) {
            model.addAttribute ( "title", title );
        }
        model.addAttribute ( "page", page );
        if (UserUtils.getPrincipal ().isMobileLogin ()) {
            return renderString ( response, page );
        }
        return "modules/act/actTaskHistoricList";
    }


    /**
     * 获取完结任务
     *
     * @param page
     * @param procDefKey 流程定义标识
     * @return
     */
    @RequestMapping(value = "end")
    public String end(Act act, String userId, String userName, String title, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        Page<Act> page = new Page<Act> ( request, response );
        String loginName = null;
        if (StringUtils.isNotBlank ( userId )) {
            loginName = UserUtils.get ( userId ).getLoginName ();
            model.addAttribute ( "userId", userId );
            model.addAttribute ( "userName", userName );
        }
        page = actTaskService.endList ( page, act, loginName, title );
        if (StringUtils.isNotBlank ( title )) {
            model.addAttribute ( "title", title );
        }
        model.addAttribute ( "page", page );
        if (UserUtils.getPrincipal ().isMobileLogin ()) {
            return renderString ( response, page );
        }
        return "modules/act/actTaskEndList";
    }

    /**
     * 获取流转历史列表
     *
     * @param procInsId 流程实例
     * @param startAct  开始活动节点名称
     * @param endAct    结束活动节点名称
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "histoicFlow")
    public String histoicFlow(Act act, String startAct, String endAct, Model model) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank ( act.getProcInsId () )) {
            List<Act> histoicFlowList = actTaskService.histoicFlowList ( act.getProcInsId (), startAct, endAct );
            model.addAttribute ( "histoicFlowList", histoicFlowList );
        }
        return "modules/act/actTaskHistoricFlow";
    }
//	public void printHex(byte[] byteArray) {
//		  StringBuffer sb = new StringBuffer();
//		  for (byte b : byteArray) {
//		    sb.append(Integer.toHexString((b >> 4) & 0xF));
//		    sb.append(Integer.toHexString(b & 0xF));
//		    sb.append(" ");
//		  }
//		  System.out.println(sb.toString());
//		};

    /**
     * 获取流程列表
     *
     * @param category 流程分类
     */
    @RequestMapping(value = "process")
    public String processList(String category, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Object[]> page = new Page<Object[]> ( request, response );
        page = actTaskService.processList ( page, category );
        model.addAttribute ( "page", page );
        model.addAttribute ( "category", category );
        return "modules/act/actTaskProcessList";
    }

    /**
     * 获取流程表单
     *
     * @param taskId     任务ID
     * @param taskName   任务名称
     * @param taskDefKey 任务环节标识
     * @param procInsId  流程实例ID
     * @param procDefId  流程定义ID
     */
    @RequestMapping(value = "form")
    public String form(Act act, HttpServletRequest request, Model model) {

        // 获取流程XML上的表单KEY
        String formKey = actTaskService.getFormKey ( act.getProcDefId (), act.getTaskDefKey () );
//		System.out.println(act.getBusinessId());
        // 获取流程实例对象
        System.out.println ( act.getProcDefId () + "****************" + act.getTaskDefKey () );
        if (act.getProcInsId () != null) {
            act.setProcIns ( actTaskService.getProcIns ( act.getProcInsId () ) );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().startsWith ( "getSale" )) {//报销
            String id = getSaleService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        /**报销新流程*/
        if (act.getBusinessId () == null && act.getProcDefId ().startsWith ( "thd_getsale" )) {//报销
            String id = getSaleService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }

        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "overtime" )) {//加班
            String id = overTimeService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        /**加班新流程*/
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_overtime" )) {//加班
            String id = overTimeService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }

        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "eave" )) {//请假
            String id = activityLeave2Service.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        /**请假新流程*/
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_leave" )) {//请假
            String id = activityLeave2Service.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }

        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "Pay" )) {//付款
            String id = payService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        /**新对外付款*/
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_pay" )) {//付款
            String id = payService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "tbMoney" )) {//预审批
            Business b = businessService.getByProcInsId ( act.getProcInsId () );
            Special s = specialService.getByProcInsId ( act.getProcInsId () );
            String id = "";
            if (b != null && b.getId () != null) {
                id = b.getId ();
            }
            if (s != null && s.getId () != null) {
                id = s.getId ();
            }
            act.setBusinessId ( id );
        }
        /**新预审批流程*/
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_special" )) {//专项费用申请
            String id = specialService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_business" )) {//业务招待申请
            String id = businessService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }

        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "Contract" )) {//合同
            String id = contractService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        /**新合同*/
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_contract" )) {//合同
            String id = contractService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "chapter" )) {//用印
            String id = chapterService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        /**用印新流程*/
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_seal" )) {//用印
            String id = chapterService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }

        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "Licenses" )) {//证照
            String id = licensesService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        /**新证照申请流程*/
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_licenses" )) {//证照
            String id = licensesService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "Signet" )) {//印章刻制
            String id = signetService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "Borrowing" )) {//借款
            String id = borrowingService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        /**新借款申请流程*/
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_borrowing" )) {//借款
            String id = borrowingService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "ticket" )) {//开票申请
            String id = invoiceApplyService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        /**新开票申请*/
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "purchase" )) {//采购申请
            String id = purchaseService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_ticket" )) {//开票申请
            String id = invoiceApplyService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }

        /**采购新流程*/
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_purchase" )) {//采购申请
            String id = purchaseService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "Quit" )) {//离职申请
            String id = quitService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "quit" )) {//离职申请
            String id = quitService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_quit" )) {//离职申请
            String id = quitService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "driving" )) {//自驾车出差
            String id = drivingService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        /**新自驾车申请流程*/
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_driving" )) {//自驾车出差
            String id = drivingService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "Party" )) {//party
            String id = partyService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }

        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_party" )) {//party
            String id = partyService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }

        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "patent" )) {//专利
            String id = patentService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "travelApply" )) {//出差
            String id = travelService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        /**新出差申请流程*/
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_travel" )) {//出差
            String id = travelService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "induction" )) {//入职
            String id = inductionService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }
        /**
         * 新入职流程
         */
        if (act.getBusinessId () == null && act.getProcDefId ().contains ( "thd_induction" )) {//入职
            String id = inductionService.getByProcInsId ( act.getProcInsId () ).getId ();
            act.setBusinessId ( id );
        }

        return "redirect:" + ActUtils.getFormUrl ( formKey, act );
		
		/*// 传递参数到视图
		model.addAttribute("act", act);
		model.addAttribute("formUrl", formUrl);
    	/return "modules/act/actTaskForm";*/
    }

    /**
     * 启动流程
     *
     * @param procDefKey    流程定义KEY
     * @param businessTable 业务表表名
     * @param businessId    业务表编号
     */
    @RequestMapping(value = "start")
    @ResponseBody
    public String start(Act act, String table, String id, Model model) throws Exception {
        actTaskService.startProcess ( act.getProcDefKey (), act.getBusinessId (), act.getBusinessTable (), act.getTitle () );
        return "true";//adminPath + "/act/task";
    }

    /**
     * 签收任务
     *
     * @param taskId 任务ID
     */
    @RequestMapping(value = "claim")
    @ResponseBody
    public String claim(Act act) {
        String userId = UserUtils.getUser ().getLoginName ();//ObjectUtils.toString(UserUtils.getUser().getId());
        actTaskService.claim ( act.getTaskId (), userId );
        return "true";//adminPath + "/act/task";
    }

    /**
     * 完成任务
     *
     * @param taskId    任务ID
     * @param procInsId 流程实例ID，如果为空，则不保存任务提交意见
     * @param comment   任务提交意见的内容
     * @param vars      任务流程变量，如下
     *                  vars.keys=flag,pass
     *                  vars.values=1,true
     *                  vars.types=S,B  @see com.thinkgem.jeesite.modules.act.utils.PropertyType
     */
    @RequestMapping(value = "complete")
    @ResponseBody
    public String complete(Act act) {
        actTaskService.complete ( act.getTaskId (), act.getProcInsId (), act.getComment (), act.getVars ().getVariableMap () );
        return "true";//adminPath + "/act/task";
    }

    /**
     * 读取带跟踪的图片
     */
    @RequestMapping(value = "trace/photo/{procDefId}/{execId}")
    public void tracePhoto(@PathVariable("procDefId") String procDefId, @PathVariable("execId") String execId, HttpServletResponse response) throws Exception {
        InputStream imageStream = actTaskService.tracePhoto ( procDefId, execId );

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read ( b, 0, 1024 )) != -1) {
            response.getOutputStream ().write ( b, 0, len );
        }
    }

    /**
     * 输出跟踪流程信息
     *
     * @param processInstanceId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "trace/info/{proInsId}")
    public List<Map<String, Object>> traceInfo(@PathVariable("proInsId") String proInsId) throws Exception {
        List<Map<String, Object>> activityInfos = actTaskService.traceProcess ( proInsId );
        return activityInfos;
    }

    /**
     * 显示流程图

     @RequestMapping(value = "processPic")
     public void processPic(String procDefId, HttpServletResponse response) throws Exception {
     ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();
     String diagramResourceName = procDef.getDiagramResourceName();
     InputStream imageStream = repositoryService.getResourceAsStream(procDef.getDeploymentId(), diagramResourceName);
     byte[] b = new byte[1024];
     int len = -1;
     while ((len = imageStream.read(b, 0, 1024)) != -1) {
     response.getOutputStream().write(b, 0, len);
     }
     }*/

    /**
     * 获取跟踪信息

     @RequestMapping(value = "processMap")
     public String processMap(String procDefId, String proInstId, Model model)
     throws Exception {
     List<ActivityImpl> actImpls = new ArrayList<ActivityImpl>();
     ProcessDefinition processDefinition = repositoryService
     .createProcessDefinitionQuery().processDefinitionId(procDefId)
     .singleResult();
     ProcessDefinitionImpl pdImpl = (ProcessDefinitionImpl) processDefinition;
     String processDefinitionId = pdImpl.getId();// 流程标识
     ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
     .getDeployedProcessDefinition(processDefinitionId);
     List<ActivityImpl> activitiList = def.getActivities();// 获得当前任务的所有节点
     List<String> activeActivityIds = runtimeService.getActiveActivityIds(proInstId);
     for (String activeId : activeActivityIds) {
     for (ActivityImpl activityImpl : activitiList) {
     String id = activityImpl.getId();
     if (activityImpl.isScope()) {
     if (activityImpl.getActivities().size() > 1) {
     List<ActivityImpl> subAcList = activityImpl
     .getActivities();
     for (ActivityImpl subActImpl : subAcList) {
     String subid = subActImpl.getId();
     System.out.println("subImpl:" + subid);
     if (activeId.equals(subid)) {// 获得执行到那个节点
     actImpls.add(subActImpl);
     break;
     }
     }
     }
     }
     if (activeId.equals(id)) {// 获得执行到那个节点
     actImpls.add(activityImpl);
     System.out.println(id);
     }
     }
     }
     model.addAttribute("procDefId", procDefId);
     model.addAttribute("proInstId", proInstId);
     model.addAttribute("actImpls", actImpls);
     return "modules/act/actTaskMap";
     }*/

    /**
     * 删除任务
     *
     * @param taskId 流程实例ID
     * @param reason 删除原因
     */
    @RequiresPermissions("act:process:edit")
    @RequestMapping(value = "deleteTask")
    public String deleteTask(String taskId, String reason, RedirectAttributes redirectAttributes) {
        if (StringUtils.isBlank ( reason )) {
            addMessage ( redirectAttributes, "请填写删除原因" );
        } else {
            actTaskService.deleteTask ( taskId, reason );
            addMessage ( redirectAttributes, "删除任务成功，任务ID=" + taskId );
        }
        return "redirect:" + adminPath + "/act/task";
    }
}
