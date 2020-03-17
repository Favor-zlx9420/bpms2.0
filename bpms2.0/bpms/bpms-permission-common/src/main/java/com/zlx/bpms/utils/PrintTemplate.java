package com.zlx.bpms.utils;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Package: com.zlx.bpms.utils
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:pdf模板打印
 */
public class PrintTemplate {
    /**
     * 模板路径
     */
    private String templatePath;
    /**
     * 导出文件路径
     */
    private String targetPath;

    /**
     * 字体路径
     */
    private String fontPath;

    public PrintTemplate() {
        super();
    }

    public PrintTemplate(String templatePath, String targetPath, String fontPath) {
        super();
        this.templatePath = templatePath;
        this.targetPath = targetPath;
        this.fontPath = fontPath;
    }

    void writeData(Map<String, Object> map, HttpServletResponse response) throws Exception {
        File file = new File(targetPath);
        file.createNewFile();
        print(file, map);
        DownloadFileTools.downloadFile(response, targetPath);
    }


    private void print(File file, Map<String, Object> map) throws Exception {
        PdfReader reader = new PdfReader(templatePath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, bos);
        BaseFont bf1 = BaseFont.createFont(fontPath + ",1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        fontList.add(bf1);
        AcroFields s = ps.getAcroFields();
        s.setSubstitutionFonts(fontList);
        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> entry = it.next();
            String value = entry.getValue() == null ? "" : entry.getValue().toString();
            s.setField(entry.getKey(), value);
        }
        ps.setFormFlattening(true);
        ps.close();
        reader.close();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bos.toByteArray());
        fos.close();
    }
}
