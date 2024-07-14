package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
    @RequestMapping("/mv")
    public ModelAndView modelAndView() {
        return new ModelAndView("view");
    }

    /**
     * http://localhost:8080/toView
     * debug
     * DispatcherServlet#render -> view = resolveViewName(..) -> create ThymeleafView
     */
    @RequestMapping("/toView")
    public String toView() {
        return "view";
    }

    /**
     * http://localhost:8080/fwView*
     * debug
     * DispatcherServlet#render -> view = resolveViewName(..) -> create internal resource view
     */
    @RequestMapping("/fwView")
    public String fwView() {
        return "forward:/toView";
    }

    /**
     * http://localhost:8080/view3
     * redirect to /view1, url -> http://localhost:8080/view1
     * debug
     * DispatcherServlet#render -> view = resolveViewName(..) -> create redirect view
     */
    @RequestMapping("/redView")
    public String redView() {
        return "redirect:/toView";
    }

}
