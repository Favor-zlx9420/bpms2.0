package com.rong.console.center.util;

import com.rong.common.util.DateUtil;
import jxl.Workbook;
import jxl.write.Alignment;
import jxl.write.Blank;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportExcelHelper {

    /**
     * 单元格 类型  枚举
     *
     */
    public enum CellType {
        NUMBER,LABEL,DATETIME,FORMULA
    }
    /**
     * 限制 每个 sheet 的行数

     */
    protected int rowLimit = 60000;
    /**
     * 列数
     */
    protected int columnCount;
    /**
     * 数据 集合
     */
    protected List<Object[]> objList;
    /**
     * 列名 集合
     */
    protected List<String> columnList;
    /**
     * 列宽度 集合
     */
    private List<Integer> widthList;
    /**
     * 列 与 单元格类型 键值对, 默认单元格类型LABEL
     * key从0开始

     */
    protected Map<Integer, CellType> columnMap;

    /**
     * key从0开始

     * value 为 java.sql.Types 中的类型
     */
    private Map<Integer, Integer> columnTypesMap;

    /**
     * 数字格式化 字符串 整数
     */
    private String numberFormatString = "0";
    private NumberFormat numberFormat = new NumberFormat(numberFormatString);
    protected WritableCellFormat nwcf = new WritableCellFormat(numberFormat);

    /**
     * 数字格式化 字符串  两位小数
     */
    private String numberFormatString2 = "0.00";
    private NumberFormat numberFormat2 = new NumberFormat(numberFormatString2);
    protected WritableCellFormat nwcf2 = new WritableCellFormat(numberFormat2);

    /**
     * 时间格式化  字符串

     */
    private String dateFormatString = DateUtil.JSONOUTPUT_yyyy_MM_dd_HH_mm_ss_EN;
    protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatString);
    private DateFormat dateFormat = new DateFormat(dateFormatString);
    protected WritableCellFormat dwcf = new WritableCellFormat(dateFormat);

    /**
     * timsStamp 格式化 字符串

     */
    private String timeStampFS = "yyyy-MM-dd HH:mm:ss.000";
    private DateFormat timsStampFormat = new DateFormat(timeStampFS);
    protected WritableCellFormat tswcf = new WritableCellFormat(timsStampFormat);

    /**
     * ExcelExportService 使用该构造方法 实例化该对象
     * columnCount = columnList.size() = columnTypesMap.size()
     * 使用 exportForServiceCreateObject() 导出
     * @param columnCount
     * @param columnList
     * @param columnTypesMap key从0开始 value 为 java.sql.Types 中的类型
     * @param objList
     */
    public ExportExcelHelper(int columnCount, List<String> columnList,Map<Integer, Integer> columnTypesMap,List<Object[]> objList){
        this.columnCount = columnCount;
        this.columnList = columnList;
        this.columnTypesMap = columnTypesMap;
        this.objList = objList;
    }

    /**
     * 构造方法
     * 使用 exportExcel() 导出
     * @param objList
     * @param columnList 列集合

     * @param formatList 单元格类型 集合
     */
    public ExportExcelHelper(List<Object[]> objList,List<String> columnList,List<CellType> formatList){
        this(objList,columnList);
        for(int i = 0; i < this.columnCount; i++){
            this.columnMap.put(i, i < formatList.size() ? formatList.get(i) : CellType.LABEL);
        }
    }
    /**
     * 构造方法 单元格类型 为 LABEL
     * 使用 exportExcel() 导出
     * @param objList
     * @param columnList 列集合

     */
    public ExportExcelHelper(List<Object[]> objList,List<String> columnList){
        if(objList == null || objList.size() == 0){
            System.out.println("objList为null或size等于0");
            return;
        }else if(columnList ==null || columnList.size() == 0){
            System.out.println("columnList为null或者size等于0！");
            return;
        }else{
            this.objList = objList;
            this.columnCount = columnList.size();
            this.columnList = columnList;
            this.columnMap = new HashMap<Integer, CellType>();
            for(int i = 0; i < this.columnCount; i++){
                this.columnMap.put(i, CellType.LABEL);
            }
            while(objList.get(0).length - this.columnCount > 0){
                this.columnList.add("");
                this.columnMap.put(this.columnCount, CellType.LABEL);
                this.columnCount++;
            }

        }
    }


    /**
     * 创建头部 第一行

     * @param sheet
     * @param columnList
     */
    public void createHeader(WritableSheet sheet, List<String> columnList) throws RowsExceededException,WriteException {
        WritableCellFormat headerWCF = new WritableCellFormat();
        headerWCF.setAlignment(Alignment.CENTRE);  // 居中
        for(int i = 0; i < this.columnCount; i++){
            Label label = new  Label(i,0,columnList.get(i),headerWCF);
            sheet.addCell(label);
        }
    }

    /**
     * 创建 第row行
     * @param sheet
     * @param row  第row行

     * @param objArray 一行数据

     */
    public void createRow(WritableSheet sheet,int row,Object[] objArray) throws RowsExceededException,WriteException,ParseException {
        WritableCellFormat rowsWCF = new WritableCellFormat();
        rowsWCF.setAlignment(Alignment.CENTRE);
        rowsWCF.setWrap(true);
        for(int i = 0; i < this.columnCount; i++){
            if(objArray[i] == null){
                Blank blank = new Blank(i,row);
                sheet.addCell(blank);
            }else{
                switch (this.columnMap.get(i)) {
                    case LABEL:
                        Label label = new  Label(i,row,objArray[i].toString(),rowsWCF);
                        sheet.addCell(label);
                        break;
                    case NUMBER:
                        Number number = new Number(i,row,Double.parseDouble(objArray[i].toString()),this.nwcf);
                        sheet.addCell(number);
                        break;
                    case DATETIME:
                        DateTime datetime = new DateTime(i,row,this.simpleDateFormat.parse(objArray[i].toString()),this.dwcf);
                        sheet.addCell(datetime);
                        break;
                    default:
                        Label label1 = new  Label(i,row,objArray[i].toString(),rowsWCF);
                        sheet.addCell(label1);
                        break;
                }
            }
        }
    }

    /**
     *
     * 由ExcelExportService 创建 的 对象 创建 第row行

     * @param sheet
     * @param row
     * @param objArray
     * @throws RowsExceededException
     * @throws WriteException
     * @throws ParseException
     * @throws SQLException
     */
    public void createRowForServiceCreateObject(WritableSheet sheet,int row,Object[] objArray) throws RowsExceededException,WriteException,ParseException, SQLException{
        for(int i = 0; i < this.columnCount; i++){
            if(objArray[i] == null){
                Blank blank = new Blank(i,row);
                sheet.addCell(blank);
            }else{
                switch (this.columnTypesMap.get(i)) {
                    case Types.NUMERIC:
                        if(objArray[i] instanceof BigDecimal && ((BigDecimal)objArray[i]).scale() > 0){
                            //System.out.println(objArray[i].toString()+"---- precision:"+((BigDecimal)objArray[i]).precision() + "---- scale:" + ((BigDecimal)objArray[i]).scale());
                            Number number = new Number(i,row,Double.parseDouble(objArray[i].toString()),this.nwcf2);
                            sheet.addCell(number);
                        }else{
                            Number number = new Number(i,row,Double.parseDouble(objArray[i].toString()),this.nwcf);
                            sheet.addCell(number);
                        }
                        break;
                    case Types.DATE:
                        DateTime datetime = new DateTime(i,row,(Date)objArray[i],this.dwcf);
                        sheet.addCell(datetime);
                        break;
                    case Types.TIMESTAMP:
                        //System.out.println(objArray[i].toString());
                        DateTime timestamp = new DateTime(i,row,new java.util.Date(),this.tswcf);
                        sheet.addCell(timestamp);
                        break;
                    default:
                        Label label = new  Label(i,row,objArray[i].toString() + "");
                        sheet.addCell(label);
                        break;
                }
            }
        }
    }

    /**
     * 设置 每列的宽度

     * @param sheet
     */
    private void setSheetColumnWidth(WritableSheet sheet){
        if(this.widthList != null){
            for(int w = 0; w < this.widthList.size(); w++){
                sheet.setColumnView(w, this.widthList.get(w));
            }
        }else{
            for(int i = 0; i < this.columnCount; i++){
                sheet.setColumnView(i, 20);
            }
        }
    }

    /**
     * 使用枚举CellType 时使用该方法
     * @param os  输出流

     */
    public void exportExcel(OutputStream os) throws IOException,Exception{
        WritableWorkbook book = Workbook.createWorkbook(os);
        WritableSheet sheet  =  book.createSheet("sheet"+1 ,0);                 // 创建第一个sheet
        setSheetColumnWidth(sheet);
        int rowCount = 0;                                                       // 行计数

        createHeader(sheet, this.columnList);                                        //设置 头 第一行

        rowCount++;                                                             //行 加 一
        int sheetNum = 1;
        while(this.objList.size() > 0){
            if(rowCount == this.rowLimit){                                           //满足 行 限制条件
                sheet  =  book.createSheet("sheet"+(sheetNum+1) ,sheetNum);    //新增一个sheet
                setSheetColumnWidth(sheet);
                rowCount = 0;                                                   //行数清零
                createHeader(sheet, this.columnList);                               //设置第一行

                rowCount++;
                sheetNum++;
            }
            createRow(sheet, rowCount, this.objList.get(0));                        //设置第rowCount 行

            rowCount++;
            this.objList.remove(0);
        }
        book.write();
        book.close();
    }

    /**
     * 使用columnTypesMap时 使用该方法

     * 用ExcelExportService 创建的对象 使用该方法 导出
     * @param os
     * @throws IOException
     * @throws RowsExceededException
     * @throws WriteException
     * @throws ParseException
     * @throws SQLException
     */
    public void exportForServiceCreateObject(OutputStream os) throws IOException, RowsExceededException, WriteException, ParseException, SQLException{
        WritableWorkbook book = Workbook.createWorkbook(os);
        WritableSheet sheet  =  book.createSheet("sheet"+1 ,0);                 // 创建第一个sheet
        setSheetColumnWidth(sheet);
        int rowCount = 0;                                                       // 行计数

        createHeader(sheet, this.columnList);                                        //设置 头 第一行

        rowCount++;                                                             //行 加 一
        int sheetNum = 1;
        while(this.objList.size() > 0){
            if(rowCount == this.rowLimit){                                           //满足 行 限制条件
                sheet  =  book.createSheet("sheet"+(sheetNum+1) ,sheetNum);    //新增一个sheet
                setSheetColumnWidth(sheet);
                rowCount = 0;                                                   //行数清零
                createHeader(sheet, this.columnList);                               //设置第一行

                rowCount++;
                sheetNum++;
            }
            createRowForServiceCreateObject(sheet, rowCount, this.objList.get(0)); //设置第rowCount 行

            rowCount++;
            this.objList.remove(0);
        }
        book.write();
        book.close();
    }

    /**
     * 设置 rowLimit 默认6000
     * @param rowLimit 不能超过65535
     */
    public void setRowLimit(int rowLimit){
        if(rowLimit > 65535){
            return;
        }
        this.rowLimit = rowLimit;
    }

    /**
     * 设置每列宽度
     * @param widthList 列宽度集合 size不能超过256
     */
    public void setWidthList(List<Integer> widthList){
        if(widthList == null || widthList.size() > 256){
            return;
        }
        this.widthList = widthList;
    }

    /**
     * 获取 列 格式 键值对
     * key 从 0开始

     * @return columnMap
     */
    public Map<Integer, CellType> getColumnMap() {
        return columnMap;
    }
    /**
     * 设置 列 格式 键值对
     * key 从 0开始

     * @param columnMap
     */
    public void setColumnMap(Map<Integer, CellType> columnMap) {
        this.columnMap = columnMap;
    }

    public Map<Integer, Integer> getColumnTypesMap() {
        return columnTypesMap;
    }
    public void setColumnTypesMap(Map<Integer, Integer> columnTypesMap) {
        this.columnTypesMap = columnTypesMap;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public void setObjList(List<Object[]> objList) {
        this.objList = objList;
    }

    public void setColumnList(List<String> columnList) {
        this.columnList = columnList;
    }

    public static void main(String[] args) {
        long begintime = System.currentTimeMillis();
        System.out.println(begintime);
        List<Object[]> objList = new ArrayList<Object[]>();
        List<String> columnList = new ArrayList<String>();
        List<CellType> formatList = new ArrayList<CellType>();
        List<Integer> widthList = new ArrayList<Integer>();
        String filePath = "E:/exceltest.xls";
        //Object[] o = {null,1,"1",null,null,null,1,1,1,1};
        //objList.add(o);
        for(int i = 0; i < 14000; i++){
            Object[] obj = new Object[10];
            for(int j = 0; j < 10; j++){
                obj[j] = "2012-02-19 00:00:05";
            }
            objList.add(obj);
        }
        for(int j = 1; j < 11; j++){
            columnList.add("第" + j + "列");
            formatList.add(CellType.DATETIME);
            widthList.add(20);
        }
        System.out.println(System.currentTimeMillis() - begintime);
    }
}
