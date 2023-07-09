package com.example.core.controller;

import com.example.core.domain.User;
import com.example.common.domain.mail.SimpleMail;
import com.example.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import com.example.common.result.Code;
import com.example.common.result.Result;
import com.example.common.util.CheckUtil;
import com.example.common.util.VerificationCodeUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.Duration;

@Api(tags = "邮箱控制层")
@RestController
@RequestMapping("mail")
public class MailController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    private UserService userService;

    @ApiOperation(value = "发送邮箱验证码", notes = ""+
            "    /**\n" +
            "    * 发送邮箱验证码\n" +
            "    *\n" +
            "    * @param mailAdr  邮箱地址\n" +
            "    * @return 发送状态\n" +
            "    */")
    @GetMapping("send")
    public Result sendVerificationCode(HttpSession httpSession,
                                       @RequestParam("mailAdr") String mailAdr){
        User user = (User) httpSession.getAttribute("user");
        if(user==null){
            return new Result(Code.GET_ERR,"请先登入");
        }
        if(!CheckUtil.checkIsEmail(mailAdr)) {
            return new Result(Code.GET_ERR, "邮箱地址错误");
        }
        String userUuid = (user).getUuid();
        if(userUuid == null){
            return new Result(Code.GET_ERR,"用户状态错误");
        }
        ValueOperations svo = stringRedisTemplate.opsForValue();
        if (svo.get(userUuid + mailAdr) != null) {
            return new Result(Code.GET_ERR, "请勿重复发送");
        }
        String verificationCode = VerificationCodeUtil.generateVerificationCode();
        svo.set(userUuid + mailAdr, verificationCode, Duration.ofSeconds(60));

        /**
         * 调用邮箱服务 发送验证码邮件
         * */
        rabbitTemplate.convertAndSend("mail", "mail.simpleMail", SimpleMail.generateVerificationCodeMail(mailAdr, verificationCode));

        return new Result(Code.GET_OK, "验证码已发送 有效期60秒");

    }

    @ApiOperation(value = "用户绑定邮箱", notes = ""+
            "    /**\n" +
            "    * 用户绑定邮箱\n" +
            "    *\n" +
            "    * @param mailAdr   邮箱地址" +
            "      @param verificationCode  验证码  " +
            "    * @return 绑定状态\n" +
            "    */")
    @GetMapping("bind")
    public Result verificationCode(HttpSession httpSession,
                                   @RequestParam("verificationCode") String verificationCode,
                                   @RequestParam("mailAdr") String mailAdr){
        User user = (User) httpSession.getAttribute("user");
        if(user==null){
            return new Result(Code.GET_ERR,"请先登入");
        }
        if(!CheckUtil.checkIsEmail(mailAdr)){
            return new Result(Code.GET_ERR,"邮箱地址错误");
        }else if (!CheckUtil.checkIsVerificationCode(verificationCode)){
            return new Result(Code.GET_ERR,"不是一个合法的验证码");
        }
        String userUuid = user.getUuid();
        ValueOperations<String,String> svo = stringRedisTemplate.opsForValue();
        String v = svo.get(userUuid + mailAdr);
        if(v == null) return new Result(Code.GET_ERR,"验证码不存在");
        if(verificationCode.equals(v)){
            user.setMail(mailAdr);
            userService.update(user);
            stringRedisTemplate.delete(userUuid + mailAdr);
            return new Result(Code.GET_OK,"绑定成功");
        }
        return new Result(Code.GET_ERR,"验证码错误");
    }

}
