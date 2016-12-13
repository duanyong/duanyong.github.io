package me.duanyong.blog.action;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by duanyong on 9/14/16.
 */

@Controller
@RequestMapping("/ss")
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String view() {
        return "/index";
    }

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String index() {
        return view();
    }
}
