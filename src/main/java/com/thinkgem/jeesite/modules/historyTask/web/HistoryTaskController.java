package com.thinkgem.jeesite.modules.historyTask.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.historyTask.entity.HistoryTask;
import com.thinkgem.jeesite.modules.historyTask.entity.Page;
import com.thinkgem.jeesite.modules.historyTask.service.HistoryTaskService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/act/task")
public class HistoryTaskController extends BaseController {

    @Autowired
    private HistoryTaskService histoService;

    @RequestMapping(value = "newHistoric")
    public String showNewsInfo(Model model, HttpServletRequest request, HistoryTask historyTask) throws UnsupportedEncodingException {
        /**
         * 获取当前登录者姓名
         */
        historyTask.setLoginName ( UserUtils.getUser ().getLoginName () );
        /**
         * 获取当前页数
         */
        String pageNow = request.getParameter ( "pageNow" );
        /**
         * 获取搜索框userId
         */
        String userId = request.getParameter ( "userId" );
        /**
         * 获取搜索框title标题值
         */
        String title = request.getParameter ( "title" );

        /**
         * 获取流程类型名
         */
        String preceName = request.getParameter ( "preceName" );

        /**
         * 判断申请人搜索框 是否为空
         */
        if (userId != null && userId.length () > 2) {
            /**
             * 获取查询的人名
             */
            String name = UserUtils.get ( userId ).getName ();
            //historyTask.setLoginName(name);
            historyTask.setUserId ( userId );
            model.addAttribute ( "userId", userId );
            model.addAttribute ( "userName", name );

        }
        /**
         * 判断标题搜索框 是否为空
         */
        else if (title != null && title.length () > 1) {
            /**
             * 转码
             */
            //title = new String(title.getBytes("ISO-8859-1"),"utf-8");
            title = new String ( title.getBytes ( "UTF-8" ), "utf-8" );
            historyTask.setTitle ( title );
            model.addAttribute ( "titleName", title );
        }
        /**
         * 判断流程名搜索框 是否为空
         */
        else if (preceName != null && preceName.length () > 1) {
            /**
             * 转码
             */
            //preceName = new String(preceName.getBytes("ISO-8859-1"),"utf-8");
            preceName = new String ( preceName.getBytes ( "UTF-8" ), "utf-8" );
            historyTask.setPreceName ( preceName );
            model.addAttribute ( "preceName", preceName );
        }
        /**
         * 获取总页数
         */
        int totalCount = (int) histoService.findNewCont ( historyTask );
        Page page = null;
        List<HistoryTask> list = new ArrayList<HistoryTask> ();
        if (pageNow != null) {
            page = new Page ( Integer.parseInt ( pageNow ), totalCount );
            /**
             * 开始翻页定量查询
             */
            list = this.histoService.findNewsPage ( page.getStartPos (), page.getPageSize (), historyTask );
        } else {
            /**
             * 开始首次加载查询
             */
            page = new Page ( 1, totalCount );
            list = this.histoService.findNewsPage ( page.getStartPos (), page.getPageSize (), historyTask );
        }
        /**
         * 遍历赋值
         * 给详情链赋值
         */
        for (HistoryTask his : list) {
            /**
             * 获取每一个procId对应的 taskId
             */
            String taskId = histoService.findTaskId ( his.getProcId (), UserUtils.getUser ().getLoginName () );
            /**
             * 获取每一个procId对应的 taskDefKey
             */
            String taskDefKey = histoService.findTaskDefKey ( his.getProcId (), UserUtils.getUser ().getLoginName () );
            /**
             * 获取流程名
             */
            String prdName = histoService.findPrdName ( his.getPrdId () );
            his.setTaskId ( taskId );
            his.setTaskDefKey ( taskDefKey );
            his.setPrdName ( prdName );
        }
        model.addAttribute ( "totalCount", totalCount );
        model.addAttribute ( "list", list );
        model.addAttribute ( "page", page );
        return "modules/act/newActTaskHistoricList";
    }

}
