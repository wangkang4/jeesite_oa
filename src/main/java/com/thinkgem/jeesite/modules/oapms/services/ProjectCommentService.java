package com.thinkgem.jeesite.modules.oapms.services;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oapms.dao.ProjectCommentDao;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectComment;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectCommentService extends CrudService<ProjectCommentDao, PmsProjectComment> {

    @Autowired
    public ProjectCommentDao projectCommentDao;

    public Page<PmsProjectComment> findProjectCommentList(int offset, int pageSize, PmsProjectComment ppc) {
        Page<PmsProjectComment> page = new Page<PmsProjectComment> ();
        page.setPageSize ( pageSize );
        page.setOffset ( offset );
        page.setMobilePage ( true );

        ppc.setPage ( page );
        page.setList ( projectCommentDao.findProjectCommentList ( ppc ) );
        return page;
    }

    @Transactional(readOnly = false)
    public int inserteOneComment(PmsProjectComment comment) {
        return projectCommentDao.inserteOneComment ( comment );
    }

    public Page<PmsProjectDocument> findProjectDocumentPage(Page<PmsProjectDocument> page,
                                                            PmsProjectDocument pmsProjectDocument) {
        pmsProjectDocument.setPage ( page );
        page.setList ( projectCommentDao.findProjectDocumentList ( pmsProjectDocument ) );
        return page;
    }

    @Transactional(readOnly = false)
    public void updateDocumentInfo(PmsProjectDocument ppd) {
        projectCommentDao.updateDocumentInfo ( ppd );
    }

}
