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
import com.tjetc.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;



    @PostMapping("/midrecvoineinfo")
    public String add(HttpServletRequest request){

        try {
            BufferedReader reader = request.getReader();
            String str = "";
            String listString = "";
            while ((str = reader.readLine())!=null){
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
    public boolean add(Product product, MultipartFile photo, HttpServletRequest request){

        System.out.println("product = " + product);
        if (photo!=null && photo.getSize()>0){
            String realPath = request.getServletContext().getRealPath("/upload/");
            File file = new File(realPath);
            if (!file.exists()){
                file.mkdir();
            }
            String filename = photo.getOriginalFilename();
            File productFile1 = new File(file, filename);

            product.setPhotopath("upload/"+filename);
            try {
                photo.transferTo(productFile1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int i=productService.add(product);
        return i>0?true:false;
    }

    @RequestMapping("/addSave")
    @ResponseBody
    public boolean addSave(Product product){

       Map<String,Object> map = new HashMap();
       map.put("product",product);
       int i =  productService.addSave(map,new Product());
       return true;
    }

    //data??????????????????JSON?????????
    @RequestMapping(value = "/dataAndJSON",method = RequestMethod.GET,produces="application/json;charset=utf-8" )
    @ResponseBody
    public String dataAndJSOn(){

        List<Product> list = productService.selectByProductJSON();


        String json="";
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

    //???????????????????????????
    @RequestMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(HttpServletRequest request,@RequestPart(value = "file") MultipartFile file) throws IOException {

        if (file!=null && file.getSize()>0){

            String realPath = request.getServletContext().getRealPath("/upload/");
            File file1 = new File(realPath);
            if (!file1.exists()){
                file1.mkdir();
            }

            // ??????????????????  ?????????????????????????????????  ?????????
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
            String res = sdf.format(new Date());
            String filename = file.getOriginalFilename();
            String[] suffixArra = filename.split("\\.");
            String preffix="data:image/jpg;base64,".replace("jpg", suffixArra[suffixArra.length - 1]);
            //??????????????????
            String newFileName = res+filename.substring(filename.lastIndexOf("."));

            //????????????base64
            //BASE64Encoder base64Encoder = new BASE64Encoder();
            //String base64EncoderImg = preffix+base64Encoder.encode(file.getBytes());
            //????????????
            String url=newFileName;

            //???????????????????????? ??????????????????
            File file2 = new File(file1, newFileName);
            file.transferTo(file2);

            Map<String,Object> map = new HashMap<>();
            Map<String,Object> dataMap = new HashMap<>();
            map.put("code",0);//0???????????? 1????????????
            map.put("msg","????????????");//????????????
            map.put("data",dataMap);
            dataMap.put("src","upload/"+url);//??????URL

            dataMap.put("title",newFileName);//??????????????????????????????????????????

            String result = new JSONObject(map).toString();
            return result;
        }else {
         return "";
        }
    }

    public String parseHtml(String html,int length) {

        if(html == null || html == "") {
            return html = "???";
        }else {
            if(html.length()<length){
                return html;
            }else {
                /*
                 * <.*?>??????????????????????????????.?????????????????????*?????????????0??????0?????????????????????????????????????????????(??????????????????????????????)
                 * "<.*?"??????<???????????????????????????????????????????????????????????????????????????????????????
                 * " "??????????????????????????????????????????????????????
                 */
                html = html.replaceAll("<.*?>", " ").replaceAll("", "");
                html = html.replaceAll("<.*?", "");
                return (html.substring(0, length) + "...");
            }
        }
    }

    //????????????????????????????????????
    @RequestMapping("/addLayuiAndfwb")
    @ResponseBody
    public String addLayuiAndfwb(HttpServletRequest request,
                                 @RequestParam("content")String content){

        //String s = this.parseHtml(content, content.length());

        System.out.println("content = " + content);
        //Map map = (Map) JSON.parseObject(content);
        Comment comment = new Comment();
        comment.setContent(content);

        return "????????????";
    }


    @RequestMapping("/listByName")
    @ResponseBody
    public WebMapPageResult listByName(HttpServletRequest request){


        Integer pageNum = Integer.parseInt(request.getParameter("page"));

        Integer pageSize = Integer.parseInt(request.getParameter("limit"));

        String product_name = request.getParameter("product_name");
        System.out.println("product_name = " + product_name);


        if (product_name!=null && product_name.trim().length()!=0){
            product_name=product_name.trim();
        }else {
            product_name="";
        }

        System.out.println("pageNum="+pageNum+" pageSie"+pageSize);

        List<Product> list= productService.listByName(product_name, pageNum, pageSize);
        //MapPageModel pageModel = productService.findMapList(product_name,pageNum,pageSize);
        //System.out.println("list = " + list);
        //MapPageModel mapPageModel = productService.listByName(name,pageNum,pageSize);
        PageInfo<Product> pageInfo=productService.listByNamePageInfo(product_name, pageNum, pageSize);
        //System.out.println("pageInfo = " + pageInfo);

//        return webMapPageResult;  setCount(pageInfo.etTotal())?????????   setData(list)???????????????
        return WebMapPageResult.success().setCount(pageInfo.getTotal()).setData(pageInfo.getList());
        //return WebMapPageResult.success().setCount(pageModel.getCount()).setData(pageModel.getData());
    }

    //??????????????????
    @RequestMapping("/selectByProductLen")
    @ResponseBody
    public PageInfo<Product> selectByProductLen(String ys,HttpServletRequest request,@RequestParam(defaultValue = "5") Integer pageSize){
        Integer pageNum = Integer.parseInt(ys);
        PageInfo<Product> productList = productService.listByNamePageInfo("", pageNum, pageSize);
        return productList;
    }

    //????????????????????????
    @RequestMapping("/listByNameAndOrdersReasonsRefund")
    @ResponseBody
    public WebMapPageResult listByNameAndOrdersReasonsRefund(HttpServletRequest request){
        Integer pageNum = Integer.parseInt(request.getParameter("page"));
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));

        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<Order> pageInfo = productService.listByNameAndOrdersReasonsRefund(map,pageNum,pageSize);

        return WebMapPageResult.success().setData(pageInfo.getList()).setCount(pageInfo.getTotal());
    }

    //????????????
    @RequestMapping("/addUploadExcel")
    @ResponseBody
    public boolean addUploadExcel(@RequestParam("file") MultipartFile file,Product product) {
        /*System.out.println("excel = " + file);
        String filename = file.getOriginalFilename();
        System.out.println("product = " + product);*/
        boolean b = productService.addUploadExcel(file);
        return b;
    }

    //??????
    @RequestMapping("/downloadProduct")
    @ResponseBody
    public boolean downloadProduct(String url) throws Exception {

        String downName = "product";

        List<Product> productList = productService.listByName("", 1, 10);

        //List list = CreateSimpleExcelToDisk.getProduct();
        productService.productExcel(productList,url,downName);
        return true;
    }


    //????????????????????????
    @RequestMapping("/uploadExcelDistributeAccess")
    @ResponseBody
    public boolean uploadExcelDistributeAccess(MultipartFile file,Product product){

        System.out.println("file = " + file);

        List<Product> list = productService.saveExcelFile(file,new Product());

        System.out.println("list = " + list);

        if (!CollectionUtils.isEmpty(list)){//CollectionUtils:????????????
            return true;
        }else {
            // ??????????????????  ????????????????????????  ???????????????
            String errors =  ExcelUtils.getErrors();
            if (errors.isEmpty()){
                errors = ErrorEnumMessage.EMPITYTEMPLATE.getErrorDescribe();
            }
            return false;
        }

    }




    //layui??????table
    @RequestMapping("/searchTestData")
    @ResponseBody
    public JsonToken searchTestData(@RequestParam(defaultValue = "") String name){
        List<Product> productList=productService.findTestDataByName(name);
        for (Product product : productList) {
            System.out.println("product = " + product);
        }
        JSONObject object=new JSONObject();
        System.out.println("object = " + object);
        return new JsonToken(0,"",productList,productList.size());
    }



    @RequestMapping("/deleteById")
    @ResponseBody
    public JsonToken deleteById(@RequestParam("id")String id){

        System.out.println("id = " + id);

        return new JsonToken(0,"0",0,0);
    }

}
