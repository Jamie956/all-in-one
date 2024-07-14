import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:springMVC.xml"})
public class ViewControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Test
    public void test() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(MockMvcRequestBuilders.get("/toView"))
                .andExpect(status().isOk())
                .andExpect(view().name("view"));
        mvc.perform(MockMvcRequestBuilders.get("/fwView"))
                .andExpect(status().isOk())
                .andExpect(view().name("forward:/toView"));
        mvc.perform(MockMvcRequestBuilders.get("/redView"))
                .andExpect(status().isFound());
        mvc.perform(MockMvcRequestBuilders.get("/mv"))
                .andExpect(status().isOk());
    }

}
