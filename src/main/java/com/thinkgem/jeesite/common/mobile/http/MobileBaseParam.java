
package com.thinkgem.jeesite.common.mobile.http;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 */
public class MobileBaseParam {

    /**
     * 应用程序标识
     */
    private String appId;

    /**
     * 渠道号
     */
    private String channels;

    /**
     * 请求IP
     */
    private String requestIp;

    /**
     * 协议版本号，默认1.0
     */
    private String ver = "1.0";

    /**
     * 客户端版本
     */
    private String clientVer;

    /**
     * 网络接入点
     */
    private String apn;

    /**
     * 设备号
     */
    private String deviceId;

    /**
     * 用户ID
     */
    private String uid;

    /**
     * 纬度
     */
    private double valat;

    /**
     * 经度
     */
    private double lalong;

    /**
     * 城市
     */
    private String city;

    /**
     * 平台
     */
    private String platform;

    /**
     * 手机分辨率
     */
    private String resolution;

    /**
     * 手机品牌
     */
    private String brand;

    /**
     * 运营商
     */
    private String carrier;

    /**
     * 手机型号
     */
    private String deviceType;

    /**
     *
     */
    private String platformVer;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getClientVer() {
        return clientVer;
    }

    public void setClientVer(String clientVer) {
        this.clientVer = clientVer;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getValat() {
        return valat;
    }

    public void setValat(double valat) {
        this.valat = valat;
    }

    /**
     * @return the lalong
     */
    public double getLalong() {
        return lalong;
    }

    /**
     * @param lalong the lalong to set
     */
    public void setLalong(double lalong) {
        this.lalong = lalong;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getPlatformVer() {
        return platformVer;
    }

    public void setPlatformVer(String platformVer) {
        this.platformVer = platformVer;
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
