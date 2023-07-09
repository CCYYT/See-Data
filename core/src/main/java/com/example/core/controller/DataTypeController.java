package com.example.core.controller;

import com.example.core.domain.DataType;
import com.example.core.service.DataTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.example.common.page.PageInfoEnhance;
import com.example.common.result.Code;
import com.example.common.result.Result;

import java.util.List;

/**
 * 设备类型信息(DataType)表控制层
 *
 * @author makejava
 * @since 2023-06-13 21:38:12
 */
@Api(tags = "设备类型信息(DataType)表控制层")
@Validated
@RestController
@RequestMapping("dataType")
public class DataTypeController {
    /**
     * 服务对象
     */
    @Resource
    private DataTypeService dataTypeService;

    @GetMapping
    public Result getAll(){
        List<DataType> dataTypes = this.dataTypeService.getAll();
        Integer code = dataTypes != null ? Code.GET_OK : Code.GET_ERR;
        String msg = dataTypes != null ? "" : "数据查询失败，请重试！";
        return new Result(code,dataTypes,msg);
    }


    @ApiOperation(value = "通过pageInfo分页查询", notes = ""+
             "    /**\n" +
             "    * 通过pageInfo分页查询\n" +
             "    *\n" +
             "    * @param pageInfo      分页对象\n" +
             "    * @return 分页结果(pageInfo)\n" +
             "    */")
    @PostMapping("query")
    public ResponseEntity queryByPage(@RequestBody @Valid PageInfoEnhance<DataType> pageInfo){
        return ResponseEntity.ok(this.dataTypeService.queryByPage(pageInfo));
    }

    @ApiOperation(value = "通过主键(id)查询单条数据", notes = ""+
             "    /**\n" +
             "    * 通过主键查询单条数据\n" +
             "    *\n" +
             "    * @param id 主键\n" +
             "    * @return 单条数据(DataType)\n" +
             "    */")
    @GetMapping("{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        DataType dataType = this.dataTypeService.queryById(id);
        Integer code = dataType != null ? Code.GET_OK : Code.GET_ERR;
        String msg = dataType != null ? "" : "数据查询失败，请重试！";
        return new Result(code,dataType,msg);
    }

    @ApiOperation(value = "新增数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param dataType 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PostMapping
    public Result add(@RequestBody @Valid DataType dataType) {
        boolean flag = this.dataTypeService.insert(dataType);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @ApiOperation(value = "编辑数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param dataType 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PutMapping
    public Result edit(@RequestBody @Valid DataType dataType) {
        boolean flag = this.dataTypeService.update(dataType);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @ApiOperation(value = "删除数据", notes = ""+
             "    /**\n" +
             "    * 删除数据\n" +
             "    *\n" +
             "    * @param id 主键\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        boolean flag = this.dataTypeService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

}

