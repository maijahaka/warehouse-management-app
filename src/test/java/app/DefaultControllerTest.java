
package app;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DefaultControllerTest extends org.fluentlenium.adapter.junit.FluentTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @LocalServerPort
    private Integer port;
    
    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void getRequestToRootReturnsIndex() throws Exception {
        MvcResult res = mockMvc.perform(get("/"))
                .andReturn();
        
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("Please select a product category to view"));    
    }
    
    @Test
    public void getRequestToNonSpecifiedPathReturnsIndex() throws Exception {
        mockMvc.perform(get("/not-specified"))
                .andExpect(status().isOk());
        
        MvcResult res = mockMvc.perform(get("/not-specified"))
                .andReturn();
        
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("Please select a product category to view"));    
    }
    
    @Test
    public void clickingHomeOnNavbarReturnsIndex() {
        goTo("http://localhost:" + port);
        assertTrue(pageSource().contains("Please select a product category to view"));
        
        find(By.partialLinkText("Home")).click();
        assertTrue(pageSource().contains("Please select a product category to view"));
    }
    
}
