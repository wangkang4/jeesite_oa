package com.thinkgem.jeesite.modules.CostExcel.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.CostExcel.entity.Client;
import com.thinkgem.jeesite.modules.CostExcel.service.ClientService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/oa/client")
public class ClientController extends BaseController {

    @Autowired
    private ClientService clientService;

    /**
     * 获取客户信息集合
     *
     * @param client 查询条件的modelAttribute
     * @return
     */
    @RequestMapping(value = {"list", ""})
    public String list(Client client, HttpServletRequest request, HttpServletResponse response,
                       Model model) {
        /*获取当前用户的角色集合*/
        List<String> list = UserUtils.getUser ().getRoleIdList ();
        String rol = "user";/*标识用户权限角色*/
        for (String str : list) {
            if (str.equals ( "a521208da0ff45189d4d316c20161ab8" )) {/*a521208da0ff45189d4d316c20161ab8管理员角色id*/
                rol = "admin";/*是管理员更新rol*/
            }
        }
        Page<Client> page = clientService.findPage ( new Page<Client> ( request, response ), client );
        model.addAttribute ( "rol", rol );
        model.addAttribute ( "page", page );
        return "modules/CostExcel/Client/ClientList";
    }

    /**
     * 通过id判断新增还是更新分别跳转不同页面
     *
     * @param id client表的的主键id
     * @return
     */
    @RequestMapping(value = "form")
    public String form(String id, HttpServletRequest request, HttpServletResponse response,
                       Model model) {
        if (StringUtils.isNotBlank ( id )) {  /*判断id是否为空或者null*/
            Client client = clientService.getById ( id );
            model.addAttribute ( "client", client );
            return "modules/CostExcel/Client/ClientForm";
        } else {
            String clientId = IdGen.uuid ();
            Client client = new Client ();
            client.setId ( clientId );
            model.addAttribute ( "client", client );
            return "modules/CostExcel/Client/ClientAdd";
        }
    }

    /**
     * 新增一条client记录
     *
     * @param client 页面传过来的client信息
     * @return
     */
    @RequestMapping(value = "add")
    public String add(Client client, HttpServletRequest request, HttpServletResponse response,
                      Model model) {
        client.setCreaterBy ( UserUtils.getUser () );
        client.setCreaterTime ( new Date () );
        clientService.addclient ( client );
        return "redirect:" + adminPath + "/oa/client/list?repage";
    }

    @RequestMapping(value = "update")
    public String update(Client client, HttpServletRequest request, HttpServletResponse response,
                         Model model) {
        clientService.updateclient ( client );
        return "redirect:" + adminPath + "/oa/client/list?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(String id, RedirectAttributes redirectAttributes) {
        clientService.deleteById ( id );
        addMessage ( redirectAttributes, "删除信息成功" );
        return "redirect:" + adminPath + "/oa/client/list?repage";
    }

}
