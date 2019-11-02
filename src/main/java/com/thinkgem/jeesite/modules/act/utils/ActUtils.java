/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.utils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.annotation.FieldName;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.ObjectUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程工具
 *
 * @author ThinkGem
 * @version 2013-11-03
 */
public class ActUtils {

//	private static Logger logger = LoggerFactory.getLogger(ActUtils.class);


    /**
     * 请假流程
     * 组成结构：string[]{"流程标识","业务主表表名"}
     */
    public static final String[] THD_LEAVE = {"thd_leave", "activity_leave2"};

    /**
     * 加班流程
     */
    public static final String[] THD_OVERTIME = {"thd_overtime", "over_time"};

    /**
     * 报销流程
     */
    public static final String[] THD_GETSALE = {"thd_getsale", "get_sale"};

    /**
     * 用印申请
     */
    public static final String[] THD_SEAL = {"thd_seal", "tb_chapter"};

    /**
     * 证照申请
     */
    public static final String[] THD_LICENSES = {"thd_licenses", "tb_licenses"};

    /**
     * 借款申请
     */
    public static final String[] THD_BORROWING = {"thd_borrowing", "tb_borrowing"};

    /**
     * 采购申请
     */
    public static final String[] THD_PURCHASE = {"thd_purchase", "tb_purchase"};

    /**
     * 开票申请
     */
    public static final String[] THD_TICKET = {"thd_ticket", "tb_ticket"};

    /**
     * 专项费用申请
     */
    public static final String[] THD_SPECIALT = {"thd_special", "tb_special"};

    /**
     * 业务招待申请
     */
    public static final String[] THD_BUSINESS = {"thd_business", "tb_business"};

    /**
     * 自驾车申请
     */
    public static final String[] THD_DRVING = {"thd_driving", "tb_driving"};

    /**
     * 出差申请
     */
    public static final String[] THD_TRAVEL = {"thd_travel", "tb_travel"};

    /**
     * 入职申请
     */
    public static final String[] THD_INDUCTION = {"thd_induction", "tb_induction"};

    /**
     * 离职申请
     */
    public static final String[] THD_QUIT = {"thd_quit", "tb_quit"};

    /**
     * 专利申请
     */
    public static final String[] THD_PATENT = {"thd_patent", "tb_patent"};

    /**
     * 团建申请
     */
    public static final String[] THD_PARTY = {"thd_party", "tb_party"};

    /**
     * 对外付款申请
     */
    public static final String[] THD_PAY = {"thd_pay", "tb_pay"};

    /**
     * 合同申请
     */
    public static final String[] THD_CONTRACT = {"thd_contract", "tb_contract"};


    /**
     * 定义流程定义KEY，必须以“PD_”开头
     * 组成结构：string[]{"流程标识","业务主表表名"}
     */
    /**
     * 部门经理 0-王超  1-苏绍清
     */
    //2019.9.30 16:39 添加李燕
    public static final String[] ACT_EXA_DEPART = new String[]{"王超", "苏绍清","李燕"};
    /**
     * 主管 0-迟笑甜 1-李建 2-马建新 3-刘岩峰 4-徐哲军
     */
    public static final String[] ACT_EXA_DIRECTOR = new String[]{"傅立秦", "李建", "徐哲军", "刘岩峰", "徐哲军", "马建新", "迟笑甜"};//2.马建新改成徐哲军  将0迟笑甜改为傅立秦，最后面增加迟笑甜
    /**
     * 行政主管 0-李国强 1-麻青青 2-王涛 3-方娜
     */
    public static final String[] ACT_EXA_STRATIVE = new String[]{"李国强", "麻青青", "王涛","方娜"};
    /**
     * 商务主管 0-马建新
     */
    public static final String[] ACT_EXA_BUSINESS = new String[]{"马建新"};//2019.8.28由谢晨改为马建新
    /**
     * 研发总监 0-傅立秦
     */
    public static final String[] ACT_EXA_DEVELOPMENT = new String[]{"傅立秦"};
    /**
     * 财务 0-李晓萌 1-俞伶群 2-高会敏
     */
    public static final String[] ACT_EXA_FINANCIAL = new String[]{"李晓萌", "俞伶群", "高会敏"};
    /**
     * 采购申请部门领导
     */
    public static final String[] ACT_EXA_PURCHASE = new String[]{"傅立秦", "马建新", "傅立秦", "迟笑甜"};//将2迟笑甜改为傅立秦，最后面增加迟笑甜,2019.8.28由谢晨改为马建新
    /**
     * 采购行政人员
     */
    public static final String[] ACT_EXA_PURCHASE1 = new String[]{"李国强", "麻青青", "王涛","方娜"};
    /**
     * 新增人员
     */
    public static final String[] ACT_EXA_ADD = new String[]{"陈璟","谢晨"};//2019.8.27 陈璟的角色权限赋给谢晨

