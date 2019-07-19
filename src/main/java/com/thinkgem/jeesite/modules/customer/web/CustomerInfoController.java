package com.thinkgem.jeesite.modules.customer.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.customer.entity.CustomerAnalysis;
import com.thinkgem.jeesite.modules.customer.entity.CustomerInfo;
import com.thinkgem.jeesite.modules.customer.entity.Family;
import com.thinkgem.jeesite.modules.customer.entity.Resume;
import com.thinkgem.jeesite.modules.customer.service.CustomerInfoService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.CustomerUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/customer")
public class CustomerInfoController extends BaseController {

    @Autowired
    private CustomerInfoService customerInfoService;

    /**
     * @param @param  customerInfo
     * @param @param  request
     * @param @param  response
     * @param @param  model
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: mycustomerList
     * @Description: 我的客户     列表页面
     */
    @RequestMapping(value = "mycustomerList")
    public String mycustomerList(CustomerInfo customerInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = UserUtils.getUser ();
        customerInfo.setCreateId ( user.getId () );

        String customerName = request.getParameter ( "customerName" );
        if (customerName != null && customerName != "") {
            customerInfo.setCustomerName ( customerName );
        }
        Page<CustomerInfo> page = null;
        if (customerInfo.getParentId () != null) {
            page = customerInfoService.findPageList ( new Page<CustomerInfo> ( request, response ), customerInfo );
        } else {
            page = customerInfoService.findPage ( new Page<CustomerInfo> ( request, response ), customerInfo );
        }
        model.addAttribute ( "customerListPage", page );
        return "modules/customer/mycustomerInfoList";
    }

    /**
     * @param @param  customerInfo
     * @param @param  request
     * @param @param  response
     * @param @param  model
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: allcustomerList
     * @Description: 所有客户    列表页面
     */
    @RequestMapping(value = "allcustomerList")
    public String allcustomerList(CustomerInfo customerInfo, HttpServletRequest request, HttpServletResponse response, Model model) {

        String customerName = request.getParameter ( "customerName" );
        if (customerName != null && customerName != "") {
            customerInfo.setCustomerName ( customerName );
        }
        Page<CustomerInfo> page = null;
        if (customerInfo.getParentId () != null) {
            page = customerInfoService.findPageList ( new Page<CustomerInfo> ( request, response ), customerInfo );
        } else {
            page = customerInfoService.findPage ( new Page<CustomerInfo> ( request, response ), customerInfo );
        }
        //Page<CustomerInfo> page = customerInfoService.findPage(new Page<CustomerInfo>(request,response),customerInfo);
        model.addAttribute ( "customerListPage", page );
        return "modules/customer/allcustomerInfoList";
    }

    /**
     * @param @param  model
     * @param @param  customerId
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: customerDetail
     * @Description: 客户详情页面
     */
    @RequestMapping(value = "customerDetail")
    public String customerDetail(Model model, String customerId, String customerName) {
        System.out.println ( customerId );
        System.out.println ( customerName );

        CustomerInfo customerInfo = customerInfoService.findCustomerInfoById ( customerId );
        List<Resume> resumeList = customerInfoService.findResumeListById ( customerId );
        List<Family> familyList = customerInfoService.findFamilyListById ( customerId );
        List<CustomerInfo> ztreeInfoList = customerInfoService.getZtreeInfoList ();
        model.addAttribute ( "ztreeInfoList", ztreeInfoList );

        model.addAttribute ( "customerInfo", customerInfo );
        model.addAttribute ( "resumeList", resumeList );
        model.addAttribute ( "familyList", familyList );
        model.addAttribute ( "customerId", customerId );
        model.addAttribute ( "customerName", customerName );
        return "modules/customer/customerDetailForm";
    }

    /**
     * 审批界面
     *
     * @param model
     * @param customerId
     * @param customerName
     * @return
     */
    @RequestMapping(value = "examine")
    public String examine(Model model, String customerId, String customerName) {

        CustomerInfo customerInfo = customerInfoService.findCustomerInfoById ( customerId );
        List<Resume> resumeList = customerInfoService.findResumeListById ( customerId );
        List<Family> familyList = customerInfoService.findFamilyListById ( customerId );
        List<CustomerInfo> ztreeInfoList = customerInfoService.getZtreeInfoList ();
        model.addAttribute ( "ztreeInfoList", ztreeInfoList );

        model.addAttribute ( "customerInfo", customerInfo );
        model.addAttribute ( "resumeList", resumeList );
        model.addAttribute ( "familyList", familyList );
        model.addAttribute ( "customerId", customerId );
        model.addAttribute ( "customerName", customerName );
        return "modules/customer/examine";
    }

