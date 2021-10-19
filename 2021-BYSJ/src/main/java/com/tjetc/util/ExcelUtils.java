package com.tjetc.util;


import com.tjetc.domain.Product;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author win-10
 *
 */
public class ExcelUtils {

    private static String errors = "";

    public static String getCelValue(Cell cell){
        if (cell!=null){
            switch (cell.getCellType()){
                case XSSFCell.CELL_TYPE_BLANK:
                    return null;
                case XSSFCell.CELL_TYPE_BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case XSSFCell.CELL_TYPE_NUMERIC:
                    return String.valueOf((int)cell.getNumericCellValue());
                case XSSFCell.CELL_TYPE_STRING:
                    return cell.getRichStringCellValue().getString();
            }
        }
        return null;
    }

    public static String getErrors() {
        return errors;
    }

    public static  <T> List listDisToObj(List<List<Object>> listObj,T t,String sourFileName) throws ParseException {
        if(t instanceof Product){
            List<Product> products = new ArrayList<Product>();
            for (List<Object> list : listObj) {
                if(list.get(0)!="" || list.get(0)!=null){
                    Product product = new Product();
                    product.setType(list.get(0).toString());                           //码号
                    product.setDetails(list.get(1).toString());                        //使用单位
                    product.setBriefly(list.get(2).toString());                   //资源类型
                    product.setName(list.get(3).toString());                       //业务类型
                    product.setPhotopath(list.get(4).toString());//是否收费
                    product.setDetails(list.get(5).toString());                      //sp代码
                    product.setBriefly(list.get(6).toString());                      //sp名称
                    products.add(product);
                }
            }
            return products;
        }
        return null;
    }

    //下载数据库信息生成Excel表格
    //表格布局
    public static HSSFWorkbook productExcel(List<Product> list,String[] fields){
        //1.创建一个webbook, 对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("商品信息表");
        //第三部，在sheet中添加表头0行，注意老版本POI对Excel的行列数有限制short
        HSSFRow row = sheet.createRow((int)0);
        //第四步：创建单元格，并设置值表头，设置表头居中
        HSSFCellStyle style = wb.createCellStyle();

        //设置背景色
        /*style.setFillBackgroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);*/

        //设置边框居中
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        //设置字体
        HSSFFont font = wb.createFont();
        font.setFontName("仿宋_GB2312");
        font.setColor(HSSFColor.OLIVE_GREEN.index);
        font.setFontHeightInPoints((short) 18);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        //把字体应用到当前的样式
        style.setFont(font);

        // 生成一个样式
        CellStyle style1 = wb.createCellStyle();
        // 设置背景色
        style1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //设置边框
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);


