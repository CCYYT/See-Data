package com.example.common.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import java.util.LinkedHashMap;
import java.util.Map;

@ApiModel("分页信息类 (增强)")
public class PageInfoEnhance<T> extends PageInfo<T>{
    @JsonIgnore
    private Map<String,String> sortList = new LinkedHashMap<>();

    public Map<String, String> getSortList() {
        if(getFieldRule()!=null){
            getFieldRule().forEach((k,v)->{
                String sort = v.getSort();
                if(sort != null){
                    sortList.put(k, sort);
                }
            });
        }
        return sortList;
    }
}