    /**
     * @param @param  customerInfo
     * @param @param  model
     * @param @return 客户添加页面
     * @return String
     * @throws
     * @Title: form
     * @Description: 跳转到客户添加页面
     */
    @RequestMapping(value = "customerForm")
    public String form(CustomerInfo customerInfo, Model model) {
        List<CustomerInfo> ztreeInfoList = customerInfoService.getZtreeInfoList ();
        model.addAttribute ( "ztreeInfoList", ztreeInfoList );
        model.addAttribute ( "customerInfo", customerInfo );
        return "modules/customer/customerInfoForm";
    }

    /**
     * 保存审批意见
     *
     * @param request
     * @param customerInfo
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "saveexamine")
    public String saveexamine(HttpServletRequest request, CustomerInfo customerInfo, Model model, RedirectAttributes redirectAttributes) {
        System.out.println ( "执行保存方法！" );
        customerInfoService.saveexamine ( customerInfo );
        System.out.println ( "执行保存方法！" );
        addMessage ( redirectAttributes, "审批成功" );
        return "redirect:" + adminPath + "/customer/mycustomerList";
    }

    /**
     * @param @param  customerInfo 客户信息
     * @param @param  model
     * @param @param  redirectAttributes 重定向
     * @param @return 重定向到用户列表页面
     * @return String
     * @throws
     * @Title: save
     * @Description: 保存客户信息
     */
    @RequestMapping(value = "customerInfoSave")
    public String save(HttpServletRequest request, CustomerInfo customerInfo, Model model, RedirectAttributes redirectAttributes) {

        String customerId = CustomerUtils.getUUID ();
        customerInfo.setId ( customerId );
        System.out.println ( customerInfo.getId () + "," + customerInfo.getCustomerName () + "," + customerInfo.getCustomerBrithday () + "," + customerInfo.getCompany () + "," + customerInfo.getPosition () + "," + customerInfo.getSex () + "," + customerInfo.getHobby () + "," + customerInfo.getPhone () + "," + customerInfo.getAdress () + "," + customerInfo.getRemarks () );

        User user = UserUtils.getUser ();
        customerInfo.setCreateId ( user.getId () );
        customerInfo.setUpdateId ( user.getId () );
        Date now = CustomerUtils.getNowDate ();
        System.out.println ( now );
        customerInfo.setCreateDate ( now );
        customerInfo.setUpdateDate ( now );
        customerInfo.setExamine ( "待审批" );
        customerInfoService.saveCustomerInfo ( customerInfo );


        String[] customerCompanys = request.getParameterValues ( "customerCompany" );
        String[] customerPositions = request.getParameterValues ( "customerPosition" );
        String[] positionTimes = request.getParameterValues ( "positionTime" );

        String[] familyNames = request.getParameterValues ( "familyName" );
        String[] familyBirthdays = request.getParameterValues ( "familyBirthday" );
        String[] relationships = request.getParameterValues ( "relationship" );


        for (int i = 0; i < customerCompanys.length; i++) {
            Resume resume = new Resume ();
            String resumeId = CustomerUtils.getUUID ();
            resume.setId ( resumeId );
            resume.setCustomerId ( customerId );
            resume.setCustomerCompany ( customerCompanys[i] );
            resume.setCustomerPosition ( customerPositions[i] );
            resume.setPositionTime ( positionTimes[i] );
            resume.setCreateBy ( user );
            resume.setCreateDate ( now );
            resume.setUpdateBy ( user );
            resume.setUpdateDate ( now );
            System.out.print ( resume.getCustomerCompany () + "," + resume.getCustomerPosition () + "," + resume.getPositionTime () );
            customerInfoService.saveResume ( resume );
        }
        for (int j = 0; j < familyNames.length; j++) {
            Family family = new Family ();
            String familyId = CustomerUtils.getUUID ();
            family.setId ( familyId );
            family.setCustomerId ( customerId );
            family.setFamilyName ( familyNames[j] );
            family.setFamilyBrithday ( familyBirthdays[j] );
            family.setRelationship ( relationships[j] );
            family.setCreateBy ( user );
            family.setCreateDate ( now );
            family.setUpdateBy ( user );
            family.setUpdateDate ( now );
            System.out.print ( family.getFamilyName () + "," + family.getFamilyBrithday () + "," + family.getRelationship () );
            customerInfoService.saveFamily ( family );

        }

        System.out.println ( "执行保存方法！" );
        addMessage ( redirectAttributes, "保存客户信息成功" );
        return "redirect:" + adminPath + "/customer/mycustomerList";
    }

