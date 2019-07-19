package com.thinkgem.jeesite.modules.CostExcel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.CostExcel.entity.Project;

import java.util.List;

/**
 * 项目Dao
 *
 * @author tanchaoyang
 */
@MyBatisDao
public interface ProjectDao extends CrudDao<Project> {

    /**
     * 通过id获取某条项目信息
     *
     * @param id 项目id
     * @return project表信息
     */
    Project getById(String id);

    /**
     * 新增一条project
     *
     * @param project
     */
    void addProject(Project project);

    /**
     * 根据id删除某条项目
     *
     * @param id 项目id
     */
    void deleteProject(String id);

    /**
     * 更新某条项目信息
     */
    void updateProject(Project project);

    /**
     * 根据客户id获取该客户下所有项目集合list
     *
     * @param clientId 客户id
     * @return 项目集合
     */
    List<Project> getProjectList(String clientId);

    /**
     * 通过项目id获取项目名字
     */
    String getProjectName(String projectId);

    /**
     * 通过项目名字获取项目id
     */
    String getProjectId(String projectName);

}
