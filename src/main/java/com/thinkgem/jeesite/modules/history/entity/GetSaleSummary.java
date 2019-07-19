package com.thinkgem.jeesite.modules.history.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * @ClassName: GetSaleSummary
 * @Description: TODO(用于审批汇总中的报销汇总展示和导出)
 * @author: WangFucheng
 * @date 2018年4月11日 下午2:17:18
 */
public class GetSaleSummary extends DataEntity<GetSaleSummary> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private User getSaleUser;
    /**
     * 报销人姓名
     */
    private String userName;
    /**
     * 报销人所属部门
     */
    private String officeName;
    /**
     * 报销人所属区域
     */
    private String areaName;

    //---------31个小类费用统计总和
    /**
     * 出差天数
     */
    private double dayExpenseOne;
    /**
     * 办公用品0101
     */
    private double oneExpenseOne;
    /**
     * 低值易耗品0102
     */
    private double oneExpenseTwo;
    /**
     * 图书资料费0103
     */
    private double oneExpenseThree;
    /**
     * 快递费0104
     */
    private double oneExpenseFour;
    /**
     * 饮用水0105
     */
    private double oneExpenseFive;
    /**
     * 其他办公费用0106
     */
    private double oneExpenseSix;
    /**
     * 座机及网络费用0201
     */
    private double twoExpenseOne;
    /**
     * 交通费0301
     */
    private double threeExpenseOne;
    /**
     * 汽油费0401
     */
    private double fourExpenseOne;
    /**
     * 过路过桥费0402
     */
    private double fourExpenseTwo;
    /**
     * 汽车维修费0403
     */
    private double fourExpenseThree;
    /**
     * 汽车保险费0404
     */
    private double fourExpenseFour;
    /**
     * 其他汽车费用0405
     */
    private double fourExpenseFive;
    /**
     * 城际交通费0501
     */
    private double fiveExpenseOne;
    /**
     * 市内交通费0502
     */
    private double fiveExpenseTwo;
    /**
     * 住宿费0503
     */
    private double fiveExpenseThree;
    /**
     * 差旅补助0504
     */
    private double fiveExpenseFour;
    /**
     * 其他差旅费用0505
     */
    private double fiveExpenseFive;


    /** 专项会务费0601 */
    //private double sixExpenseOne;
    /** 专项业务招待费0602 */
    //private double sixExpenseTwo;
    /** 礼品费0603 */
    //private double sixExpenseThree;
    /** 其他专项费用0604 */
    //private double sixExpenseFour;

    /**
     * 市场推广费0601
     */
    private double sixExpenseOne;

    /**
     * 维修费用0701
     */
    private double sevenExpenseOne;
    /**
     * 会务费0801
     */
    private double eightExpenseOne;
    /**
     * 业务招待餐饮费0901
     */
    private double nineExpenseOne;
    /**
     * 员工培训费1001
     */
    private double tenExpenseOne;
    /**
     * 员工加班餐费1101
     */
    private double elevenExpenseOne;
    /**
     * 下午茶1102
     */
    private double elevenExpenseTwo;
    /**
     * 团建费1103
     */
    private double elevenExpenseThree;
    /**
     * 其他福利费用1104
     */
    private double elevenExpenseFour;
    /**
     * 其他费用1201
     */
    private double twelveExpenseOne;

    @ExcelField(title = "报销人姓名", sort = 10)
    public String getUserName() {
        return userName;
    }

    @ExcelField(title = "报销人部门", sort = 20)
    public String getOfficeName() {
        return officeName;
    }

    @ExcelField(title = "报销人区域", sort = 30)
    public String getAreaName() {
        return areaName;
    }

    public User getGetSaleUser() {
        return getSaleUser;
    }

    @ExcelField(title = "出差天数", sort = 35)
    public double getDayExpenseOne() {
        return dayExpenseOne;
    }

    //费用大类
    @ExcelField(title = "办公费用(大类)", sort = 40)
    public double getOneExpense() {
        return oneExpenseOne + oneExpenseTwo + oneExpenseThree
                + oneExpenseFour + oneExpenseFive + oneExpenseSix;
    }

    @ExcelField(title = "通讯费用(大类)", sort = 50)
    public double getTwoExpense() {
        return twoExpenseOne;
    }

    @ExcelField(title = "交通费用(大类)", sort = 60)
    public double getThreeExpense() {
        return threeExpenseOne;
    }

    @ExcelField(title = "汽车使用费用(大类)", sort = 70)
    public double getFourExpense() {
        return fourExpenseOne + fourExpenseTwo + fourExpenseThree
                + fourExpenseFour + fourExpenseFive;
    }

    @ExcelField(title = "差旅费用(大类)", sort = 80)
    public double getFiveExpense() {
        return fiveExpenseOne + fiveExpenseTwo + fiveExpenseThree
                + fiveExpenseFour + fiveExpenseFive;
    }

    @ExcelField(title = "市场推广费(大类)", sort = 90)
    public double getSixExpense() {
		/*return sixExpenseOne+sixExpenseTwo+sixExpenseThree
				+sixExpenseFour;*/
        return sixExpenseOne;
    }

    @ExcelField(title = "维修费用(大类)", sort = 100)
    public double getSevenExpense() {
        return sevenExpenseOne;
    }

    @ExcelField(title = "会务费(大类)", sort = 110)
    public double getEightExpense() {
        return eightExpenseOne;
    }

    @ExcelField(title = "业务招待费(大类)", sort = 120)
    public double getNineExpense() {
        return nineExpenseOne;
    }

    @ExcelField(title = "培训费(大类)", sort = 130)
    public double getTenExpense() {
        return tenExpenseOne;
    }

    @ExcelField(title = "福利费(大类)", sort = 140)
    public double getElevenExpense() {
        return elevenExpenseOne + elevenExpenseTwo + elevenExpenseThree
                + elevenExpenseFour;
    }

    @ExcelField(title = "其他费用(大类)", sort = 150)
    public double getTwelveExpense() {
        return twelveExpenseOne;
    }

    //费用小类
    @ExcelField(title = "办公用品(办公费用)", sort = 160)
    public double getOneExpenseOne() {
        return oneExpenseOne;
    }

    @ExcelField(title = "低值易耗品(办公费用)", sort = 170)
    public double getOneExpenseTwo() {
        return oneExpenseTwo;
    }

    @ExcelField(title = "图书资料费(办公费用)", sort = 180)
    public double getOneExpenseThree() {
        return oneExpenseThree;
    }

    @ExcelField(title = "快递费(办公费用)", sort = 190)
    public double getOneExpenseFour() {
        return oneExpenseFour;
    }

    @ExcelField(title = "饮用水(办公费用)", sort = 200)
    public double getOneExpenseFive() {
        return oneExpenseFive;
    }

    @ExcelField(title = "其他办公费用(办公费用)", sort = 210)
    public double getOneExpenseSix() {
        return oneExpenseSix;
    }

    @ExcelField(title = "座机及网络费用(通讯费用)", sort = 220)
    public double getTwoExpenseOne() {
        return twoExpenseOne;
    }

    @ExcelField(title = "交通费(交通费用)", sort = 230)
    public double getThreeExpenseOne() {
        return threeExpenseOne;
    }

    @ExcelField(title = "汽油费(汽车使用费用)", sort = 240)
    public double getFourExpenseOne() {
        return fourExpenseOne;
    }

    @ExcelField(title = "过路过桥费(汽车使用费用)", sort = 250)
    public double getFourExpenseTwo() {
        return fourExpenseTwo;
    }

    @ExcelField(title = "汽车维修费(汽车使用费用)", sort = 260)
    public double getFourExpenseThree() {
        return fourExpenseThree;
    }

    @ExcelField(title = "汽车保险费(汽车使用费用)", sort = 270)
    public double getFourExpenseFour() {
        return fourExpenseFour;
    }

    @ExcelField(title = "其他汽车费用(汽车使用费用)", sort = 280)
    public double getFourExpenseFive() {
        return fourExpenseFive;
    }

    @ExcelField(title = "城际交通费(差旅费用)", sort = 290)
    public double getFiveExpenseOne() {
        return fiveExpenseOne;
    }

    @ExcelField(title = "市内交通费(差旅费用)", sort = 300)
    public double getFiveExpenseTwo() {
        return fiveExpenseTwo;
    }

    @ExcelField(title = "住宿费(差旅费用)", sort = 310)
    public double getFiveExpenseThree() {
        return fiveExpenseThree;
    }

    //	@ExcelField(title = "差旅补助(差旅费用)",sort=320)
    public double getFiveExpenseFour() {
        return fiveExpenseFour;
    }

    @ExcelField(title = "其他差旅费用(差旅费用)", sort = 330)
    public double getFiveExpenseFive() {
        return fiveExpenseFive;
    }

    @ExcelField(title = "市场推广费(市场推广费)", sort = 340)
    public double getSixExpenseOne() {
        return sixExpenseOne;
    }

    /*@ExcelField(title = "专项会务费(专项费用)",sort=340)
    public double getSixExpenseOne() {
        return sixExpenseOne;
    }
    @ExcelField(title = "专项业务招待费(专项费用)",sort=350)
    public double getSixExpenseTwo() {
        return sixExpenseTwo;
    }
    @ExcelField(title = "礼品费(专项费用)",sort=360)
    public double getSixExpenseThree() {
        return sixExpenseThree;
    }
    @ExcelField(title = "其他专项费用(专项费用)",sort=370)
    public double getSixExpenseFour() {
        return sixExpenseFour;
    }*/
    @ExcelField(title = "维修费用(维修费用)", sort = 380)
    public double getSevenExpenseOne() {
        return sevenExpenseOne;
    }

    @ExcelField(title = "会务费(会务费)", sort = 390)
    public double getEightExpenseOne() {
        return eightExpenseOne;
    }

    @ExcelField(title = "业务招待费用(业务招待费)", sort = 400)
    public double getNineExpenseOne() {
        return nineExpenseOne;
    }

    @ExcelField(title = "员工培训费(培训费)", sort = 410)
    public double getTenExpenseOne() {
        return tenExpenseOne;
    }

    @ExcelField(title = "员工加班餐费(福利费)", sort = 420)
    public double getElevenExpenseOne() {
        return elevenExpenseOne;
    }

    @ExcelField(title = "下午茶(福利费)", sort = 430)
    public double getElevenExpenseTwo() {
        return elevenExpenseTwo;
    }

    @ExcelField(title = "团建费(福利费)", sort = 440)
    public double getElevenExpenseThree() {
        return elevenExpenseThree;
    }

    @ExcelField(title = "其他福利费用(福利费)", sort = 450)
    public double getElevenExpenseFour() {
        return elevenExpenseFour;
    }

    @ExcelField(title = "其他费用(其他费用)", sort = 460)
    public double getTwelveExpenseOne() {
        return twelveExpenseOne;
    }

    @ExcelField(title = "总计", sort = 470)
    public double getExpense() {
        return oneExpenseOne + oneExpenseTwo + oneExpenseThree
                + oneExpenseFour + oneExpenseFive + oneExpenseSix
                + twoExpenseOne + threeExpenseOne + fourExpenseOne
                + fourExpenseTwo + fourExpenseThree + fourExpenseFour
                + fourExpenseFive + fiveExpenseOne + fiveExpenseTwo
                + fiveExpenseThree + fiveExpenseFour + fiveExpenseFive
                + sixExpenseOne + sevenExpenseOne + eightExpenseOne
                + nineExpenseOne + tenExpenseOne + elevenExpenseTwo
                + elevenExpenseTwo + elevenExpenseThree + elevenExpenseFour
                + twelveExpenseOne;
    }

    public void setGetSaleUser(User getSaleUser) {
        this.getSaleUser = getSaleUser;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setDayExpenseOne(double dayExpenseOne) {
        this.dayExpenseOne = dayExpenseOne;
    }

    //	public void setOneExpense(double oneExpense) {
//		this.oneExpense = oneExpense;
//	}
//	public void setTwoExpense(double twoExpense) {
//		this.twoExpense = twoExpense;
//	}
//	public void setThreeExpense(double threeExpense) {
//		this.threeExpense = threeExpense;
//	}
//	public void setFourExpense(double fourExpense) {
//		this.fourExpense = fourExpense;
//	}
//	public void setFiveExpense(double fiveExpense) {
//		this.fiveExpense = fiveExpense;
//	}
//	public void setSixExpense(double sixExpense) {
//		this.sixExpense = sixExpense;
//	}
//	public void setSevenExpense(double sevenExpense) {
//		this.sevenExpense = sevenExpense;
//	}
//	public void setEightExpense(double eightExpense) {
//		this.eightExpense = eightExpense;
//	}
//	public void setNineExpense(double nineExpense) {
//		this.nineExpense = nineExpense;
//	}
//	public void setTenExpense(double tenExpense) {
//		this.tenExpense = tenExpense;
//	}
//	public void setElevenExpense(double elevenExpense) {
//		this.elevenExpense = elevenExpense;
//	}
//	public void setTwelveExpense(double twelveExpense) {
//		this.twelveExpense = twelveExpense;
//	}
    public void setOneExpenseOne(double oneExpenseOne) {
        this.oneExpenseOne = oneExpenseOne;
    }

    public void setOneExpenseTwo(double oneExpenseTwo) {
        this.oneExpenseTwo = oneExpenseTwo;
    }

    public void setOneExpenseThree(double oneExpenseThree) {
        this.oneExpenseThree = oneExpenseThree;
    }

    public void setOneExpenseFour(double oneExpenseFour) {
        this.oneExpenseFour = oneExpenseFour;
    }

    public void setOneExpenseFive(double oneExpenseFive) {
        this.oneExpenseFive = oneExpenseFive;
    }

    public void setOneExpenseSix(double oneExpenseSix) {
        this.oneExpenseSix = oneExpenseSix;
    }

    public void setTwoExpenseOne(double twoExpenseOne) {
        this.twoExpenseOne = twoExpenseOne;
    }

    public void setThreeExpenseOne(double threeExpenseOne) {
        this.threeExpenseOne = threeExpenseOne;
    }

    public void setFourExpenseOne(double fourExpenseOne) {
        this.fourExpenseOne = fourExpenseOne;
    }

    public void setFourExpenseTwo(double fourExpenseTwo) {
        this.fourExpenseTwo = fourExpenseTwo;
    }

    public void setFourExpenseThree(double fourExpenseThree) {
        this.fourExpenseThree = fourExpenseThree;
    }

    public void setFourExpenseFour(double fourExpenseFour) {
        this.fourExpenseFour = fourExpenseFour;
    }

    public void setFourExpenseFive(double fourExpenseFive) {
        this.fourExpenseFive = fourExpenseFive;
    }

    public void setFiveExpenseOne(double fiveExpenseOne) {
        this.fiveExpenseOne = fiveExpenseOne;
    }

    public void setFiveExpenseTwo(double fiveExpenseTwo) {
        this.fiveExpenseTwo = fiveExpenseTwo;
    }

    public void setFiveExpenseThree(double fiveExpenseThree) {
        this.fiveExpenseThree = fiveExpenseThree;
    }

    public void setFiveExpenseFour(double fiveExpenseFour) {
        this.fiveExpenseFour = fiveExpenseFour;
    }

    public void setFiveExpenseFive(double fiveExpenseFive) {
        this.fiveExpenseFive = fiveExpenseFive;
    }

    public void setSixExpenseOne(double sixExpenseOne) {
        this.sixExpenseOne = sixExpenseOne;
    }

    /*public void setSixExpenseOne(double sixExpenseOne) {
        this.sixExpenseOne = sixExpenseOne;
    }
    public void setSixExpenseTwo(double sixExpenseTwo) {
        this.sixExpenseTwo = sixExpenseTwo;
    }
    public void setSixExpenseThree(double sixExpenseThree) {
        this.sixExpenseThree = sixExpenseThree;
    }
    public void setSixExpenseFour(double sixExpenseFour) {
        this.sixExpenseFour = sixExpenseFour;
    }*/
    public void setSevenExpenseOne(double sevenExpenseOne) {
        this.sevenExpenseOne = sevenExpenseOne;
    }

    public void setEightExpenseOne(double eightExpenseOne) {
        this.eightExpenseOne = eightExpenseOne;
    }

    public void setNineExpenseOne(double nineExpenseOne) {
        this.nineExpenseOne = nineExpenseOne;
    }

    public void setTenExpenseOne(double tenExpenseOne) {
        this.tenExpenseOne = tenExpenseOne;
    }

    public void setElevenExpenseOne(double elevenExpenseOne) {
        this.elevenExpenseOne = elevenExpenseOne;
    }

    public void setElevenExpenseTwo(double elevenExpenseTwo) {
        this.elevenExpenseTwo = elevenExpenseTwo;
    }

    public void setElevenExpenseThree(double elevenExpenseThree) {
        this.elevenExpenseThree = elevenExpenseThree;
    }

    public void setElevenExpenseFour(double elevenExpenseFour) {
        this.elevenExpenseFour = elevenExpenseFour;
    }

    public void setTwelveExpenseOne(double twelveExpenseOne) {
        this.twelveExpenseOne = twelveExpenseOne;
    }

}