package com.thinkgem.jeesite.modules.history.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.history.entity.DownGetSale;
import com.thinkgem.jeesite.modules.history.entity.GetSaleSummary;
import com.thinkgem.jeesite.sale.entity.GetSale;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@MyBatisDao
public interface HistorySummaryDao extends CrudDao<GetSale> {
    public GetSale getSaleById(@Param("id") String id);

    public List<GetSaleSummary> selectAmount(@Param("st") Date st, @Param("et") Date et);

    public List<DownGetSale> downList(GetSale getSale);

}
