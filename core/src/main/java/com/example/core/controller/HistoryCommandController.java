package com.example.core.controller;

import com.example.common.page.PageInfoEnhance;
import com.example.common.result.Code;
import com.example.common.result.Result;
import com.example.common.util.CheckUtil;
import com.example.core.domain.HistoryCommand;
import com.example.core.domain.User;
import com.example.core.event.CommandEvent;
import com.example.core.service.HistoryCommandService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 历史命令信息(HistoryCommand)表控制层
 *
 * @author makejava
 * @since 2023-07-03 16:59:27
 */
@Api(tags = "历史命令信息(HistoryCommand)表控制层")
@Validated
@RestController
@RequestMapping("historyCommand")
public class HistoryCommandController {
    /**
     * 服务对象
     */
    @Resource
    private HistoryCommandService historyCommandService;

    @Resource
    private ApplicationContext applicationContext;

    @ApiOperation(value = "通过pageInfo分页查询", notes = ""+
             "    /**\n" +
             "    * 通过pageInfo分页查询\n" +
             "    *\n" +
             "    * @param pageInfo      分页对象\n" +
             "    * @return 分页结果(pageInfo)\n" +
             "    */")
    @PostMapping("query")
    public ResponseEntity queryByPage(@RequestBody @Valid PageInfoEnhance<HistoryCommand> pageInfo){
        return ResponseEntity.ok(this.historyCommandService.queryByPage(pageInfo));
    }

    @ApiOperation(value = "通过主键(id)查询单条数据", notes = ""+
             "    /**\n" +
             "    * 通过主键查询单条数据\n" +
             "    *\n" +
             "    * @param id 主键\n" +
             "    * @return 单条数据(HistoryCommand)\n" +
             "    */")
    @GetMapping("{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        HistoryCommand historyCommand = this.historyCommandService.queryById(id);
        Integer code = historyCommand != null ? Code.GET_OK : Code.GET_ERR;
        String msg = historyCommand != null ? "" : "数据查询失败，请重试！";
        return new Result(code,historyCommand,msg);
    }

    @ApiOperation(value = "新增数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param historyCommand 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PostMapping
    public Result add(@RequestBody @Valid HistoryCommand historyCommand, HttpSession httpSession) {

//        User user = (User) httpSession.getAttribute("user");
//        if(user==null){
//            return new Result(Code.UN_LOGIN,"请先登入");
//        }
//        historyCommand.setUserId(user.getId());

//        applicationContext.publishEvent(new CommandEvent(this,historyCommand));
        boolean flag = this.historyCommandService.insert(historyCommand);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @ApiOperation(value = "编辑数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param historyCommand 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PutMapping
    public Result edit(@RequestBody @Valid HistoryCommand historyCommand, HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//        if(user==null){
//            return new Result(Code.UN_LOGIN,"请先登入");
//        }
        boolean flag = this.historyCommandService.update(historyCommand);
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
        boolean flag = this.historyCommandService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

}

