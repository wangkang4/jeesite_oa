package com.thinkgem.jeesite.modules.CostExcel.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.CostExcel.dao.ProjectDao;
import com.thinkgem.jeesite.modules.CostExcel.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目Service
 *
 * @author tanchaoyang
 */
@Service
@Transactional(readOnly = false)
public class ProjectService extends CrudService<ProjectDao, Project> {

    @Autowired
    private ProjectDao projectDao;

    /**
     * 分页查询项目集合list
     */
    public Page<Project> findPage(Page<Project> page, Project entity) {
        return super.findPage ( page, entity );
    }

    public Project getById(String id) {
        return projectDao.getById ( id );
    }

    public void addProject(Project project) {
        projectDao.addProject ( project );
    }

    public void deleteProject(String id) {
        projectDao.deleteProject ( id );
    }

    public void updateProject(Project project) {
        projectDao.updateProject ( project );
    }

    public List<Project> getProjectList(String clientId) {
        return projectDao.getProjectList ( clientId );
    }

    public String getProjectName(String projectId) {
        return projectDao.getProjectName ( projectId );
    }

    public String getProjectId(String projectName) {
        return projectDao.getProjectId ( projectName );
    }

}
