package com.thinkgem.jeesite.common.mobile.utils;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileUploadResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件上传工具类
 *
 * @author yangdc
 * @date Apr 18, 2012
 *
 * <pre>
 *       </pre>
 */
public class MobileUploadUtils {
    // /**
    // * 表单字段常量
    // */
    // public static final String FORM_FIELDS = "form_fields";
    // /**
    // * 文件域常量
    // */
    // public static final String FILE_FIELDS = "file_fields";

    // 最大文件大小 1M * size
    private long maxSize = 1048576 * 100;
    // 定义允许上传的文件扩展名
    private Map<String, String> extMap = new HashMap<String, String> ();
    // 上传的域名
    private String serverDomain = "";
    // 文件保存目录相对路径
//	private String basePath = "upload/mobiles";
    private String basePath = "/mobiles";
    // 文件的目录名
    private String dirName = "files";
    // 上传临时路径
    private static final String TEMP_PATH = "/temp";
    private String tempPath = basePath + TEMP_PATH;
    // 若不指定则文件名默认为 yyyyMMddHHmmss_xyz
    private String fileName;

    // 文件保存目录路径
    private String savePath;
    // 文件保存目录url
    private String saveUrl;
    // 文件最终的url包括文件名
    private String fileUrl;

    public MobileUploadUtils() {

        final String images = "gif,jpg,jpeg,png,bmp,";
        final String flashs = "swf,flv,";
        final String medias = "swf,flv,mp3,mp4,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,";
        final String docs = "doc,docx,xls,xlsx,ppt,pdf,";
        final String htmls = "htm,html,js,css,jsp,";
        final String zips = "zip,rar,gz,bz2,";
        final String txts = "txt,";
        final String others = "";
        final String files = images + flashs + medias + docs + htmls + zips + txts + others;

        extMap.put ( "images", images );
        extMap.put ( "flashs", flashs );
        extMap.put ( "medias", medias );
        extMap.put ( "files", files );

    }

    /**
     * 文件上传
     *
     * @param files   非空
     * @param type    上传类型 用于后续扩展 如 上传图片 特殊处理 修改图片大小等
     * @param request
     * @return List<FileUploadResult> 上传结果
     */
    public List<FileUploadResult> uploadFile(@NotNull CommonsMultipartFile[] files, String type,
                                             HttpServletRequest request) {

        List<FileUploadResult> uploadResult = new ArrayList<FileUploadResult> ();

        // 验证上传路径的合法性
        String validateInfo = this.validateFields ( request );

        if ("true".equals ( validateInfo )) {

            for (CommonsMultipartFile file : files) {

                String saveStateInfo = this.saveFile ( file );

                FileUploadResult model = new FileUploadResult ();

                model.setFileOriginalFilename ( file.getOriginalFilename () );
                // model.setFileFinalPath(serverDomain + fileUrl);
                model.setFileFinalPath ( fileUrl );
                model.setId ( IdGen.uuid () );
                model.setSize ( file.getSize () );
                model.setCreatertime ( new Date () );

                if ("true".equals ( saveStateInfo )) {

                    model.setState ( true );
                    model.setInfo ( "true" );

                } else {

                    model.setState ( false );
                    model.setInfo ( saveStateInfo );

                }

                uploadResult.add ( model );
            }

        } else {

            /**
             * 校验失败 构建一个临时的result 用来返回错误信息
             */
            FileUploadResult model = new FileUploadResult ();

            model.setState ( false );
            // model.setInfo("上传文件 路径校验失败");
            model.setInfo ( validateInfo );

            uploadResult.add ( model );
        }

        return uploadResult;
    }

    /**
     * 上传验证,并初始化文件目录
     *
     * @param request
     */
    private String validateFields(HttpServletRequest request) {
        String errorInfo = "true";
        // boolean errorFlag = true;
        // 获取内容类型
        // String contentType = request.getContentType();
        // int contentLength = request.getContentLength();

        serverDomain = request.getScheme () + "://" + request.getServerName () + ":" + request.getServerPort ();
        // System.out.println(serverDomain);

        // 文件保存目录路径
//		savePath = Global.getConfig("uploadPath")+"mobiles/" + basePath + "/";
        savePath = Global.getConfig ( "uploadPath" ) + "mobiles/";

        // 文件保存目录URL
        saveUrl = basePath + "/";


        System.out.println ( "savePath----->" + savePath );
        System.out.println ( "saveUrl----->" + saveUrl );

        File uploadDir = new File ( savePath );
        // if (contentType == null || !contentType.startsWith("multipart")) {
        // // TODO
        // System.out.println("请求不包含multipart/form-data流");
        // errorInfo = "请求不包含multipart/form-data流";
        // }
        // else if (maxSize < contentLength) {
        // // TODO
        // System.out.println("上传文件大小超出文件最大大小");
        // errorInfo = "上传文件大小超出文件最大大小[" + maxSize + "]";
        // }
        // else if (!ServletFileUpload.isMultipartContent(request)) {
        // // TODO
        // errorInfo = "请选择文件";
        // }
        // else
        if (!uploadDir.isDirectory ()) {// 检查目录
            // TODO
            errorInfo = "上传目录[" + savePath + "]不存在";
        } else if (!uploadDir.canWrite ()) {
            // TODO
            errorInfo = "上传目录[" + savePath + "]没有写权限";
        } else if (!extMap.containsKey ( dirName )) {
            // TODO
            errorInfo = "目录名不正确";
        } else {
            // .../basePath/dirName/
            // 创建文件夹
            savePath += dirName + "/";
            saveUrl += dirName + "/";
            File saveDirFile = new File ( savePath );
            if (!saveDirFile.exists ()) {
                saveDirFile.mkdirs ();
            }
            // .../basePath/dirName/yyyyMMdd/
            SimpleDateFormat sdf = new SimpleDateFormat ( "yyyyMMdd" );
            String ymd = sdf.format ( new Date () );
            savePath += ymd + "/";
            saveUrl += ymd + "/";
            File dirFile = new File ( savePath );
            if (!dirFile.exists ()) {
                dirFile.mkdirs ();
            }

            // 获取上传临时路径
            tempPath = request.getSession ().getServletContext ().getRealPath ( "/" ) + tempPath + "/";
            File file = new File ( tempPath );
            if (!file.exists ()) {
                file.mkdirs ();
            }
        }

        return errorInfo;
    }

