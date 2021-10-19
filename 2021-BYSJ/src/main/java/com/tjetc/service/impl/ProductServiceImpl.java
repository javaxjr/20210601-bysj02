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
           //获取Excel对象
            Workbook workbook = null;
            //获得文件名
            String fileName = excel.getOriginalFilename();
            System.out.println("文件名称 = " + fileName);

            //根据后缀，得到不同的workbook子类，即HSSFWorkbook或XSSFWorkbook
            if (excel.getOriginalFilename().endsWith(xlsx)){
                workbook = new XSSFWorkbook(in);//给定输入流读取文件创建XLSX操作对象
            }else if (excel.getOriginalFilename().endsWith(xls)){
                workbook = new HSSFWorkbook(in);//给定输入流读取文件创建XLS操作对象
            }else {
                throw new  Exception("文件格式不正确，或者文件已损坏");
            }
            //获取sheet对应对象，获取第一页对象
            Sheet sheet = workbook.getSheetAt(0);
            //创建station对象容器
            Product product;
            //解析sheet，获取多行数据，并放入迭代器中
            Iterator<Row> ito = sheet.iterator();
            int count = 0;
            int sum = 0;
            Row row = null;
            //每次导入就把上次的删除
            //delInterfaceList();
            while (ito.hasNext()){
                row = ito.next();
                //由于第一行是标题行，因此单独处理
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

                        //为对象赋值
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
        Product product1 = new Product(1,"张三",0.3,5,"fdfdfd","fdfd","dfdfd","鞋子");
        Product product2 = new Product(2,"李四",0.3,5,"fdfdfd","fdfd","dfdfd","吃的");
        Product product3 = new Product(3,"王五",0.3,5,"fdfdfd","fdfd","dfdfd","穿的");
        Product product4 = new Product(4,"赵柳",0.3,5,"fdfdfd","fdfd","dfdfd","住的");

        list.add(product1);
        list.add(product2);
        list.add(product3);
        list.add(product4);
        return list;
    }*/

    //表格布局
    @Override
    public void productExcel(List list,String url,String downName){
        //1.创建一个webbook, 对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet();
        //第三部，在sheet中添加表头0行，注意老版本POI对Excel的行列数有限制short
        HSSFRow row = sheet.createRow((int)0);
        //第四步：创建单元格，并设置值表头，设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//创建一个居中格式

        //处理list集合中的数据信息
        ExcelUtils.downloadExcel(style,sheet,row,list,downName);

        //第六步、将文件存到指定位置C:\Users\17814\Desktop
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
                String suffix = filename.substring(filename.lastIndexOf("."));// 后缀名
                String uuid = UUID.randomUUID().toString();
                String uuidName = uuid + suffix;
                String path = "c:/upload/"+uuidName;
                //String saveType = getSaveFileType(t);
                //saveFileInfo(filename,uuidName,path,saveType);//文件信息插入到数据库
                File file = new File(path);
                file.mkdirs();
                File dest = new File(path);

                /*取出Excel表中的每一个单元格的值，存放在list集合中*/
                InputStream in = mulFile.getInputStream();
                //lastIndexOf() 方法可返回一个指定的字符串值最后出现的位置,如果指定第二个参数 start,则在一个字符串中的指定位置从后向前搜索。”
                //String type = t.toString().substring(t.toString().lastIndexOf(".")+1, t.toString().lastIndexOf("#"));
                List<List<Object>> bankListByExcel = ExcelUtils.getBankListByExcel(in, filename,"4545");
                /*保存上传的Excel文件到本地目录*/
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
