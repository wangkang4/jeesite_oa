package com.thinkgem.jeesite.modules.CostExcel.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.CostExcel.entity.Client;
import com.thinkgem.jeesite.modules.CostExcel.entity.CostRecondExcel;
import com.thinkgem.jeesite.modules.CostExcel.entity.CostRecondExcelFile;
import com.thinkgem.jeesite.modules.CostExcel.entity.Project;
import com.thinkgem.jeesite.modules.CostExcel.service.ClientService;
import com.thinkgem.jeesite.modules.CostExcel.service.CostRecondExcelFileService;
import com.thinkgem.jeesite.modules.CostExcel.service.CostRecondExcelService;
import com.thinkgem.jeesite.modules.CostExcel.service.ProjectService;
import com.thinkgem.jeesite.modules.CostExcel.utils.ExcelUtil;
import com.thinkgem.jeesite.modules.CostExcel.utils.ImportExcelSheet;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 费用Controller
 *
 * @author tanchaoyang
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/costexcel")
public class CostRecondExcelController extends BaseController {

    @Autowired
    private CostRecondExcelService costRecondExcelService;

    @Autowired
    private CostRecondExcelFileService costRecondExcelFileService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProjectService projectService;

    /**
     * 根据筛选调教查找费用集合list
     *
     * @param costRecondExcel 在实体类的筛选条件
     * @param startTime1      一个时间段的开始时间
     * @param endTime1        一个时间段的结束时间
     */
    @RequestMapping(value = {"list", ""})
    public String list(CostRecondExcel costRecondExcel, String startTime1, String endTime1, HttpServletRequest request,
                       HttpServletResponse response, Model model) throws ParseException {
        /**时间格式**/
        DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" );

        if (StringUtils.isNotBlank ( startTime1 )) {
            costRecondExcel.setStartTime ( df.parse ( startTime1 ) );
        }
        if (StringUtils.isNotBlank ( endTime1 )) {
            costRecondExcel.setEndTime ( df.parse ( endTime1 ) );
        }
        /**界面下拉框获取客户集合**/
        List<Client> clientList = clientService.getClientList ();
        /**当前用户信息**/
        User user = UserUtils.getUser ();
        /**检索条件转化从id到name（费用表里有的字段）**/
        if (StringUtils.isNotBlank ( costRecondExcel.getUserId () )) {
            costRecondExcel.setUserName ( costRecondExcelService.getUserNameByUserId ( costRecondExcel.getUserId () ) );
        }
        if (StringUtils.isNotBlank ( costRecondExcel.getOffieId () )) {
            costRecondExcel.setOffieName ( costRecondExcelService.getOffieNameByOffie ( costRecondExcel.getOffieId () ) );
        }
        /**部门权限设置**/
        if (!user.getOffice ().getName ().equals ( "财务部" )) {//如果不是财务部（财务部看所有人，部门经理看部门，普通用户看自己）
            if (user.getLeaderid () == 0) {                    //如果不是该部门领导
                costRecondExcel.setOffieId ( "" );     //至空部门检索条件
            }
            if (user.getLeaderid () == 1 && StringUtils.isNotBlank ( costRecondExcel.getUserId () )) {  //是部门领导且人员检索条件不为空
                costRecondExcel.setOffieId ( "" );                                //至空部门检索条件
                user.setId ( costRecondExcel.getUserId () );
            }
            costRecondExcel.setCreaterBy ( user );      //普通用户set当前用户信息
        }
        /**检索条件转化从id到name（费用表里有的字段）**/
        if (StringUtils.isNotBlank ( costRecondExcel.getClientId () )) {
            costRecondExcel.setClientName ( clientService.getClientName ( costRecondExcel.getClientId () ) );// 通过客户id查找客户名
        }
        if (StringUtils.isNotBlank ( costRecondExcel.getProjectId () )) {
            costRecondExcel.setProjectName ( projectService.getProjectName ( costRecondExcel.getProjectId () ) );// 通过项目id查找项目名
        }
        /**费用List查询**/
        Page<CostRecondExcel> page = costRecondExcelService.findPage ( new Page<CostRecondExcel> ( request, response ),
                costRecondExcel );
        model.addAttribute ( "page", page );
        model.addAttribute ( "clientList", clientList );
        model.addAttribute ( "costRecondExcel", costRecondExcel );
        int flag = 0;
        /**费用锁定权限判定**/
        if (user.getOffice ().getName ().equals ( "财务部" )) {
            flag = 1;
        }
        model.addAttribute ( "flag", flag );
        return "modules/CostExcel/CostExcelList";
    }

