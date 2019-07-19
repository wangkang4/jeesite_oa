package com.thinkgem.jeesite.modules.receiptNotice.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.receiptNotice.dao.ReceiptNumberDao;
import com.thinkgem.jeesite.modules.receiptNotice.entity.ReceiptNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReceiptNumberService extends CrudService<ReceiptNumberDao,ReceiptNumber>{
	
	@Autowired
	private ReceiptNumberDao receiptNumberDao;
	
	@Transactional(readOnly = false)
	public void insert(ReceiptNumber rn) {
		receiptNumberDao.insertCount(rn);
		
	}
	@Transactional(readOnly = false)
	public Integer find(ReceiptNumber rn) {
		return receiptNumberDao.find2(rn);
	}
	
}
