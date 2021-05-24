package com.tjetc.mapper;

import com.tjetc.domain.Admin;

import java.util.List;

public interface AdminMapper {
    int addAdmin(Admin admin);

    List<Admin> listByName(String name);
}
