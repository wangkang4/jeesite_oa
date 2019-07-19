/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.customer.entity.CustomerInfo;
import com.thinkgem.jeesite.modules.customer.service.CustomerInfoService;
import com.thinkgem.jeesite.modules.oaproject.entity.*;
import com.thinkgem.jeesite.modules.oaproject.service.PmProjectDetailedService;
import com.thinkgem.jeesite.modules.oaproject.service.PmProjectMainService;
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
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/oaproject/pmProjectDetailed")
public class PmProjectDetailedController extends BaseController {

    @Autowired
    private PmProjectDetailedService pmProjectDetailedService;
    @Autowired
    private PmProjectMainService pmProjectMainService;
    @Autowired
    private CustomerInfoService customerInfoService;

    @ModelAttribute
    public PmProjectDetailed get(@RequestParam(required = false) String id) {
        PmProjectDetailed entity = null;
        if (StringUtils.isNotBlank ( id )) {
            entity = pmProjectDetailedService.get ( id );
        }
        if (entity == null) {
            entity = new PmProjectDetailed ();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    public String list(PmProjectDetailed pmProjectDetailed, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PmProjectDetailed> page = pmProjectDetailedService.findPage ( new Page<PmProjectDetailed> ( request, response ), pmProjectDetailed );
        model.addAttribute ( "page", page );
        return "modules/oaproject/pmProjectDetailedList";
    }

    @RequestMapping(value = "form")
    public String form(PmProjectDetailed pmProjectDetailed, Model model) {
        List<PmProjectMain> projectMainList = pmProjectMainService.findPmProjectMainList ();
        List<CustomerInfo> customerInfoList = customerInfoService.getCustomerInfoList ();

        model.addAttribute ( "customerInfoList", customerInfoList );
        model.addAttribute ( "projectMainList", projectMainList );
        model.addAttribute ( "pmProjectDetailed", pmProjectDetailed );
        return "modules/oaproject/pmProjectDetailedForm";
    }

    @RequestMapping(value = "save")
    public String save(PmProjectDetailed pmProjectDetailed, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (!beanValidator ( model, pmProjectDetailed )) {
            return form ( pmProjectDetailed, model );
        }
        pmProjectDetailedService.save ( pmProjectDetailed );
        //删除项目关联表
        pmProjectDetailedService.delProjectRelation ( pmProjectDetailed.getProjectId () );
        //删除项目客户关联表
        pmProjectDetailedService.delProjectCustomer ( pmProjectDetailed.getProjectId () );
        //删除项目竞争对手关联表
        pmProjectDetailedService.delProjectOpponent ( pmProjectDetailed.getProjectId () );
        //删除项目合作单位关联表
        pmProjectDetailedService.delProjectCooperation ( pmProjectDetailed.getProjectId () );
        //保存项目关联表
        String[] otherProjiectId = request.getParameterValues ( "otherProjiectId" );
        for (int i = 0; i < otherProjiectId.length; i++) {
            PmProjectRelation projectRelation = new PmProjectRelation ();
            projectRelation.setId ( IdGen.uuid () );
            projectRelation.setProjectId ( pmProjectDetailed.getProjectId () );
            projectRelation.setOtherProjectId ( otherProjiectId[i] );
            projectRelation.setCreateBy ( pmProjectDetailed.getCreateBy () );
            projectRelation.setCreateDate ( pmProjectDetailed.getCreateDate () );
            projectRelation.setUpdateBy ( pmProjectDetailed.getUpdateBy () );
            projectRelation.setUpdateDate ( pmProjectDetailed.getUpdateDate () );
            pmProjectDetailedService.saveProjectRelationList ( projectRelation );
        }

        //客户关联
        String[] customerId = request.getParameterValues ( "customerId" );
        for (int i = 0; i < customerId.length; i++) {
            PmProjectCustomer projectCustomer = new PmProjectCustomer ();
            projectCustomer.setId ( IdGen.uuid () );
            projectCustomer.setProjectId ( pmProjectDetailed.getProjectId () );
            projectCustomer.setCustomerId ( customerId[i] );
            projectCustomer.setCreateBy ( pmProjectDetailed.getCreateBy () );
            projectCustomer.setCreateDate ( pmProjectDetailed.getCreateDate () );
            projectCustomer.setUpdateBy ( pmProjectDetailed.getUpdateBy () );
            projectCustomer.setUpdateDate ( pmProjectDetailed.getUpdateDate () );
            pmProjectDetailedService.saveProjectCustomerList ( projectCustomer );
        }

        //项目竞争对手关联表和竞争对手信息
        String[] opponetName = request.getParameterValues ( "opponetName" );
        String[] opponetContent = request.getParameterValues ( "opponetContent" );
        for (int i = 0; i < opponetName.length; i++) {
            PmProjectOpponent projectOpponent = new PmProjectOpponent ();
            projectOpponent.setId ( IdGen.uuid () );
            projectOpponent.setOpponentId ( IdGen.uuid () );
            projectOpponent.setProjectId ( pmProjectDetailed.getProjectId () );
            projectOpponent.setOpponetContent ( opponetContent[i] );
            projectOpponent.setOpponetName ( opponetName[i] );
            projectOpponent.setCreateBy ( pmProjectDetailed.getCreateBy () );
            projectOpponent.setCreateDate ( pmProjectDetailed.getCreateDate () );
            projectOpponent.setUpdateBy ( pmProjectDetailed.getUpdateBy () );
            projectOpponent.setUpdateDate ( pmProjectDetailed.getUpdateDate () );
            pmProjectDetailedService.saveProjectOpponentList ( projectOpponent );
        }

        //保存合作单位信息和项目关系
        String[] cooperation = request.getParameterValues ( "cooperation" );
        String[] contacts = request.getParameterValues ( "contacts" );
        String[] iphone = request.getParameterValues ( "iphone" );
        String[] position = request.getParameterValues ( "position" );
        String[] cooperationPattern = request.getParameterValues ( "cooperationPattern" );
        for (int i = 0; i < cooperation.length; i++) {
            PmProjectCooperation projectCooperation = new PmProjectCooperation ();
            projectCooperation.setId ( IdGen.uuid () );
            projectCooperation.setCooperationId ( IdGen.uuid () );
            projectCooperation.setProjectId ( pmProjectDetailed.getProjectId () );
            projectCooperation.setCooperation ( cooperation[i] );
            projectCooperation.setContacts ( contacts[i] );
            projectCooperation.setIphone ( iphone[i] );
            projectCooperation.setPosition ( position[i] );
            projectCooperation.setCooperationPattern ( cooperationPattern[i] );
            projectCooperation.setCreateBy ( pmProjectDetailed.getCreateBy () );
            projectCooperation.setCreateDate ( pmProjectDetailed.getCreateDate () );
            projectCooperation.setUpdateBy ( pmProjectDetailed.getUpdateBy () );
            projectCooperation.setUpdateDate ( pmProjectDetailed.getUpdateDate () );
            pmProjectDetailedService.saveProjectCooperationList ( projectCooperation );
        }

        addMessage ( redirectAttributes, "保存单表成功" );
        return "redirect:" + Global.getAdminPath () + "/oaproject/pmProjectMain";
    }

    @RequestMapping(value = "delete")
    public String delete(PmProjectDetailed pmProjectDetailed, RedirectAttributes redirectAttributes) {
        pmProjectDetailedService.delete ( pmProjectDetailed );
        addMessage ( redirectAttributes, "删除单表成功" );
        return "redirect:" + Global.getAdminPath () + "/oaproject/pmProjectDetailed/?repage";
    }

    @ResponseBody
    @RequestMapping(value = "getCustomer")
    public Map<String, List<CustomerInfo>> getProjectMain() {
        Map<String, List<CustomerInfo>> map = new HashMap<String, List<CustomerInfo>> ();
        List<CustomerInfo> customerInfoList = customerInfoService.getCustomerInfoList ();
        map.put ( "1", customerInfoList );
        return map;
    }
}