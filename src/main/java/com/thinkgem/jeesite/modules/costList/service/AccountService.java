package com.thinkgem.jeesite.modules.costList.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.costList.dao.AccountDao;
import com.thinkgem.jeesite.modules.costList.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 报销账单Service层
 *
 * @author shengchanghao
 */
@Service
@Transactional(readOnly = false)
public class AccountService extends CrudService<AccountDao, Account> {
    @Autowired
    private AccountDao accountDao;

    @Override
    public Page<Account> findPage(Page<Account> page, Account entity) {
        return super.findPage ( page, entity );
    }

    /**
     * 插入集合数据
     */
    public int insertList(List<Account> list) {
        return accountDao.insertList ( list );
    }


}
