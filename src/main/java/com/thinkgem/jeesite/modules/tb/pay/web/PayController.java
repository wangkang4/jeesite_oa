package com.thinkgem.jeesite.modules.tb.pay.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.insertRollBack.HistoryPROC;
import com.thinkgem.jeesite.common.mapper.insertRollBack.RollBackService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.tb.contract.entity.Contract;
import com.thinkgem.jeesite.modules.tb.pay.entity.Pay;
import com.thinkgem.jeesite.modules.tb.pay.entity.PayTypeBig;
import com.thinkgem.jeesite.modules.tb.pay.entity.PayTypeSmall;
import com.thinkgem.jeesite.modules.tb.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/tb/pay")
public class PayController extends BaseController {
    @Autowired
    private PayService payService;
    @Autowired
    private RollBackService rollBackService;

    @ModelAttribute
    public Pay get(@RequestParam(required = false) String id) {
        Pay pay = null;
        if (StringUtils.isNotBlank(id)) {
            pay = payService.get(id);
        }
        if (pay == null) {
            pay = new Pay();
        }
        return pay;
    }

    @RequestMapping(value = "toAdd")
    public String toAdd(Pay pay, Model model) {
        pay.setEname(UserUtils.getUser().getCompany().getName());
        pay.setCreateBy(UserUtils.getUser());
        pay.setCreateDate(new Date());
        model.addAttribute("pay", pay);
        return "modules/tb/pay/addPay";
    }

    @RequestMapping("add")
    public String add(Pay pay, Model model, RedirectAttributes redirectAttributes) {
        //pay.setEname(UserUtils.getUser().getCompany().getName());
        pay.setCreateBy(UserUtils.getUser());
        pay.setCreateDate(new Date());
        pay.setReason("对外付款申请-" + pay.getPayMoney() + "元-" + UserUtils.getUser().getOffice().getName() + "-"
                + UserUtils.getUser().getName());
        payService.save(pay);
        return "redirect:" + adminPath + "/tb/pay/list";
    }

    @ResponseBody
    @RequestMapping("uploadfile")
    public String upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String address = "";
        if (StringUtils.isNoneBlank(fileName)) {
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            address = IdGen.uuid() + suffix;
            FileOutputStream fos = null;
            try {
                byte[] fileData = file.getBytes();
                fos = new FileOutputStream(Global.getConfig("PayUploadPath") + address);
                fos.write(fileData);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return address;
    }



    @RequestMapping("upload")
    public String update(@RequestParam("file") MultipartFile file, String id) {
        String fileName = file.getOriginalFilename();
        if (StringUtils.isNoneBlank(fileName)) {
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            String invoicAddress = IdGen.uuid() + suffix;
            FileOutputStream fos = null;
            try {
                byte[] fileData = file.getBytes();
                fos = new FileOutputStream(Global.getConfig("PayUploadPath") + invoicAddress);
                fos.write(fileData);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Pay pay = new Pay();
            pay.setId(id);
            pay.setInvoicAddress(invoicAddress);
            payService.upload(pay);
        }
        return "redirect:/a/tb/pay/employeesList";
    }

    @RequestMapping("download")
    public String download(Pay pay, HttpServletRequest request, HttpServletResponse response, String sign) throws IOException {
        String downloadPrefix = Global.getConfig("PayUploadPath");
        String url = "";
        if ("1".equals(sign)) {
            url = downloadPrefix + pay.getInvoicAddress();
            String[] str = pay.getInvoicAddress().split("\\.");
            pay.setApplyAddress(pay.getReason() + "-银行流水." + str[1]);//临时存放文件名称
        } else {
            url = downloadPrefix + pay.getApplyAddress();
            String[] str = pay.getApplyAddress().split("\\.");
            pay.setApplyAddress(pay.getReason() + "-提交." + str[1]);
        }
        File file = new File(url);
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition",
                "attachment;filename=" + new String(pay.getApplyAddress().getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
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

    @RequestMapping("list")
    public String payList(Pay pay, HttpServletRequest request, HttpServletResponse response, Model model) {
        pay.setCreateBy(UserUtils.getUser());

        Page<Pay> page = payService.findPage(new Page<Pay>(request, response), pay);
        model.addAttribute("page", page);
        return "modules/tb/pay/payList";
    }

    /**
     * 行政人员查看所属区域申请列表
     *
     * @param pay
     * @param act
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "payList2")
    public String payList2(Pay pay, Act act, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Pay> page = payService.findPage2(new Page<Pay>(request, response), pay);
        model.addAttribute("page", page);
        return "modules/tb/pay/payList2";
    }

    @RequestMapping("employeesList")
    public String employeeList(Pay pay, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Pay> page = payService.findEmployeesPage(new Page<Pay>(request, response), pay);
        model.addAttribute("page", page);
        model.addAttribute("st", pay.getSt());
        model.addAttribute("et", pay.getEt());
        model.addAttribute("createBy", pay.getCreateBy());
        return "modules/tb/pay/payEmployeesList";
    }

    @RequestMapping("form")
    public String form(Pay pay, Model model) {
        String view = "payView";
        if (StringUtils.isNotBlank(pay.getId())) {
            // 获取环节ID
            String taskDefKey = pay.getAct().getTaskDefKey();
            if (pay.getAct().isFinishTask()) {
                view = "payView";
            } else if ("userTask1".equals(taskDefKey) ||
                    "userTask2".equals(taskDefKey) ||
                    "userTask3".equals(taskDefKey) ||
                    "userTask4".equals(taskDefKey) ||
                    "userTask5".equals(taskDefKey)) {
                view = "payAudit";
            } else if ("modifyTask".equals(taskDefKey)) {
                view = "toAdd";
            }
        }
        model.addAttribute("pay", pay);
        return "modules/tb/pay/" + view;
    }

    @RequestMapping("saveAudit")
    public String saveAudit(Pay pay) {
        payService.auditSave(pay);
        //return "redirect:" + adminPath + "/act/task/todo?click";
        return "redirect:" + adminPath + "/act/task/todo";
    }

    @ResponseBody
    @RequestMapping("selectType")
    public Map<String, Object> selectType() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<PayTypeBig> blist = payService.selectFromPayTypeBig();
        List<PayTypeSmall> slist = payService.selectFromPayTypeSmall();
        map.put("blist", blist);
        map.put("slist", slist);
        return map;
    }

    @ResponseBody
    @RequestMapping("getContract")
    public Map<String, Object> getContract() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Contract> list = payService.getContract(UserUtils.getUser().getId());
        map.put("contract", list);
        return map;
    }

    @RequestMapping(value = "print")
    public String print(String id, Model model) {
        Pay pay = payService.get(id);
        model.addAttribute("pay", pay);
        List<HistoryPROC> historyList = rollBackService.selectHistoryPROC(pay.getAct().getProcInsId());
        model.addAttribute("historyList", historyList);
        return "modules/tb/pay/printPayView";
    }

    /**
     * 作废流程
     *
     * @param
     * @return 重定向至@RequestMapping(value=list)方法
     */
    @RequestMapping(value = "back")
    public String back(Pay pay) {
        payService.deletePay(pay.getProcInsId());
        /*payService.deleteTask(pay.getProcInsId());*/
        return "redirect:" + adminPath + "/tb/pay/list";
    }

    /**
     * 财务打印页面专用
     *
     * @param pay
     * @param model
     * @return
     */
    @RequestMapping("getSysIndex")
    public String getSysIndex(Pay pay, Model model) {
        model.addAttribute("pay", pay);
        return "modules/tb/pay/paySysIndex";
    }
}
