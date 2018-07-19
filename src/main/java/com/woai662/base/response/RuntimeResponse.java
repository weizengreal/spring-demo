package com.woai662.base.response;

import java.util.HashMap;
import java.util.Map;

public class RuntimeResponse {

    // 存储 jvm 和 os 的基本信息
    public Map<String,String> property = new HashMap<String, String>();

    // 存储 cpu 相关信息
    public Map<String,Map<String,String>> cpusInfo = new HashMap<String, Map<String, String>>();

    // 存储 memory
    public Map<String,String> memory = new HashMap<String, String>();




    public void printThis() {
        System.out.println("print os property!");
        for (String key : property.keySet()) {
            System.out.println(key + " : " + property.get(key));
        }

        System.out.println("print cpus!");
        for (String process : cpusInfo.keySet()) {
            System.out.println(process);
            for (String key : cpusInfo.get(process).keySet()) {
                System.out.println(key + " : " + cpusInfo.get(process).get(key));
            }
            System.out.println();
        }

        System.out.println("print memory!");
        for (String key : memory.keySet()) {
            System.out.println(key + " : " + memory.get(key));
        }

    }




}
