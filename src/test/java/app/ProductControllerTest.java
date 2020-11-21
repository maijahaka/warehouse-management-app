
package app;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest extends org.fluentlenium.adapter.junit.FluentTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @LocalServerPort
    private Integer port;
    
    @Autowired
    private ProductService productService;
    
    @Test
    public void statusOk() throws Exception {
        for (String category : productService.getProductCategories().keySet()) {        
            mockMvc.perform(get("/" + category))
                    .andExpect(status().isOk());
        }
    }
    
    @Test
    public void getRequestToCategoryReturnsProductPage() throws Exception {
        for (String category : productService.getProductCategories().keySet()) {        
            MvcResult res = mockMvc.perform(get("/" + category))
                    .andReturn();
            
            String content = res.getResponse().getContentAsString();
            assertTrue(content.contains("Product ID"));  
        }        
    }
    
    @Test
    public void clickingProductCategoryOnNavbarReturnsProductPage() {
        goTo("http://localhost:" + port);
        assertTrue(pageSource().contains("Please select a product category to view"));
        
        find(By.id("nav-jackets")).click();
        assertTrue(pageSource().contains("Product ID"));
        
        find(By.id("nav-shirts")).click();
        assertTrue(pageSource().contains("Product ID"));
        
        find(By.id("nav-accessories")).click();
        assertTrue(pageSource().contains("Product ID"));
    }
    
}
