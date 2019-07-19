package com.thinkgem.jeesite.modules.oapms.services;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oapms.dao.ProjectUploadDao;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
public class ProjectUploadService extends CrudService<ProjectUploadDao, PmsProjectDocument> {

    @Autowired
    private ProjectUploadDao projectUploadDao;

    /**
     * 文件删除
     *
     * @param path
     * @return
     * @throws IOException
     */
    public boolean deleteFiletoServer(String path)
            throws IOException {
        boolean success = Boolean.FALSE;
        File f = new File ( path );
        if (f.exists ()) {
            f.delete ();
            success = Boolean.TRUE;
        }
        return success;
    }

    @Transactional(readOnly = false)
    public int saveFileToMysql(PmsProjectDocument document) {
        return projectUploadDao.saveFileToMysql ( document );
    }

    @Transactional(readOnly = false)
    public void saveProjectId(String projectId, String documentId) {
        projectUploadDao.saveProjectId ( projectId, documentId );
    }

    public PmsProjectDocument getonefile(String id) {
        return projectUploadDao.getonefile ( id );
    }

    @Transactional(readOnly = false)
    public int deleteFileToMysql(String id) {
        return projectUploadDao.deleteFileToMysql ( id );
    }

    public PmsProjectDocument getOneDocumentDownload(String id) {
        return projectUploadDao.getOneDocumentDownload ( id );
    }

}
