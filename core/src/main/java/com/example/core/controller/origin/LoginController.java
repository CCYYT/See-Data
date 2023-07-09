package com.example.core.controller.origin;



import com.example.core.domain.Admin;
import com.example.core.domain.User;

import com.example.core.service.AdminService;
import com.example.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.common.result.Code;
import com.example.common.result.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(tags = "登入验证控制层")
@Validated
@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    UserService userService;

    @Resource
    AdminService adminService;

    @ApiOperation(value = "用户登入验证", notes = ""+
            "    /**\n" +
            "    * 用户登入验证\n" +
            "    *\n" +
            "    * @param user      用户对象\n" +
            "    * @return 验证结果\n" +
            "    */")
    @PostMapping("/user")
    public Result Login(HttpSession session,
                        @RequestBody @Valid @NotNull User user) {
        if (userService.verifyUserAndPassword(user)) {
            User u = userService.queryByUserName(user.getUserName());
            session.setAttribute("user",u);
            return new Result(Code.LOGIN_OK,u.getUuid(),"");
        }
        return new Result(Code.LOGIN_ERR,"用户或密码错误");
    }

    @ApiOperation(value = "管理员登入验证", notes = ""+
            "    /**\n" +
            "    * 管理员登入验证\n" +
            "    *\n" +
            "    * @param admin      管理员对象\n" +
            "    * @return 验证结果\n" +
            "    */")
    @PostMapping("/admin")
    public Result Login(HttpSession session,
                        @RequestBody @Valid @NotNull Admin admin) {
        if (adminService.verifyNameAndPassword(admin)) {
            Admin a = adminService.queryByName(admin.getName());
            session.setAttribute("admin",a);
            return new Result(Code.LOGIN_OK);
        }
        return new Result(Code.LOGIN_ERR,"用户或密码错误");
    }
}
