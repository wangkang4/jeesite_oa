package com.thinkgem.jeesite.modules.oapms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectDocument;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface ProjectUploadDao extends CrudDao<PmsProjectDocument> {

    int saveFileToMysql(PmsProjectDocument document);

    PmsProjectDocument getonefile(String id);

    int deleteFileToMysql(String id);

    PmsProjectDocument getOneDocumentDownload(String id);

    void saveProjectId(@Param(value = "projectId") String projectId, @Param(value = "documentId") String documentId);

}
