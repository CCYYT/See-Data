package com.example.core.controller;

import com.example.common.page.PageInfoEnhance;
import com.example.common.result.Code;
import com.example.common.result.Result;
import com.example.core.domain.UserGroup;
import com.example.core.service.UserGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户组信息(UserGroup)表控制层
 *
 * @author makejava
 * @since 2023-06-08 14:53:53
 */
@Api(tags = "用户组信息(UserGroup)表控制层")
@Validated
@RestController
@RequestMapping("userGroup")
public class UserGroupController {
    /**
     * 服务对象
     */
    @Resource
    private UserGroupService userGroupService;


    @ApiOperation(value = "通过pageInfo分页查询", notes = ""+
             "    /**\n" +
             "    * 通过pageInfo分页查询\n" +
             "    *\n" +
             "    * @param pageInfo      分页对象\n" +
             "    * @return 分页结果(pageInfo)\n" +
             "    */")
    @PostMapping("query")
    public ResponseEntity queryByPage(@RequestBody @Valid PageInfoEnhance<UserGroup> pageInfo){
        return ResponseEntity.ok(this.userGroupService.queryByPage(pageInfo));
    }

    @ApiOperation(value = "通过(uuid)查询单条数据", notes = ""+
             "    /**\n" +
             "    * 通过uuid查询单条数据\n" +
             "    *\n" +
             "    * @param uuid\n" +
             "    * @return 单条数据(UserGroup)\n" +
             "    */")
    @GetMapping("{uuid}")
    public Result queryByUuid(@PathVariable("uuid") String uuid) {
        UserGroup userGroup = this.userGroupService.queryByUuid(uuid);
        Integer code = userGroup != null ? Code.GET_OK : Code.GET_ERR;
        String msg = userGroup != null ? "" : "数据查询失败，请重试！";
        return new Result(code,userGroup,msg);
    }

    @ApiOperation(value = "新增数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param userGroup 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PostMapping
    public Result add(@RequestBody @Valid UserGroup userGroup) {
        boolean flag = this.userGroupService.insert(userGroup);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @ApiOperation(value = "编辑数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param userGroup 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PutMapping
    public Result edit(@RequestBody @Valid UserGroup userGroup) {
        boolean flag = this.userGroupService.update(userGroup);
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
    public Result deleteByUuid(@PathVariable String uuid) {
        boolean flag = this.userGroupService.deleteByUuid(uuid);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

}

