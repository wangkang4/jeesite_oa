package com.thinkgem.jeesite.modules.oapms.controller;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.oapms.entity.Customer;
import com.thinkgem.jeesite.modules.oapms.entity.CustomerContact;
import com.thinkgem.jeesite.modules.oapms.services.CustomerContactService;
import com.thinkgem.jeesite.modules.oapms.services.CustomerService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/oapms/contact")
public class CustomerContactController {
    @Autowired
    private CustomerContactService customerContactService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "list")
    public String selectAllContact(String customerId, CustomerContact customerContact,
                                   HttpServletRequest request,
                                   HttpServletResponse response, Model model) {
        if (StringUtils.isNotBlank ( customerId )) {
            Customer customer = new Customer ();
            customer.setCustomerId ( customerId );
            customerContact.setCustomer ( customer );
        }
        model.addAttribute ( "contact", customerContact );
        Page<CustomerContact> page =
                customerContactService.findPage ( new Page<CustomerContact> ( request, response ),
                        customerContact );
        model.addAttribute ( "page", page );
        List<Customer> customerList = customerService.selectIdAndName ();
        model.addAttribute ( "customerList", customerList );
        return "modules/oapms/contact/contactList";
    }

    @ResponseBody
    @RequestMapping(value = "downContact")
    public String downCustomer(
            CustomerContact contact, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        List<CustomerContact> list = customerContactService.findList ( contact );
        List<CustomerContact> loadList = new ArrayList<CustomerContact> ();
        for (CustomerContact ct : list) {
            ct.setCustomerName ( ct.getCustomer ().getCustomerName () );
            loadList.add ( ct );
        }
        String fileName = DateUtils.getDate ( "yyyy_MM_dd_" ) + "contact" + ".xlsx";
        try {
            new ExportExcel ( "联系人信息", CustomerContact.class ).setDataList ( loadList ).write ( response, fileName ).dispose ();
            return null;
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return "redirect:/a/oapms/contact/list";
    }

    @RequestMapping(value = "toAdd")
    public String toAdd(String customerId, Model model) {
        List<Customer> customerList = customerService.selectIdAndName ();
        model.addAttribute ( "customerList", customerList );
        model.addAttribute ( "customerId", customerId );
        return "modules/oapms/contact/addContact";
    }

    @RequestMapping(value = "toUpdate")
    public String toUpdate(CustomerContact contact, Model model) {
        contact = customerContactService.findContactByContactId ( contact.getCustomerContactId () );
        model.addAttribute ( "contact", contact );
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd" );
        if (contact.getBirthday () != null) {
            String birthdayString = sdf.format ( contact.getBirthday () );
            model.addAttribute ( "birthdayString", birthdayString );
        }
        List<Customer> customerList = customerService.selectIdAndName ();
        model.addAttribute ( "customerList", customerList );
        return "modules/oapms/contact/updateContact";
    }

    @RequestMapping(value = "add")
    public String insertContact(CustomerContact contact, String birthdayString, Model model) throws ParseException {
        contact.setCustomerContactId ( IdGen.uuid () );
        contact.setCreateBy ( UserUtils.getUser () );
        contact.setUpdateBy ( UserUtils.getUser () );
        if (StringUtils.isNotBlank ( birthdayString )) {
            SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd" );
            contact.setBirthday ( sdf.parse ( birthdayString ) );
        }
        customerContactService.insertContact ( contact );
        return "redirect:/a/oapms/contact/list";
    }

    @RequestMapping(value = "update")
    public String updateContact(CustomerContact contact, String birthdayString, Model model) throws ParseException {
        contact.setUpdateBy ( UserUtils.getUser () );
        if (StringUtils.isNotBlank ( birthdayString )) {
            SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd" );
            contact.setBirthday ( sdf.parse ( birthdayString ) );
        }
        customerContactService.updateContact ( contact );
        return "redirect:/a/oapms/contact/list";
    }

    @RequestMapping(value = "toContactDetail")
    public String toContactDetail(CustomerContact contact, Model model) {
        contact = customerContactService.findContactByContactId ( contact.getCustomerContactId () );
        model.addAttribute ( "contact", contact );
        String birthdayString = "";
        if (contact.getBirthday () != null) {
            SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd" );
            birthdayString = sdf.format ( contact.getBirthday () );
        }
        model.addAttribute ( "birthdayString", birthdayString );
        return "modules/oapms/contact/contactDetail";
    }

    @RequestMapping(value = "deleteContact")
    public String deleteContact(String customerContactId) {
        customerContactService.deleteCustomerContact ( customerContactId );
        return "redirect:/a/oapms/contact/list";
    }
}
