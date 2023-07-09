package com.example.common.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ApiModel("分页信息类")
public class PageInfo<T> {
    @Valid
//    @Size(min=1,message = "字段规则为空")
    @ApiModelProperty("条件信息列表 (字段名,条件信息)")
    private Map<@NotBlank(message ="字段名为空")String,@NotNull FieldInfo> fieldRule = new LinkedHashMap<>();
    @ApiModelProperty("第几页(从0开始)")
    private int page; //第几页
    @Range(min = 1,max = 50,message = "超出范围 范围(1-50)")
    @ApiModelProperty("每一页有多少行(默认10) 范围(1-50)")
    private int pageSize =10; //每一页的大小
    @ApiModelProperty("一共有多少页")
    private long totalPagesNum; //一共有多少页
    @ApiModelProperty("一共多少行")
    private long totalElementNum; //一共多少行
    @ApiModelProperty("结果字段")
    private List<T> content; //结果字段

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElementNum() {
        return totalElementNum;
    }

    public Map<String, FieldInfo> getFieldRule() {
        return fieldRule;
    }

    public void setFieldRule(Map<String, FieldInfo> fieldRule) {
        this.fieldRule = fieldRule;
    }

    public void setTotalElementNum(long totalElementNum) {
        this.totalElementNum = totalElementNum;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getOffset(){
        return this.page*this.pageSize;
    }

    public long getTotalPagesNum(){
        if(this.pageSize!=0) return (int)(this.totalElementNum/this.pageSize)+((this.totalElementNum%this.pageSize != 0)?1:0);
        return 0;
    }
}
