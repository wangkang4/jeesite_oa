package com.thinkgem.jeesite.modules.mobile.dailyrecord.web;

import com.thinkgem.jeesite.common.mobile.http.MobileRequest;
import com.thinkgem.jeesite.common.mobile.http.MobileResponse;
import com.thinkgem.jeesite.common.mobile.http.MobileResponseState;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.entity.PunchCard;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.entity.PunchCardRequest;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.entity.PunchCardResponse;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.entity.PunchCardbe;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.service.PunchCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/mobile/workcard")
public class PunchCardController {

    @Autowired
    private PunchCardService punchCardService;

    /**
     * 获取签到状态
     */
    @RequestMapping(value = "findPunch")
    public @ResponseBody
    MobileResponse<PunchCardResponse> getMessage(
            @RequestBody MobileRequest<HashMap<String, Object>> request) {
        if (!MobileRequest.checkIllegalAll ( request )) {
            return new MobileResponse<PunchCardResponse> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        String userid = request.getBase ().getUid ();
        PunchCardResponse punchCardResponse = new PunchCardResponse ();
        List<PunchCard> pCardList = punchCardService.findPunchCards ( userid );
        if (pCardList != null && pCardList.size () > 0) {
            if (pCardList.size () == 1) {
                punchCardResponse.setStartPunchCard ( pCardList.get ( 0 ) );
            } else {
                punchCardResponse.setStartPunchCard ( pCardList.get ( 0 ) );
                punchCardResponse.setEndPunchCard ( pCardList.get ( 1 ) );
            }
        }
        return new MobileResponse<PunchCardResponse> ( punchCardResponse );
    }

    /**
     * 更新签到状态
     *
     * @return
     */
    @RequestMapping(value = "updatePunch")
    public @ResponseBody
    MobileResponse<PunchCardResponse> saveMesage(
            @RequestBody MobileRequest<PunchCardRequest> request) {
        if (!MobileRequest.checkIllegalAll ( request )) {
            return new MobileResponse<PunchCardResponse> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        String userid = request.getBase ().getUid ();

        PunchCardRequest pCardrequest = request.getParam ();
        System.out.println ( pCardrequest.toString () );

        PunchCard pCard = new PunchCard ();
        pCard.setLatitude ( pCardrequest.getLatitude () );
        pCard.setLongitude ( pCardrequest.getLongitude () );
        pCard.setPlace ( pCardrequest.getPlace () );
        pCard.setPunchType ( pCardrequest.getPunchType () );
        pCard.setPunchTimeType ( pCardrequest.getPunchTimeType () );
        if (StringUtils.isNotBlank ( pCardrequest.getId () )) {
            pCard.setId ( pCardrequest.getId () );
        }

        PunchCardbe pCardb = new PunchCardbe ();
        pCardb = punchCardService.savePunchCards ( userid, pCard );
        PunchCardResponse punchCardResponse = new PunchCardResponse ();
        if (pCardb.getFlag () == -1) {
            return new MobileResponse<PunchCardResponse> ( new MobileResponseState ( "传入类型出错！" ) );
        } else if (pCardb.getFlag () == -2) {
            return new MobileResponse<PunchCardResponse> ( new MobileResponseState ( "传入类型为空！" ) );
        } else {
            if (pCard.getPunchType ().equals ( "1" )) {
                punchCardResponse.setStartPunchCard ( pCardb.getpCardbefore () );
                punchCardResponse.setEndPunchCard ( pCardb.getpCard () );
            } else if (pCard.getPunchType ().equals ( "0" )) {
                punchCardResponse.setStartPunchCard ( pCardb.getpCard () );
            }
            return new MobileResponse<PunchCardResponse> ( punchCardResponse );
        }
    }

}
