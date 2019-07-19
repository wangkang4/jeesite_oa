package com.thinkgem.jeesite.modules.tb.party.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.tb.party.entity.Party;
public interface PartyService {
	public void save(Party party);
	public Party get(String id);
	public Page<Party> findPage(Page<Party> page, Party party);
	public void auditSave(Party party);
	public Party getByProcInsId(String procInsId);
}
