package com.sid.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
//public class TestController {
//
//	@RequestMapping(value = "test",  method = RequestMethod.GET)
//	public String home() {
//		return "test";
//	}
//}
@RequestMapping("/Test")
@Controller
public class TestController {
    
    @RequestMapping(value = "/{cat}", method = RequestMethod.GET)
    //@GetMapping(value = "/{category}")
    public String home(@PathVariable String category ) {
        return "test";
    }
}