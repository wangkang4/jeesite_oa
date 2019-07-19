package com.thinkgem.jeesite.modules.leave.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.leave.entity.ActivityLeave2;
import com.thinkgem.jeesite.modules.leave.service.ActivityLeave2Service;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/activity/leave2")
public class ActivityLeave2Controller extends BaseController {

    @Autowired
    private ActivityLeave2Service activityLeave2Service;

    @Autowired
    private SystemService systemService;

    private String address;

    private String filename;

    @ModelAttribute
    public ActivityLeave2 get(@RequestParam(required = false) String id) {
        ActivityLeave2 activityLeave2 = null;
        if (StringUtils.isNotBlank ( id )) {
            activityLeave2 = activityLeave2Service.get ( id );
        }
        if (activityLeave2 == null) {
            activityLeave2 = new ActivityLeave2 ();
        }
        return activityLeave2;
    }

    @RequestMapping(value = {"list", ""})
    public String list(ActivityLeave2 activityLeave2, HttpServletRequest request, HttpServletResponse response,
                       Model model) {
        String userName = UserUtils.getUser ().getLoginName ();
        activityLeave2.setUser ( UserUtils.getUser () );
        Page<ActivityLeave2> page = activityLeave2Service.findPage ( new Page<ActivityLeave2> ( request, response ),
                activityLeave2 );
        List<ActivityLeave2> list = page.getList ();
        for (ActivityLeave2 act : list) {
            String status = act.getStatus ();
            String proinstId = act.getProcInsId ();
            if ("审核中".equals ( status ) || "审核通过".equals ( status )) {
                act.setUrl ( "form" );
            } else {
                act.setUrl ( "form1" );
            }
        }
        if ("李国强".equals ( userName ) || "麻青青".equals ( userName ) || "王涛".equals ( userName )) {
            model.addAttribute ( "name", userName );
        }
        model.addAttribute ( "page", page );
        return "modules/leave/activityLeave2List";
    }

    @RequestMapping(value = "form")
    public String form(ActivityLeave2 activityLeave2, Model model) {
        //查询该用户的可用请假天数
        User user = UserUtils.getUser ();
        double use = systemService.selectUseOverTimeById ( user );
        String userName = UserUtils.getUser ().getName ();
        if ("李国强".equals ( userName ) || "麻青青".equals ( userName ) || "王涛".equals ( userName ) || "俞伶群".equals ( userName )) {
            model.addAttribute ( "name", userName );
        }
        String view = "activityLeave2Form";
        if (StringUtils.isNotBlank ( activityLeave2.getId () )) {
            // 获取环节ID
            String taskDefKey = activityLeave2.getAct ().getTaskDefKey ();
            // 查看申请
            if (activityLeave2.getAct ().isFinishTask ()) {
                view = "activityLeave2View";
            }
            // 项目经理审核
            else if ("userTask1".equals ( taskDefKey )) {
                view = "activityLeave2Audit";
            }
            //地区主管审核
            else if ("userTask2".equals ( taskDefKey )) {
                view = "activityLeave2Audit";
            }
            // 修改请假申请
            else if ("modifyTask".equals ( taskDefKey )) {
                view = "activityLeave2Form";
            }
        } else {
            activityLeave2.setUser ( UserUtils.getUser () );
            Office office = activityLeave2Service.getOfficeByUserId ( UserUtils.getUser ().getId () );
            activityLeave2.setOffice ( office );
            if (activityLeave2.getUser () != null) {
                activityLeave2.getUser ().setUseOverTimeDays ( use );
            }
        }
        model.addAttribute ( "activityLeave2", activityLeave2 );
        return "modules/leave/" + view;
    }

    @RequestMapping(value = "form1")
    public String updateForm(ActivityLeave2 activityLeave2, Model model) {
        activityLeave2.setUser ( UserUtils.getUser () );
        Office office = activityLeave2Service.getOfficeByUserId ( UserUtils.getUser ().getId () );
        activityLeave2.setOffice ( office );
        String view = "activityLeave2Form1";
        Double day = activityLeave2.getDays ();
        Double days = day / 8;
        activityLeave2.setDays ( days );
        model.addAttribute ( "activityLeave2", activityLeave2 );
        return "modules/leave/" + view;
    }

    @RequestMapping(value = {"allList"})
    public String allList(ActivityLeave2 activityLeave2, HttpServletRequest request, HttpServletResponse response,
                          Model model) {
        String userName = UserUtils.getUser ().getName ();
        if ("李国强".equals ( userName )) {
            Page<ActivityLeave2> page = activityLeave2Service.findAllPage ( new Page<ActivityLeave2> ( request, response ), activityLeave2 );
            model.addAttribute ( "page", page );
        }
        if ("俞伶群".equals ( userName )) {
            Page<ActivityLeave2> page = activityLeave2Service.findAllPage ( new Page<ActivityLeave2> ( request, response ), activityLeave2 );
            model.addAttribute ( "page", page );
        }
        //是麻青青查看查询济南市场办
        if ("麻青青".equals ( userName )) {
            activityLeave2.setA ( 2 );
            activityLeave2.setUser ( UserUtils.getUser () );
            Page<ActivityLeave2> page = activityLeave2Service.findPage ( new Page<ActivityLeave2> ( request, response ),
                    activityLeave2 );
            model.addAttribute ( "page", page );
        }
        //是王涛查询山西市场办
        if ("王涛".equals ( userName )) {
            activityLeave2.setA ( 1 );
            activityLeave2.setUser ( UserUtils.getUser () );
            Page<ActivityLeave2> page = activityLeave2Service.findPage ( new Page<ActivityLeave2> ( request, response ),
                    activityLeave2 );
            model.addAttribute ( "page", page );
        }
        model.addAttribute ( "name", userName );
        return "modules/leave/activityLeave2List";
    }

