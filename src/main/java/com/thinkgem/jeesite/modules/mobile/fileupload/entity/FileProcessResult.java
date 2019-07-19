package com.thinkgem.jeesite.modules.mobile.fileupload.entity;

import java.util.List;

public class FileProcessResult {

    /**
     * 所有文件上传的状态 全部成功 则成功，有一个失败 则失败
     */
    private boolean state;

    /**
     * 用于描述文件上传额外信息 如 文件上传失败-原因
     */
    private String info;

    /**
     * 文件上传结果
     */
    private List<FileUploadResult> results;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<FileUploadResult> getResults() {
        return results;
    }

    public void setResults(List<FileUploadResult> results) {
        this.results = results;
    }

}
