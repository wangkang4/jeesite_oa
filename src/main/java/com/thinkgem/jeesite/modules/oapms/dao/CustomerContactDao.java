package com.thinkgem.jeesite.modules.oapms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oapms.entity.CustomerContact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface CustomerContactDao extends CrudDao<CustomerContact> {
    /**
     * @param contact void   返回类型
     * @throws
     * @Title: selectContact
     * @Description: TODO(插入联系人)
     * @author: WangFucheng
     */
    public void insertContact(CustomerContact contact);

    /**
     * @param customerContactId
     * @return CustomerContact   返回类型
     * @throws
     * @Title: findContactByContactId
     * @Description: TODO(根据联系人Id查询联系人信息)
     * @author: WangFucheng
     */
    public CustomerContact findContactByContactId(@Param("customerContactId") String customerContactId);

    /**
     * @param contact void   返回类型
     * @throws
     * @Title: updateContact
     * @Description: TODO(修改联系人)
     * @author: WangFucheng
     */
    public void updateContact(CustomerContact contact);

    /**
     * @param customerContactId void   返回类型
     * @throws
     * @Title: deleteCustomerContact
     * @Description: TODO(根据联系人id删除联系人)
     * @author: WangFucheng
     */
    public void deleteCustomerContact(@Param("customerContactId") String customerContactId);

    /**
     * @return List<CustomerContact>   返回类型
     * @throws
     * @Title: selectContact
     * @Description: TODO(下拉选所有的联系人)
     * @author: WangFucheng
     */
    public List<CustomerContact> selectContact();
}
