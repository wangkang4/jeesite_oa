/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyRecordDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.weekly.dao.UploadDao;
import com.thinkgem.jeesite.modules.weekly.entity.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通知通告Service
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OaNotifyService extends CrudService<OaNotifyDao, OaNotify> {

    @Autowired
    private OaNotifyRecordDao oaNotifyRecordDao;

    @Autowired
    private SystemService systemService;

    @Autowired
    private UploadDao uploadDao;

    public OaNotify get(String id) {
        OaNotify entity = dao.get ( id );
        return entity;
    }

    /**
     * 获取通知发送记录
     *
     * @param oaNotify
     * @return
     */
    public OaNotify getRecordList(OaNotify oaNotify) {
        oaNotify.setOaNotifyRecordList ( oaNotifyRecordDao.findList ( new OaNotifyRecord ( oaNotify ) ) );
        return oaNotify;
    }

    public Page<OaNotify> find(Page<OaNotify> page, OaNotify oaNotify) {
        oaNotify.setPage ( page );
        page.setList ( dao.findList ( oaNotify ) );
        return page;
    }

    /**
     * 获取通知数目
     *
     * @param oaNotify
     * @return
     */
    public Long findCount(OaNotify oaNotify) {
        return dao.findCount ( oaNotify );
    }


    @Transactional(readOnly = false)
    public void save(OaNotify oaNotify) {

        String content = oaNotify.getContent ();//获取通知公告的正文内容

        String exchangeContent = HtmlUtils.htmlUnescape ( content );//将字符串内容转义为HTML

        oaNotify.setContent ( exchangeContent );//将转义侯=后的内容set至oaNotify对象

//		super.save(oaNotify);

        if (oaNotify.getIsNewRecord ()) {
            oaNotify.preInsert ();
            dao.insert ( oaNotify );
        } else {
            oaNotify.preUpdate ();
            dao.update ( oaNotify );
        }

        String value = oaNotify.getOaNotifyRecordIds ();//获取接受人的用户id

        String[] sourceStrArray = value.split ( "," );

        List<User> list = new ArrayList<User> ();

        for (int i = 0; i < sourceStrArray.length; i++) {

            User user = systemService.getUser ( sourceStrArray[i] );//通过id获取发送人的邮箱账号

            list.add ( user );

        }

        /**
         * 遍历list获取已查找用户的邮箱地址信息
         */
        for (User user : list) {

            /**
             * 判断接收人邮箱是否为空，将内容添加至String Buffer中
             */
            if (StringUtils.isNotBlank ( user.getEmail () )) {

                StringBuffer sb = new StringBuffer ();
                SimpleDateFormat smt = new SimpleDateFormat ( "yyyy-MM-dd hh:mm" );
                String date = smt.format ( oaNotify.getCreateDate () );


                sb.append ( "<table width='500' border='0' cellpadding='0' cellspacing='0'><tr><td>&nbsp;你好：" + user.getName () + "<br/></td></tr><tr><td>&nbsp;通知标题&nbsp;&nbsp;" + oaNotify.getTitle () + "</td></tr><tr><td>&nbsp;通知内容&nbsp;&nbsp;" + exchangeContent + "</td></tr></table>" );

                sb.append ( date );

                String file = oaNotify.getFiles ();//获取上传的附件名
                if (StringUtils.isNotBlank ( file )) {


//				String fileName = encodefile.substring(1, encodefile.length());

                    String[] fileidArray = file.split ( "\\|" );//去除上传路径的第一个'|'

                    String[] fileList = new String[fileidArray.length];

                    String uploadHome = Global.getConfig ( "uploadPath" );

                    for (int i = 0; i < fileidArray.length; i++) {

                        FileModel fileModel = uploadDao.getonefile ( fileidArray[i] );

                        fileModel.setProfid ( oaNotify.getId () );

                        fileModel.setProftype ( "3" );

                        oaNotifyRecordDao.updateFileMessages ( fileModel );

                        fileList[i] = uploadHome + fileModel.getAttapath ();//将上传的路径赋值到新的字符串数组中

                    }
                    System.out.println ( "-----fileList----" + fileList );
                    MailManager.getInstance ().sendEMail ( user.getEmail (), user.getEmail (), "通知公告", sb.toString (), fileList );//发送邮件

                    System.out.println ( "发送邮件" );
                } else {
                    MailManager.getInstance ().sendEMail ( user.getEmail (), user.getEmail (), "通知公告", sb.toString (), new String[0] );//发送邮件

                    System.out.println ( "发送邮件" );
                }


            } else {

                String file = oaNotify.getFiles ();//获取上传的附件名

//					String fileName = encodefile.substring(1, encodefile.length());

                String[] fileidArray = file.split ( "\\|" );//去除上传路径的第一个'|'

                for (int i = 0; i < fileidArray.length; i++) {

                    FileModel fileModel = uploadDao.getonefile ( fileidArray[i] );

                    fileModel.setProfid ( oaNotify.getId () );

                    fileModel.setProftype ( "3" );

                    oaNotifyRecordDao.updateFileMessages ( fileModel );

                }
            }
        }
        //+String file2  = oaNotify.getContent();


        // 更新发送接受人记录
        oaNotifyRecordDao.deleteByOaNotifyId ( oaNotify.getId () );
        if (oaNotify.getOaNotifyRecordList ().size () > 0) {

            oaNotifyRecordDao.insertAll ( oaNotify.getOaNotifyRecordList () );
        }
    }


    /**
     * 更新阅读状态
     */
    @Transactional(readOnly = false)
    public void updateReadFlag(OaNotify oaNotify) {
        OaNotifyRecord oaNotifyRecord = new OaNotifyRecord ( oaNotify );
        oaNotifyRecord.setUser ( oaNotifyRecord.getCurrentUser () );
        oaNotifyRecord.setReadDate ( new Date () );
        oaNotifyRecord.setReadFlag ( "1" );
        oaNotifyRecordDao.update ( oaNotifyRecord );
    }

    public List<FileModel> findfiles(String id) {
        return oaNotifyRecordDao.findfiles ( id );
    }
}