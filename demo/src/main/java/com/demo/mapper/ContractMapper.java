package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.Contract;

import java.util.List;

public interface ContractMapper extends BaseMapper<Contract> {
    public List<Contract> listAll();
}
