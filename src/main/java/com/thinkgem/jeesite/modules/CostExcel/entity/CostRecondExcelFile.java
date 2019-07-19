package com.thinkgem.jeesite.modules.CostExcel.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 批量导入的excel存入数据库的实体类
 *
 * @author tanchaoyang
 */
public class CostRecondExcelFile extends DataEntity<CostRecondExcelFile> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件入库状态  0失败 1成功
     */
    private String state;
    /**
     * 文件的大小
     */
    private String fileSize;
    /**
     * 创建人
     */
    private String createrid;
    /**
     * 创建时间
     */
    private Date creatertime;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreaterid() {
        return createrid;
    }

    public void setCreaterid(String createrid) {
        this.createrid = createrid;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public void setCreatertime(Date creatertime) {
        this.creatertime = creatertime;
    }

    public Date getCreatertime() {
        return creatertime;
    }


}
