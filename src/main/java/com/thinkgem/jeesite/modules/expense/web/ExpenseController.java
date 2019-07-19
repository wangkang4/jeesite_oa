package com.thinkgem.jeesite.modules.expense.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.expense.entity.CostDescription;
import com.thinkgem.jeesite.modules.expense.entity.CostType;
import com.thinkgem.jeesite.modules.expense.service.CostDescriptionService;
import com.thinkgem.jeesite.modules.expense.service.CostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 报销流程控制层，包括费用分类获取，通过id获取费用描述
 *
 * @author vat
 */
@Controller
@RequestMapping("${adminPath}/expense/expense")
public class ExpenseController extends BaseController {

    @Autowired
    private CostTypeService costTypeService;
    @Autowired
    private CostDescriptionService costDescriptionService;

    /**
     * 界面传输费用类型list
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("type")
    public List<CostType> findCostType() {
        List<CostType> costType = costTypeService.findCostType ();
        return costType;
    }

    /**
     * 传输费用描述list
     *
     * @param costTypeId
     * @return
     */
    @ResponseBody
    @RequestMapping("description")
    public List<CostDescription> findById(@RequestParam String costTypeId) {
        List<CostDescription> costDescription = costDescriptionService.findById ( costTypeId );
        return costDescription;

    }


}
