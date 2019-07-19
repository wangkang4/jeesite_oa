package com.thinkgem.jeesite.modules.CostExcel.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

/**
 * 费用统计表实体类
 *
 * @author tanchaoyang
 */
public class CostRecondExcel extends DataEntity<CostRecondExcel> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 客户名
     */
    private String clientName;
    /**
     * 客户id
     */
    private String clientId;
    /**
     * 项目名
     */
    private String projectName;
    /**
     * 项目id
     */
    private String projectId;
    /**
     * 使用金额（万元）
     */
    private String projectMoney;
    /**
     * 使用时间
     */
    private Date useTime;
    /**
     * 差旅费
     */
    private String travelExpense;
    /**
     * 餐费
     */
    private String mealMoney;
    /**
     * 文化礼品（自购）
     */
    private String culturalgiftsPerson;
    /**
     * 文化礼品（公司）
     */
    private String culturalgiftsCompeny;
    /**
     * 其他金额
     */
    private String otherMoney;
    /**
     * 说明
     */
    private String conment;
    /**
     * 类型 1：销售人员，2：技术人员，3：行政人员
     */
    private Integer type;
    /**
     * 类型名
     */
    private String typeName;
    /**
     * 创建人
     */
    private User createrBy;
    /**
     * 创建时间
     */
    private Date createrTime;
    /**
     * 标记审核是否锁定
     */
    private Integer haveSee;

    /**
     * 伪列
     */
    private double total;
    /**
     * 金额总额
     **/
    private Date startTime;
    /**
     * 查询开始时间
     **/
    private Date endTime;
    /**
     * 查询结束时间
     **/
    private String userId;
    /**
     * 人员id
     **/
    private String userName;
    /**
     * 人员名字
     **/
    private String offieId;
    /**
     * 部门id
     **/
    private String offieName;
    /**
     * 部门名字
     **/
    private String exName;

    /**
     * 申报人id（Excel导出）
     **/

    @ExcelField(title = "客户名", sort = 1)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @ExcelField(title = "项目名", sort = 20)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @ExcelField(title = "项目金额", sort = 30)
    public String getProjectMoney() {
        return projectMoney;
    }

    public void setProjectMoney(String projectMoney) {
        this.projectMoney = projectMoney;
    }

    @ExcelField(title = "差旅费", sort = 50)
    public String getTravelExpense() {
        return travelExpense;
    }

    public void setTravelExpense(String travelExpense) {
        this.travelExpense = travelExpense;
    }

    @ExcelField(title = "餐费", sort = 60)
    public String getMealMoney() {
        return mealMoney;
    }

    public void setMealMoney(String mealMoney) {
        this.mealMoney = mealMoney;
    }

    @ExcelField(title = "文化礼品（个人）", sort = 70)
    public String getCulturalgiftsPerson() {
        return culturalgiftsPerson;
    }

    public void setCulturalgiftsPerson(String culturalgiftsPerson) {
        this.culturalgiftsPerson = culturalgiftsPerson;
    }

    @ExcelField(title = "文化礼品（公司）", sort = 90)
    public String getCulturalgiftsCompeny() {
        return culturalgiftsCompeny;
    }

    public void setCulturalgiftsCompeny(String culturalgiftsCompeny) {
        this.culturalgiftsCompeny = culturalgiftsCompeny;
    }

    @ExcelField(title = "其他费用", sort = 80)
    public String getOtherMoney() {
        return otherMoney;
    }

    public void setOtherMoney(String otherMoney) {
        this.otherMoney = otherMoney;
    }

    @ExcelField(title = "说明", sort = 100)
    public String getConment() {
        return conment;
    }

    public void setConment(String conment) {
        this.conment = conment;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @ExcelField(title = "使用时间", sort = 40)
    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public User getCreaterBy() {
        return createrBy;
    }

    public void setCreaterBy(User createrBy) {
        this.createrBy = createrBy;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOffieId() {
        return offieId;
    }

    public void setOffieId(String offieId) {
        this.offieId = offieId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOffieName() {
        return offieName;
    }

    public void setOffieName(String offieName) {
        this.offieName = offieName;
    }

    @ExcelField(title = "申请人", sort = 110)
    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public Integer getHaveSee() {
        return haveSee;
    }

    public void setHaveSee(Integer haveSee) {
        this.haveSee = haveSee;
    }

}
