package com.thinkgem.jeesite.modules.clock.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.clock.entity.Checkin;

/**
 * @ClassName: CheckinDao
 * @Description: TODO(打卡Dao接口)
 * @author: WangFucheng
 * @date 2017年12月26日 上午11:45:44
 */
@MyBatisDao
public interface CheckinDao1 extends CrudDao<Checkin> {

}
