package com.zjl.org.conterllor;

import com.zjl.comp.util.LoginInfo;
import com.zjl.comp.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author zhoujl
 * @date 2020/6/18
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${uploadFilePath}")
    private String path;

    @Value("${appId}")
    private String appId;

    @PostMapping(value = "/fileUploads")
    public void fileUploads(@RequestParam(value = "avatar") MultipartFile files[],HttpServletRequest request) {
        if (files.length<=0) {
            System.out.println("未找到文件！");
            return;
        }
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath = path+appId+"//"+ LoginInfo.getUser().getUserId() +"//"; // 上传后的路径
            fileName = UUIDUtil.getUUID() + suffixName; // 新文件名
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
