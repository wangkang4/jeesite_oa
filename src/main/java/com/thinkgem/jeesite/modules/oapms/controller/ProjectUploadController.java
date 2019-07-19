package com.thinkgem.jeesite.modules.oapms.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.CostExcel.utils.ExcelUtil;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectDocument;
import com.thinkgem.jeesite.modules.oapms.services.ProjectUploadService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping(value = "${adminPath}/project/upload")
public class ProjectUploadController extends BaseController {

    @Autowired
    private ProjectUploadService projectUploadService;

    /**
     * 描述 : <事先就并不知道确切的上传文件数目，比如FancyUpload这样的多附件上传组件>. <br>
     * <p>
     *
     * @param model
     * @param multipartRequest
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/upload2")
    public String handleImport(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
            throws IOException {
        // String uploadHome =
        // request.getSession().getServletContext().getRealPath("/upload");
//		String uploadHome = Global.getConfig("uploadPath") + "pc/";
        String uploadHome = Global.getConfig ( "windowsUploadPath" );

        List<PmsProjectDocument> list = new ArrayList<PmsProjectDocument> ();
        if (multipartRequest != null) {
            // 上传文件，并保存路径到数据库
            Iterator<String> iterator = multipartRequest.getFileNames ();
            while (iterator.hasNext ()) {
                // 一次遍历所有文件
                MultipartFile multifile = multipartRequest.getFile ( (String) iterator.next () );
                if (multifile != null) {
                    // 重命名文件名
                    String rename = ExcelUtil.rename ( multifile.getOriginalFilename () );
                    //服务器路径
                    String path = uploadHome + rename;
                    PmsProjectDocument document = new PmsProjectDocument ();
                    document.setDocumentName ( multifile.getOriginalFilename () );
                    document.setDocumentId ( IdGen.uuid () );
                    document.setUploadAddr ( rename );
                    document.setUploadBy ( UserUtils.getUser () );
                    document.setUploadTime ( new Date () );
                    document.setCreateBy ( UserUtils.getUser () );
                    document.setCreateDate ( new Date () );
                    document.setUpdateBy ( UserUtils.getUser () );
                    document.setUpdateDate ( new Date () );
                    document.setDelFlag ( "0" );
                    multifile.transferTo ( new File ( path ) );
                    int i = projectUploadService.saveFileToMysql ( document );
                    if (1 == i) {
                        list.add ( document );
                    }
                }
            }
        }
        // uploadService.json_encode(response, list);
        JSONArray ja = new JSONArray ();
        JSONObject json = new JSONObject ();
        for (int i = 0; i < list.size (); i++) {
            json.put ( "name", list.get ( i ).getDocumentName () );
            json.put ( "id", list.get ( i ).getDocumentId () );
            json.put ( "url", list.get ( i ).getUploadAddr () );
            json.put ( "deleteUrl", list.get ( i ).getUploadAddr () );
            json.put ( "deleteType", "DELETE" );
            ja.add ( json );
        }
        JSONObject js = new JSONObject ();
        js.put ( "files", ja );
        response.getWriter ().print ( js.toString () );
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "upload1")
    public Map<String, String> upload(String projectId, String documentId) {
        projectUploadService.saveProjectId ( projectId, documentId );
        Map<String, String> map = new HashMap<String, String> ();
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteupload")
    public Map<String, String> handleDelete(String fileid, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PmsProjectDocument file = projectUploadService.getonefile ( fileid );
        int i = projectUploadService.deleteFileToMysql ( fileid );
        String downloadPrefix = Global.getConfig ( "windowsUploadPath" );
        Map<String, String> js = new HashMap<String, String> ();
        if (1 == i) {
            if (projectUploadService.deleteFiletoServer ( downloadPrefix + file.getUploadAddr () )) {
                js.put ( "files", "success" );
            } else {
                js.put ( "files", "fail" );
            }
        }
        return js;
    }


    @RequestMapping(value = "projectDocumentDownload")
    public String projectDocumentDownload(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        PmsProjectDocument pmsProjectDocument = projectUploadService.getOneDocumentDownload ( id );
        String fileName = pmsProjectDocument.getDocumentName ();
        String nowPath = pmsProjectDocument.getUploadAddr ();
        String downloadPrefix = Global.getConfig ( "windowsUploadPath" );
        String url = downloadPrefix + nowPath;
        File file = new File ( url );
        // 清空response
        response.reset ();
        // 设置response的Header
        response.addHeader ( "Content-Disposition",
                "attachment;filename=" + new String ( fileName.getBytes ( "gbk" ), "iso-8859-1" ) ); // 转码之后下载的文件不会出现中文乱码
        response.addHeader ( "Content-Length", "" + file.length () );

        try {

            // 以流的形式下载文件
            InputStream fis = new BufferedInputStream ( new FileInputStream ( downloadPrefix + nowPath ) );
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

}
