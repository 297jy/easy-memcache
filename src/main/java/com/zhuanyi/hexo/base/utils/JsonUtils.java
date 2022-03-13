package com.zhuanyi.hexo.base.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonUtils {

    public static JSONObject readJsonObjectFromFile(String filename) {
        BufferedReader configReader = null;
        try {
            configReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            List<String> allLines = new ArrayList<>();
            String line = configReader.readLine();
            while (line != null) {
                allLines.add(line);
                line = configReader.readLine();
            }
            String configJsonStr = StringUtils.join(allLines, "");
            return JSONObject.parseObject(configJsonStr);
        } catch (Throwable e) {
            log.error("readJsonObjectFromFile失败，{%s}", e);
            return new JSONObject();
        } finally {
            if (configReader != null) {
                try {
                    configReader.close();
                } catch (IOException e) {
                    log.error("关闭configReader失败，{%s}", e);
                }
            }
        }
    }

    public static boolean writeJsonObjectToFile(String filename, JSONObject data) {
        BufferedWriter configWriter = null;
        try {
            JSONObject configJson = readJsonObjectFromFile(filename);
            if (configJson == null) {
                configJson = new JSONObject();
            }
            configJson.putAll(data);
            configWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            configWriter.write(configJson.toJSONString());
            return true;
        } catch (Throwable e) {
            log.error("writeJsonObjectToFile失败，{%s}", e);
            return false;
        } finally {
            if (configWriter != null) {
                try {
                    configWriter.close();
                } catch (IOException e) {
                    log.error("关闭configWriter失败，{%s}", e);
                }
            }
        }
    }
}