    /**
     * 处理上传内容
     *
     * @param request
     * @param maxSize
     * @return
     */
    // @SuppressWarnings("unchecked")
    // private Map<String, Object> initFields(HttpServletRequest request) {
    //
    // // 存储表单字段和非表单字段
    // Map<String, Object> map = new HashMap<String, Object>();
    //
    // // 第一步：判断request
    // boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    // // 第二步：解析request
    // if (isMultipart) {
    // // Create a factory for disk-based file items
    // DiskFileItemFactory factory = new DiskFileItemFactory();
    //
    // // 阀值,超过这个值才会写到临时目录,否则在内存中
    // factory.setSizeThreshold(1024 * 1024 * 10);
    // factory.setRepository(new File(tempPath));
    //
    // // Create a new file upload handler
    // ServletFileUpload upload = new ServletFileUpload(factory);
    //
    // upload.setHeaderEncoding("UTF-8");
    //
    // // 最大上传限制
    // upload.setSizeMax(maxSize);
    //
    // /* FileItem */
    // List<FileItem> items = null;
    // // Parse the request
    // try {
    // items = upload.parseRequest(request);
    // } catch (FileUploadException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // System.out.println("FileUploadException--->"+e.toString());
    // }
    //
    // System.out.println("items--->"+items==null?"null":items.size());
    //
    // // 第3步：处理uploaded items
    // if (items != null && items.size() > 0) {
    // Iterator<FileItem> iter = items.iterator();
    // // 文件域对象
    // List<FileItem> list = new ArrayList<FileItem>();
    // // 表单字段
    // Map<String, String> fields = new HashMap<String, String>();
    // while (iter.hasNext()) {
    // FileItem item = iter.next();
    // // 处理所有表单元素和文件域表单元素
    // if (item.isFormField()) { // 表单元素
    // String name = item.getFieldName();
    // String value = item.getString();
    // fields.put(name, value);
    // } else { // 文件域表单元素
    // list.add(item);
    // }
    // }
    // map.put(FORM_FIELDS, fields);
    // map.put(FILE_FIELDS, list);
    // }
    // }
    //
    //
    // System.out.println("map--->"+map.toString());
    //
    // return map;
    // }

    /**
     * 保存文件
     *
     * @param obj  要上传的文件域
     * @param file
     * @return
     */
    private String saveFile(CommonsMultipartFile item) {
        String error = "true";
        // String fileName = item.getName();
        String fileName = item.getOriginalFilename ();

        // System.out.println("fileName----------------->" + fileName);

        String fileExt = fileName.substring ( fileName.lastIndexOf ( "." ) + 1 ).toLowerCase ();

        if (item.getSize () > maxSize) { // 检查文件大小
            // TODO
            error = fileName + "上传文件大小超过限制";
        } else if (!Arrays.<String>asList ( extMap.get ( dirName ).split ( "," ) ).contains ( fileExt )) {// 检查扩展名

            error = fileName + "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get ( dirName ) + "格式。";

        } else {

            String newFileName;
            // if ("".equals(fileName.trim())) {
            //
            // SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            // newFileName = df.format(new Date()) + "_" + new
            // Random().nextInt(1000) + "."
            // + fileExt;
            //
            // } else {
            //
            // // newFileName = fileName + "." + fileExt;
            // newFileName = fileName;
            // }

            // 重新构建文件名称 防止文件名相同 但是文件不同 覆盖原文件
            SimpleDateFormat df = new SimpleDateFormat ( "yyyyMMddHHmmss" );
            newFileName = df.format ( new Date () ) + "_" + new Random ().nextInt ( 1000 ) + "." + fileExt;

            // .../basePath/dirName/yyyyMMdd/yyyyMMddHHmmss_xxx.xxx
            fileUrl = saveUrl + newFileName;
            try {
                File uploadedFile = new File ( savePath, newFileName );

                item.transferTo ( uploadedFile );

                /*
                 * FileOutputStream fos = new FileOutputStream(uploadFile); //
                 * 文件全在内存中 if (item.isInMemory()) { fos.write(item.get()); }
                 * else { InputStream is = item.getInputStream(); byte[] buffer
                 * = new byte[1024]; int len; while ((len = is.read(buffer)) >
                 * 0) { fos.write(buffer, 0, len); } is.close(); } fos.close();
                 * item.delete();
                 */
            } catch (IOException e) {

                error = fileName + "上传失败了！！！" + e.toString ();
                e.printStackTrace ();
                // System.out.println("上传失败了！！！" + e.toString());

            } catch (Exception e) {

                error = fileName + "上传失败了！！！" + e.toString ();
                e.printStackTrace ();
                // System.out.println("上传失败了！！！" + e.toString());
            }
        }
        return error;
    }

    /**
     * *********************get/set方法*********************************
     */

    public String getSavePath() {
        return savePath;
    }

    public String getSaveUrl() {
        return saveUrl;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public Map<String, String> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, String> extMap) {
        this.extMap = extMap;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
        tempPath = basePath + TEMP_PATH;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
