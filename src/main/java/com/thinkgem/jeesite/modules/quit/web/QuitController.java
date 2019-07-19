package com.thinkgem.jeesite.modules.quit.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.quit.entity.Quit;
import com.thinkgem.jeesite.modules.quit.service.QuitService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author dongxueyong
 */
@Controller
@RequestMapping(value = "${adminPath}/tb/quit")
public class QuitController extends BaseController {

    /**
     * 依赖注入service层属性
     */
    @Autowired
    private QuitService quitService;

    /**
     * @param id 实例id
     * @return Quit对象
     */
    @ModelAttribute
    public Quit get(String id) {
        Quit quit = null;
        if (StringUtils.isNotBlank ( id )) {
            quit = quitService.get ( id );
        }
        if (quit == null) {
            quit = new Quit ();
        }
        return quit;
    }

    /**
     * 表单填写页面
     *
     * @param model
     * @param quit
     * @return 视图解析至add.jsp
     */
    @RequestMapping(value = "add")
    public String add(Model model, Quit quit) {
        quit.setUser ( UserUtils.getUser () );
        quit.setOffice ( UserUtils.getUser ().getOffice () );
        quit.setApplyDate ( new Date () );
        model.addAttribute ( "quit", quit );

        return "modules/quit/add";
    }

    /**
     * 提交表单信息
     *
     * @param file
     * @param quit
     * @param request
     * @return 重定向至@RequestMapping(value=list)方法
     * @throws ParseException
     */
    @RequestMapping(value = "toAdd")
    public String toAdd(@RequestParam("file") MultipartFile[] file, Quit quit, HttpServletRequest request) throws ParseException {
		/*String addressName="";
		for(int i=0;i<file.length;i++){
			String address=null;
			if (request instanceof MultipartHttpServletRequest) {
				String fileName = file[i].getOriginalFilename();
				if(StringUtils.isNoneBlank(fileName)){
					String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
					address = IdGen.uuid()+suffix;
					addressName+=address+"-";
					FileOutputStream fos = null;
					try {
						byte[] fileData = file[i].getBytes();
						fos = new FileOutputStream(Global.getConfig("quit")+address);
						fos.write(fileData);
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
		    }
		}
		quit.setAddress(addressName);*/
        quit.setUser ( UserUtils.getUser () );
        quit.setOffice ( UserUtils.getUser ().getOffice () );
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd" );
        quit.setApplyDate ( sdf.parse ( sdf.format ( new Date () ) ) );
        quit.setProneText ( null );
        quit.setPrtwoText ( null );
        quit.setPrthreeText ( null );
        quit.setPrfourText ( null );
        quit.setPrfiveText ( null );
        quit.setPrsixText ( null );
        quitService.save ( quit );
        return "redirect:" + adminPath + "/tb/quit/list";
    }

    /**
     * 文件上传功能
     *
     * @param file
     * @param request
     * @return 返回json串addressName
     */
    @ResponseBody
    @RequestMapping("upload")
    public String upload(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) {
        String addressName = "";
        for (int i = 0; i < file.length; i++) {
            String address = null;
            if (request instanceof MultipartHttpServletRequest) {
                String fileName = file[i].getOriginalFilename ();
                if (StringUtils.isNoneBlank ( fileName )) {
                    String suffix = fileName.substring ( fileName.lastIndexOf ( "." ) );//后缀名
                    address = IdGen.uuid () + suffix;
                    addressName += address + "-";
                    FileOutputStream fos = null;
                    try {
                        byte[] fileData = file[i].getBytes ();
                        fos = new FileOutputStream ( Global.getConfig ( "quit" ) + address );
                        fos.write ( fileData );
                        fos.close ();
                    } catch (IOException e) {
                        e.printStackTrace ();
                    }

                }
            }
        }
        return addressName;
    }

    /**
     * 列表页面
     *
     * @param model
     * @param quit
     * @param act
     * @param request
     * @param response
     * @return 视图解析至list.jsp
     */
    @RequestMapping(value = "list")
    public String list(Model model, Quit quit, Act act, HttpServletRequest request, HttpServletResponse response) {
        quit.setUser ( UserUtils.getUser () );
        Page<Quit> page = quitService.findPages ( new Page<Quit> ( request, response ), quit );
        model.addAttribute ( "page", page );
        return "modules/quit/list";
    }

    /**
     * 查看信息页面
     *
     * @param model
     * @param quit
     * @return 视图解析至变量view
     */
    @RequestMapping(value = "view")
    public String view(Model model, Quit quit) {
        String view = "view";
        if (StringUtils.isNotBlank ( quit.getId () )) {
            //获取环节ID
            String taskDefKey = quit.getAct ().getTaskDefKey ();
            if (quit.getAct ().isFinishTask ()) {
                view = "view";
            } else if ("userTask1".equals ( taskDefKey )
                    || "userTask2".equals ( taskDefKey )
                    || "userTask3".equals ( taskDefKey )
                    || "userTask4".equals ( taskDefKey )) {
                view = "audit";
            } else if ("modifyTask".equals ( taskDefKey )) {
                view = "add";
            }
        }
        model.addAttribute ( "quit", quit );
        return "modules/quit/" + view;
    }

