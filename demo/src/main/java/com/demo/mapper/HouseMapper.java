package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.House;
import java.util.List;

public interface HouseMapper extends BaseMapper<House> {
    public List<House> listAll();
}
