package com.thinkgem.jeesite.modules.weekly.service;

import com.thinkgem.jeesite.modules.CostExcel.utils.ExcelUtil;
import com.thinkgem.jeesite.modules.weekly.dao.UploadDao;
import com.thinkgem.jeesite.modules.weekly.entity.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class UploadService {

	@Autowired
	private UploadDao uploadDao;

    /** 
     * 描述 : <将文件保存到指定路径>. <br> 
     *<p> 
     * 
     * @param multifile 
     * @param path 
     * @return 
     * @throws IOException 
     */  
    public String saveFileToServer(MultipartFile multifile, String path)  
            throws IOException {  
        // 创建目录  
        File dir = new File(path);  
        if (!dir.exists()) {  
            dir.mkdir();  
        }  
        String rename = ExcelUtil.rename(multifile.getOriginalFilename());
        // 读取文件流并保持在指定路径  
        InputStream inputStream = multifile.getInputStream();  
        OutputStream outputStream = new FileOutputStream(path
                + rename);  
        byte[] buffer = multifile.getBytes();  
        int bytesum = 0;  
        int byteread = 0;  
        while ((byteread = inputStream.read(buffer)) != -1) {  
            bytesum += byteread;  
            outputStream.write(buffer, 0, byteread);  
            outputStream.flush();  
        }  
        outputStream.close();  
        inputStream.close();  
        
        return path + rename;  
    }  
    public boolean deleteFiletoServer(String path)  
            throws IOException {  
        boolean success = Boolean.FALSE;  
        File f = new File(path);  
        if (f.exists()) {  
           f.delete();  
           success = Boolean.TRUE;  
        }  
  
  
        return success;  
    }  
//    public void json_encode(final HttpServletResponse response, Object o) throws IOException{  
//        response.setHeader("Cache-Control", "no-store");  
//        response.setHeader("Pragma", "no-cache");  
//        response.setDateHeader("Expires", 0);  
//        response.setContentType("text/html");  
//        PrintWriter out = response.getWriter();  
//        Gson gs = new Gson();  
//        out.write(gs.toJson(o));  
//    }  
	public void saveFileToMysql(FileModel fileModel) {
		uploadDao.saveFileToMysql(fileModel);
	}
	public void deleteFileToMysql(String fileid) {
		uploadDao.deleteFileToMysql(fileid);
	}
	public FileModel getonefile(String fileid) {
		return uploadDao.getonefile(fileid);
	}
	public List<FileModel> getfiles() {
		return uploadDao.getfiles();
	}
	
}
