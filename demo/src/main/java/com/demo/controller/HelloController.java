package com.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.common.QueryPageParam;
import com.demo.common.Result;
import com.demo.entity.Contract;
import com.demo.entity.House;
import com.demo.entity.User;
import com.demo.service.ContractService;
import com.demo.service.HouseService;
import com.demo.service.UserService;
import com.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class HelloController {
    @GetMapping
    public String hello(){
        return "123";
    }
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list(){
        return userService.listAll();
    }

    @PostMapping("/login")
    public Result listLogin(@RequestBody User user) throws IOException {
            System.out.println(user);
            List<User> list=userService.lambdaQuery()
                            .eq(User::getUserName, user.getUserName()).eq(User::getPassword,user.getPassword()).list();
        try(BufferedWriter bufferedWriter =
                    new BufferedWriter(new FileWriter("login.txt")))
        {
            bufferedWriter.write(user.getUserName()+" "+user.getPassword());}
            return list.size()>0?Result.suc(list.get(0), 1L):Result.fall();
    }
    @PostMapping("/logint")
    public Result listLogint(@RequestBody User user){
        System.out.println(user);
        List<User> list=userService.lambdaQuery().eq(User::getUserName, user.getUserName()).list();
        return list.size()>0?Result.suc(list.get(0), 1L):Result.fall();
    }

    @PostMapping("/save")
    public boolean save(@RequestBody User user)
    {
        return userService.save(user);
    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody User user)
    {
        return userService.updateById(user);
    }

    @PostMapping("/saveOrMode")
    public boolean saveOrMod(@RequestBody User user)
    {
        return userService.saveOrUpdate(user);
    }
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return userService.removeById(id);
    }

    @PostMapping("/listP")
    public List<User> listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(user.getName())){
            lambdaQueryWrapper.like(User::getName, user.getName());
        }
        return userService.list(lambdaQueryWrapper);
    }

    @PostMapping("/listPage")
    public List<User> listPage(@RequestBody QueryPageParam param){
        System.out.println(param);
        Page<User> page = new Page();
        page.setCurrent(param.getPageNum());
        page.setSize(param.getPageNum());

       // LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
       // HashMap map = param.getMap();
       // String name = (String)map.get("name");
       // lambdaQueryWrapper.like(User::getName,name);
      //  IPage result = userService.page(page,lambdaQueryWrapper);

        //return result.getRecords();
        return null;
    }
    @PostMapping("/listResult")
    public Result listResult(@RequestBody QueryPageParam param){
        HashMap map = param.getMap();
        String name = (String)map.get("name");
        System.out.println(param);

        Page<User> page = new Page();
        page.setCurrent(param.getPageNum());
        page.setSize(param.getPageNum());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName,name);

        IPage result = userService.page(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
        //return null;
    }



    @Autowired
    private HouseService houseService;

    @GetMapping("/hlist")
    public List<House> hlist(){
        return houseService.listAll();
    }

    @PostMapping("/hlogin")
    public Result hlistLogin(@RequestBody House house){
        System.out.println(house);
        List<House> list=houseService.lambdaQuery().eq(House::getName, house.getName()).list();
        return list.size()>0?Result.suc(list.get(0), 1L):Result.fall();
    }

    @PostMapping("/hsave")
    public boolean hsave(@RequestBody House house)
    {
        return houseService.save(house);
    }

    @PostMapping("/hmod")
    public boolean hmod(@RequestBody House house)
    {
        return houseService.updateById(house);
    }

    @PostMapping("/hsaveOrMode")
    public boolean hsaveOrMod(@RequestBody House house)
    {
        return houseService.saveOrUpdate(house);
    }
    @GetMapping("/hdelete")
    public boolean hdelete(@RequestParam String id){
        return houseService.removeById(id);
    }

    @PostMapping("/hlistP")
    public List<House> hlistP(@RequestBody House house){
        LambdaQueryWrapper<House> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(house.getInformation())){
            lambdaQueryWrapper.eq(House::getStatue, house.getStatue());
        }
        return houseService.list(lambdaQueryWrapper);
    }
    @PostMapping("/hlistI")
    public List<House> hlistI(@RequestBody House house){
        LambdaQueryWrapper<House> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(house.getInformation())){
            lambdaQueryWrapper.eq(House::getStatue, house.getStatue()).like(House::getInformation, house.getInformation());
        }
        return houseService.list(lambdaQueryWrapper);
    }
    @PostMapping("/hlistS")
    public List<House> hlistS(@RequestBody House house){
        LambdaQueryWrapper<House> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(House::getStatue, house.getStatue());
        return houseService.list(lambdaQueryWrapper);
    }
    @PostMapping("/hlistn")
    public List<House> hlistn(@RequestBody House house){
        LambdaQueryWrapper<House> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(House::getName, house.getName());
        return houseService.list(lambdaQueryWrapper);
    }
    @PostMapping("/hlistC")
    public List<House> hlistC(@RequestBody House house) {
        LambdaQueryWrapper<House> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(house.getProvince())) {
            lambdaQueryWrapper.eq(House::getProvince, house.getProvince());
        }
        return houseService.list(lambdaQueryWrapper);
    }


        @PostMapping("/hlistPage")
    public List<House> hlistPage(@RequestBody QueryPageParam param){
        System.out.println(param);
        Page<House> page = new Page();
        page.setCurrent(param.getPageNum());
        page.setSize(param.getPageNum());

        // LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        // HashMap map = param.getMap();
        // String name = (String)map.get("name");
        // lambdaQueryWrapper.like(User::getName,name);
        //  IPage result = userService.page(page,lambdaQueryWrapper);

        //return result.getRecords();
        return null;
    }
    @PostMapping("/hlistResult")
    public Result hlistResult(@RequestBody QueryPageParam param){
        HashMap map = param.getMap();
        String name = (String)map.get("name");
        System.out.println(param);

        Page<House> page = new Page();
        page.setCurrent(param.getPageNum());
        page.setSize(param.getPageNum());

        LambdaQueryWrapper<House> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(House::getName,name);

        IPage result = houseService.page(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
        //return null;
    }

    @Autowired
    private ContractService contractService;
    @PostMapping("/clistS")
    public List<Contract> clistS(@RequestBody Contract contract){
        LambdaQueryWrapper<Contract> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Contract::getName, contract.getName());
        return contractService.list(lambdaQueryWrapper);
    }
    @PostMapping("/clistC")
    public List<Contract> clistC(@RequestBody Contract contract){
        LambdaQueryWrapper<Contract> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Contract::getCustomer, contract.getCustomer());
        return contractService.list(lambdaQueryWrapper);
    }

    @PostMapping("/csaveOrMode")
    public boolean csaveOrMod(@RequestBody Contract contract)
    {
        return contractService.saveOrUpdate(contract);
    }
}
