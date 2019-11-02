package com.thinkgem.jeesite.modules.overtime.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.overtime.entity.DownloadOverTime;
import com.thinkgem.jeesite.modules.overtime.entity.OverTime;
import com.thinkgem.jeesite.modules.overtime.service.OverTimeService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/work/overtime")
public class OverTimeController extends BaseController {

    @Autowired
    private OverTimeService overTimeService;

    @ModelAttribute
    public OverTime get(@RequestParam(required = false) String id) {
        OverTime overTime = null;
        if (StringUtils.isNotBlank ( id )) {
            overTime = overTimeService.get ( id );
        }
        if (overTime == null) {
            overTime = new OverTime ();
        }
        return overTime;
    }

    @RequestMapping(value = {"list", ""})
    public String list(OverTime overTime, HttpServletRequest request, HttpServletResponse response, Model model) {
        overTime.setUser ( UserUtils.getUser () );
        Page<OverTime> page = overTimeService.findPage ( new Page<OverTime> ( request, response ), overTime );
        String loginName = UserUtils.getUser ().getLoginName ();
        if ("李国强".equals ( loginName ) || "麻青青".equals ( loginName ) || "王涛".equals ( loginName ) || "俞伶群".equals ( loginName )||"李晓萌".equals(loginName)||"方娜".equals(loginName)) {
            model.addAttribute ( "name", loginName );
        }
        getUrl ( page );
        model.addAttribute ( "page", page );
        return "modules/overtime/overTimeList";
    }

    @RequestMapping(value = "allList")
    public String allList(OverTime overTime, HttpServletRequest request, HttpServletResponse response, Model model) {
        overTime.setUser ( UserUtils.getUser () );
        String loginName = UserUtils.getUser ().getLoginName ();
        model.addAttribute ( "name", loginName );
        if ("李国强".equals ( loginName )) {
            Page<OverTime> page = overTimeService.findAllPage ( new Page<OverTime> ( request, response ), overTime );
            getUrl ( page );
            model.addAttribute ( "page", page );
        }
        if ("方娜".equals ( loginName )) {
            Page<OverTime> page = overTimeService.findAllPage ( new Page<OverTime> ( request, response ), overTime );
            getUrl ( page );
            model.addAttribute ( "page", page );
        }
        if("李晓萌".equals(loginName)){
            Page<OverTime> page =	overTimeService.findAllPage(new Page<OverTime>(request, response),overTime);
            getUrl(page);
            model.addAttribute("page", page);
        }
        if ("俞伶群".equals ( loginName )) {
            Page<OverTime> page = overTimeService.findAllPage ( new Page<OverTime> ( request, response ), overTime );
            getUrl ( page );
            model.addAttribute ( "page", page );
        }
        if ("麻青青".equals ( loginName )) {
            //设置标注值只能查看本地区人员
            overTime.setAnno ( 2 );
            Page<OverTime> page = overTimeService.findPage ( new Page<OverTime> ( request, response ), overTime );
            getUrl ( page );
            model.addAttribute ( "page", page );
        }
        if ("王涛".equals ( loginName )) {
            overTime.setAnno ( 1 );
            Page<OverTime> page = overTimeService.findPage ( new Page<OverTime> ( request, response ), overTime );
            getUrl ( page );
            model.addAttribute ( "page", page );
        }

        return "modules/overtime/overTimeList";
    }

    @RequestMapping(value = "form")
    public String form(OverTime overTime, Model model) {
        String view = "overTimeForm";
        //
        if (StringUtils.isNotBlank ( overTime.getId () )) {
            // 获取环节ID
            String taskDefKey = overTime.getAct ().getTaskDefKey ();
            // 查看申请
            if (overTime.getAct ().isFinishTask ()) {
                view = "overTimeView";
            }
            // 第一级审核人
            else if ("userTask1".equals ( taskDefKey )) {
                view = "overTimeAudit";
                //第二级审核人
            } else if ("userTask2".equals ( taskDefKey )) {
                view = "overTimeAudit";
            }
            // 修改请假申请
            else if ("modifyTask".equals ( taskDefKey )) {
                view = "overTimeForm";
            }
        } else {
            overTime.setUser ( UserUtils.getUser () );
            Office office = overTimeService.getOfficeByUserId ( UserUtils.getUser ().getId () );
            overTime.setOffice ( office );
        }
        model.addAttribute ( "overTime", overTime );
        return "modules/overtime/" + view;
    }

