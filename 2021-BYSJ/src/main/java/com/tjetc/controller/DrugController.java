package com.tjetc.controller;

import com.github.pagehelper.PageInfo;
import com.tjetc.domain.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/drug")
public class DrugController {

    @RequestMapping("/add")
    @ResponseBody
    public boolean add(Drug drug, MultipartFile photo, HttpServletRequest request){
        try {
            if (photo!=null&&photo.getSize()>0){
                String realPath = request.getServletContext().getRealPath("/uploud/");
                File file = new File(realPath);
                if (!file.exists()){
                    file.mkdir();
                }
                String filename = photo.getOriginalFilename();
                File file2 = new File(file, filename);
                System.out.println(file2);
                try {
                    photo.transferTo(file2);
                    drug.setPhotopath("uploud/"+filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return true;
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*@RequestMapping("/list")
    @ResponseBody
    public PageInfo<Drug> listName(@RequestParam(defaultValue = "") String name,
                                   @RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "4") Integer pageSize){

        pageNum=pageNum==0?1:pageNum;

        PageInfo<Drug> pageInfo= drugService.list(name,pageNum,pageSize);

        System.out.println("vfhvjh:"+pageInfo);
        return pageInfo;
    }

    @RequestMapping("/list2")
    @ResponseBody
    public PageInfo<Drug> listName2(@RequestParam(defaultValue = "") String name,
                                    @RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "8") Integer pageSize){

        pageNum=pageNum==0?1:pageNum;

        PageInfo<Drug> pageInfo= drugService.list2(name,pageNum,pageSize);
        System.out.println("pageInfo的值是："+pageInfo);
        System.out.println("vfhvjh:"+pageInfo);
        return pageInfo;
    }

    @RequestMapping("/findById")
    public String findById(Integer id, Model model){
        System.out.println("id的值是："+id);
        Drug drug=drugService.findById(id);
        model.addAttribute("d",drug);
        return "drug/drug_updata";
    }

    @RequestMapping("/updata")
    @ResponseBody
    public boolean updata(Drug drug, MultipartFile photo, HttpServletRequest request){
        try {
            if (photo!=null && photo.getSize()>0){
                String realPath = request.getServletContext().getRealPath("/uploud/");
                System.out.println("realPath的路径是："+realPath);
                File file = new File(realPath);
                if (!file.exists()){
                    file.mkdir();
                }
                System.out.println(drug);
                String filename = photo.getOriginalFilename();
                File file1 = new File(file, filename);

                try {
                    photo.transferTo(file1);
                    drug.setPhotopath("uploud/"+filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int i=drugService.update(drug);
                System.out.println("i的值是："+i);
                return true;
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/delById")
    public String delById(Integer id){
        int i=drugService.delById(id);
        return "drug/drug_list";
    }*/
}
