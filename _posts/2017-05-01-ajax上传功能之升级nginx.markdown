---
layout:         post
title:          "ajax上传功能之升级nginx 增加upload-module 和 upload-process模块"
subtitle:       "记录升级过程"
tags:           [nginx,upload-moudle,upload-proccess-module]
keyword:        "nginx"
description:    "升级nginx"
---

### 为什么记录？
1. 想采用线上服务器的的安装环境。
2. 让nginx负责上传和上传进度的功能。
由于需求方需要显示上传和上传完成的百分比, 而公司提供的Stroage无法提供百分比功能, 所以准备在产品线上提供一个统一的上传百分比的标准功能.
准备采用nginx的上传扩展结合PHP文件来实现. 此文章用来如何说明添加nginx的上传模块.

### 利用现在有nginx的编译参数和默认目录结果
```shell
nginx -V
nginx version: nginx/1.9
built by gcc 5.4.0 20160609 (Ubuntu 5.4.0-6ubuntu1~16.04.4)
built with OpenSSL 1.0.2g  1 Mar 2016
TLS SNI support enabled
configure arguments:
    --with-cc-opt='-g -O2 -fPIE -fstack-protector-strong -Wformat -Werror=format-security -Wdate-time -D_FORTIFY_SOURCE=2'
    --with-ld-opt='-Wl,-Bsymbolic-functions -fPIE -pie -Wl,-z,relro -Wl,-z,now'
    --prefix=/usr/share/nginx
    --conf-path=/etc/nginx/nginx.conf
    --http-log-path=/var/log/nginx/access.log
    --error-log-path=/var/log/nginx/error.log
    --lock-path=/var/lock/nginx.lock
    --pid-path=/run/nginx.pid
    --http-client-body-temp-path=/var/lib/nginx/body
    --http-fastcgi-temp-path=/var/lib/nginx/fastcgi
    --http-proxy-temp-path=/var/lib/nginx/proxy
    --http-scgi-temp-path=/var/lib/nginx/scgi
    --http-uwsgi-temp-path=/var/lib/nginx/uwsgi
    --with-pcre-jit
    --with-http_stub_status_module
    --with-http_auth_request_module
    --with-http_addition_module
    --with-http_dav_module
    --with-http_gunzip_module
    --with-http_gzip_static_module
    --with-http_v2_module
    --with-http_sub_module
    --with-http_xslt_module
    --with-stream
    --with-stream_ssl_module
    --with-threads
```
从上面的信息可以得知基本的编译参数, 我们在不改变其配置的情况下增加上传和上传百分比模块

```shell
~ cd /tmp & git clone https://github.com/Austinb/nginx-upload-module.git
~ cd /tmp & git clone https://github.com/masterzen/nginx-upload-progress-module.git
~ cd /tmp & wget http://nginx.org/download/nginx-1.13.4.tar.gz & tar -zxf nginx-1.13.4.tar.gz
~ cd /tmp/nginx-1.13.4
~ ./configure \
        --with-cc-opt='-g -O2 -fPIE -fstack-protector-strong -Wformat -Werror=format-security -Wdate-time -D_FORTIFY_SOURCE=2' \
        --with-ld-opt='-Wl,-Bsymbolic-functions -fPIE -pie -Wl,-z,relro -Wl,-z,now' \
        --prefix=/usr/share/nginx \
        --conf-path=/etc/nginx/nginx.conf \
        --http-log-path=/var/log/nginx/access.log \
        --error-log-path=/var/log/nginx/error.log \
        --lock-path=/var/lock/nginx.lock \
        --pid-path=/run/nginx.pid \
        --http-client-body-temp-path=/var/lib/nginx/body \
        --http-fastcgi-temp-path=/var/lib/nginx/fastcgi \
        --http-proxy-temp-path=/var/lib/nginx/proxy \
        --http-scgi-temp-path=/var/lib/nginx/scgi \
        --http-uwsgi-temp-path=/var/lib/nginx/uwsgi \
        --with-pcre-jit \
        --with-http_stub_status_module \
        --with-http_auth_request_module \
        --with-http_addition_module \
        --with-http_dav_module \
        --with-http_gunzip_module \
        --with-http_gzip_static_module \
        --with-http_v2_module \
        --with-http_sub_module \
        --with-http_xslt_module \
        --with-stream \
        --with-stream_ssl_module \
        --with-threads \
        --add-module=/tmp/nginx-upload-module \
        --add-module=/tmp/nginx-upload-progress-module
```

configure之后出现下面提示
```shell
Configuration summary
  + using threads
  + using system PCRE library
  + using system OpenSSL library
  + using system zlib library

  nginx path prefix: "/etc/nginx"
  nginx binary file: "/etc/nginx/sbin/nginx"
  nginx modules path: "/etc/nginx/modules"
  nginx configuration prefix: "/etc/nginx"
  nginx configuration file: "/etc/nginx/nginx.conf"
  nginx pid file: "/run/nginx.pid"
  nginx error log file: "/var/log/nginx/error.log"
  nginx http access log file: "/var/log/nginx/access.log"
  nginx http client request body temporary files: "/var/lib/nginx/body"
  nginx http proxy temporary files: "/var/lib/nginx/proxy"
  nginx http fastcgi temporary files: "/var/lib/nginx/fastcgi"
  nginx http uwsgi temporary files: "/var/lib/nginx/uwsgi"
  nginx http scgi temporary files: "/var/lib/nginx/scgi"

~ make && sudo make install
~ nginx -V
  nginx version: nginx/1.13.4
```


ajax上传功能之升级nginx 增加upload-module 和 upload-process模块 完成