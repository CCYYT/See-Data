package com.example.core.controller;

import com.example.common.page.PageInfoEnhance;
import com.example.common.result.Code;
import com.example.common.result.Result;
import com.example.core.domain.Remind;
import com.example.core.service.RemindService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 提示接收中间表(Remind)表控制层
 *
 * @author makejava
 * @since 2023-07-02 15:04:56
 */
@Api(tags = "提示接收中间表(Remind)表控制层")
@Validated
@RestController
@RequestMapping("remind")
public class RemindController {
    /**
     * 服务对象
     */
    @Resource
    private RemindService remindService;


    @ApiOperation(value = "通过pageInfo分页查询", notes = ""+
             "    /**\n" +
             "    * 通过pageInfo分页查询\n" +
             "    *\n" +
             "    * @param pageInfo      分页对象\n" +
             "    * @return 分页结果(pageInfo)\n" +
             "    */")
    @PostMapping("query")
    public ResponseEntity queryByPage(@RequestBody @Valid PageInfoEnhance<Remind> pageInfo){
        return ResponseEntity.ok(this.remindService.queryByPage(pageInfo));
    }

    @ApiOperation(value = "通过主键(id)查询单条数据", notes = ""+
             "    /**\n" +
             "    * 通过主键查询单条数据\n" +
             "    *\n" +
             "    * @param id 主键\n" +
             "    * @return 单条数据(Remind)\n" +
             "    */")
    @GetMapping("{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        Remind remind = this.remindService.queryById(id);
        Integer code = remind != null ? Code.GET_OK : Code.GET_ERR;
        String msg = remind != null ? "" : "数据查询失败，请重试！";
        return new Result(code,remind,msg);
    }

    @ApiOperation(value = "新增数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param remind 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PostMapping
    public Result add(@RequestBody @Valid Remind remind) {
        boolean flag = this.remindService.insert(remind);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @ApiOperation(value = "编辑数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param remind 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PutMapping
    public Result edit(@RequestBody @Valid Remind remind) {
        boolean flag = this.remindService.update(remind);
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
        boolean flag = this.remindService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

}

