package com.thinkgem.jeesite.modules.weekly.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.daily.dao.TCheckBacklogDao;
import com.thinkgem.jeesite.modules.daily.entity.TCheckBacklog;
import com.thinkgem.jeesite.modules.daily.entity.TDaily;
import com.thinkgem.jeesite.modules.daily.service.TCheckBacklogService;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.weekly.entity.FileModel;
import com.thinkgem.jeesite.modules.weekly.entity.Weekly;
import com.thinkgem.jeesite.modules.weekly.service.UploadService;
import com.thinkgem.jeesite.modules.weekly.service.WeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * OA系统周报表控制层
 * @author tanchaoyang
 * @version 2017-8-2
 */
@Controller
@RequestMapping(value="${adminPath}/oa/weekly")
public class WeeklyController extends BaseController{

	@Autowired
	private WeeklyService weeklyService;
	
	@Autowired
	private TCheckBacklogService tCheckBacklogService;
	
	@Autowired
	private TCheckBacklogDao tCheckBacklogDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UploadService uploadService;
	
	/**
	 * 周报管理列表
	 * @return
	 */
	@RequestMapping(value={"list",""})
	public String list(Weekly weekly, HttpServletRequest request, HttpServletResponse response,Model model){
		weekly.setUser(UserUtils.getUser());
		//获取携带页码的周报信息 
		Page<Weekly> page = weeklyService.findPage(new Page<Weekly>(request, response), weekly);
        model.addAttribute("page", page);
		return "modules/weekly/weeklylist";
        
	}
	
	/**
	 * 附件上传子页面
	 * @return
	 */
	@RequestMapping("/fileuploads")
	public String fileuploads(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException{
		//附件上传页面的跳转
		return "modules/weekly/index";
	}
	
	/**
	 * 周报添加保存
	 * @return
	 */
	@RequestMapping(value="addone")
	public String addone(Weekly weekly ,Model model, RedirectAttributes redirectAttributes) throws ParseException{
		weekly.setId(IdGen.uuid());//初始化周报信息id
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String[] fileids = weekly.getFileid().split("\\|");//附件id分割城数组
		fileData(fileids, weekly.getId());//保存附件信息
		Date creatertime = new Date();
		weekly.setCreatertime(creatertime);//保存发送时间
		String dayTime = df.format(creatertime);
		weekly.setDaytime(df.parse(dayTime));
		weekly.setCreaterid(UserUtils.getUser().getId());
		tCheckBacklogData(weekly);//保存中间表信息
		String name1 = weekly.getUser().getName();
		weekly.setRemarks(name1);//收件人名字保存
		weeklyService.addweekly(weekly);
		addMessage(redirectAttributes, "保存保存成功成功");
	return "redirect:"+Global.getAdminPath()+"/oa/weekly/?repage";
		
	}
	
	/**
	 * 周报添加，修改页面跳转
	 * @return
	 */
	@RequestMapping(value="form")
	public String form(String id , Weekly weekly,Model model){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String url="";
		//传过来的id不为空时
		if(StringUtils.isNotBlank(id)){//根据前台id的值判断是新增 还是 修改
			weekly = weeklyService.get(weekly.getId());//获取周报
			List<FileModel> filelist =new ArrayList<FileModel>();
			filelist = weeklyService.getallfile(id);//获取该周报的附件信息
			List<TDaily> tDailylist = new ArrayList<TDaily>();
			Calendar cal = Calendar.getInstance();
			cal.setTime(weekly.getDaytime());
			cal.set(Calendar.HOUR_OF_DAY, 0);

			cal.set(Calendar.SECOND, 0);

			cal.set(Calendar.MINUTE, 0);
			Date date1 = new Date();
			Date date2 = new Date();
//			Map<String,Date> map = new HashMap<String, Date>();
			int week_index = cal.get(Calendar.DAY_OF_WEEK)-1;
			cal.add(Calendar.DATE, -week_index+1);
			for(int i = 0 ;i<7 ; i++){
				Weekly w = new Weekly();   //临时查找类
				date1 = cal.getTime();
				cal.add(Calendar.DATE,1);
				date2 = cal.getTime();
				w.setStartTime(date1);
				w.setEndTime(date2);
				w.setUser(UserUtils.getUser());
//				map.put("a", date1);
//				map.put("b", date2);
				List<TDaily> tlist = weeklyService.getdaily(w);
				if(tlist != null && tlist.size() > 0){
//					tDaily.setRemarks(df.format(tDaily.getDayTime()));
					for(TDaily t : tlist){
						tDailylist.add(t);
					}
					
				}
			}
			model.addAttribute("tDailylist", tDailylist);
			
			model.addAttribute("filelist", filelist);
			model.addAttribute("weekly",weekly);
 			url =  "modules/weekly/weeklyform";
		}else{
			
			List<TDaily> tDailylist = new ArrayList<TDaily>();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);

			cal.set(Calendar.SECOND, 0);

			cal.set(Calendar.MINUTE, 0);
			Date date1 = new Date();
			Date date2 = new Date();
//			Map<String,Date> map = new HashMap<String, Date>();
			int week_index = cal.get(Calendar.DAY_OF_WEEK)-1;
			cal.add(Calendar.DATE, -week_index+1);
			for(int i = 0 ;i<7 ; i++){
				Weekly w = new Weekly();   //临时查找类
				date1 = cal.getTime();
				cal.add(Calendar.DATE,1);
				date2 = cal.getTime();
				w.setStartTime(date1);
				w.setEndTime(date2);
				w.setUser(UserUtils.getUser());
//				map.put("a", date1);
//				map.put("b", date2);
				List<TDaily> tlist = weeklyService.getdaily(w);
				if(tlist != null && tlist.size() > 0){
//					tDaily.setRemarks(df.format(tDaily.getDayTime()));
					for(TDaily t : tlist){
						tDailylist.add(t);
					}
					
				}
			}
			model.addAttribute("tDailylist", tDailylist);
			url =  "modules/weekly/weeklyadd";
		}
		return url;
	}
	
