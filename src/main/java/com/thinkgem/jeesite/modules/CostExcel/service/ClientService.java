package com.thinkgem.jeesite.modules.CostExcel.service;


import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.CostExcel.dao.ClientDao;
import com.thinkgem.jeesite.modules.CostExcel.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客户信息的Service层
 *
 * @author tanchaoyang
 */
@Service
@Transactional(readOnly = false)
public class ClientService extends CrudService<ClientDao, Client> {

    @Autowired
    private ClientDao clientDao;

    /**
     * 分页查询所有客户信息
     */
    public Page<Client> findPage(Page<Client> page, Client entity) {
        return super.findPage ( page, entity );
    }

    /**
     * 通过id查找某条client
     *
     * @param id
     * @return
     */
    public Client getById(String id) {
        return clientDao.getById ( id );
    }

    /**
     * 通过id删除某条client
     *
     * @param id
     */
    public void deleteById(String id) {
        clientDao.deleteById ( id );
    }

    /**
     * 新增一条client
     *
     * @param client
     */
    public void addclient(Client client) {
        clientDao.addclient ( client );
    }

    /**
     * 更新某条cleint
     *
     * @param client
     */
    public void updateclient(Client client) {
        clientDao.updateclient ( client );
    }

    /**
     * 项目新增时下拉框选择客户名的list
     */
    public List<Client> getClientList() {
        return clientDao.getClientList ();
    }

    /**
     * 通过id获取name
     *
     * @param clientId
     * @return
     */
    public String getClientName(String clientId) {
        return clientDao.getClientName ( clientId );
    }

    /**
     * 通过name获取id
     *
     * @param clientName
     * @return
     */
    public String getClientId(String clientName) {
        return clientDao.getClientId ( clientName );
    }

}
