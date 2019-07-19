/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mobile.fileupload.service;

import com.thinkgem.jeesite.common.mobile.utils.MobileUploadUtils;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.mobile.fileupload.dao.MobileFileUploadDao;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileProcessResult;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ThinkGem
 * @version 2013-12-05
 */
@Service
@Transactional(readOnly = true)
public class MobileFileUploadService extends BaseService {

    @Autowired
    private MobileFileUploadDao uploadDao;

    /**
     * @param files   上传的文件
     * @param type    用于后续扩展 如 tyep = "images" 特殊处理
     * @param request
     * @return FileProcessResult state = true 結果List<FileUploadResult>
     * <p>
     * state = false 错误信息 info
     */
    public FileProcessResult uploadFiles(CommonsMultipartFile[] files, String type, HttpServletRequest request) {

        FileProcessResult processResult = new FileProcessResult ();

        MobileUploadUtils uploadUtils = new MobileUploadUtils ();

        List<FileUploadResult> uploadResult = uploadUtils.uploadFile ( files, type, request );

        StringBuilder logInfo = new StringBuilder ();
        logInfo.append ( "uploadFilesMobile-->" );

        String errorInfo = "";
        for (FileUploadResult model : uploadResult) {

            logInfo.append ( model.getFileOriginalFilename () )
                    .append ( ":" )
                    .append ( model.getFileFinalPath () )
                    .append ( ":" )
                    .append ( model.getInfo () )
                    .append ( "," );

            if (!model.isState ()) {

                errorInfo = errorInfo + "," + model.getInfo ();
            }
        }
        if (!"".equals ( errorInfo )) {

            processResult.setState ( false );
            processResult.setInfo ( errorInfo );

        } else {

            processResult.setState ( true );
        }

        processResult.setResults ( uploadResult );

        logInfo.append ( "\n" );
        logger.warn ( logInfo.toString () );


        return processResult;
    }


    @Transactional(readOnly = false)
    public void savetomysql(FileUploadResult result) {
        uploadDao.savetomysql ( result );
    }

    @Transactional(readOnly = false)
    public void change(FileUploadResult result) {
        uploadDao.change ( result );
    }


    public List<FileUploadResult> getFileList(String id) {
        return uploadDao.getFileList ( id );
    }

}
