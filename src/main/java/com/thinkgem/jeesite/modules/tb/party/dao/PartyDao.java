package com.thinkgem.jeesite.modules.tb.party.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tb.party.entity.Party;

import java.util.List;
/**
 * 
 * @ClassName: PartyDao 
 * @Description: 团建申请持久层/resources/mappings/modules/tb/party/Party.xml
 * @author: WangFucheng
 * @date 2018年8月9日 上午9:35:03 
 *
 */
@MyBatisDao
public interface PartyDao extends CrudDao<Party>{
	List<String> selectTaskId(String procInsId);
	void updateStatu(Party party);
	void updateStatus(String id);
	Party getByProcInsId(String procInsId);
	String selectTaskIdByProcinsId(String procInstId);

	/**
	 * 获取所有数据
	 * @return list
	 */
	List<Party> getAllList(Party dwa);
}
