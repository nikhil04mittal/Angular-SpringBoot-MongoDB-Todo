package com.todo

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

/**
 * Controller that is used to expose SeoLink features to external partners
 */

@Controller
@CompileStatic
class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView renderContextualWidget() {
        ModelAndView mav = new ModelAndView()
        mav.setViewName("index");
        return mav;
    }

}



