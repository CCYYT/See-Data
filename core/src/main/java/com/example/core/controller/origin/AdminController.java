//package com.example.mqtt_springBoot_demo.controller.origin;
//
//import com.example.mqtt_springBoot_demo.common.page.PageInfo;
//import com.example.mqtt_springBoot_demo.common.result.Code;
//import com.example.mqtt_springBoot_demo.common.result.Result;
//import com.example.mqtt_springBoot_demo.domain.Admin;
//import com.example.mqtt_springBoot_demo.service.AdminService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.validation.Valid;
//
//
///**
// * (Admin)表控制层
// *
// * @author makejava
// * @since 2023-06-03 16:52:34
// */
//
//public class AdminController {
//    /**
//     * 服务对象
//     */
//    @Resource
//    private AdminService adminService;
//
//    /**
//     * 分页查询
//     *
//     * @param admin 筛选条件
//     * @param pageInfo      分页对象
//     * @return 查询结果
//     */
////    @GetMapping
////    public Result queryByPage(Admin admin,
////                              @RequestParam(defaultValue = "0") int page,
////                              @RequestParam(defaultValue = "10") int size,
////                              @RequestParam(defaultValue = "id") String sortField,
////                              @RequestParam(defaultValue = "asc") String sortDirection) {
////        PageRequest pageRequest = PageRequest.of(page,size,Sort.by(Sort.Direction.fromString(sortDirection),sortField));
////
////        List<Admin> admins = this.adminService.queryByPage(admin,pageInfo);
////        Integer code = admins != null ? Code.GET_OK : Code.GET_ERR;
////        String msg = admins != null ? "" : "数据查询失败，请重试！";
////        return new Result(code,admins,msg);
////    }
//
////    @GetMapping
////    public Result queryByPage(Admin admin, PageInfo<Admin> pageInfo) {
////        try{
////            List<SortInfo> sorts = new ArrayList<>();
////            for (String item : pageInfo.getSorts()) {
////                String[] sort = item.split(":");
////
////                sorts.add(new SortInfo(sort[0],"true".equals(sort[1])));
////            }
////            pageInfo.setSortList(sorts);
////        }catch (ArrayIndexOutOfBoundsException e){
////            return new Result(Code.GET_ERR,null,"请求参数有误");
////        }
////        PageInfo pages = this.adminService.queryByPage(admin,pageInfo);
////        Integer code = pages != null ? Code.GET_OK : Code.GET_ERR;
////        String msg = pages != null ? "" : "数据查询失败，请重试！";
////        return new Result(code,pages,msg);
////    }
//
//    @PostMapping("query")
//    public ResponseEntity queryByPageInfo(@RequestBody @Valid PageInfo<Admin> pageInfo) {
//        PageInfo pages = this.adminService.queryByPage(pageInfo);
//        return ResponseEntity.ok(pages);
//    }
//
//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("{id}")
//    public ResponseEntity queryById(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok(this.adminService.queryById(id));
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param admin 实体
//     * @return 新增结果
//     */
//    @PostMapping
//    public Result add(Admin admin) {
//        boolean flag = this.adminService.insert(admin);
//        return new Result(flag ? Code.SAVE_OK: Code.SAVE_ERR,flag);
//    }
//
//    /**
//     * 编辑数据
//     *
//     * @param admin 实体
//     * @return 编辑结果
//     */
//    @PutMapping
//    public Result edit(Admin admin) {
//        boolean flag = this.adminService.update(admin);
//        return new Result(flag ? Code.UPDATE_OK: Code.UPDATE_ERR,flag);
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param id 主键
//     * @return 删除是否成功
//     */
//    @DeleteMapping
//    public Result deleteById(Integer id) {
//        boolean flag = this.adminService.deleteById(id);
//        return new Result(flag ? Code.DELETE_OK: Code.DELETE_ERR,flag);
//    }
//
//}
//
