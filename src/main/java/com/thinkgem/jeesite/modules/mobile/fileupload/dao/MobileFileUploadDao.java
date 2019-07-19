package com.thinkgem.jeesite.modules.mobile.fileupload.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileUploadResult;

import java.util.List;

@MyBatisDao
public interface MobileFileUploadDao {

    public void savetomysql(FileUploadResult result);

    public void change(FileUploadResult result);

    public List<FileUploadResult> getFileList(String id);

}
