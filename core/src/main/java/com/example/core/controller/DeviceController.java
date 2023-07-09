package com.example.core.controller;

import com.example.common.domain.device.openFeign.DeviceValidate;
import com.example.common.domain.device.openFeign.DevicesValidate;
import com.example.common.page.PageInfoEnhance;
import com.example.common.result.Code;
import com.example.common.result.Result;
import com.example.core.domain.Device;
import com.example.core.domain.User;
import com.example.core.service.DeviceService;
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
 * 设备信息(Device)表控制层
 *
 * @author makejava
 * @since 2023-06-16 17:17:26
 */
@Api(tags = "设备信息(Device)表控制层")
@Validated
@RestController
@RequestMapping("device")
public class DeviceController {
    /**
     * 服务对象
     */
    @Resource
    private DeviceService deviceService;

//
//    @ApiOperation(value = "通过session中的用户信息 查询用户组中的所有设备", notes = ""+
//            "    /**\n" +
//            "    * @return List<Device>\n" +
//            "    */")
//    @GetMapping("group/deviceList")
//    public Result getDeviceListByGroupId(HttpSession session) {
//        User user = (User)session.getAttribute("user");
//        if(user == null){
//            return new Result(Code.UN_LOGIN,"请先登入");
//        }
//        Integer groupId = user.getGroupId();
//        List<Device> deviceList = this.deviceService.getDeviceListByGroupId(groupId);
//        Integer code = deviceList != null ? Code.GET_OK : Code.GET_ERR;
//        String msg = deviceList != null ? "" : "数据查询失败，请重试！";
//        return new Result(code,deviceList,msg);
//    }

    @ApiOperation(value = "通过session中的用户信息 查询用户组中的所有设备和设备的数据类型", notes = "" +
            "    /**\n" +
            "    * @return List<Device>\n" +
            "    */")
    @GetMapping("group/deviceAndDataTypeList")
    public Result getDeviceAndDataTypeListByGroupId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null){
            return new Result(Code.UN_LOGIN,"请先登入");
        }
        Integer groupId = user.getGroupId();
        List<Device> deviceList = this.deviceService.getDeviceAndDataTypeListByGroupId(groupId);
        Integer code = deviceList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = deviceList != null ? "" : "数据查询失败，请重试！";
        return new Result(code,deviceList,msg);
    }

    @ApiOperation(value = "通过pageInfo分页查询", notes = ""+
             "    /**\n" +
             "    * 通过pageInfo分页查询\n" +
             "    *\n" +
             "    * @param pageInfo      分页对象\n" +
             "    * @return 分页结果(pageInfo)\n" +
             "    */")
    @PostMapping("query")
    public ResponseEntity queryByPage(@RequestBody @Valid PageInfoEnhance<Device> pageInfo){
        return ResponseEntity.ok(this.deviceService.queryByPage(pageInfo));
    }

    @ApiOperation(value = "通过uuid查询单条数据", notes = ""+
             "    /**\n" +
             "    * 通过主键查询单条数据\n" +
             "    *\n" +
             "    * @param uuid\n" +
             "    * @return 单条数据(Device)\n" +
             "    */")
    @GetMapping("{uuid}")
    public Result queryById(@PathVariable("uuid") String uuid) {
        Device device = this.deviceService.queryByUuid(uuid);
        Integer code = device != null ? Code.GET_OK : Code.GET_ERR;
        String msg = device != null ? "" : "数据查询失败，请重试！";
        return new Result(code,device,msg);
    }

    @ApiOperation(value = "新增数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param device 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PostMapping
    public Result add(@RequestBody @Valid Device device) {
        boolean flag = this.deviceService.insert(device);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @ApiOperation(value = "验证设备 是否 属于 区域", notes = ""+
            "    /**\n" +
            "    * @param deviceValidate 实体类\n" +
            "    * @return 验证结果\n" +
            "    */")
    @PostMapping("validate")
    public Result validate(@RequestBody @Valid DeviceValidate deviceValidate) {
        boolean flag = this.deviceService.validate(deviceValidate);
        return new Result(flag ? Code.VERIFICATION_OK:Code.VERIFICATION_ERR,flag);
    }

    @ApiOperation(value = "验证多个设备 是否 属于 区域", notes = ""+
            "    /**\n" +
            "    * @param devicesValidate 实体类\n" +
            "    * @return 验证结果\n" +
            "    */")
    @PostMapping("validates")
    public Result validates(@RequestBody @Valid DevicesValidate devicesValidate) {
        boolean flag = this.deviceService.validates(devicesValidate);
        return new Result(flag ? Code.VERIFICATION_OK:Code.VERIFICATION_ERR,flag);
    }

    @ApiOperation(value = "编辑数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param device 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PutMapping
    public Result edit(@RequestBody @Valid Device device) {
        boolean flag = this.deviceService.update(device);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @ApiOperation(value = "删除数据", notes = ""+
             "    /**\n" +
             "    * 删除数据\n" +
             "    *\n" +
             "    * @param id 主键\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @DeleteMapping("/{uuid}")
    public Result deleteById(@PathVariable String uuid) {
        boolean flag = this.deviceService.deleteByUuid(uuid);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

}

