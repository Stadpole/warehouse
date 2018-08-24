package com.bupt.service;

import com.bupt.entity.Predict;
import com.bupt.mapper.PredictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by mengying on 2017/9/21.
 */
@Service
@Transactional
public class PredictService{
    @Autowired
    private PredictMapper predictMapper;
    public List<Predict> selectDetail(){
        return predictMapper.selectDetail();
    }

}