	/**
	 * 修改保存
	 * @return
	 */
	@RequestMapping(value="save")
	public String save(Weekly weekly,Model model, RedirectAttributes redirectAttributes){
			String[] fileids = weekly.getFileid().split("\\|");
			fileData(fileids, weekly.getId());//附件信息存储
			Date creatertime = new Date();
			weekly.setCreatertime(creatertime);
			weekly.setCreaterid(UserUtils.getUser().getId());
			tCheckBacklogData(weekly);//中间表信息存储
			String name = weekly.getUser().getName();
			weekly.setRemarks(name);
			weeklyService.save(weekly);
			addMessage(redirectAttributes, "保存保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/oa/weekly/?repage";
	}
	
	/**
	 * 附件信息
	 * @param id
	 */
	public void fileData(String[] fileids,String id){
		for(String str3 : fileids){
			if(StringUtils.isNotBlank(str3)){
				FileModel fileModel = weeklyService.getonefile(str3);
				fileModel.setProftype("2");
				fileModel.setProfid(id);
				weeklyService.deleteonefile(str3);
				weeklyService.saveFileToMysql(fileModel);
			}
		}
		
	}
	
	/**
	 * 中间表信息存储
	 * @param weekly
	 */
	public void tCheckBacklogData(Weekly weekly){
		tCheckBacklogDao.deleteone(weekly.getId());
		String str = weekly.getUser().getId();//前台选择的多个收件人
		String[] str1 = str.split(",");
		for(String str2:str1){//循环插入多个收件人中间表信息
			User user = new User();
			user.setId(str2);
			TCheckBacklog tCheckBacklog = new TCheckBacklog();
			tCheckBacklog.setId(IdGen.uuid());
			tCheckBacklog.setTypes(2);
			tCheckBacklog.setProfId(weekly.getId());
			tCheckBacklog.setUser(user);
			tCheckBacklog.setCreaterId(weekly.getCreaterid());
			tCheckBacklog.setCreaterTime(weekly.getCreatertime());
			tCheckBacklogDao.insert(tCheckBacklog);
		}

	}
	
	/**
	 * 删除某条信息
	 * @return
	 */
	@RequestMapping(value="delete")
	public String delete(Weekly weekly,RedirectAttributes redirectAttributes){
		weeklyService.delete(weekly);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:"+Global.getAdminPath()+"/oa/weekly/?repage";
	}
	
	/**
	 * 获取本周日报详情
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getweekcontent")
	public List<TDaily> getweekcontent(String daytime) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<TDaily> tDailylist = new ArrayList<TDaily>();
		Date date = df.parse(daytime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date date1 = new Date();
		Date date2 = new Date();
//		Map<String,Date> map = new HashMap<String, Date>();
		int week_index = cal.get(Calendar.DAY_OF_WEEK)-1;
		cal.add(Calendar.DATE, -week_index+1);
		for(int i = 0 ;i<7 ; i++){
			Weekly w = new Weekly();   //临时查找类
			date1 = cal.getTime();
			cal.add(Calendar.DATE,1);
			date2 = cal.getTime();
			w.setStartTime(date1);
			w.setEndTime(date2);
			w.setUser(UserUtils.getUser());
//			map.put("a", date1);
//			map.put("b", date2);
			List<TDaily> tlist = weeklyService.getdaily(w);
			if(tlist != null && tlist.size() > 0){
//				tDaily.setRemarks(df.format(tDaily.getDayTime()));
				for(TDaily t : tlist){
					tDailylist.add(t);
				}
				
			}
		}
		return tDailylist;
	}
	
	/**
	 * 删除历史附件
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="deletefile")
	public String deletefile(String id,Model model) throws IOException{
		System.out.println(">>>>>>>>>>>>>>>>>>>>>"+id);
		FileModel file1 = weeklyService.getonefile(id);
		String weeklyid = file1.getProfid();
		Weekly weekly = weeklyService.get(weeklyid);
		weeklyService.deleteonefile(id);
		String downloadPrefix = Global.getConfig("uploadPath");
		uploadService.deleteFiletoServer(downloadPrefix+ file1.getAttapath()); 
		List<FileModel> filelist =new ArrayList<FileModel>();
		filelist = weeklyService.getallfile(weekly.getId());
		model.addAttribute("filelist", filelist);
		model.addAttribute("weekly",weekly);
		return "modules/weekly/weeklyform";
	}
	
	/**
	 * 领导查看全部信息页面
	 * @return
	 */
	@RequestMapping(value="weeklyLeadList")
	public String LeadList(TCheckBacklog tCheckBacklog,Model model, HttpServletRequest request,
			HttpServletResponse response){
		tCheckBacklog.setUser(UserUtils.getUser());
		tCheckBacklog.setTypes(2);
		Page<TCheckBacklog> page = tCheckBacklogService.findPage(new Page<TCheckBacklog>(request, response),
				tCheckBacklog);
		model.addAttribute("page", page);
		return "modules/weekly/weeklyLeadList";
	}

	/**
	 * 周报领导视图查看某个信息功能
	 * @return
	 */
	@RequestMapping(value="weeklyLeadform")
	public String weeklyLeadform(String profid,Model model, HttpServletRequest request,
			HttpServletResponse response){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Weekly weekly = weeklyService.get(profid);
		User user = userDao.get(weekly.getCreaterid());
		List<FileModel> filemodellist =new ArrayList<FileModel>();
		filemodellist = weeklyService.getallfile(weekly.getId());
		String fileids= "";
		for(int j =0 ;j<filemodellist.size();j++){
			fileids = filemodellist.get(j).getId()+"|";
		}
		weekly.setFileid(fileids);
		List<FileModel> filelist =new ArrayList<FileModel>();
		filelist = weeklyService.getallfile(weekly.getId());
		model.addAttribute("user",user);
		model.addAttribute("filelist", filelist);
		model.addAttribute("weekly",weekly);
		List<TDaily> tDailylist = new ArrayList<TDaily>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(weekly.getDaytime());
		Date date1 = new Date();
		Date date2 = new Date();
//		Map<String,Date> map = new HashMap<String, Date>();
		int week_index = cal.get(Calendar.DAY_OF_WEEK)-1;
		cal.add(Calendar.DATE, -week_index+1);
		for(int i = 0 ;i<7 ; i++){
			Weekly w = new Weekly();   //临时查找类
			date1 = cal.getTime();
			cal.add(Calendar.DATE,1);
			date2 = cal.getTime();
			w.setStartTime(date1);
			w.setEndTime(date2);
			w.setUser(UserUtils.getUser());
//			map.put("a", date1);
//			map.put("b", date2);
			List<TDaily> tlist = weeklyService.getdaily(w);
			if(tlist != null && tlist.size() > 0){
//				tDaily.setRemarks(df.format(tDaily.getDayTime()));
				for(TDaily t : tlist){
					tDailylist.add(t);
				}
				
			}
		}
		model.addAttribute("tDailylist", tDailylist);
		return 	"modules/weekly/weeklyLeadForm";
	}
	
	/**
	 * 下载周报附件
	 * @param fileid
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
    @RequestMapping(value = "/download3")  
    public void down(String fileid,HttpServletRequest request,HttpServletResponse response) throws IOException { 
		FileModel fileModel = weeklyService.getonefile(fileid);
    	String fileName = fileModel.getName();
		String nowPath = fileModel.getAttapath();
		String downloadPrefix = Global.getConfig("uploadPath");
		String url = downloadPrefix + nowPath;
		File file = new File(url);
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
		response.addHeader("Content-Length", "" + file.length());

		try {
			
			// 以流的形式下载文件
			InputStream fis = new BufferedInputStream(new FileInputStream(downloadPrefix+nowPath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();

			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
}
