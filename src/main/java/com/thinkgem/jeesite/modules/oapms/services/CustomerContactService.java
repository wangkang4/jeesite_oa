package com.thinkgem.jeesite.modules.oapms.services;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oapms.dao.CustomerContactDao;
import com.thinkgem.jeesite.modules.oapms.dao.CustomerDao;
import com.thinkgem.jeesite.modules.oapms.entity.CustomerContact;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerContactService extends CrudService<CustomerContactDao, CustomerContact> {
    @Autowired
    private CustomerContactDao customerContactDao;
    @Autowired
    private CustomerDao customerDao;

    public Page<CustomerContact> findPage(Page<CustomerContact> page, CustomerContact customerContact) {
        customerContact.setPage ( page );
        List<CustomerContact> list = dao.findList ( customerContact );
        if ("1".equals ( UserUtils.getUser ().getId () )) {
            page.setList ( list );
            return page;
        }
        List<CustomerContact> slist = new ArrayList<CustomerContact> ();
        for (int i = 0; i < list.size (); i++) {
            List<String> idlist = customerDao.selectIdByEmployeesId ( UserUtils.getUser ().getId () );
            for (String customerId : idlist) {
                if (list.get ( i ).getCustomer ().getCustomerId ().equals ( customerId )) {
                    slist.add ( list.get ( i ) );
                    break;
                } else {
                    continue;
                }
            }
        }
        page.setList ( slist );
        return page;
    }

    @Transactional(readOnly = false)
    public void insertContact(CustomerContact contact) {
        customerContactDao.insertContact ( contact );
    }

    public CustomerContact findContactByContactId(String customerContactId) {
        return customerContactDao.findContactByContactId ( customerContactId );
    }

    @Transactional(readOnly = false)
    public void updateContact(CustomerContact contact) {
        customerContactDao.updateContact ( contact );
    }

    @Transactional(readOnly = false)
    public void deleteCustomerContact(String customerContactId) {
        customerContactDao.deleteCustomerContact ( customerContactId );
    }

    public List<CustomerContact> selectContact() {
        return customerContactDao.selectContact ();
    }
}
