package com.net.metadata.service.impl;

import com.net.metadata.mapper.SlaveMapper;
import com.net.metadata.service.SlaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SlaveServiceImpl implements SlaveService {

    @Autowired
    private SlaveMapper slaveMapper;

    @Override
    public Integer count() {
        return slaveMapper.count();
    }

}
