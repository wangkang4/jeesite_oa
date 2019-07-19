/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oaproject.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.CostExcel.utils.ExcelUtil;
import com.thinkgem.jeesite.modules.customer.entity.CustomerInfo;
import com.thinkgem.jeesite.modules.customer.entity.PmCustomerZtree;
import com.thinkgem.jeesite.modules.customer.service.CustomerInfoService;
import com.thinkgem.jeesite.modules.customer.service.PmCustomerZtreeService;
import com.thinkgem.jeesite.modules.oaproject.entity.PmProjectDetailed;
import com.thinkgem.jeesite.modules.oaproject.entity.PmProjectMain;
import com.thinkgem.jeesite.modules.oaproject.service.PmProjectDetailedService;
import com.thinkgem.jeesite.modules.oaproject.service.PmProjectMainService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 单表生成Controller
 *
 * @author zhangbingbing
 * @version 2018-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/oaproject/pmProjectMain")
public class PmProjectMainController extends BaseController {

    @Autowired
    private PmProjectMainService pmProjectMainService;
    @Autowired
    private PmProjectDetailedService pmProjectDetailedService;
    @Autowired
    private CustomerInfoService customerInfoService;
    @Autowired
    private PmCustomerZtreeService pmCustomerZtreeService;

    @ModelAttribute
    public PmProjectMain get(@RequestParam(required = false) String id) {
        PmProjectMain entity = null;
        if (StringUtils.isNotBlank ( id )) {
            entity = pmProjectMainService.get ( id );
        }
        if (entity == null) {
            entity = new PmProjectMain ();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    public String list(PmProjectMain pmProjectMain, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PmProjectMain> page = pmProjectMainService.findPage ( new Page<PmProjectMain> ( request, response ), pmProjectMain );
        model.addAttribute ( "page", page );
        return "modules/oaproject/pmProjectMainList";
    }

    @RequestMapping(value = "form")
    public String form(PmProjectMain pmProjectMain, Model model) {
        List<PmCustomerZtree> pmCustomerZtreeList = pmCustomerZtreeService.findPmCustomerZtreeList ();
        List<PmProjectMain> documentList = null;
        if (pmProjectMain.getId () != null) {
            documentList = pmProjectMainService.getDocumentList ( pmProjectMain.getId () );
        }
        model.addAttribute ( "documentList", documentList );
        model.addAttribute ( "pmCustomerZtreeList", pmCustomerZtreeList );
        model.addAttribute ( "pmProjectMain", pmProjectMain );
        return "modules/oaproject/pmProjectMainForm";
    }

    @RequestMapping(value = "pmProjectMainLook")
    public String pmProjectMainLook(PmProjectMain pmProjectMain, Model model) {
        List<PmCustomerZtree> pmCustomerZtreeList = pmCustomerZtreeService.findPmCustomerZtreeList ();

        model.addAttribute ( "pmCustomerZtreeList", pmCustomerZtreeList );
        model.addAttribute ( "pmProjectMain", pmProjectMain );
        return "modules/oaproject/pmProjectMainForm";
    }

    /**
     * 跳转到项目团队页面
     *
     * @param pmProjectMain
     * @param model
     * @return
     */
    @RequestMapping(value = "people")
    public String people(PmProjectMain pmProjectMain, Model model) {
        model.addAttribute ( "pmProjectMain", pmProjectMain );
        return "modules/oaproject/people";
    }

    /**
     * 项目团队保存
     *
     * @param pmProjectMain
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "savepeople")
    public String savepeople(PmProjectMain pmProjectMain, Model model, RedirectAttributes redirectAttributes) {
        pmProjectMainService.delpeople ( pmProjectMain.getProjectId () );
        List<PmProjectMain> pmProjectMainList = new ArrayList<PmProjectMain> ();
        String manager[] = pmProjectMain.getManager ().getId ().split ( "," );
        for (int i = 0; i < manager.length; i++) {
            PmProjectMain pmProjectMainInformation = new PmProjectMain ();
            pmProjectMainInformation.setProjectId ( pmProjectMain.getProjectId () );
            pmProjectMainInformation.setId ( IdGen.uuid () );
            pmProjectMainInformation.setUserId ( manager[i] );
            pmProjectMainInformation.setRoleName ( "manager" );
            pmProjectMainInformation.setCreateBy ( UserUtils.getUser () );
            pmProjectMainInformation.setUpdateBy ( UserUtils.getUser () );
            pmProjectMainList.add ( pmProjectMainInformation );
        }
        String member[] = pmProjectMain.getMember ().getId ().split ( "," );
        for (int i = 0; i < member.length; i++) {
            PmProjectMain pmProjectMainInformation = new PmProjectMain ();
            pmProjectMainInformation.setProjectId ( pmProjectMain.getProjectId () );
            pmProjectMainInformation.setId ( IdGen.uuid () );
            pmProjectMainInformation.setUserId ( member[i] );
            pmProjectMainInformation.setRoleName ( "member" );
            pmProjectMainInformation.setCreateBy ( UserUtils.getUser () );
            pmProjectMainInformation.setUpdateBy ( UserUtils.getUser () );
            pmProjectMainList.add ( pmProjectMainInformation );
        }
        String administration[] = pmProjectMain.getAdministration ().getId ().split ( "," );
        for (int i = 0; i < administration.length; i++) {
            PmProjectMain pmProjectMainInformation = new PmProjectMain ();
            pmProjectMainInformation.setProjectId ( pmProjectMain.getProjectId () );
            pmProjectMainInformation.setId ( IdGen.uuid () );
            pmProjectMainInformation.setUserId ( administration[i] );
            pmProjectMainInformation.setRoleName ( "administration" );
            pmProjectMainInformation.setCreateBy ( UserUtils.getUser () );
            pmProjectMainInformation.setUpdateBy ( UserUtils.getUser () );
            pmProjectMainList.add ( pmProjectMainInformation );
        }
        String product[] = pmProjectMain.getProduct ().getId ().split ( "," );
        for (int i = 0; i < product.length; i++) {
            PmProjectMain pmProjectMainInformation = new PmProjectMain ();
            pmProjectMainInformation.setProjectId ( pmProjectMain.getProjectId () );
            pmProjectMainInformation.setId ( IdGen.uuid () );
            pmProjectMainInformation.setUserId ( product[i] );
            pmProjectMainInformation.setRoleName ( "product" );
            pmProjectMainInformation.setCreateBy ( UserUtils.getUser () );
            pmProjectMainInformation.setUpdateBy ( UserUtils.getUser () );
            pmProjectMainList.add ( pmProjectMainInformation );
        }
        String research[] = pmProjectMain.getResearch ().getId ().split ( "," );
        for (int i = 0; i < research.length; i++) {
            PmProjectMain pmProjectMainInformation = new PmProjectMain ();
            pmProjectMainInformation.setProjectId ( pmProjectMain.getProjectId () );
            pmProjectMainInformation.setId ( IdGen.uuid () );
            pmProjectMainInformation.setUserId ( research[i] );
            pmProjectMainInformation.setRoleName ( "research" );
            pmProjectMainInformation.setCreateBy ( UserUtils.getUser () );
            pmProjectMainInformation.setUpdateBy ( UserUtils.getUser () );
            pmProjectMainList.add ( pmProjectMainInformation );
        }
        String warranty[] = pmProjectMain.getWarranty ().getId ().split ( "," );
        for (int i = 0; i < warranty.length; i++) {
            PmProjectMain pmProjectMainInformation = new PmProjectMain ();
            pmProjectMainInformation.setProjectId ( pmProjectMain.getProjectId () );
            pmProjectMainInformation.setId ( IdGen.uuid () );
            pmProjectMainInformation.setUserId ( warranty[i] );
            pmProjectMainInformation.setRoleName ( "warranty" );
            pmProjectMainInformation.setCreateBy ( UserUtils.getUser () );
            pmProjectMainInformation.setUpdateBy ( UserUtils.getUser () );
            pmProjectMainList.add ( pmProjectMainInformation );
        }
		/*String business[] = pmProjectMain.getBusiness().getId().split(",");
		for(int i=0;i<business.length;i++){
			PmProjectMain pmProjectMainInformation = new PmProjectMain();
			pmProjectMainInformation.setProjectId(pmProjectMain.getProjectId());
			pmProjectMainInformation.setId(IdGen.uuid());
			pmProjectMainInformation.setUserId(business[i]);
			pmProjectMainInformation.setRoleName("business");
			pmProjectMainInformation.setCreateBy(UserUtils.getUser());
			pmProjectMainInformation.setUpdateBy(UserUtils.getUser());
			pmProjectMainList.add(pmProjectMainInformation);
		}*/
        String logistics[] = pmProjectMain.getLogistics ().getId ().split ( "," );
        for (int i = 0; i < logistics.length; i++) {
            PmProjectMain pmProjectMainInformation = new PmProjectMain ();
            pmProjectMainInformation.setProjectId ( pmProjectMain.getProjectId () );
            pmProjectMainInformation.setId ( IdGen.uuid () );
            pmProjectMainInformation.setUserId ( logistics[i] );
            pmProjectMainInformation.setRoleName ( "logistics" );
            pmProjectMainInformation.setCreateBy ( UserUtils.getUser () );
            pmProjectMainInformation.setUpdateBy ( UserUtils.getUser () );
            pmProjectMainList.add ( pmProjectMainInformation );
        }
        pmProjectMainService.savePeople ( pmProjectMainList );
        PmProjectMain projectMain = pmProjectMainService.getProjectMain ( pmProjectMain.getProjectId () );
        PmProjectDetailed projectDetailed = pmProjectDetailedService.getProjectDetailed ( pmProjectMain.getProjectId () );
        List<PmProjectMain> projectMainList = pmProjectMainService.findPmProjectMainList ();
        PmProjectDetailed pmProjectDetailed = new PmProjectDetailed ();
        if (projectDetailed != null) {
            pmProjectDetailed = pmProjectDetailedService.get ( projectDetailed.getId () );
        }
        List<CustomerInfo> customerInfoList = customerInfoService.getCustomerInfoList ();

        //查询项目关联表信息
        List<PmProjectMain> projectRelationList = pmProjectMainService.getProjectRelationList ( pmProjectMain.getProjectId () );
        //查询竞争对手关联表信息
        List<PmProjectMain> projectOpponentList = pmProjectMainService.getProjectOpponentList ( pmProjectMain.getProjectId () );
        //查询项目客户信息关联表
        List<PmProjectMain> projectCustomerList = pmProjectMainService.getProjectCustomerList ( pmProjectMain.getProjectId () );
        //查询项目合作单位信息关联表
        List<PmProjectMain> projectCooperationList = pmProjectMainService.getProjectCooperationList ( pmProjectMain.getProjectId () );

        model.addAttribute ( "projectCooperationList", projectCooperationList );
        model.addAttribute ( "projectCustomerList", projectCustomerList );
        model.addAttribute ( "projectOpponentList", projectOpponentList );
        model.addAttribute ( "projectRelationList", projectRelationList );
        model.addAttribute ( "customerInfoList", customerInfoList );
        model.addAttribute ( "projectMainList", projectMainList );
        model.addAttribute ( "pmProjectDetailed", pmProjectDetailed );
        model.addAttribute ( "projectMain", projectMain );
        return "modules/oaproject/pmProjectDetailedForm";
    }

    @RequestMapping(value = "save")
    public String save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response, PmProjectMain pmProjectMain, Model model, RedirectAttributes redirectAttributes) throws IOException {
        if (!beanValidator ( model, pmProjectMain )) {
            return form ( pmProjectMain, model );
        }
        String projectId = "THD";
        //Office office = officeService.getOffice(pmProjectMain.getProjectAddress().getId());
        if ("0".equals ( pmProjectMain.getProjectAddress () )) {
            projectId = projectId + "01";
        } else if ("1".equals ( pmProjectMain.getProjectAddress () )) {
            projectId = projectId + "02";
        } else if ("2".equals ( pmProjectMain.getProjectAddress () )) {
            projectId = projectId + "03";
        } else if ("3".equals ( pmProjectMain.getProjectAddress () )) {
            projectId = projectId + "04";
        } else if ("4".equals ( pmProjectMain.getProjectAddress () )) {
            projectId = projectId + "05";
        } else if ("5".equals ( pmProjectMain.getProjectAddress () )) {
            projectId = projectId + "06";
        } else if ("6".equals ( pmProjectMain.getProjectAddress () )) {
            projectId = projectId + "07";
        } else if ("7".equals ( pmProjectMain.getProjectAddress () )) {
            projectId = projectId + "08";
        } else if ("8".equals ( pmProjectMain.getProjectAddress () )) {
            projectId = projectId + "09";
        } else if ("9".equals ( pmProjectMain.getProjectAddress () )) {
            projectId = projectId + "10";
        } else {
            projectId = projectId + "00";
        }

        if ("教育行业".equals ( pmProjectMain.getProjectIndustry () )) {
            projectId = projectId + "E";
        } else if ("矿山大企业".equals ( pmProjectMain.getProjectIndustry () )) {
            projectId = projectId + "M";
        } else if ("金融行业".equals ( pmProjectMain.getProjectIndustry () )) {
            projectId = projectId + "B";
        } else if ("司法行业".equals ( pmProjectMain.getProjectIndustry () )) {
            projectId = projectId + "P";
        } else if ("其他行业".equals ( pmProjectMain.getProjectIndustry () )) {
            projectId = projectId + "O";
        }

        String year = String.valueOf ( Calendar.getInstance ().get ( Calendar.YEAR ) - 2000 );
        projectId = projectId + year;

        int No = pmProjectMainService.getNum ( Calendar.getInstance ().get ( Calendar.YEAR ) ) + 1;
        if (No < 10) {
            projectId = projectId + "00" + No;
        } else if (No < 100) {
            projectId = projectId + "0" + No;
        } else {
            projectId = projectId + No;
        }
        pmProjectMain.setProjectId ( projectId );
        pmProjectMain.setExamine ( "待审核" );

        pmProjectMainService.save ( pmProjectMain );
        addMessage ( redirectAttributes, "保存单表成功" );

        Iterator<String> fileNames = multipartRequest.getFileNames ();
        while (fileNames.hasNext ()) {
            //把fileNames集合中的值打出来  
            String fileName = fileNames.next ();
            /*
             * request.getFiles(fileName)方法即通过fileName这个Key, 得到对应的文件
             * 集合列表. 只是在这个Map中, 文件被包装成MultipartFile类型
             */
            List<MultipartFile> fileList = multipartRequest.getFiles ( fileName );
            if (fileList.size () > 0) {
                //遍历文件列表  
                Iterator<MultipartFile> fileIte = fileList.iterator ();
                while (fileIte.hasNext ()) {
                    //获得每一个文件  
                    MultipartFile multipartFile = fileIte.next ();
                    //获得原文件名  
                    String originalFilename = multipartFile.getOriginalFilename ();
                    System.out.println ( "originalFilename: " + originalFilename );
                    // 重命名文件名
                    String rename = ExcelUtil.rename ( multipartFile.getOriginalFilename () );
                    //设置保存路径.   
                    //String path = Global.getConfig("uploadPath") ;
                    String path = "F:/upload/";
                    //检查该路径对应的目录是否存在. 如果不存在则创建目录  
                    File dir = new File ( path );
                    if (!dir.exists ()) {
                        dir.mkdirs ();
                    }
                    String filePath = path + rename;
                    //System.out.println("filePath: "+filePath);  
                    //保存文件  
                    File dest = new File ( filePath );
                    if (!(dest.exists ())) {
                        PmProjectMain projectDocument = new PmProjectMain ();
                        projectDocument.setId ( IdGen.uuid () );
                        projectDocument.setProjectId ( pmProjectMain.getId () );
                        projectDocument.setDocumentName ( originalFilename );
                        projectDocument.setDocumentAddress ( filePath );
                        projectDocument.setProjectStage ( pmProjectMain.getProjectStage () );
                        projectDocument.setCreateBy ( pmProjectMain.getCreateBy () );
                        projectDocument.setCreateDate ( pmProjectMain.getCreateDate () );
                        projectDocument.setUpdateBy ( pmProjectMain.getUpdateBy () );
                        projectDocument.setUpdateDate ( pmProjectMain.getUpdateDate () );
                        pmProjectMainService.saveDocument ( projectDocument );
                        /*
                         * MultipartFile提供了void transferTo(File dest)方法,
                         * 将获取到的文件以File形式传输至指定路径.
                         */
                        multipartFile.transferTo ( dest );
                        /*
                         * 如果需对文件进行其他操作, MultipartFile也提供了
                         * InputStream getInputStream()方法获取文件的输入流
                         *
                         * 例如下面的语句即为通过
                         * org.apache.commons.io.FileUtils提供的
                         * void copyInputStreamToFile(InputStream source, File destination)
                         * 方法, 获取输入流后将其保存至指定路径
                         */
                        //FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), dest);  
                    }
                    //MultipartFile也提供了其他一些方法, 用来获取文件的部分属性  
                    //获取文件contentType  
                    //String contentType=multipartFile.getContentType();  
                    //System.out.println("contentType: "+contentType);  
                    //String name=multipartFile.getName();  
                    //获取文件大小, 单位为字节  
                    //long size=multipartFile.getSize();  
                }
            }
        }
        List<PmProjectMain> projectMainTeam = pmProjectMainService.getProjectMainTeam ( pmProjectMain.getId () );
        if (projectMainTeam.size () > 0) {
            String managerId = "";
            String managername = "";
            String memberId = "";
            String membername = "";
            String administrationId = "";
            String administrationname = "";
            String productId = "";
            String productname = "";
            String researchId = "";
            String researchname = "";
            String warrantyId = "";
            String warrantyname = "";
            String businessId = "";
            String businessname = "";
            String logisticsId = "";
            String logisticsname = "";
            for (int i = 0; i < projectMainTeam.size (); i++) {
                if (projectMainTeam.get ( i ).getRoleName ().equals ( "manager" )) {
                    managerId = managerId + projectMainTeam.get ( i ).getUserId () + ",";
                    managername = managername + projectMainTeam.get ( i ).getUseraName () + ",";
                }
                if (projectMainTeam.get ( i ).getRoleName ().equals ( "member" )) {
                    memberId = memberId + projectMainTeam.get ( i ).getUserId () + ",";
                    membername = membername + projectMainTeam.get ( i ).getUseraName () + ",";
                }
                if (projectMainTeam.get ( i ).getRoleName ().equals ( "administration" )) {
                    administrationId = administrationId + projectMainTeam.get ( i ).getUserId () + ",";
                    administrationname = administrationname + projectMainTeam.get ( i ).getUseraName () + ",";
                }
                if (projectMainTeam.get ( i ).getRoleName ().equals ( "product" )) {
                    productId = productId + projectMainTeam.get ( i ).getUserId () + ",";
                    productname = productname + projectMainTeam.get ( i ).getUseraName () + ",";
                }
                if (projectMainTeam.get ( i ).getRoleName ().equals ( "research" )) {
                    researchId = researchId + projectMainTeam.get ( i ).getUserId () + ",";
                    researchname = researchname + projectMainTeam.get ( i ).getUseraName () + ",";
                }
                if (projectMainTeam.get ( i ).getRoleName ().equals ( "warranty" )) {
                    warrantyId = warrantyId + projectMainTeam.get ( i ).getUserId () + ",";
                    warrantyname = warrantyname + projectMainTeam.get ( i ).getUseraName () + ",";
                }
                if (projectMainTeam.get ( i ).getRoleName ().equals ( "business" )) {
                    businessId = businessId + projectMainTeam.get ( i ).getUserId () + ",";
                    businessname = businessname + projectMainTeam.get ( i ).getUseraName () + ",";
                }
                if (projectMainTeam.get ( i ).getRoleName ().equals ( "logistics" )) {
                    logisticsId = businessId + projectMainTeam.get ( i ).getUserId () + ",";
                    logisticsname = businessname + projectMainTeam.get ( i ).getUseraName () + ",";
                }
            }
            if (managerId.length () > 0) {
                managerId = managerId.substring ( 0, managerId.length () - 1 );
                managername = managername.substring ( 0, managername.length () - 1 );
                User manager = new User ();
                manager.setId ( managerId );
                manager.setName ( managername );
                pmProjectMain.setManager ( manager );
            }
            if (memberId.length () > 0) {
                memberId = memberId.substring ( 0, memberId.length () - 1 );
                membername = membername.substring ( 0, membername.length () - 1 );
                User member = new User ();
                member.setId ( memberId );
                member.setName ( membername );
                pmProjectMain.setMember ( member );
            }
            if (administrationId.length () > 0) {
                administrationId = administrationId.substring ( 0, administrationId.length () - 1 );
                administrationname = administrationname.substring ( 0, administrationname.length () - 1 );
                User administration = new User ();
                administration.setId ( administrationId );
                administration.setName ( administrationname );
                pmProjectMain.setAdministration ( administration );
            }
            if (productId.length () > 0) {
                productId = productId.substring ( 0, productId.length () - 1 );
                productname = productname.substring ( 0, productname.length () - 1 );
                User product = new User ();
                product.setId ( productId );
                product.setName ( productname );
                pmProjectMain.setProduct ( product );
            }
            if (researchId.length () > 0) {
                researchId = researchId.substring ( 0, researchId.length () - 1 );
                researchname = researchname.substring ( 0, researchname.length () - 1 );
                User research = new User ();
                research.setId ( researchId );
                research.setName ( researchname );
                pmProjectMain.setResearch ( research );
            }

            if (warrantyId.length () > 0) {
                warrantyId = warrantyId.substring ( 0, warrantyId.length () - 1 );
                warrantyname = warrantyname.substring ( 0, warrantyname.length () - 1 );
                User warranty = new User ();
                warranty.setId ( warrantyId );
                warranty.setName ( warrantyname );
                pmProjectMain.setWarranty ( warranty );
            }

            if (businessId.length () > 0) {
                businessId = businessId.substring ( 0, businessId.length () - 1 );
                businessname = businessname.substring ( 0, businessname.length () - 1 );
                User business = new User ();
                business.setId ( businessId );
                business.setName ( businessname );
                pmProjectMain.setBusiness ( business );
            }

            if (logisticsId.length () > 0) {
                logisticsId = logisticsId.substring ( 0, logisticsId.length () - 1 );
                logisticsname = logisticsname.substring ( 0, logisticsname.length () - 1 );
                User logistics = new User ();
                logistics.setId ( logisticsId );
                logistics.setName ( logisticsname );
                pmProjectMain.setLogistics ( logistics );
            }
        }
        model.addAttribute ( "pmProjectMain", pmProjectMain );
        return "modules/oaproject/people";
    }

