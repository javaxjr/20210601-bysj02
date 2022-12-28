package com.tjetc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.domain.Admin;
import com.tjetc.mapper.AdminMapper;
import com.tjetc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin);
    }

    @Override
    public PageInfo<Admin> listByName(String name, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.listByName(name);

        PageInfo<Admin> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Admin findById(String id) {
        System.out.println("id = " + id);
        Admin admin = adminMapper.findById(id);
        return admin;
    }

    /*
     * 启动Tomcat时调用
     * */
    @PostConstruct
    public void init() {
        System.out.println("初始化");
    }

    /*
     * 关闭Tomcat时调用
     * */
    @PreDestroy
    public void dostory() {
        System.out.println("销毁方法");
    }
}
