package com.thinkgem.jeesite.modules.history.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.history.dao.HistorySummaryDao;
import com.thinkgem.jeesite.modules.history.entity.DownGetSale;
import com.thinkgem.jeesite.modules.history.entity.GetSaleSummary;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.sale.entity.GetSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class HistorySummaryService extends CrudService<HistorySummaryDao, GetSale> {

    @Autowired
    private HistorySummaryDao historySummaryDao;

    public Page<GetSale> findPage(Page<GetSale> page, GetSale getSale) {
        getSale.setPage ( page );
        page.setList ( historySummaryDao.findList ( getSale ) );
        return page;
    }

    public List<DownGetSale> downList(GetSale getSale) {
        return historySummaryDao.downList ( getSale );
    }

    public GetSale getSaleById(String id) {
        return historySummaryDao.getSaleById ( id );
    }

    public List<GetSaleSummary> selectAmount(Date st, Date et) {
        List<GetSaleSummary> list = historySummaryDao.selectAmount ( st, et );
        GetSaleSummary gss = new GetSaleSummary ();
        gss.setUserName ( "合计" );
        for (GetSaleSummary gs : list) {
            gs.setUserName ( UserUtils.get ( gs.getGetSaleUser ().getId () ).getLoginName () );
            gs.setOfficeName ( UserUtils.get ( gs.getGetSaleUser ().getId () ).getOffice ().getName () );
            gs.setAreaName ( UserUtils.get ( gs.getGetSaleUser ().getId () ).getOffice ().getArea ().getName () );

            gss.setDayExpenseOne ( gss.getDayExpenseOne () + gs.getDayExpenseOne () );//出差天数
            gss.setOneExpenseOne ( gss.getOneExpenseOne () + gs.getOneExpenseOne () );//0101
            gss.setOneExpenseTwo ( gss.getOneExpenseTwo () + gs.getOneExpenseTwo () );//0102
            gss.setOneExpenseThree ( gss.getOneExpenseThree () + gs.getOneExpenseThree () );//0103
            gss.setOneExpenseFour ( gss.getOneExpenseFour () + gs.getOneExpenseFour () );//0104
            gss.setOneExpenseFive ( gss.getOneExpenseFive () + gs.getOneExpenseFive () );//0105
            gss.setOneExpenseSix ( gss.getOneExpenseSix () + gs.getOneExpenseSix () );//0106
            gss.setTwoExpenseOne ( gss.getTwoExpenseOne () + gs.getTwoExpenseOne () );//0201
            gss.setThreeExpenseOne ( gss.getThreeExpenseOne () + gs.getThreeExpenseOne () );//0301
            gss.setFourExpenseOne ( gss.getFourExpenseOne () + gs.getFourExpenseOne () );//0401
            gss.setFourExpenseTwo ( gss.getFourExpenseTwo () + gs.getFourExpenseTwo () );//0402
            gss.setFourExpenseThree ( gss.getFourExpenseThree () + gs.getFourExpenseThree () );//0403
            gss.setFourExpenseFour ( gss.getFourExpenseFour () + gs.getFourExpenseFour () );//0404
            gss.setFourExpenseFive ( gss.getFourExpenseFive () + gs.getFourExpenseFive () );//0405
            gss.setFiveExpenseOne ( gss.getFiveExpenseOne () + gs.getFiveExpenseOne () );//0501
            gss.setFiveExpenseTwo ( gss.getFiveExpenseTwo () + gs.getFiveExpenseTwo () );//0502
            gss.setFiveExpenseThree ( gss.getFiveExpenseThree () + gs.getFiveExpenseThree () );//0503
            gss.setFiveExpenseFour ( gss.getFiveExpenseFour () + gs.getFiveExpenseFour () );//0504
            gss.setFiveExpenseFive ( gss.getFiveExpenseFive () + gs.getFiveExpenseFive () );//0505
			/*gss.setSixExpenseOne(gss.getSixExpenseOne()+gs.getSixExpenseOne());//0601
			gss.setSixExpenseTwo(gss.getSixExpenseTwo()+gs.getSixExpenseTwo());//0602
			gss.setSixExpenseThree(gss.getSixExpenseThree()+gs.getSixExpenseThree());//0603
			gss.setSixExpenseFour(gss.getSixExpenseFour()+gs.getSixExpenseFour());//0604
			*/
            gss.setSixExpenseOne ( gss.getSixExpenseOne () + gs.getSixExpenseOne () );//市场推广费0601
            gss.setSevenExpenseOne ( gss.getSevenExpenseOne () + gs.getSevenExpenseOne () );//0701
            gss.setEightExpenseOne ( gss.getEightExpenseOne () + gs.getEightExpenseOne () );//0801
            gss.setNineExpenseOne ( gss.getNineExpenseOne () + gs.getNineExpenseOne () );//0901
            gss.setTenExpenseOne ( gss.getTenExpenseOne () + gs.getTenExpenseOne () );//1001
            gss.setElevenExpenseOne ( gss.getElevenExpenseOne () + gs.getElevenExpenseOne () );//1101
            gss.setElevenExpenseOne ( gss.getElevenExpenseTwo () + gs.getElevenExpenseTwo () );//1102
            gss.setElevenExpenseThree ( gss.getElevenExpenseThree () + gs.getElevenExpenseThree () );//1103
            gss.setElevenExpenseFour ( gss.getElevenExpenseFour () + gs.getElevenExpenseFour () );//1104
            gss.setTwelveExpenseOne ( gss.getTwelveExpenseOne () + gs.getTwelveExpenseOne () );//1201
        }
        list.add ( gss );
        return list;
    }

}
