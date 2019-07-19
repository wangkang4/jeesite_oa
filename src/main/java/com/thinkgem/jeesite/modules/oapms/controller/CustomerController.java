package com.thinkgem.jeesite.modules.oapms.controller;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.oapms.entity.Customer;
import com.thinkgem.jeesite.modules.oapms.entity.CustomerDownLoad;
import com.thinkgem.jeesite.modules.oapms.entity.ProjectRelativer;
import com.thinkgem.jeesite.modules.oapms.entity.Replace;
import com.thinkgem.jeesite.modules.oapms.services.CustomerService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/oapms/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "list")
    public String selectAllCustomer(
            Customer customer, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        Page<Customer> page = customerService.findPage ( new Page<Customer> ( request, response ), customer );
        model.addAttribute ( "page", page );
        return "modules/oapms/customer/customerList";
    }

    @RequestMapping(value = "toAdd")
    public String toAdd(Customer customer, Model model) {
        return "modules/oapms/customer/addCustomer";
    }

    @ResponseBody
    @RequestMapping(value = "judgeToUpdate")
    public Map<String, String> judgeToUpdate(String customerId) {
        Map<String, String> map = new HashMap<String, String> ();
        String userId = UserUtils.getUser ().getId ();
        List<String> list = customerService.selectManagerByCustomerId ( customerId );
        if ("1".equals ( userId ) || list.contains ( userId )) {
            map.put ( "data", "ok" );
        } else {
            map.put ( "data", "error" );
        }
        return map;
    }

    @RequestMapping(value = "toUpdate")
    public String toUpdate(Customer customer, Model model) {
        customer = customerService.selectCustomerByCustomerId ( customer.getCustomerId () );
        model.addAttribute ( "customer", customer );
        List<String> persons = customerService.selectPersonsByCustomerId ( customer.getCustomerId () );
        if (persons.size () > 0) {
            String personsName = "";
            String personsId = "";
            for (int i = 0; i < persons.size (); i++) {
                personsId = personsId + persons.get ( i ) + ",";
                User person = systemService.getUser ( persons.get ( i ) );
                personsName = personsName + person.getName () + ",";
            }
            personsName = personsName.substring ( 0, personsName.length () - 1 );
            personsId = personsId.substring ( 0, personsId.length () - 1 );
            model.addAttribute ( "personsId", personsId );
            model.addAttribute ( "personsName", personsName );
        }
        return "modules/oapms/customer/updateCustomer";
    }

    @RequestMapping(value = "toCustomerDetail")
    public String toCustomerDetail(Customer customer, Model model) {
        customer = customerService.selectCustomerByCustomerId ( customer.getCustomerId () );
        model.addAttribute ( "customer", customer );
        List<String> persons = customerService.selectPersonsByCustomerId ( customer.getCustomerId () );
        if (persons.size () > 0) {
            String personsName = "";
            String personsId = "";
            for (int i = 0; i < persons.size (); i++) {
                personsId = personsId + persons.get ( i ) + ",";
                User person = systemService.getUser ( persons.get ( i ) );
                personsName = personsName + person.getName () + ",";
            }
            personsName = personsName.substring ( 0, personsName.length () - 1 );
            personsId = personsId.substring ( 0, personsId.length () - 1 );
            model.addAttribute ( "personsId", personsId );
            model.addAttribute ( "personsName", personsName );
        }
        return "modules/oapms/customer/customerDetail";
    }

    @RequestMapping(value = "update")
    public String update(Customer customer, Model model) {
        customer.setUpdateBy ( UserUtils.getUser () );
        customerService.updateCustomerByCustomer ( customer );
        customerService.deleteCustomerRelativerByCustomerId ( customer.getCustomerId () );
        ProjectRelativer projectRelativer = new ProjectRelativer ();
        projectRelativer.setId ( customer.getCustomerId () );
        projectRelativer.setRelativeType ( "0" );
        User saler = customer.getSaler ();
        projectRelativer.setEmployees ( saler );
        projectRelativer.setEmployeesType ( "销售经理" );
        customerService.insertProjectRelativer ( projectRelativer );
        User producter = customer.getProducter ();
        projectRelativer.setEmployees ( producter );
        projectRelativer.setEmployeesType ( "产品经理" );
        customerService.insertProjectRelativer ( projectRelativer );
        String[] list = customer.getPersons ().split ( "," );
        projectRelativer.setEmployeesType ( "相关人" );
        for (int i = 0; i < list.length; i++) {
            User person = new User ();
            if (StringUtils.isNotBlank ( list[i] )) {
                person.setId ( list[i] );
                projectRelativer.setEmployees ( person );
                customerService.insertProjectRelativer ( projectRelativer );
            }
        }
        return "redirect:/a/oapms/customer/list";
    }

    @ResponseBody
    @RequestMapping(value = "judgeCustomerName")
    public Map<String, String> judgeCustomerName(String customerName) {
        Map<String, String> map = new HashMap<String, String> ();
        int count = customerService.judgeCustomerName ( customerName );
        if (count == 1) {
            map.put ( "data", "error" );
        } else {
            map.put ( "data", "ok" );
        }
        return map;
    }

    @RequestMapping(value = "add")
    public String insertCustomer(Customer customer, @RequestParam("file") MultipartFile file, Model model, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        String fileName = file.getOriginalFilename ();
        if (StringUtils.isNoneBlank ( fileName )) {
            String suffix = fileName.substring ( fileName.lastIndexOf ( "." ) );
            String attachmentAddress = IdGen.uuid ();
            FileOutputStream fos = null;
            try {
                byte[] fileData = file.getBytes ();
                fos = new FileOutputStream ( Global.getConfig ( "windowsUploadPath" ) + attachmentAddress + suffix );
                fos.write ( fileData );
                fos.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            customer.setAttachmentName ( fileName );
            customer.setAttachmentAddress ( attachmentAddress );
        }

        customer.setCustomerId ( IdGen.uuid () );
        customer.setCreateBy ( UserUtils.getUser () );
        customer.setUpdateBy ( UserUtils.getUser () );
        customerService.insertCustomer ( customer );
        ProjectRelativer projectRelativer = new ProjectRelativer ();
        projectRelativer.setId ( customer.getCustomerId () );
        projectRelativer.setRelativeType ( "0" );
        User saler = customer.getSaler ();
        projectRelativer.setEmployees ( saler );
        projectRelativer.setEmployeesType ( "销售经理" );
        customerService.insertProjectRelativer ( projectRelativer );
        User producter = customer.getProducter ();
        projectRelativer.setEmployees ( producter );
        projectRelativer.setEmployeesType ( "产品经理" );
        customerService.insertProjectRelativer ( projectRelativer );
        String[] list = customer.getPersons ().split ( "," );
        projectRelativer.setEmployeesType ( "相关人" );
        for (int i = 0; i < list.length; i++) {
            User person = new User ();
            if (StringUtils.isNotBlank ( list[i] )) {
                String id = list[i];
                person.setId ( id );
                projectRelativer.setEmployees ( person );
                customerService.insertProjectRelativer ( projectRelativer );
            }
        }
        return "redirect:/a/oapms/customer/list";
    }

    @RequestMapping(value = "download")
    public String fileDownload(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String attachmentName = customerService.selectAttachmentById ( id );
        String[] str = attachmentName.split ( "\\." );
        String suffix = str[1];
        String downloadPrefix = Global.getConfig ( "windowsUploadPath" );
        String url = downloadPrefix + id + "." + suffix;
        File file = new File ( url );
        // 清空response
        response.reset ();
        // 设置response的Header
        response.addHeader ( "Content-Disposition",
                "attachment;filename=" + new String ( attachmentName.getBytes ( "gbk" ), "iso-8859-1" ) ); // 转码之后下载的文件不会出现中文乱码
        response.addHeader ( "Content-Length", "" + file.length () );

        try {
            // 以流的形式下载文件
            InputStream is = new BufferedInputStream ( new FileInputStream ( url ) );
            byte[] buffer = new byte[is.available ()];
            is.read ( buffer );
            is.close ();

            OutputStream os = new BufferedOutputStream ( response.getOutputStream () );
            os.write ( buffer );
            os.flush ();
            os.close ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "deleteCustomer")
    public Map<String, String> deleteCustomer(String customerId) {
        Map<String, String> map = new HashMap<String, String> ();
        String userId = UserUtils.getUser ().getId ();
        List<String> list = customerService.selectManagerByCustomerId ( customerId );
        if ("1".equals ( userId ) || list.contains ( userId )) {
            customerService.deleteCustomer ( customerId );
            customerService.deleteCustomerRelativerByCustomerId ( customerId );
            map.put ( "data", "ok" );
        } else {
            map.put ( "data", "error" );
        }
        //根据客户Id删除与其对应的联系人
        customerService.deleteContactByCustmerId ( customerId );
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "downCustomer")
    public String downCustomer(
            Customer customer, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        List<Customer> list = customerService.findList ( customer );
        List<Replace> categoryList = customerService.selectCategory ();
        List<Replace> industryList = customerService.selectIndustry ();
        List<CustomerDownLoad> loadList = new ArrayList<CustomerDownLoad> ();
        for (Customer cu : list) {
            CustomerDownLoad cd = new CustomerDownLoad ();
            cd.setCustomerName ( cu.getCustomerName () );
            cd.setAddress ( cu.getAddress () );
            for (Replace replace : categoryList) {
                if (replace.getValue ().equals ( cu.getCategory () )) {
                    cd.setCategory ( replace.getLabel () );
                }
            }
            for (Replace replace : industryList) {
                if (replace.getValue ().equals ( cu.getIndustry () )) {
                    cd.setIndustry ( replace.getLabel () );
                }
            }
            cd.setSalerName ( cu.getSaler ().getName () );
            cd.setProducterName ( cu.getProducter ().getName () );
            cd.setCreateByName ( cu.getCreateBy ().getName () );
            loadList.add ( cd );
        }
        String fileName = DateUtils.getDate ( "yyyy_MM_dd_" ) + "customer" + ".xlsx";
        try {
            new ExportExcel ( "客户信息", CustomerDownLoad.class ).setDataList ( loadList ).write ( response, fileName ).dispose ();
            return null;
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return "redirect:/a/oapms/customer/list";
    }
}