    /**
     * 文件上传界面跳转
     *
     * @return
     */
    @RequestMapping(value = "uploadexcelform")
    public String uploadexcelform(Model model) {
        User user = UserUtils.getUser ();
        model.addAttribute ( "user", user );
        return "modules/CostExcel/CostExceluoploadForm";
    }

    /**
     * 导入Excel并存储数据库
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "uploadExcel")
    public String uploadExcel(HttpServletRequest request, RedirectAttributes redirect) {

        // flag为 success 则为上传 并处理成功 非success 则为失败原因
        String flag = "success";

        try {
            // 获取文件
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartHttpServletRequest.getFile ( "upfile" );
            if (file.isEmpty ()) {
                throw new Exception ( "文件不存在！" );
            }
            // 用于判断文件类型
            ExcelUtil excelUtil = new ExcelUtil ();
            // 用于读取excel数据
            ImportExcelSheet importExcelSheet = new ImportExcelSheet ();
            importExcelSheet.read ( file.getInputStream (), excelUtil.getExcelFormat ( file.getOriginalFilename () ) );
            // 获取excel所有sheet的集合
            Map<String, List<List<String>>> map = importExcelSheet.getMap ();

            // 校验sheet中数据的合法性 不校验 是否存在sheet 或者sheet中的数据是否为空

            String[] shouldCheckSheetNames = {"销售", "技术", "行政"};
            for (String sheetName : shouldCheckSheetNames) {

                String errorInfo = checkCommonInsert ( map, sheetName );
                if (!"".equals ( errorInfo )) {

                    flag = errorInfo;
                    return flag;
                }
            }

            // 用来判断 文件的有效性 如果非垃圾文件 则在web端存储
            boolean flag2 = false;
            if (map.containsKey ( "销售" )) {
                flag2 = true;
                List<CostRecondExcel> costRecondExcelList = ExcelUtil.commonInsert ( map, "销售", 1 );
                if (costRecondExcelList != null && costRecondExcelList.size () > 0) {

                    costRecondExcelService.insertList ( costRecondExcelList );
                }
            }
            if (map.containsKey ( "技术" )) {
                List<CostRecondExcel> costRecondExcelList = ExcelUtil.commonInsert ( map, "技术", 2 );
                flag2 = true;
                if (costRecondExcelList != null && costRecondExcelList.size () > 0) {

                    costRecondExcelService.insertList ( costRecondExcelList );
                }
            }
            if (map.containsKey ( "行政" )) {
                flag2 = true;
                List<CostRecondExcel> costRecondExcelList = ExcelUtil.commonInsert ( map, "行政", 3 );
                if (costRecondExcelList != null && costRecondExcelList.size () > 0) {

                    costRecondExcelService.insertList ( costRecondExcelList );
                }
            }

            if (flag2) {
                // 上传文件，并保存路径到数据库
                Iterator<String> iter = multipartHttpServletRequest.getFileNames ();
                while (iter.hasNext ()) {
                    // 一次遍历所有文件
                    MultipartFile file2 = multipartHttpServletRequest.getFile ( iter.next ().toString () );
                    if (file2 != null) {
                        CostRecondExcelFile costRecondExcelFile = new CostRecondExcelFile ();
                        // 重命名文件名
                        String rename = ExcelUtil.rename ( file2.getOriginalFilename () );
                        String path = Global.getConfig ( "uploadPath" ) + rename;
                        costRecondExcelFile.setId ( IdGen.uuid () );
                        costRecondExcelFile.setFileName ( file2.getOriginalFilename () );
                        costRecondExcelFile.setFilePath ( path );
                        costRecondExcelFile.setState ( String.valueOf ( 1 ) );
                        costRecondExcelFile.setFileSize ( String.valueOf ( file2.getSize () ) );
                        costRecondExcelFile.setCreaterid ( UserUtils.getUser ().getLoginName () );
                        Date now = new Date ();
                        costRecondExcelFile.setCreatertime ( now );
                        file2.transferTo ( new File ( path ) );
                        costRecondExcelFileService.insertone ( costRecondExcelFile );
                    }
                }
            } else {
                flag = "模板不正确或无有效数据！";
            }

        } catch (Exception e) {

            e.printStackTrace ();
            flag = "上传excel出现异常，请联系管理员排除错误:" + e.toString ();
        }
        return flag;
    }

    // return "" 表示校验通过 非 "" 为失败原因
    // 不校验sheet的非空与sheet的数据是否为空
    public String checkCommonInsert(Map<String, List<List<String>>> map, String sheetName) {

        List<List<String>> list = map.get ( sheetName );// 获取指定sheet的List<List<String>>

        if (list != null && list.size () > 0) {

            for (int i = 1; i < list.size (); i++) {

                List<String> lo = list.get ( i );

                String clientName = String.valueOf ( lo.get ( 0 ) );
                if (StringUtils.isEmpty ( clientName )) {

                    return "失败->sheet:" + sheetName + " 行数:" + String.valueOf ( i ) + " 客户名称数据为空";

                } else {

                    String clientId = clientService.getClientId ( clientName );

                    if (StringUtils.isBlank ( clientId )) {

                        return "失败->sheet:" + sheetName + " 行数:" + String.valueOf ( i ) + " 客户名称:" + clientName
                                + "  在系统中不存在，请联系管理员添加";
                    }
                }

                String projectName = String.valueOf ( lo.get ( 1 ) );
                if (StringUtils.isEmpty ( projectName )) {

                    return "失败->sheet:" + sheetName + " 行数:" + String.valueOf ( i ) + " 项目名称数据为空";

                } else {

                    String projectId = projectService.getProjectId ( projectName );

                    if (StringUtils.isBlank ( projectId )) {

                        return "失败->sheet:" + sheetName + " 行数:" + String.valueOf ( i ) + " 项目名称名称:" + clientName
                                + "  在系统中不存在，请联系管理员添加";
                    }
                }
            }
        }
        return "";
    }

    /**
     * 下载费用Excel模板
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "downexcelcopy")
    public String downexcelcopy(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType ( "application/vnd.ms-excel" );
        String fileName = "费用统计模板.xlsx";
        String filebefore = "templates.xlsx";
        String nowPath = Global.getConfig ( "uploadPath" ) + filebefore;
//		String nowPath = "D:/"+filebefore;
        File file = new File ( nowPath );
        // 清空response
        response.reset ();
        // 设置response的Header
        response.addHeader ( "Content-Disposition",
                "attachment;filename=" + new String ( fileName.getBytes ( "gbk" ), "iso-8859-1" ) ); // 转码之后下载的文件不会出现中文乱码
        response.addHeader ( "Content-Length", "" + file.length () );

        try {
            // 以流的形式下载文件
            InputStream fis = new BufferedInputStream ( new FileInputStream ( nowPath ) );
            byte[] buffer = new byte[fis.available ()];
            fis.read ( buffer );
            fis.close ();

            OutputStream toClient = new BufferedOutputStream ( response.getOutputStream () );
            toClient.write ( buffer );
            toClient.flush ();
            toClient.close ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return null;
    }

    /**
     * 导出数据到Excel
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "ExExcel")
    public String ExportCost(String type, String clientId, String projectId, String conment,
                             String startTime1, String endTime1, String userId1, String offieId1, HttpServletRequest request, HttpServletResponse response,
                             RedirectAttributes redirectAttributes) {
        try {
            String fileName = "费用统计" + DateUtils.getDate ( "yyyyMMddHHmmss" ) + ".xlsx";
            User user = UserUtils.getUser ();
            //界面筛选条件存入实体类
            CostRecondExcel costRecondExcel = new CostRecondExcel ();
            costRecondExcel.setClientId ( clientId );
            costRecondExcel.setProjectId ( projectId );
            costRecondExcel.setConment ( conment );
            DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" );
            if (StringUtils.isNotBlank ( startTime1 )) {
                costRecondExcel.setStartTime ( df.parse ( startTime1 ) );
            }
            if (StringUtils.isNotBlank ( endTime1 )) {
                costRecondExcel.setEndTime ( df.parse ( endTime1 ) );
            }
            if (StringUtils.isNotBlank ( type )) {
                costRecondExcel.setType ( Integer.parseInt ( type ) );
            }
            if (StringUtils.isNotBlank ( costRecondExcel.getClientId () )) {
                costRecondExcel.setClientName ( clientService.getClientName ( costRecondExcel.getClientId () ) );// 通过客户id查找客户名
            }
            if (StringUtils.isNotBlank ( costRecondExcel.getProjectId () )) {
                costRecondExcel.setProjectName ( projectService.getProjectName ( costRecondExcel.getProjectId () ) );// 通过项目id查找项目名
            }
            costRecondExcel.setUserId ( userId1 );
            costRecondExcel.setOffieId ( offieId1 );

            //权限判定
            if (!user.getOffice ().getName ().equals ( "财务部" )) {
                if (user.getLeaderid () == 0) {
                    costRecondExcel.setOffieId ( "" );
                }
                if (user.getLeaderid () == 1 && StringUtils.isNotBlank ( costRecondExcel.getUserId () )) {
                    costRecondExcel.setOffieId ( "" );
                    user.setId ( costRecondExcel.getUserId () );
                }
                costRecondExcel.setCreaterBy ( user );
            }

            Page<CostRecondExcel> page = costRecondExcelService
                    .findPage ( new Page<CostRecondExcel> ( request, response, -1 ), costRecondExcel );
            new ExportExcel ( "费用统计", CostRecondExcel.class ).setDataList ( page.getList () ).write ( response, fileName )
                    .dispose ();
            return null;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return "redirect:" + Global.getAdminPath () + "/oa/costexcel/?repage";
    }

    /**
     * 费用统计修改页面
     *
     * @return
     */
    @RequestMapping(value = "costmessagefrom")
    public String costmessagefrom(String id, CostRecondExcel costRecondExcel, Model model) {
        //获取所有客户集合
        List<Client> clientList = clientService.getClientList ();
        if (StringUtils.isNotBlank ( id )) {
            costRecondExcel = costRecondExcelService.getone ( id );
            costRecondExcel.setClientId ( clientService.getClientId ( costRecondExcel.getClientName () ) );// 通过客户名字查找客户id
            costRecondExcel.setProjectId ( projectService.getProjectId ( costRecondExcel.getProjectName () ) );// 通过项目名称查找项目id
            model.addAttribute ( "costRecondExcel", costRecondExcel );
            model.addAttribute ( "clientList", clientList );
            return "modules/CostExcel/CostExceluoploadListForm";
        } else {
            String costid = IdGen.uuid ();
            Date now = new Date ();
            model.addAttribute ( "now", now );
            model.addAttribute ( "costid", costid );
            model.addAttribute ( "clientList", clientList );
            return "modules/CostExcel/CostExceluoploadListAdd";
        }
    }