    /**
     * 高层人员，用于领导流程判断
     */
    public static final String[] ACT_EXA_LEADER = new String[]{"傅立秦", "傅立秦", "顾春雷", "李建", "徐哲军", "刘岩峰", "倪晋", "俞林伟", "徐哲军", "王丙磊", "马建新", "迟笑甜"};//5.马建新改成徐哲军;将1迟笑甜改为傅立秦，最后面增加迟笑甜

    public static final String[] PD_LEAVE = new String[]{"leave", "oa_leave"};
    public static final String[] PD_TEST_AUDIT = new String[]{"test_audit", "oa_test_audit"};
    public static final String[] ACT_LEAVE_AUDIT = new String[]{"leaveprocess", "activity_leave"};
    public static final String[] ACT_LEAVE_AUDIT2 = new String[]{"simpleLeave2", "activity_leave2"};
    //市场部请假流程    模型标识
    public static final String[] ACT_LEAVE_MARKET = new String[]{"marketSimpleLeave2", "activity_leave2"};

    //1.合肥研发报销
    public static final String[] ACT_SALE_GET = new String[]{"getSale1", "get_sale"};
    //2.济南研发报销
    public static final String[] ACT_SALE_GET2 = new String[]{"getSale2", "get_sale"};
    //3.合肥市场报销
    public static final String[] ACT_SALE_GET3 = new String[]{"getSale3", "get_sale"};
    //4.济南市场报销
    public static final String[] ACT_SALE_GET4 = new String[]{"getSale4", "get_sale"};
    //5.其他办事处报销
    public static final String[] ACT_SALE_GET5 = new String[]{"getSale5", "get_sale"};
    //6.领导报销
    public static final String[] ACT_SALE_GET6 = new String[]{"getSale6", "get_sale"};
    //7.北京、山西报销
    public static final String[] ACT_SALE_GET7 = new String[]{"getSale7", "get_sale"};

    //加班流程；
    public static final String[] ACT_OVER_TIME = new String[]{"workOverTime", "over_time"};
    //加班直接到二级审核人名单
    //2019.9.30 添加李燕
    public static final String[] ACT_EXA_TIME = new String[]{"李燕", "王超", "杨连群", "陈岩", "李陈", "毕越", "李冠卿"};

    //请假流程其中最后一个为表名,其他为流程标识
    public static final String[] ACT_LEAVE_ARR = new String[]{"HFLeave", "JNLeave", "HFMarketLeave"
            , "JNMarketLeave", "HDLeave", "SXMarket", "CWLeave", "activity_leave2"};
    //北京总部请假及公司领导请假流程
    public static final String[] ACT_LEAVE_BJ = new String[]{"BJLeave", "activity_leave2"};

    //加班流程其中下标0位实体表名,其他为流程标识下标1合肥研发2济南研发3合肥市场4济南市场5华东区6山西市场7财务8北京总部
    public static final String[] ACT_OVERTIME = new String[]{"over_time", "HFOverTime", "JNOverTime", "HFMarketOverTime",
            "JNMarketOvertime", "HDOverTime", "SXMarketOverTime", "CWOverTime", "BJOverTime"};

    //预审批流程 1.合肥 2.济南 3.北京和领导  4.山西 5.华东（福州，上海，杭州） 专项费用表，业务招待费用表
    public static final String[] ACT_TRIAL_BATCH = new String[]{"tbMoney1", "tbMoney2", "tbMoney3", "tbMoney4", "tbMoney5", "tb_special", "tb_business"};

