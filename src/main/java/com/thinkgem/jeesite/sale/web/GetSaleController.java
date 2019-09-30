package com.thinkgem.jeesite.sale.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.expense.entity.CostDescription;
import com.thinkgem.jeesite.modules.expense.entity.CostType;
import com.thinkgem.jeesite.modules.expense.entity.ExpenseDetail;
import com.thinkgem.jeesite.modules.expense.service.CostDescriptionService;
import com.thinkgem.jeesite.modules.expense.service.CostTypeService;
import com.thinkgem.jeesite.modules.expense.service.ExpenseDetailService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Business;
import com.thinkgem.jeesite.modules.tb.tbMoney.entity.Special;
import com.thinkgem.jeesite.modules.tb.travelApply.service.TravelService;
import com.thinkgem.jeesite.sale.entity.Cost;
import com.thinkgem.jeesite.sale.entity.DownloadGetSale;
import com.thinkgem.jeesite.sale.entity.GetSale;
import com.thinkgem.jeesite.sale.service.GetSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Controller
@RequestMapping(value = "${adminPath}/get/sale")
public class GetSaleController extends BaseController {

    @Autowired
    private GetSaleService getSaleService;

    @Autowired
    private CostTypeService costTypeService;

    @Autowired
    private CostDescriptionService costDescriptionService;

    @Autowired
    private ExpenseDetailService expenseDetailService;

    @Autowired
    private TravelService travelService;

    @ModelAttribute
    public GetSale get(@RequestParam(required = false) String id) {
        GetSale getSale = null;
        if (StringUtils.isNotBlank(id)) {
            getSale = getSaleService.get(id);
        }
        if (getSale == null) {
            getSale = new GetSale();
        }
        return getSale;
    }

