package com.thinkgem.jeesite.modules.mobile.dailyrecord.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mobile.http.MobileRequest;
import com.thinkgem.jeesite.common.mobile.http.MobileResponse;
import com.thinkgem.jeesite.common.mobile.http.MobileResponseState;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.service.OANotifyRecordService;
import com.thinkgem.jeesite.modules.mobile.fileupload.entity.FileUploadResult;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/mobile/oaNotity")
public class OANotifyMobileController {

    @Autowired
    private OANotifyRecordService oanoService;

    /**
     * 手机通告列表
     *
     * @return
     */
    @RequestMapping(value = "getMyNotity")
    public @ResponseBody
    MobileResponse<List<OaNotifyRecord>> getMyNotity(
            @RequestBody MobileRequest<HashMap<String, Object>> request) {
        if (!MobileRequest.checkIllegalAll ( request ) || !MobileRequest.checkParamsMapKey ( request.getParam (), "offset" )) {
            return new MobileResponse<List<OaNotifyRecord>> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        Integer offset = (Integer) request.getParam ().get ( "offset" );
        String userid = request.getBase ().getUid ();
        List<OaNotifyRecord> oaNoRelist = oanoService.getall ( offset, 10, userid );

        if (oaNoRelist != null && oaNoRelist.size () > 0) {

            for (OaNotifyRecord oanr : oaNoRelist) {
                OaNotify oaNotify = oanr.getOaNotify ();

                String typeName = oanoService.gettypename ( oaNotify.getType () );
                oanr.getOaNotify ().setTypeName ( typeName );
                if (StringUtils.isNotBlank ( oaNotify.getContent () )) {
                    if (oaNotify.getContent ().length () > 100) {
                        oaNotify.setContent ( oaNotify.getContent ().substring ( 0, 100 ) );
                    }

                    oaNotify.setContent ( oaNotify.getContent ().replaceFirst ( "<p>", "" ) );
                    // oanr.setOaNotify(oaNotify);
                }

            }

            return new MobileResponse<List<OaNotifyRecord>> ( oaNoRelist );

        } else {
            if (offset == 0) {

                return new MobileResponse<List<OaNotifyRecord>> ( MobileResponseState.DEFAULT_NO_DATAS );

            } else {

                return new MobileResponse<List<OaNotifyRecord>> ( MobileResponseState.DEFAULT_NO_MORE_DATAS );
            }

        }
    }

    /**
     * 通告信息详情
     *
     * @return
     */
    @RequestMapping(value = "OANotifyForm")
    public @ResponseBody
    MobileResponse<HashMap<String, Object>> OANotifyForm(
            @RequestBody MobileRequest<HashMap<String, Object>> request) {
        if (!MobileRequest.checkIllegalAll ( request ) || !MobileRequest.checkParamsMapKey ( request.getParam (), "id" )) {
            return new MobileResponse<HashMap<String, Object>> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        String id = request.getParam ().get ( "id" ).toString ();

        OaNotify oaNotify = oanoService.getOaNotify ( id );

        oaNotify.setType ( oanoService.gettypename ( oaNotify.getType () ) );

        String[] fileids = oaNotify.getFiles ().split ( "\\|" );

        List<FileUploadResult> fileList = new ArrayList<FileUploadResult> ();

        String downloadPrefix = Global.getConfig ( "httpPath" );

        for (String str : fileids) {

            FileUploadResult file = oanoService.getonefile ( str );

            if (file != null && StringUtils.isNotBlank ( file.getId () )) {
                file.setFileFinalPath ( downloadPrefix + file.getFileFinalPath () );
                fileList.add ( file );
            }


        }

        HashMap<String, Object> list = new HashMap<String, Object> ();
        list.put ( "oaNotify", oaNotify );
        list.put ( "fileList", fileList );

        return new MobileResponse<HashMap<String, Object>> ( list );
    }

}
