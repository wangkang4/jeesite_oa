package com.thinkgem.jeesite.modules.oapms.controller;


import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oapms.entity.PmsPayment;
import com.thinkgem.jeesite.modules.oapms.services.PmsPaymentService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Controller
@RequestMapping(value = "${adminPath}/oapms/pmsPayment")
public class PmsPaymentController extends BaseController {

    @Autowired
    private PmsPaymentService pmsPaymentService;

    @ModelAttribute
    public PmsPayment get(@RequestParam(required = false) String id) {
        PmsPayment entity = null;
        if (StringUtils.isNotBlank ( id )) {
            entity = pmsPaymentService.get ( id );
        }
        if (entity == null) {
            entity = new PmsPayment ();
        }
        return entity;
    }

    //列表展示
    @RequestMapping(value = {"list", ""})
    public String list(PmsPayment pmsPayment, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PmsPayment> page = pmsPaymentService.findPage ( new Page<PmsPayment> ( request, response ), pmsPayment );
        model.addAttribute ( "page", page );
        return "modules/oapms/pmsContract/paymentList";
    }

    //新增表单；
    @RequestMapping(value = "form")
    public String form(PmsPayment pmsPayment, Model model) {
        pmsPayment.setPaymentId ( new Random ( 5000 ).toString () );
        model.addAttribute ( "pmsPayment", pmsPayment );
        return "modules/oapms/pmsContract/paymentForm";
    }

    @RequestMapping(value = "save")
    public String save(PmsPayment pmsPayment, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator ( model, pmsPayment )) {
            return form ( pmsPayment, model );
        }
        pmsPaymentService.save ( pmsPayment );
        addMessage ( redirectAttributes, "保存信息成功" );
        return "redirect:" + adminPath + "/oapms/pmsPayment/?repage";
    }

    //去更新
    @RequestMapping(value = "toUpdate")
    public String toUpdate(@RequestParam String paymentId, Model model) {
        PmsPayment pmsPayment = pmsPaymentService.selectById ( paymentId );
        model.addAttribute ( "pmsPayment", pmsPayment );
        return "modules/oapms/pmsContract/paymentUpdate";
    }

    @RequestMapping(value = "updatePms")
    public String updatePms(PmsPayment pmsPayment) {
        pmsPayment.setUpdateBy ( UserUtils.getUser () );
        pmsPaymentService.update ( pmsPayment );
        return "redirect:" + adminPath + "/oapms/pmsPayment/?repage";
    }


    //删除；
    @RequestMapping(value = "delete")
    public String delete(@RequestParam String paymentId, RedirectAttributes redirectAttributes) {
        pmsPaymentService.delete ( paymentId );
        addMessage ( redirectAttributes, "删除成功" );
        return "redirect:" + adminPath + "/oapms/pmsPayment/list";
    }
		/*@RequestMapping(value="updatePms")
		public String updatePms(PmsContract pmsContract){
			pmsContractService.update(pmsContract);
			return "redirect:" + adminPath + "/oapms/pmsContract/?repage";
		}*/
		/*@RequestMapping(value="toUpdate")
		public String toUpdate(@RequestParam String id,Model model){
			PmsContract pmsContract=pmsContractService.selectById(id);
			List<Customer> customerList=customerService.selectIdAndName();
			List<PmsProject> pmsProjectList=pmsProjectService.findIdAndName();
			model.addAttribute("customerList", customerList);
			model.addAttribute("pmsProjectList", pmsProjectList);
			model.addAttribute("pmsContract", pmsContract);
			return "modules/oapms/pmsContract/pmsContractUpdate";
		}*/
}
