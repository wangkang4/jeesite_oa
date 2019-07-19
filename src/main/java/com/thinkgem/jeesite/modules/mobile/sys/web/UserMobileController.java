package com.thinkgem.jeesite.modules.mobile.sys.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mobile.http.MobileEntityEmpty;
import com.thinkgem.jeesite.common.mobile.http.MobileRequest;
import com.thinkgem.jeesite.common.mobile.http.MobileResponse;
import com.thinkgem.jeesite.common.mobile.http.MobileResponseState;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mobile.sys.entity.UserMobile;
import com.thinkgem.jeesite.modules.mobile.sys.service.UserMobileService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/mobile/sys")
public class UserMobileController {

    @Autowired
    private UserMobileService userMobileService;

    /**
     * 手机端登录接口
     *
     * @return
     */
    @RequestMapping(value = "login")
    public @ResponseBody
    MobileResponse<UserMobile> login(
            @RequestBody MobileRequest<HashMap<String, Object>> request) {
        //校验传入数据的值（null）
        if (!MobileRequest.checkIllegalAll ( request ) || !MobileRequest.checkParamsMapKey ( request.getParam (), "userName" )
                || !MobileRequest.checkParamsMapKey ( request.getParam (), "password" )) {
            return new MobileResponse<UserMobile> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        //接受mobile传入的userName和password
        String userName = request.getParam ().get ( "userName" ).toString ();
        String password = request.getParam ().get ( "password" ).toString ();
        //判断空值
        if (StringUtils.isBlank ( userName ) || StringUtils.isBlank ( password )) {
            return new MobileResponse<UserMobile> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        //根据登录名获取该用户信息
        UserMobile user = userMobileService.login ( userName );

        if (user != null) {
            //将明文密码和数据库内密文密码进行比较
            if (SystemService.validatePassword ( password, user.getPassword () )) {
                return new MobileResponse<UserMobile> ( user );
            } else {
                return new MobileResponse<UserMobile> ( new MobileResponseState ( "用户不存在或登录名密码错误" ) );
            }
        } else {
            return new MobileResponse<UserMobile> ( new MobileResponseState ( "用户不存在或登录名密码错误" ) );
        }
    }

    /**
     * 收件人信息
     *
     * @return
     */
    @RequestMapping(value = "treeData")
    public @ResponseBody
    MobileResponse<List<Map<String, Object>>> treeDate(
            @RequestBody MobileRequest<HashMap<String, Object>> request) {
        if (!MobileRequest.checkIllegalAll ( request ) || !MobileRequest.checkParamsMapKey ( request.getParam (), "offset" )) {

            return new MobileResponse<List<Map<String, Object>>> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }

        Integer offset = (Integer) request.getParam ().get ( "offset" );


        List<UserMobile> list = userMobileService.findMobilelist ( offset, 30 );
        List<Map<String, Object>> mapList = Lists.newArrayList ();
        for (int i = 0; i < list.size (); i++) {
            String str = list.get ( i ).getOffice ().getParentIds ();
            StringBuffer SBf = new StringBuffer ( "" );
            String[] parentids = str.split ( "," );
            for (String str1 : parentids) {
                if (!str1.equals ( "0" )) {
                    String str2 = userMobileService.getofficeName ( str1 );
                    SBf.append ( str2 );
                }
            }
            SBf.append ( list.get ( i ).getOffice ().getName () );
            String id = list.get ( i ).getId ();
            String names = SBf.toString ();
            String name = list.get ( i ).getName ();
            Map<String, Object> map = Maps.newHashMap ();
            map.put ( "id", id );
            map.put ( "officeNames", names );
            map.put ( "name", name );
            map.put ( "photo", list.get ( i ).getPhoto () );
            mapList.add ( map );
        }

        if (list != null && list.size () > 0) {


            return new MobileResponse<List<Map<String, Object>>> ( mapList );

        } else {
            if (offset == 0) {

                return new MobileResponse<List<Map<String, Object>>> ( MobileResponseState.DEFAULT_NO_DATAS );

            } else {

                return new MobileResponse<List<Map<String, Object>>> ( MobileResponseState.DEFAULT_NO_MORE_DATAS );
            }

        }
    }

    /**
     * 修改个人信息
     *
     * @return
     */
    @RequestMapping(value = "updateUserInfo")
    public @ResponseBody
    MobileResponse<UserMobile> changeMessage(
            @RequestBody MobileRequest<UserMobile> request) {
        if (!MobileRequest.checkIllegalAll ( request )) {
            return new MobileResponse<UserMobile> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        UserMobile userMobile = request.getParam ();
        String id = request.getBase ().getUid ();
        userMobile.setId ( id );
        if (userMobile.getType () == 1) {
            userMobileService.uploadone ( userMobile );
        } else if (userMobile.getType () == 2) {
            String downloadPrefix = Global.getConfig ( "httpPath" );
            userMobile.setPhoto ( downloadPrefix + userMobile.getPhoto () );
            userMobileService.uploadone ( userMobile );
        } else {
            return new MobileResponse<UserMobile> ( new MobileResponseState ( "修改类型不存在！" ) );
        }
        UserMobile user = userMobileService.findone ( id );
        if (user != null) {
            return new MobileResponse<UserMobile> ( user );
        } else {
            return new MobileResponse<UserMobile> ( new MobileResponseState ( "修改失败！" ) );
        }

    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "updatePassword")
    public @ResponseBody
    MobileResponse<MobileEntityEmpty> save(
            @RequestBody MobileRequest<HashMap<String, Object>> request) {
        if (!MobileRequest.checkIllegalAll ( request ) ||
                !MobileRequest.checkParamsMapKey ( request.getParam (), "oldPassword" ) ||
                !MobileRequest.checkParamsMapKey ( request.getParam (), "newPassword" )) {
            return new MobileResponse<MobileEntityEmpty> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        String id = request.getBase ().getUid ();
        String oldPassword = request.getParam ().get ( "oldPassword" ).toString ();
        String newPassword = request.getParam ().get ( "newPassword" ).toString ();

        UserMobile user = userMobileService.findone ( id );
        if (!SystemService.validatePassword ( oldPassword, user.getPassword () )) {
            return new MobileResponse<MobileEntityEmpty> ( new MobileResponseState ( "原始密码错误！" ) );
        } else {
            UserMobile userMobile = new UserMobile ();
            userMobile.setId ( id );
            String password = SystemService.entryptPassword ( newPassword );
            userMobile.setPassword ( password );
            userMobileService.uploadone ( userMobile );
        }

        if (SystemService.validatePassword ( newPassword, userMobileService.findone ( id ).getPassword () )) {
            return new MobileResponse<MobileEntityEmpty> ( MobileResponseState.DEFAULT_SUCCESS );
        } else {
            return new MobileResponse<MobileEntityEmpty> ( new MobileResponseState ( "修改失败！" ) );
        }
    }
}
