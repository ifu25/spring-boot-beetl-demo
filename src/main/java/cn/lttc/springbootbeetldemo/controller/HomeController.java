package cn.lttc.springbootbeetldemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件名
 * 作者：xinggang
 * 邮箱：willcoo@qq.com
 * 网址：https://weiku.co
 * 日期：2019-08-29
 * 说明：
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "admin");
        Map<String, Object> map = new HashMap<>();
        map.put("uid", "xg");
        map.put("pwd", "263499118");
        map.put("mail", "web@vcoo.cc");
        map.put("age", 16);
        model.addAttribute("data", map);
        return "index";
    }
}
