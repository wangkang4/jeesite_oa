package com.thinkgem.jeesite.common.mobile.http;


public class MobileResponse<T> {

    /**
     * 返回的数据状态
     */
    private MobileResponseState base;


    /**
     * 返回的数据 无数据返回 可传入 MobileEntityEmpty
     */
    private T data;


    public MobileResponse() {
        super ();
    }


    /**
     * 构造函数
     * <p>
     * 返回状态base
     * <p>
     * 返回数据 data
     */
    public MobileResponse(MobileResponseState base, T data) {
        super ();
        this.base = base;
        this.data = data;
    }


    /**
     * 构造函数
     * <p>
     * 默认返回状态成功
     * <p>
     * 返回数据 data
     */
    public MobileResponse(T data) {
        super ();
        this.base = MobileResponseState.DEFAULT_SUCCESS;
        this.data = data;
    }


    /**
     * 构造函数
     * <p>
     * 返回状态-失败原因
     */
    public MobileResponse(MobileResponseState base) {
        super ();
        this.base = base;
    }


    public MobileResponseState getBase() {
        return base;
    }

    public void setBase(MobileResponseState base) {
        this.base = base;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
