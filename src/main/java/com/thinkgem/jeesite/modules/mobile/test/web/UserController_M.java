/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mobile.test.web;

import com.thinkgem.jeesite.common.mobile.http.MobileRequest;
import com.thinkgem.jeesite.common.mobile.http.MobileResponse;
import com.thinkgem.jeesite.common.mobile.http.MobileResponseState;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mobile.test.entity.User_M;
import com.thinkgem.jeesite.modules.mobile.test.service.UserService_M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 用户Controller
 *
 * @author ThinkGem
 * @version 2013-8-29
 */

@Controller
@RequestMapping(value = "/mobile/user_m")
// @RequestMapping(value = "${adminPath}/mobile/user_m")
public class UserController_M extends BaseController {

    @Autowired
    private UserService_M userServicce;

    @RequestMapping(value = "login")
    public @ResponseBody
    MobileResponse<User_M> login(@RequestBody MobileRequest<HashMap<String, Object>> request) {

        if (!MobileRequest.checkIllegalAll ( request ) || !MobileRequest.checkParamsMapKey ( request.getParam (), "userName" )
                || !MobileRequest.checkParamsMapKey ( request.getParam (), "password" )) {

            return new MobileResponse<User_M> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }

        // System.out.println("request---->" + request.toString());

        String userName = request.getParam ().get ( "userName" ).toString ();
        String password = request.getParam ().get ( "password" ).toString ();

        if (StringUtils.isBlank ( userName ) || StringUtils.isBlank ( password )) {

            return new MobileResponse<User_M> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }

        User_M user = userServicce.login ( userName, password );

        if (user != null) {

            return new MobileResponse<User_M> ( user );

        } else {

            return new MobileResponse<User_M> ( new MobileResponseState ( "用户不存在或登录名密码错误" ) );
        }

    }

//	@RequestMapping(value = "login2")
//	public @ResponseBody MobileResponse2 login(@RequestBody MobileRequest<HashMap<String, Object>> request) {
//
//		if (!MobileRequest.checkIllegalAll(request) || !MobileRequest.checkParamsMapKey(request.getParam(), "userName")
//				|| !MobileRequest.checkParamsMapKey(request.getParam(), "password")) {
//
//			return MobileResponse2.wrapFail(MobileResponseState.DEFAULT_UNSUPPORT_PARAMS);
//		}
//
////	    System.out.println("request---->" + request.toString());
//
//		String userName = request.getParam().get("userName").toString();
//		String password = request.getParam().get("password").toString();
//
//		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
//
//			return MobileResponse2.wrapFail(MobileResponseState.DEFAULT_UNSUPPORT_PARAMS);
//		}
//
//		User_M user = userServicce.login(userName, password);
//
//		if (user != null) {
//
//			return MobileResponse2.wrapOkWithData(user);
//
//		} else {
//
//			return MobileResponse2.wrapFail(new MobileResponseState("用户不存在或登录名密码错误"));
//		}
//
//	}


    @RequestMapping(value = "getByLoginName")
    public @ResponseBody
    MobileResponse<User_M> getByLoginName(
            @RequestBody MobileRequest<HashMap<String, Object>> request) {

        if (!MobileRequest.checkIllegalAll ( request )
                || !MobileRequest.checkParamsMapKey ( request.getParam (), "loginName" )) {

            return new MobileResponse<User_M> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }

        // System.out.println("request---->" + request.toString());

        String loginName = request.getParam ().get ( "loginName" ).toString ();
        if (StringUtils.isBlank ( loginName )) {

            return new MobileResponse<User_M> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }

        User_M user = userServicce.getByLoginName ( loginName );

        if (user != null) {

            return new MobileResponse<User_M> ( user );

        } else {

            return new MobileResponse<User_M> ( new MobileResponseState ( "用户未找到" ) );
        }

    }

    @RequestMapping(value = "queryAllUser")
    public @ResponseBody
    MobileResponse<List<User_M>> queryAllUser(
            @RequestBody MobileRequest<HashMap<String, Object>> request) {

        if (!MobileRequest.checkIllegalAll ( request ) || !MobileRequest.checkParamsMapKey ( request.getParam (), "offset" )) {

            return new MobileResponse<List<User_M>> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }

        // System.out.println("request---->" + request.toString());

        int offset = (Integer) request.getParam ().get ( "offset" );

        List<User_M> users = userServicce.queryAllUser ( offset, 10 );

        if (users != null && users.size () > 0) {

            return new MobileResponse<List<User_M>> ( users );

        } else {

            if (offset == 0) {

                return new MobileResponse<List<User_M>> ( MobileResponseState.DEFAULT_NO_DATAS );

            } else {

                return new MobileResponse<List<User_M>> ( MobileResponseState.DEFAULT_NO_MORE_DATAS );
            }
        }

    }

}
