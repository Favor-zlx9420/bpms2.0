package com.rong.console.center.util;

import com.rong.common.util.DateUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExportExcelUtil {
    public static void excelExport(String name, List<Object[]> dateList, List<String> headerList, HttpServletResponse response){
        try {
            String fileName=name+"-"+ DateUtil.getDateTimeFileName()+".xls";
            response.reset();
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename="+new String(fileName.getBytes("gbk"),"iso-8859-1"));
            OutputStream os;
            List<ExportExcelHelper.CellType> formatList = new ArrayList<>();
            if(dateList!=null && dateList.size()>0){
                Object[] item=dateList.get(0);
                for (Object object : item) {
                    if(object instanceof BigDecimal){
                        formatList.add(ExportExcelHelper.CellType.NUMBER);
                    }else {
                        formatList.add(ExportExcelHelper.CellType.LABEL);
                    }
                }
            }
            ExportExcelHelper ex = new ExportExcelHelper(dateList, headerList,formatList);

            os = response.getOutputStream();
            ex.exportExcel(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
