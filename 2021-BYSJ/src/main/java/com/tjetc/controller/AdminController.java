package com.tjetc.controller;

import com.github.pagehelper.PageInfo;
import com.tjetc.domain.Admin;
import com.tjetc.domain.Product;
import com.tjetc.service.AdminService;
import com.tjetc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/add")
    public String add(Admin admin){
        System.out.println("admin = " + admin);
        admin.setPermission("IDENTITY");
        //int i=adminService.addAdmin(admin);
        Map<String,Object> map = new HashMap<>();
        map.put("admin",admin);
        //int i=productService.add(product);
        int i =  productService.addSave(map,new Admin());
        System.out.println("i = " + i);
        return "redirect:/admin/list";
    }

    @RequestMapping("/addSave")
    public String addSave(Admin admin){
        System.out.println("admin = " + admin);

        Map<String,Object> map = new HashMap<>();
        map.put("admin",admin);
        int i=productService.addSave(map,new Admin());
        System.out.println("i = " + i);
        return "redirect:/admin/list";
    }

    @RequestMapping("/list")
    public String listByName(@RequestParam(defaultValue = "") String name,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "3") Integer pageSize, Model model){

        PageInfo<Admin> pageInfo=adminService.listByName(name,pageNum,pageSize);
        System.out.println("pageInfo = " + pageInfo);
        model.addAttribute("page",pageInfo);
        model.addAttribute("name",name);
        return "admin/list";
    }

}
