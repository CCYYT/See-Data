package com.example.core.controller;

import com.example.common.page.PageInfoEnhance;
import com.example.common.result.Code;
import com.example.common.result.Result;
import com.example.core.domain.Device;
import com.example.core.domain.User;
import com.example.core.domain.Warning;
import com.example.core.service.WarningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 警报信息(Warning)表控制层
 *
 * @author makejava
 * @since 2023-06-17 11:55:35
 */
@Api(tags = "警报信息(Warning)表控制层")
@Validated
@RestController
@RequestMapping("warning")
public class WarningController {
    /**
     * 服务对象
     */
    @Resource
    private WarningService warningService;

    @ApiOperation(value = "通过pageInfo分页查询", notes = ""+
             "    /**\n" +
             "    * 通过pageInfo分页查询\n" +
             "    *\n" +
             "    * @param pageInfo      分页对象\n" +
             "    * @return 分页结果(pageInfo)\n" +
             "    */")
    @PostMapping("query")
    public ResponseEntity queryByPage(@RequestBody @Valid PageInfoEnhance<Warning> pageInfo){
        return ResponseEntity.ok(this.warningService.queryByPage(pageInfo));
    }

    @ApiOperation(value = "通过主键(id)查询单条数据", notes = ""+
             "    /**\n" +
             "    * 通过主键查询单条数据\n" +
             "    *\n" +
             "    * @param id 主键\n" +
             "    * @return 单条数据(Warning)\n" +
             "    */")
    @GetMapping("{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        Warning warning = this.warningService.queryById(id);
        Integer code = warning != null ? Code.GET_OK : Code.GET_ERR;
        String msg = warning != null ? "" : "数据查询失败，请重试！";
        return new Result(code,warning,msg);
    }

    @ApiOperation(value = "通过session中的用户信息 查询用户组中的所有设备的警报信息", notes = "" +
            "    /**\n" +
            "    * @return List<Device>\n" +
            "    */")
    @GetMapping("/group/deviceWarningList")
    public Result getDeviceWarningListByGroupId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null){
            return new Result(Code.UN_LOGIN,"请先登入");
        }
        Integer groupId = user.getGroupId();
        List<Warning> deviceList = this.warningService.getDeviceWarningListByGroupId(groupId);
        Integer code = deviceList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = deviceList != null ? "" : "数据查询失败，请重试！";
        return new Result(code,deviceList,msg);
    }


    @ApiOperation(value = "新增数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param warning 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PostMapping
    public Result add(@RequestBody @Valid Warning warning) {
        boolean flag = this.warningService.insert(warning);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @ApiOperation(value = "编辑数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param warning 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PutMapping
    public Result edit(@RequestBody @Valid Warning warning) {
        boolean flag = this.warningService.update(warning);
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
        boolean flag = this.warningService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

}

