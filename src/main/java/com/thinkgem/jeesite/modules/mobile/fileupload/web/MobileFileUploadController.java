/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mobile.fileupload.web;

import com.thinkgem.jeesite.common.mobile.http.MobileResponse;
import com.thinkgem.jeesite.common.mobile.http.MobileResponseState;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileProcessResult;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileUploadResult;
import com.thinkgem.jeesite.modules.mobile.fileupload.service.MobileFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户Controller
 *
 * @author ThinkGem
 * @version 2013-8-29
 */

@Controller
@RequestMapping(value = "/mobile/sys")
public class MobileFileUploadController extends BaseController {

    @Autowired
    private MobileFileUploadService uploadService;

    /**
     * @param files   上传的文件
     * @param type    用于后续扩展 如 type = "images" 特殊处理
     * @param request
     */
    @RequestMapping(value = "uploadFiles")
    public @ResponseBody
    MobileResponse<List<FileUploadResult>> uploadFiles(
            @RequestParam("files") CommonsMultipartFile[] files, String type, HttpServletRequest request) {

        if (files == null || files.length == 0) {

            return new MobileResponse<List<FileUploadResult>> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }

        FileProcessResult result = uploadService.uploadFiles ( files, type, request );

        if (result.isState ()) {

            for (int i = 0; i < result.getResults ().size (); i++) {
                uploadService.savetomysql ( result.getResults ().get ( i ) );
            }

            return new MobileResponse<List<FileUploadResult>> ( result.getResults () );

        } else {

            return new MobileResponse<List<FileUploadResult>> ( new MobileResponseState ( "有文件上传失败-->" + result.getInfo () ) );
        }

    }

}
