package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:springMVC.xml"})
public class MyControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Test
    public void filter() throws Exception {
        String responseMessage = "this is response message";
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/b")
                        .content(responseMessage)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(responseMessage, response.getContentAsString());
    }
}
