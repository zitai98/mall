package com.zitai.mall;

import com.zitai.mall.consts.MallConst;
import com.zitai.mall.enums.ResponseEnum;
import com.zitai.mall.exception.UserLoginException;
import com.zitai.mall.pojo.User;
import com.zitai.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @data 2020/3/24 - 下午8:29
 * 描述：
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * true 表示继续流程，false表示中断
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        User user = (User)request.getSession().getAttribute(MallConst.CURRENT_USER);
        if(user == null){
            log.info("user=null");
//            response.getWriter().print("error");
            throw new UserLoginException();
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
        }
        return true;
    }
}