    /**
     * @param @param  model
     * @param @param  customerId
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: customerInfoUpdateForm
     * @Description: 跳转到    客户信息修改页面
     */
    @RequestMapping(value = "customerInfoUpdateForm")
    public String customerInfoUpdateForm(Model model, String customerId) {

        System.out.println ( customerId );

        //获取客户的所有信息
        CustomerInfo customerInfo = customerInfoService.findCustomerInfoById ( customerId );
        List<Resume> resumeList = customerInfoService.findResumeListById ( customerId );
        List<Family> familyList = customerInfoService.findFamilyListById ( customerId );
        List<CustomerInfo> ztreeInfoList = customerInfoService.getZtreeInfoList ();
        model.addAttribute ( "ztreeInfoList", ztreeInfoList );
        model.addAttribute ( "customerInfo", customerInfo );
        model.addAttribute ( "resumeList", resumeList );
        model.addAttribute ( "familyList", familyList );
        model.addAttribute ( "customerId", customerId );

        return "modules/customer/customerUpdateForm";
    }


    @RequestMapping(value = "customerInfoUpdate")
    public String customerInfoUpdate(HttpServletRequest request, CustomerInfo customerInfo, Model model, RedirectAttributes redirectAttributes) {

        String customerId = request.getParameter ( "customerId" );
        System.out.println ( "待修改客户的id：" + customerId );
        customerInfo.setId ( customerId );

        List<CustomerInfo> createByDate = customerInfoService.findCreateByDate ( customerId );
        String createId = createByDate.get ( 0 ).getCreateId ();
        Date createDate = createByDate.get ( 0 ).getCreateDate ();

        User user = UserUtils.getUser ();
        customerInfo.setCreateId ( createId );
        customerInfo.setUpdateId ( user.getId () );
        Date now = CustomerUtils.getNowDate ();
        customerInfo.setCreateDate ( createDate );
        customerInfo.setUpdateDate ( now );
        customerInfo.setExamine ( "待审批" );
        customerInfo.setReject ( "" );
        customerInfoService.updateCustomerInfo ( customerInfo );

        //通过客户id取到对应的履历id
        List<Resume> resumeIds = customerInfoService.findResumeIdsByCusId ( customerId );


        String[] customerCompanys = request.getParameterValues ( "customerCompany" );
        String[] customerPositions = request.getParameterValues ( "customerPosition" );
        String[] positionTimes = request.getParameterValues ( "positionTime" );

        int m = 0;
        if (resumeIds != null && resumeIds.size () > 0) {
            for (m = 0; m < resumeIds.size (); m++) {
                String resumeId = resumeIds.get ( m ).getId ();
                Resume resume = new Resume ();
                resume.setId ( resumeId );
                resume.setCustomerId ( customerId );
                resume.setCustomerCompany ( customerCompanys[m] );
                resume.setCustomerPosition ( customerPositions[m] );
                resume.setPositionTime ( positionTimes[m] );
                resume.setCreateId ( createId );
                resume.setCreateDate ( createDate );
                resume.setUpdateId ( user.getId () );
                resume.setUpdateDate ( now );
                System.out.print ( resume.getCustomerCompany () + "," + resume.getCustomerPosition () + "," + resume.getPositionTime () );
                customerInfoService.updateResume ( resume );
            }
            if (customerCompanys.length > m) {
                for (int n = customerCompanys.length - 1; m < n + 1; n--) {
                    String newresumeId = CustomerUtils.getUUID ();
                    Resume resume = new Resume ();
                    resume.setId ( newresumeId );
                    resume.setCustomerId ( customerId );
                    resume.setCustomerCompany ( customerCompanys[n] );
                    resume.setCustomerPosition ( customerPositions[n] );
                    resume.setPositionTime ( positionTimes[n] );
                    resume.setCreateId ( createId );
                    resume.setCreateDate ( createDate );
                    resume.setUpdateId ( user.getId () );
                    resume.setUpdateDate ( now );
                    System.out.print ( resume.getCustomerCompany () + "," + resume.getCustomerPosition () + "," + resume.getPositionTime () );
                    customerInfoService.saveResume ( resume );
                }
            }
        } else {
            for (int j = 0; j < customerCompanys.length; j++) {
                Resume resume = new Resume ();
                String resumeId = CustomerUtils.getUUID ();
                resume.setId ( resumeId );
                resume.setCustomerId ( customerId );
                resume.setCustomerCompany ( customerCompanys[j] );
                resume.setCustomerPosition ( customerPositions[j] );
                resume.setPositionTime ( positionTimes[j] );
                resume.setCreateId ( createId );
                resume.setCreateDate ( createDate );
                resume.setUpdateId ( user.getId () );
                resume.setUpdateDate ( now );
                customerInfoService.saveResume ( resume );
            }
        }


        //通过客户id取到对应的家庭信息id
        List<Family> familyIds = customerInfoService.findFamilyIdsByCusId ( customerId );

        String[] familyNames = request.getParameterValues ( "familyName" );
        String[] familyBirthdays = request.getParameterValues ( "familyBirthday" );
        String[] relationships = request.getParameterValues ( "relationship" );

        int p = 0;
        if (familyIds != null && familyIds.size () > 0) {
            for (p = 0; p < familyIds.size (); p++) {
                String familyId = familyIds.get ( p ).getId ();
                Family family = new Family ();
                family.setId ( familyId );
                family.setCustomerId ( customerId );
                family.setFamilyName ( familyNames[p] );
                family.setFamilyBrithday ( familyBirthdays[p] );
                family.setRelationship ( relationships[p] );
                family.setCreateId ( user.getId () );
                family.setCreateDate ( now );
                family.setUpdateId ( user.getId () );
                family.setUpdateDate ( now );
                customerInfoService.updateFamily ( family );
            }
            if (familyNames.length > p) {
                for (int q = familyNames.length - 1; p < q + 1; q--) {
                    String newfamilyId = CustomerUtils.getUUID ();
                    Family family = new Family ();
                    family.setId ( newfamilyId );
                    family.setCustomerId ( customerId );
                    family.setFamilyName ( familyNames[q] );
                    family.setFamilyBrithday ( familyBirthdays[q] );
                    family.setRelationship ( relationships[q] );
                    family.setCreateId ( user.getId () );
                    family.setCreateDate ( now );
                    family.setUpdateId ( user.getId () );
                    family.setUpdateDate ( now );
                    customerInfoService.saveFamily ( family );
                }
            }
        } else {
            for (int h = 0; h < familyNames.length; h++) {
                Family family = new Family ();
                String familyId = CustomerUtils.getUUID ();
                family.setId ( familyId );
                family.setCustomerId ( customerId );
                family.setFamilyName ( familyNames[h] );
                family.setFamilyBrithday ( familyBirthdays[h] );
                family.setRelationship ( relationships[h] );
                family.setCreateId ( user.getId () );
                family.setCreateDate ( now );
                family.setUpdateId ( user.getId () );
                family.setUpdateDate ( now );
                System.out.print ( family.getFamilyName () + "," + family.getFamilyBrithday () + "," + family.getRelationship () );
                customerInfoService.saveFamily ( family );

            }
        }


//		model.addAttribute("customerInfo", customerInfo);
        System.out.println ( "执行修改客户信息方法！" );
        addMessage ( redirectAttributes, "修改客户信息成功" );
        return "redirect:" + adminPath + "/customer/mycustomerList";
    }

