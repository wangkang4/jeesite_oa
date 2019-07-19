package com.thinkgem.jeesite.modules.leave.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.leave.entity.ActivityLeave2;
import com.thinkgem.jeesite.modules.leave.service.LeaAndOveService;
import com.thinkgem.jeesite.modules.overtime.entity.OverTime;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/activity/leaAndove")
public class LeaAndOveController extends BaseController {

    @Autowired
    private LeaAndOveService leaAndOveService;

    @RequestMapping(value = "list")
    public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<User> page = leaAndOveService.findUserPage ( new Page<User> ( request, response ), user );
        if (StringUtils.isNotBlank ( user.getId () )) {
            user.setName ( leaAndOveService.findUsername ( user.getId () ) );
        }
        for (User user1 : page.getList ()) {
            System.out.println ( "user1:" + user1.getUseOverTimeDays () );
            System.out.println ( "user1:" + user1.getNumOverTimeDays () );
            System.out.println ( "user1:" + user1.getNumLeaveDays () );
        }
        model.addAttribute ( "user", user );
        model.addAttribute ( "page", page );
        return "modules/overtime/historyLeaAndOve";
    }

    @RequestMapping(value = "overList")
    public String OverTimelist(String id, OverTime overTime, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (StringUtils.isNotBlank ( id )) {
            User user = new User ();
            user.setId ( id );
            overTime.setUser ( user );
        }
        Page<OverTime> page = leaAndOveService.findOverTimePage ( new Page<OverTime> ( request, response ), overTime );
        List<OverTime> list = page.getList ();
        for (OverTime ove : list) {
            System.out.println ( "ove:" + ove.getStatus () );
            System.out.println ( "ove:" + ove.getStartTime () );
            System.out.println ( "ove:" + ove.getEndTime () );
        }
        model.addAttribute ( "page", page );
        return "modules/overtime/historyOverTime";
    }


    @RequestMapping(value = "overdetaile")
    public String overdetail(String id, Model model) {
        OverTime overTime = leaAndOveService.getOneOverTime ( id );
        model.addAttribute ( "overTime", overTime );
        return "modules/overtime/historyOverDetaile";
    }


    @RequestMapping(value = "leaveList")
    public String leaveList(String id, ActivityLeave2 leave, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (StringUtils.isNotBlank ( id )) {
            User user = new User ();
            user.setId ( id );
            leave.setUser ( user );
        }
        Page<ActivityLeave2> page = leaAndOveService.findleavePage ( new Page<ActivityLeave2> ( request, response ), leave );
        List<ActivityLeave2> list = page.getList ();
        System.out.println ( "list:" + list );
        for (ActivityLeave2 act : list) {
            System.out.println ( act.getDays () );
            System.out.println ( act.getUser ().getPhone () );
            System.out.println ( act.getRemoveDays () );
        }
        model.addAttribute ( "page", page );
        return "modules/overtime/historyLeave";
    }

    @RequestMapping(value = "leavedetaile")
    public String leavedetaile(String id, Model model) {
        ActivityLeave2 leave = leaAndOveService.findOneLeave ( id );
        model.addAttribute ( "leave", leave );
        return "modules/overtime/historyLeaveDetaile";
    }

}