        // 设置字体
        Font font1 = wb.createFont();
        font1.setFontName("仿宋_GB2312");
        font1.setColor(HSSFColor.OLIVE_GREEN.index);
        font1.setFontHeightInPoints((short) 14);
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style1.setFont(font1);
        sheet.setColumnWidth(0, 5000);
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 5000);


        //生成标题行
        Row row2 = sheet.createRow(1);
        Cell cell;
        for (int k = 0;k<fields.length;k++){
            cell = row2.createCell(k);
            cell.setCellValue(fields[k]);
            cell.setCellStyle(style1);
        }

        if (list == null || list.size() == 0) {
            return wb;
        }

        Row row3;
        Cell cell1;
        int contentIndex = 2;
        int count = 0;
        for (int i = 0;i<list.size()-1;i++){
            Product bean = list.get(i);
            row3 = sheet.createRow(contentIndex+i+count);

            cell1 = row3.createCell(0);
            cell1.setCellValue(bean.getId());
            cell1.setCellStyle(style);

            cell1 = row3.createCell(1);
            cell1.setCellValue(bean.getName());
            cell1.setCellStyle(style);

            cell1 = row3.createCell(2);
            cell1.setCellValue(bean.getType());
            cell1.setCellStyle(style);

            cell1 = row3.createCell(3);
            cell1.setCellValue(bean.getPhotopath());
            cell1.setCellStyle(style);

        }
        return wb;
    }



    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {//对2003版本的Excel的支持 ---- HSSFWorkbook
            wb = new HSSFWorkbook(inStr); // 2003-
        } else if (".xlsx".equals(fileType)) {//对2007版本以及更高版本的支持 ---- XSSFWorkbook
            wb = new XSSFWorkbook(inStr); // 2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    //获取准确的文件行数
    public static int getRealRowNum(Sheet sheet) {
        int rowNum = sheet.getLastRowNum();//sheet中一行数据都没有则返回-1，只有第一行有数据则返回0，最后有数据的行是第n行则返回 n-1；
        while(rowNum > 0 ){
            Row row = sheet.getRow(rowNum);//不是返回行数,而是返回当前是哪一行
            if (row != null) {
                for (Cell cell : row) {
                    if (!StringUtils.isEmpty(getCellValue(cell,"",0).toString().trim())){
                        return rowNum;
                    }
                }
            }
            rowNum--;
        }
        return rowNum;
    }

    /**
     *  校验5：校验Excel表格有效单元格数量
     */
    public static String verifyCellsNum(int end,String firstCellVal,String type){
        String error = "";
        String str = firstCellVal.substring(0,2);
        if(type.equals("Distribute") && end==34){
            return error;
        }else if(type.equals("SendedNumber") && end==4 && str.equals("码号")){
            return error;
        }else if(type.equals("SpamMessageFeeReceipt") && end==4 && str.equals("SP")){
            return error;
        }
        return ErrorEnumMessage.COMMONERRORS.getErrorDescribe();
    }



    /**
     * Excel导入
     */
    public static List<List<Object>>  getBankListByExcel(InputStream in, String fileName, String type) throws Exception {
        List<List<Object>> list = null;
        // 创建Excel工作薄
        Workbook work = getWorkbook(in, fileName); //获取work对象
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();

        // 遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {  //没有分表分list，多个表的数据放在一个list集合下。

            sheet = work.getSheetAt(i);//获取工作薄的第i个sheet页
            if (sheet == null) {
                continue;
            }

            //真正的行数，去掉没有数值，但有样式的行。
            int realRowNum = getRealRowNum(sheet); //获取当前sheet总共有多少真实有效的行

            // 遍历当前sheet中的所有行
            // 包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部
            for (int j = sheet.getFirstRowNum(); j <= realRowNum; j++) { //j为实际行数

                row = sheet.getRow(j);//返回当前j的行数
                if (row == null || j==0) {//若当前行是第一行则跳过，即使xmls中标题部分
                    continue;
                }

                // 遍历所有的列
                List<Object> li = new ArrayList<Object>();

                //校验3：列数与模板列数不一致
                Row firstRow = sheet.getRow(0); //获取到当前0行，这里是获取第一行标题部分。
                Cell firstCell= firstRow.getCell(0); //获取0行第一列的数值
                if(firstCell==null){
                    continue;
                }
                String firstCellVal = getCellValue(firstCell,type,0).toString().trim();//对0行第0列的表格中数值进行格式化，获取单元格中数据
                String errCellNumMsg = ExcelUtils.verifyCellsNum(firstRow.getLastCellNum(),firstCellVal,type);//校验Excel表格有效单元格数量
                if (errCellNumMsg.equals("")){
                    errors += errCellNumMsg;
                    break;
                }


                String isCharge = "";
                for (int y = firstRow.getFirstCellNum(); y < firstRow.getLastCellNum(); y++) { //得到一行记录，y是每一行记录中的第几个单元格（从零开始计数）
                    cell = row.getCell(y);//获取第y个单元格的值
                    String cellValue = getCellValue(cell,type,y).toString().trim();//对表格中数值进行格式化


                    li.add(cellValue);
                }
                list.add(li);//装载一行的数据到list中
            }
        }
        return list;
    }



    /**
     * 描述：对表格中数值进行格式化
     */
    @SuppressWarnings("deprecation")
    public static Object getCellValue(Cell cell,String type,int y) {
        if(cell==null){
            return "";
        }
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化字符类型的数字
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                    if(type.equals("SpamMessageFeeReceipt") && y==3){
                        value = cell.getNumericCellValue();
                    }
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

    public static void downloadExcel(HSSFCellStyle style, HSSFSheet sheet, HSSFRow row, List list, String downName) {

        if ("product".equals(downName)){
            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue("编号");
            cell.setCellStyle(style);

            cell = row.createCell((short)1);
            cell.setCellValue("商品名称");
            cell.setCellStyle(style);

            cell = row.createCell((short)2);
            cell.setCellValue("单价");
            cell.setCellStyle(style);

            cell = row.createCell((short)3);
            cell.setCellValue("库存量");
            cell.setCellStyle(style);

            cell = row.createCell((short)4);
            cell.setCellValue("商品图片");
            cell.setCellStyle(style);

            cell = row.createCell((short)5);
            cell.setCellValue("商品简介");
            cell.setCellStyle(style);

            cell = row.createCell((short)6);
            cell.setCellValue("商品详情");
            cell.setCellStyle(style);

            cell = row.createCell((short)7);
            cell.setCellValue("商品类型");
            cell.setCellStyle(style);

            //第五步、写入实体数据，数据时从数据库得到
            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow((int)i+1);
                Product product = (Product) list.get(i);

                //第四步、创建单元格、设置值
                row.createCell((short)0).setCellValue(product.getId());
                row.createCell((short)1).setCellValue(product.getName());
                row.createCell((short)2).setCellValue(product.getPrice());
                row.createCell((short)3).setCellValue(product.getCount());
                row.createCell((short)4).setCellValue(product.getPhotopath());
                row.createCell((short)5).setCellValue(product.getBriefly());
                row.createCell((short)6).setCellValue(product.getDetails());
                row.createCell((short)7).setCellValue(product.getType());
            }
        }
    }
}