package com.thinkgem.jeesite.modules.mobile.dailyrecord.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileUploadResult;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;

import java.util.List;

@MyBatisDao
public interface OANotifyRecordDao extends CrudDao<OaNotifyRecord> {

    public List<OaNotifyRecord> getall(OaNotifyRecord oaNoRe);

    public String gettypename(String type);

    public OaNotify getOaNotify(String id);

    public FileUploadResult getonefile(String str);

}
