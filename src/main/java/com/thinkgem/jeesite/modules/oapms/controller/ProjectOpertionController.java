package com.thinkgem.jeesite.modules.oapms.controller;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectHelp;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectOperation;
import com.thinkgem.jeesite.modules.oapms.services.PmsProjectOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${adminPath}/pms/projectOpertion")
public class ProjectOpertionController extends BaseController {

    @Autowired
    private PmsProjectOperationService pmsProjectOperationService;

    @RequestMapping(value = "list")
    public String list(PmsProjectOperation pmsProjectOperation, PmsProjectHelp pmsProjectHelp, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PmsProjectOperation> page = pmsProjectOperationService.findProjectOperationList ( new Page<PmsProjectOperation> ( request, response ), pmsProjectOperation );
        model.addAttribute ( "page", page );
        return "modules/oapms/projectOperation";
    }

}
