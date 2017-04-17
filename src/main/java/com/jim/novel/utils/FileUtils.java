package com.jim.novel.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    public static String saveFile(MultipartFile multipartFile, String path, String fileName) throws IllegalStateException, IOException {
        if(StringUtils.isNotBlank(fileName)) {
            File file = new File(path, fileName);
            if(!file.exists()) {
                file.mkdirs();
            }

            multipartFile.transferTo(file);
            return file.getPath();
        } else {
            return "";
        }
    }

    public static String saveFile(MultipartFile multipartFile, String fileName) throws IllegalStateException, IOException {
        int index = fileName.lastIndexOf(".");
        String extensions = fileName.substring(index);
        String newName = fileName.substring(0, index) + CalendarTools.formatDateTime(new Date(), "yyyyMMddHHmmss");
        newName = System.currentTimeMillis() + extensions;
        return saveFile(multipartFile, "/data/webserver/stock/user", newName);
    }

    public static String copyFile(String orgFilePath, String newFilePath) throws IOException {
        FileInputStream input = new FileInputStream(orgFilePath);
        FileChannel fiCh = input.getChannel();
        FileOutputStream output = new FileOutputStream(newFilePath);
        FileChannel fcout = output.getChannel();
        fiCh.transferTo(0L, fiCh.size(), fcout);
        fcout.close();
        output.close();
        fiCh.close();
        input.close();
        return "";
    }

    public static String getEncodingFileName(String fileName, String userAgent) {
        String name = null;

        try {
            name = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException var4) {
            name = fileName;
        }

        if(StringUtils.isNotEmpty(userAgent)) {
            userAgent = userAgent.toLowerCase();
            if(userAgent.indexOf("opera") != -1) {
                name = "filename*=UTF-8\'\'" + name;
            } else if(userAgent.indexOf("msie") != -1 || userAgent.indexOf("rv:") != -1 && userAgent.indexOf("firefox") == -1) {
                name = "filename=\"" + name + "\"";
            } else if(userAgent.indexOf("mozilla") != -1) {
                try {
                    name = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"";
                } catch (UnsupportedEncodingException var3) {
                    name = "filename=\"" + name + "\"";
                }
            } else {
                name = "\"filename=" + name + "\"";
            }
        } else {
            name = "\"filename=" + name + "\"";
        }

        return name;
    }
}
