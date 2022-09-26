package com.atguigu;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.atguigu.util.AliyunOSSUtil;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author Joiy908
 * @date 2022/9/26
 */

public class TestFileUploadUtil {
    @Test
    public void testUpload() {
//        AliyunOSSUtil.uploadBytes("hello, shangHaoFang".getBytes(StandardCharsets.UTF_8),
//                "hello.txt");
    }

    @Test
    public void testDelete() {
//        AliyunOSSUtil.deleteFile("hello.txt");
    }
}
