package com.example.core.controller;

import com.example.common.page.PageInfoEnhance;
import com.example.common.result.Code;
import com.example.common.result.Result;
import com.example.core.domain.Area;
import com.example.core.service.AreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 区域信息(Area)表控制层
 *
 * @author makejava
 * @since 2023-06-13 21:20:44
 */
@Api(tags = "区域信息(Area)表控制层")
@Validated
@RestController
@RequestMapping("area")
public class AreaController {
    /**
     * 服务对象
     */
    @Resource
    private AreaService areaService;

    @ApiOperation(value = "通过pageInfo分页查询", notes = ""+
             "    /**\n" +
             "    * 通过pageInfo分页查询\n" +
             "    *\n" +
             "    * @param pageInfo      分页对象\n" +
             "    * @return 分页结果(pageInfo)\n" +
             "    */")
    @PostMapping("query")
    public ResponseEntity queryByPage(@RequestBody @Valid PageInfoEnhance<Area> pageInfo){
        return ResponseEntity.ok(this.areaService.queryByPage(pageInfo));
    }

    @ApiOperation(value = "通过uuid查询单条数据", notes = ""+
             "    /**\n" +
             "    * 通过主键查询单条数据\n" +
             "    *\n" +
             "    * @param uuid\n" +
             "    * @return 单条数据(Area)\n" +
             "    */")
    @GetMapping("{uuid}")
    public Result queryById(@PathVariable("uuid") String uuid) {
        Area area = this.areaService.queryByUuid(uuid);
        Integer code = area != null ? Code.GET_OK : Code.GET_ERR;
        String msg = area != null ? "" : "数据查询失败，请重试！";
        return new Result(code,area,msg);
    }

    @ApiOperation(value = "新增数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param area 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PostMapping
    public Result add(@RequestBody @Valid Area area) {
        boolean flag = this.areaService.insert(area);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @ApiOperation(value = "编辑数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param area 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PutMapping
    public Result edit(@RequestBody @Valid Area area) {
        boolean flag = this.areaService.update(area);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @ApiOperation(value = "删除数据", notes = ""+
             "    /**\n" +
             "    * 删除数据\n" +
             "    *\n" +
             "    * @param uuid\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @DeleteMapping("/{uuid}")
    public Result deleteById(@PathVariable String uuid) {
        boolean flag = this.areaService.deleteByUuid(uuid);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

}

