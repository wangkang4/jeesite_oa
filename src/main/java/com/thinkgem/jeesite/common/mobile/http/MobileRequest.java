
package com.thinkgem.jeesite.common.mobile.http;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Map;

/**
 *
 */
public class MobileRequest<T> {

    /**
     * 客户端请求的基本参数
     */
    private MobileBaseParam base;

    /**
     * 客户端请求的额外参数
     * <p>
     * 类型如 HashMap<String,Object>, UserEntity ...
     * <p>
     * 无额外参数 可传入 MobileEntityEmpty
     */
    private T param;

    /**
     * 校验客户端请求的合法性
     * <p>
     * 校验 额外参数 代表有额外参数传入
     */
    public static final boolean checkIllegalAll(MobileRequest<?> request) {

        if (request == null) {

            return false;
        }
        if (request.getBase () == null) {

            return false;
        }
        if (request.getParam () == null) {

            return false;
        }

        return true;

    }

    /**
     * 校验客户端请求的合法性
     * <p>
     * 不校验 额外参数 代表无额外参数传入
     */
    public static final boolean checkIllegalBase(MobileRequest<?> request) {

        if (request == null) {

            return false;
        }
        if (request.getBase () == null) {

            return false;
        }
        return true;

    }

    /**
     * 校验客户端请求的合法性
     * <p>
     * 校验 额外参数类型为 Map<String,?> 下
     * <p>
     * key值是否存在
     */
    public static final boolean checkParamsMapKey(Map<String, ?> map, String key) {

        return map.containsKey ( key );
    }

    /**
     * @return the base
     */
    public MobileBaseParam getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(MobileBaseParam base) {
        this.base = base;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString ( this, ToStringStyle.SHORT_PREFIX_STYLE );
    }
}
