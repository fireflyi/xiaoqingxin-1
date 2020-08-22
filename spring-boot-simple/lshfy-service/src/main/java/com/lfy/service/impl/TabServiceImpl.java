package com.lfy.service.impl;

import com.lfy.dao.slave.mapper.TabMapper;
import com.lfy.domain.Tab;
import com.lfy.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lishihao4 on 2019/7/15.
 * DESC TODO
 */
@Service
public class TabServiceImpl implements TabService {

    @Autowired
    TabMapper tabMapper;

    @Override
    public List<Tab> list() {
        return tabMapper.list();
    }
}
