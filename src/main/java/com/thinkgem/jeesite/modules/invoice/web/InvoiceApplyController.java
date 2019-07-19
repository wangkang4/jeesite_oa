package com.thinkgem.jeesite.modules.invoice.web;


import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.invoice.entity.InvoiceApply;
import com.thinkgem.jeesite.modules.invoice.service.InvoiceApplyService;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping(value = "${adminPath}/tb/invoiceApply")
public class InvoiceApplyController extends BaseController {

    @Autowired
    private InvoiceApplyService invoiceApplyService;

    @ModelAttribute
    public InvoiceApply get(String id) {
        InvoiceApply invoiceApply = null;
        if (StringUtils.isNotBlank ( id )) {
            invoiceApply = invoiceApplyService.get ( id );
        }
        if (invoiceApply == null) {
            invoiceApply = new InvoiceApply ();
        }
        return invoiceApply;
    }

    /**
     * 表单页面
     *
     * @param model
     * @param invoiceApply
     * @return
     */
    @RequestMapping(value = "add")
    public String add(Model model, InvoiceApply invoiceApply, HttpServletRequest request, HttpServletResponse response) {

        invoiceApply.setUser ( UserUtils.getUser () );
        model.addAttribute ( "invoice", invoiceApply );
        return "modules/tbInvoiceApply/add";
    }

    /**
     * 提交表单数据
     *
     * @param model
     * @param invoiceApply
     * @param redirectAttributes
     * @param request
     * @return
     */
    @RequestMapping(value = "toAdd")
    public String toAdd(@RequestParam("file") MultipartFile[] file, InvoiceApply invoiceApply, RedirectAttributes redirectAttributes) {

        invoiceApply.setOneText ( null );
        invoiceApply.setProneText ( null );
        invoiceApply.setPrtwoText ( null );
        invoiceApply.setUser ( UserUtils.getUser () );
        invoiceApplyService.save ( invoiceApply );
        return "redirect:" + adminPath + "/tb/invoiceApply/list";
    }


    @ResponseBody
    @RequestMapping("upload")
    public String upload(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) {
        String addressName = "";
        for (int i = 0; i < file.length; i++) {
            String address = null;
            if (request instanceof MultipartHttpServletRequest) {
                String fileName = file[i].getOriginalFilename ();
                if (StringUtils.isNoneBlank ( fileName )) {
                    String suffix = fileName.substring ( fileName.lastIndexOf ( "." ) );//后缀名
                    address = IdGen.uuid () + suffix;
                    addressName += address + "-";
                    FileOutputStream fos = null;
                    try {
                        byte[] fileData = file[i].getBytes ();
                        fos = new FileOutputStream ( Global.getConfig ( "invoiceApply" ) + address );
                        fos.write ( fileData );
                        fos.close ();
                    } catch (IOException e) {
                        e.printStackTrace ();
                    }

                }
            }
        }
        return addressName;
    }

    /**
     * 列表页面
     *
     * @param model
     * @param invoiceApply
     * @param act
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "list")
    public String list(Model model, InvoiceApply invoiceApply, Act act, HttpServletRequest request, HttpServletResponse response) {
        invoiceApply.setUser ( UserUtils.getUser () );
        Page<InvoiceApply> page = invoiceApplyService.findPages ( new Page<InvoiceApply> ( request, response ), invoiceApply );
        model.addAttribute ( "page", page );
        return "modules/tbInvoiceApply/list";
    }

    /**
     * 行政人员查看所属区域申请列表
     *
     * @param invoiceApply
     * @param act
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "list3")
    public String list3(InvoiceApply invoiceApply, Act act, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<InvoiceApply> page = invoiceApplyService.findPage3 ( new Page<InvoiceApply> ( request, response ), invoiceApply );
        model.addAttribute ( "page", page );
        return "modules/tbInvoiceApply/list3";
    }

    /**
     * 详情页面
     *
     * @param model
     * @param invoiceApply
     * @param invoiceSpecifi
     * @return
     */
    @RequestMapping(value = "view")
    public String view(Model model, InvoiceApply invoiceApply) {
        String view = "view";
        if (StringUtils.isNotBlank ( invoiceApply.getId () )) {
            // 获取环节ID
            String taskDefKey = invoiceApply.getAct ().getTaskDefKey ();
            if (invoiceApply.getAct ().isFinishTask ()) {
                view = "view";
            } else if ("userTask1".equals ( taskDefKey )) {
                view = "audit";
            } else if ("userTask2".equals ( taskDefKey )) {
                view = "audit";
            } else if ("userTask3".equals ( taskDefKey )) {
                view = "audit";
            } else if ("modifyTask".equals ( taskDefKey )) {
                view = "add";
            }
        }
        model.addAttribute ( "invoice", invoiceApply );
        return "modules/tbInvoiceApply/" + view;
    }

