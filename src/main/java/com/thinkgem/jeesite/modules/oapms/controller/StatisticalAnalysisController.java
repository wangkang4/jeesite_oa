package com.thinkgem.jeesite.modules.oapms.controller;

import com.thinkgem.jeesite.modules.oapms.entity.HighchartsEntity;
import com.thinkgem.jeesite.modules.oapms.services.PmsProjectService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping(value = "${adminPath}/oapms/statistical")
public class StatisticalAnalysisController {
    @Autowired
    private PmsProjectService pmsProjectService;

    @RequestMapping(value = "list")
    public String list() {
        return "modules/oapms/statistical/statisticalList";
    }

    @ResponseBody
    @RequestMapping(value = "get")
    public Map<String, List<HighchartsEntity>> get(String X) {
        Map<String, List<HighchartsEntity>> map = new HashMap<String, List<HighchartsEntity>> ();
        List<HighchartsEntity> list = new ArrayList<HighchartsEntity> ();
        if ("industry".equals ( X )) {
            list = pmsProjectService.selectIndustryAndY ();
            for (HighchartsEntity he : list) {
                he.setCategory ( DictUtils.getDictLabel ( he.getCategory (), "pms_customer_industry", null ) );
            }
        }
        if ("category".equals ( X )) {
            list = pmsProjectService.selectOfficeAndY ();
            for (HighchartsEntity he : list) {
                he.setCategory ( DictUtils.getDictLabel ( he.getCategory (), "pms_customer_office", null ) );
            }
        }
        if ("area".equals ( X )) {
            list = pmsProjectService.selectAreaAndY ();
            for (HighchartsEntity he : list) {
                he.setCategory ( DictUtils.getDictLabel ( he.getCategory (), "pms_customer_area", null ) );
            }
        }
        if ("customer".equals ( X )) {
            list = pmsProjectService.selectCustomerAndY ();
        }
        if ("saler".equals ( X )) {
            list = pmsProjectService.selectSalerAndY ();
        }
        if ("projecter".equals ( X )) {
            list = pmsProjectService.selectProducterAndY ();
        }
        if ("time".equals ( X )) {
            Calendar cal = Calendar.getInstance ();
            int year = cal.get ( Calendar.YEAR ) - 1;
            int month = cal.get ( Calendar.MONTH ) + 2;
            for (int i = 0; i < 12; i++) {

                String ye = year + "";
                String mon = month + "";
                if (month < 10) {
                    mon = "0" + month;
                }
                HighchartsEntity high = pmsProjectService.selectTimeAndY ( ye, mon );
                high.setCategory ( year + "-" + month );
                list.add ( high );
                if (month == 12) {
                    month = 1;
                    year++;
                } else {
                    month++;
                }
            }
        }
        if ("status".equals ( X )) {
            list = pmsProjectService.selectStatusAndY ();
            for (HighchartsEntity he : list) {
                he.setCategory ( DictUtils.getDictLabel ( he.getCategory (), "pms_project_status", null ) );
            }
        }
        if ("productType".equals ( X )) {
            list = pmsProjectService.selectProductTypeAndY ();
            for (HighchartsEntity he : list) {
                he.setCategory ( DictUtils.getDictLabel ( he.getCategory (), "pms_project_productType", null ) );
            }
        }
        map.put ( "list", list );
        return map;
    }
}
