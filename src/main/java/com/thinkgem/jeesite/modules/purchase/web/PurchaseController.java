package com.thinkgem.jeesite.modules.purchase.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.purchase.entity.Purchase;
import com.thinkgem.jeesite.modules.purchase.service.PurchaseService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * OA系统采购申请流程
 *
 * @author dongxueyong
 */
@Controller
@RequestMapping(value = "${adminPath}/tb/purchase")
public class PurchaseController extends BaseController {

    /**
     * 依赖注入service层属性
     */
    @Autowired
    private PurchaseService purchaseService;

    /**
     * 最先执行，通过id获取对象信息
     *
     * @param id
     * @return
     */
    @ModelAttribute
    public Purchase get(String id) {
        Purchase purchase = null;
        if (StringUtils.isNotBlank ( id )) {
            purchase = purchaseService.get ( id );
        }
        if (purchase == null) {
            purchase = new Purchase ();
        }
        return purchase;
    }

    /**
     * 表单页面
     *
     * @param model
     * @param purchase
     * @return 解析视图至add.jsp页面
     */
    @RequestMapping(value = "add")
    public String add(Model model, Purchase purchase) {
        purchase.setUser ( UserUtils.getUser () );
        Date date = new Date ();
        purchase.setApplyDate ( date );
        purchase.setOffice ( UserUtils.getUser ().getOffice () );
        model.addAttribute ( "purchase", purchase );
        return "modules/purchase/add";
    }

    /**
     * 添加表单内容到数据库
     *
     * @param purchase
     * @param file
     * @param request
     * @return 重定向至list方法
     * @throws ParseException
     */
    @RequestMapping(value = "toAdd")
    public String toAdd(Purchase purchase, MultipartFile file, HttpServletRequest request) throws ParseException {
        /*上传文件*/
			/*String fileName = file.getOriginalFilename();
			if(StringUtils.isNoneBlank(fileName)){
				String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
				String address = IdGen.uuid()+suffix;
				FileOutputStream fos = null;
				try {
					byte[] fileData = file.getBytes();
					fos = new FileOutputStream(Global.getConfig("purchase")+address);
					fos.write(fileData);
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				purchase.setApplyAddress(address);
			}*/

        purchase.setUser ( UserUtils.getUser () );
        purchase.setOffice ( UserUtils.getUser ().getOffice () );
        purchase.setProneText ( null );
        purchase.setPrtwoText ( null );
        purchase.setPrthreeText ( null );
        purchase.setPrfourText ( null );
        purchase.setPrfiveText ( null );
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd" );
        purchase.setApplyDate ( sdf.parse ( sdf.format ( new Date () ) ) );
        purchaseService.save ( purchase );
        return "redirect:" + adminPath + "/tb/purchase/list";
    }

    /**
     * 列表页面
     *
     * @param model
     * @param purchase
     * @param act
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "list")
    public String list(Model model, Purchase purchase, Act act, HttpServletRequest request, HttpServletResponse response) {
        purchase.setUser ( UserUtils.getUser () );
        Page<Purchase> page = purchaseService.findPages ( new Page<Purchase> ( request, response ), purchase );
        model.addAttribute ( "page", page );
        return "modules/purchase/list";
    }

    /**
     * 行政人员查看所属区域申请列表
     *
     * @param purchase
     * @param act
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "list2")
    public String list2(Purchase purchase, Act act, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<Purchase> page = purchaseService.findPage2 ( new Page<Purchase> ( request, response ), purchase );
        model.addAttribute ( "page", page );
        return "modules/purchase/list2";
    }

    /**
     * 财务人员查看员工申请列表
     *
     * @param purchase
     * @param act
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "list4")
    public String list4(Purchase purchase, Act act, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<Purchase> page = purchaseService.findPage4 ( new Page<Purchase> ( request, response ), purchase );
        model.addAttribute ( "page", page );
        return "modules/purchase/list4";
    }

    /**
     * 审核详情页面
     *
     * @param model
     * @param purchase
     * @return
     */
    @RequestMapping(value = "view")
    public String view(Model model, Purchase purchase) {
        String view = "view";
        if (StringUtils.isNotBlank ( purchase.getId () )) {
            //获取环节ID
            String taskDefKey = purchase.getAct ().getTaskDefKey ();
            if (purchase.getAct ().isFinishTask ()) {
                view = "view";
            } else if ("userTask1".equals ( taskDefKey )
                    || "userTask2".equals ( taskDefKey )
                    || "userTask3".equals ( taskDefKey )) {
                view = "audit";
            } else if ("modifyTask".equals ( taskDefKey )) {
                view = "add";
            }
        }
        model.addAttribute ( "purchase", purchase );
        return "modules/purchase/" + view;
    }

    /**
     * 处理流程
     *
     * @param purchase
     * @param model
     * @return
     */
    @RequestMapping(value = "saveAudit")
    public String saveAudit(Purchase purchase, Model model) {
        purchaseService.auditSave ( purchase );
        //return "redirect:" + adminPath + "/act/task/todo?click";
        return "redirect:" + adminPath + "/act/task/todo";
    }

    /**
     * 销毁流程
     *
     * @param purchase
     * @return
     */
    @RequestMapping(value = "back")
    public String back(Purchase purchase) {
        purchaseService.deletePurchase ( purchase.getProcInsId () );
        purchaseService.deleteTask ( purchase.getProcInsId () );
        return "redirect:" + adminPath + "/tb/purchase/list";
    }

    /**
     * 上传文件功能
     *
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename ();
        String address = "";
        if (StringUtils.isNoneBlank ( fileName )) {
            String suffix = fileName.substring ( fileName.lastIndexOf ( "." ) );//后缀名
            address = IdGen.uuid () + suffix;
            FileOutputStream fos = null;
            try {
                byte[] fileData = file.getBytes ();
                fos = new FileOutputStream ( Global.getConfig ( "purchase" ) + address );
                fos.write ( fileData );
                fos.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        return address;
    }

    /**
     * 文件下载
     *
     * @param purchase
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("download")
    public String download(Purchase purchase, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String downloadPrefix = Global.getConfig ( "purchase" );
        String url = downloadPrefix + purchase.getApplyAddress ();
        String[] str = purchase.getApplyAddress ().split ( "\\." );
        purchase.setApplyAddress ( purchase.getpName () + "." + str[1] );
        File file = new File ( url );
        // 清空response
        response.reset ();
        // 设置response的Header
        response.addHeader ( "Content-Disposition",
                "attachment;filename=" + new String ( purchase.getApplyAddress ().getBytes ( "gbk" ), "iso-8859-1" ) ); // 转码之后下载的文件不会出现中文乱码
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

}


















