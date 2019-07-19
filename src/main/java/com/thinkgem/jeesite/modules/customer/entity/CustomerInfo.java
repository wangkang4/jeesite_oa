package com.thinkgem.jeesite.modules.customer.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * @author Cxq
 * @ClassName: Customer
 * @Description: 客户基本信息实体类
 * @date 2018年3月14日 下午2:05:05
 */
public class CustomerInfo extends DataEntity<CustomerInfo> {

    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;

    private String id;//客户id
    private String customerName;//客户姓名
    private String company;//就职单位
    private String position;//职位
    private String customerBrithday;//出生日期
    private String hobby;//兴趣爱好
    private String sex;//性别
    private String phone;//电话
    private String adress;//住址
    private String remarks;//备注

    private String createId; //创建者id
    private String updateId; //更新者id

    private String examine;        //审批字段
    private String reject;        //审批意见
//	private List<Resume> resumeList;//客户履历记录
//	private List<Family> familyList;//客户家庭成员信息


    public String getReject() {
        return reject;
    }

    public void setReject(String reject) {
        this.reject = reject;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    private String parentId;            //父节点id
    private String nodeCode;            //节点id
    private String nodeName;            //节点名称

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public CustomerInfo() {
        super ();
    }

    public CustomerInfo(String id) {
        super ( id );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCustomerBrithday() {
        return customerBrithday;
    }

    public void setCustomerBrithday(String customerBrithday) {
        this.customerBrithday = customerBrithday;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

//	public List<Resume> getResumeList() {
//		return resumeList;
//	}
//
//	public void setResumeList(List<Resume> resumeList) {
//		this.resumeList = resumeList;
//	}
//
//	public List<Family> getFamilyList() {
//		return familyList;
//	}
//
//	public void setFamilyList(List<Family> familyList) {
//		this.familyList = familyList;
//	}


}
