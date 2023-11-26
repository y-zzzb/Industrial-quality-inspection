package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.Contract;

import java.util.List;


public interface ContractService extends IService<Contract> {
    public List<Contract> listAll();

}