    /**
     * @param @param  model
     * @param @param  customerId
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: customerAnalysisUpdate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping(value = "customerAnalysisUpdateForm")
    public String customerAnalysisUpdateForm(Model model, String customerId) {

        System.out.println ( customerId );

        User user = UserUtils.getUser ();
        String department = user.getOffice ().getName ();
        String userName = user.getName ();
        String alyPerson = department + "-" + userName;

        //获取客户分析内容
        List<CustomerAnalysis> customerAnalysisList = customerInfoService.findCusAnalysisByCustomerId ( customerId );

        model.addAttribute ( "customerId", customerId );
        model.addAttribute ( "alyPerson", alyPerson );
        model.addAttribute ( "customerAnalysisList", customerAnalysisList );
        return "modules/customer/customerAnalysisUpdateForm";

    }


    @RequestMapping(value = "customerAnalysisUpdate")
    public String customerAnalysisUpdate(HttpServletRequest request, CustomerAnalysis customerAnalysis, Model model, RedirectAttributes redirectAttributes) {

        String customerId = request.getParameter ( "customerId" );
        System.out.println ( "待修改客户的id：" + customerId );
        customerAnalysis.setCustomerId ( customerId );

        List<CustomerInfo> createByDate = customerInfoService.findCreateByDate ( customerId );
        String createId = createByDate.get ( 0 ).getCreateId ();
        Date createDate = createByDate.get ( 0 ).getCreateDate ();

        User user = UserUtils.getUser ();
        customerAnalysis.setCreateId ( createId );
        customerAnalysis.setUpdateId ( user.getId () );
        Date now = CustomerUtils.getNowDate ();
        customerAnalysis.setCreateDate ( createDate );
        customerAnalysis.setUpdateDate ( now );

        //通过客户id取到对应的家庭信息id
        List<CustomerAnalysis> cusAnalysisIds = customerInfoService.findCusAnalysisIdsByCusId ( customerId );

        String[] alyPersons = request.getParameterValues ( "alyPerson" );
        String[] alyContents = request.getParameterValues ( "alyContent" );
        String[] alyTimes = request.getParameterValues ( "alyTime" );

        int i = 0;
        if (cusAnalysisIds != null && cusAnalysisIds.size () > 0) {
            for (i = 0; i < cusAnalysisIds.size (); i++) {
                String cusAnalysisId = cusAnalysisIds.get ( i ).getId ();
                customerAnalysis.setId ( cusAnalysisId );
                customerAnalysis.setCustomerId ( customerId );
                customerAnalysis.setAlyPerson ( alyPersons[i] );
                customerAnalysis.setAlyContent ( alyContents[i] );
                customerAnalysis.setAlyTime ( alyTimes[i] );
                customerAnalysis.setCreateId ( createId );
                customerAnalysis.setUpdateId ( user.getId () );
                customerAnalysis.setCreateDate ( createDate );
                customerAnalysis.setUpdateDate ( now );
                customerInfoService.updateCustomerAnalysis ( customerAnalysis );
            }
            if (alyPersons.length > i) {
                for (int j = alyPersons.length - 1; i < j + 1; j--) {
                    String newCusAlyId = CustomerUtils.getUUID ();
                    customerAnalysis.setId ( newCusAlyId );
                    customerAnalysis.setCustomerId ( customerId );
                    customerAnalysis.setAlyPerson ( alyPersons[i] );
                    customerAnalysis.setAlyContent ( alyContents[i] );
                    customerAnalysis.setAlyTime ( alyTimes[i] );
                    customerAnalysis.setCreateId ( createId );
                    customerAnalysis.setUpdateId ( user.getId () );
                    customerAnalysis.setCreateDate ( createDate );
                    customerAnalysis.setUpdateDate ( now );
                    customerInfoService.saveCustomerAnalysis ( customerAnalysis );
                }
            }
        } else {
            for (int m = 0; m < alyPersons.length; m++) {
                String newCusAlyId = CustomerUtils.getUUID ();
                customerAnalysis.setId ( newCusAlyId );
                customerAnalysis.setCustomerId ( customerId );
                customerAnalysis.setAlyPerson ( alyPersons[m] );
                customerAnalysis.setAlyContent ( alyContents[m] );
                customerAnalysis.setAlyTime ( alyTimes[m] );
                customerAnalysis.setCreateId ( createId );
                customerAnalysis.setUpdateId ( user.getId () );
                customerAnalysis.setCreateDate ( createDate );
                customerAnalysis.setUpdateDate ( now );
                customerInfoService.saveCustomerAnalysis ( customerAnalysis );

            }
        }
        System.out.println ( "执行修改客户分析内容方法！" );
        addMessage ( redirectAttributes, "修改客户分析内容成功" );
        return "redirect:" + adminPath + "/customer/mycustomerList";
    }


    /**
     *
     * @Title: customerAnalysisForm
     * @Description: 添加客户分析页面
     * @param @param customerAnalysis
     * @param @param model
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
	/*
	@RequestMapping(value = "customerAnalysis")
	public String customerAnalysisForm(Model model,String customerId) {
		
		User user = UserUtils.getUser();
		String department = user.getOffice().getName();
		String userName = user.getName();
		String alyPerson = department+"-"+userName;
		System.out.println(alyPerson);
		
		System.out.println(customerId);
		model.addAttribute("alyPerson", alyPerson);
		model.addAttribute("customerId", customerId);
		return "modules/customer/customerAnalysisForm";
	}*/