    //用印申请流程 1.合肥研发 2.济南研发 3.合肥市场 4.济南市场 5.山西市场 6.华东区域（上海，杭州，福州） 7.领导
    public static final String[] ACT_TB_CHAPTER = new String[]{"chapter1", "chapter2", "chapter3", "chapter4", "chapter5", "chapter6", "chapter7", "tb_chapter"};
    //付款申请1.研发付款申请2.市场付款申请3.领导付款申请
    public static final String[] ACT_TB_PAY = new String[]{"Pay1", "Pay2", "Pay3", "tb_pay"};
    /**
     * 0-团建实体表  1-团建流程
     */
    public static final String[] ACT_TB_PARTY = new String[]{"tb_party", "Party1"};
    public static final String[] ACT_TB_CONTRACT = new String[]{"Contract", "tb_contract"};
    public static final String[] ACT_TB_BORROWING = new String[]{"tb_borrowing", "YFBorrowing", "SCBorrowing", "LeaderBorrowing"};
    public static final String[] ACT_TB_LICENSES = new String[]{"tb_licenses", "Licenses"};
    public static final String[] ACT_TB_SIGNET = new String[]{"tb_signet", "Signet", "Signet1"};
    //录用转正申请
    public static final String[] ACT_TB_INDUCTION = new String[]{"tb_induction", "induction1", "induction2", "induction3", "induction4"};
    //自驾车申请
    public static final String[] ACT_TB_DRIVING = new String[]{"tb_driving", "driving1"};
    //开票申请
    public static final String[] ACT_TB_INVOICE = new String[]{"ticket", "tb_ticket"};
    //采购申请
    public static final String[] ACT_TB_PURCHASE = new String[]{"purchase", "tb_purchase"};
    //专利申请
    public static final String[] ACT_TB_PATENT = new String[]{"patent", "tb_patent"};
    /******离职申请******/
    /**
     * 领导层
     */
    public static final String[] ACT_EXA_QUIT = new String[]{"傅立秦", "傅立秦", "李建", "徐哲军", "刘岩峰", "徐哲军", "马建新", "高会敏", "王丙磊", "迟笑甜"};//3.马建新改成徐哲军 ； 将1迟笑甜改为傅立秦，最后面增加迟笑甜,2019.8.28由谢晨改为马建新
    /**
     *
     */
    //2019.9.30 添加李燕
    public static final String[] ACT_EXA_QUIT1 = new String[]{"李燕","俞伶群", "王超", "马建新", "苏绍清", "吴苗苗"};//2019.8.28由谢晨改为马建新
    public static final String[] ACT_EXA_QUIT2 = new String[]{"麻青青", "王涛"};
    public static final String[] ACT_EXA_QUIT3 = new String[]{"李国强", "俞伶群","方娜"};//离职申请ACT_EXA_QUIT3[1]由高会敏改为郭晓敏--2018-12-3,离职申请ACT_EXA_QUIT3[1]由郭晓敏改为俞伶群--2019-6-17
    public static final String[] ACT_EXA = new String[]{"俞跃舒", "傅立秦"};
    //北京离职申请
    public static final String[] ACT_TB_QUIT1 = new String[]{"QuitBeijing", "tb_quit"};
    //合肥离职申请
    public static final String[] ACT_TB_QUIT2 = new String[]{"QuitHefei", "tb_quit"};
    //济南离职申请
    public static final String[] ACT_TB_QUIT3 = new String[]{"QuitJinan", "tb_quit"};
    //山西离职申请
    public static final String[] ACT_TB_QUIT4 = new String[]{"QuitShanxi", "tb_quit"};
    //华东离职申请（上海，杭州，福州）
    public static final String[] ACT_TB_QUIT5 = new String[]{"QuitHuadong", "tb_quit"};
    //领导层离职申请
    public static final String[] ACT_TB_QUIT6 = new String[]{"QuitLingdao", "tb_quit"};
    /**
     * 出差申请
     */
    public static final String[] ACT_TB_TRAVEL = new String[]{"travelApply1", "travelApply2", "tb_travel"};//0:研发，1：非研发
    //出差申请中研发只需一级审批的人员
    //2019.9.30 添加李燕
    public static final String[] ACT_EXA_TRAVEL = new String[]{"李燕", "王超", "杨连群", "柏涛", "陈岩", "王丙磊", "李陈", "毕越", "苏绍清", "田学武", "李冠卿", "傅立秦"};

