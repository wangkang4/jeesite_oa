/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.customer.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.customer.entity.PmCustomerZtree;

import java.util.List;

/**
 * 单表生成DAO接口
 *
 * @author zhangbingbing
 * @version 2018-03-22
 */
@MyBatisDao
public interface PmCustomerZtreeDao extends CrudDao<PmCustomerZtree> {

    List<PmCustomerZtree> findPmCustomerZtreeList();

    /**
     * 查询指定的客户群
     *
     * @param id
     * @return
     */
    PmCustomerZtree findPmCustomerZtree(String id);

}