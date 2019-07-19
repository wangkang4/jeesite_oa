package com.thinkgem.jeesite.modules.oapms.controller;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oapms.entity.Customer;
import com.thinkgem.jeesite.modules.oapms.entity.CustomerContact;
import com.thinkgem.jeesite.modules.oapms.entity.Visit;
import com.thinkgem.jeesite.modules.oapms.services.CustomerContactService;
import com.thinkgem.jeesite.modules.oapms.services.CustomerService;
import com.thinkgem.jeesite.modules.oapms.services.VisitService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/oapms/visit")
public class VisitController {
    @Autowired
    private VisitService visitService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerContactService customerContactService;

    @RequestMapping(value = "list")
    public String selectAllVisit(String customerContactId,
                                 Visit visit, HttpServletRequest request,
                                 HttpServletResponse response, Model model) {
        CustomerContact contact = new CustomerContact ();
        contact.setCustomerContactId ( customerContactId );
        visit.setCustomerContact ( contact );
        Page<Visit> page = visitService.findPage ( new Page<Visit> ( request, response ), visit );
        model.addAttribute ( "page", page );
        return "modules/oapms/visit/visitList";
    }

    @RequestMapping(value = "toAdd")
    public String toAdd(String customerContactId, Model model) {
        if (StringUtils.isNotBlank ( customerContactId )) {
            CustomerContact contact = customerContactService.findContactByContactId ( customerContactId );
            model.addAttribute ( "contact", contact );
        }
        return "modules/oapms/visit/addVisit";
    }

    @ResponseBody
    @RequestMapping(value = "loading")
    public Map<String, Object> loading() {
        List<Customer> customerList = customerService.selectIdAndName ();
        List<CustomerContact> contactList = customerContactService.selectContact ();
        Map<String, Object> map = new HashMap<String, Object> ();
        map.put ( "customerList", customerList );
        map.put ( "contactList", contactList );
        return map;
    }

    @RequestMapping(value = "add")
    public String insertVisit(Visit visit, String visitTimeString, Model model) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd" );
        visit.setVisitTime ( sdf.parse ( visitTimeString ) );
        visit.setCreateBy ( UserUtils.getUser () );
        visit.setUpdateBy ( UserUtils.getUser () );
        visit.setVisitId ( IdGen.uuid () );
        visitService.insertVisit ( visit );
        return "redirect:/a/oapms/visit/list?customerContactId=" + visit.getCustomerContact ().getCustomerContactId ();
    }

    @ResponseBody
    @RequestMapping(value = "deleteVisit")
    public Map<String, String> deleteVisit(String visitId) {
        Map<String, String> map = new HashMap<String, String> ();
        String createId = visitService.selectCreateByByVisitId ( visitId );
        String userId = UserUtils.getUser ().getId ();
        if (createId.equals ( userId ) || "1".equals ( userId )) {
            map.put ( "data", "ok" );
            visitService.deleteVisitById ( visitId );
        } else {
            map.put ( "data", "error" );
        }
        return map;
    }
}
