package com.thinkgem.jeesite.modules.costList.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.costList.entity.CostDetails;
import com.thinkgem.jeesite.modules.costList.service.CostDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 费用统计详情表Controller层
 *
 * @author shengchanghao
 */
@Controller
@RequestMapping("${adminPath}/cost/costDetails")
public class CostDetailsController extends BaseController {

    @Autowired
    private CostDetailsService costDetailsService;

    @ResponseBody
    @RequestMapping("getDetailsList")
    public List<CostDetails> findById(@RequestParam Integer costId) {
        List<CostDetails> costDetails = costDetailsService.findById ( costId );
        return costDetails;
    }


}
