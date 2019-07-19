/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.daysign.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.daysign.entity.Udaysign;
import com.thinkgem.jeesite.modules.daysign.service.UdaysignService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 打卡成功Controller
 *
 * @author ShiLiangYu
 * @version 2017-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/daysign/udaysign")
public class UdaysignController extends BaseController {

    @Autowired
    private UdaysignService udaysignService;

    @ModelAttribute
    public Udaysign get(@RequestParam(required = false) String id) {
        Udaysign entity = null;
        if (StringUtils.isNotBlank ( id )) {
            entity = udaysignService.get ( id );
        }
        if (entity == null) {
            entity = new Udaysign ();
        }
        return entity;
    }

    @RequiresPermissions("daysign:udaysign:view")
    @RequestMapping(value = {"list", ""})
    public String list(Udaysign udaysign, HttpServletRequest request, HttpServletResponse response, Model model) {
        udaysign.setUser ( UserUtils.getUser () );
        Page<Udaysign> page = udaysignService.findPage ( new Page<Udaysign> ( request, response ), udaysign );
        model.addAttribute ( "page", page );
        return "modules/daysign/udaysignList";
    }

    @RequiresPermissions("daysign:udaysign:view")
    @RequestMapping(value = "form")
    public String form(Udaysign udaysign, Model model) {
        model.addAttribute ( "udaysign", udaysign );
        return "modules/daysign/udaysignForm";
    }

    @RequiresPermissions("daysign:udaysign:edit")
    @RequestMapping(value = "save")
    @ResponseBody
    public boolean save(Udaysign udaysign, HttpServletRequest request) {
        // 获取ip地址

        String ip;
        try {
            ip = InetAddress.getLocalHost ().getHostAddress ();
            udaysign.setIp ( ip );
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }
        // 设置User
        udaysign.setUser ( UserUtils.getUser () );
        udaysign.setName ( UserUtils.getUser ().getLoginName () );
        List<Udaysign> list = udaysignService.findList ( udaysign );

        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd" );
        Date now = new Date ();
        String strNow = format.format ( now );
        boolean flag = false;
        // 判断是否存在相同的日期
        if (list != null && list.size () > 0) {
            for (Udaysign udaysign2 : list) {
                String strOld = format.format ( udaysign2.getCreateDate () );

                if (strNow.equals ( strOld )) {
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
        } else {
            flag = true;
        }
        // 判断状态 :1迟到，0正常
        udaysign.setState ( Integer.toString ( compare_date ( now ) ) );

        // 判断是否满足打卡条件
        if (flag == true) {
            udaysignService.save ( udaysign );
        }
        return flag;
    }

    // 判断上班打卡状态
    public static int compare_date(Date date1) {
        String dateBegin = "09:00:00";// 规定上班时间
        String dateEnd = "18:00:00";// 规定下班时间
        String dateStart = "00:00:00";// 凌晨时间
        SimpleDateFormat df = new SimpleDateFormat ( "HH:mm:ss" );
        String dateNow = df.format ( date1 );// 当前打卡时间
        try {
            Date dt1 = df.parse ( dateNow );
            Date dt2 = df.parse ( dateBegin );
            Date dt3 = df.parse ( dateEnd );
            Date dt4 = df.parse ( dateStart );
            if (dt1.getTime () >= dt2.getTime () && dt1.getTime () <= dt3.getTime ()) {
                return 1;// 迟到
            }
            if (dt1.getTime () <= dt2.getTime () && dt1.getTime () >= dt4.getTime ()) {
                return 0;// 正常
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }
        return 1;
    }

    @RequiresPermissions("daysign:udaysign:edit")
    @RequestMapping(value = "update")
    @ResponseBody
    public int update(Udaysign udaysign) {
        // 设置User
        udaysign.setUser ( UserUtils.getUser () );
        udaysign.setName ( UserUtils.getUser ().getLoginName () );
        List<Udaysign> list = udaysignService.findList ( udaysign );

        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd" );
        Date now = new Date ();
        String strNow = format.format ( now );
        int Identification = 0;// 标识:0签到正常，1不能重复签到,3今日未签到不能签退,4早退
        // 判断是否存在相同的日期
        for (Udaysign udaysign2 : list) {
            String strOld = format.format ( udaysign2.getCreateDate () );
            if (strNow.equals ( strOld )) {
                if (udaysign2.getEnd () == null) {
                    udaysign.setEnd ( now );
                    if (compare_date2 ( now ) == 4) {
                        udaysign.setEndState ( "4" );
                    } else {
                        udaysign.setEndState ( "0" );
                    }
                    udaysignService.update ( udaysign );
                    return Identification = 0;
                } else {
                    return Identification = 1;
                }

            } else {
                Identification = 3;
            }
        }
        if (compare_date2 ( now ) == 4) {
            Identification = 3;
        }

        return Identification;
    }

    // 判断下班打卡状态
    public static int compare_date2(Date date) {
        String dateBegin = "09:00:00";// 规定上班时间
        String dateEnd = "18:00:00";// 规定下班时间

        SimpleDateFormat df = new SimpleDateFormat ( "HH:mm:ss" );
        String dateNow = df.format ( date );// 当前打卡时间
        int flag = 3;
        try {
            Date dt1 = df.parse ( dateNow );// 当前时间
            Date dt2 = df.parse ( dateEnd );// 下班时间
            Date dt3 = df.parse ( dateBegin );// 上班时间

            if (dt1.getTime () >= dt3.getTime () && dt1.getTime () <= dt2.getTime ()) {
                flag = 4;// 早退
            } else {
                flag = 3;// 今日未签到不能签退
            }

        } catch (ParseException e) {
            e.printStackTrace ();
        }
        return flag;
    }

    @RequiresPermissions("daysign:udaysign:edit")
    @RequestMapping(value = "delete")
    public String delete(Udaysign udaysign, RedirectAttributes redirectAttributes) {
        udaysignService.delete ( udaysign );
        addMessage ( redirectAttributes, "删除签到成功" );
        return "redirect:" + Global.getAdminPath () + "/daysign/udaysign/?repage";
    }

    @RequiresPermissions("daysign:udaysign:view")
    @RequestMapping(value = "allList")
    public String allList(Udaysign udaysign, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Udaysign> page = udaysignService.findPage ( new Page<Udaysign> ( request, response ), udaysign );
        model.addAttribute ( "page", page );
        return "modules/daysign/udaysignList2";
    }

}