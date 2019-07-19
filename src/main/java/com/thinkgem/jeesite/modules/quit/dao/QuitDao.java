package com.thinkgem.jeesite.modules.quit.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.quit.entity.Quit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface QuitDao extends CrudDao<Quit> {
    /**
     * 修改审核状态
     *
     * @param quit
     */
    void updateStatu(Quit quit);

    /**
     * 获取流程
     *
     * @param procInsId
     * @return
     */
    String selectTaskIdByProcinsId(String procInsId);

    /**
     * 通过流程id获取Quit对象信息
     *
     * @param procInsId
     * @return
     */
    Quit getByProcInsId(String procInsId);

    /**
     * 第一个人审核意见
     *
     * @param quit
     */
    void updateProneText(Quit quit);

    /**
     * 第二个人审核意见
     *
     * @param quit
     */
    void updatePrtwoText(Quit quit);

    /**
     * 第三个人审核意见
     *
     * @param quit
     */
    void updatePrthreeText(Quit quit);

    /**
     * 第四个人审核意见
     *
     * @param quit
     */
    void updatePrfourText(Quit quit);

    /**
     * 第五个人审核意见
     *
     * @param quit
     */
    void updatePrfiveText(Quit quit);

    /**
     * 第六个人审核意见
     *
     * @param quit
     */
    void updatePrsixText(Quit quit);

    /**
     * 获取列表信息
     */
    List<Quit> findAllList();

    /**
     * 获取员工列表信息
     *
     * @return
     */
    List<Quit> findAllList1();

    /**
     * 通过流程实例id删除对应数据
     *
     * @param procInsId
     */
    void deleteQuit(String procInsId);

    /**
     * 通过流程实例id删除对应流程数据
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
    List<Quit> findList2(@Param(value = "name") String name);

    /**
     * 财务商务人员查看员工离职信息列表
     *
     * @param name
     * @return
     */
    List<Quit> findList4(@Param(value = "name") String name);
}
