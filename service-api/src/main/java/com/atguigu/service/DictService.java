package com.atguigu.service;


import java.util.List;
import java.util.Map;

public interface DictService {
    List<Map<String,Object>> findZnodes(Long id);
}
