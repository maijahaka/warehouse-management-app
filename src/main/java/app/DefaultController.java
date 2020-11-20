
package app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home");
        return "index";
    }
    
    @GetMapping("*")
    public String pageNotFound(Model model) {
        return "notfound";
    }
    
}
