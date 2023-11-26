package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.User;

import java.util.List;


public interface UserService extends IService<User> {
    public List<User> listAll();

}
