package com.zlx.bpms.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Package: com.kamluen.modules.customer.template
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:文件下载模板
 */
@Slf4j
public class DownloadFileTools {

    public static final String SEPARATOR = File.separator;

    /**
     * 下载文件
     *
     * @param filePath    文件目录
     * @param fileName    文件名
     * @param newFileName 下载的展示文件名
     * @return
     */
    public static ResponseEntity<InputStreamResource> download(String filePath, String fileName, String newFileName) {
        String route = "static" + SEPARATOR + "templates" + SEPARATOR;
        String path = null;
        ResponseEntity<InputStreamResource> response = null;
        try {
            path = route + filePath + SEPARATOR + fileName;
            ClassPathResource classPathResource = new ClassPathResource(path);
            InputStream inputStream = classPathResource.getInputStream();
            //File file = new File(path);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition",
                    "attachment; filename="
                            + new String(newFileName.getBytes("gbk"), "iso8859-1") + ".xlsx");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            response = ResponseEntity.ok().headers(headers)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(inputStream));
        } catch (FileNotFoundException e1) {
            log.error("找不到指定的文件", e1);
        } catch (IOException e) {
            log.error("获取不到文件流", e);
        }
        return response;
    }

    static void downloadFile(HttpServletResponse response, String path) throws Exception {
        File file = new File(path);
        String name = file.getName();
        InputStream fis = null;
        fis = new FileInputStream(file);
        //强制下载不打开
        response.setContentType("application/force-download");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes(StandardCharsets.UTF_8), "iso8859-1"));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        byte[] b = new byte[1024];
        int len;
        while ((len = fis.read(b)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
        response.flushBuffer();
        fis.close();
    }


}
