package com.thinkgem.jeesite.modules.mobile.dailyrecord.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mobile.http.MobileEntityEmpty;
import com.thinkgem.jeesite.common.mobile.http.MobileRequest;
import com.thinkgem.jeesite.common.mobile.http.MobileResponse;
import com.thinkgem.jeesite.common.mobile.http.MobileResponseState;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.daily.entity.TCheckBacklog;
import com.thinkgem.jeesite.modules.daily.entity.TDaily;
import com.thinkgem.jeesite.modules.daily.service.TCheckBacklogService;
import com.thinkgem.jeesite.modules.daily.service.TDailyService;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.entity.TCheckBack;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.service.TCheckBackService;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileUploadResult;
import com.thinkgem.jeesite.modules.mobile.fileupload.service.MobileFileUploadService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 日报模块和信息获取
 *
 * @author tanchaoyang
 */
@Controller
@RequestMapping(value = "/mobile/daily")
public class DailyRecondsController {

    @Autowired
    private TCheckBacklogService tCheckBacklogService;

    @Autowired
    private TDailyService tDailyService;

    @Autowired
    private TCheckBackService tCheckBackService;

    @Autowired
    private MobileFileUploadService fileService;

    /**
     * 日报信息保存
     *
     * @param request
     */
    @RequestMapping(value = "savedaily")
    public @ResponseBody
    MobileResponse<MobileEntityEmpty> save(@RequestBody MobileRequest<TDaily> request) throws ParseException {
        if (!MobileRequest.checkIllegalAll ( request )) {
            return new MobileResponse<MobileEntityEmpty> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        TDaily tDaily = request.getParam ();
        // 获取当前时间
        Date now = new Date ();
        // 获取用户id
        String userid = request.getBase ().getUid ();
        User user = new User ();
        user.setId ( userid );
        tDaily.setUser ( user );
        tDaily.setId ( IdGen.uuid () );
        tDaily.setCreaterTime ( now );
        DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" );
        String day = df.format ( now );
        tDaily.setDayTime ( df.parse ( day ) );
        TCheckBacklog tCheckBacklog = new TCheckBacklog ();
        String[] names = tDaily.getSendName ().split ( "," );
        String remarks = "";
        for (String str1 : names) {
            tCheckBacklog.setCreaterId ( userid );
            tCheckBacklog.setCreaterTime ( now );
            tCheckBacklog.setTypes ( 1 );
            tCheckBacklog.setProfId ( tDaily.getId () );

            User userTest = new User ();
            userTest.setId ( str1 );// 往中间表插入记录：收报人的id
            tCheckBacklog.setUser ( userTest );
            tCheckBacklogService.save ( tCheckBacklog );
            remarks = remarks + tDailyService.getofficeName ( str1 ) + ",";
        }
        tDaily.setRemarks ( remarks.substring ( 0, remarks.length () - 1 ) );
        tDailyService.inserone ( tDaily );
        TDaily tDaily1 = tDailyService.get ( tDaily.getId () );

        if (tDaily1 != null) {

            return new MobileResponse<MobileEntityEmpty> ( MobileResponseState.DEFAULT_SUCCESS );
        } else {

            return new MobileResponse<MobileEntityEmpty> ( new MobileResponseState ( "日报保存失败！" ) );

        }
    }

    /**
     * 获取本人以前发送的日、周报信息
     *
     * @return
     */
    @RequestMapping(value = "sendbefore")
    public @ResponseBody
    MobileResponse<List<TCheckBack>> sendbefore(
            @RequestBody MobileRequest<HashMap<String, Object>> request) {
        if (!MobileRequest.checkIllegalAll ( request ) || !MobileRequest.checkParamsMapKey ( request.getParam (), "offset" )) {
            return new MobileResponse<List<TCheckBack>> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        Integer offset = (Integer) request.getParam ().get ( "offset" );
        String userid = request.getBase ().getUid ();
        List<TCheckBack> list1 = tCheckBackService.findRecondlist ( offset, 5, userid );

        if (list1 != null && list1.size () > 0) {
            for (int i = 0; i < list1.size (); i++) {
                User user = tCheckBackService.getUserone ( userid );
                List<FileUploadResult> fileList = fileService.getFileList ( list1.get ( i ).getProfId () );

                String downloadPrefix = Global.getConfig ( "httpPath" );
                if (fileList != null) {
                    for (FileUploadResult file1 : fileList) {
                        file1.setFileFinalPath ( downloadPrefix + file1.getFileFinalPath () );
                    }
                }
                list1.get ( i ).setFileList ( fileList );
                list1.get ( i ).setUser ( user );
            }

            return new MobileResponse<List<TCheckBack>> ( list1 );

        } else {
            if (offset == 0) {

                return new MobileResponse<List<TCheckBack>> ( MobileResponseState.DEFAULT_NO_DATAS );

            } else {

                return new MobileResponse<List<TCheckBack>> ( MobileResponseState.DEFAULT_NO_MORE_DATAS );
            }

        }
    }

    /**
     * 收件人获取日周报信息
     *
     * @return
     */
    @RequestMapping(value = "getsend")
    public @ResponseBody
    MobileResponse<List<TCheckBack>> getsend(
            @RequestBody MobileRequest<HashMap<String, Object>> request) {
        if (!MobileRequest.checkIllegalAll ( request ) || !MobileRequest.checkParamsMapKey ( request.getParam (), "offset" )) {
            return new MobileResponse<List<TCheckBack>> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        Integer offset = (Integer) request.getParam ().get ( "offset" );
        String userid = request.getBase ().getUid ();
        List<TCheckBack> list1 = tCheckBackService.findgetsendlist ( offset, 5, userid );


        if (list1 != null && list1.size () > 0) {

            for (int i = 0; i < list1.size (); i++) {
                User user = tCheckBackService.getUserone ( list1.get ( i ).getUser ().getId () );
                List<FileUploadResult> fileList = fileService.getFileList ( list1.get ( i ).getProfId () );
                String downloadPrefix = Global.getConfig ( "httpPath" );
                if (fileList != null) {
                    for (FileUploadResult file1 : fileList) {
                        file1.setFileFinalPath ( downloadPrefix + file1.getFileFinalPath () );
                    }
                }
                list1.get ( i ).setFileList ( fileList );
                list1.get ( i ).setUser ( user );
            }

            return new MobileResponse<List<TCheckBack>> ( list1 );

        } else {
            if (offset == 0) {

                return new MobileResponse<List<TCheckBack>> ( MobileResponseState.DEFAULT_NO_DATAS );

            } else {

                return new MobileResponse<List<TCheckBack>> ( MobileResponseState.DEFAULT_NO_MORE_DATAS );
            }

        }
    }

}
