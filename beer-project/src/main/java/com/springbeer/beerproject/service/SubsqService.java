package com.springbeer.beerproject.service;

import java.util.List;

import com.springbeer.beerproject.entity.SubFile;
import com.springbeer.beerproject.entity.Subscription;

public interface SubsqService {

	List<Subscription> findSubsqList();
	void writeSubsq(Subscription subscription);
//	void writeSubsq2(Subscription subscription); // 그림 없이 업로드
	List<Subscription> subsqdetail(int subsqNo);
	List<SubFile> subsqDetailFileListBySubsqNo(Subscription subscription);

	
	List<Subscription> findSubsqList(int subsDate);

	List<Subscription> subsqupdate(int subsqNo);
	void subsqdelete(int subsqNo);

	void subsqupdatewrite(Subscription subscription);
	List<Subscription> findMylistByDiv(String subsqDiv);

}
