package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * handle request and response and view:
 * debug DispatcherServlet#doDispatch step mv = ha.handle(
 *
 * resolve view template:
 * debug DispatcherServlet#render step view = resolveViewName(
 */
@Controller
public class MyController {
    /**
     * http request setAttribute
     */
    @RequestMapping("/setRequestAttribute")
    public String setRequestAttribute(HttpServletRequest request) {
        request.setAttribute("myKey", "this is request attribute value");
        return "success";
    }
    /**
     * ModelAndView set key-value
     */
    @RequestMapping("/modelAndViewAttribute")
    public ModelAndView modelAndViewAttribute() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("myKey", "this is model and view value");
        mav.setViewName("success");
        return mav;
    }
    /**
     * Model addAttribute
     */
    @RequestMapping("/modelAttribute")
    public String modelAttribute(Model model) {
        model.addAttribute("myKey", "this is model value");
        return "success";
    }
    /**
     * Param Map add attribute
     */
    @RequestMapping("/paramMapSetAttribute")
    public String paramMapSetAttribute(Map<String, Object> map) {
        map.put("myKey", "this param map value");
        return "success";
    }
    /**
     * Param model map add attribute
     */
    @RequestMapping("/paramModelMapAttribute")
    public String paramModelMapAttribute(ModelMap model) {
        model.addAttribute("myKey", "this is param ModelMap value");
        return "success";
    }
    /**
     * set session Attribute
     */
    @RequestMapping("/setSessionAttribute")
    public String setSessionAttribute(HttpSession session) {
        session.setAttribute("myKey", "this is session Attribute");
        return "success";
    }
    /**
     * application context set Attribute
     */
    @RequestMapping("/contextAttribute")
    public String contextAttribute(HttpSession session) {
        ServletContext context = session.getServletContext();
        context.setAttribute("myKey", "this is context Attribute");
        return "success";
    }
}
