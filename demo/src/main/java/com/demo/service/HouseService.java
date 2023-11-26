package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.House;

import java.util.List;

public interface HouseService extends IService<House> {
    public List<House> listAll();
}
