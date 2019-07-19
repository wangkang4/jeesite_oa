package com.thinkgem.jeesite.modules.oapms.controller;


import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oapms.entity.Customer;
import com.thinkgem.jeesite.modules.oapms.entity.PmsContract;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProject;
import com.thinkgem.jeesite.modules.oapms.services.CustomerService;
import com.thinkgem.jeesite.modules.oapms.services.PmsContractService;
import com.thinkgem.jeesite.modules.oapms.services.PmsProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/oapms/pmsContract")
public class PmsContractController extends BaseController {
    @Autowired
    private PmsContractService pmsContractService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PmsProjectService pmsProjectService;

    @ModelAttribute
    public PmsContract get(@RequestParam(required = false) String id) {
        PmsContract entity = null;
        if (StringUtils.isNotBlank ( id )) {
            entity = pmsContractService.get ( id );
        }
        if (entity == null) {
            entity = new PmsContract ();
        }
        return entity;
    }

    //列表展示
    @RequestMapping(value = {"list", ""})
    public String list(PmsContract pmsContract, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PmsContract> page = pmsContractService.findPage ( new Page<PmsContract> ( request, response ), pmsContract );
        model.addAttribute ( "page", page );
        model.addAttribute ( "pmsContract", pmsContract );
        List<Customer> customerList = customerService.selectIdAndName ();
        List<PmsProject> pmsProjectList = pmsProjectService.findIdAndName ();
        model.addAttribute ( "customerList", customerList );
        model.addAttribute ( "pmsProjectList", pmsProjectList );
        return "modules/oapms/pmsContract/contractList";
    }


    @RequestMapping(value = "form")
    public String form(PmsContract pmsContract, Model model) {
        List<Customer> customerList = customerService.selectIdAndName ();
        List<PmsProject> pmsProjectList = pmsProjectService.findIdAndName ();
        model.addAttribute ( "customerList", customerList );
        model.addAttribute ( "pmsProjectList", pmsProjectList );
        model.addAttribute ( "pmsContract", pmsContract );
        return "modules/oapms/pmsContract/pmsContractForm";
    }

    @RequestMapping(value = "toUpdate")
    public String toUpdate(@RequestParam String id, Model model) {
        PmsContract pmsContract = pmsContractService.selectById ( id );
        List<Customer> customerList = customerService.selectIdAndName ();
        List<PmsProject> pmsProjectList = pmsProjectService.findIdAndName ();
        model.addAttribute ( "customerList", customerList );
        model.addAttribute ( "pmsProjectList", pmsProjectList );
        model.addAttribute ( "pmsContract", pmsContract );
        return "modules/oapms/pmsContract/pmsContractUpdate";
    }

    @RequestMapping(value = "updatePms")
    public String updatePms(PmsContract pmsContract) {
        pmsContractService.update ( pmsContract );
        return "redirect:" + adminPath + "/oapms/pmsContract/?repage";
    }

    @RequestMapping(value = "save")
    public String save(PmsContract pmsContract, MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator ( model, pmsContract )) {
            return form ( pmsContract, model );
        }
        if (StringUtils.isNoneBlank ( pmsContract.getAttachmentAddress () )) {
            String sss = pmsContract.getAttachmentAddress ().substring ( 0, pmsContract.getAttachmentAddress ().length () - 1 );
            pmsContract.setAttachmentAddress ( sss );
        }
        String fileName = file.getOriginalFilename ();
        if (StringUtils.isNoneBlank ( fileName )) {
            String suffix = fileName.substring ( fileName.lastIndexOf ( "." ) );//后缀名
            String address = IdGen.uuid () + suffix;
            FileOutputStream fos = null;
            try {
                byte[] fileData = file.getBytes ();
                fos = new FileOutputStream ( Global.getConfig ( "contractUploadPath" ) + address );
                fos.write ( fileData );
                fos.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            pmsContract.setAttachmentAddress ( address );
            pmsContract.setAttachmentName ( fileName );
        }
        String attachmentName = pmsContractService.selectFileNameById ( pmsContract.getAttachmentAddress () );
        pmsContract.setAttachmentName ( attachmentName );
        pmsContractService.save ( pmsContract );
        addMessage ( redirectAttributes, "保存信息成功" );
        return "redirect:" + adminPath + "/oapms/pmsContract/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
        pmsContractService.delete ( id );
        addMessage ( redirectAttributes, "删除成功" );
        return "redirect:" + adminPath + "/oapms/pmsContract/list";
    }

}
