package com.net.metadata.service;


import com.net.metadata.entity.InitFltBags;

import java.util.List;

public interface InitFltBagsService {

	public List<InitFltBags> findInit(String ip);

	public List<InitFltBags> findCounts(String plandate, String aircorp,
                                        String fltno, String code);
}
