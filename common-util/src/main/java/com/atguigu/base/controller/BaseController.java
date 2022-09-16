package com.atguigu.base.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;


public class BaseController {
    /**
     * 封装页面提交的分页参数及搜索条件: 1 pageNum 2 PageSize 3 RoleName
     * 如果request中没有, 第一次请求，则自己填入 默认值: (1, 10, null)
     * 如果有, 拿出来, 转成 string
     */
    protected Map<String, Object> getFilters(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> filters = new TreeMap();
        while(paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            if (values != null && values.length != 0) {
                if (values.length > 1) {
                    filters.put(paramName, values);
                } else {
                    filters.put(paramName, values[0]);
                }
            }
        }
        if(!filters.containsKey("pageNum")) {
            filters.put("pageNum", 1);
        }
        if(!filters.containsKey("pageSize")) {
            filters.put("pageSize", 10);
        }

        return filters;
    }
}
