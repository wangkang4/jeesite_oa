package com.thinkgem.jeesite.modules.weekly.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.weekly.entity.FileModel;
import com.thinkgem.jeesite.modules.weekly.entity.Weekly;

import java.util.List;

@MyBatisDao
public interface UploadDao extends CrudDao<Weekly>{


	public void saveFileToMysql(FileModel fileModel);

	public void deleteFileToMysql(String fileid);

	public FileModel getonefile(String fileid);

	public List<FileModel> getfiles();

}