    @RequestMapping(value = "saveAudit")
    public String saveAudit(InvoiceApply invoiceApply, Model model) {
        invoiceApplyService.auditSave ( invoiceApply );
        //return "redirect:" + adminPath + "/act/task/todo?click";
        return "redirect:" + adminPath + "/act/task/todo";
    }

    //文件下载（打成压缩包下载）
    @RequestMapping("download")
    public String download(InvoiceApply invoiceApply, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String downloadPrefix = Global.getConfig ( "invoiceApply" );
        String[] strName = invoiceApply.getApplyAddress ().split ( "\\-" );
        List<File> srcFiles = new ArrayList<File> ();
        for (int i = 0; i < strName.length; i++) {
            String url = downloadPrefix + strName[i];
            srcFiles.add ( new File ( url ) );
        }
        String zipUrl = downloadPrefix + invoiceApply.getTaxName () + ".zip";
        invoiceApply.setApplyAddress ( invoiceApply.getTaxName () + ".zip" );
        File zipFile = new File ( zipUrl );
        //调用压缩方法
        zipFiles ( srcFiles, zipFile );
        // 清空response
        response.reset ();
        // 设置response的Header
        response.addHeader ( "Content-Disposition",
                "attachment;filename=" + new String ( invoiceApply.getApplyAddress ().getBytes ( "gbk" ), "iso-8859-1" ) ); // 转码之后下载的文件不会出现中文乱码
        response.addHeader ( "Content-Length", "" + zipFile.length () );
        try {
            // 以流的形式下载文件
            InputStream is = new BufferedInputStream ( new FileInputStream ( zipUrl ) );
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

    //将文件压缩成zip
    public static void zipFiles(List<File> srcFiles, File zipFile) {
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists ()) {
            try {
                zipFile.createNewFile ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;

        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream ( zipFile );
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream ( fileOutputStream );
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件集合
            for (int i = 0; i < srcFiles.size (); i++) {
                // 将源文件集合中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream ( srcFiles.get ( i ) );
                // 实例化 ZipEntry 对象，源文件数集合的当前文件
                zipEntry = new ZipEntry ( srcFiles.get ( i ).getName () );
                zipOutputStream.putNextEntry ( zipEntry );
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read ( buffer )) > 0) {
                    zipOutputStream.write ( buffer, 0, len );
                }
            }
            zipOutputStream.closeEntry ();
            zipOutputStream.close ();
            fileInputStream.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }

    @RequestMapping(value = "list2")
    public String list2(Model model, HttpServletRequest request, HttpServletResponse response, InvoiceApply invoiceApply) {
        invoiceApply.setUser ( UserUtils.getUser () );
        Page<InvoiceApply> page = invoiceApplyService.findPages2 ( new Page<InvoiceApply> ( request, response ), invoiceApply );
        model.addAttribute ( "page", page );
        return "modules/tbInvoiceApply/list2";
    }

    @RequestMapping(value = "back")
    public String back(InvoiceApply invoiceApply) {
        invoiceApplyService.deleteInvoiceApply ( invoiceApply.getProcInsId () );
        invoiceApplyService.deleteTask ( invoiceApply.getProcInsId () );
        return "redirect:" + adminPath + "/tb/invoiceApply/list";
    }
}























