package com.thinkgem.jeesite.modules.weekly.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.CostExcel.utils.ExcelUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.weekly.entity.FileModel;
import com.thinkgem.jeesite.modules.weekly.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {

	@Resource(name = "uploadService")
	private UploadService uploadService;

	/**
	 * 描述 : <事先就并不知道确切的上传文件数目，比如FancyUpload这样的多附件上传组件>. <br>
	 * <p>
	 * 
	 * @param model
	 * @param multipartRequest
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/upload2")
	public String handleImport(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws IOException {
		// String uploadHome =
		// request.getSession().getServletContext().getRealPath("/upload");
		String uploadHome = Global.getConfig("uploadPath") + "pc/";
//		String uploadHome = "f:/thdOA";
		System.out.println(">>>>>>>>>>>>>>>>>." + uploadHome);

		List<FileModel> list = new ArrayList<FileModel>();
		if (multipartRequest != null) {
			// 上传文件，并保存路径到数据库
			Iterator<String> iterator = multipartRequest.getFileNames();
			while (iterator.hasNext()) {
				// 一次遍历所有文件
				MultipartFile multifile = multipartRequest.getFile((String) iterator.next());
				if (multifile != null) {
					// 重命名文件名
					String rename = ExcelUtil.rename(multifile.getOriginalFilename());
					//服务器路径
					String path = uploadHome + rename;
//					String mypath = myupload + rename;
					//数据库路径
					String savepath = "/pc/" + rename;
					FileModel fileModel = new FileModel();
					fileModel.setName(multifile.getOriginalFilename());
					fileModel.setAttasize(Long.toString(multifile.getSize()));
//					String path = uploadService.saveFileToServer(multifile, uploadHome);
					fileModel.setAttapath(savepath);
					fileModel.setId(IdGen.uuid());
					fileModel.setCreaterid(UserUtils.getUser().getId());
					fileModel.setCreatertime(new Date());
					multifile.transferTo(new File(path));
					uploadService.saveFileToMysql(fileModel);
					list.add(fileModel);
				}
			}
		}
		// uploadService.json_encode(response, list);
		JSONArray ja = new JSONArray();
		JSONObject json = new JSONObject();
		for (int i = 0; i < list.size(); i++) {
			json.put("name", list.get(i).getName());
			json.put("id", list.get(i).getId());
			json.put("size", list.get(i).getAttasize());
			json.put("url", list.get(i).getAttapath());
			json.put("thumbnailUrl", uploadHome + "/" + list.get(i).getName());
			json.put("deleteUrl", list.get(i).getAttapath());
			json.put("deleteType", "DELETE");
			ja.add(json);
		}
		JSONObject js = new JSONObject();
		js.put("files", ja);
		response.getWriter().print(js.toString());
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteupload")
	public String handleDelete(String fileid, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		FileModel fileModel = uploadService.getonefile(fileid);
		uploadService.deleteFileToMysql(fileid);
		String downloadPrefix = Global.getConfig("uploadPath");
		boolean success = uploadService.deleteFiletoServer(downloadPrefix + fileModel.getAttapath());
		// uploadService.json_encode(response, success);
		JSONObject js = new JSONObject();
		js.put("files", success);
		response.getWriter().print(js.toString());
		return null;
	}

}
