package com.mydata.service;

import com.mydata.mapper.SubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MyService {

    @Autowired
    private SubMapper subMapper;

    public Object select(int id){
        return subMapper.select(id);
    }

    public void insert(HashMap id){
        subMapper.insert(id);
    }

    public List<Object> selectAll(){
        return subMapper.selectAll();
    }
}
