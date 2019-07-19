/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oaproject.entity.PmProjectMain;
import com.thinkgem.jeesite.modules.oaproject.entity.PmTaskMain;
import com.thinkgem.jeesite.modules.oaproject.service.PmProjectMainService;
import com.thinkgem.jeesite.modules.oaproject.service.PmTaskMainService;
import com.thinkgem.jeesite.modules.sys.entity.User;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 单表生成Controller
 *
 * @author zhangbingbing
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/oaproject/pmTaskMain")
public class PmTaskMainController extends BaseController {

    @Autowired
    private PmTaskMainService pmTaskMainService;
    @Autowired
    private PmProjectMainService pmProjectMainService;

    @ModelAttribute
    public PmTaskMain get(@RequestParam(required = false) String id) {
        PmTaskMain entity = null;
        if (StringUtils.isNotBlank ( id )) {
            entity = pmTaskMainService.get ( id );
        }
        if (entity == null) {
            entity = new PmTaskMain ();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    public String list(PmTaskMain pmTaskMain, HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = UserUtils.getUser ();
        Page<PmTaskMain> page = null;
        if (user.getId ().equals ( "1" )) {
            page = pmTaskMainService.findPage ( new Page<PmTaskMain> ( request, response ), pmTaskMain );
        } else {
            pmTaskMain.setUserId ( user.getId () );
            page = pmTaskMainService.findPageList ( new Page<PmTaskMain> ( request, response ), pmTaskMain );
        }
        model.addAttribute ( "page", page );
        return "modules/oaproject/pmTaskMainList";
    }

    @RequestMapping(value = "form")
    public String form(PmTaskMain pmTaskMain, Model model) {
        List<PmProjectMain> pmProjectMainList = pmProjectMainService.findPmProjectMainList ();

        List<PmTaskMain> correlationList = pmTaskMainService.getCorrelationList ( pmTaskMain.getId () );
        if (correlationList.size () > 0) {
            String responsibilityId = "";
            String responsibilityName = "";
            String copyId = "";
            String copyName = "";
            for (int i = 0; i < correlationList.size (); i++) {
                if (correlationList.get ( i ).getPeoplePosition ().equals ( "copy" )) {
                    copyId = copyId + correlationList.get ( i ).getUserId () + ",";
                    copyName = copyName + correlationList.get ( i ).getUserName () + ",";
                }
                if (correlationList.get ( i ).getPeoplePosition ().equals ( "responsibility" )) {
                    responsibilityId = responsibilityId + correlationList.get ( i ).getUserId () + ",";
                    responsibilityName = responsibilityName + correlationList.get ( i ).getUserName () + ",";
                }
            }
            copyId = copyId.substring ( 0, copyId.length () - 1 );
            copyName = copyName.substring ( 0, copyName.length () - 1 );
            User copy = new User ();
            copy.setId ( copyId );
            copy.setName ( copyName );
            pmTaskMain.setCopy ( copy );

            responsibilityId = responsibilityId.substring ( 0, responsibilityId.length () - 1 );
            responsibilityName = responsibilityName.substring ( 0, responsibilityName.length () - 1 );
            User responsibility = new User ();
            responsibility.setId ( responsibilityId );
            responsibility.setName ( responsibilityName );
            pmTaskMain.setResponsibility ( responsibility );
        }

        model.addAttribute ( "pmProjectMainList", pmProjectMainList );
        model.addAttribute ( "pmTaskMain", pmTaskMain );
        return "modules/oaproject/pmTaskMainForm";
    }

    @RequestMapping(value = "save")
    public String save(PmTaskMain pmTaskMain, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator ( model, pmTaskMain )) {
            return form ( pmTaskMain, model );
        }

        pmTaskMainService.save ( pmTaskMain );
        pmTaskMainService.delCorrelation ( pmTaskMain.getId () );
        List<PmTaskMain> correlationList = new ArrayList<PmTaskMain> ();
        String responsibility[] = pmTaskMain.getResponsibility ().getId ().split ( "," );
        for (int i = 0; i < responsibility.length; i++) {
            PmTaskMain pmTaskMainList = new PmTaskMain ();
            pmTaskMainList.setId ( IdGen.uuid () );
            pmTaskMainList.setUserId ( responsibility[i] );
            pmTaskMainList.setTaskId ( pmTaskMain.getId () );
            pmTaskMainList.setPeoplePosition ( "responsibility" );
            pmTaskMainList.setCreateBy ( pmTaskMain.getCreateBy () );
            pmTaskMainList.setCreateDate ( pmTaskMain.getCreateDate () );
            pmTaskMainList.setUpdateBy ( pmTaskMain.getUpdateBy () );
            pmTaskMainList.setUpdateDate ( pmTaskMain.getUpdateDate () );
            pmTaskMainList.setDelFlag ( "0" );
            correlationList.add ( pmTaskMainList );
        }
        String copy[] = pmTaskMain.getCopy ().getId ().split ( "," );
        for (int i = 0; i < copy.length; i++) {
            PmTaskMain pmTaskMainList = new PmTaskMain ();
            pmTaskMainList.setId ( IdGen.uuid () );
            pmTaskMainList.setUserId ( copy[i] );
            pmTaskMainList.setTaskId ( pmTaskMain.getId () );
            pmTaskMainList.setPeoplePosition ( "copy" );
            pmTaskMainList.setCreateBy ( pmTaskMain.getCreateBy () );
            pmTaskMainList.setCreateDate ( pmTaskMain.getCreateDate () );
            pmTaskMainList.setUpdateBy ( pmTaskMain.getUpdateBy () );
            pmTaskMainList.setUpdateDate ( pmTaskMain.getUpdateDate () );
            pmTaskMainList.setDelFlag ( "0" );
            correlationList.add ( pmTaskMainList );
        }
        //保存责任人和抄送人
        pmTaskMainService.saveCorrelationList ( correlationList );
        addMessage ( redirectAttributes, "保存单表成功" );
        return "redirect:" + Global.getAdminPath () + "/oaproject/pmTaskMain/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(PmTaskMain pmTaskMain, RedirectAttributes redirectAttributes) {
        pmTaskMainService.delete ( pmTaskMain );
        addMessage ( redirectAttributes, "删除单表成功" );
        return "redirect:" + Global.getAdminPath () + "/oaproject/pmTaskMain/?repage";
    }

}