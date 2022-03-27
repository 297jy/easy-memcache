package com.zhuanyi.hexo.base.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class FileUtils {

    public static List<String> readAllLinesFromFile(String path) {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            List<String> lines = new ArrayList<>();
            String line = fileReader.readLine();
            while (line != null) {
                lines.add(line);
                line = fileReader.readLine();
            }
            return lines;
        } catch (Throwable e) {
            log.error("readAllLinesFromFile失败，{%s}", e);
            return null;
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    log.error("关闭fileReader失败，{%s}", e);
                }
            }
        }
    }

    public static boolean writeContentToFile(String path, String content) {
        BufferedWriter fileWriter = null;
        try {
            fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
            fileWriter.write(content);
            return true;
        } catch (Throwable e) {
            log.error("writeContentToFile失败，{%s}", e);
            return false;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    log.error("关闭fileWriter失败，{%s}", e);
                }
            }
        }
    }

    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<>();
        File file = new File(path);
        File[] tempList = file.listFiles();
        if (tempList == null) {
            return Collections.emptyList();
        }

        for (File value : tempList) {
            if (value.isFile()) {
                files.add(value.getName());
            }
        }
        return files;
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    public static boolean existFile(String path) {
        File file = new File(path);
        return file.exists();
    }

    public static void main(String[] args) {

    }
}
