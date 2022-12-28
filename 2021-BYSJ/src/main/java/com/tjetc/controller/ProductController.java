package com.tjetc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.tjetc.domain.Comment;
import com.tjetc.domain.Order;
import com.tjetc.domain.Product;
import com.tjetc.service.ProductService;
import com.tjetc.util.ErrorEnumMessage;
import com.tjetc.util.ExcelUtils;
import com.tjetc.util.JsonToken;
import com.tjetc.util.WebMapPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

//import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/midrecvoineinfo")
    public String add(HttpServletRequest request) {

        try {
            BufferedReader reader = request.getReader();
            String str = "";
            String listString = "";
            while ((str = reader.readLine()) != null) {
                listString += str;
            }

            JSONObject jsonObject = JSON.parseObject(listString);
            System.out.println("jsonObject = " + jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "product/productAdd";
    }

    @PostMapping("/add")
    @ResponseBody
    public boolean add(Product product, MultipartFile photo, HttpServletRequest request) {

        System.out.println("product = " + product);
        if (photo != null && photo.getSize() > 0) {
            String realPath = request.getServletContext().getRealPath("/upload/");
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdir();
            }
            String filename = photo.getOriginalFilename();
            File productFile1 = new File(file, filename);

            product.setPhotopath("upload/" + filename);
            try {
                photo.transferTo(productFile1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int i = productService.add(product);
        return i > 0 ? true : false;
    }

    @RequestMapping("/addSave")
    @ResponseBody
    public boolean addSave(Product product) {

        Map<String, Object> map = new HashMap();
        map.put("product", product);
        int i = productService.addSave(map, new Product());
        return true;
    }

    //data对象转换成为JSON字符串
    @RequestMapping(value = "/dataAndJSON", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String dataAndJSOn() {

        List<Product> list = productService.selectByProductJSON();


        String json = "";
        for (Product product : list) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                json += mapper.writeValueAsString(product);
                System.out.println("json = " + json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        System.out.println("json = " + json);
        return json;
    }

    //富文本实现图片上传
    @RequestMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(HttpServletRequest request, @RequestPart(value = "file") MultipartFile file) throws IOException {

        if (file != null && file.getSize() > 0) {

            String realPath = request.getServletContext().getRealPath("/upload/");
            File file1 = new File(realPath);
            if (!file1.exists()) {
                file1.mkdir();
            }

            // 实现名称拼接  用日期去拼接图片的名称  有想法
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
            String res = sdf.format(new Date());
            String filename = file.getOriginalFilename();
            String[] suffixArra = filename.split("\\.");
            String preffix = "data:image/jpg;base64,".replace("jpg", suffixArra[suffixArra.length - 1]);
            //新的文件名称
            String newFileName = res + filename.substring(filename.lastIndexOf("."));

            //转换成为base64
            //BASE64Encoder base64Encoder = new BASE64Encoder();
            //String base64EncoderImg = preffix+base64Encoder.encode(file.getBytes());
            //返回结果
            String url = newFileName;

            //将拼好的新的名称 存到文件夹中
            File file2 = new File(file1, newFileName);
            file.transferTo(file2);

            Map<String, Object> map = new HashMap<>();
            Map<String, Object> dataMap = new HashMap<>();
            map.put("code", 0);//0表示成功 1表示失败
            map.put("msg", "上传成功");//提示信息
            map.put("data", dataMap);
            dataMap.put("src", "upload/" + url);//图片URL

            dataMap.put("title", newFileName);//图片名称，这个会显示在页面中

            String result = new JSONObject(map).toString();
            return result;
        } else {
            return "";
        }
    }


    public String parseHtml(String html, int length) {

        if (html == null || html == "") {
            return html = "空";
        } else {
            if (html.length() < length) {
                return html;
            } else {
                /*
                 * <.*?>为正则表达式，其中的.表示任意字符，*?表示出现0次或0次以上，此方法可以去掉双头标签(双头针对于残缺的标签)
                 * "<.*?"表示<尖括号后的所有字符，此方法可以去掉残缺的标签，及后面的内容
                 * " "，若有多种此种字符，可用同一方法去除
                 */
                html = html.replaceAll("<.*?>", " ").replaceAll("", "");
                html = html.replaceAll("<.*?", "");
                return (html.substring(0, length) + "...");
            }
        }
    }

    //存储富文本框中的数据信息
    @RequestMapping("/addLayuiAndfwb")
    @ResponseBody
    public String addLayuiAndfwb(HttpServletRequest request,
                                 @RequestParam("content") String content) {

        //String s = this.parseHtml(content, content.length());

        System.out.println("content = " + content);
        //Map map = (Map) JSON.parseObject(content);
        Comment comment = new Comment();
        comment.setContent(content);

        return "添加成功";
    }


    @RequestMapping("/listByName")
    @ResponseBody
    public WebMapPageResult listByName(HttpServletRequest request) {


        Integer pageNum = Integer.parseInt(request.getParameter("page"));

        Integer pageSize = Integer.parseInt(request.getParameter("limit"));

        String product_name = request.getParameter("product_name");
        System.out.println("product_name = " + product_name);


        if (product_name != null && product_name.trim().length() != 0) {
            product_name = product_name.trim();
        } else {
            product_name = "";
        }

        System.out.println("pageNum=" + pageNum + " pageSie" + pageSize);

        //线程非安全
        List<Product> list = productService.listByName(product_name, pageNum, pageSize);

        //将获取到的arraryList转换成线程安全
        List<Product> synchronizedList = Collections.synchronizedList(list);
        //MapPageModel pageModel = productService.findMapList(product_name,pageNum,pageSize);
        //System.out.println("list = " + list);
        //MapPageModel mapPageModel = productService.listByName(name,pageNum,pageSize);
        PageInfo<Product> pageInfo = productService.listByNamePageInfo(product_name, pageNum, pageSize);
        //System.out.println("pageInfo = " + pageInfo);

//        return webMapPageResult;  setCount(pageInfo.etTotal())：总数   setData(list)：数据对象
        return WebMapPageResult.success().setCount(pageInfo.getTotal()).setData(pageInfo.getList());
        //return WebMapPageResult.success().setCount(pageModel.getCount()).setData(pageModel.getData());
    }

    //获取数据总数
    @RequestMapping("/selectByProductLen")
    @ResponseBody
    public PageInfo<Product> selectByProductLen(String ys, HttpServletRequest request, @RequestParam(defaultValue = "5") Integer pageSize) {
        Integer pageNum = Integer.parseInt(ys);
        PageInfo<Product> productList = productService.listByNamePageInfo("", pageNum, pageSize);
        return productList;
    }

    //查看退货订单信息
    @RequestMapping("/listByNameAndOrdersReasonsRefund")
    @ResponseBody
    public WebMapPageResult listByNameAndOrdersReasonsRefund(HttpServletRequest request) {
        Integer pageNum = Integer.parseInt(request.getParameter("page"));
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));

        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<Order> pageInfo = productService.listByNameAndOrdersReasonsRefund(map, pageNum, pageSize);

        return WebMapPageResult.success().setData(pageInfo.getList()).setCount(pageInfo.getTotal());
    }

    //批量上传
    @RequestMapping("/addUploadExcel")
    @ResponseBody
    public boolean addUploadExcel(@RequestParam("file") MultipartFile file, Product product) {
        /*System.out.println("excel = " + file);
        String filename = file.getOriginalFilename();
        System.out.println("product = " + product);*/
        boolean b = productService.addUploadExcel(file);
        return b;
    }

    //下载
    @RequestMapping("/downloadProduct")
    @ResponseBody
    public boolean downloadProduct(String url) throws Exception {

        String downName = "product";

        List<Product> productList = productService.listByName("", 1, 10);

        //List list = CreateSimpleExcelToDisk.getProduct();
        productService.productExcel(productList, url, downName);
        return true;
    }


    //批量导入商品信息
    @RequestMapping("/uploadExcelDistributeAccess")
    @ResponseBody
    public boolean uploadExcelDistributeAccess(MultipartFile file, Product product) {

        System.out.println("file = " + file);

        List<Product> list = productService.saveExcelFile(file, new Product());

        System.out.println("list = " + list);

        if (!CollectionUtils.isEmpty(list)) {//CollectionUtils:用来比较
            return true;
        } else {
            // 比较错误信息  并从枚举类中获取  相对应信息
            String errors = ExcelUtils.getErrors();
            if (errors.isEmpty()) {
                errors = ErrorEnumMessage.EMPITYTEMPLATE.getErrorDescribe();
            }
            return false;
        }

    }


    //layui中的table
    @RequestMapping("/searchTestData")
    @ResponseBody
    public JsonToken searchTestData(@RequestParam(defaultValue = "") String name) {
        List<Product> productList = productService.findTestDataByName(name);
        for (Product product : productList) {
            System.out.println("product = " + product);
        }
        JSONObject object = new JSONObject();
        System.out.println("object = " + object);
        return new JsonToken(0, "", productList, productList.size());
    }


    @RequestMapping("/selectByPrm")
    @ResponseBody
    public void selectByPrm() {

    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public JsonToken deleteById(@RequestParam("id") String id) {

        System.out.println("id = " + id);

        return new JsonToken(0, "0", 0, 0);
    }

}