    @SuppressWarnings({"unused"})
    public static Map<String, Object> getMobileEntity(Object entity, String spiltType) {
        if (spiltType == null) {
            spiltType = "@";
        }
        Map<String, Object> map = Maps.newHashMap ();

        List<String> field = Lists.newArrayList ();
        List<String> value = Lists.newArrayList ();
        List<String> chinesName = Lists.newArrayList ();

        try {
            for (Method m : entity.getClass ().getMethods ()) {
                if (m.getAnnotation ( JsonIgnore.class ) == null && m.getAnnotation ( JsonBackReference.class ) == null && m.getName ().startsWith ( "get" )) {
                    if (m.isAnnotationPresent ( FieldName.class )) {
                        Annotation p = m.getAnnotation ( FieldName.class );
                        FieldName fieldName = (FieldName) p;
                        chinesName.add ( fieldName.value () );
                    } else {
                        chinesName.add ( "" );
                    }
                    if (m.getName ().equals ( "getAct" )) {
                        Object act = m.invoke ( entity, new Object[]{} );
                        Method actMet = act.getClass ().getMethod ( "getTaskId" );
                        map.put ( "taskId", ObjectUtils.toString ( m.invoke ( act, new Object[]{} ), "" ) );
                    } else {
                        field.add ( StringUtils.uncapitalize ( m.getName ().substring ( 3 ) ) );
                        value.add ( ObjectUtils.toString ( m.invoke ( entity, new Object[]{} ), "" ) );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }

        map.put ( "beanTitles", StringUtils.join ( field, spiltType ) );
        map.put ( "beanInfos", StringUtils.join ( value, spiltType ) );
        map.put ( "chineseNames", StringUtils.join ( chinesName, spiltType ) );

        return map;
    }

    /**
     * 获取流程表单URL
     *
     * @param formKey
     * @param act     表单传递参数
     * @return
     */
    public static String getFormUrl(String formKey, Act act) {

        StringBuilder formUrl = new StringBuilder ();

        String formServerUrl = Global.getConfig ( "activiti.form.server.url" );
        if (StringUtils.isBlank ( formServerUrl )) {
            formUrl.append ( Global.getAdminPath () );
        } else {
            formUrl.append ( formServerUrl );
        }
        formUrl.append ( formKey ).append ( formUrl.indexOf ( "?" ) == -1 ? "?" : "&" );
        formUrl.append ( "act.taskId=" ).append ( act.getTaskId () != null ? act.getTaskId () : "" );
        formUrl.append ( "&act.taskName=" ).append ( act.getTaskName () != null ? Encodes.urlEncode ( act.getTaskName () ) : "" );
        formUrl.append ( "&act.taskDefKey=" ).append ( act.getTaskDefKey () != null ? act.getTaskDefKey () : "" );
        formUrl.append ( "&act.procInsId=" ).append ( act.getProcInsId () != null ? act.getProcInsId () : "" );
        formUrl.append ( "&act.procDefId=" ).append ( act.getProcDefId () != null ? act.getProcDefId () : "" );
        formUrl.append ( "&act.status=" ).append ( act.getStatus () != null ? act.getStatus () : "" );
        formUrl.append ( "&id=" ).append ( act.getBusinessId () != null ? act.getBusinessId () : "" );

        return formUrl.toString ();
    }

    /**
     * 转换流程节点类型为中文说明
     *
     * @param type 英文名称
     * @return 翻译后的中文名称
     */
    public static String parseToZhType(String type) {
        Map<String, String> types = new HashMap<String, String> ();
        types.put ( "userTask", "用户任务" );
        types.put ( "serviceTask", "系统任务" );
        types.put ( "startEvent", "开始节点" );
        types.put ( "endEvent", "结束节点" );
        types.put ( "exclusiveGateway", "条件判断节点(系统自动根据条件处理)" );
        types.put ( "inclusiveGateway", "并行处理任务" );
        types.put ( "callActivity", "子流程" );
        return types.get ( type ) == null ? type : types.get ( type );
    }

    public static UserEntity toActivitiUser(User user) {
        if (user == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity ();
        userEntity.setId ( user.getLoginName () );
        userEntity.setFirstName ( user.getName () );
        userEntity.setLastName ( StringUtils.EMPTY );
        userEntity.setPassword ( user.getPassword () );
        userEntity.setEmail ( user.getEmail () );
        userEntity.setRevision ( 1 );
        return userEntity;
    }

    public static GroupEntity toActivitiGroup(Role role) {
        if (role == null) {
            return null;
        }
        GroupEntity groupEntity = new GroupEntity ();
        groupEntity.setId ( role.getEnname () );
        groupEntity.setName ( role.getName () );
        groupEntity.setType ( role.getRoleType () );
        groupEntity.setRevision ( 1 );
        return groupEntity;
    }

    public static void main(String[] args) {
        User user = new User ();
        System.out.println ( getMobileEntity ( user, "@" ) );
    }
}
