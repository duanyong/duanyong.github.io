---
layout:         post
title:          "ajax上传功能之升级nginx配置文件"
subtitle:       "记录升级过程"
tags:           [nginx,upload-moudle,upload-proccess-module]
keyword:        "nginx"
description:    "升级nginx"
---


承接升级nginx成功之后, 需要个性www的配置文件, 需要指定后端处理上传文件的脚本文件.

### 修改nginx的配置文件, 修改页面的<from action="/upload" method="post" enctype="multipart/form-data">来指定上传的路径
```shell
location /upload {

    # Pass altered request body to this location
    # The directory is hashed, subdirectories 0 1 2 3 4 5 6 7 8 9 should exist
    upload_pass             /upload.php;
    upload_store            /var/www/uploads 1;
    upload_max_file_size    50m;

    upload_pass_form_field "^(.*)$";


    # Allow uploaded files to be read only by user
    upload_store_access user:rw group:rw all:rw;


    # 限制上传速度，实际生成环境可放宽到更高。
    #upload_limit_rate   512k;        #for test
    upload_limit_rate   0;


    # Set specified fields in request body
    upload_set_form_field name                              "$upload_field_name";
    upload_set_form_field $upload_field_name.name           "$upload_file_name";
    upload_set_form_field $upload_field_name.content_type   "$upload_content_type";
    upload_set_form_field $upload_field_name.path           "$upload_tmp_path";

    # Inform backend about hash and size of a file
    upload_aggregate_form_field "$upload_field_name.md5"    "$upload_file_md5";
    upload_aggregate_form_field "$upload_field_name.size"   "$upload_file_size";


    upload_cleanup 400 404 499 500-505;
}
```

location /upload        --- 指定上传的路径
upload_pass /upload.php --- 指定后端处理的脚本

upload_set_form_field name                              "$upload_field_name";   --- 脚本接受的标志
upload_set_form_field $upload_field_name.name           "$upload_file_name";    --- 脚本接收的的文件名称
upload_set_form_field $upload_field_name.content_type   "$upload_content_type"; --- 脚本接收到的文件类型
upload_set_form_field $upload_field_name.path           "$upload_tmp_path";     --- 脚本接收到的文件存放路径

upload_aggregate_form_field "$upload_field_name.md5"    "$upload_file_md5";     --- 脚本接收到的文件的md5值
upload_aggregate_form_field "$upload_field_name.size"   "$upload_file_size";    --- 脚本接收到的文件大小


以上upload对应的是后端PHP脚本的各项变量【POST】提交, 下面是示例:
```
/*
array(6) {
    ["name"]                => string(4) "demo"
    ["demo_name"]           => string(39) "Screenshot from 2012-11-02 14:27:42.png"
    ["demo_content_type"]   => string(9) "image/png"
    ["demo_path"]           => string(23) "/tmp/uploads/0000000018"
    ["demo_md5"]            => string(32) "9dd424deb05b85d45fc03d24e54c7c11"
    ["demo_size"]           => string(6) "191694"
}
*/
```

脚本先拿到name值,再通过name获取其它变量进行文件处理.
```php
if (s_bad_post('name', $name)) {
    return s_action_error('没有上传的文件');
}

if (s_bad_post("{$name}_name", $fname)) {
    $fname = "";
}

if (s_bad_post("{$name}_content_type", $ftype)) {
    $ftype = "";
}

if (s_bad_post("{$name}_path", $fpath)) {
    $fpath = "";
}

if (s_bad_post("{$name}_md5", $fmd5)) {
    $fmd5 = "";
}

if (s_bad_post("{$name}_size", $fsize)) {
    $fsize = "";
}

... ... ...
```