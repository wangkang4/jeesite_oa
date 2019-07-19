package com.thinkgem.jeesite.modules.CostExcel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.CostExcel.entity.Client;

import java.util.List;

/**
 * 客户Dao层
 *
 * @author tanchaoyang
 */
@MyBatisDao
public interface ClientDao extends CrudDao<Client> {

    /**
     * 查找id客户client
     *
     * @param id client客户表id
     */
    Client getById(String id);

    /**
     * 删除
     *
     * @param id client客户表id
     */
    void deleteById(String id);

    /**
     * 新增一条客户信息
     **/
    void addclient(Client client);

    /**
     * 更新某条客户信息
     **/
    void updateclient(Client client);

    /**
     * 获取客户信息集合
     **/
    List<Client> getClientList();

    /**
     * 通过id获取客户名字
     **/
    String getClientName(String clientId);

    /**
     * 通过名字获取id
     **/
    String getClientId(String clientName);

}
