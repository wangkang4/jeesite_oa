package com.thinkgem.jeesite.modules.oanotify.web;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oanotify.entity.NotifyType;
import com.thinkgem.jeesite.modules.oanotify.service.NotifyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
//@RequestMapping(value="${adminPath}/oa/notifyType")
@RequestMapping(value = "${adminPath}/oanotify/notifyType")
public class OaNotifyTypeController extends BaseController {
    @Autowired
    private NotifyTypeService NotifyTypeService;

    /**
     * 获取通知类型列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "getTypeList")
    public String getTypeList(Model model) {
        List<NotifyType> typeList = NotifyTypeService.getTypeList ();

        model.addAttribute ( "typeList", typeList );
        return "modules/oanotify/oaNotifyTypeList";

    }


    /*	@RequestMapping(value = "addDictForm")
        public String form(NotifyType dict,Model model) {
                System.out.println("------add------");
            return "modules/oanotify/addNotifyTypeForm";
        }

        @RequestMapping("addNotifyType")
        public String addType(Model model,NotifyType dict){
            dict.setId("111111111111");
            NotifyTypeService.addType(dict);

            return "redirect:"+adminPath+"/oanotify/notifyType/getTypeList";}

        @RequestMapping(value="doUpdateNotifyType")
        public String doUpdateNotifyType(NotifyType dict,Model model){
            dict = NotifyTypeService.getTypeById(dict.getId());
            model.addAttribute("dict", dict);
            return "modules/oanotify/updateNotifyTypeForm";
        }

        @RequestMapping("updateNotifyType")
        public String updateNotifyType(NotifyType dict,Model model){
            dict.setType("oa_notify_type");
            System.out.println("remarks"+dict.getRemarks());
            NotifyTypeService.updateNotifyType(dict);
            return "redirect:"+adminPath+"/oanotify/notifyType/getTypeList";
            }*/
    @ModelAttribute
    public NotifyType get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank ( id )) {
            return NotifyTypeService.get ( id );
        } else {
            return new NotifyType ();
        }
    }
	

/*	@RequestMapping(value = {"list", ""})
	public String list(NotifyType dict, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> typeList = NotifyTypeService.findTypeList();
		model.addAttribute("typeList", typeList);
		return "modules/sys/dictList";
	}*/


    @RequestMapping(value = "form")
    public String form(NotifyType dict, Model model) {
        model.addAttribute ( "dict", dict );
        return "modules/oanotify/dictForm";
    }


    @RequestMapping(value = "save")//@Valid
    public String save(NotifyType dict, Model model, RedirectAttributes redirectAttributes) {
/*		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/oanotify/dict/?repage&type="+dict.getType();
		}*/
        if (!beanValidator ( model, dict )) {
            return form ( dict, model );
        }
        System.out.println ( "------dictid-------" + dict.getId () );
        dict.setType ( "oa_notify_type" );//只修改dict表的类型为‘oa_notify_type’的字段
        NotifyTypeService.save ( dict );
        return "redirect:" + adminPath + "/oanotify/notifyType/getTypeList";
    }


    @RequestMapping(value = "delete")
    public String delete(NotifyType dict, RedirectAttributes redirectAttributes) {

        NotifyTypeService.delete ( dict );
        addMessage ( redirectAttributes, "删除类型成功" );
        return "redirect:" + adminPath + "/oanotify/notifyType/getTypeList";
    }


}