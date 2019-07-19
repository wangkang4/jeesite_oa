package com.thinkgem.jeesite.modules.oapms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oapms.entity.Customer;
import com.thinkgem.jeesite.modules.oapms.entity.ProjectRelativer;
import com.thinkgem.jeesite.modules.oapms.entity.Replace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface CustomerDao extends CrudDao<Customer> {
    /**
     * @param customer void   返回类型
     * @throws
     * @Title: insertCustomer
     * @Description: TODO(插入一个客户信息)
     * @author: WangFucheng
     */
    public void insertCustomer(Customer customer);

    /**
     * @param projectRelativer
     * @return int   返回类型
     * @throws
     * @Title: insertProjectRelativer
     * @Description: TODO(插入客户时同时插入关联信息)
     * @author: WangFucheng
     */
    public void insertProjectRelativer(ProjectRelativer projectRelativer);

    /**
     * @param custoerId
     * @return List<String>   返回类型
     * @throws
     * @Title: selectManagerByCustoerId
     * @Description: TODO(判断哪些人可以删除客户)
     * @author: WangFucheng
     */
    public List<String> selectManagerByCustomerId(@Param("customerId") String customerId);

    /**
     * @throws
     * @Title: deleteCustomerByCustomerId
     * @Description: TODO(根据客户id删除客户)
     * @author: WangFucheng void   返回类型
     */
    public void deleteCustomerByCustomerId(@Param("customerId") String customerId);

    /**
     * @param customerId void   返回类型
     * @throws
     * @Title: deleteCustomerRelativerByCustomerId
     * @Description: TODO(根据客户id删除相关人 ， 销售经理 ， 产品经理)
     * @author: WangFucheng
     */
    public void deleteCustomerRelativerByCustomerId(@Param("customerId") String customerId);

    /**
     * @param customerId
     * @return Customer   返回类型
     * @throws
     * @Title: selectCustomerByCustomerId
     * @Description: TODO(根据客户id查询客户信息)
     * @author: WangFucheng
     */
    public Customer selectCustomerByCustomerId(@Param("customerId") String customerId);

    /**
     * @param customerId
     * @return List<String>   返回类型
     * @throws
     * @Title: selectPeopleByCustomerId
     * @Description: TODO(根据客户id查询相关人信息)
     * @author: WangFucheng
     */
    public List<String> selectPersonsByCustomerId(@Param("customerId") String customerId);

    /**
     * @param customer void   返回类型
     * @throws
     * @Title: updateCustomerByCustomer
     * @Description: TODO(修改客户信息)
     * @author: WangFucheng
     */
    public void updateCustomerByCustomer(Customer customer);

    /**
     * @return List<String>   返回类型
     * @throws
     * @Title: selectEmployeesId
     * @Description: TODO(根据当前用户查询所有的客户)
     * @author: WangFucheng
     */
    public List<String> selectIdByEmployeesId(@Param("employeesId") String employeesId);

    /**
     * @return List<Replace>   返回类型
     * @throws
     * @Title: selectCategory
     * @Description: TODO(查询Category的存放值和展示值)
     * @author: WangFucheng
     */
    public List<Replace> selectCategory();

    /**
     * @return List<Replace>   返回类型
     * @throws
     * @Title: selectIndustry
     * @Description: TODO(查询Industry的存放值和展示值)
     * @author: WangFucheng
     */
    public List<Replace> selectIndustry();

    /**
     * @return List<Customer>   返回类型
     * @throws
     * @Title: selectIdAndName
     * @Description: TODO(查询所有客户id和客户名称)
     * @author: WangFucheng
     */
    public List<Customer> selectIdAndName();

    /**
     * @return String   返回类型
     * @throws
     * @Title: selectAttachmentById
     * @Description: TODO(根据id查询附件名字)
     * @author: WangFucheng
     */
    public String selectAttachmentById(@Param("attachmentAddress") String attachmentAddress);

    /**
     * @param customerName
     * @return int   返回类型
     * @throws
     * @Title: judgeCustoerName
     * @Description: TODO(判断客户是否存在)
     * @author: WangFucheng
     */
    public int judgeCustomerName(@Param("customerName") String customerName);

    /**
     * @param customerId void   返回类型
     * @throws
     * @Title: deleteContactByCustmerId
     * @Description: TODO(删除客户时同时删除联系人)
     * @author: WangFucheng
     */
    public void deleteContactByCustmerId(@Param("customerId") String customerId);
}