    @RequestMapping(value = "delete")
    public String delete(PmProjectMain pmProjectMain, RedirectAttributes redirectAttributes) {
        pmProjectMainService.delete ( pmProjectMain );
        addMessage ( redirectAttributes, "删除单表成功" );
        return "redirect:" + Global.getAdminPath () + "/oaproject/pmProjectMain/?repage";
    }

    @ResponseBody
    @RequestMapping(value = "getProjectMain")
    public Map<String, List<PmProjectMain>> getProjectMain() {
        Map<String, List<PmProjectMain>> map = new HashMap<String, List<PmProjectMain>> ();
        List<PmProjectMain> projectMainList = pmProjectMainService.findPmProjectMainList ();
        map.put ( "1", projectMainList );
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "getCustomerList")
    public Map<String, List<PmProjectMain>> getCustomerList() {
        Map<String, List<PmProjectMain>> map = new HashMap<String, List<PmProjectMain>> ();
        List<PmProjectMain> customerList = pmProjectMainService.getCustomerList ();
        map.put ( "1", customerList );
        return map;
    }

    @RequestMapping(value = "uploadjsp")
    public String uploadjsp(PmProjectMain pmProjectMain, Model model) {
        model.addAttribute ( "pmProjectMain", pmProjectMain );
        return "modules/oapms/index";
    }

