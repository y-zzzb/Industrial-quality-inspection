package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.Contract;
import com.demo.mapper.ContractMapper;
import com.demo.service.ContractService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements ContractService {
    @Resource
    private ContractMapper contractMapper;
    @Override
    public List<Contract> listAll()
    {
        return contractMapper.listAll();
    }
}
