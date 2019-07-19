package com.thinkgem.jeesite.modules.weekly.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.daily.entity.TCheckBacklog;
import com.thinkgem.jeesite.modules.daily.entity.TDaily;
import com.thinkgem.jeesite.modules.weekly.dao.WeeklyDao;
import com.thinkgem.jeesite.modules.weekly.entity.FileModel;
import com.thinkgem.jeesite.modules.weekly.entity.Weekly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * OA系统周报service操作
 * @author tanchaoyang
 * @version 2017-8-2
 */
@Service
public class WeeklyService extends CrudService<WeeklyDao, Weekly>{
	
	@Autowired
	private WeeklyDao weeklyDao;
	
	//获取周报提交人数；
	public int getWeekNum(){
		int num=weeklyDao.getWeekNum();
		return num;
	}
	/**
	 * 获取页码和列表信息
	 */
	public Page<Weekly> findPage(Page<Weekly> page, Weekly Weekly) {
		return super.findPage(page, Weekly);
	}

	public List<TDaily> getdaily(Weekly w) {
		return weeklyDao.getdaily(w);
	}


	public List<FileModel> getallfile(String id) {
		return weeklyDao.getallfile(id);
	}

	public FileModel getonefile(String str3) {
		return weeklyDao.getonefile(str3);
	}

	@Transactional(readOnly = false)
	public void deleteonefile(String str3) {
		weeklyDao.deleteonefile(str3);
	}

	@Transactional(readOnly = false)
	public void saveFileToMysql(FileModel fileModel) {
		weeklyDao.saveFileToMysql(fileModel);
	}
	
	@Transactional(readOnly = false)
	public void addweekly(Weekly weekly) {
		weeklyDao.addweekly(weekly);
	}

	public List<TCheckBacklog> findtCheckBL(TCheckBacklog tCheckBacklog) {
		return weeklyDao.findtCheckBL(tCheckBacklog);
	}

	public String getofficeName(String str1) {
		return weeklyDao.getofficeName(str1);
	}

	public Weekly getone(String id) {
		return weeklyDao.getone(id);
	}

	
}
