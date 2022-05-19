package com.example.demo.controller;

import com.example.demo.common.DocumentHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yzk
 * @version 1.0
 * @desc
 * @since 2022/5/10 10:07
 **/

public class Main {

    /**
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws IOException {

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("title", "试卷");
        dataMap.put("courseName", "试卷");
        dataMap.put("type", "试卷");
        dataMap.put("duration", "试卷");
        dataMap.put("difficulty", "试卷");
        dataMap.put("score", "试卷");
        dataMap.put("addtime", "试卷");
        dataMap.put("singlenum", "试卷");
        dataMap.put("multinum", "试卷");
        dataMap.put("blanknum", "试卷");
        dataMap.put("judgementnum", "试卷");

        // 单选题
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        for (int i = 1; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("num2", i + ".");
            map.put("single", "(   )操作系统允许在一台主机上同时连接多台终端，多个用户可以通过各自的终端同时交互地使用计算机。");
            map.put("singleA", "A" + i);
            map.put("singleB", "B" + i);
            map.put("singleC", "C" + i);
            map.put("singleD", "D" + i);
            list2.add(map);
        }
        dataMap.put("table2", list2);

        // 多选题
        List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
        for (int i = 1; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("num3", i + ".");
            map.put("multi", "(   )操作系统允许在一台主机上同时连接多台终端，多个用户可以通过各自的终端同时交互地使用计算机。");
            map.put("multiA", "A" + i);
            map.put("multiB", "B" + i);
            map.put("multiC", "C" + i);
            map.put("multiD", "D" + i);
            list3.add(map);
        }
        dataMap.put("table3", list3);

        // 知识点
        List<Map<String, Object>> list4 = new ArrayList<Map<String, Object>>();
        for (int i = 1; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("num4", i + ".");
            map.put("blank", "试卷");
            list4.add(map);
        }
        dataMap.put("table4", list4);

        // 知识点
        List<Map<String, Object>> list5 = new ArrayList<Map<String, Object>>();
        for (int i = 1; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("num5", i + ".");
            map.put("judgement", "试卷");
            list5.add(map);
        }
        dataMap.put("table5", list5);

        DocumentHandler mdoc = new DocumentHandler();
        mdoc.createDoc(dataMap, "out\\outFile.doc");
    }

}
