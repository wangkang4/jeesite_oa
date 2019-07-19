package com.thinkgem.jeesite.modules.Myface.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.daily.entity.TCheckBacklog;
import com.thinkgem.jeesite.modules.daily.service.TCheckBacklogService;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/oa/homepage")
public class MyfaceController extends BaseController {

    @Autowired
    private TCheckBacklogService tCheckBacklogService;

    @Autowired
    private OaNotifyService oaNotifyService;

    @RequestMapping(value = "myface")
    public String myface(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        User user = UserUtils.getUser ();
        //获取昨日时间
        SimpleDateFormat df = new SimpleDateFormat ( "yyyy-MM-dd " );
        Calendar cal = Calendar.getInstance ();
        cal.add ( Calendar.DATE, -1 );
        String yesterday = df.format ( cal.getTime () );

        TCheckBacklog tCheckBacklog = new TCheckBacklog ();
        tCheckBacklog.setCreaterTime ( df.parse ( yesterday ) );
        tCheckBacklog.setUser ( user );
        tCheckBacklog.setTypes ( 1 );

        int countYstday = tCheckBacklogService.findYstayCount ( tCheckBacklog );

        List<TCheckBacklog> list = tCheckBacklogService.findList ( tCheckBacklog );

        OaNotify oaNotify = new OaNotify ();
        oaNotify.setSelf ( true );
        List<OaNotify> list1 = oaNotifyService.findList ( oaNotify );

        model.addAttribute ( "list1", list1 );
        model.addAttribute ( "list", list );
        model.addAttribute ( "countYstday", countYstday );

        model.addAttribute ( "user", user );
        return "modules/MyFace/MyFace";
    }

}
