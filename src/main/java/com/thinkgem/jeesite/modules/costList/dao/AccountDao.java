package com.thinkgem.jeesite.modules.costList.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.costList.entity.Account;

import java.util.List;

/**
 * 报销账单表Dao层
 *
 * @author shengchanghao
 */
@MyBatisDao
public interface AccountDao extends CrudDao<Account> {

    /**
     * 插入Account集合
     */
    public int insertList(List<Account> list);


}
