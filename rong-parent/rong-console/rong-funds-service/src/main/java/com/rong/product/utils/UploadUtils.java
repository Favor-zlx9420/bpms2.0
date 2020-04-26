package com.rong.product.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Package: com.rong.product.utils
 * @Author: LQW
 * @Date: 2020/4/23
 * @Description:
 */
public class UploadUtils {


    public static String uploadFile(MultipartFile file, String filePath) {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath + file.getOriginalFilename());
            out.write(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }
}
