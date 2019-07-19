package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * @ClassName: Customer
 * @Description: TODO(客户导出类)
 * @author: WangFucheng
 * @date 2018年1月28日 上午11:03:00
 */
public class CustomerDownLoad extends DataEntity<CustomerDownLoad> {
    private static final long serialVersionUID = 1L;
    /**
     * 客户名字
     */
    private String customerName;
    /**
     * 客户地址
     */
    private String address;
    /**
     * 所属类别(公司级别、办事处级别 自定义见字典)
     */
    private String category;
    /**
     * 所属行业(大企业、矿山、高校、金融、司法 自定义见字典)
     */
    private String industry;
    /**
     * 销售经理的名字
     */
    private String salerName;
    /**
     * 产品经理的名字
     */
    private String producterName;
    private String createByName;

    @ExcelField(title = "客户名称", sort = 20)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @ExcelField(title = "客户地址", sort = 30)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ExcelField(title = "所属类别", sort = 50)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @ExcelField(title = "所属行业", sort = 40)
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @ExcelField(title = "销售经理", sort = 60)
    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    @ExcelField(title = "产品经理", sort = 70)
    public String getProducterName() {
        return producterName;
    }

    public void setProducterName(String producterName) {
        this.producterName = producterName;
    }

    @ExcelField(title = "创建人", sort = 80)
    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }
}
