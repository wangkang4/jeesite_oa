package com.thinkgem.jeesite.modules.oanotify.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.modules.oanotify.dao.NotifyTypeDao;
import com.thinkgem.jeesite.modules.oanotify.entity.NotifyType;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class NotifyTypeService extends CrudService<NotifyTypeDao, NotifyType> {

    @Autowired
    NotifyTypeDao dictDao;

    public List<NotifyType> getTypeList() {
        // TODO 自动生成的方法存根
        NotifyType dict = new NotifyType ();
        dict.setType ( "oa_notify_type" );
        return dictDao.findList ( dict );
    }

    public List<String> findTypeList() {
        return dao.findTypeList ( new NotifyType () );
    }


    @Transactional(readOnly = false)
    public void delete(NotifyType dict) {
        super.delete ( dict );
        CacheUtils.remove ( DictUtils.CACHE_DICT_MAP );
    }

    @Transactional(readOnly = false)
    public void addType(NotifyType dict) {
        // TODO 自动生成的方法存根
        dictDao.addType ( dict );
    }

    @Transactional(readOnly = false)
    public void save(NotifyType dict) {
        System.out.println ( "asve  dict   id" + dict.getId () );
        super.save ( dict );
        CacheUtils.remove ( DictUtils.CACHE_DICT_MAP );
    }


    public NotifyType getTypeById(String id) {
        // TODO 自动生成的方法存根
        return dictDao.getTypeById ( id );
    }

    @Transactional(readOnly = false)
    public void updateNotifyType(NotifyType dict) {
        // TODO 自动生成的方法存根
        dictDao.updateNotifyType ( dict );

    }

}
