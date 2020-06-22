package com.zjl.comp.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Component
public class AddressUtils {

    public String getAddresses(String IP) throws IOException {
        String key = "759bd36a0d3a9036ea619e148c6f7910"; //高德开发平台
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
        String addr = province+"-"+city;
        if(city==""||city==null)addr = "内网ip无法解析*_*";
        return addr;
    }
}