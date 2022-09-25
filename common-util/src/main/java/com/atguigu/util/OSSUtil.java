package com.atguigu.util;

/**
 * @author Joiy908
 * @date 2022/9/25
 */

public interface OSSUtil {
    //上传文件
    void uploadBytes(byte[] bytes, String objectKey);

    //删除文件
   void deleteFile(String objectKey);
}
