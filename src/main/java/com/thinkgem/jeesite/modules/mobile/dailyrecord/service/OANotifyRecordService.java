package com.thinkgem.jeesite.modules.mobile.dailyrecord.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.dao.OANotifyRecordDao;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileUploadResult;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OANotifyRecordService extends CrudService<OANotifyRecordDao, OaNotifyRecord> {

    @Autowired
    private OANotifyRecordDao oanoDao;


    public List<OaNotifyRecord> getall(Integer offset, int pageSize, String id) {
        OaNotifyRecord oaNoRe = new OaNotifyRecord ();
        User user = new User ();
        user.setId ( id );
        oaNoRe.setUser ( user );
        Page<OaNotifyRecord> page = new Page<OaNotifyRecord> ();

        page.setPageSize ( pageSize );

        page.setOffset ( offset );

        page.setMobilePage ( true );

        oaNoRe.setPage ( page );
        return oanoDao.getall ( oaNoRe );
    }


    public String gettypename(String type) {
        return oanoDao.gettypename ( type );
    }


    public OaNotify getOaNotify(String id) {
        return oanoDao.getOaNotify ( id );
    }


    public FileUploadResult getonefile(String str) {
        return oanoDao.getonefile ( str );
    }

}
