package com.zjl.comp.test;

import com.alibaba.fastjson.JSONObject;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class test1 {

    public static void main(String[] args) throws Exception {
        String IP = "223.252.199.73";
        String key = "759bd36a0d3a9036ea619e148c6f7910";
        String strurl = "https://restapi.amap.com/v3/ip?output=json&key=";
        strurl+=key + "&ip=" + IP;
        URL url = new URL(strurl);
        HttpsURLConnection urlcon = (HttpsURLConnection) url.openConnection();
        urlcon.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlcon.getInputStream(),"utf-8"));
        StringBuffer sb = new StringBuffer();
        String content = null ;
        while((content=br.readLine())!=null) {
            sb.append(content);
        }
        JSONObject json = JSONObject.parseObject(sb.toString());
        String province = json.getString("province");
        String city = json.getString("city");
        System.out.println(province+"-"+city);
    }

}
