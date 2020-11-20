
package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/{category}")
    public String listJackets(@PathVariable String category, Model model) {
        if (!productService.getProductCategories().containsKey(category)) { 
            return "notfound";
        }
        model.addAttribute("title", productService.getProductCategories().get(category));
        return "products";
    }
    
}
