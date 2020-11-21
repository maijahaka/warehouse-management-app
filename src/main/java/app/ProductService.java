
package app;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ProductService {
    
    private final Map<String, String> productCategories = new HashMap<String, String>()
    {{
        put("jackets", "Jackets");
        put("shirts", "Shirts");
        put("accessories", "Accessories");
    }};
        
}
