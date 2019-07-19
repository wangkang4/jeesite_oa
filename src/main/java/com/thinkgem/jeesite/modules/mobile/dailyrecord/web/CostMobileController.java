package com.thinkgem.jeesite.modules.mobile.dailyrecord.web;

import com.thinkgem.jeesite.common.mobile.http.MobileRequest;
import com.thinkgem.jeesite.common.mobile.http.MobileResponse;
import com.thinkgem.jeesite.common.mobile.http.MobileResponseState;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.CostExcel.entity.CostRecondExcel;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/mobile/cost")
public class CostMobileController {

    @Autowired
    private CostService costService;

    /**
     * 获取通知公告列表
     *
     * @return
     */
    @RequestMapping(value = "getList")
    public @ResponseBody
    MobileResponse<List<CostRecondExcel>> getCost(
            @RequestBody MobileRequest<HashMap<String, Object>> request) {
        if (!MobileRequest.checkIllegalAll ( request ) || !MobileRequest.checkParamsMapKey ( request.getParam (), "offset" )) {
            return new MobileResponse<List<CostRecondExcel>> ( MobileResponseState.DEFAULT_UNSUPPORT_PARAMS );
        }
        Integer offset = (Integer) request.getParam ().get ( "offset" );
        String id = request.getBase ().getUid ();
        List<CostRecondExcel> list = costService.findAllCost ( id, 10, offset );


        if (list != null && list.size () > 0) {
            for (CostRecondExcel crx : list) {
                //根据type类型插入分组
                if (crx.getType () == 1) {
                    crx.setTypeName ( "销售人员" );
                } else if (crx.getType () == 2) {
                    crx.setTypeName ( "技术人员" );
                } else if (crx.getType () == 3) {
                    crx.setTypeName ( "行政人员" );
                } else {
                    crx.setTypeName ( "未标示的类型" );
                }
                //计算全部费用总额
                Double total = 0.0;

                //各个费用String转double方便计算
                Double travelExpense = 0.0;
                try {
                    if (StringUtils.isNotBlank ( crx.getTravelExpense () )) {
                        travelExpense = Double.parseDouble ( crx.getTravelExpense () );
                    }
                } catch (Exception e) {
                    e.printStackTrace ();
                }

                Double mealMoney = 0.0;
                try {
                    if (StringUtils.isNotBlank ( crx.getMealMoney () )) {
                        mealMoney = Double.parseDouble ( crx.getMealMoney () );
                    }
                } catch (Exception e) {
                    e.printStackTrace ();
                }

                Double culturalgiftsPerson = 0.0;
                try {
                    if (StringUtils.isNotBlank ( crx.getCulturalgiftsPerson () )) {
                        culturalgiftsPerson = Double.parseDouble ( crx.getCulturalgiftsPerson () );
                    }
                } catch (Exception e) {
                    e.printStackTrace ();
                }

                Double culturalgiftsCompeny = 0.0;
                try {
                    if (StringUtils.isNotBlank ( crx.getCulturalgiftsCompeny () )) {
                        culturalgiftsCompeny = Double.parseDouble ( crx.getCulturalgiftsCompeny () );
                    }
                } catch (Exception e) {
                    e.printStackTrace ();
                }

                Double otherMoney = 0.0;
                try {
                    if (StringUtils.isNotBlank ( crx.getOtherMoney () )) {
                        otherMoney = Double.parseDouble ( crx.getOtherMoney () );
                    }
                } catch (Exception e) {
                    e.printStackTrace ();
                }

                total = culturalgiftsCompeny + culturalgiftsPerson + otherMoney + travelExpense + mealMoney;

                crx.setTotal ( total );

            }

            return new MobileResponse<List<CostRecondExcel>> ( list );

        } else {
            if (offset == 0) {

                return new MobileResponse<List<CostRecondExcel>> ( MobileResponseState.DEFAULT_NO_DATAS );

            } else {

                return new MobileResponse<List<CostRecondExcel>> ( MobileResponseState.DEFAULT_NO_MORE_DATAS );
            }

        }
    }

    /**
     * 通知公告详情（客户端）
     *
     * @return
     */
//	@RequestMapping(value = "/mNewsShare/{newsId}", method = RequestMethod.GET)
//	public String indexShow(Model model, @PathVariable("newsId") String newsId) throws Exception {
    @RequestMapping(value = "costDetail/{id}", method = RequestMethod.GET)
    public String getdetail(@PathVariable("id") String id, Model model) {
//		@RequestMapping(value = "costDetail",method=RequestMethod.GET)
//		public String getdetail(@RequestParam String id, HttpServletRequest request,Model model) {

        CostRecondExcel costRecondExcel = costService.getone ( id );
        if (costRecondExcel.getType () == 1) {
            costRecondExcel.setTypeName ( "销售" );
        } else if (costRecondExcel.getType () == 2) {
            costRecondExcel.setTypeName ( "技术" );
        } else if (costRecondExcel.getType () == 3) {
            costRecondExcel.setTypeName ( "行政" );
        } else {
            costRecondExcel.setTypeName ( "未标示的类型" );
        }
        model.addAttribute ( "costRecondExcel", costRecondExcel );

        return "mobile/modules/costdetail";
    }

}
