package com.thinkgem.jeesite.modules.oapms.services;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oapms.dao.CustomerDao;
import com.thinkgem.jeesite.modules.oapms.entity.Customer;
import com.thinkgem.jeesite.modules.oapms.entity.ProjectRelativer;
import com.thinkgem.jeesite.modules.oapms.entity.Replace;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService extends CrudService<CustomerDao, Customer> {
    @Autowired
    private CustomerDao customerDao;

    public Page<Customer> findPage(Page<Customer> page, Customer customer) {
        customer.setPage ( page );
        List<Customer> list = dao.findList ( customer );
        if ("1".equals ( UserUtils.getUser ().getId () )) {
            page.setList ( list );
            return page;
        }
        List<Customer> slist = new ArrayList<Customer> ();
        for (int i = 0; i < list.size (); i++) {
            List<String> idlist = customerDao.selectIdByEmployeesId ( UserUtils.getUser ().getId () );
            for (String customerId : idlist) {
                if (list.get ( i ).getCustomerId ().equals ( customerId )) {
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
    public void insertCustomer(Customer customer) {
        customerDao.insertCustomer ( customer );
    }

    @Transactional(readOnly = false)
    public void insertProjectRelativer(ProjectRelativer projectRelativer) {
        customerDao.insertProjectRelativer ( projectRelativer );
    }

    public List<String> selectManagerByCustomerId(String customerId) {
        return customerDao.selectManagerByCustomerId ( customerId );
    }

    @Transactional(readOnly = false)
    public void deleteCustomer(String customerId) {
        customerDao.deleteCustomerByCustomerId ( customerId );
    }

    @Transactional(readOnly = false)
    public void deleteCustomerRelativerByCustomerId(String customerId) {
        customerDao.deleteCustomerRelativerByCustomerId ( customerId );
    }

    public Customer selectCustomerByCustomerId(String customerId) {
        return customerDao.selectCustomerByCustomerId ( customerId );
    }

    public List<String> selectPersonsByCustomerId(String customerId) {
        return customerDao.selectPersonsByCustomerId ( customerId );
    }

    @Transactional(readOnly = false)
    public void updateCustomerByCustomer(Customer customer) {
        customerDao.updateCustomerByCustomer ( customer );
    }

    public List<Replace> selectCategory() {
        return customerDao.selectCategory ();
    }

    public List<Replace> selectIndustry() {
        return customerDao.selectIndustry ();
    }

    public List<Customer> selectIdAndName() {
        return customerDao.selectIdAndName ();
    }

    public String selectAttachmentById(String attachmentAddress) {
        return customerDao.selectAttachmentById ( attachmentAddress );
    }

    public int judgeCustomerName(String customerName) {
        return customerDao.judgeCustomerName ( customerName );
    }

    @Transactional(readOnly = false)
    public void deleteContactByCustmerId(String customerId) {
        customerDao.deleteContactByCustmerId ( customerId );
    }
}
