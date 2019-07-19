/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.customer.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.customer.entity.PmCustomerZtree;
import com.thinkgem.jeesite.modules.customer.service.PmCustomerZtreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单表生成Controller
 *
 * @author zhangbingbing
 * @version 2018-03-22
 */
@Controller
@RequestMapping(value = "${adminPath}/customer/pmCustomerZtree")
public class PmCustomerZtreeController extends BaseController {

    @Autowired
    private PmCustomerZtreeService pmCustomerZtreeService;

    @ModelAttribute
    public PmCustomerZtree get(@RequestParam(required = false) String id) {
        PmCustomerZtree entity = null;
        if (StringUtils.isNotBlank ( id )) {
            entity = pmCustomerZtreeService.get ( id );
        }
        if (entity == null) {
            entity = new PmCustomerZtree ();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    public String list(PmCustomerZtree pmCustomerZtree, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PmCustomerZtree> page = pmCustomerZtreeService.findPage ( new Page<PmCustomerZtree> ( request, response ), pmCustomerZtree );
        model.addAttribute ( "page", page );
        return "modules/customer/pmCustomerZtreeList";
    }

    @RequestMapping(value = "form")
    public String form(PmCustomerZtree pmCustomerZtree, Model model) {
        model.addAttribute ( "pmCustomerZtree", pmCustomerZtree );
        return "modules/customer/pmCustomerZtreeForm";
    }

    @RequestMapping(value = "save")
    public String save(PmCustomerZtree pmCustomerZtree, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator ( model, pmCustomerZtree )) {
            return form ( pmCustomerZtree, model );
        }
        pmCustomerZtree.setParentId ( "1" );
        pmCustomerZtreeService.save ( pmCustomerZtree );
        addMessage ( redirectAttributes, "保存单表成功" );
        return "redirect:" + Global.getAdminPath () + "/customer/pmCustomerZtree/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(PmCustomerZtree pmCustomerZtree, RedirectAttributes redirectAttributes) {
        pmCustomerZtreeService.delete ( pmCustomerZtree );
        addMessage ( redirectAttributes, "删除单表成功" );
        return "redirect:" + Global.getAdminPath () + "/customer/pmCustomerZtree/?repage";
    }

    @ResponseBody
    @RequestMapping("getCustomerZtree")
    public Map<String, List<PmCustomerZtree>> getCustomerZtree() {
        Map<String, List<PmCustomerZtree>> customerZtreeList = new HashMap<String, List<PmCustomerZtree>> ();
        List<PmCustomerZtree> pmCustomerZtreeList = pmCustomerZtreeService.findPmCustomerZtreeList ();
        customerZtreeList.put ( "1", pmCustomerZtreeList );
        return customerZtreeList;
    }

}