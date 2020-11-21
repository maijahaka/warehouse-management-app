
package app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    
    @Autowired
    private ProductService productService;
    
    @Test
    public void allAddedProductCategoriesAreFound() throws Exception {
        assertTrue(productService.getProductCategories().containsKey("jackets"));
        assertTrue(productService.getProductCategories().containsKey("shirts"));
        assertTrue(productService.getProductCategories().containsKey("accessories"));
    }
    
    @Test
    public void productCategoriesThatHaveNotBeenAddedAreNotFound() throws Exception {
        assertFalse(productService.getProductCategories().containsKey("nonexistent"));
    }
    
}
