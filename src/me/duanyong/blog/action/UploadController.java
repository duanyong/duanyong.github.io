package me.duanyong.blog.action;


import me.duanyong.blog.bean.File;
import me.duanyong.blog.service.StorageService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by duanyong on 9/14/16.
 */

@RestController
@RequestMapping("/upload")
public class UploadController extends BaseRestAction {
    @Autowired
    private StorageService service;


    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ModelAndView view() {
        return new ModelAndView("/upload/index");
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return gson(100, "no upload file");
        }

        //获取临时目录
        java.io.File path = service.getUploadDirectory();
        try {
            file.transferTo(path);
        } catch (java.io.IOException e) {
            return gson(101, "saveToDatabase upload file to local storage fail. case by :" + e.getMessage());
        }

        File bean = service.saveToDatabase(file.getOriginalFilename(), path.toString(), file.getContentType(), file.getSize(), "user upload");

        if (bean == null) {
            return gson(102, "upload file to remote storage fail.");
        }

        Map<String, Object> map = new HashMap();
        map.put("id", bean.getId());

        return gson(map);
    }

    @RequestMapping(path = "/qiniu", method = RequestMethod.POST)
    public String qiniu(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return gson(100, "no upload file");
        }

        //获取临时地址
        java.io.File path = service.getTempDFile(file.getOriginalFilename());

        if (path == null) {
            return gson(101, "limit to create directory.");
        }

        try {
            file.transferTo(path);
        } catch (java.io.IOException e) {
            return gson(102, "saveToDatabase upload file to local storage fail. case by :" + e.getMessage());
        }

        try {
            File bean = service.qiniuUpload(service.getDateFormatPath() + file.getOriginalFilename(), path.toString(), file.getSize(), file.getContentType());

            json.put("download", bean.getPath());
            json.put("id", bean.getId());

            return gson(0, "ok");
        } catch (Exception e) {
            return gson(103, "upload file to remote storage fail. case by :" + e.getMessage());
        } finally {
            path.delete();
        }
    }
}
