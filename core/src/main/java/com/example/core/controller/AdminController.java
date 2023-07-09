package com.example.core.controller;

import com.example.common.page.PageInfoEnhance;
import com.example.common.result.Code;
import com.example.common.page.PageInfo;
import com.example.common.result.Result;
import com.example.core.domain.Admin;
import com.example.core.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2023-06-03 16:52:34
 */
@Api(tags = "(Admin)表控制层")
@Validated
@RestController
@RequestMapping("admin")
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    @ApiOperation(value = "通过pageInfo分页查询", notes = "" +
            "    /**\n" +
            "     * 通过pageInfo分页查询\n" +
            "     *\n" +
            "     * @param pageInfo      分页对象\n" +
            "     * @return 查询结果(pageInfo)\n" +
            "     */")
    @PostMapping("query")
    public ResponseEntity queryByPageInfo(@RequestBody @Valid PageInfoEnhance<Admin> pageInfo) {
        PageInfo pages = this.adminService.queryByPage(pageInfo);
        return ResponseEntity.ok(pages);
    }

    /**
     * 通过uuid查询单条数据
     *
     * @param uuid
     * @return 单条数据
     */
    @GetMapping("{uuid}")
    public Result queryByUuid(@PathVariable("uuid") String uuid) {
        Admin admin = this.adminService.queryByUuid(uuid);
        Integer code = admin != null ? Code.GET_OK : Code.GET_ERR;
        String msg = admin != null ? "" : "数据查询失败，请重试！";
        return new Result(code,admin,msg);
    }

    /**
     * 新增数据
     *
     * @param admin 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody Admin admin) {
        boolean flag = this.adminService.insert(admin);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    /**
     * 编辑数据
     *
     * @param admin 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(@RequestBody Admin admin) {
        boolean flag = this.adminService.update(admin);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    /**
     * 删除数据
     *
     * @param uuid
     * @return 删除是否成功
     */
    @DeleteMapping("/{uuid}")
    public Result deleteById(@PathVariable Integer uuid) {
        boolean flag = this.adminService.deleteById(uuid);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

}

