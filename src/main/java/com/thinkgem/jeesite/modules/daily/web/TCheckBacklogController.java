package com.thinkgem.jeesite.modules.daily.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.daily.entity.TCheckBacklog;
import com.thinkgem.jeesite.modules.daily.service.TCheckBacklogService;
import com.thinkgem.jeesite.modules.daily.service.TDailyService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping(value = "${adminPath}/daily/tCheckBacklog")
public class TCheckBacklogController extends BaseController {

    @Autowired
    private TCheckBacklogService tCheckBacklogService;

    @Autowired
    private TDailyService tDailyService;

    @RequestMapping("list")
    public String list(TCheckBacklog tCheckBacklog, Model model, HttpServletRequest request,
                       HttpServletResponse response) {
        tCheckBacklog.setUser ( UserUtils.getUser () );
        tCheckBacklog.setTypes ( 1 );
        Page<TCheckBacklog> page = tCheckBacklogService.findPage ( new Page<TCheckBacklog> ( request, response ),
                tCheckBacklog );
        model.addAttribute ( "page", page );
        model.addAttribute ( "tCheckBacklog", tCheckBacklog );
        return "modules/daily/tCheckBacklogList";
    }

    @RequestMapping("form")
    public String form(String profId, Model model) {
        if (StringUtils.isNoneBlank ( profId )) {
            model.addAttribute ( "tDaily", tDailyService.get ( profId ) );
        }
        return "modules/daily/tCheckBacklogForm";
    }

    @RequestMapping(value = "ExExcel")
    public String ExExcel(String createrTime, HttpServletRequest request,
                          HttpServletResponse response, RedirectAttributes redirectAttributes) {
        DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" );
        try {
            String fileName = "";
            TCheckBacklog tCheckBacklog = new TCheckBacklog ();
            tCheckBacklog.setUser ( UserUtils.getUser () );
            tCheckBacklog.setTypes ( 1 );
            if (StringUtils.isNotBlank ( createrTime )) {
                fileName = createrTime + "日报" + ".xlsx";
                tCheckBacklog.setCreaterTime ( df.parse ( createrTime ) );
            } else {
                fileName = DateUtils.getDate ( "yyyy-MM-dd" ) + "日报" + ".xlsx";
            }
            Page<TCheckBacklog> page = tCheckBacklogService.findPage ( new Page<TCheckBacklog> ( request, response ),
                    tCheckBacklog );
            new ExportExcel ( "日报统计", TCheckBacklog.class ).setDataList ( page.getList () ).write ( response, fileName ).dispose ();
            return null;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return "redirect:" + Global.getAdminPath () + "/daily/tCheckBacklog/?repage";
    }

}
