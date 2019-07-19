package com.thinkgem.jeesite.modules.daily.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.daily.entity.TCheckBacklog;
import com.thinkgem.jeesite.modules.daily.entity.TDaily;
import com.thinkgem.jeesite.modules.daily.service.TCheckBacklogService;
import com.thinkgem.jeesite.modules.daily.service.TDailyService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "${adminPath}/daily/tDaily")
public class TDailyController extends BaseController {

    @Autowired
    private TDailyService tDailyService;

    @Autowired
    private TCheckBacklogService tCheckBacklogService;

    @ModelAttribute
    public TDaily get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank ( id )) {
            return tDailyService.get ( id );
        } else {
            return new TDaily ();
        }
    }

    @RequestMapping("list")
    public String list(TDaily tDaily, TCheckBacklog tCheckBacklog, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        tDaily.setUser ( UserUtils.getUser () );// 将当前登录user填充进tDaily作为user_id
        Page<TDaily> page = tDailyService.findPage ( new Page<TDaily> ( request, response ), tDaily );
        model.addAttribute ( "page", page );
        return "modules/daily/list";
    }

    @RequestMapping("form")
    public String form(String id, TDaily tDaily, Model model) {
        // 当id不为空时，读取这个记录并传送到前台
        if (StringUtils.isNotBlank ( id )) {
            tDaily = new TDaily ();
            tDaily.setId ( id );
            tDaily = tDailyService.get ( tDaily );
            model.addAttribute ( "tDaily", tDaily );
            return "modules/daily/form2";
        }
        Date now = new Date ();
        tDaily.setDayTime ( now );// 此时间作为前台显示的默认时间
        model.addAttribute ( "tDaily", tDaily );
        return "modules/daily/form";
    }

    @RequestMapping("save")
    public String save(TDaily tDaily, RedirectAttributes redirectAttributes) throws ParseException {
        DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" );
        Date now = new Date ();
        tDaily.setCreaterTime ( now );

        String dayTime = df.format ( now );
        tDaily.setDayTime ( df.parse ( dayTime ) );

        String str = tDaily.getUser ().getId ();// 获取收报人的id，并拆分为String数组，逐个插入中间表
        String[] userId = str.split ( "," );

        tDaily.setRemarks ( tDaily.getUser ().getName () );// 用于前台显示收报人姓名
        tDaily.setUser ( UserUtils.getUser () );// 用于插入记录creater_id即user.id
        tDailyService.save ( tDaily );

        for (String string : userId) {
            TCheckBacklog tCheckBacklog = new TCheckBacklog ();
            tCheckBacklog.setTypes ( 1 );// 业务类型
            tCheckBacklog.setProfId ( tDaily.getId () ); // 日报ID
            tCheckBacklog.setCreaterId ( tDaily.getCreateBy ().getId () );// 用户ID
            tCheckBacklog.setCreaterTime ( tDaily.getCreaterTime () );// 创建时间

            User userTest = new User ();
            userTest.setId ( string );// 往中间表插入记录：收报人的id
            tCheckBacklog.setUser ( userTest );
            tCheckBacklogService.save ( tCheckBacklog );
        }

        return "redirect:" + Global.getAdminPath () + "/daily/tDaily/list";
    }

    @RequestMapping("update")
    public String update(TDaily tDaily, RedirectAttributes redirectAttributes) {
        // 先保存日报记录
        tDailyService.update ( tDaily );
        // 如果用户改变收报人，则先删除原有记录，在插入新纪录
        if (StringUtils.isNotBlank ( tDaily.getUser ().getId () )) {
            // 删除原有记录
            TCheckBacklog tCheckBacklog = new TCheckBacklog ();
            tCheckBacklog.setProfId ( tDaily.getId () );
            tCheckBacklogService.delete ( tCheckBacklog );

            TDaily tDaily2 = tDailyService.get ( tDaily.getId () );
            String str = tDaily.getUser ().getId ();
            String[] userId = str.split ( "," );

            for (String string : userId) {
                tCheckBacklog.setTypes ( 1 );// 业务类型
                tCheckBacklog.setProfId ( tDaily2.getId () ); // 日报ID
                tCheckBacklog.setCreaterId ( tDaily2.getCreateBy ().getId () );// 用户ID
                tCheckBacklog.setCreaterTime ( tDaily2.getCreaterTime () );// 创建时间

                User userTest = new User ();
                userTest.setId ( string );
                tCheckBacklog.setUser ( userTest );
                tCheckBacklogService.save ( tCheckBacklog );
            }
            tDaily.setRemarks ( tDaily.getUser ().getName () );
            tDailyService.update ( tDaily );
        }
        return "redirect:" + Global.getAdminPath () + "/daily/tDaily/list";
    }

    @RequestMapping("delete")
    public String delete(TDaily tDaily, RedirectAttributes redirectAttributes) {

        tDailyService.delete ( tDaily );
        TCheckBacklog tCheckBacklog = new TCheckBacklog ();
        tCheckBacklog.setProfId ( tDaily.getId () );
        tCheckBacklogService.delete ( tCheckBacklog );

        return "redirect:" + Global.getAdminPath () + "/daily/tDaily/list";
    }

}
