package me.duanyong.blog.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import me.duanyong.blog.bean.File;
import me.duanyong.blog.dao.FileMapper;
import org.apache.commons.io.FileUtils;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import me.duanyong.handswork.util.RandomUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PP on 10/16/16.
 */
@org.springframework.stereotype.Service
public class StorageService {
    private static final Logger log = LogManager.getLogger();

    @Autowired
    private SqlSessionFactory sessionFactory;


    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // 七牛存储
    //

    //设置好账号的ACCESS_KEY和SECRET_KEY
    private final static String ACCESS_KEY = "cHTf6d3NcMSiPBclK2smgIeoUtkXIsK4uR2S6EZU";
    private final static String SECRET_KEY = "9atZkM5K3woX4g5FqEP9EH36WmbC0ckJFP0Hchxx";

    //要上传的空间
    private final static String QINIUBUCKET     = "tonghua";
    private final static String QINIUDOMAIN     = "of3pl7fau.bkt.clouddn.com";

    //密钥配置
    private final static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象
    private final static UploadManager manager = new UploadManager();

    //
    // 七牛存储
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    //返回日期格式的目录 2016/10/abc/
    public String getDateFormatPath() {
        return new SimpleDateFormat("yyyy" + java.io.File.separator + "mm").format(new Date()).toString() + java.io.File.separator + RandomUtil.getLetter(3) + java.io.File.separator;
    }

    //返回临时目录，/tmp/2016/10/abc
    public java.io.File getTempDirectory() {
        java.io.File path = new java.io.File(FileUtils.getTempDirectory() + java.io.File.separator + getDateFormatPath());

        if (!path.exists()) {
            if (false == path.mkdirs()) {
                return null;
            }
        }

        return path;
    }

    //返回临时文件，/tmp/2016/10/abc/filename.zip
    public java.io.File getTempDFile(String filename) {
        java.io.File path = getTempDirectory();

        if (path == null) {
            return null;
        }

        return new java.io.File(path + java.io.File.separator + filename);
    }


    public java.io.File getUploadDirectory() {
        return getTempDirectory();
    }

    public File save(String name, String path, long size, String type) {
        return saveToDatabase(name, path, type, size, "存储到本地");
    }

    public File qiniuUpload(String name, String path, long size, String type) {
        try {
            //调用put方法上传 按/2016/07/xxx/a.zip 格式保存到七牛服务器
            Response res = manager.put(path, name, auth.uploadToken(QINIUBUCKET));

            //打印返回的信息
            Map<String, String> result = new HashMap<>();
            result = new Gson().fromJson(res.bodyString(), result.getClass());

            return saveToDatabase(new java.io.File(path).getName(), qiniuDownload(result.get("key")), type, size, "存储到七牛");

        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            log.error(r.toString());

            try {
                //响应的文本信息
                log.error(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }

            return null;
        }
    }


    public String qiniuDownload(String name) {
        return "http://" + QINIUDOMAIN + "/" + name;
//        return auth.privateDownloadUrl(name, 86400);
    }


    public File saveToDatabase(String name, String path, String type, long size, String desc) {
        SqlSession sqlSession = sessionFactory.openSession();

        File bean = new File();
        bean.setName(name);
        bean.setType(type);
        bean.setSize(size);
        bean.setPath(path);
        bean.setDesc(desc);

        FileMapper mapper = sqlSession.getMapper(FileMapper.class);

        try {
            return mapper.insert(bean) > 0 ? mapper.selectByPrimaryKey(bean.getId()) : null;
        } finally {
            sqlSession.close();
        }
    }

}
