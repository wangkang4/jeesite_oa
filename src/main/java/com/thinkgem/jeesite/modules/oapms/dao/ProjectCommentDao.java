package com.thinkgem.jeesite.modules.oapms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectComment;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectDocument;

import java.util.List;

@MyBatisDao
public interface ProjectCommentDao extends CrudDao<PmsProjectComment> {

    List<PmsProjectComment> findProjectCommentList(PmsProjectComment ppc);

    int inserteOneComment(PmsProjectComment comment);

    List<PmsProjectDocument> findProjectDocumentList(PmsProjectDocument pmsProjectDocument);

    void updateDocumentInfo(PmsProjectDocument ppd);

}
