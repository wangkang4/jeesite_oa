package com.thinkgem.jeesite.modules.costList.web;


import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.costList.entity.Account;
import com.thinkgem.jeesite.modules.costList.entity.CostList;
import com.thinkgem.jeesite.modules.costList.service.AccountService;
import com.thinkgem.jeesite.modules.costList.service.CostListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 统计总表的Controller层
 *
 * @author shengchanghao
 */
@Controller
@RequestMapping("${adminPath}/cost/costList")
public class CostListController extends BaseController {

    @Autowired
    private CostListService costListService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("list")
    public String List(Account account, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<CostList> list = costListService.findCostList ();
        model.addAttribute ( "list", list );

        Page<Account> page = accountService.findPage ( new Page<Account> ( request, response ), account );
        model.addAttribute ( "page", page );
        model.addAttribute ( "account", account );
        return "modules/costList/list";
    }

    @RequestMapping("toAdd")
    public String ListAdd(CostList costList, Model model) {
        List<CostList> list = costListService.findCostList ();
        model.addAttribute ( "list", list );

        model.addAttribute ( "costList", costList );
        return "modules/costList/account";
    }

    @ResponseBody
    @RequestMapping("insertList")
    public boolean InsertList(@RequestBody List<Account> acc) {
		
		/*//获取jsonObject对象
		JSONObject obj=JSONObject.parseObject(acc);
		//将对象转化为jsonArray数组
		JSONArray arr=obj.getJSONArray("resultData");
		//将数组转化为字符串
		String js=JSONObject.toJSONString(arr,SerializerFeature.WriteClassName);*/
        //将字符串转化为list集合
        System.out.println ( acc );
        accountService.insertList ( acc );

        return true;


    }


}
