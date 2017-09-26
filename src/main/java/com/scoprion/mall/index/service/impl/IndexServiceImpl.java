package com.scoprion.mall.index.service.impl;

import com.scoprion.mall.index.service.IndexService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2017/9/25.
 */
@Service
public class IndexServiceImpl implements IndexService {


    @Override
    public List<String> findAll() {

        String[] str = {"1", "2", "3", "4"};

        return Arrays.asList(str);
    }
}
