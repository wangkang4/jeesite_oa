package com.thinkgem.jeesite.modules.clock.service.serviceImpl;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.clock.dao.CheckinDao1;
import com.thinkgem.jeesite.modules.clock.entity.Checkin;
import com.thinkgem.jeesite.modules.clock.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckinServiceImpl extends CrudService<CheckinDao1, Checkin> implements CheckinService {
    @Autowired
    private CheckinDao1 checkinDao;

    /**
     * 获取页码和列表信息
     */
    public Page<Checkin> findPage(Page<Checkin> page, Checkin Checkin) {
        return super.findPage ( page, Checkin );
    }
}
