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
@ContextConfiguration("classpath:springMVC.xml")
public class MyControllerTest {
    @Autowired
    private WebApplicationContext context;

    public void assertContainText(String url, String containText) throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("success"))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(response.getContentAsString().contains(containText));
    }

    @Test
    public void test() throws Exception {
        // debug MockHttpServletRequest#setAttribute Condition name.equals("myKey")
        assertContainText("/setRequestAttribute","this is request attribute value");
        // debug MockHttpServletRequest#setAttribute Condition name.equals("myKey")
        assertContainText("/modelAndViewAttribute", "this is model and view value");
        // debug MockHttpServletRequest#setAttribute Condition name.equals("myKey")
        assertContainText("/modelAttribute", "this is model value");
        // debug MockHttpServletRequest#setAttribute Condition name.equals("myKey")
        assertContainText("/paramMapSetAttribute", "this param map value");
        // debug MockHttpServletRequest#setAttribute Condition name.equals("myKey")
        assertContainText("/paramModelMapAttribute", "this is param ModelMap value");
        // debug MockHttpSession#getAttribute
        assertContainText("/setSessionAttribute", "this is session Attribute");
        // debug MockServletContext#getAttribute Condition "myKey".equalsIgnoreCase(name)
        assertContainText("/contextAttribute", "this is context Attribute");
    }
}
