package com.zitai.mall.controller;

import com.zitai.mall.form.UserLoginForm;
import com.zitai.mall.form.UserRegisterForm;
import com.zitai.mall.consts.MallConst;
import com.zitai.mall.enums.ResponseEnum;
import com.zitai.mall.pojo.User;
import com.zitai.mall.service.IUserService;
import com.zitai.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


/**
 * @data 2020/3/24 - 上午10:57
 * 描述：
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/user/register")
    public ResponseVo<User> register(@Valid @RequestBody UserRegisterForm userRegisterForm,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("注册提交的参数有误，{} {}",
                        bindingResult.getFieldError().getField(),
                        bindingResult.getFieldError().getDefaultMessage()
                    );
            return ResponseVo.error(ResponseEnum.FORM_ERROR,
                    bindingResult);
        }

        User user = new User();
        BeanUtils.copyProperties(userRegisterForm, user);
        return userService.register(user);
    }

    @PostMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  BindingResult bindingResult,
                                  HttpSession session){
        if(bindingResult.hasErrors()){
            return ResponseVo.error(ResponseEnum.FORM_ERROR,
                    bindingResult);
        }

        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        //设置session
        session.setAttribute(MallConst.CURRENT_USER, userResponseVo.getData());

        return userResponseVo;
    }

    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession session){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success(user);
    }

    @PostMapping("/user/logout")
    /**
     * {@link org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory} getSessionTimeoutInMinutes
     */
    public ResponseVo logout(HttpSession session){
        log.info("/user/logout sessionId={}",session.getId());
        session.removeAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success();

    }
}
