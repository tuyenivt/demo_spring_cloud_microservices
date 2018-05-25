package com.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.EmployeeApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmployeeApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void verifyFindAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andDo(print());
    }

    @Test
    public void verifyFindById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/5").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.organizationId").exists())
                .andExpect(jsonPath("$.departmentId").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.age").exists())
                .andExpect(jsonPath("$.position").exists())
                .andExpect(jsonPath("$.id").value(is(5)))
                .andExpect(jsonPath("$.organizationId").value(is(1)))
                .andExpect(jsonPath("$.departmentId").value(is(2)))
                .andExpect(jsonPath("$.name").value(is("Patrick Dempsey")))
                .andExpect(jsonPath("$.age").value(is(27)))
                .andExpect(jsonPath("$.position").value(is("Developer")))
                .andDo(print());
    }

    @Test
    public void verifyFindByIdBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/text").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andDo(print());
    }

    @Test
    public void verifyCustomMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/custom-message").accept(MediaType.TEXT_PLAIN))
                .andExpect(content().string(is("This is employee service with project default")))
                .andDo(print());
    }

}