    // 获取费用描述总的集合
    @ResponseBody
    @RequestMapping(value = "costDescription")
    public Map<String, List<CostDescription>> getCostDescription() {
        List<CostType> costType = costTypeService.findCostType();
        Map<String, List<CostDescription>> map = new HashMap<String, List<CostDescription>>();
        for (int i = 0; i < costType.size(); i++) {
            List<CostDescription> costDescription = costDescriptionService.findByCostTypeId(String.valueOf(i + 1));
            map.put(String.valueOf(i + 1), costDescription);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "getCostDescription")
    public Map<String, Object> getCostDescriptions() {
        Map<String, Object> map = new HashMap<>();
        List<Cost> list = costTypeService.selectCostDescription();
        List<CostType> costType = costTypeService.findCostType();
        map.put("cost", list);
        map.put("costType", costType);
        return map;
    }

    @ResponseBody
    @RequestMapping("getDetail")
    public Map<String, List<ExpenseDetail>> getDetail(String saleDetailId) {
        List<ExpenseDetail> listOther = expenseDetailService.findById(saleDetailId);
        Map<String, List<ExpenseDetail>> map = new HashMap<String, List<ExpenseDetail>>();
        map.put("detail", listOther);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "getCost")
    public Map<String, List<CostDescription>> getCost(String costTypeId) {
        List<CostDescription> costDescription = costDescriptionService.findByCostTypeId(costTypeId);
        Map<String, List<CostDescription>> map = new HashMap<String, List<CostDescription>>();
        map.put("costDescription", costDescription);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "updateCost")
    public Map<String, String> updateCost(String detailId,
                                          String costTypeId, String costType,
                                          String costDescriptionId, String costDescription) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotBlank(costDescriptionId)) {
            costDescriptionService.updateDescription(detailId, costTypeId, costType, costDescriptionId, costDescription);
            map.put("success", "ok");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "updateAllowMoney")
    public Map<String, String> updateAllowMoney(ExpenseDetail expenseDetail) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotBlank(expenseDetail.getDetailId())) {
            expenseDetailService.updateAllowMoney(expenseDetail);
            map.put("success", "ok");
        }
        return map;
    }

    @RequestMapping(value = {"list", ""})
    public String list(GetSale getSale, Act act, HttpServletRequest request, HttpServletResponse response, Model model) {
        getSale.setUser(UserUtils.getUser());
        Page<GetSale> page = getSaleService.findPages(new Page<GetSale>(request, response), getSale);
        List<GetSale> getSales = page.getList();
        if (getSales != null && getSales.size() > 0) {
            for (GetSale getSaleTmp : getSales) {
                getSaleTmp.setOpt("form");//查看页面
                if ("modifyTask".equals(getSaleService.selectTaskKey(getSaleTmp.getProcInsId()))) {
                    getSaleTmp.setOpt("form1");//修改页面
                }
            }
        }
        model.addAttribute("page", page);
        return "modules/getSalefolder/getSaleList";
    }


    /**
     * 行政人员查看所属区域申请列表
     *
     * @param getSale
     * @param act
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "getSaleList2")
    public String getSaleList2(GetSale getSale, Act act, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<GetSale> page = getSaleService.findPage2(new Page<GetSale>(request, response), getSale);
        model.addAttribute("page", page);
        if (getSale.getSt() != null) {
            model.addAttribute("st", getSale.getSt());
        }
        if (getSale.getEt() != null) {
            model.addAttribute("et", getSale.getEt());
        }
        if (getSale.getUser() != null && getSale.getUser().getId() != null) {
            model.addAttribute("userId", getSale.getUser().getId());
            model.addAttribute("userName", getSale.getUser().getName());
        }
        return "modules/getSalefolder/getSaleList2";
    }


    /**
     * @param getSale
     * @param act
     * @param request
     * @param response
     * @param model
     * @return String   返回类型
     * @throws
     * @Title: CWSubList
     * @Description: TODO(财务用来查看全体人员的报销)
     * @author: WangFucheng
     */
    @RequestMapping("CWSubList")
    public String CWSubList(GetSale getSale, Act act, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<GetSale> page = getSaleService.findCWPage(new Page<GetSale>(request, response), getSale);
        model.addAttribute("page", page);
        if (getSale.getSt() != null) {
            model.addAttribute("st", getSale.getSt());
        }
        if (getSale.getEt() != null) {
            model.addAttribute("et", getSale.getEt());
        }
        if (getSale.getUser() != null && getSale.getUser().getId() != null) {
            model.addAttribute("userId", getSale.getUser().getId());
            model.addAttribute("userName", getSale.getUser().getName());
        }
        return "modules/getSalefolder/getSaleSubList";
    }

    @RequestMapping("upload")
    public String update(@RequestParam("file") MultipartFile file, String id) {
        String fileName = file.getOriginalFilename();
        if (StringUtils.isNoneBlank(fileName)) {
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            String address = IdGen.uuid() + suffix;
            FileOutputStream fos = null;
            try {
                byte[] fileData = file.getBytes();
                fos = new FileOutputStream(Global.getConfig("getSaleUploadPath") + address);
                fos.write(fileData);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            GetSale gs = new GetSale();
            gs.setId(id);
            gs.setAddress(address);
            getSaleService.upload(gs);
        }
        return "redirect:/a/get/sale/CWSubList";
    }

    @RequestMapping("download")
    public String download(GetSale getSale, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String downloadPrefix = Global.getConfig("getSaleUploadPath");
        String url = downloadPrefix + getSale.getAddress();
        String[] str = getSale.getAddress().split("\\.");
        getSale.setAddress(getSale.getReason() + "." + str[1]);
        File file = new File(url);
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition",
                "attachment;filename=" + new String(getSale.getAddress().getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
        response.addHeader("Content-Length", "" + file.length());

        try {
            // 以流的形式下载文件
            InputStream is = new BufferedInputStream(new FileInputStream(url));
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            os.write(buffer);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "form")
    public String form(GetSale getSale, Model model) {
        String view = "getSaleForm2";
        // if(getSale.getStatus()==0)
        if (StringUtils.isNotBlank(getSale.getId())) {
            // 获取环节ID
            String taskDefKey = getSale.getAct().getTaskDefKey();
            // 查看申请
            if (getSale.getAct().isFinishTask()) {
                view = "getSaleView";
            }
            // 人事主管审核
            else if ("userTask1".equals(taskDefKey)
                    || "userTask2".equals(taskDefKey)
                    || "userTask3".equals(taskDefKey)
                    || "userTask4".equals(taskDefKey)
                    || "userTask5".equals(taskDefKey)
                    || "userTask6".equals(taskDefKey)
            ) {
                view = "getSaleAudit";
            }

            // 修改请假申请
            else if ("modifyTask".equals(taskDefKey)) {
                view = "getSaleForm2";
            } else {//已完结
                view = "getSaleAudit";
            }
        } else {

            getSale.setUser(UserUtils.getUser());
            Office office = getSaleService.getOfficeByUserId(UserUtils.getUser().getId());
            getSale.setOffice(office);
            getSale.setEname(UserUtils.getUser().getCompany().getName());
        }
        model.addAttribute("getSale", getSale);
        // add，通过procInsId获取报销单的详细信息；通过后台传到前端；
        String saleDetailId = getSaleService.getSaleDetailIdByProcInsId(getSale.getProcInsId());
        List<ExpenseDetail> listOther = expenseDetailService.findById(saleDetailId);
        model.addAttribute("listOther", listOther);
        List<CostType> list = costTypeService.findCostType();
        model.addAttribute("list", list);
//		model.addAttribute("taskId", getSale.getAct().getTaskId());
//		System.out.println(getSale.getAct().getTaskId()+"==============");
        return "modules/getSalefolder/" + view;
    }

    //驳回页面和草稿页面
    @RequestMapping(value = "form1")
    public String form1(GetSale getSale, Model model) {
        getSale.setEname(UserUtils.getUser().getCompany().getName());
        getSale.setUser(UserUtils.getUser());
        Office office = getSaleService.getOfficeByUserId(UserUtils.getUser().getId());
        getSale.setOffice(office);
        if (getSale.getReason() != null) {//
            String reason[] = getSale.getReason().split("-");
            if (reason.length > 3) {
                getSale.setReason(reason[2]);
                getSale.setsNum(reason[0]);
            }
        }
        model.addAttribute("getSale", getSale);
        // add，通过procInsId获取报销单的详细信息；通过后台传到前端；
        /*
         * String saleDetailId =
         * getSaleService.getSaleDetailIdByProcInsId(getSale.getId());
         */
        String saleDetailId = getSale.getSaleDetailId();
        List<ExpenseDetail> listOther = expenseDetailService.findById(saleDetailId);
        model.addAttribute("listOther", listOther);
        List<CostType> list = costTypeService.findCostType();
        model.addAttribute("list", list);
        return "modules/getSalefolder/getSaleForm2";
    }

    @RequestMapping(value = "updateSale")
    public String updateSale(GetSale getSale, ExpenseDetail expenseDetail, Model model) {
        getSale.setUser(UserUtils.getUser());
        Office office = getSaleService.getOfficeByUserId(UserUtils.getUser().getId());
        getSale.setOffice(office);
        getSaleService.updateForm(getSale);
        expenseDetailService.updateById(expenseDetail);
        return "redirect:" + adminPath + "/get/sale/list";
    }


    @RequestMapping(value = "finishForm")
    public String finishForm(GetSale getSale, Model model, String procInsId) {
        /* System.out.println(procInsId); */
        getSale = getSaleService.getByProcInsId(procInsId);
        model.addAttribute("getSale", getSale);
        return "modules/getSalefolder/getSaleView";
    }

    @RequestMapping(value = "save")
    public String save(GetSale getSale, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request)
            throws ParseException {
        if (!beanValidator(model, getSale)) {
            return form(getSale, model);
        }
        // 获取关联id
        UUID uu = UUID.randomUUID();
        String saleDetailId = uu.toString();
        // 获取前台传过来的数据
        /*String saleDetailIds = getSale.getSaleDetailId();*/
        String[] saleDetailIds = request.getParameterValues("saleDetailId");
//		String[] detailId = request.getParameterValues("detailId");
        String[] detailDate = request.getParameterValues("detailDate");
        String[] money = request.getParameterValues("money");//报销金额
        String[] costTypeId = request.getParameterValues("costTypeId");
        String[] costDescriptionId = request.getParameterValues("costDescriptionId");
        String[] amountMoney = request.getParameterValues("amountMoney");//可报销金额
        String[] projectName = request.getParameterValues("projectName");
        String[] information = request.getParameterValues("information");
//		String[] tbMoney = request.getParameterValues("tbMoneyId");
        String[] day = request.getParameterValues("day");
        String[] origin = request.getParameterValues("origin");
        String[] destination = request.getParameterValues("destination");
        String expenseId = request.getParameter("user.id");
        /*出差编号*/
        String[] num = request.getParameterValues("num");
        /*出差津贴*/
        String[] allowance = request.getParameterValues("allowance");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<ExpenseDetail> list = new ArrayList<ExpenseDetail>();
        double forMoney = 0.0;
        /*出差总津贴*/
        double sumAllowance = 0.0;
        String costTypeName = "";
        for (int i = 0; i < detailDate.length; i++) {
            ExpenseDetail ex = new ExpenseDetail();
            ex.setDetailDate(sdf.parse(detailDate[i]));
            ex.setMoney(new BigDecimal(money[i]));
            ex.setCostTypeId(costTypeId[i]);
            String costType = costTypeService.findById(costTypeId[i]);
            costTypeName = costType;
            ex.setCostType(costType);
            ex.setCostDescriptionId(costDescriptionId[i]);
            if ("0501".equals(costDescriptionId[i])) {
                ex.setDay(new Double(day[i]));
                ex.setOrigin(origin[i]);
                ex.setDestination(destination[i]);
                ex.setAllowance(Double.valueOf(allowance[i]));//出差津贴
                ex.setAllowanceMoney(Double.valueOf(allowance[i]));//最终的出差津贴与初始出差津贴相同
                /*出差编号验证改出差申请是否通过审核*/
                if (travelService.selectNum(num[i]) == 0) {
                    addMessage(redirectAttributes, "该出差申请未通过审批");
                    return "redirect:" + adminPath + "/get/sale/form";
                }
                ;
                /*验证出差编号是否已经使用*/

                System.out.println("+++++" + getSale.getId());
                if (expenseDetailService.selectNum(num[i]) != 0 && getSale.getId().equals("")) {
                    addMessage(redirectAttributes, "该出差申请编号已使用");
                    return "redirect:" + adminPath + "/get/sale/form";
                }
                ex.setNum(num[i]);
                /*计算总津贴*/
                Double sumAllowanceOne = new Double(allowance[i]);
                sumAllowance += sumAllowanceOne;
            }
//			if(tbMoney!=null&&tbMoney.length>0){
//				if(StringUtils.isNotBlank(tbMoney[i])){
//					String[] tbMoneyId = tbMoney[i].split("-");
//					ex.setTbMoneyId(tbMoneyId[0]);
//					ex.setTbMoneyName(tbMoneyId[1]+"-"+tbMoneyId[2]);
//				}
//			}
            String costDescription = costDescriptionService.findByOne(costDescriptionId[i]);
            ex.setCostDescription(costDescription);
            ex.setAmountMoney(new BigDecimal(amountMoney[i]));
            Double forAmount = new Double(amountMoney[i]);
            forMoney += forAmount;

            ex.setProjectName(projectName[i]);
            ex.setInformation(information[i]);
            ex.setExpenseId(expenseId);
            ex.setCreateDate(new Date());
            ex.setSaleDetailId(saleDetailId);
            list.add(ex);
        }
        getSale.setCostType(costTypeName);
        /*总金额，报销加出差津贴*/
        getSale.setForMoney(forMoney + sumAllowance);
        getSale.setSaleDetailId(saleDetailId);
        getSale.setSumAllowance(sumAllowance);
        //getSale.setEname(UserUtils.getUser().getCompany().getName());
        if (list.size() > 0) {
            expenseDetailService.insertAll(list);
            System.out.println(list);
        }
        if (getSale.getStatus() == 0) {//如果为草稿
            getSaleService.delete(getSale);//删除草稿
        }
        expenseDetailService.deleteAll(saleDetailIds[0]);
        getSale.setPrText(null);
        getSale.setLeaderText(null);
        getSale.setLeadertwoText(null);
        getSale.setPrtwoText(null);
        getSale.setManagerText(null);
        getSale.setSaleDetailId(saleDetailId);
        getSaleService.save(getSale);
        if ("yes".equals(getSale.getAct().getFlag())) {
            addMessage(redirectAttributes, "提交报销申请成功");
        } else {
            addMessage(redirectAttributes, "销毁报销单成功");
        }
        return "redirect:" + adminPath + "/get/sale/list";
    }

    // 保存草稿；
    @RequestMapping(value = "saveDraft")
    public String saveDraft(GetSale getSale, Model model, RedirectAttributes redirectAttributes,
                            HttpServletRequest request) throws ParseException {
        if (!beanValidator(model, getSale)) {
            return form(getSale, model);
        }
        // 获取关联id
        UUID uu = UUID.randomUUID();
        String saleDetailId = uu.toString();
        // 获取前台传过来的数据
        /*String saleDetailIds = getSale.getSaleDetailId();*/
        String[] saleDetailIds = request.getParameterValues("saleDetailId");
//		String[] detailId = request.getParameterValues("detailId");
        String[] detailDate = request.getParameterValues("detailDate");
        String[] money = request.getParameterValues("money");
        String[] costTypeId = request.getParameterValues("costTypeId");
        String[] costDescriptionId = request.getParameterValues("costDescriptionId");
        String[] amountMoney = request.getParameterValues("amountMoney");
        String[] projectName = request.getParameterValues("projectName");
        String[] information = request.getParameterValues("information");
        String[] tbMoney = request.getParameterValues("tbMoneyId");
        String expenseId = request.getParameter("user.id");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<ExpenseDetail> list = new ArrayList<ExpenseDetail>();
        String[] day = request.getParameterValues("day");
        /*出差津贴*/
        String[] allowance = request.getParameterValues("allowance");
        /*出差编号*/
        String[] num = request.getParameterValues("num");
        String[] origin = request.getParameterValues("origin");
        String[] destination = request.getParameterValues("destination");
        double forMoney = 0.0;
        /*出差总津贴*/
        double sumAllowance = 0.0;
        for (int i = 0; i < detailDate.length; i++) {
            ExpenseDetail ex = new ExpenseDetail();
            ex.setDetailDate(sdf.parse(detailDate[i]));
            ex.setMoney(new BigDecimal(money[i]));
            ex.setCostTypeId(costTypeId[i]);

            if ("0501".equals(costDescriptionId[i])) {
                ex.setDay(new Double(day[i]));
                ex.setOrigin(origin[i]);
                ex.setDestination(destination[i]);
                ex.setAllowance(Double.valueOf(allowance[i]));//出差津贴
                ex.setAllowanceMoney(Double.valueOf(allowance[i]));//最终的出差津贴与初始出差津贴相同
                /*出差编号验证改出差申请是否通过审核*/
                if (travelService.selectNum(num[i]) == 0) {
                    addMessage(redirectAttributes, "该出差申请未通过审批");
                    return "redirect:" + adminPath + "/get/sale/form";
                }
                ;
                ex.setNum(num[i]);
                /*计算总津贴*/
                Double sumAllowanceOne = new Double(allowance[i]);
                sumAllowance += sumAllowanceOne;
            }
            String costType = costTypeService.findById(costTypeId[i]);
            ex.setCostType(costType);
            ex.setCostDescriptionId(costDescriptionId[i]);
            if (tbMoney != null && tbMoney.length > 0) {
                if (StringUtils.isNotBlank(tbMoney[i])) {
                    String[] tbMoneyId = tbMoney[i].split("-");
                    ex.setTbMoneyId(tbMoneyId[0]);
                    ex.setTbMoneyName(tbMoneyId[1] + "-" + tbMoneyId[2]);
                }
            }
            String costDescription = costDescriptionService.findByOne(costDescriptionId[i]);
            ex.setCostDescription(costDescription);
            ex.setAmountMoney(new BigDecimal(amountMoney[i]));
            Double forAmount = new Double(amountMoney[i]);
            forMoney += forAmount;

            ex.setProjectName(projectName[i]);
            ex.setInformation(information[i]);
            ex.setExpenseId(expenseId);
            ex.setCreateDate(new Date());
            ex.setSaleDetailId(saleDetailId);
            getSale.setCostType(costType);
            list.add(ex);
        }
        getSale.setForMoney(forMoney);
        getSale.setSaleDetailId(saleDetailId);
        getSale.setStatus(0);
        getSale.setSumAllowance(sumAllowance);
        //getSale.setEname(UserUtils.getUser().getCompany().getName());
		/*String costType1 = costTypeService.findById(costTypeId[0]);
		String ex=request.getParameter("userName")+"--"+request.getParameter("officeName")+"--"+costType1;
		getSale.setReason(ex);*/
        if (StringUtils.isNotBlank(saleDetailIds[0])) {

            expenseDetailService.deleteAll(saleDetailIds[0]);
        }
        if (list.size() > 0) {
            expenseDetailService.insertAll(list);
        }
        getSaleService.saveDraft(getSale);

        addMessage(redirectAttributes, "报销申请存入草稿成功");
        return "redirect:" + adminPath + "/get/sale/listDraft";
    }

    // 草稿列表；
    @RequestMapping(value = "listDraft")
    public String listDraft(GetSale getSale, HttpServletRequest request, HttpServletResponse response, Model model) {
        getSale.setUser(UserUtils.getUser());
        Page<GetSale> page = getSaleService.findDraft(new Page<GetSale>(request, response), getSale);
        model.addAttribute("page", page);
        return "modules/getSalefolder/saveDraftList";
    }

    @RequestMapping(value = "saveAudit")
    public String saveAudit(GetSale getSale, Model model, HttpServletRequest request) {
        getSaleService.auditSave(getSale);
//		model.addAttribute("taskid", request.getParameter("taskids"));
        //return "redirect:" + adminPath + "/act/task/getSale?click";
        return "redirect:" + adminPath + "/act/task/getSale";
    }

    @RequestMapping(value = "delete")
    public String delete(GetSale getSale, RedirectAttributes redirectAttributes) {
        getSaleService.delete(getSale);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/get/sale/listDraft";
    }

    @RequestMapping(value = "deleteSale")
    public String deleteSale(GetSale getSale, RedirectAttributes
            redirectAttributes) {
        getSaleService.delete(getSale);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/get/sale/list";
    }

    @ResponseBody
    @RequestMapping(value = "withdraw")
    public Map<String, String> withdraw(GetSale getSale) {
        Map<String, String> map = new HashMap<String, String>();
        if (getSale.getPrText() != null || getSale.getPrtwoText() != null || getSale.getPrthreeText() != null
                || getSale.getLeaderText() != null || getSale.getLeadertwoText() != null
                || getSale.getManagerText() != null || getSale.getPrfourText() != null) {
            map.put("data", "error");
        } else {
            getSaleService.withdraw(getSale);
            map.put("data", "ok");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("business")
    public Map<String, List<Business>> business() {
        Map<String, List<Business>> map = new HashMap<String, List<Business>>();
        User use = UserUtils.getUser();
        List<Business> list = getSaleService.selectBusiness(use.getId());
        map.put("list", list);
        return map;
    }

    @ResponseBody
    @RequestMapping("special")
    public Map<String, List<Special>> special() {
        Map<String, List<Special>> map = new HashMap<String, List<Special>>();
        User use = UserUtils.getUser();
        List<Special> list = getSaleService.selectSpecial(use.getId());
        map.put("list", list);
        return map;
    }

    /**
     * 文件上传功能
     *
     * @param file
     * @param request
     * @return 返回json串addressName
     */
    @ResponseBody
    @RequestMapping("upload1")
    public String upload(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) {
        String addressName = "";
        for (int i = 0; i < file.length; i++) {
            String address = null;
            if (request instanceof MultipartHttpServletRequest) {
                String fileName = file[i].getOriginalFilename();
                if (StringUtils.isNoneBlank(fileName)) {
                    String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
                    address = IdGen.uuid() + suffix;
                    addressName += address + "-";
                    FileOutputStream fos = null;
                    try {
                        byte[] fileData = file[i].getBytes();
                        fos = new FileOutputStream(Global.getConfig("getSale") + address);
                        fos.write(fileData);
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return addressName;
    }

    /**
     * 文件下载（打成压缩包下载）
     *
     * @param getSale
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("downloadFiel")
    public String downloadFiel(GetSale getSale, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String downloadPrefix = Global.getConfig("getSale");
        String[] strName = getSale.getFileAddress().split("\\-");
        List<File> srcFiles = new ArrayList<File>();
        for (int i = 0; i < strName.length; i++) {
            String url = downloadPrefix + strName[i];
            srcFiles.add(new File(url));
        }
        String zipUrl = downloadPrefix + getSale.getUser().getName() + "-报销申请" + ".zip";
        getSale.setFileAddress(getSale.getUser().getName() + "-报销申请" + ".zip");
        File zipFile = new File(zipUrl);
        //调用压缩方法
        zipFiles(srcFiles, zipFile);
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition",
                "attachment;filename=" + new String(getSale.getFileAddress().getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
        response.addHeader("Content-Length", "" + zipFile.length());
        try {
            // 以流的形式下载文件
            InputStream is = new BufferedInputStream(new FileInputStream(zipUrl));
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            os.write(buffer);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将文件压缩成zip
     *
     * @param srcFiles
     * @param zipFile
     */
    public static void zipFiles(List<File> srcFiles, File zipFile) {
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;

        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件集合
            for (int i = 0; i < srcFiles.size(); i++) {
                // 将源文件集合中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream(srcFiles.get(i));
                // 实例化 ZipEntry 对象，源文件数集合的当前文件
                zipEntry = new ZipEntry(srcFiles.get(i).getName());
                zipOutputStream.putNextEntry(zipEntry);
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 财务打印专用
     * 页面浏览器打印
     *
     * @param getSale
     * @param model
     * @param procInsId
     * @return
     */
    @RequestMapping(value = "getSysIndex")
    public String index(GetSale getSale, Model model, String procInsId) {
        /* System.out.println(procInsId); */
        getSale = getSaleService.getByProcInsId(procInsId);
        model.addAttribute("getSale", getSale);
        String saleDetailId = getSaleService.getSaleDetailIdByProcInsId(getSale.getProcInsId());
        List<ExpenseDetail> listOther = expenseDetailService.findById(saleDetailId);
        model.addAttribute("listOther", listOther);
        List<CostType> list = costTypeService.findCostType();
        model.addAttribute("list", list);
        return "modules/getSalefolder/getSysIndex";
    }

    /**
     * @return java.lang.String
     * @Author gangzi
     * @Description 报销申请中员工报销列表数据导出表格
     * @Date 19:47 2019/6/5
     * @Param [getSale, request, response, model, startTime1, endTime1]
     **/
    @RequestMapping(value = "historyExpense")
    public String historyExpense(GetSale getSale, HttpServletRequest request, HttpServletResponse response, Model model, String startTime1, String endTime1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (StringUtils.isNotBlank(startTime1)) {
                model.addAttribute("startTime", sdf1.parse(startTime1));
                getSale.setSt(sdf.parse(startTime1 + " 00:00:00"));
            }
            if (StringUtils.isNoneBlank(endTime1)) {
                model.addAttribute("endTime", sdf1.parse(endTime1));
                getSale.setEt(sdf.parse(endTime1 + " 23:59:59"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (getSale.getUser() != null && getSale.getUser().getId() != null && getSale.getUser().getId() != "") {
            model.addAttribute("userId", getSale.getUser().getId());
            model.addAttribute("userName", getSale.getUser().getName());
        }
        Page<GetSale> page = getSaleService.findPage(new Page<GetSale>(request, response), getSale);
        model.addAttribute("page", page);
        return "modules/getSalefolder/getSaleSubList";
    }

    /**
     * @return java.lang.String
     * @Author gangzi
     * @Description 报销申请中员工报销列表数据导出表格
     * @Date 19:48 2019/6/5
     * @Param [startTime1, endTime1, userId1, request, response, redirectAttributes]
     **/
    @ResponseBody
    @RequestMapping(value = "ExExcel")
    public String ExportCost(String startTime1, String endTime1, String userId1, HttpServletRequest request, HttpServletResponse response,
                             RedirectAttributes redirectAttributes) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String fileName = DateUtils.getDate("yyyy_MM_dd_") + "报销记录" + ".xlsx";
            GetSale getSale = new GetSale();
            if (StringUtils.isNotBlank(startTime1)) {
                getSale.setSt(sdf.parse(startTime1 + " 00:00:00"));
            }
            if (StringUtils.isNotBlank(endTime1)) {
                getSale.setEt(sdf.parse(endTime1 + " 23:59:59"));
            }
            String userId = null;
            if (StringUtils.isNotBlank(userId1)) {
                User user = new User();
                user.setId(userId1);
                getSale.setUser(user);
            }

//			Page<Checkin> page = checkinService.findPage(new Page<Checkin>(request, response), checkin);
            List<DownloadGetSale> list = getSaleService.downList(getSale);
            new ExportExcel("报销信息", DownloadGetSale.class).setDataList(list).write(response, fileName)
                    .dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:" + Global.getAdminPath() + "/get/sale/historyExpense?repage";
    }
}