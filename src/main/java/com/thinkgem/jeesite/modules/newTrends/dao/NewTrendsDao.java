package com.thinkgem.jeesite.modules.newTrends.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.newTrends.entity.NewTrends;
import com.thinkgem.jeesite.modules.newTrends.entity.NewTrendsText;

import java.util.List;

@MyBatisDao
public interface NewTrendsDao extends CrudDao<NewTrends> {

    List<NewTrends> findList2(NewTrends newTrends);

    void saveNewText(NewTrendsText newText);

    List<String> findText(String text);

    void deleteNewText(String id);

}
