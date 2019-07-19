package com.thinkgem.jeesite.modules.purchase.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.purchase.entity.Purchase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface PurchaseDao extends CrudDao<Purchase> {

    /**
     * 修改审核状态
     *
     * @param purchase
     */
    void updateStatu(Purchase purchase);

    /**
     * 获取流程节点
     *
     * @param procInsId
     * @return
     */
    String selectTaskIdByProcinsId(String procInsId);

    /**
     * 第一个审核人意见
     *
     * @param purchase
     */
    void updateProneText(Purchase purchase);

    /**
     * 第二个审核人意见
     *
     * @param purchase
     */
    void updatePrtwoText(Purchase purchase);

    /**
     * 第三个审核人意见
     *
     * @param purchase
     */
    void updatePrthreeText(Purchase purchase);

    /**
     * 第四个审核人意见
     *
     * @param purchase
     */
    void updatePrfourText(Purchase purchase);

    /**
     * 第五个审核人意见
     *
     * @param purchase
     */
    void updatePrfiveText(Purchase purchase);

    /**
     * 通过流程ID获取对象信息
     *
     * @param procInsId
     * @return
     */
    Purchase getByProcInsId(String procInsId);

    /**
     * 删除数据
     *
     * @param procInsId
     */
    void deletePurchase(String procInsId);

    /**
     * 删除流程数据
     *
     * @param procInsId
     */
    void deleteTask(String procInsId);

    /**
     * 行政人员查看所属区域申请列表
     *
     * @param name
     * @return
     */
    List<Purchase> findList2(@Param(value = "name") String name);

    /**
     * 财务人员查看员工申请列表
     *
     * @param purchase
     * @return
     */
    List<Purchase> findList4(Purchase purchase);
}
