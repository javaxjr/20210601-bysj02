package com.tjetc.service;

import com.github.pagehelper.PageInfo;
import com.tjetc.domain.Admin;

public interface AdminService {
    int addAdmin(Admin admin);

    PageInfo<Admin> listByName(String name, Integer pageNum, Integer pageSize);

    Admin findById(String id);
}
