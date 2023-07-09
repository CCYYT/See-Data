package com.example.core.controller;

import com.example.common.page.PageInfoEnhance;
import com.example.common.result.Code;
import com.example.common.result.Result;
import com.example.core.domain.WarningRules;
import com.example.core.service.WarningRulesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 预警规则(WarningRules)表控制层
 *
 * @author makejava
 * @since 2023-06-17 12:05:48
 */
@Api(tags = "预警规则(WarningRules)表控制层")
@Validated
@RestController
@RequestMapping("warningRules")
public class WarningRulesController {
    /**
     * 服务对象
     */
    @Resource
    private WarningRulesService warningRulesService;


    @ApiOperation(value = "通过pageInfo分页查询", notes = ""+
             "    /**\n" +
             "    * 通过pageInfo分页查询\n" +
             "    *\n" +
             "    * @param pageInfo      分页对象\n" +
             "    * @return 分页结果(pageInfo)\n" +
             "    */")
    @PostMapping("query")
    public ResponseEntity queryByPage(@RequestBody @Valid PageInfoEnhance<WarningRules> pageInfo){
        return ResponseEntity.ok(this.warningRulesService.queryByPage(pageInfo));
    }

    @ApiOperation(value = "通过主键(id)查询单条数据", notes = ""+
             "    /**\n" +
             "    * 通过主键查询单条数据\n" +
             "    *\n" +
             "    * @param id 主键\n" +
             "    * @return 单条数据(WarningRules)\n" +
             "    */")
    @GetMapping("{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        WarningRules warningRules = this.warningRulesService.queryById(id);
        Integer code = warningRules != null ? Code.GET_OK : Code.GET_ERR;
        String msg = warningRules != null ? "" : "数据查询失败，请重试！";
        return new Result(code,warningRules,msg);
    }

    @ApiOperation(value = "新增数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param warningRules 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PostMapping
    public Result add(@RequestBody @Valid WarningRules warningRules) {
        boolean flag = this.warningRulesService.insert(warningRules);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @ApiOperation(value = "编辑数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param warningRules 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PutMapping
    public Result edit(@RequestBody @Valid WarningRules warningRules) {
        boolean flag = this.warningRulesService.update(warningRules);
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
        boolean flag = this.warningRulesService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

}

