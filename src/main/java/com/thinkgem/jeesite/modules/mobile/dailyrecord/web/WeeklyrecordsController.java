package com.thinkgem.jeesite.modules.mobile.dailyrecord.web;

import com.thinkgem.jeesite.common.mobile.http.MobileEntityEmpty;
import com.thinkgem.jeesite.common.mobile.http.MobileRequest;
import com.thinkgem.jeesite.common.mobile.http.MobileResponse;
import com.thinkgem.jeesite.common.mobile.http.MobileResponseState;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.daily.entity.TCheckBacklog;
import com.thinkgem.jeesite.modules.daily.service.TCheckBacklogService;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.entity.WeeklyRequest;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileUploadResult;
import com.thinkgem.jeesite.modules.mobile.fileupload.service.MobileFileUploadService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.weekly.entity.Weekly;
import com.thinkgem.jeesite.modules.weekly.service.WeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 周报模块
 *
 * @author tanchaoyang
 */
@Controller
@RequestMapping(value = "/mobile/weekly")
public class WeeklyrecordsController {
    @Autowired
    private TCheckBacklogService tCheckBacklogService;
    @Autowired
    private WeeklyService weeklyService;
    @Autowired
    private MobileFileUploadService uploadService;

    /**
     * 保存周报信息
     *
     * @return
     */
    @RequestMapping(value = "saveweekly")
    public @ResponseBody
    MobileResponse<MobileEntityEmpty> save(
            @RequestBody MobileRequest<WeeklyRequest> request) {
        if (!MobileRequest.checkIllegalAll ( request )) {
            return new MobileResponse<MobileEntityEmpty> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        WeeklyRequest weeklyRequest = request.getParam ();
        Weekly weekly = new Weekly ();
        weekly.setFilelist ( weeklyRequest.getFilelist () );
        weekly.setPlancontent ( weeklyRequest.getPlanContent () );
        weekly.setDaycontent ( weeklyRequest.getDayContent () );
        weekly.setSendname ( weeklyRequest.getSendName () );
        weekly.setRemark ( weeklyRequest.getRemark () );
        //获取当前时间
        Date now = new Date ();
        //获取用户id
        String userid = request.getBase ().getUid ();
        User user = new User ();
        user.setId ( userid );
        weekly.setUser ( user );
        weekly.setId ( IdGen.uuid () );
        weekly.setCreatertime ( now );
        weekly.setCreaterid ( userid );
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd" );
        DateFormat df1 = DateFormat.getDateInstance ();
        String time = df1.format ( now );
        try {
            Date daytime = sdf.parse ( time );
            weekly.setDaytime ( daytime );
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        weekly.setDaytime ( now );
        //中间表收报人数据保存
        String[] names = weekly.getSendname ().split ( "," );
        String remarks = "";
        for (String str1 : names) {
            TCheckBacklog tCheckBacklog = new TCheckBacklog ();
            tCheckBacklog.setCreaterId ( userid );
            tCheckBacklog.setCreaterTime ( now );
            tCheckBacklog.setTypes ( 2 );
            tCheckBacklog.setProfId ( weekly.getId () );

            User userTest = new User ();
            userTest.setId ( str1 );// 往中间表插入记录：收报人的id
            tCheckBacklog.setUser ( userTest );
            tCheckBacklogService.save ( tCheckBacklog );
            remarks = remarks + weeklyService.getofficeName ( str1 ) + ",";
        }
        weekly.setRemarks ( remarks.substring ( 0, remarks.length () - 1 ) );
        weeklyService.addweekly ( weekly );

        for (int i = 0; i < weekly.getFilelist ().size (); i++) {
            FileUploadResult result = weekly.getFilelist ().get ( i );
            result.setProfId ( weekly.getId () );
            result.setProfType ( 2 );
            result.setCreaterid ( weekly.getCreaterid () );
            result.setCreatertime ( new Date () );
            uploadService.change ( result );
        }

        Weekly weekly1 = weeklyService.getone ( weekly.getId () );


        if (weekly1 != null) {
            return new MobileResponse<MobileEntityEmpty> ( MobileResponseState.DEFAULT_SUCCESS );
        } else {
            return new MobileResponse<MobileEntityEmpty> ( new MobileResponseState ( "周报保存失败！" ) );

        }
    }


}