    /**
     * 费用统计更新
     *
     * @return
     */
    @RequestMapping(value = "costmessageupdate")
    public String costmessageupdate(CostRecondExcel costRecondExcel, String useTime1) throws Exception {
        DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" );
        costRecondExcel.setUseTime ( df.parse ( useTime1 ) );
        if (StringUtils.isNotBlank ( costRecondExcel.getClientId () )) {
            costRecondExcel.setClientName ( clientService.getClientName ( costRecondExcel.getClientId () ) );// 通过客户id查找客户名
        }
        if (StringUtils.isNotBlank ( costRecondExcel.getProjectId () )) {
            costRecondExcel.setProjectName ( projectService.getProjectName ( costRecondExcel.getProjectId () ) );// 通过项目id查找项目名
        }
        costRecondExcelService.updateone ( costRecondExcel );
        return "redirect:" + Global.getAdminPath () + "/oa/costexcel/?repage";
    }

    /**
     * 费用统计添加
     *
     * @return
     */
    @RequestMapping(value = "costmessageinsert")
    public String costmessageinsert(CostRecondExcel costRecondExcel, Model model, String useTime1) throws Exception {
        DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" );
        costRecondExcel.setUseTime ( df.parse ( useTime1 ) );
        costRecondExcel.setCreaterBy ( UserUtils.getUser () );
        costRecondExcel.setCreaterTime ( new Date () );
        costRecondExcel.setClientName ( clientService.getClientName ( costRecondExcel.getClientId () ) );// 通过客户id查找客户名
        costRecondExcel.setProjectName ( projectService.getProjectName ( costRecondExcel.getProjectId () ) );// 通过项目id查找项目名
        costRecondExcelService.insertone ( costRecondExcel );
        return "redirect:" + Global.getAdminPath () + "/oa/costexcel/?repage";
    }

    /**
     * 费用统计删除
     *
     * @return
     */
    @RequestMapping(value = "costmessagedelete")
    public String costmessagedelete(String id, CostRecondExcel costRecondExcel, Model model,
                                    RedirectAttributes redirectAttributes) {
        costRecondExcelService.deleteone ( id );
        return "redirect:" + Global.getAdminPath () + "/oa/costexcel/?repage";
    }

    /**
     * ajax获取客户下所有项目
     *
     * @param clientId 客户id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getProjectList")
    public List<Project> getProjectList(String clientId) {
        List<Project> list = projectService.getProjectList ( clientId );
        return list;
    }

    /**
     * 所有某条费用
     *
     * @param id      费用id
     * @param haveSee 锁定标识
     * @return
     */
    @RequestMapping(value = "haveSee")
    public String haveSee(String id, String haveSee) {
        CostRecondExcel costRecondExcel = new CostRecondExcel ();
        costRecondExcel.setId ( id );
        costRecondExcel.setHaveSee ( Integer.parseInt ( haveSee ) );
        costRecondExcelService.updateHaveSee ( costRecondExcel );
        return "redirect:" + Global.getAdminPath () + "/oa/costexcel/?repage";
    }

}
