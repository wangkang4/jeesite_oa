/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.customer.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.customer.dao.PmCustomerZtreeDao;
import com.thinkgem.jeesite.modules.customer.entity.PmCustomerZtree;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 单表生成Service
 *
 * @author zhangbingbing
 * @version 2018-03-22
 */
@Service
@Transactional(readOnly = true)
public class PmCustomerZtreeService extends CrudService<PmCustomerZtreeDao, PmCustomerZtree> {

    public PmCustomerZtree get(String id) {
        return super.get ( id );
    }

    public List<PmCustomerZtree> findList(PmCustomerZtree pmCustomerZtree) {
        return super.findList ( pmCustomerZtree );
    }

    public Page<PmCustomerZtree> findPage(Page<PmCustomerZtree> page, PmCustomerZtree pmCustomerZtree) {
        return super.findPage ( page, pmCustomerZtree );
    }

    @Transactional(readOnly = false)
    public void save(PmCustomerZtree pmCustomerZtree) {
        super.save ( pmCustomerZtree );
    }

    @Transactional(readOnly = false)
    public void delete(PmCustomerZtree pmCustomerZtree) {
        super.delete ( pmCustomerZtree );
    }

    public List<PmCustomerZtree> findPmCustomerZtreeList() {

        return dao.findPmCustomerZtreeList ();
    }

    /**
     * 查询指定的客户群
     *
     * @param id
     * @return
     */
    public PmCustomerZtree findPmCustomerZtree(String id) {
        // TODO Auto-generated method stub
        return dao.findPmCustomerZtree ( id );
    }

}