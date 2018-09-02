package com.yuxiao.websocket.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: Yuxiao
 * @Date: 2018/9/1 11:11
 * @Description:
 */
@Controller
public class MyController {

    @RequestMapping(value = {"/", "index.html"})
    public String index(){
        return "index";
    }

}
