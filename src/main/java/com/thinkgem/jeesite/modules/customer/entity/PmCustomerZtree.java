/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.customer.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 单表生成Entity
 *
 * @author zhangbingbing
 * @version 2018-03-22
 */
public class PmCustomerZtree extends DataEntity<PmCustomerZtree> {

    private static final long serialVersionUID = 1L;
    private String parentId;        // parent_id
    private String name;        // name
    private String projectIndustry;        //项目行业
    private String label;                //标签

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public PmCustomerZtree() {
        super ();
    }

    public String getProjectIndustry() {
        return projectIndustry;
    }

    public void setProjectIndustry(String projectIndustry) {
        this.projectIndustry = projectIndustry;
    }

    public PmCustomerZtree(String id) {
        super ( id );
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Length(min = 0, max = 64, message = "name长度必须介于 0 和 64 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}