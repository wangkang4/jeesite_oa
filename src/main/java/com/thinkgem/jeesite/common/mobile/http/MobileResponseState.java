package com.thinkgem.jeesite.common.mobile.http;

public class MobileResponseState {

    /**
     * 操作成功
     */
    public static final String FLAG_OK = "0";

    /**
     * 操作失敗
     */
    public static final String FLAG_NOT_OK = "-1";

    /**
     * 操作失敗 无更多数据-单独区分
     */
    public static final String FLAG_NOT_OK_NO_MORE_DATA = "-2";

    /**
     * 默认操作成功
     */
    public static final MobileResponseState DEFAULT_SUCCESS = new MobileResponseState ( FLAG_OK, "操作成功" );

    /**
     * 默认操作失败-请求参数错误
     */
    public static final MobileResponseState DEFAULT_UNSUPPORT_PARAMS = new MobileResponseState ( FLAG_NOT_OK, "参数不支持" );

    /**
     * 默认操作失败-未知错误
     */
    public static final MobileResponseState DEFAULT_UNKNOW_ERROR = new MobileResponseState ( FLAG_NOT_OK, "未知错误" );

    /**
     * 默认操作失败-未查询到相应数据
     */
    public static final MobileResponseState DEFAULT_NO_DATAS = new MobileResponseState ( FLAG_NOT_OK, "未查询到相应数据" );

    /**
     * 默认操作失败-没有更多数据了
     */
    public static final MobileResponseState DEFAULT_NO_MORE_DATAS = new MobileResponseState ( FLAG_NOT_OK_NO_MORE_DATA,
            "没有更多数据了" );

    /**
     * 默认失败
     */
    private String flag;

    /**
     * 默认未知错误
     */
    private String msg;

    /**
     * 构造函数
     */
    public MobileResponseState() {
        super ();

        this.flag = FLAG_NOT_OK;
        this.msg = "未知错误";

    }

    /**
     * 构造函数
     */
    public MobileResponseState(String flag, String msg) {
        super ();
        this.flag = flag;
        this.msg = msg;
    }

    /**
     * 构造函数
     * <p>
     * msg 失败原因
     */
    public MobileResponseState(String msg) {
        super ();
        this.flag = FLAG_NOT_OK;
        this.msg = msg;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
