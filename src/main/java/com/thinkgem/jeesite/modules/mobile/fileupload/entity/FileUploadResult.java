package com.thinkgem.jeesite.modules.mobile.fileupload.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

public class FileUploadResult extends DataEntity<FileUploadResult> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用于描述文件原名，因为上传后服务器上可能更改名字
     */
    private String fileOriginalFilename;

    /**
     * 用于描述文件最终存储路径
     */
    private String fileFinalPath;

    private long size;

    private Date creatertime;

    private String profId;

    private Integer profType;

    private String createrid;

    /**
     * 用于描述文件上传结果 上传成功 或 失败
     */
    @JsonIgnore
    private boolean state;

    /**
     * 用于描述文件上传额外信息 如 文件上传失败-原因
     */
    @JsonIgnore
    private String info;

    public String getFileOriginalFilename() {
        return fileOriginalFilename;
    }

    public void setFileOriginalFilename(String fileOriginalFilename) {
        this.fileOriginalFilename = fileOriginalFilename;
    }

    public String getFileFinalPath() {
        return fileFinalPath;
    }

    public void setFileFinalPath(String fileFinalPath) {
        this.fileFinalPath = fileFinalPath;
    }

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


    public Date getCreatertime() {
        return creatertime;
    }

    public void setCreatertime(Date creatertime) {
        this.creatertime = creatertime;
    }

    public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId;
    }


    public String getCreaterid() {
        return createrid;
    }

    public void setCreaterid(String createrid) {
        this.createrid = createrid;
    }

    public Integer getProfType() {
        return profType;
    }

    public void setProfType(Integer profType) {
        this.profType = profType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

}
