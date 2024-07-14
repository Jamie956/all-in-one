package org.example;

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

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:springMVC.xml"})
public class MappingControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Test
    public void mappingTest() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(MockMvcRequestBuilders.get("/mapping1")
                        .param("name", "11")
                        .param("address", "123")
                        .param("id", "12")
                        .header("Host", "localhost:8080")
                ).andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/mapping1")
                .param("name", "11")
                .param("address", "123")
                .param("id", "12")
                .header("Host", "localhost:8080")
        ).andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/mapping2")
                .param("name", "11")
                .param("address", "123")
                .param("id", "12")
                .header("Host", "localhost:8080")
        ).andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/mapping1")
                .param("name", "11")
                .param("password", "33")
                .param("address", "123")
                .param("id", "12")
                .header("Host", "localhost:8080")
        ).andExpect(status().isBadRequest());

        mvc.perform(MockMvcRequestBuilders.get("/mapping1")
                .param("name", "11")
                .param("address", "123")
                .param("id", "12")
        ).andExpect(status().isNotFound());
    }

    @Test
    public void expressionMatchTest() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(MockMvcRequestBuilders.get("/aaa")).andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/a1a")).andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/aa")).andExpect(status().isNotFound());
    }

    @Test
    public void pathVarTest() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(MockMvcRequestBuilders.get("/user/111")).andExpect(status().isOk());
    }

    @Test
    public void paramTest() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(MockMvcRequestBuilders.get("/paramTest").param("name", "22"))
                .andExpect(status().isOk());
    }

    @Test
    public void paramStrTest() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(MockMvcRequestBuilders.get("/paramStr")
                        .param("name", "22")
                        .param("address", "a")
                        .param("address", "b")
                )
                .andExpect(status().isOk());
    }

    @Test
    public void requestParamTest() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(MockMvcRequestBuilders.get("/requestParam").param("na_me", "22"))
                .andExpect(status().isOk());
    }

    @Test
    public void headerTest() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(MockMvcRequestBuilders.get("/header").header("Host", "111"))
                .andExpect(status().isOk());
    }

    @Test
    public void cookieTest() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(MockMvcRequestBuilders.get("/cookie").cookie(new Cookie("JESSIONID", "111")))
                .andExpect(status().isOk());
    }

    @Test
    public void objParamTest() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(MockMvcRequestBuilders.get("/objParam")
                        .param("name", "22")
                        .param("address", "a")
                        .param("address", "b")
                )
                .andExpect(status().isOk());
    }

}
