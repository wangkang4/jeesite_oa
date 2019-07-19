package com.thinkgem.jeesite.modules.leader.controller;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.daily.entity.TCheckBacklog;
import com.thinkgem.jeesite.modules.daily.service.TCheckBacklogService;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.weekly.entity.FileModel;
import com.thinkgem.jeesite.modules.weekly.service.WeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/leader/leaderPage")
public class LeaderController extends BaseController {

    @Autowired
    private TCheckBacklogService tCheckBacklogService;

    @Autowired
    private WeeklyService weeklyService;
    @Autowired
    private OaNotifyService oaNotifyService;

    @RequestMapping(value = "index")
    public String index(OaNotify oaNotify, TCheckBacklog tCheckBacklog, Model model, HttpServletRequest request, HttpServletResponse response) {

        int num = 24;
        int dailyNotCount = 0;
        int weeklyNotCount = 0;
        // 获取日报上交总数；
        int dailyCount = tCheckBacklogService.getNum ();
        // 获取周报上交总数
        int weeklyCount = weeklyService.getWeekNum ();
        if (dailyCount <= num) {
            dailyNotCount = num - dailyCount;
        }
        if (weeklyCount <= num) {
            weeklyNotCount = num - weeklyCount;
        }
        oaNotify.setSelf ( true );
        Page<OaNotify> page = oaNotifyService.find ( new Page<OaNotify> ( request, response ), oaNotify );
        List<FileModel> filelist = oaNotifyService.findfiles ( oaNotify.getId () );
        model.addAttribute ( "filelist", filelist );
        model.addAttribute ( "page", page );

        tCheckBacklog.setUser ( UserUtils.getUser () );
        tCheckBacklog.setTypes ( 1 );
        model.addAttribute ( "tCheckBacklog", tCheckBacklog );

        model.addAttribute ( "dailyNotCount", dailyNotCount );
        model.addAttribute ( "weeklyNotCount", weeklyNotCount );
        model.addAttribute ( "dailyCount", dailyCount );
        model.addAttribute ( "weeklyCount", weeklyCount );
        return "modules/leader/1";
    }

}