    @RequestMapping(value = "finishForm")
    public String finishForm(ActivityLeave2 activityLeave2, Model model, String procInsId) {
        activityLeave2 = activityLeave2Service.getByProcInsId ( procInsId );
        model.addAttribute ( "activityLeave2", activityLeave2 );
        return "modules/leave/activityLeave2View";
    }

    @RequestMapping(value = "save")
    public String save(String attachAddress, ActivityLeave2 activityLeave2, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator ( model, activityLeave2 )) {
            return form ( activityLeave2, model );
        }
        activityLeave2.setAttachName ( filename );
        activityLeave2.setAttachAddress ( address );
        activityLeave2Service.save ( activityLeave2 );
        addMessage ( redirectAttributes, "提交请假申请成功" );
        return "redirect:" + adminPath + "/activity/leave2/list";
        /* return "redirect:"+adminPath+"/act/task/todo"; */
    }

    @RequestMapping(value = "saveAudit")
    public String saveAudit(ActivityLeave2 activityLeave2, Model model) {
        if (StringUtils.isBlank ( activityLeave2.getAct ().getFlag () )
                || StringUtils.isBlank ( activityLeave2.getAct ().getComment () )) {
            activityLeave2.setHrText ( "[同意]" );
        }
        activityLeave2Service.auditSave ( activityLeave2 );
        // 如果审核成功，更新可用请假天数
        String opinion = activityLeave2.getHrText ();
        //获取提交的请假时间
        double days = activityLeave2.getDays ();
        User user = activityLeave2.getUser ();
        //查询该用户的可用请假天数
        double use = systemService.selectUseOverTimeById ( user );
        //查询用户当前总请假天数
        double numLeave = systemService.selectNumLeaveDays ( user );
        boolean bool = opinion != null && opinion.startsWith ( "[同意]" );
        String type = activityLeave2.getLeaveType ();
        if (bool) {
            double allLeave = days + numLeave;
            user.setNumLeaveDays ( allLeave );
            //更新总的请假天数
            systemService.updateNumLeaveDays ( user );
        }
        //请假类型为调休
        if ("1".equals ( type )) {
            //如果审批同意
            if (bool) {

                //如果可用请假天数大于请假天数
                if (use > days) {
                    //计算剩余可用请假天数
                    double useDays = use - days;
                    //设置已经消除的请假天数
                    activityLeave2.setRemoveDays ( days );
                    //设置当前的可用请假天数
                    user.setUseOverTimeDays ( useDays );
                    //更新可用的请假天数
                    systemService.updateUseOverTimeById ( user );
                    //更新已经消除的请假天数
                    activityLeave2Service.updateRemoveDays ( activityLeave2 );
                } else {
                    //如果可用天数小于当前请假天数
                    //那么可用天数就是当前的销假天数
                    activityLeave2.setRemoveDays ( use );
                    //把可用请假天数清空
                    user.setUseOverTimeDays ( 0 );
                    //更新可用请假天数
                    systemService.updateUseOverTimeById ( user );
                    //更新消除的请假天数
                    activityLeave2Service.updateRemoveDays ( activityLeave2 );
                }
            }
        }
        //return "redirect:" + adminPath + "/act/task/todo?click";
        return "redirect:" + adminPath + "/act/task/todo";
    }


    //添加文件下载功能
    @RequestMapping(value = "download")
    public Map<String, String> fileDownload(String attachAddress, HttpServletRequest request, HttpServletResponse response) {
        String attachName = activityLeave2Service.findAttachNameByAttachAddress ( attachAddress );
        String[] str = attachName.split ( "\\." );
        String suffix = str[1];
        String downloadPrefix = Global.getConfig ( "windowsUploadPath" );
        String url = downloadPrefix + attachAddress + "." + suffix;
        File file = new File ( url );
        //清空reponse
        response.reset ();
        //设置response的Header
        try {
            response.addHeader ( "Content-Disposition", "attachment;filename=" + new String ( attachName.getBytes ( "gbk" ), "iso-8859-1" ) );
            response.addHeader ( "Content-Length", "" + file.length () );
            //以流的形式下载文件
            InputStream is = new BufferedInputStream ( new FileInputStream ( url ) );
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


    //添加文件上传功能
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> fileUpload(@RequestParam MultipartFile attach, HttpServletRequest request) throws IllegalStateException, IOException {
        Map<String, String> map = new HashMap<String, String> ();
        String attachAddress = IdGen.uuid (); //随机不重复地址
        filename = attach.getOriginalFilename ();  //获取文件名
        String[] str = filename.split ( "\\." );
        String suffix = str[1];
        //输出实际路径
        String uploadHome = Global.getConfig ( "windowsUploadPath" );
        String path = uploadHome + attachAddress + "." + suffix;
        File file = new File ( path );
        attach.transferTo ( file );
        address = attachAddress;
        map.put ( "data", "上传成功" );
        return map;
    }

    // @RequestMapping(value = "delete")
    // public String delete(ActivityLeave activityLeave, RedirectAttributes
    // redirectAttributes) {
    // activityLeaveService.delete(activityLeave);
    // addMessage(redirectAttributes, "删除审批成功");
    // return "redirect:" + adminPath + "/activity/leave/?repage";
    // }


}
