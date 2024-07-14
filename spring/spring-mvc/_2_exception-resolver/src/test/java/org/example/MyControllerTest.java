package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:springMVC.xml"})
public class MyControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Test
    public void error() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/error"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("error"))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(response.getContentAsString().contains("java.lang.ArithmeticException: / by zero"));
    }

    @Test
    public void index() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("index"))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(response.getContentAsString().contains("hello"));
    }

}
