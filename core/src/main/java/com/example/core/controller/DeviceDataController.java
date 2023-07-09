package com.example.core.controller;


import com.example.common.page.PageInfoEnhance;
import com.example.common.result.Code;
import com.example.common.result.Result;
import com.example.core.domain.DeviceData;
import com.example.core.service.DeviceDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 设备数据信息(DeviceData)表控制层
 *
 * @author makejava
 * @since 2023-06-16 18:20:55
 */
@Api(tags = "设备数据信息(DeviceData)表控制层")
@Validated
@RestController
@RequestMapping("deviceData")
public class DeviceDataController {
    /**
     * 服务对象
     */
    @Resource
    private DeviceDataService deviceDataService;


    @ApiOperation(value = "通过pageInfo分页查询", notes = ""+
             "    /**\n" +
             "    * 通过pageInfo分页查询\n" +
             "    *\n" +
             "    * @param pageInfo      分页对象\n" +
             "    * @return 分页结果(pageInfo)\n" +
             "    */")
    @PostMapping("query")
    public ResponseEntity queryByPage(@RequestBody @Valid PageInfoEnhance<DeviceData> pageInfo){
        return ResponseEntity.ok(this.deviceDataService.queryByPage(pageInfo));
    }



//    @ApiOperation(value = "通过设备(deviceUuid)查询最新的一条数据", notes = ""+
//             "    /**\n" +
//             "    * 通过主键查询单条数据\n" +
//             "    *\n" +
//             "    * @param deviceUuid \n" +
//             "    * @return 设备数据DeviceData\n" +
//             "    */")
//    @GetMapping("{deviceUuid}")
//    public Result getLatestOneByDeviceUuid(@PathVariable("deviceUuid") String deviceUuid) {
//        DeviceData deviceData = this.deviceDataService.getLatestOneByDeviceUuid(deviceUuid);
//        Integer code = deviceData != null ? Code.GET_OK : Code.GET_ERR;
//        String msg = deviceData != null ? "" : "数据查询失败，请重试！";
//        return new Result(code,deviceData,msg);
//    }

//    @ApiOperation(value = "通过主键(id)查询单条数据", notes = ""+
//             "    /**\n" +
//             "    * 通过主键查询单条数据\n" +
//             "    *\n" +
//             "    * @param id 主键\n" +
//             "    * @return 单条数据(DeviceData)\n" +
//             "    */")
//    @GetMapping("{id}")
//    public Result queryById(@PathVariable("id") Integer id) {
//        DeviceData deviceData = this.deviceDataService.queryById(id);
//        Integer code = deviceData != null ? Code.GET_OK : Code.GET_ERR;
//        String msg = deviceData != null ? "" : "数据查询失败，请重试！";
//        return new Result(code,deviceData,msg);
//    }

    @ApiOperation(value = "新增数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param deviceData 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PostMapping
    public Result add(@RequestBody @Valid DeviceData deviceData) {
        boolean flag = this.deviceDataService.insert(deviceData);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @ApiOperation(value = "编辑数据", notes = ""+
             "    /**\n" +
             "    * 新增数据\n" +
             "    *\n" +
             "    * @param deviceData 实体\n" +
             "    * @return 是否成功()\n" +
             "    */")
    @PutMapping
    public Result edit(@RequestBody @Valid DeviceData deviceData) {
        boolean flag = this.deviceDataService.update(deviceData);
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
        boolean flag = this.deviceDataService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

}

