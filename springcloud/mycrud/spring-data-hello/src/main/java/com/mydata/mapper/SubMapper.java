package com.mydata.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SubMapper {

    Object select(int id);

    List<Object> selectAll();

    void insert(HashMap param);

}
