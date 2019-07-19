package com.thinkgem.jeesite.modules.oapms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProject;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectExpense;
import com.thinkgem.jeesite.modules.oapms.entity.PmsProjectHelp;
import com.thinkgem.jeesite.modules.oapms.entity.ProjectRelativer;

import java.util.List;

@MyBatisDao
public interface ProjectHelpDao extends CrudDao<PmsProjectHelp> {

    /**
     * 分页查询某个项目的求助信息
     *
     * @param page
     * @param pmsProjectHelp
     * @return
     */
    List<PmsProjectHelp> findProjectHelpList(PmsProjectHelp pmsProjectHelp);

    /**
     * 通过求助id查找该条求助记录
     *
     * @param id
     * @return
     */
    PmsProjectHelp getOneProjectHelp(String id);

    /**
     * 通过项目id查找该条项目记录
     *
     * @param id
     * @return
     */
    PmsProject getOnePmsProjectByProjectId(String id);

    /**
     * 插入一条项目求助记录
     *
     * @param pmsProjectHelp
     * @return
     */
    int addOneProjectHelp(PmsProjectHelp pmsProjectHelp);

    /**
     * 将协助者插入到项目关系人表中（增加可以查看该项目的权限）
     *
     * @param pr
     */
    void insertHelperToRelative(ProjectRelativer pr);

    /**
     * 更新一条项目求助记录
     *
     * @param pmsProjectHelp
     * @return
     */
    int updateOneProjectHelp(PmsProjectHelp pmsProjectHelp);

    /**
     * 修改项目求助的求助人时删除对应的关系人表中以前的协助者
     *
     * @param prbefore
     * @return
     */
    int deleteHelperInRelative(ProjectRelativer prbefore);

    /**
     * 删除某条项目的某条求助
     *
     * @param id
     */
    void deleteOneProjectHelp(String id);

    /**
     * 分页查询某个项目的费用信息
     *
     * @param pmsProjectExpense
     * @return
     */
    List<PmsProjectExpense> findProjectExpenseList(PmsProjectExpense pmsProjectExpense);

    /**
     * 通过费用id获取某个费用的详情
     *
     * @param id
     * @return
     */
    PmsProjectExpense getOneProjectExpense(String id);

    /**
     * 修改项目费用
     *
     * @param pmsProjectExpense
     */
    int updateOneProjectExpense(PmsProjectExpense pmsProjectExpense);

    /**
     * 插入一条项目费用
     *
     * @param pmsProjectExpense
     * @return
     */
    int addOneProjectExpense(PmsProjectExpense pmsProjectExpense);

    /**
     * 删除某条项目费用
     *
     * @param id
     * @return
     */
    int deleteOneProjectExpense(String id);

}
