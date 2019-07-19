/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oaproject.entity.PmProjectFeedback;
import com.thinkgem.jeesite.modules.oaproject.entity.PmTaskMain;
import com.thinkgem.jeesite.modules.oaproject.service.PmProjectFeedbackService;
import com.thinkgem.jeesite.modules.oaproject.service.PmTaskMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 单表生成Controller
 *
 * @author zhangbingbing
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/oaproject/pmProjectFeedback")
public class PmProjectFeedbackController extends BaseController {

    @Autowired
    private PmProjectFeedbackService pmProjectFeedbackService;
    @Autowired
    private PmTaskMainService pmTaskMainService;

    @ModelAttribute
    public PmProjectFeedback get(@RequestParam(required = false) String id) {
        PmProjectFeedback entity = null;
        if (StringUtils.isNotBlank ( id )) {
            entity = pmProjectFeedbackService.get ( id );
        }
        if (entity == null) {
            entity = new PmProjectFeedback ();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    public String list(PmProjectFeedback pmProjectFeedback, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PmProjectFeedback> page = pmProjectFeedbackService.findPage ( new Page<PmProjectFeedback> ( request, response ), pmProjectFeedback );
        model.addAttribute ( "page", page );
        return "modules/oaproject/pmProjectFeedbackList";
    }

    @RequestMapping(value = "form")
    public String form(PmProjectFeedback pmProjectFeedback, Model model) {
        PmTaskMain pmTaskMain = pmTaskMainService.findPmTaskMain ( pmProjectFeedback.getTaskId () );
        model.addAttribute ( "pmTaskMain", pmTaskMain );
        model.addAttribute ( "pmProjectFeedback", pmProjectFeedback );
        return "modules/oaproject/pmProjectFeedbackForm";
    }

    @RequestMapping(value = "save")
    public String save(PmProjectFeedback pmProjectFeedback, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator ( model, pmProjectFeedback )) {
            return form ( pmProjectFeedback, model );
        }
        pmProjectFeedbackService.save ( pmProjectFeedback );
        addMessage ( redirectAttributes, "保存单表成功" );
        return "redirect:" + Global.getAdminPath () + "/oaproject/pmProjectFeedback/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(PmProjectFeedback pmProjectFeedback, RedirectAttributes redirectAttributes) {
        pmProjectFeedbackService.delete ( pmProjectFeedback );
        addMessage ( redirectAttributes, "删除单表成功" );
        return "redirect:" + Global.getAdminPath () + "/oaproject/pmProjectFeedback/?repage";
    }

}