    //被驳回后的修改界面
    @RequestMapping(value = "form1")
    public String updateForm(OverTime overTime, Model model) {
        String view = "overTimeForm1";
        overTime.setUser ( UserUtils.getUser () );
        Office office = overTimeService.getOfficeByUserId ( UserUtils.getUser ().getId () );
        overTime.setOffice ( office );
        Double day = overTime.getDays ();
        String s = String.valueOf ( day );
        String[] s1 = s.split ( "." );
        if (s1.length != 0) {
            String number = s1[0];
            Double dou = Double.valueOf ( number );
            overTime.setDays ( dou );
        }
        model.addAttribute ( "overTime", overTime );
        return "modules/overtime/" + view;
    }

    @RequestMapping(value = "finishForm")
    public String finishForm(OverTime overTime, Model model, String procInsId) {
        overTime = overTimeService.getByProcInsId ( procInsId );
        model.addAttribute ( "overTime", overTime );
        return "modules/overtime/overTimeView";
    }

    @RequestMapping(value = "save")
    public String save(OverTime overTime, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator ( model, overTime )) {
            return form ( overTime, model );
        }
        //选择的上级领导
        //	String leader=request.getParameter("leader");
        //	overTime.setLeader(leader);
        overTimeService.save ( overTime );
        if ("yes".equals ( overTime.getAct ().getFlag () )) {
            addMessage ( redirectAttributes, "提交加班申请成功" );
        } else {
            addMessage ( redirectAttributes, "撤销加班申请成功" );
        }

        return "redirect:" + adminPath + "/work/overtime/list";
    }

    @RequestMapping(value = "saveAudit")
    public String saveAudit(OverTime overTime) {
        if (StringUtils.isBlank ( overTime.getAct ().getFlag () ) || StringUtils.isBlank ( overTime.getAct ().getComment () )) {
            overTime.setHrText ( "[同意]" );
        }
        overTimeService.auditSave ( overTime );

        //return "redirect:" + adminPath + "/act/task/todo?click";
        return "redirect:" + adminPath + "/act/task/todo";
    }

    private void getUrl(Page<OverTime> page) {
        for (OverTime ot : page.getList ()) {
            String status = ot.getStatus ();
            if ("审核中".equals ( status ) || "同意".equals ( status )) {
                ot.setUrl ( "form" );
            }
            if ("驳回".equals ( status )) {
                ot.setUrl ( "form1" );
            }
        }
    }


    /**
     * 查询本月的加班情况（审核通过）
     *
     * @param overTime
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "overTimeList2")
    public String monthAll(OverTime overTime, HttpServletRequest request, HttpServletResponse response, Model model) {
        Calendar cal = Calendar.getInstance ();
        /*获取当前时间的年份*/
        int y = cal.get ( Calendar.YEAR );
        /*获取当前时间的上一个月份*/
        int m = cal.get ( Calendar.MONTH );
        List<OverTime> list = overTimeService.monthAll ( String.valueOf ( y ), String.valueOf ( m ) );
        model.addAttribute ( "list", list );
        return "modules/overtime/overTimeList2";
    }

    /**
     * @Description: 导出上个月加班表格
     * @author: gangzi
     * @params: [startTime1, endTime1, userId1, response]
     * @return: java.lang.String
     * @date: 2019/8/20
     * @exception:
     */
    @ResponseBody
    @RequestMapping(value = "ExExcel")
    public String ExportCost(HttpServletResponse response, String userId1, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        try {
            String fileName = DateUtils.getDate ("yyyy_MM_dd_") + "上个月加班数据表" + ".xlsx";
            Calendar cal = Calendar.getInstance ();
            /*获取当前时间的年份*/
            int y = cal.get (Calendar.YEAR);
            /*获取当前时间的上一个月份*/
            int m = cal.get (Calendar.MONTH);

            List<DownloadOverTime> list = overTimeService.downList (String.valueOf (y), String.valueOf (m), userId1);
            model.addAttribute ("list", list);
            new ExportExcel ("上个月加班数据表", DownloadOverTime.class).setDataList (list).write (response, fileName)
                    .dispose ();
            return null;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return "redirect:" + Global.getAdminPath() + "/work/overtime/overTimeList2?repage";

    }

}