    @RequestMapping(value = "projectDocumentDownload")
    public String projectDocumentDownload(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        PmProjectMain projectMain = pmProjectMainService.getDocument ( id );
        File file = new File ( projectMain.getDocumentAddress () );
        // 清空response
        response.reset ();
        // 设置response的Header
        response.addHeader ( "Content-Disposition",
                "attachment;filename=" + new String ( projectMain.getDocumentName ().getBytes ( "gbk" ), "iso-8859-1" ) ); // 转码之后下载的文件不会出现中文乱码
        response.addHeader ( "Content-Length", "" + file.length () );

        try {

            // 以流的形式下载文件
            InputStream fis = new BufferedInputStream ( new FileInputStream ( projectMain.getDocumentAddress () ) );
            byte[] buffer = new byte[fis.available ()];
            fis.read ( buffer );
            fis.close ();

            OutputStream toClient = new BufferedOutputStream ( response.getOutputStream () );
            toClient.write ( buffer );
            toClient.flush ();
            toClient.close ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "projectDocumentDel")
    public Map<String, String> projectDocumentDel(String id, HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<String, String> ();
        try {
            pmProjectMainService.delProjectDocument ( id );
            map.put ( "1", "删除成功！" );
        } catch (Exception e) {
            map.put ( "2", "删除失败！" );
        }
        return map;
    }

    @RequestMapping(value = "examination")
    public String examination(PmProjectMain pmProjectMain, Model model) {
        PmCustomerZtree pmCustomerZtree = pmCustomerZtreeService.findPmCustomerZtree ( pmProjectMain.getId () );
        List<PmProjectMain> documentList = pmProjectMainService.getDocumentList ( pmProjectMain.getId () );
        List<PmProjectMain> projectMainTeam = pmProjectMainService.getProjectMainTeam ( pmProjectMain.getId () );
        //查询项目关联表信息
        List<PmProjectMain> projectRelationList = pmProjectMainService.getProjectRelationList ( pmProjectMain.getId () );
        //查询竞争对手关联表信息
        List<PmProjectMain> projectOpponentList = pmProjectMainService.getProjectOpponentList ( pmProjectMain.getId () );
        //查询项目客户信息关联表
        List<PmProjectMain> projectCustomerList = pmProjectMainService.getProjectCustomerList ( pmProjectMain.getId () );
        //查询项目合作单位信息关联表
        List<PmProjectMain> projectCooperationList = pmProjectMainService.getProjectCooperationList ( pmProjectMain.getId () );
        PmProjectDetailed projectDetailed = pmProjectDetailedService.getProjectDetailed ( pmProjectMain.getId () );

        model.addAttribute ( "projectDetailed", projectDetailed );
        model.addAttribute ( "projectOpponentList", projectOpponentList );
        model.addAttribute ( "projectCustomerList", projectCustomerList );
        model.addAttribute ( "projectCooperationList", projectCooperationList );
        model.addAttribute ( "projectRelationList", projectRelationList );
        model.addAttribute ( "projectMainTeam", projectMainTeam );
        model.addAttribute ( "documentList", documentList );
        model.addAttribute ( "pmCustomerZtree", pmCustomerZtree );
        model.addAttribute ( "pmProjectMain", pmProjectMain );
        return "modules/oaproject/pmProjectMainExamination";
    }

    @RequestMapping("saveExamine")
    public String saveExamine(PmProjectMain pmProjectMain, Model model) {
        pmProjectMainService.saveExamine ( pmProjectMain );
        return "redirect:" + Global.getAdminPath () + "/oaproject/pmProjectMain";
    }

    @RequestMapping(value = "look")
    public String look(PmProjectMain pmProjectMain, Model model) {
        PmCustomerZtree pmCustomerZtree = pmCustomerZtreeService.findPmCustomerZtree ( pmProjectMain.getId () );
        List<PmProjectMain> documentList = pmProjectMainService.getDocumentList ( pmProjectMain.getId () );
        List<PmProjectMain> projectMainTeam = pmProjectMainService.getProjectMainTeam ( pmProjectMain.getId () );
        //查询项目关联表信息
        List<PmProjectMain> projectRelationList = pmProjectMainService.getProjectRelationList ( pmProjectMain.getId () );
        //查询竞争对手关联表信息
        List<PmProjectMain> projectOpponentList = pmProjectMainService.getProjectOpponentList ( pmProjectMain.getId () );
        //查询项目客户信息关联表
        List<PmProjectMain> projectCustomerList = pmProjectMainService.getProjectCustomerList ( pmProjectMain.getId () );
        //查询项目合作单位信息关联表
        List<PmProjectMain> projectCooperationList = pmProjectMainService.getProjectCooperationList ( pmProjectMain.getId () );
        PmProjectDetailed projectDetailed = pmProjectDetailedService.getProjectDetailed ( pmProjectMain.getId () );

        model.addAttribute ( "projectDetailed", projectDetailed );
        model.addAttribute ( "projectOpponentList", projectOpponentList );
        model.addAttribute ( "projectCustomerList", projectCustomerList );
        model.addAttribute ( "projectCooperationList", projectCooperationList );
        model.addAttribute ( "projectRelationList", projectRelationList );
        model.addAttribute ( "projectMainTeam", projectMainTeam );
        model.addAttribute ( "documentList", documentList );
        model.addAttribute ( "pmCustomerZtree", pmCustomerZtree );
        model.addAttribute ( "pmProjectMain", pmProjectMain );
        return "modules/oaproject/look";
    }

}