    /**
     * @param @param  model
     * @param @param  customerId
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: customerAnalysisDetail
     * @Description: 客户分析    详情页面
     */
    @RequestMapping(value = "customerAnalysisDetail")
    public String customerAnalysisDetail(Model model, String customerId, String customerName) {

        List<CustomerAnalysis> customerAnalysisList = customerInfoService.findCusAnalysisByCustomerId ( customerId );
        model.addAttribute ( "customerAnalysisList", customerAnalysisList );

        model.addAttribute ( "customerId", customerId );
        model.addAttribute ( "customerName", customerName );
        return "modules/customer/customerAnalysisDetail";
    }

    /**
     * @param @param  request
     * @param @param  customerAnalysis
     * @param @param  model
     * @param @param  redirectAttributes
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: saveAnalysis
     * @Description: 保存客户分析内容
     */
    @RequestMapping(value = "customerAnalysisSave")
    public String saveAnalysis(HttpServletRequest request, CustomerAnalysis customerAnalysis, Model model, RedirectAttributes redirectAttributes) {

        User user = UserUtils.getUser ();
        Date now = CustomerUtils.getNowDate ();
        System.out.println ( now );

        String customerId = request.getParameter ( "customerId" );
        System.out.println ( customerId );

        String[] alyPersons = request.getParameterValues ( "alyPerson" );
        String[] alyContents = request.getParameterValues ( "alyContent" );
        String[] alyTimes = request.getParameterValues ( "alyTime" );

        for (int i = 0; i < alyPersons.length; i++) {
            String analysisId = CustomerUtils.getUUID ();
            customerAnalysis.setId ( analysisId );
            customerAnalysis.setCustomerId ( customerId );
            customerAnalysis.setAlyPerson ( alyPersons[i] );
            customerAnalysis.setAlyContent ( alyContents[i] );
            customerAnalysis.setAlyTime ( alyTimes[i] );
            customerAnalysis.setCreateBy ( user );
            customerAnalysis.setUpdateBy ( user );
            customerAnalysis.setCreateDate ( now );
            customerAnalysis.setUpdateDate ( now );
            customerInfoService.saveCustomerAnalysis ( customerAnalysis );
        }
        addMessage ( redirectAttributes, "保存客户分析内容成功" );
        return "redirect:" + adminPath + "/customer/customerList";
    }
	
