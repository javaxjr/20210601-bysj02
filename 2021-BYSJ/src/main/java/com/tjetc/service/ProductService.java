package com.tjetc.service;

import com.github.pagehelper.PageInfo;
import com.tjetc.domain.Order;
import com.tjetc.domain.Product;
import com.tjetc.util.MapPageModel;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductService {
    int add(Product product);

    List<Product> listByName(String name, Integer pageNum, Integer pageSize);

    PageInfo<Product> listByNamePageInfo(String name, Integer pageNum, Integer pageSize);

    List<Product> findTestDataByName(String name);

    MapPageModel findMapList(String name, Integer pageNum, Integer pageSize);

    PageInfo<Order> listByNameAndOrdersReasonsRefund(Map<String, Object> map, Integer pageNum, Integer pageSize);

    boolean addUploadExcel(MultipartFile excel);

    void productExcel(List list,String url,String downName);

    List<Product> selectByProductJSON();

    public <T> List<T> saveExcelFile(MultipartFile mulFile, T t);

    /*共用模块*/
    public <T> int addSave(Map<String,Object> map ,T t);

    public <T> PageInfo<T> selectByObject(Map<String,Object> map,T t);

    public <T> int updateByObject(Map<String,Object> map,T t);

    public <T> T findByIdAndObject(Map<String,Object> map,T t);
    //MapPageModel listByName(String name, Integer pageNum, Integer pageSize);

    public interface platformTransactionManager{
        // 由TransactionDefinition得到TransactionStatus对象
        TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;
        // 提交
        Void commit(TransactionStatus status) throws TransactionException;
        // 回滚
        Void rollback(TransactionStatus status) throws TransactionException;
    }

}
