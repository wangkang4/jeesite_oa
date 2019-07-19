package com.thinkgem.jeesite.modules.oapms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * @ClassName: HighchartsEntity
 * @Description: TODO(highCharts柱状图数据实体类)
 * @author: WangFucheng
 * @date 2018年2月8日 下午12:00:05
 */
public class HighchartsEntity extends DataEntity<HighchartsEntity> {

    private static final long serialVersionUID = 1L;
    /**
     * 存放X轴类别
     */
    private String category;
    /**
     * 存放项目数
     */
    private int num;
    /**
     * 存放销售额
     */
    private double sales;
    /**
     * 存放支出
     */
    private double spending;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public double getSpending() {
        return spending;
    }

    public void setSpending(double spending) {
        this.spending = spending;
    }


}