	/*
	@RequestMapping(value = "mycustomerAnalysisList")
	public String mycustomerAnalysisList(CustomerAnalysis customerAnalysis,HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		//根据当前登陆用户id查找其对应的用户组
		String[] customerIds = customerInfoService.findCustomerGroupById(userId);
		for(int i=0;i<customerIds.length;i++){
			System.out.println(customerIds[i]);
		}
		return null;
	}*/


    @RequestMapping(value = "customerInfoDelete")
    public String customerInfoDelete(Model model, RedirectAttributes redirectAttributes, String customerId) {

        customerInfoService.deleteResumeByCustomerId ( customerId );
        customerInfoService.deleteFamilyByCustomerId ( customerId );
        customerInfoService.deleteCustomerInfo ( customerId );

        addMessage ( redirectAttributes, "删除客户信息成功" );
        return "redirect:" + adminPath + "/customer/mycustomerList";
    }

    @ResponseBody
    @RequestMapping("/allcustomerajax")
    public List<CustomerInfo> allcustomerList() {
        List<CustomerInfo> customerInfoList = customerInfoService.getCustomerInfoZtreeList ();
        return customerInfoList;
    }

    @RequestMapping(value = "customerZtree")
    public String customerZtree(CustomerInfo customerInfo, Model model) {
        model.addAttribute ( "customerInfo", customerInfo );
        return "modules/customer/customerZtree";
    }

    @ResponseBody
    @RequestMapping("/mycustomerajax")
    public List<CustomerInfo> mycustomerList() {
        User user = UserUtils.getUser ();
        List<CustomerInfo> customerInfoList = customerInfoService.getMyCustomerInfoZtreeList ( user.getId () );
        return customerInfoList;
    }

    @RequestMapping(value = "/mycustomerZtree")
    public String mycustomerZtree(CustomerInfo customerInfo, Model model) {
        model.addAttribute ( "customerInfo", customerInfo );
        return "modules/customer/mycustomerZtree";
    }

}
