package com.zlx.bpms.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Package: com.bpms.auth.util
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description: 读取Excel工具类
 */
public class ExcelReadTools {

    private static final Logger log = LoggerFactory.getLogger(ExcelReadTools.class);


    public static List<Object> readExcelData(String filePath, Integer startWithaFewColumns, Integer startWithaFewLines, boolean isNumericCellValue, boolean isStringCellValue, boolean isDateCellValue){
        return cyclicReadingOfExcelData(filePath, startWithaFewColumns, startWithaFewLines, isNumericCellValue, isStringCellValue, isDateCellValue);
    }

    /**
     * 循环读取Excel中指定列 指定行的数据
     *
     * @param filePath             文件路径
     * @param startWithaFewColumns 从第几列开始
     * @param startWithaFewLines   从第几行开始
     * @param isNumericCellValue   是否为数字单元格值
     * @param isStringCellValue    是否为字符串单元格值
     * @param isDateCellValue      是否为日期单元格值
     * @return
     */
    static List<Object> cyclicReadingOfExcelData(String filePath, Integer startWithaFewColumns, Integer startWithaFewLines, boolean isNumericCellValue, boolean isStringCellValue, boolean isDateCellValue) {
        Workbook workbook = readExcel(filePath);
        List<Object> result = new ArrayList<>();
        //读取第一个工作页sheet
        Sheet sheet = workbook.getSheetAt(0);
        //获取Excel的总行数
        int rows = sheet.getPhysicalNumberOfRows();
        log.info("总共有:{}行", rows);
        Row row = null;
        // 从用户想要查询的行数开始循环
        for (int i = startWithaFewLines; i < rows; i++) {
            log.info("指定从第:{}行读取", i);
            row = sheet.getRow(i);
            if (StringTools.objNotNull(row)) {
                Object object = getCellValue(row.getCell(startWithaFewColumns), isNumericCellValue, isStringCellValue, isDateCellValue);
                result.add(object);
            } else {
                break;
            }
        }
        return result;
    }

    private static Object getCellValue(Cell cell, boolean isNumericCellValue, boolean isStringCellValue, boolean isDateCellValue) {
        Object value = null;
        if (isNumericCellValue) {
            value = cell.getNumericCellValue();
        } else if (isStringCellValue) {
            value = cell.getStringCellValue();
        } else if (isDateCellValue && DataTools.isCellDateFormatted(cell)) {
            value = cell.getDateCellValue();
        }
        return value;
    }

    /**
     * 读取Excel中指定列 指定行的数据
     *
     * @param filePath  文件路径
     * @param columns   指定需要获取的列数 默认从0开始
     * @param startRows 指定从第几行开始读取数据
     * @param endRows   指定结束行
     * @return 返回读取列数据的list
     */
    public static List<String> readColumn(String filePath, Integer columns, Integer startRows, Integer endRows, Sheet sheet) {
        List<String> result = new ArrayList<>();
        if (StringTools.objIsNull(sheet)) {
            Workbook workbook = readExcel(filePath);
            //读取第一个工作页sheet
            sheet = workbook.getSheetAt(0);
        }
        //获取Excel的总行数
        int rows = sheet.getPhysicalNumberOfRows();
        log.info("总共有:{}行", rows);
        Row row = null;
        String cellData = null;
        if (StringTools.objNotNull(sheet)) {
            for (int i = startRows; i < endRows; i++) {
                log.info("指定从第:{}行读取", i);
                row = sheet.getRow(i);
                if (StringTools.objNotNull(row)) {
                    cellData = (String) getCellValue(row.getCell(columns));
                    result.add(cellData);
                } else {
                    break;
                }
            }
        }
        return result;
    }


    /**
     * 读取单元格数据
     *
     * @param filePath  文件路径
     * @param columns   指定需要获取的列数 默认从0开始
     * @param startRows 指定从第几行开始读取数据
     * @return 返回读取列数据的list
     */
    public static List<String> readCellData(String filePath, Integer columns, Integer startRows) {
        Workbook workbook = readExcel(filePath);
        //读取第一个工作页sheet
        Sheet sheet = workbook.getSheetAt(0);
        //获取Excel的总行数
        int rows = sheet.getPhysicalNumberOfRows();
        return readColumn(filePath, columns, startRows, rows, sheet);
    }

    /**
     * 读取Excel
     *
     * @param path 路径
     * @return workbook
     */
    private static Workbook readExcel(String path) {
        Workbook workbook = null;
        if (StringTools.isEmpty(path)) {
            return null;
        }
        String fileSuffix = path.substring(path.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            if (".xls".equals(fileSuffix)) {
                workbook = new HSSFWorkbook(is);
            } else if (".xlsx".equals(fileSuffix)) {
                workbook = new XSSFWorkbook(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("读取Excel文件时发生异常,异常信息为:" + e);
        } finally {
            if (StringTools.objNotNull(is)) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return workbook;
    }


    private static Object getCellValue(Cell cell) {
        Object value = null;
        if (StringTools.objNotNull(cell)) {
            cell.setCellType(CellType.STRING);
            value = cell.getStringCellValue();
        }
        return value;
    }


    public static void main(String[] args) {
        boolean isFirstLoop = false;
        Integer count = 1;
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        Set<Map<String, Object>> maps = new HashSet<Map<String, Object>>();
        List<String> list = ExcelReadTools.readCellData("C:/Users/Administrator/Desktop/20191116/更改手机号和邮箱.xlsx", 6, 4);
        //region step-1 将 "data":[["change_item","old_value","new_value"],["1","","13728616905"],["2","","yayazj666@163.com"]] 该格式组装为map格式
        for (String jsonString : list) {
            Map<Object, Object> map = JsonTools.fromJson(jsonString);
            List<List<String>> data = (List<List<String>>) map.get("data");
            for (List<String> datum : data) {
                LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
                isFirstLoop = 1 == count;
                //如果是第一次循环,组装该集合
                if (isFirstLoop) {
                    result = MapTools.setTheMapCollection(datum);
                } else {
                    MapTools.setValueByFixedSizeKey(result, datum);
                    paramMap = (LinkedHashMap<String, Object>) result.clone();
                    maps.add(paramMap);
                }
                count++;
            }
            MapTools.resetMaps(maps);
            System.out.println(maps);
        }
        //endregion
    }
}
