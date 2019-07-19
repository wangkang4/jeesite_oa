package com.thinkgem.jeesite.modules.clock.controller;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.clock.entity.Checkin;
import com.thinkgem.jeesite.modules.clock.service.serviceImpl.CheckinServiceImpl;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/clock/checkin")
public class CheckinController {
    @Autowired
    private CheckinServiceImpl checkinService;

    @RequestMapping(value = "checkin")
    public String selectAllCheckin(Checkin checkin, HttpServletRequest request, HttpServletResponse response, Model model, String startTime1, String endTime1) {
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
        try {
            if (startTime1 != null && startTime1 != "") {
                startTime1 = startTime1 + " 00:00:00";
                Long startTime = sdf.parse ( startTime1 ).getTime () / 1000;
                checkin.setStartTime ( startTime );
                Date st = new Date ( startTime * 1000 );
                model.addAttribute ( "startTime", st );
            }
            if (endTime1 != null && endTime1 != "") {
                endTime1 = endTime1 + " 23:59:59";
                Long endTime = sdf.parse ( endTime1 ).getTime () / 1000;
                checkin.setEndTime ( endTime );
                Date et = new Date ( endTime * 1000 );
                model.addAttribute ( "endTime", et );
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        Page<Checkin> page = checkinService.findPage ( new Page<Checkin> ( request, response ), checkin );
        for (Checkin ch : page.getList ()) {
            ch.setCheckinTime1 ( sdf.format ( new Date ( ch.getCheckinTime () * 1000 ) ) );
        }
        Boolean bool = checkin.getUser () != null && checkin.getUser ().getId () != null && checkin.getUser ().getId () != "";
        if (bool) {
            model.addAttribute ( "userId", checkin.getUser ().getId () );
        }
        bool = checkin.getUser () != null && checkin.getUser ().getName () != null && checkin.getUser ().getName () != "";
        if (bool) {
            model.addAttribute ( "userName", checkin.getUser ().getName () );
        }
        model.addAttribute ( "page", page );
        return "modules/clock/clock";
    }

    @RequestMapping(value = "syn")
    public String syn() {

        return "redirect:checkin";
    }


    @ResponseBody
    @RequestMapping(value = "ExExcel")
    public String ExportCost(String startTime1, String endTime1, String userId1, HttpServletRequest request, HttpServletResponse response,
                             RedirectAttributes redirectAttributes) {
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
        try {
            String fileName = DateUtils.getDate ( "yyyy_MM_dd_" ) + "checkin" + ".xlsx";
            Checkin checkin = new Checkin ();
            DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" );
            if (StringUtils.isNotBlank ( startTime1 )) {
                checkin.setStartTime ( df.parse ( startTime1 ).getTime () / 1000 );
            }
            if (StringUtils.isNotBlank ( endTime1 )) {
                checkin.setEndTime ( df.parse ( endTime1 ).getTime () / 1000 );
            }
            if (StringUtils.isNotBlank ( userId1 )) {
                User user = new User ();
                user.setId ( userId1 );
                checkin.setUser ( user );
            }

//			Page<Checkin> page = checkinService.findPage(new Page<Checkin>(request, response), checkin);
            List<Checkin> list = checkinService.findList ( checkin );
            for (Checkin ch : list) {
                ch.setCheckinTime1 ( sdf.format ( new Date ( ch.getCheckinTime () * 1000 ) ) );
            }
            new ExportExcel ( "打卡信息", Checkin.class ).setDataList ( list ).write ( response, fileName )
                    .dispose ();
            return null;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return "redirect:" + Global.getAdminPath () + "/clock/checkin/checkin?repage";
    }

}
