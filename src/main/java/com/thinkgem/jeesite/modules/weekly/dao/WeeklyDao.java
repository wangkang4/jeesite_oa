package com.thinkgem.jeesite.modules.weekly.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.daily.entity.TCheckBacklog;
import com.thinkgem.jeesite.modules.daily.entity.TDaily;
import com.thinkgem.jeesite.modules.weekly.entity.FileModel;
import com.thinkgem.jeesite.modules.weekly.entity.Weekly;

import java.util.List;

/**
 * OA系统周报表数据库操作dao
 * @author tanchaoyang
 * @version 2017-8-2
 */
@MyBatisDao
public interface WeeklyDao extends CrudDao<Weekly>{

	public List<TDaily> getdaily(Weekly w);

	public void addfiles(FileModel fileModel);

	public List<FileModel> getallfile(String weeklyid);

	public FileModel getonefile(String str3);

	public void deleteonefile(String str3);

	public void saveFileToMysql(FileModel fileModel);

	public void addweekly(Weekly weekly);

	public List<TCheckBacklog> findtCheckBL(TCheckBacklog tCheckBacklog);

	public String getofficeName(String str1);

	public Weekly getone(String id);

	//获取周报提交人数；
	public int getWeekNum();

}
