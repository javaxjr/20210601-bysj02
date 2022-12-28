package com.tjetc.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.test.AsyncTest;
import com.tjetc.config.GlobalExceptionHandler;
import com.tjetc.domain.Admin;
import com.tjetc.redisConnecation.JedisClientCluster;
import com.tjetc.service.AdminService;
import com.tjetc.service.FruitService;
import com.tjetc.service.ProductService;
import com.tjetc.springAnotation.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@Slf4j
@Scope("prototype")//配置bean的作用域
@Controller
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    @Autowired(required = false)
    private RedisTemplate<String, String> redisTemplate;

    @Resource(type = FruitService.class)
    private FruitService fruitService;

    @Autowired
    Executor executor;


    @Autowired
    @Qualifier("apple")//当接口有多个实现类时，制定其引用的是哪个实现类的bean
    private Fruit fruit;

    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Value("${wx_appid}")
    public String appid;

    @Autowired
    private JedisClientCluster jedisClientCluster;

    //@Valid下后面紧跟BindingResult result，验证结果保存在result
    //在入参Admin上添加了@Valid做校验，在Admin类里属性上实行实际的特定校验。
    @RequestMapping("/add")
    public String add(Admin admin) {
        System.out.println("admin = " + admin);
        admin.setPermission("IDENTITY");

        if (!StringUtils.isEmpty(admin.getPassword())) {

        }



        /*if (result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError allError : allErrors) {
                System.out.println("allError = " + allError);
            }
        }*/

        //int i=adminService.addAdmin(admin);
        Map<String, Object> map = new HashMap<>();
        map.put("admin", admin);
        //int i=productService.add(product);
        int i = productService.addSave(map, new Admin());
        System.out.println("i = " + i);
        return "redirect:/admin/list";
    }

    @RequestMapping("/redisAdmin")
    @ResponseBody
    public String redisAdmin() {
        String s = jedisClientCluster.get("111");
        System.out.println("s = " + s);
        return s;
    }

    @RequestMapping("/addSave")
    public String addSave(Admin admin) {
        System.out.println("admin = " + admin);

        Map<String, Object> map = new HashMap<>();
        map.put("admin", admin);
        int i = productService.addSave(map, new Admin());
        System.out.println("i = " + i);
        return "redirect:/admin/list";
    }

    @RequestMapping("/modelAndView")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("admin/list");

        PageInfo<Admin> pageInfo = adminService.listByName("", 1, 10);

        modelAndView.addObject("page", pageInfo);

        return modelAndView;
    }

    @RequestMapping("/hello")
    public String sayHello(ModelMap map) {
        System.out.println("say hello ……");

        map.put("name", "mym");

        return "/hello";
    }


    @RequestMapping("/list")
    public String listByName(@RequestParam(defaultValue = "") String name,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "3") Integer pageSize, Model model) {


        System.out.println("appid = " + appid);

        System.out.println("fruitService.hello() = " + fruitService.hello());

        System.out.println("fruit.hello() = " + fruit.hello());

        String handle = globalExceptionHandler.handle(new ValidationException("异常啦"));
        System.out.println("handle = " + handle);

        AsyncTest.testAsyncVoid();

        AsyncTest.testAsyncReturn();

        String string = executor.toString();
        System.out.println("string = " + string);
        System.out.println("string = " + executor.hashCode());

        PageInfo<Admin> pageInfo = adminService.listByName(name, pageNum, pageSize);

        /*String adminByList = jedisClientCluster.get("adminByList");
        if (adminByList == null || adminByList.length() == 0) {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("adminByList", pageInfo.getList());
            jsonArray.add(jsonObject);
            jedisClientCluster.set("adminByList", jsonArray.toJSONString());
        }
        System.out.println("--------------------------------");
        JSONArray jsonArray = JSON.parseArray(adminByList);

        JSONArray adminByListJson = (JSONArray) jsonArray.getJSONObject(0).get("adminByList");
        System.out.println("adminByListJson = " + adminByListJson);
        System.out.println("adminByListJson.size() = " + adminByListJson.size());

        List<Admin> adminList = new ArrayList<>();

        for (int i = 0; i < adminByListJson.size(); i++) {

            JSONObject jsonObject = (JSONObject) adminByListJson.getJSONObject(i);
            Admin a1 = new Admin();
            a1.setPassword(jsonObject.getString("password"));
            a1.setName(jsonObject.getString("name"));
            a1.setAge(Integer.parseInt(jsonObject.getString("age")));
            a1.setUsername(jsonObject.getString("username"));
            a1.setPhone(jsonObject.getString("phone"));
            a1.setPhotopath(jsonObject.getString("photopath"));
            a1.setSex(jsonObject.getString("sex"));
            a1.setPermission(jsonObject.getString("permission"));
            adminList.add(a1);
        }
        log.info("adminList=" + adminList);
*/
        System.out.println("pageInfo = " + pageInfo);
        model.addAttribute("page", pageInfo);
        model.addAttribute("name", name);
        return "admin/list";
    }

    @RequestMapping("/testResponseStatus")
    @ResponseBody
    public String testResponseStatus(int i) {
        String handle = globalExceptionHandler.handle(new ValidationException("异常啦"));
        return handle;
    }

    @CacheEvict(value = "admins", allEntries = true)
    @RequestMapping("/deleteId")
    @ResponseBody
    public void delete(Integer id) {
        System.out.println("id = " + id);
    }

    //调用此方法之前，清除value = "admin"的  缓存
    @CacheEvict(value = "admin", beforeInvocation = true)
    @RequestMapping("/deleteID")
    @ResponseBody
    public String delete(String id) {
        return id;
    }

    //将调用此方法返回的接口 使用cacheable进行缓存
    @Cacheable(value = "admin", key = "#admin.id")
    @RequestMapping("/find")
    @ResponseBody
    public Admin find(String id) {

        Admin admin = adminService.findById(id);

        return admin;
    }


    @PostMapping("/selectByAdmin")
    @ResponseBody
    public JSONObject selectByAdmin(@RequestBody JSONObject requestJson) {
        System.out.println("requestJson = " + requestJson);
        PageInfo<Admin> adminPageInfo = adminService.listByName("", 1, 4);
        return requestJson;
    }

}