    /**
     * 提交流程
     *
     * @param quit
     * @param model
     * @return
     */
    @RequestMapping(value = "saveAudit")
    public String saveAudit(Quit quit, Model model) {
        quitService.auditSave ( quit );
        //return "redirect:" + adminPath + "/act/task/todo?click";
        return "redirect:" + adminPath + "/act/task/todo";
    }

    /**
     * 查询所有数据信息
     *
     * @param model
     * @param request
     * @param response
     * @param quit
     * @return 视图解析至list2.jsp
     */
    @RequestMapping(value = "list2")
    public String list2(Model model, HttpServletRequest request, HttpServletResponse response, Quit quit) {
        quit.setUser ( UserUtils.getUser () );
        Page<Quit> page = quitService.findPages2 ( new Page<Quit> ( request, response ), quit );
        model.addAttribute ( "page", page );
        return "modules/quit/list2";
    }

    /**
     * 行政人员查看所属区域申请列表
     *
     * @param quit
     * @param act
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "list3")
    public String List3(Quit quit, Act act, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Quit> page = quitService.findPage3 ( new Page<Quit> ( request, response ), quit );
        model.addAttribute ( "page", page );
        return "modules/quit/list3";
    }

    /**
     * 财务商务人员查看员工离职信息列表
     *
     * @param quit
     * @param act
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "list4")
    public String List4(Quit quit, Act act, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Quit> page = quitService.findPage4 ( new Page<Quit> ( request, response ), quit );
        model.addAttribute ( "page", page );
        return "modules/quit/list4";
    }


    /**
     * 撤销流程
     *
     * @param quit
     * @return 重定向至@RequestMapping(value=list)方法
     */
    @RequestMapping(value = "back")
    public String back(Quit quit) {
        quitService.deleteQuit ( quit.getProcInsId () );
        quitService.deleteTask ( quit.getProcInsId () );
        return "redirect:" + adminPath + "/tb/quit/list";
    }

    /**
     * 文件下载（打成压缩包下载）
     *
     * @param quit
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("download")
    public String download(Quit quit, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String downloadPrefix = Global.getConfig ( "quit" );
        String[] strName = quit.getAddress ().split ( "\\-" );
        List<File> srcFiles = new ArrayList<File> ();
        for (int i = 0; i < strName.length; i++) {
            String url = downloadPrefix + strName[i];
            srcFiles.add ( new File ( url ) );
        }
        String zipUrl = downloadPrefix + quit.getUser ().getName () + "-离职申请" + ".zip";
        quit.setAddress ( quit.getUser ().getName () + "-离职申请" + ".zip" );
        File zipFile = new File ( zipUrl );
        //调用压缩方法
        zipFiles ( srcFiles, zipFile );
        // 清空response
        response.reset ();
        // 设置response的Header
        response.addHeader ( "Content-Disposition",
                "attachment;filename=" + new String ( quit.getAddress ().getBytes ( "gbk" ), "iso-8859-1" ) ); // 转码之后下载的文件不会出现中文乱码
        response.addHeader ( "Content-Length", "" + zipFile.length () );
        try {
            // 以流的形式下载文件
            InputStream is = new BufferedInputStream ( new FileInputStream ( zipUrl ) );
            byte[] buffer = new byte[is.available ()];
            is.read ( buffer );
            is.close ();
            OutputStream os = new BufferedOutputStream ( response.getOutputStream () );
            os.write ( buffer );
            os.flush ();
            os.close ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return null;
    }


    /**
     * 将文件压缩成zip
     *
     * @param srcFiles
     * @param zipFile
     */
    public static void zipFiles(List<File> srcFiles, File zipFile) {
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists ()) {
            try {
                zipFile.createNewFile ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;

        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream ( zipFile );
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream ( fileOutputStream );
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件集合
            for (int i = 0; i < srcFiles.size (); i++) {
                // 将源文件集合中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream ( srcFiles.get ( i ) );
                // 实例化 ZipEntry 对象，源文件数集合的当前文件
                zipEntry = new ZipEntry ( srcFiles.get ( i ).getName () );
                zipOutputStream.putNextEntry ( zipEntry );
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read ( buffer )) > 0) {
                    zipOutputStream.write ( buffer, 0, len );
                }
            }
            zipOutputStream.closeEntry ();
            zipOutputStream.close ();
            fileInputStream.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }
}
