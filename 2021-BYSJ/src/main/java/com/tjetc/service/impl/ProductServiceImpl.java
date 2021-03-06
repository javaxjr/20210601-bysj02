package com.tjetc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.domain.Admin;
import com.tjetc.domain.Order;
import com.tjetc.domain.Product;
import com.tjetc.domain.User;
import com.tjetc.mapper.AdminMapper;
import com.tjetc.mapper.ProductMapper;
import com.tjetc.service.ProductService;
import com.tjetc.util.CreateSimpleExcelToDisk;
import com.tjetc.util.ExcelUtils;
import com.tjetc.util.MapPageModel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final static String xls = "xls";
    private final static String xlsx = "xlsx";


    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int add(Product product) {
        return productMapper.add(product);
    }

    @Override
    public List<Product> listByName(String name, Integer pageNum, Integer pageSize) {
        return productMapper.listByName(name);
    }

    @Override
    public PageInfo<Product> listByNamePageInfo(String name, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo<Product>(productMapper.listByName(name));
        return pageInfo;
    }

    @Override
    public List<Product> findTestDataByName(String name) {
       return productMapper.listByName(name);
    }

    @Override
    public MapPageModel findMapList(String name, Integer pageNum, Integer pageSize) {

        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> mapList = productMapper.findMapList(map);
        MapPageModel pm = new MapPageModel();
        pm.setCount(mapList.size());
        pm.setData(mapList);
        pm.setPage(pageNum);
        return pm;
    }

    public PageInfo<Order> listByNameAndOrdersReasonsRefund(Map<String, Object> map, Integer pageNum, Integer pageSize) {

        List<Order> orderList = productMapper.selectOrderByReasonsRefund();
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Order> pageInfo = new PageInfo<Order>(orderList);

        return pageInfo;
    }

    @Override
    public boolean addUploadExcel(MultipartFile excel) {

        if (excel==null){
            return false;
        }
        InputStream in = null;
        try {
           in = excel.getInputStream();
           //??????Excel??????
            Workbook workbook = null;
            //???????????????
            String fileName = excel.getOriginalFilename();
            System.out.println("???????????? = " + fileName);

            //??????????????????????????????workbook????????????HSSFWorkbook???XSSFWorkbook
            if (excel.getOriginalFilename().endsWith(xlsx)){
                workbook = new XSSFWorkbook(in);//?????????????????????????????????XLSX????????????
            }else if (excel.getOriginalFilename().endsWith(xls)){
                workbook = new HSSFWorkbook(in);//?????????????????????????????????XLS????????????
            }else {
                throw new  Exception("?????????????????????????????????????????????");
            }
            //??????sheet????????????????????????????????????
            Sheet sheet = workbook.getSheetAt(0);
            //??????station????????????
            Product product;
            //??????sheet?????????????????????????????????????????????
            Iterator<Row> ito = sheet.iterator();
            int count = 0;
            int sum = 0;
            Row row = null;
            //?????????????????????????????????
            //delInterfaceList();
            while (ito.hasNext()){
                row = ito.next();
                //????????????????????????????????????????????????
                if (count == 0){
                    ++count;
                    continue;
                }else {
                    if (row != null){
                        product = new Product();
                        String name = ExcelUtils.getCelValue(row.getCell(0));
                        if (name==null){
                            break;
                        }
                        Double price = Double.valueOf(ExcelUtils.getCelValue(row.getCell(1)));
                        Integer pcount = Integer.valueOf(ExcelUtils.getCelValue(row.getCell(2)));
                        String photopath = ExcelUtils.getCelValue(row.getCell(3));
                        String briefly = ExcelUtils.getCelValue(row.getCell(4));
                        String details = ExcelUtils.getCelValue(row.getCell(5));
                        String type = ExcelUtils.getCelValue(row.getCell(6));

                        //???????????????
                        product.setName(name);
                        product.setPrice(price);
                        product.setCount(pcount);
                        product.setPhotopath(photopath);
                        if (photopath==null || photopath.trim().length()==0){
                            product.setPhotopath("upload/6fb0dfccyifu06.jpg");
                        }
                        product.setBriefly(briefly);
                        product.setDetails(details);
                        product.setType(type);
                        addProduct(product);
                    }
                }
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void addProduct(Product product){
       productMapper.add(product);
    }

    /*@Override
    public MapPageModel listByName(String name, Integer pageNum, Integer pageSize) {
        return productMapper.listByName(name);
    }*/


    /*private static List getProduct() throws Exception{

        List list = new ArrayList();
        Product product1 = new Product(1,"??????",0.3,5,"fdfdfd","fdfd","dfdfd","??????");
        Product product2 = new Product(2,"??????",0.3,5,"fdfdfd","fdfd","dfdfd","??????");
        Product product3 = new Product(3,"??????",0.3,5,"fdfdfd","fdfd","dfdfd","??????");
        Product product4 = new Product(4,"??????",0.3,5,"fdfdfd","fdfd","dfdfd","??????");

        list.add(product1);
        list.add(product2);
        list.add(product3);
        list.add(product4);
        return list;
    }*/

    //????????????
    @Override
    public void productExcel(List list,String url,String downName){
        //1.????????????webbook, ????????????Excel??????
        HSSFWorkbook wb = new HSSFWorkbook();
        // ???????????????webbook???????????????sheet,??????Excel????????????sheet
        HSSFSheet sheet = wb.createSheet();
        //???????????????sheet???????????????0?????????????????????POI???Excel?????????????????????short
        HSSFRow row = sheet.createRow((int)0);
        //?????????????????????????????????????????????????????????????????????
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//????????????????????????

        //??????list????????????????????????
        ExcelUtils.downloadExcel(style,sheet,row,list,downName);

        //???????????????????????????????????????C:\Users\17814\Desktop
        try {
            FileOutputStream fps = new FileOutputStream("c:/Users/17814/Desktop/"+url+".xls");
            wb.write(fps);
            fps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Product> selectByProductJSON() {



        return productMapper.selectByProductJSON();
    }





    public <T> List<T> saveExcelFile(MultipartFile mulFile, T t) {
        if (!mulFile.isEmpty()) {
            try {
                String filename = mulFile.getOriginalFilename();
                String suffix = filename.substring(filename.lastIndexOf("."));// ?????????
                String uuid = UUID.randomUUID().toString();
                String uuidName = uuid + suffix;
                String path = "c:/upload/"+uuidName;
                //String saveType = getSaveFileType(t);
                //saveFileInfo(filename,uuidName,path,saveType);//??????????????????????????????
                File file = new File(path);
                file.mkdirs();
                File dest = new File(path);

                /*??????Excel?????????????????????????????????????????????list?????????*/
                InputStream in = mulFile.getInputStream();
                //lastIndexOf() ???????????????????????????????????????????????????????????????,??????????????????????????? start,???????????????????????????????????????????????????????????????
                //String type = t.toString().substring(t.toString().lastIndexOf(".")+1, t.toString().lastIndexOf("#"));
                List<List<Object>> bankListByExcel = ExcelUtils.getBankListByExcel(in, filename,"4545");
                /*???????????????Excel?????????????????????*/
                mulFile.transferTo(dest);
                String errors = ExcelUtils.getErrors();
                List<T> list = null;
                if(errors.isEmpty()){
                    list = ExcelUtils.listDisToObj(bankListByExcel, t, filename);
                }
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public <T> int addSave(Map<String,Object> map ,T t) {

        if (t instanceof  Product){
            Product product = (Product) map.get("product");
            System.out.println("product = " + product);
            productMapper.add(product);
        }else if (t instanceof Admin){
            Admin admin = (Admin) map.get("admin");
            System.out.println("admin = " + admin);
            adminMapper.addAdmin(admin);
        }else if (t instanceof User){
            User user = (User) map.get("user");
        }

        return 0;
    }

    @Override
    public <T> PageInfo<T> selectByObject(Map<String, Object> map, T t) {
        return null;
    }

    @Override
    public <T> int updateByObject(Map<String, Object> map, T t) {
        return 0;
    }

    @Override
    public <T> T findByIdAndObject(Map<String, Object> map, T t) {
        return null;
    }


}
