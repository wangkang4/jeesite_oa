package com.thinkgem.jeesite.modules.tb.salarySheet.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 功能描述:
 *
 * @Author:wangzhichao
 * @Date: 2019/4/19$ 17:23$
 */
public class SalarySheet extends DataEntity<SalarySheet> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    private String number;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCardNumber;

    /**
     * 部门
     */
    private String department;

    /**
     * 职务
     */
    private String job;

    /**
     * 基本工资
     */
    private String basePay;

    /**
     * 电脑补助
     */
    private String computerSubsidy;

    /**
     * 奖金/罚款
     */
    private String bonusOrFine;

    /**
     * 交通补助
     */
    private String trafficPay;

    /**
     * 小计(含免税所得)
     */
    private String countFirst;

    /**
     * 免税所得
     */
    private String exemptIncome;

    /**
     * 养老保险
     */
    private String endowmentInsurance;

    /**
     * 医疗保险
     */
    private String medicalInsurance;

    /**
     * 失业保险
     */
    private String unemploymentInsurance;

    /**
     * 住房公积金
     */
    private String housingProvidentFund;

    /**
     * 小计（专项扣除）
     */
    private String countSecond;

    /**
     * 子女教育
     */
    private String childrenEducation;

    /**
     * 继续教育
     */
    private String continuingEducation;

    /**
     * 大病医疗
     */
    private String medicalTreatmentForSeriousIllness;

    /**
     * 住房贷款利息
     */
    private String interestOnHousingLoans;

    /**
     * 住房租金
     */
    private String housingRent;

    /**
     * 赡养老人
     */
    private String supportForTheElderly;

    /**
     * 小计（专心附加扣除）
     */
    private String countThird;

    /**
     * 小计（依法确定的其）
     */
    private String countFourth;

    /**
     * 基本减除额
     */
    private String basicDeductions;

    /**
     * 本月扣除合计
     */
    private String totalDeductionForThisMonth;

    /**
     * 累计收入
     */
    private String accumulatedIncome;

    /**
     * 截至扣除金额
     */
    private String upToDeductionAmount;

    /**
     * 应纳税所得额
     */
    private String taxableIncome;

    /**
     * 适用税率
     */
    private String applicableTaxRate;

    /**
     * 累计应纳个税
     */
    private String accumulatedTaxPayable;

    /**
     * 已缴纳个税
     */
    private String aTaxHasBeenPaid;

    /**
     * 代扣个税
     */
    private String withholdingTax;

    /**
     * 社保个人负担
     */
    private String socialSecurityPersonalBurden;

    /**
     * 小计（公司扣款）
     */
    private String countFivth;

    /**
     * 实发金额
     */
    private String actualAmount;

    /**
     * 邮箱地址
     */
    private String emailAddress;

    /**
     * 领款人签字（备注）
     */
    private String receiverSignature;

    /**
     * 发件人邮箱
     */
    private String emailName;

    /**
     * 发件人密码
     */
    private String password;

    /**
     * 邮件标题
     */
    private String title;

    @ExcelField(title = "序号", sort = 10)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ExcelField(title = "姓名", sort = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title = "身份证号码", sort = 30)
    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    @ExcelField(title = "部门", sort = 40)
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @ExcelField(title = "职务", sort = 50)
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @ExcelField(title = "基本工资", sort = 60)
    public String getBasePay() {
        return basePay;
    }

    public void setBasePay(String basePay) {
        this.basePay = basePay;
    }

    @ExcelField(title = "电脑补助", sort = 70)
    public String getComputerSubsidy() {
        return computerSubsidy;
    }

    public void setComputerSubsidy(String computerSubsidy) {
        this.computerSubsidy = computerSubsidy;
    }

    @ExcelField(title = "奖金/罚款", sort = 80)
    public String getBonusOrFine() {
        return bonusOrFine;
    }

    public void setBonusOrFine(String bonusOrFine) {
        this.bonusOrFine = bonusOrFine;
    }

    @ExcelField(title = "交通补助", sort = 90)
    public String getTrafficPay() {
        return trafficPay;
    }
    public void setTrafficPay(String trafficPay) {
        this.trafficPay = trafficPay;
    }

    @ExcelField(title = "小计(含免税所得)", sort = 100)
    public String getCountFirst() {
        return countFirst;
    }

    public void setCountFirst(String countFirst) {
        this.countFirst = countFirst;
    }

    @ExcelField(title = "含免税所得", sort = 110)
    public String getExemptIncome() {
        return exemptIncome;
    }

    public void setExemptIncome(String exemptIncome) {
        this.exemptIncome = exemptIncome;
    }

    @ExcelField(title = "养老保险", sort = 120)
    public String getEndowmentInsurance() {
        return endowmentInsurance;
    }

    public void setEndowmentInsurance(String endowmentInsurance) {
        this.endowmentInsurance = endowmentInsurance;
    }

    @ExcelField(title = "医疗保险", sort = 130)
    public String getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(String medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    @ExcelField(title = "失业保险", sort = 140)
    public String getUnemploymentInsurance() {
        return unemploymentInsurance;
    }

    public void setUnemploymentInsurance(String unemploymentInsurance) {
        this.unemploymentInsurance = unemploymentInsurance;
    }

    @ExcelField(title = "住房公积金", sort = 150)
    public String getHousingProvidentFund() {
        return housingProvidentFund;
    }

    public void setHousingProvidentFund(String housingProvidentFund) {
        this.housingProvidentFund = housingProvidentFund;
    }

    @ExcelField(title = "小计(专项扣除)", sort = 160)
    public String getCountSecond() {
        return countSecond;
    }

    public void setCountSecond(String countSecond) {
        this.countSecond = countSecond;
    }

    @ExcelField(title = "子女教育", sort = 170)
    public String getChildrenEducation() {
        return childrenEducation;
    }

    public void setChildrenEducation(String childrenEducation) {
        this.childrenEducation = childrenEducation;
    }

    @ExcelField(title = "继续教育", sort = 180)
    public String getContinuingEducation() {
        return continuingEducation;
    }

    public void setContinuingEducation(String continuingEducation) {
        this.continuingEducation = continuingEducation;
    }

    @ExcelField(title = "大病医疗", sort = 190)
    public String getMedicalTreatmentForSeriousIllness() {
        return medicalTreatmentForSeriousIllness;
    }

    public void setMedicalTreatmentForSeriousIllness(String medicalTreatmentForSeriousIllness) {
        this.medicalTreatmentForSeriousIllness = medicalTreatmentForSeriousIllness;
    }

    @ExcelField(title = "住房贷款利息", sort = 200)
    public String getInterestOnHousingLoans() {
        return interestOnHousingLoans;
    }

    public void setInterestOnHousingLoans(String interestOnHousingLoans) {
        this.interestOnHousingLoans = interestOnHousingLoans;
    }

    @ExcelField(title = "住房租金", sort = 210)
    public String getHousingRent() {
        return housingRent;
    }

    public void setHousingRent(String housingRent) {
        this.housingRent = housingRent;
    }

    @ExcelField(title = "赡养老人", sort = 220)
    public String getSupportForTheElderly() {
        return supportForTheElderly;
    }

    public void setSupportForTheElderly(String supportForTheElderly) {
        this.supportForTheElderly = supportForTheElderly;
    }

    @ExcelField(title = "小计(专项附加扣除)", sort = 230)
    public String getCountThird() {
        return countThird;
    }

    public void setCountThird(String countThird) {
        this.countThird = countThird;
    }

    @ExcelField(title = "小计(依法确定的其)", sort = 240)
    public String getCountFourth() {
        return countFourth;
    }

    public void setCountFourth(String countFourth) {
        this.countFourth = countFourth;
    }

    @ExcelField(title = "基本减除额", sort = 250)
    public String getBasicDeductions() {
        return basicDeductions;
    }

    public void setBasicDeductions(String basicDeductions) {
        this.basicDeductions = basicDeductions;
    }

    @ExcelField(title = "本月扣除合计", sort = 260)
    public String getTotalDeductionForThisMonth() {
        return totalDeductionForThisMonth;
    }

    public void setTotalDeductionForThisMonth(String totalDeductionForThisMonth) {
        this.totalDeductionForThisMonth = totalDeductionForThisMonth;
    }

    @ExcelField(title = "累计收入", sort = 270)
    public String getAccumulatedIncome() {
        return accumulatedIncome;
    }

    public void setAccumulatedIncome(String accumulatedIncome) {
        this.accumulatedIncome = accumulatedIncome;
    }

    @ExcelField(title = "截至扣除金额", sort = 280)
    public String getUpToDeductionAmount() {
        return upToDeductionAmount;
    }

    public void setUpToDeductionAmount(String upToDeductionAmount) {
        this.upToDeductionAmount = upToDeductionAmount;
    }

    @ExcelField(title = "应纳税所得额", sort = 290)
    public String getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(String taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    @ExcelField(title = "适用税率", sort = 300)
    public String getApplicableTaxRate() {
        return applicableTaxRate;
    }

    public void setApplicableTaxRate(String applicableTaxRate) {
        this.applicableTaxRate = applicableTaxRate;
    }

    @ExcelField(title = "累计应纳个税", sort = 310)
    public String getAccumulatedTaxPayable() {
        return accumulatedTaxPayable;
    }

    public void setAccumulatedTaxPayable(String accumulatedTaxPayable) {
        this.accumulatedTaxPayable = accumulatedTaxPayable;
    }

    @ExcelField(title = "已缴纳个税", sort = 320)
    public String getaTaxHasBeenPaid() {
        return aTaxHasBeenPaid;
    }

    public void setaTaxHasBeenPaid(String aTaxHasBeenPaid) {
        this.aTaxHasBeenPaid = aTaxHasBeenPaid;
    }

    @ExcelField(title = "代扣个税", sort = 330)
    public String getWithholdingTax() {
        return withholdingTax;
    }

    public void setWithholdingTax(String withholdingTax) {
        this.withholdingTax = withholdingTax;
    }

    @ExcelField(title = "社保个人负担", sort = 340)
    public String getSocialSecurityPersonalBurden() {
        return socialSecurityPersonalBurden;
    }

    public void setSocialSecurityPersonalBurden(String socialSecurityPersonalBurden) {
        this.socialSecurityPersonalBurden = socialSecurityPersonalBurden;
    }

    @ExcelField(title = "小计(公司扣款)", sort = 350)
    public String getCountFivth() {
        return countFivth;
    }

    public void setCountFivth(String countFivth) {
        this.countFivth = countFivth;
    }

    @ExcelField(title = "实发金额", sort = 360)
    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    @ExcelField(title = "邮箱地址", sort = 370)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @ExcelField(title = "领款人签字(备注)", sort = 380)
    public String getReceiverSignature() {
        return receiverSignature;
    }

    public void setReceiverSignature(String receiverSignature) {
        this.receiverSignature = receiverSignature;
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
